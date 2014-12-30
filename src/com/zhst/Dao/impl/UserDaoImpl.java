package com.zhst.Dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Semester_Course;
import com.zhst.Bean.User;
import com.zhst.Bean.User_Class;
import com.zhst.Dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {


	private static final long serialVersionUID = 1L;

	/**
	 * 更新用户信息
	 */
	@Override
	public void updateUser(User user) {
		this.saveOrupdate(user);
	}

	@Override
	public User getUserById(Class<User> class1, int Id) {
		List<User> users = (List<User>) this.findByProperty(User.class, "id",
				Id);
		if (users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public List<User> getAllUser() {
		String queryString = "from User u where u.valid = '1'";
		List<Object> params = new ArrayList<Object>();
		return this.findWithSelect(queryString,params);
	}

	@Override
	public User getUserByAccountId(String accountId) {
		List<User> users = (List<User>) this.findByProperty(User.class, "accountId",
				accountId);
		if (users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}

	/**
	 * 删除用户信息
	 */
	@Override
	public boolean delUser(User user) {
		this.saveOrupdate(user);
		return true;
	}

	/**
	 * 根据教师id获得课程
	 */ 
	@Override
	public List<Semester_Course> getCourseByTeacherId(int teacherid) {
		return this.findByProperty(Semester_Course.class,"teacher", teacherid);
	}

	/**
	 * 根据课程id获得班级
	 */
	@Override
	public List<Class_Course> getClassByCourseId(int teacherid,int courseId) {
		String queryString = "from Class_Course u where u.course.courseId = ? and u.teacher.userId = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(courseId);
		params.add(teacherid);
		return this.findWithSelect(queryString,params);
		}

	/**
	 * 根据班级（教学班）id获取该班学生
	 */
	@Override
	public List<User_Class> getStudentByClassId(int classId,int start,int pageSize,boolean isGetCount) {
		if(isGetCount){
		return (this.find("from User_Class uc where uc.clas.classId = '"+classId+"'"));
		}else{
			List<Object> params = new ArrayList<Object>();
			params.add(classId);
		return (this.find("from User_Class uc where uc.clas.classId = ?", start, pageSize, params));
		}
	}

	/**
	 * 多个班级id获取所有学生id
	 * 带有用户名筛选
	 */
	@Override
	public List<User_Class> getClassByClassIdAndName(StringBuffer classIdBuff,String userName,int start,int pageSize,boolean isGetCount) {
		if(isGetCount){
		return (this.find("from User_Class uc where uc.clas.classId in ("+classIdBuff.toString()+") and uc.user.userName like '%"+userName+"%' order by uc.clas.className"));
		}else{
			List<Object> params = new ArrayList<Object>();
//			params.add(classId);
		return (this.find("from User_Class uc where uc.clas.classId in("+classIdBuff.toString()+") and uc.user.userName like '%"+userName+"%' order by uc.clas.className", start, pageSize, params));
		}
	}
	
	/**
	 * 多个班级id获取所有学生id
	 */
	@Override
	public List<User_Class> getStudentByClassId(StringBuffer classIdBuff,int start,int pageSize,boolean isGetCount) {
		if(isGetCount){
		return (this.find("from User_Class uc where uc.clas.classId in ("+classIdBuff.toString()+") order by uc.clas.classId"));
		}else{
			List<Object> params = new ArrayList<Object>();
//			params.add(classId);
		return (this.find("from User_Class uc where uc.clas.classId in("+classIdBuff.toString()+") order by uc.clas.classId", start, pageSize, params));
		}
	}
	
   //富允
	@Override
	public int getMatchCount(String accountId,String password){
		if(getUserByAccountId(accountId)!=null){
	   	   User user = getUserByAccountId(accountId);
			if(user.getPassword().equals(password))
				return 1;
		}
		return 0;
	}

	/**
	 * 根据班级id和学生id获取班级学生对象
	 */
	@Override
	public List<User_Class> getUserClassByTwoId(int classId, int userId) {
		List<Object> params = new ArrayList<Object>();
		params.add(classId);
		params.add(userId);
	   return (this.find("from User_Class uc where uc.clas.classId =? and uc.user.userId=?",params));
	}
}
