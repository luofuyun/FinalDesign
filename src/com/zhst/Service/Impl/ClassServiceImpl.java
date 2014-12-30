package com.zhst.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Class_bean;
import com.zhst.Bean.Semester_Course;
import com.zhst.Bean.User_Class;
import com.zhst.Dao.ClassDao;
import com.zhst.Dao.UserDao;
import com.zhst.Service.ClassService;

@SuppressWarnings("serial")
@Service("classService")
public class ClassServiceImpl extends BaseServiceImpl implements ClassService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public ClassDao classDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Class_bean getClassByClass(int classid) {
		return classDao.getClassByClassName(classid);
	}

	@Override
	public Class_bean getClassByClassName(String adminclassname) {
		return classDao.getClassByClassName(adminclassname);
	}

	/**
	 * 根据班级类型获取所有对应类型班级
	 */
	@Override
	public List<Class_bean> getAllClassByType(int type) {
		return classDao.getAllClassByType(type);
	}

	/**
	 * 根据教师id和课程id获取开设的教学班
	 */
	@Override
	public List<Class_bean> getAllteachClassByCourse(String courseid,
			int teacherid) {
		List<Class_bean> cb = new ArrayList<Class_bean>();
		List<Class_Course> cc = classDao.getTeachClassByTwoID(
				Integer.parseInt(courseid), teacherid);
		if (cc != null && cc.size() > 0) {
			for (Class_Course cctemp : cc) {
				cb.add(cctemp.getCourseClass());
			}
		}
		return cb;
	}

	/**
	 * 根据教师id和课程id Buff获取开设的教学班
	 */
	@Override
	public List<Class_bean> getAllteachClassByCourse(StringBuffer courseidBuff,
			int teacherid) {
		List<Class_bean> cb = new ArrayList<Class_bean>();
		List<Class_Course> cc = classDao.getTeachClassByCourseidBuffer(
				courseidBuff, teacherid);
		if (cc != null && cc.size() > 0) {
			for (Class_Course cctemp : cc) {
				cb.add(cctemp.getCourseClass());
			}
		}
		return cb;
	}

	/**
	 * 根据教学班id和学生id获得对应教学班-学生联系对象
	 */
	@Override
	public User_Class getUserCourseByTwoId(int classid, int userid) {
		List<User_Class> uclist = classDao
				.getUserCourseByTwoId(classid, userid);
		if (uclist != null && uclist.size() > 0) {
			return uclist.get(0);
		}
		return null;
	}

	@Override
	public List<Class_bean> getClassForPage(int start, int pagesize,
			int teacherid, int courseId) {
		String courseIdStr = ((Integer)courseId).toString();
		List<Class_bean> wholeClassList = this.getAllteachClassByCourse(courseIdStr, teacherid);
		List<Class_bean> result = null;
		if(start+pagesize>wholeClassList.size()){
			result = wholeClassList.subList(start, wholeClassList.size());}
			else {result = wholeClassList.subList(start, start+pagesize);}
		return result;
	}
}
