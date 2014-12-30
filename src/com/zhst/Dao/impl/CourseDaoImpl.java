package com.zhst.Dao.impl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.zhst.Bean.Class_bean;
import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Class_bean;
import com.zhst.Bean.Course;
import com.zhst.Bean.Semester;
import com.zhst.Bean.Semester_Course;
import com.zhst.Bean.User;
import com.zhst.Dao.CourseDao;

@Repository("courseDao")
public class CourseDaoImpl extends BaseDaoImpl implements CourseDao{

	@Override
	public List<Course> getAllCourse() {
		String queryString = "from Course";
		List<Object> params=new ArrayList<Object>();
		return this.findWithSelect(queryString, params);
	}

	/**
	 * 根据用户id获得对应年度课程记录（管理员获得所有记录）
	 */
	@Override
	public List<Semester_Course> getSemesterCourseItemByUser(int start, int pagesize,int userId,boolean isTeacher) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer queryString = new StringBuffer();
		queryString.append("from Semester_Course");
		if(isTeacher){
		queryString.append(" where teacher.userId=?");
		params.add(userId);
		}
		queryString.append(" order by semester.term,semester.semesterTime");
		return this.find(queryString.toString(),start,pagesize,params);
	}
	
	/**
	 * 根据用户id获得对应年度课程记录数（管理员获得所有记录）
	 */
	@Override
	public int getCountSemesterCourseByUser(int userId, boolean isTeacher) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer queryString = new StringBuffer();
		queryString.append("from Semester_Course t");
		if(isTeacher){
		queryString.append(" where t.teacher.userId=?");
		params.add(userId);
		}
		queryString.append(" order by t.semester.term,semester.semesterTime");
		return this.getTotalCount(queryString.toString(),params);
	}
	
	@Override
	public void deleteCourse(Course course){
		this.delete(course);
	}
	
	//������ӵ�
	@Override
	public void saveCourse(Course course,User user){
		
		course.setValid(1);
//		course.setCreator(user.getRole());
		this.save(course);
	}
	
	//�����޸ı����
	@Override
	public void saveCourse(Course course){
		
		course.setValid(1);
		this.update(course);
	}
/*
	@Override
	public Class courseHasClass(Course course) {
		//String sql = "select * from t_class as c inner join t_class_course as cc on c.course_id=cc.course_id";
		List<Class_Course> tempClassCourse = this.findByProperty(Class_Course.class, "course_id",course.getId());
		Class_Course newOneClassCourse = tempClassCourse.get(0);
		List<Class> tempClass = this.findByProperty(Class.class, "id", 
				newOneClassCourse.getClass());
		return tempClass.get(0);
	}
	
	@Override
	public Semester courseInSemester(Course course) {
		List<Semester_Course> tempSemesterCourse = this.findByProperty(Semester_Course.class, "course_id",course.getId());
		Semester_Course newOneSemesterCourse = tempSemesterCourse.get(0);
		List<Semester> tempSemester = this.findByProperty(Semester.class, "id", 
				newOneSemesterCourse.getClass());
		return tempSemester.get(0);
	}

	@Override
	public List<Course> getTeacherCourse(User user) {
		String queryString = "from Course where creator=\"1\"";
		List<Object> params=new ArrayList<Object>();
		return this.findWithSelect(queryString, params);
	}

	@Override
	public void addStudentInCourse(Course course,User user) {
		
		Class newClass = this.courseHasClass(course);
		newClass.getUsers().add(user);
		this.update(newClass);
	}

	@Override
	public void deleteStudentInCourse(Course course, User user) {
		// TODO Auto-generated method stub
		Class newClass = this.courseHasClass(course);
		newClass.getUsers().remove(user);
		this.update(newClass);
	}
*/
	@Override
	public Course findCourseByCourseId(int courseId) {
		List<Course> courses = this.findByProperty(Course.class, 
				"courseId", courseId);
		Course course = courses.get(0);
		return course;
	}

	@Override
	public List<Course> findCoursesBySerachString(String searchString) {
		String queryString = "from Course where courseName like "+searchString;
		List<Object> searchCourses = new ArrayList<Object>();
		return this.findWithSelect(queryString, searchCourses);
	}

	@Override
	public void newDeleteCourse(Course course) {
		// TODO Auto-generated method stub
		course.setValid(0);
		this.update(course);
	}

	@Override
	public Class courseHasClass(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Semester courseInSemester(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Semester_Course> getSemesterCourseByTeacherId(int teacherid) {
		List<Semester_Course> semester_courses = this.findByProperty(Semester_Course.class, 
				"teacher", teacherid);
		return semester_courses;
	}

	@Override
	public void addStudentInCourse(Course course, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteStudentInCourse(Course course, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Semester> getAllSemester() {
			String queryString = "from Semester t where t.close=0";
			List<Object> params=new ArrayList<Object>();
			return this.findWithSelect(queryString, params);
	}


}
