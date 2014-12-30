package com.zhst.Dao;
import java.util.List;

import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Course;
import com.zhst.Bean.Class_bean;
import com.zhst.Bean.Semester;
import com.zhst.Bean.Semester_Course;
import com.zhst.Bean.User;
public interface CourseDao extends BaseDao{
	public List<Course> getAllCourse();
	public void saveCourse(Course course,User user);
	public void saveCourse(Course course);
	public void deleteCourse(Course course);
	public Class courseHasClass(Course course);
	public Semester courseInSemester(Course course);
	public List<Semester_Course> getSemesterCourseByTeacherId(int teacherid);
	public void addStudentInCourse(Course course,User user);
	public void deleteStudentInCourse(Course course,User user);
	public Course findCourseByCourseId(int courseId);
	public List<Course> findCoursesBySerachString(String searchString);
	public void newDeleteCourse(Course course);
	
	List<Semester_Course> getSemesterCourseItemByUser(int start, int pagesize,int userId,boolean isTeacher);

	public int getCountSemesterCourseByUser(int userId, boolean isTeacher);
	public List<Semester> getAllSemester();

}
