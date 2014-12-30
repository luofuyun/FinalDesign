package com.zhst.Service;

import java.util.List;
import java.util.Map;

import com.zhst.Bean.Class_bean;
import com.zhst.Bean.Semester_Course;
import com.zhst.Bean.User_Class;

public interface ClassService extends BaseService{

	Class_bean getClassByClass(int classid);

	Class_bean getClassByClassName(String adminclassname);

	List<Class_bean> getAllClassByType(int type);

	List<Class_bean> getAllteachClassByCourse(String courseid, int teacherid);
	
	List<Class_bean> getAllteachClassByCourse(StringBuffer courseidBuff, int teacherid);

	User_Class getUserCourseByTwoId(int classid,int userid);
	
	List<Class_bean> getClassForPage(int start, int pagesize, int teacherid,int courseId);
}
