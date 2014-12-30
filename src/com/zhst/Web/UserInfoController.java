package com.zhst.Web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zhst.Bean.Class_bean;
import com.zhst.Bean.Course;
import com.zhst.Bean.PageBean;
import com.zhst.Bean.User;
import com.zhst.Bean.User_Class;
import com.zhst.Service.ClassService;
import com.zhst.Service.CourseService;
import com.zhst.Service.UserService;
import com.zhst.Util.JSONUtil;
import com.zhst.Util.JxlExcelUtil;
import com.zhst.cons.CommonConstant;

@Controller
@RequestMapping("/userinfo")
public class UserInfoController extends BaseController {

	@Autowired
	public UserService userService;

	@Autowired
	public ClassService classService;
	
	@Autowired
	public CourseService courseService;

	List<User> newUserList = new ArrayList<User>();
	
	/**
	 * 用户跳转到首页，要加工成带数据跳到首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/toFirst")
	public String toFirst(HttpServletRequest request){		
		int type = ((User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT)).getRole();
		if(type != 0){
		if(1 == type ){//管理员跳转到首页
			return "jsp/admin";
		}else if(2 == type ){//教师跳转到首页
			return "jsp/teacher";
		}else{//学生跳转到首页
			return "jsp/student";
		} 
	}else{
		return "/Login.jsp";
	}
	}
	
	/**
	 * 跳转到【编辑个人信息】
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEditPersonalInfo")
	public String toEditPersonalInfo(HttpServletRequest request){		
		return "jsp/editPersonalInfo";
	}
	
	/**
	 * 管理员获取所有用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllUser")
	public String getAllUser(HttpServletRequest request) {
		int pagesize = 10;
		PageBean page = new PageBean();
		page.setAllRow(userService.getUserCount());
		page.setPageSize(pagesize);
		page.setTotalPage(page.countTotalPage(pagesize,
				userService.getUserCount()));
		String type = request.getParameter("type");
		String username = request.getParameter("username");
		if(username != null){
		try {
			username = URLDecoder.decode(username,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		}
		if (request.getParameter("pageNo") != null && page.getTotalPage()>=(Integer.parseInt(request.getParameter("pageNo")))) {
			page.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
			page.init();
			request.setAttribute("userList", userService.getUserItem(
					((Integer.parseInt(request.getParameter("pageNo"))) - 1)
							* pagesize, pagesize,type,username));
		} else {
			page.setCurrentPage(1);
			page.init();
			request.setAttribute("userList",
					userService.getUserItem(0, pagesize,type,username));
		}
		if(type != null){
			request.setAttribute("usertype",type);				
		}else{
			request.setAttribute("usertype","0");
		}
		request.setAttribute("pageUrl", "/FinalDesign/userinfo/getAllUser");
		request.setAttribute("pageAttrKey", page);
		return "jsp/teachManageStudent";
	}

	/**
	 * 教师获取学生信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getStudentInfo")
	public String getStudenrInfo(HttpServletRequest request){
		int teacherid = ((User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT)).getId();
		String courseidstr = (String)request.getParameter("courseid");
		int courseid = -1;
		List<Course> courseForTeacher = new ArrayList<Course>();
		if(courseidstr==null){
			courseForTeacher = courseService.findCoursesByTeacherIdInSemester(teacherid);
		    if(courseForTeacher.size()>0)
			courseid = courseForTeacher.get(0).getCourseId();
		}else{
			courseid = Integer.parseInt(courseidstr);
		}
		int pagesize = 20;
		PageBean page = new PageBean();
		page.setAllRow(userService.getStudentCountByTeacher(teacherid,courseid));
		page.setPageSize(pagesize);
		page.setTotalPage(page.countTotalPage(pagesize,
				userService.getStudentCountByTeacher(teacherid,courseid)));
		if (request.getParameter("pageNo") != null && page.getTotalPage()>=(Integer.parseInt(request.getParameter("pageNo")))) {
			page.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
			page.init();
			request.setAttribute("userList", userService.getStudentItem(
					((Integer.parseInt(request.getParameter("pageNo"))) - 1)
							* pagesize, pagesize,teacherid,courseid));
		} else {
			page.setCurrentPage(1);
			page.init();
			request.setAttribute("userList",
					userService.getStudentItem(0, pagesize,teacherid,courseid));
		}
		request.setAttribute("pageUrl", "/FinalDesign/userinfo/getStudentInfo?courseid="+courseid);
		request.setAttribute("pageAttrKey", page);
		request.setAttribute("courseid", courseid);
		return "jsp/teachManageStudent";
	}
	
	/**
	 * 教师获取学生信息
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/searchStudentByName")
	public String searchStudentByName(String userName,HttpServletRequest request) throws UnsupportedEncodingException{
		int teacherid = ((User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT)).getId();
		String courseidstr = (String)request.getParameter("courseid");
		userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
		int courseid = -1;
		List<Course> courseForTeacher = courseService.findCoursesByTeacherIdInSemester(teacherid);;
		//记录教师所教课程开始班级对应的课程id，用作在表t_class_course中查询
		StringBuffer courseidBuff = new StringBuffer();
		if(courseForTeacher!=null && courseForTeacher.size()>0){
			for(int i = 0;i<courseForTeacher.size();i++){
			 if(i!=(courseForTeacher.size()-1)){
				 courseidBuff.append(courseForTeacher.get(i).getCourseId()+",");
			 }else{
				 courseidBuff.append(courseForTeacher.get(i).getCourseId()); 
			 }
		}	
		}
		int pagesize = 2;
		PageBean page = new PageBean();
		page.setAllRow(userService.getStudentCountByTeacherAndName(teacherid,courseidBuff,userName));
		page.setPageSize(pagesize);
		page.setTotalPage(page.countTotalPage(pagesize,
				userService.getStudentCountByTeacherAndName(teacherid,courseidBuff,userName)));
		if (request.getParameter("pageNo") != null) {
			page.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
			page.init();
			request.setAttribute("userList", userService.getStudentItem(
					((Integer.parseInt(request.getParameter("pageNo"))) - 1)
							* pagesize, pagesize,teacherid,courseidBuff,userName));
		} else {
			page.setCurrentPage(1);
			page.init();
			request.setAttribute("userList",
					userService.getStudentItem(0, pagesize,teacherid,courseidBuff,userName));
		}
		request.setAttribute("pageUrl", "/FinalDesign/userinfo/searchStudentByName?userName="+userName);
		request.setAttribute("pageAttrKey", page);
		request.setAttribute("courseid", courseid);
		return "jsp/teachManageStudent";
	}
	
	/**
	 * 修改个人信息
	 * @param request
	 * @param response
	 * @param user
	 * @throws IOException
	 */
	@RequestMapping("/modifyPersonalInfo")
	public void modifyPersonalInfo(HttpServletRequest request,
			HttpServletResponse response, User user) throws IOException {
		String id = (String) request.getParameter("userId");
		String accountid = (String) request.getParameter("accountId");
		String username = (String) request.getParameter("userName");
		String gender = (String) request.getParameter("gender");
		String checkModifypsd = (String) request.getParameter("checkModifypsd");
		// String gender = (String) request.getAttribute("gender");
		System.out.println(checkModifypsd + "----" + id + "----username-"
				+ username + "----" + accountid + "----" + gender);

		user = userService.getUserById(Integer.parseInt(request
				.getParameter("userId")));
		user.setUserId(Integer.parseInt(request.getParameter("userId")));
		user.setAccountId((String) request.getParameter("accountId"));
		user.setUserName((String) request.getParameter("userName"));
		user.setGender(Integer.parseInt(request.getParameter("gender")));
		user.setContact(request.getParameter("contact"));
		if ("on".equals((String) request.getParameter("checkModifypsd"))) {
			user.setPassword(request.getParameter("password"));
		}
		userService.updateUser(user);
		//这里需要更新session中的User对象
		setSessionUser(request,user);
		
		List<User> users = new ArrayList<User>();
		users.add(user);
		JSONArray json = JSONArray.fromObject(users,JSONUtil.filterPropertyInJsonData("creator"));
		System.out.println("--" + json.toString());
		// response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json.toString());
	}

