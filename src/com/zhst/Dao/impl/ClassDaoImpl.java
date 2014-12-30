package com.zhst.Dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Class_bean;
import com.zhst.Bean.User_Class;
import com.zhst.Dao.ClassDao;
import com.zhst.Dao.UserDao;

@Repository("classDao")
public class ClassDaoImpl  extends BaseDaoImpl implements ClassDao{

	@Autowired
	private UserDao userDao;
	
	@Override
	public Class_bean getClassByClassName(int classid) {
		List<Class_bean> class_beans = (List<Class_bean>) this.findByProperty(Class_bean.class, "id",
				classid);
		if (class_beans.size() == 0) {
			return null;
		} else {
			return class_beans.get(0);
		}
	}

	

	@Override
	public Class_bean getClassByClassName(String adminclassname) {
		List<Class_bean> class_beans = (List<Class_bean>) this.findByProperty(Class_bean.class, "className",
				adminclassname);
		if (class_beans.size() == 0) {
			return null;
		} else {
			return class_beans.get(0);
		}
	}

	@Override
	public List<Class_bean> getAllClassByType(int type) {
		List<Class_bean> class_beans = (List<Class_bean>) this.findByProperty(Class_bean.class, "type",
				type);
		return class_beans;
	}

	@Override
	public List<Class_Course> getTeachClassByTwoID(int courseid, int teacherid) {
		return (this.find("from Class_Course cc where cc.course.courseId ='"+courseid+"' and cc.teacher.userId ='"+teacherid+"' order by cc.course.courseName"));	
	}

	@Override
	public List<User_Class> getUserCourseByTwoId(int classid, int userid) {
		return (this.find("from User_Class cc where cc.clas.classId ='"+classid+"' and cc.user.userId ='"+userid+"'"));
	}

	@Override
	public List<Class_Course> getTeachClassByCourseidBuffer(
			StringBuffer courseidBuff, int userid) {
	return (this.find("from Class_Course cc where cc.course.courseId in ("+courseidBuff.toString()+") and cc.teacher.userId="+userid+" order by cc.courseClass.className"));		
    }

}
