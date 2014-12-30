package com.zhst.Service;

import java.util.List;

import com.zhst.Bean.Course;
import com.zhst.Bean.Semester;
import com.zhst.Bean.Semester_Course;
import com.zhst.Bean.User;

public interface CourseService extends BaseService{ 
	
	public List<Course> getAllCourse();
	public void saveCourse(Course course,User user);
	public void saveCourse(Course course);
	public void deleteCourse(Course course);
	public Class courseHasClass(Course course);
	public Semester courseInSemester(Course course);
	public void addStudentInCourse(Course course, User user);
	public void deleteStudentInCourse(Course course,User user);
	public Course findCourseByCourseId(int CourseId);
	public List<Course> findCoursesBySearchString(String serachString);
	public List<Course> findCoursesByTeacherIdInSemester(int teacherid);
	public void newDeleteCourse(Course course);
	public int getCourseCount();
	public List<Course> getOnePageCourse(int first, int pageSize);
	
	List<Semester_Course> getSemesterCourseItemByUser(int start, int pagesize,int userId,boolean isTeacher);
	
	public int getCountSemesterCourseByUser(int userId,boolean isTeacher);
	
	public boolean delSemesterCourse(int semesterCourseId);
	
	public Semester_Course getSemesterCourseById(int semesterCourseId);
	
	public List<Semester> findAllSemester();

	public boolean updateSemesterCourseStatus(int parseInt, int parseInt2);

}
