package com.zhst.Service.Impl;

import com.zhst.Dao.CourseDao;
import com.zhst.Service.CourseService;
import com.zhst.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zhst.Bean.Arrange;
import com.zhst.Bean.Class_bean;
import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Course;
import com.zhst.Bean.Item;
import com.zhst.Bean.Semester;
import com.zhst.Bean.Semester_Course;
import com.zhst.Bean.User;
import com.zhst.Bean.User_Class;

@Service("courseService")
public class CourseServiceImpl extends BaseServiceImpl implements CourseService{
	
	@Autowired
	private CourseDao courseDao;
	
	@Override
	public List<Course> getAllCourse() {
		return courseDao.getAllCourse();
	}

	/**
	 * 根据用户是教师还是管理员，获得对应的年度课程信息
	 */
	@Override
	public List<Semester_Course> getSemesterCourseItemByUser(int start, int pagesize,int userId,boolean isTeacher) {
		return courseDao.getSemesterCourseItemByUser(start,pagesize,userId,isTeacher);
	}
	
	/**
	 * 根据用户是教师还是管理员，获得对应的年度课程记录数目
	 */
	@Override
	public int getCountSemesterCourseByUser(int userId,boolean isTeacher) {
		return courseDao.getCountSemesterCourseByUser(userId,isTeacher);
	}
	
	@Override
	public void saveCourse(Course course,User user) {
		courseDao.saveCourse(course,user);		
	}
	
	@Override
	public void saveCourse(Course course){
		courseDao.saveCourse(course);
	}

	@Override
	public void deleteCourse(Course course) {
		courseDao.deleteCourse(course);
	}

	@Override
	public Class courseHasClass(Course course) {
		return courseDao.courseHasClass(course);
	}

	@Override
	public Semester courseInSemester(Course course) {
		return courseDao.courseInSemester(course);
	}

	@Override
	public void addStudentInCourse(Course course, User user) {
		courseDao.addStudentInCourse(course, user);
	}

	@Override
	public void deleteStudentInCourse(Course course, User user) {
		// TODO Auto-generated method stub
		courseDao.deleteStudentInCourse(course, user);	
	}

	@Override
	public Course findCourseByCourseId(int courseId) {
		return courseDao.findCourseByCourseId(courseId);
	}

	@Override
	public List<Course> findCoursesBySearchString(String searchString) {
		
		return courseDao.findCoursesBySerachString(searchString);
	}

	@Override
	public void newDeleteCourse(Course course) {
		// TODO Auto-generated method stub
		courseDao.newDeleteCourse(course);
	}

	@Override
	public int getCourseCount() {
		return courseDao.getTotalCount(" from Course as t ");
	}

	@Override
	public List<Course> getOnePageCourse(int first, int pageSize) {
		
		
		List<Course> allCourse = courseDao.getAllCourse();
		if(first+pageSize<=allCourse.size())
			return allCourse.subList(first, first+pageSize);
		else
			return allCourse.subList(first, allCourse.size());
	}

	@Override
	public List<Course> findCoursesByTeacherIdInSemester(int teacherid) {
		List<Semester_Course> sc = courseDao.getSemesterCourseByTeacherId(teacherid);
		List<Course> course = new ArrayList<Course>();
		if(sc!=null && sc.size()>0){
			for(Semester_Course s_c:sc){
				if(s_c.getIsClose()==0)
				course.add(s_c.getCourse());
			}
		}
		return course;
	}

	/**
	 * 删除年度课程信息
	 */
	@Override
	public boolean delSemesterCourse(int semesterCourseId) {
		courseDao.delete(this.getSemesterCourseById(semesterCourseId));
		return true;
	}

	/**
	 *根据id获得年度课程
	 */
	@Override
	public Semester_Course getSemesterCourseById(int semesterCourseId) {
		List<Semester_Course> list= courseDao.findByProperty(Semester_Course.class,"semesterCourseId",semesterCourseId);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Semester> findAllSemester() {
		return courseDao.getAllSemester();
	}

	@Override
	public boolean updateSemesterCourseStatus(int semesterCouserId, int newStatus) {
	try {
	  Semester_Course ss= this.get(Semester_Course.class,semesterCouserId);
	  ss.setIsClose(newStatus);
	  this.saveOrUpdate(ss);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return true;
	}

	
}