	/**
	 * 教师修改学生信息
	 * @param request
	 * @param response
	 * @param user
	 * @throws Exception 
	 */
	@RequestMapping("/modifyStudentInfo")
	public void modifyStudentInfo(HttpServletRequest request,
			HttpServletResponse response, User user) throws Exception {
		String id = (String) request.getParameter("userId");
		String accountid = (String) request.getParameter("accountId");
		String username = (String) request.getParameter("userName");
		String classSelect = (String) request.getParameter("classSelect");
		String courseSelect = (String) request.getParameter("courseSelect");
		String teachClassId = (String) request.getParameter("teachClassId");
		 String teachclassSelect = (String) request.getParameter("teachclassSelect");
		user = userService.getUserById(Integer.parseInt(request
				.getParameter("userId")));
		user.setUserId(Integer.parseInt(request.getParameter("userId")));
		user.setAccountId((String) request.getParameter("accountId"));
		user.setUserName((String) request.getParameter("userName"));
		user.setAdminclass(classService.getClassByClass(Integer.parseInt(classSelect)));
		userService.updateUser(user);	
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("student",user);
		User_Class uc = classService.getUserCourseByTwoId(Integer.parseInt(teachClassId),Integer.parseInt(id));
		if(uc!=null){
			//判断修改后的学生是否已经存在于选择的教学班中，如果有，则不更新，并删除原来的user_class对象
			User_Class ucorg = classService.getUserCourseByTwoId(Integer.parseInt(teachclassSelect), Integer.parseInt(id));
			if(ucorg!=null){
				classService.delete(uc);
			}else{
			uc.setClas(classService.getClassByClass(Integer.parseInt(teachclassSelect)));
			classService.saveOrUpdate(uc);
			}
		}
		
		JSONArray json = JSONArray.fromObject(map,JSONUtil.filterPropertyInJsonData("creator"));
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json.toString());
	}
	
	@RequestMapping("/getUser")
	public String getUser(HttpServletRequest request, String accountId) {
		System.out.println("----getUser---");
		if (accountId != "") {
			User user = userService.getUserByAccountId(accountId);
			request.setAttribute("user", user);
		}
		return "WEB-INF/jsp/editUser";
	}

	/**
	 * 学生信息管理页面跳转到学生信息编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEditStudentInfo")
	public String toEditStudentInfo(HttpServletRequest request){
		request.setAttribute("course",courseService.findCourseByCourseId(Integer.parseInt(request.getParameter("courseid"))));
		request.setAttribute("student",userService.getUserById(Integer.parseInt(request.getParameter("studentid"))));
		request.setAttribute("courseid",courseService.findCourseByCourseId(Integer.parseInt(request.getParameter("courseid"))).getCourseId());
		request.setAttribute("classid",userService.getUserById(Integer.parseInt(request.getParameter("studentid"))).getAdminclass().getClassId());
		request.setAttribute("teachclass",classService.getClassByClass(Integer.parseInt(request.getParameter("teachclassid"))));
		request.setAttribute("teachclassid",classService.getClassByClass(Integer.parseInt(request.getParameter("teachclassid"))).getClassId());
		return "jsp/editStudentInfo";
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(User user, HttpServletRequest request) {
		user.setAdminclass(classService.getClassByClass(user.getAdminclass()
				.getClassId()));
		userService.updateUser(user);
		user = userService.getUserByAccountId(user.getAccountId());
		request.setAttribute("user", user);
		return "redirect:/userinfo/getAllUser";
	}

	/**
	 * 刪除单个学生
	 * @param accountId
	 * @param response
	 */
	@RequestMapping("/delUser")
	public void delUser(String accountId, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		if (userService.delUser(accountId)) {
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

	/**
	 * 从教师对应课程删除对应学生信息
	 * @param userId 学生id
	 * @param courseId 课程id
	 * @param request 获得教师id
	 * @param response
	 */
	@RequestMapping("/delUserInClass")
	public void delUserInClass(String userId,String classId,HttpServletRequest request,HttpServletResponse response) {
		int teacherId = ((User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT)).getId();
		String result = "{\"result\":\"error\"}";

		if (userService.delUserInClass(userId,classId,teacherId)) {
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

	
	/**
	 * 批量删除用户信息
	 * @param accountId
	 * @param response
	 */
	@RequestMapping("/bacthDelUser")
	public void bacthDelUser(HttpServletRequest request, HttpServletResponse response) {

		String result = "{\"result\":\"error\"}";
		boolean isSuccess = false;
		String ids = request.getParameter("userIds");
		String[] arrIds = ids.split(",");
		for (int i = 0; i < arrIds.length; i++) {
			isSuccess = userService.delUser(arrIds[i]);
		}
		if(isSuccess){
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
	
	@RequestMapping("/resetPassword")
	public void resetPassword(String userId, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		if (userService.resetPassword(userId)) {
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
	
	@RequestMapping("/toAddUser")
	public String toAddUser() {
		System.out.println("----toAddUser");
		return "WEB-INF/jsp/addUser";
	}

	@RequestMapping("/addUser")
	public void addUser(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		User creator = (User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
		user.setAccountId(request.getParameter("accountId"));
		user.setUserName(request.getParameter("userName"));
		user.setGender(Integer.parseInt(request.getParameter("gender")));
		user.setRole(Integer.parseInt(request.getParameter("role")));
		String college = (String) request.getParameter("college");
		if (college != null && (!("".equals(college)))) {
			user.setCollege(college);
		}
		String grade = (String) request.getParameter("grade");
		if (grade != null && (!("".equals(grade)))) {
			user.setGrade(Integer.parseInt(grade));
		}
		String department = (String) request.getParameter("department");
		if (department != null && (!("".equals(department)))) {
			user.setDepartment(department);
		}
		String contact = (String) request.getParameter("contact");
		if (contact != null && (!("".equals(contact)))) {
			user.setContact(contact);
		}
		String adminclassid = (String) request.getParameter("adminclass");
		user.setAdminclass(classService.getClassByClass(Integer
				.parseInt(adminclassid)));
		user.setCreator(creator);
		user.setCreateDate(new Date());
		userService.updateUser(user);
		String result = "{\"result\":\"success\"}";
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/toBatchAddUser")
	public String toBatchAddUser() {
		System.out.println("----toBatchAddUser");
		return "WEB-INF/jsp/batchAddUser";
	}

	@RequestMapping("/BacthAddUser")
	public void BatchAddUser(@RequestParam MultipartFile[] myfiles,HttpServletRequest request, HttpServletResponse response) throws Exception{
		// String filepath = request.getParameter("filepath");
		// System.out.println("----BatchAddUser--"+filepath);
		for (MultipartFile myfile : myfiles) {	
		String strPath = myfile.getOriginalFilename();
		int rowNum = 0;
		String cellValue = "";
		File file = new File("D:\\"+strPath);
		myfile.transferTo(file);
		Workbook wb = null;
		 Map<String,Object> validresult = new HashMap<String,Object>();
		// 构造Workbook（工作薄）对象
		wb = Workbook.getWorkbook(file);
		if (wb != null) {
			Sheet[] sheet = wb.getSheets();

			if (sheet != null) {
				// 对每个工作表进行循环
				for (int i = 0; i < sheet.length; i++) {

					// 得到当前工作表的行数
//					rowNum = .getRows();
					int notNullRow = JxlExcelUtil.getRightRows(sheet[i]);
					System.out.println("-----notnullRow-"+notNullRow);
						for (int j = 1; j < notNullRow; j++) {
						Cell[] cells = sheet[i].getRow(j);
						if (cells != null && cells.length > 0) {
							// 获取单元格
							User newuser = new User();
							  newuser.setAccountId(cells[0].getContents());
							  newuser.setUserName(cells[1].getContents());
							  String gender = cells[2].getContents();
							  if(gender != ""){
								  if("男".equals(gender)){
									  newuser.setGender(1);
								  }else if("女".equals(gender)){
									  newuser.setGender(2);
								  }
								  else{newuser.setGender(3);}
							  }
							  if(cells[3].getContents() != ""){
							  newuser.setGrade(Integer.parseInt(cells[3].getContents()));
							  }
							  newuser.setCollege(cells[4].getContents());
							  newuser.setDepartment(cells[5].getContents());
							  String adminclassname = cells[6].getContents();
							  if(adminclassname != null){
								 Class_bean adminclass = classService.getClassByClassName(adminclassname);
								 if(adminclass != null){
									 newuser.setAdminclass(adminclass);
								 }
							  }
							  newuser.setContact(cells[7].getContents());
							  String isValid = validateData(newuser);
							  newUserList.add(newuser);
							  validresult.put(newuser.getAccountId(), isValid);		  
						}
					}
						validresult.put("USERLIST", newUserList);	
				}
			}
		}	
		JSONArray json = JSONArray.fromObject(validresult);
		System.out.println("--" + json.toString());
		// response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json.toString());
		}
	}
	
	public String validateData(User user){
		String result = "1";
		String accountid = user.getAccountId();
		if(accountid == null){
			result = "0";
		}else{
			if((!accountid.matches("[0-9]{12}")) || (!accountid.matches("[0-9]{8}"))){
				result = "0";
			}
		}
		String username = user.getUserName();
		int grade = user.getGrade();
		String college = user.getCollege();
		String department = user.getDepartment();
		if(username == null || grade == 0 || college == null || department == null){
			result = "0";
		}
		int gender = user.getGender();
		if(gender != 1 || gender != 2){
			result = "0";
		}
		System.out.println(accountid+"-"+username+"-"+gender+"-"+grade+"-"+college+"-"+department);					
	
		return result;
	}

	@RequestMapping("/BacthAddUserInfo")
	public void BatchAddUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("----data---"+newUserList.size());
	   for(int i = 0;i<newUserList.size();i++){
		   userService.updateUser(newUserList.get(i));
	    }
		String result = "{\"result\":\"success\"}";
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	
}
