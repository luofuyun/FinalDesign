package com.zhst.Web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Class_bean;
import com.zhst.Bean.Course;
import com.zhst.Bean.Item;
import com.zhst.Bean.PageBean;
import com.zhst.Bean.User;
import com.zhst.Dao.UserDao;
import com.zhst.Service.ClassService;
import com.zhst.Service.CourseService;
import com.zhst.Service.UserService;
import com.zhst.Util.JSONUtil;
import com.zhst.cons.CommonConstant;

@Controller
@RequestMapping("/classinfo")
public class ClassInfoController extends BaseController{

	@Autowired
	public ClassService classService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;
	/**
	 * 获得所有行政班级
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getAllAdminClass")
	public void getAllAdminClass(HttpServletResponse response) throws IOException{
		List<Class_bean> class_beans = classService.getAllClassByType(1); 
		JSONArray json = JSONArray.fromObject(class_beans,JSONUtil.filterPropertyInJsonData("creator"));
		System.out.println("--" + json.toString());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json.toString());
	}
	
	/**
	 * 根据课程id获取该课程所开设的教学班
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getAllteachClassByCourse")
	public void getAllteachClassByCourse(String courseid,HttpServletRequest request,HttpServletResponse response) throws IOException{
		int teacherid = ((User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT)).getId();
		List<Course> course = null;
		if(courseid ==null || "".equals(courseid)){
			course = courseService.findCoursesByTeacherIdInSemester(teacherid);
			if(course != null && course.size()>0){
				courseid = String.valueOf(course.get(0).getCourseId());
			}else{
				courseid = "1";
			}
		}
		List<Class_bean> class_beans = classService.getAllteachClassByCourse(courseid,teacherid); 
		JSONArray json = JSONArray.fromObject(class_beans,JSONUtil.filterPropertyInJsonData("creator"));
		System.out.println("--" + json.toString());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json.toString());
	}
	/**
	 * 教师-点击课程班级管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/courseClassMan")
	public String couresClassMan(HttpServletRequest request){
		int teacherid = ((User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT)).getId();
		String courseidstr = (String)request.getParameter("courseid");
		int courseid = -1;
		List<Course> courseForTeacher = new ArrayList<Course>();
		if(courseidstr==null){
			courseForTeacher = courseService.findCoursesByTeacherIdInSemester(teacherid);
		    if(courseForTeacher.size()>0)
			courseid = courseForTeacher.get(0).getCourseId();
		    courseidstr = ((Integer)courseid).toString();
		}else{
			courseid = Integer.parseInt(courseidstr);
			courseidstr = ((Integer)courseid).toString();
		}
		
		
		int pagesize = 10;
		PageBean page = new PageBean();
		List<Class_bean> classListForPageRow = classService.getAllteachClassByCourse(courseidstr, teacherid);
		page.setAllRow(classListForPageRow.size());
		page.setPageSize(pagesize);
		page.setTotalPage(page.countTotalPage(pagesize,
				classService.getAllteachClassByCourse(courseidstr, teacherid).size()));
		if (request.getParameter("pageNo") != null && page.getTotalPage()>=(Integer.parseInt(request.getParameter("pageNo")))) {
			page.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
			page.init();
			List<Class_bean> classListForSend = classService.getClassForPage(
					((Integer.parseInt(request.getParameter("pageNo"))) - 1)
					* pagesize, pagesize,teacherid,courseid);
			request.setAttribute("classList", classListForSend);
		} else {
			page.setCurrentPage(1);
			page.init();
			List<Class_bean> classListForSend = classService.getClassForPage(0, pagesize,teacherid,courseid);
			request.setAttribute("classList",
					classListForSend);
		}
		request.setAttribute("pageUrl", "/FinalDesign/classinfo/courseClassMan?courseid="+courseid);
		request.setAttribute("pageAttrKey", page);
		request.setAttribute("courseid", courseid);
		return "jsp/courseClassMan";
	}
	
	@RequestMapping("/addClass")
	public String addClass(String classId,HttpServletRequest request){
		String courseIdStr = request.getParameter("courseId");
		if (!"".equals(classId)) {
			Class_bean cb = classService.getClassByClass(Integer.parseInt(classId));
			request.setAttribute("classForShow", cb);
		}
		request.setAttribute("courseId", courseIdStr);
		return "jsp/editClassInfo";
	}
	@RequestMapping("/modifyOrSaveClassInfo")
	public void modifyOrSaveClassInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//int creatorId = ((User) request.getSession().getAttribute(
				//CommonConstant.USER_CONTEXT)).getId();
		User creator = (User)request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT);
		String classIdStr = request.getParameter("classId");
		String className = request.getParameter("className");
		String courseIdStr = request.getParameter("courseId");
		String classTypeStr = request.getParameter("classType");
		Integer courseId = Integer.parseInt(courseIdStr);
		Integer classType = Integer.parseInt(classTypeStr);
		Course course = courseService.findCourseByCourseId(courseId);
		if("".equals(classIdStr)){
			Class_bean cb = new Class_bean();
			cb.setClassName(className);
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr=format.format(date);
			String yearStr = dateStr.substring(0,4);
			int dateYear = Integer.parseInt(yearStr);
			cb.setClassYear(dateYear);
			cb.setCreateDate(new Date());
			cb.setType(2);
			cb.setValid(1);
			cb.setCreator(creator);
			cb.setType(classType);
			classService.saveOrUpdate(cb);
			
			Class_Course cc = new Class_Course();
			cc.setCourseClass(cb);
			cc.setTeacher(creator);
			cc.setCourse(course);
			cc.setType(1);
			cc.setValid(1);
			cc.setIsclose(1);
			cc.setCreateDate(new Date());
			classService.saveOrUpdate(cc);
		}else{
			Integer classId = Integer.parseInt(classIdStr);
			Class_bean cb = classService.getClassByClass(classId);
			cb.setClassname(className);
			classService.saveOrUpdate(cb);
		}
	}
	@RequestMapping("/deleteClass")
	public void deleteClass(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String classIdStr = request.getParameter("classId");
		Integer classId = Integer.parseInt(classIdStr);
		Class_bean cb = classService.getClassByClass(classId);
		classService.delete(cb);
	}
}
