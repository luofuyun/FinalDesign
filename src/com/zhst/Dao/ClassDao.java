package com.zhst.Dao;

import java.util.List;

import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Class_bean;
import com.zhst.Bean.Semester_Course;
import com.zhst.Bean.User_Class;

public interface ClassDao extends BaseDao{

	Class_bean getClassByClassName(int id);

	Class_bean getClassByClassName(String adminclassname);

	List<Class_bean> getAllClassByType(int type);

	List<Class_Course> getTeachClassByTwoID(int courseid, int teacherid);

	List<User_Class> getUserCourseByTwoId(int classid, int userid);
	
	List<Class_Course> getTeachClassByCourseidBuffer(StringBuffer courseidBuff, int userid);

}
