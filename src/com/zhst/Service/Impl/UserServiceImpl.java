package com.zhst.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Class_bean;
import com.zhst.Bean.Course;
import com.zhst.Bean.Semester_Course;
import com.zhst.Bean.User;
import com.zhst.Bean.User_Class;
import com.zhst.Dao.UserDao;
import com.zhst.Service.ClassService;
import com.zhst.Service.CourseService;
import com.zhst.Service.UserService;
import com.zhst.Util.MD5;

@SuppressWarnings("serial")
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService{

	@Autowired
	public UserDao userDao;

	@Autowired
	public CourseService courseService;
	
	@Autowired
	public ClassService classService;
	
	/**
	 * 更新用户信息
	 */
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	/**
	 * 获取用户信息
	 */
	@Override
	public User getUserById(int Id) {
		return userDao.getUserById(User.class, Id);
	}

	/**
	 * 获取所有用户信息
	 */
	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	/**
	 * 根据学号获取用户信息
	 */
	@Override
	public User getUserByAccountId(String accountId) {
		return userDao.getUserByAccountId(accountId);
	}

	/**
	 * 删除用户
	 */
	@Override
	public boolean delUser(String accountId) {
		User user = this.getUserByAccountId(accountId);
		user.setValid(0);
		return userDao.delUser(user);
	}

	@Override
	public int getUserCount() {
		return userDao.getTotalCount(" from User as t where t.valid = '1'");
	}

	@Override
	public List<User> getUserItem(int start, int pagesize,String type,String username)
	{
		String queryUsername = "";
		if((username == null) || ("".equals(username))){
		}else{
			queryUsername = " and u.userName like '%"+username+"%'";
		}
		if((type == null) || ("0".equals(type))){
		return userDao.find( "from User u where u.valid = '1'"+queryUsername, start, pagesize);
	    }else{
	    return userDao.find( "from User u where u.valid = '1' and u.role = "+type+""+queryUsername, start, pagesize);
	 	 }
	}

	/**
	 * 根据教师id获取学生的人数
	 */ 
	@Override
	public int getStudentCountByTeacher(int teacherid,int courseid) {
		int count = 0;
		//获取教师所教对应的课程
		List<Semester_Course> course = userDao.getCourseByTeacherId(teacherid);
		//根据每个课程id获得开课的班级id
		for(int i = 0;i<course.size();i++){
		if(course.get(i).getCourse().getCourseId()==courseid){
			List<Class_Course> classOfCourse = userDao.getClassByCourseId(teacherid,courseid);
			//根据班级id，找出在该教学班中的学生id
			for(int j = 0;j <classOfCourse.size();j++){
				List<User_Class> studentOfTeacher = userDao.getStudentByClassId(classOfCourse.get(j).getCourseClass().getClassId(),0,0,false);
			count = count + studentOfTeacher.size();
			}
		}
		}
			return count;
	}

	/**
	 * 根据教师id和输入的用户名获取学生的人数
	 */ 
	@Override
	public int getStudentCountByTeacherAndName(int teacherid,StringBuffer courseidBuff,String userName) {
		int count = 0;
		//记录教师开设的教学班的id
		StringBuffer classid = new StringBuffer();
	    //根据每个课程id获得开课的班级id
		List<Class_bean> classcourse = classService.getAllteachClassByCourse(courseidBuff, teacherid);
		for(int i = 0;i<classcourse.size();i++){
		if(i!=(classcourse.size()-1)){
			classid.append(classcourse.get(i).getClassId()+",");
		}else{
			classid.append(classcourse.get(i).getClassId());
		}
		}	
			List<User_Class> classOfCourse = userDao.getClassByClassIdAndName(classid,userName,0,0,true);
			count = count + classOfCourse.size();
			return count;
	}
	
	/**
	 * 根据教师id获取学生信息
	 * 可以根据课程id进行筛选
	 */
	@Override
	public List<Map<String,Object>> getStudentItem(int start, int pagesize, int teacherid,int courseId) {
		//记录某个教师某个课程所开设的教学班的id
		StringBuffer classidsOfteacher =new StringBuffer();
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		//获取教师所教对应的课程
				List<Semester_Course> course = userDao.getCourseByTeacherId(teacherid);
				//根据每个课程id获得开课的班级id
				for(int i = 0;i<course.size();i++){
					if(course.get(i).getCourse().getCourseId()==courseId){
					//获得对应课程
//					Course courseOfTeacher = courseService.findCourseByCourseId(courseId);
					List<Class_Course> classOfCourse = userDao.getClassByCourseId(teacherid,courseId);
					for(int j = 0;j <classOfCourse.size();j++){
					if(j !=classOfCourse.size()-1){
					classidsOfteacher.append(classOfCourse.get(j).getCourseClass().getClassId()+",");
					}else{
						classidsOfteacher.append(classOfCourse.get(j).getCourseClass().getClassId());	
					}
					}
					 //根据班级id，找出在该教学班中的学生id					
						List<User_Class> studentOfTeacher = userDao.getStudentByClassId(classidsOfteacher,start,pagesize,false);
						for(int k = 0;k < studentOfTeacher.size();k++){//逐个获取用户信息
							Map<String,Object> tempMap = new HashMap<String,Object>();
							//带回教学班信息和学生个人信息
							tempMap.put("teachclass", studentOfTeacher.get(k).getClas());
							tempMap.put("user", studentOfTeacher.get(k).getUser());
							result.add(tempMap);
					}
					
				}
				}
				return result;
}
	
	/**
	 * 根据教师id获取学生信息
	 * 可以根据课程id进行筛选
	 * 带有名字筛选
	 */
	@Override
	public List<Map<String,Object>> getStudentItem(int start, int pagesize, int teacherid,StringBuffer courseidBuff,String userName) {
		//记录教师开设的教学班的id
		StringBuffer classid = new StringBuffer();
	    //根据每个课程id获得开课的班级id
		List<Class_bean> classcourse = classService.getAllteachClassByCourse(courseidBuff, teacherid);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		 //根据每个课程id获得开课的班级id
		//根据每个课程id获得开课的班级id
		 classcourse = classService.getAllteachClassByCourse(courseidBuff, teacherid);
		for(int i = 0;i<classcourse.size();i++){
		if(i!=(classcourse.size()-1)){
				classid.append(classcourse.get(i).getClassId()+",");
		}else{
				classid.append(classcourse.get(i).getClassId());
		}
		}
		List<User_Class> classOfCourse = userDao.getClassByClassIdAndName(classid,userName,start,pagesize,false);
			for(int k = 0;k < classOfCourse.size();k++){//逐个获取用户信息
				Map<String,Object> tempMap = new HashMap<String,Object>();
							//带回教学班信息和学生个人信息
							tempMap.put("teachclass", classOfCourse.get(k).getClas());
							tempMap.put("user", classOfCourse.get(k).getUser());
							result.add(tempMap);
					}
	    return result;
}
	
	//富允
	public boolean hasMatchUser(String accountId, String password) {
        int matchCount = userDao.getMatchCount(accountId, password);
        return matchCount == 1;
    }

	@Override
	public Course getCourseByStudentClass(int studentId) {
		return null;
	}

	/**
	 * 从教师所教对应课程的对应教学班中删除学生信息
	 */
	@Override
	public boolean delUserInClass(String userId, String classId, int teacherId) {
		Boolean result = true;
		/*
		List<Class_Course> classOfCourse = userDao.getClassByCourseId(teacherId,Integer.parseInt(courseId));
		if(classOfCourse!=null && classOfCourse.size()>0){
			for(Class_Course cc:classOfCourse){
				Class_bean cb = cc.getCourseClass();
				List<User_Class> uc = userDao.getUserClassByTwoId(cb.getClassId(),Integer.parseInt(userId));
			    if(uc!=null && uc.size()>0){
			    	for(User_Class uctemp:uc){//这里虽使用循环，但只执行一次
			    	     userDao.delete(uctemp);
			    	}
			    }
			}
		}
		*/
		List<User_Class> uc = userDao.getUserClassByTwoId(Integer.parseInt(classId),Integer.parseInt(userId));
	    if(uc!=null && uc.size()>0)
		 for(User_Class uctemp:uc){//这里虽使用循环，但只执行一次
			 userDao.delete(uctemp);
		}
	return result;
	}

	/**
	 * 重置密码
	 */
	@Override
	public boolean resetPassword(String userId) {
		User user = userDao.getUserById(User.class, Integer.parseInt(userId));
		user.setPassword(MD5.getMD5("123456"));
		userDao.saveOrupdate(user);
		return true;
	}
}
