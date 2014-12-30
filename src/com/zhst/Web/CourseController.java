package com.zhst.Web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhst.Bean.Course;
import com.zhst.Bean.PageBean;
import com.zhst.Bean.Semester;
import com.zhst.Bean.Semester_Course;
import com.zhst.Bean.User;
import com.zhst.Service.CourseService;
import com.zhst.Service.UserService;
import com.zhst.Util.JSONUtil;
import com.zhst.cons.CommonConstant;

@Controller("courseController")
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private UserService userService;

	@RequestMapping("/getAllCourse")
	public ModelAndView getAllCourse(HttpServletRequest request) {

		int pagesize = 5;
		PageBean page = new PageBean();
		page.setAllRow(courseService.getCourseCount());
		System.out
				.println("can we get the count from the all Courses?:the count is:"
						+ courseService.getCourseCount());
		page.setPageSize(pagesize);
		page.setTotalPage(page.countTotalPage(pagesize,
				courseService.getCourseCount()));
		if (request.getParameter("pageNo") != null) {
			page.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
			page.init();
			request.setAttribute(
					"onePageCourse",
					courseService.getOnePageCourse(
							((Integer.parseInt(request.getParameter("pageNo"))) - 1)
									* pagesize, pagesize));
		} else {
			page.setCurrentPage(1);
			page.init();
			request.setAttribute("onePageCourse",
					courseService.getOnePageCourse(0, pagesize));
		}
		request.setAttribute("pageUrl", "/Program/course/getAllCourse");
		request.setAttribute("pageAttrKey", page);

		request.getSession().setAttribute("allCourse",
				courseService.getAllCourse());
		return new ModelAndView("showAllCourse");
	}

	/**
	 * 根据当前登录用户 获得开设的年度课程信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	@RequestMapping("/getSemesterClassByUserId")
	public String getSemesterClassByUserId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int userId = ((User) request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT)).getId();
		int userType = ((User) request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT)).getRole();
		boolean isTeacher = true;
		int pagesize = 5;
		if (userType == 1) {
			isTeacher = false;
		}
		PageBean page = new PageBean();
		page.setAllRow(courseService.getCountSemesterCourseByUser(userId,
				isTeacher));
		page.setPageSize(pagesize);
		page.setTotalPage(page.countTotalPage(pagesize,
				courseService.getCountSemesterCourseByUser(userId, isTeacher)));

		if (request.getParameter("pageNo") != null) {
			page.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
			page.init();
			request.setAttribute(
					"courseList",
					courseService.getSemesterCourseItemByUser(
							((Integer.parseInt(request.getParameter("pageNo"))) - 1)
									* pagesize, pagesize, userId, isTeacher));
		} else {
			page.setCurrentPage(1);
			page.init();
			request.setAttribute("courseList",
					courseService.getSemesterCourseItemByUser(0, pagesize,
							userId, isTeacher));
		}
		request.setAttribute("pageUrl",
				"/FinalDesign/course/getSemesterClassByUserId");
		request.setAttribute("pageAttrKey", page);
		return "jsp/manageSemesterCourse";
	}

	/**
	 * 跳转到年度课程编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEditSemesterCourse")
	public String toEditSemesterCourse(String semesterCourseId,
			HttpServletRequest request) {
		if (!"".equals(semesterCourseId)) {
			Semester_Course ss = courseService.getSemesterCourseById(Integer
					.parseInt(semesterCourseId));
			request.setAttribute("semestercourse", ss);
			request.setAttribute("semesterid", ss.getSemester().getSemesterId());
		}
		return "jsp/editSemesterCourse";
	}

	/**
	 * 修改年度课程
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@RequestMapping("/modifyOrSaveSemesterCourseInfo")
	public void modifyOrSaveSemesterCourseInfo(HttpServletRequest request,
			HttpServletResponse response, Semester_Course semesterCourse)
			throws Exception {
		int creatorId = ((User) request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT)).getId();
		String semestercourseid = (String) request
				.getParameter("semestercourseid");
		String courseNo = (String) request.getParameter("courseNo");
		String courseName = (String) request.getParameter("courseName");
		String semesterid = (String) request.getParameter("semesterid");
		String coursetype = (String) request.getParameter("coursetype");
		String credit = (String) request.getParameter("credit");
		if (!"".equals(semestercourseid)) {// 修改
			semesterCourse = courseService.getSemesterCourseById(Integer
					.parseInt(semestercourseid));
			semesterCourse.getCourse().setCourseNo(courseNo);
			semesterCourse.getCourse().setCourseName(courseName);
			semesterCourse.getCourse().setCourseTye(
					Integer.parseInt(coursetype));
			semesterCourse.getCourse().setCredit(Double.parseDouble(credit));
			semesterCourse.setSemester(courseService.get(Semester.class,
					Integer.parseInt(semesterid)));
		} else {//
			semesterCourse = new Semester_Course();
			Course course = new Course();
			course.setCourseNo(courseNo);
			course.setCourseName(courseName);
			course.setCourseTye(Integer.parseInt(coursetype));
			course.setCredit(Double.parseDouble(credit));
			course.setValid(1);
			course.setCreateDate(new Date());
			course.setCreator(userService.getUserById(creatorId));
			courseService.save(course);
			semesterCourse.setCourse(course);
			semesterCourse.setSemester(courseService.get(Semester.class,
					Integer.parseInt(semesterid)));
			semesterCourse.setIsClose(0);
			semesterCourse.setTeacher(userService.getUserById(creatorId));
			semesterCourse.setValid(1);
			semesterCourse.setCreateDate(new Date());
		}

		courseService.saveOrUpdate(semesterCourse);

		List<Semester_Course> semesterCourses = new ArrayList<Semester_Course>();
		semesterCourses.add(semesterCourse);
		JSONArray json = JSONArray.fromObject(semesterCourses,
				JSONUtil.filterPropertyInJsonData("creator"));
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json.toString());
	}

	/**
	 * 删除年度课程
	 * 
	 * @param semesterCourseId
	 * @param request
	 * @param response
	 */
	@RequestMapping("/deleteSemesterCourse")
	public void deleteSemesterCourse(String semesterCourseId,
			HttpServletRequest request, HttpServletResponse response) {
		int teacherId = ((User) request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT)).getId();
		String result = "{\"result\":\"error\"}";

		if (courseService.delSemesterCourse(Integer.parseInt(semesterCourseId))) {
			result = "{\"result\":\"success\"}";
		}
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/addCourse")
	public ModelAndView addCourse(HttpServletRequest request) {

		return new ModelAndView("addCourse");
	}

	@RequestMapping(value = "/saveAddCourse")
	public ModelAndView saveAddCourse(HttpServletRequest request, Course course) {

		User user = (User) request.getSession().getAttribute("user");
		courseService.saveCourse(course, user);
		request.getSession().setAttribute("allCourse",
				courseService.getAllCourse());
		return new ModelAndView("showAllCourse", "success", "保存成功");
	}

	@RequestMapping(value = "/changeCourse")
	public ModelAndView changeCourse(HttpServletRequest request) {

		int courseId = Integer.parseInt(request.getParameter("courseId"));
		// System.out.println("Have I got the courseId??the courseId is "+courseId);
		// System.out.println("now we are changing the course!");
		Course course = courseService.findCourseByCourseId(courseId);
		request.getSession().setAttribute("course", course);
		return new ModelAndView("changeCourse");
	}

	@RequestMapping(value = "/saveChangeCourse")
	public ModelAndView saveChangeCourse(HttpServletRequest request,
			Course course) {

		System.out.println("Have I get the changing coures??courseName:"
				+ course.getCourseName());
		System.out.println("Have I get the changing coures??courseId:"
				+ course.getCourseId());

		courseService.saveCourse(course);
		request.getSession().setAttribute("allCourse",
				courseService.getAllCourse());
		return new ModelAndView("showAllCourse", "success", "����ɹ�");
	}

	@RequestMapping(value = "/deleteCourse")
	public ModelAndView deleteCourse(HttpServletRequest request)
			throws Exception {

		int courseId = Integer.parseInt(request.getParameter("courseId"));
		System.out.println("Have I got the deleted courseId??the courseId is "
				+ courseId);
		// System.out.println("now we are changing the course!");
		Course course = courseService.findCourseByCourseId(courseId);
		courseService.newDeleteCourse(course);
		request.getSession().setAttribute("allCourse",
				courseService.getAllCourse());
		return new ModelAndView("showAllCourse", "success", "ɾ��ɹ�");
	}

	@RequestMapping(value = "/searchCourse")
	public ModelAndView searchCourse(HttpServletRequest request,
			String searchString) {

		System.out.println("Have I got into a serach case??");
		System.out.println("Let me look at the searchString:" + searchString);
		String newSearchString = "'%%" + searchString + "%%'";
		List<Course> allCourse = courseService
				.findCoursesBySearchString(newSearchString);
		request.getSession().setAttribute("allCourse", allCourse);
		return new ModelAndView("showAllCourse", "success", "��ѯ���");
	}

	@RequestMapping(value = "/showCourseDetail")
	public ModelAndView showCourseDetail(HttpServletRequest request,
			Course course) {

		request.setAttribute("courseSemester",
				courseService.courseInSemester(course));
		request.setAttribute("courseClass",
				courseService.courseHasClass(course));
		return new ModelAndView("courseDetail", "success", "��ȡ�ɹ�");
	}

	@RequestMapping(value = "/addStudentInCourse")
	public ModelAndView addStudentInCourse(HttpServletRequest request,
			Course course, User user) {

		courseService.addStudentInCourse(course, user);
		return new ModelAndView("courseDetail", "success", "添加成功");
	}

	@RequestMapping(value = "/deleteStudentInCourse")
	public ModelAndView deleteStudentInCourse(HttpServletRequest request,
			Course course, User user) {

		courseService.deleteStudentInCourse(course, user);
		return new ModelAndView("courseDetail", "success", "删除成功");
	}

	/**
	 * 根据教师id获得所有教师添加的年度课程
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCoursesByTeacherIdInSemester")
	public void getCoursesByTeacherIdInSemester(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int teacherid = ((User) request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT)).getId();
		List<Course> course = courseService
				.findCoursesByTeacherIdInSemester(teacherid);
		JSONArray json = JSONArray.fromObject(course,
				JSONUtil.filterPropertyInJsonData("creator"));
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json.toString());
	}

	/**
	 * 获取管理员添加的学年学期信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getAllSemester")
	public void getAllSemester(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Semester> semester = courseService.findAllSemester();
		JSONArray json = JSONArray.fromObject(semester);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json.toString());
	}

	@RequestMapping("/updateSemesterCourseStatus")
	public void updateSemesterCourseStatus(String semesterCourseId,
			String newstatus, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";

		if (courseService
				.updateSemesterCourseStatus(Integer.parseInt(semesterCourseId),
						Integer.parseInt(newstatus))) {
			result = "{\"result\":\"success\"}";
		}
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
