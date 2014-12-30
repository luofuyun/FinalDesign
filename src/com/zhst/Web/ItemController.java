package com.zhst.Web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zhst.Bean.Arrange;
import com.zhst.Bean.Class_Course;
import com.zhst.Bean.Course;
import com.zhst.Bean.Item;
import com.zhst.Bean.PageBean;
import com.zhst.Bean.Semester_Course;
import com.zhst.Bean.Submit;
import com.zhst.Bean.User;
import com.zhst.Bean.User_Class;
import com.zhst.Service.ArrangeService;
import com.zhst.Service.CourseService;
import com.zhst.Service.ItemService;
import com.zhst.Service.SubmitService;
import com.zhst.Service.UserService;
import com.zhst.Util.JSONUtil;
import com.zhst.cons.CommonConstant;


/*          itemController
 * 			学生主要使用的有showallfile
 * 			和uploadfile,getfile
 */
@Controller("itemController")  
@RequestMapping("/item")
public class ItemController extends BaseController{
	
	@Autowired
	private ItemService itemService;	
	
	@Autowired
	private ArrangeService arrangeService;
	
	@Autowired
	private SubmitService submitService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/touploadfile")
	public String touploadfile(String studentNum,HttpServletRequest request){
		 request.setAttribute("studentNum", studentNum);
		 return "/uploadFile";
	 }
	 
	@RequestMapping("/uploadfile")
	public String uploadfile(@RequestParam MultipartFile[] myfiles,HttpServletRequest request,HttpServletResponse response){
				System.out.println("----------------------start-----------------------");
				String path=null;
				//可以在上传文件的同时接收其它参数
		        //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
		         //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建
				Item item =new Item();
		        String realPath = request.getSession().getServletContext().getRealPath("/upload"); 
		         //设置响应给前台内容的数据格式
		        response.setContentType("text/plain; charset=UTF-8");
		        //设置响应给前台内容的PrintWriter对象
//		        PrintWriter out = response.getWriter();
		        //上传文件的原名(即上传前的文件名字)
		        String originalFilename = null;
		        //如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
		        //如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
		        //上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		        for(MultipartFile myfile : myfiles){
		        	if(myfile.isEmpty()){
		        		System.out.println("myfile is empty!");
		        		return null;
		             }else{
		                originalFilename = myfile.getOriginalFilename();
		                item. setTopic(request.getParameter("topic"));
		                item. setDescription(request.getParameter("description"));
		                item.setChapter(request.getParameter("chapter"));
		                item.setCreateDate(new Date());
		                item.setValid(1);
		                item.setIsDefault(0);
		                System.out.println("文件原名: " + originalFilename);
		                System.out.println("文件名称: " + myfile.getName());
		                System.out.println("文件长度: " + myfile.getSize());
		                System.out.println("文件类型: " + myfile.getContentType());
		                System.out.println("========================================");
		                try {
		                     //这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
		                     //此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
		                	path = request.getSession().getServletContext().getRealPath(File.separator) + "upload" + File.separator+originalFilename;  
		                	File file=new File(realPath);
		                	if(!file.exists()){
		                		System.out.println("文件夹不存在，新建一个文件夹");
		                		file.mkdirs();
		                	}else
		                		System.out.println("文件夹已经存在！");
		                		myfile.transferTo(new File(path));
		                	 //FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));
		                	} catch (IOException e) {

		                		System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
		                		e.printStackTrace();
//		                    	out.print("1`文件上传失败，请重试！！");
//		                    	out.flush();
		                		return null;
		                 }
		             }
		         }
		         //此时在Windows下输出的是[D:\Develop\apache-tomcat-6.0.36\webapps\AjaxFileUpload\\upload\愤怒的小鸟.jpg]
		         //System.out.println(realPath + "\\" + originalFilename);
		         //此时在Windows下输出的是[/AjaxFileUpload/upload/愤怒的小鸟.jpg]
		         //System.out.println(request.getContextPath() + "/upload/" + originalFilename);
		         //不推荐返回[realPath + "\\" + originalFilename]的值
		         //因为在Windows下<img src="file:///D:/aa.jpg">能被firefox显示,而<img src="D:/aa.jpg">firefox是不认的
//		         out.print("0`" + request.getContextPath() + "/upload/" + originalFilename);
//		         out.flush(); 
		         System.out.println("----------------------end-----------------------");
		        //fileManager.addFile(request.getSession().getAttribute("studentNum").toString(), originalFilename, request.getParameter("description"),path);
		         item.setMatherialPath(path);
		         try {
					itemService.save(item);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         return null;
	     /*
	      * request.setAttribute("user", userManager.getUser(user.getStudentNum()));
            	  request.setAttribute("studentName", userManager.getUser(user.getStudentNum()).getStudentName());
                  return "/student"; 
	      * */
	 }
	
	@RequestMapping("/studentUploadfile")
	public String studentUploadfile(@RequestParam MultipartFile[] myfiles,
			HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("代码开始：\n"+request.getParameter("answer")+"\n代码结束");
		String path = null;
		String realPath = request.getSession().getServletContext()
				.getRealPath("/upload");
		System.out.println(realPath);
		response.setContentType("text/plain; charset=UTF-8");
		String originalFilename = null;
		Submit submit = new Submit();
		Arrange arrange = new Arrange();
		for (MultipartFile myfile : myfiles) {
			
			if (myfile.isEmpty()) {
				System.out.println("myfile is empty!");
				try {
					arrange = itemService.get(Arrange.class, Integer.parseInt(request.getParameter("arrangeid")));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					arrange = itemService
							.get(Arrange.class, Integer.parseInt(request
									.getParameter("arrangeid")));

					originalFilename = myfile.getOriginalFilename();
					submit.setFileName(originalFilename);
					path = arrange.getDocpath() + File.separator
							+ originalFilename;
					File file = new File(arrange.getDocpath());
					if (!file.exists()) {
						System.out.println("文件夹不存在，新建一个文件夹");
						file.mkdirs();
					} else
						System.out.println("文件夹已经存在！");
					myfile.transferTo(new File(path));
				} catch (IOException e) {
					System.out.println("文件[" + originalFilename
							+ "]上传失败,堆栈轨迹如下");
					e.printStackTrace();
					return null;
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					return null;
				} catch (Exception e1) {
					e1.printStackTrace();
					return null;
				}
			}
		}
		try {
			// User user=(User)request.getSession().getAttribute("user");
			// submit.setStudent(user);
			submit.setArrange(arrange);
			String answer=URLDecoder.decode(URLDecoder.decode(request.getParameter("answer"),"UTF-8"),"UTF-8");
			System.out.println("代码开始：\n"+answer+"\n代码结束");
			submit.setAnswer(answer);
			submit.setSubmitTime(new Date());
			submit.setSubmitClass(arrange.getClassCourse().getCourseClass());
			Double sorce=new Double(-1);
			submit.setScore(sorce);
			submitService.save(submit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	 @RequestMapping("downloadfile")
		public void downloadFile(String fileName,HttpServletResponse response,HttpServletRequest request){
	
		 System.out.println("--------start---------");
		 
		 response.setContentType("application/x-download");   
		 //application.getRealPath("/main/mvplayer/CapSetup.msi");获取的物理路径   
		 String filedownload=null;
		 String filedisplay=null;
		 try {
			 System.out.println("解码前："+request.getParameter("fileposititon"));
			 System.out.println("解码后："+URLDecoder.decode(request.getParameter("fileposititon"),"UTF-8"));
		//未解码直接输出
			filedownload = request.getParameter("fileposititon");
			int num=filedownload.lastIndexOf("\\");  
			filedisplay=filedownload.substring(num+1);
			System.out.println("-------文件位置------"+filedownload);
			System.out.println("-------文件名-------"+filedisplay);
		//解码之后转码在jsp显示
			String Tfiledownload=URLDecoder.decode(request.getParameter("fileposititon"),"UTF-8");
			int Tnum=Tfiledownload.lastIndexOf("\\");  
			String Tfiledisplay=Tfiledownload.substring(Tnum+1);
				Tfiledisplay = URLEncoder.encode(Tfiledisplay,"UTF-8");
				System.out.println("转码后："+Tfiledisplay);
				response.addHeader("Content-Disposition","attachment;filename=" + Tfiledisplay);   
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		    
		  
		  java.io.OutputStream outp = null;   
		  java.io.FileInputStream in = null;   
		  try   
		  {   
		  outp = response.getOutputStream();   
		  in = new java.io.FileInputStream(filedownload);   
		  
		  byte[] b = new byte[1024];   
		  int i = 0;   
		  
		  while((i = in.read(b)) > 0)   
		  {   
		   outp.write(b, 0, i);   
		  }   
		outp.flush();   
		//要加以下两句话，否则会报错   
		//java.lang.IllegalStateException: getOutputStream() has already been called for this respons
	//	out.clear();   
		//out = pageContext.pushBody();   
		}   
		  catch(Exception e)   
		  {   
		  System.out.println("Error!");   
		  e.printStackTrace();   
		  }   
		  finally   
		  {   
		  if(in != null)   
		  {   
		//  in.close();   
		  in = null;   
		  }  
		  }   

		}
	@RequestMapping("showallitem")
	public String showAllFile(HttpServletRequest request,HttpServletResponse response){
		 request.setAttribute("item",itemService.getAllItem());
		 return "AllItem";
	 }

	 /* 功能: 根据前台选中的item的id
	  * 得到该id的item的所有信息
	  * 将item放入item中
	  * 返回到getFile.jsp
	  * 使用者:所有
	  */ 
	
	@RequestMapping("getitem")
	public String getFile(HttpServletRequest request,int itemId) throws Exception{
		Item item=itemService.get(Item.class, itemId);
		request.setAttribute("item", item);
		return "showItem";
	 }
	
	/*
	 * 删除单个题库题目
	 */
	@RequestMapping("deletesingleitem")
	public void deleteSingleItem(String itemId,HttpServletResponse response) throws Exception {
		String result = "{\"result\":\"error\"}";
		if (itemService.delItem(itemService.get(Item.class, Integer.parseInt(itemId)))) {
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
	 * 批量删除题目信息
	 * @param accountId
	 * @param response
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("/bacthDelItem")
	public void bacthDelItem(String itemIds, HttpServletResponse response) throws NumberFormatException, Exception {

		String result = "{\"result\":\"error\"}";
		boolean isSuccess = false;
		String[] arrIds = itemIds.split(",");
		for (int i = 0; i < arrIds.length; i++) {
			isSuccess = itemService.delItem(itemService.get(Item.class,Integer.parseInt(arrIds[i])));
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
	
	/**
	 * 调整到编辑题目页面
	 * @param request
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toEditItem")
	public String toEditItem(String itemId,HttpServletRequest request)
			throws Exception {
		if(!"".equals(itemId)){
		Item item = itemService.get(Item.class, Integer.parseInt(itemId));
		request.setAttribute("item", item);
		request.setAttribute("course", new Course());
		request.setAttribute("courseid", item.getCourse().getCourseId());
		}
		return "jsp/editItem";
	}
	
	/**
	 * 调整到展示默认题目页面
	 * @param request
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toShowDefaultItem")
	public String toShowDefaultItem(String itemId,HttpServletRequest request)
			throws Exception {
		if(!"".equals(itemId)){
		Item item = itemService.get(Item.class, Integer.parseInt(itemId));
		request.setAttribute("item", item);
		request.setAttribute("courseid", item.getCourse().getCourseId());
		}
		return "jsp/editItem";
	}
	
	 /*	功能:批量删除
	  * 根据前台传递来的删除item的deleteId数组
	  * 进行删除操作
	  * 返回到showallfile函数进行显示操作
	  * 使用者:管理员
	  */
	  //ps:此函数的使用需要从前台传回一个要删除的item的itemId数组!!!
	
	@RequestMapping("deleteitem")
	public String deleteFile(HttpServletRequest request) throws Exception{
		int[] deleteId=new int[15];	//此为测试数据......
		deleteId[0]=1;
		for(int i=0;i<15;i++)	//分页大小为15.故一次性最多删除15条item
			if(deleteId[i]!=0){
				Item item=itemService.get(Item.class, deleteId[i]);
				item.setValid(0);
				itemService.update(item);
			}
		return "redirect:/item/showallitem";
	 }
	
	 /* 功能:添加作业条目
	  * 使用者:教师或者管理员
	  * 从前台传回的表单必须与bean中的类的字段名称一模一样!
	  * 其中,itemId,creatorId,createDate等系统自动生成的数据不需要传回,将在后台完成
	  */
	
	@RequestMapping("additem")
	public String addItem(HttpServletRequest request,Item item) throws Exception{
		User user=(User) request.getSession().getAttribute("user");
		item.setCreator(user);
		item.setCreateDate(new Date());
		item.setValid(1);
		itemService.save(item);
		return "redirect:/item/showallitem";
	}
	

	/**
	 * 修改或添加个人题库题目
	 * @param request
	 * @param response
	 * @param item
	 * @throws Exception
	 */
	@RequestMapping("/modifyOrSaveItem")
	public void modifyOrSaveItem(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Item item = null;
		int creatorId = ((User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT)).getId();
		String itemId = (String) request.getParameter("itemId");
		String courseid = (String) request.getParameter("courseid");
		String chapter = (String) request.getParameter("chapter");
		String topic = (String) request.getParameter("topic");
		String description = (String) request.getParameter("description");
		String goal = (String) request.getParameter("goal");
		String cksuffix = (String)  request.getParameter("cksuffix");
		String suffix = (String) request.getParameter("suffix");
		String ckisDefault = (String)  request.getParameter("ckisDefault");
	   
	    int method = 0;
		    if("true".equals(cksuffix)){//设置提交方式，是否有文档
		    	method = 1;
		    }
		int isDefault = 0;
	    if("true".equals(ckisDefault)){//管理员设置为默认作业
	    	isDefault = 1;
	    }
	    
		if(!"".equals(itemId) && itemId != null){//修改
			item = itemService.get(Item.class,Integer.parseInt(itemId));
			item.setCourse(courseService.get(Course.class,Integer.parseInt(courseid)));
			item.setChapter(chapter);
			item.setTopic(topic);
			item.setDescription(description);
			item.setGoal(goal);
			item.setSuffix(suffix);
			item.setIsDefault(isDefault);
			item.setMethod(method);
			item.setType(1);
			}else{//
			item = new Item();
			item.setCourse(courseService.get(Course.class,Integer.parseInt(courseid)));
			item.setChapter(chapter);
			item.setTopic(topic);
			item.setDescription(description);
			item.setGoal(goal);
			item.setSuffix(suffix);
			item.setIsDefault(isDefault);
			item.setMethod(method);
			item.setType(1);
			item.setCreator(userService.getUserById(creatorId));
			item.setValid(1);
			item.setCreateDate(new Date());
			}
        	itemService.saveOrUpdate(item);
    		
    		List<Item> items = new ArrayList<Item>();
    		items.add(item);
    		JSONArray json = JSONArray.fromObject(items,JSONUtil.filterPropertyInJsonData("creator"));
    		response.setCharacterEncoding("UTF-8");
    		response.setContentType("application/json");
    		response.getWriter().print(json.toString());
	}
	
	@RequestMapping("showarrange")
	public String showArrangeById(HttpServletRequest request){
		int userId=1;
		User user=(User)request.getSession().getAttribute("user");
		//userId=user.getId();
		List <Arrange> userArranges=itemService.getUserArranges(userId);
		request.setAttribute("userArranges", userArranges);
		return null;											//返回路径你们自己写.
	}
	
	@RequestMapping("studentShowArrangeById")
	public String studentShowArrangeById(HttpServletRequest request) {
		int userId = 1;
		int pagesize = 10;
		PageBean page = new PageBean();

		page.setPageSize(pagesize);

		User user = (User) request.getSession().getAttribute("user");
		// userId=user.getId();
		List<Arrange> userArranges;
		int first;
		
			if (request.getParameter("pageNo") != null) {
				page.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
				page.init();
				first = ((Integer.parseInt(request.getParameter("pageNo"))) - 1)
						* pagesize;
			} else {
				page.setCurrentPage(1);
				page.init();
				first = 0;
			}
			if(request.getParameter("courseid").equals("0")){
				userArranges = itemService.getUserArranges(userId);
				
			}else{
				userArranges = itemService.getCourseArranges(Integer.parseInt(request.getParameter("courseid")),userId);
				
			}
		userArranges = itemService.getUserSubmitArranges(userArranges, Integer.parseInt(request.getParameter("option")));
		if (first + pagesize <= userArranges.size())
			userArranges = userArranges.subList(first, first + pagesize);
		else
			userArranges = userArranges.subList(first, userArranges.size());
		request.setAttribute("courseid", request.getParameter("courseid"));
		page.setAllRow(userArranges.size());
		page.setTotalPage(page.countTotalPage(pagesize, userArranges.size()));
		request.setAttribute("option", request.getParameter("option"));
		request.setAttribute("submitcout", itemService.hasSubmitCount(userArranges));
		request.setAttribute("homeworkcout", userArranges.size());
		request.setAttribute("pageUrl", "/Program/file/showallfile");
		request.setAttribute("pageAttrKey", page);
		request.setAttribute("Allcourses", itemService.getUserCourse(userId));
		for(int i=0;i<userArranges.size();i++)
		{
			System.out.println(userArranges.get(i).getSubmitTime());
			System.out.println(userArranges.get(i).getDeadlineDate());
			String[] a=new String [3];
			String[] b=new String [3];
			Date tempDate = userArranges.get(i).getDeadlineDate();
			b=new SimpleDateFormat("yyyy-MM-dd").format(tempDate).split("-");
			a=new SimpleDateFormat("yyyy-MM-dd").format(new Date()).split("-");
			if(Integer.parseInt(a[0])>Integer.parseInt(b[0]))
				userArranges.get(i).setHasPasstime(1);
			else if(Integer.parseInt(a[1])>Integer.parseInt(b[1])&&Integer.parseInt(a[0])==Integer.parseInt(b[0])){
				userArranges.get(i).setHasPasstime(1);
			}else if(Integer.parseInt(a[2])>Integer.parseInt(b[2])&&Integer.parseInt(a[1])==Integer.parseInt(b[1])){
				userArranges.get(i).setHasPasstime(1);
			}else {
				userArranges.get(i).setHasPasstime(0);
			}
			}
		request.setAttribute("userArranges", userArranges);
		request.setAttribute("file", userArranges);
		return "AllHomework";
	}
	
	/*
	 * 
	 */
	@RequestMapping("findcourseitem")
	public String findCourseItem(HttpServletRequest request,int courseId){
		courseId=1001;
		System.out.println(courseId);
		List<Arrange> courseArranges=itemService.getCourseArranges(courseId);
		request.setAttribute("courseArranges", courseArranges);
		return null;
	}
	
	@RequestMapping("updatefile")	
	public String updateFile(){
		return null;
	}
	
	/**
	 * 展示所有管理员添加的所有默认作业（所有课程）
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/showDefaultItems")
	public String showDefaultItems(HttpServletRequest request,HttpServletResponse response) throws IOException{	
		int pagesize = 2;
		PageBean page = new PageBean();
		page.setAllRow(itemService.getCountDefaultItems());
		page.setPageSize(pagesize);
		page.setTotalPage(page.countTotalPage(pagesize,
				itemService.getCountDefaultItems()));
		
		if ((request.getParameter("pageNo") != null) && page.getTotalPage()>=(Integer.parseInt(request.getParameter("pageNo")))) {
			page.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
			page.init();
			request.setAttribute("defaultitemlist", itemService.getDefaultItems(
					((Integer.parseInt(request.getParameter("pageNo"))) - 1)
							* pagesize, pagesize));
		} else {
			page.setCurrentPage(1);
			page.init();
			request.setAttribute("defaultitemlist",
					itemService.getDefaultItems(0, pagesize));
		}
		request.setAttribute("pageUrl", "/FinalDesign/item/showDefaultItems");
		request.setAttribute("pageAttrKey", page);
		return "jsp/showDefaultItems";	
	}
	
	/**
	 * 获取
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/showDefaultItemsNoPage")
	public void showDefaultItemsNoPage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Item> defaultItems = itemService.getDefaultItems();
		JSONArray json = JSONArray.fromObject(defaultItems,JSONUtil.filterPropertyInJsonData("creator"));
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json.toString());
	}
	
	/**
	 * 根据用户id获得用户的个人题库
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("findPersonalItemsByUser")
	public String findPersonalItemsByUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int userId = ((User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT)).getId();
		String courseidstr = (String)request.getParameter("courseid");
		int userType = ((User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT)).getRole();
		boolean isTeacher = true;
		if(userType == 1){
			isTeacher = false;
		}
		int courseid = -1;
		List<Course> courseForTeacher = new ArrayList<Course>();
		if(courseidstr==null){
			courseForTeacher = courseService.findCoursesByTeacherIdInSemester(userId);
		    if(courseForTeacher.size()>0)
			courseid = courseForTeacher.get(0).getCourseId();
		}else{
			courseid = Integer.parseInt(courseidstr);
		}
		int pagesize = 2;
		PageBean page = new PageBean();
		page.setAllRow(itemService.getCountItemByUser(userId,isTeacher,courseid));
		page.setPageSize(pagesize);
		page.setTotalPage(page.countTotalPage(pagesize,
				itemService.getCountItemByUser(userId,isTeacher,courseid)));
		
		if ((request.getParameter("pageNo") != null) && page.getTotalPage()>=(Integer.parseInt(request.getParameter("pageNo")))) {
			page.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
			page.init();
			request.setAttribute("itemlist", itemService.getItemByUser(
					((Integer.parseInt(request.getParameter("pageNo"))) - 1)
							* pagesize, pagesize,userId,isTeacher,courseid));
		} else {
			page.setCurrentPage(1);
			page.init();
			request.setAttribute("itemlist",
					itemService.getItemByUser(0, pagesize,userId,isTeacher,courseid));
		}
		request.setAttribute("pageUrl", "/FinalDesign/item/findPersonalItemsByUser?courseid="+courseid);
		request.setAttribute("pageAttrKey", page);
		request.setAttribute("courseid", courseid);
		return "jsp/manageItems";
	}
	
	/**
	 * 根据用户id获得用户的个人题库
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findPersonalItemsByUserNoPage")
	public void findPersonalItemsByUserNoPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int userId = ((User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT)).getId();
//		String courseidstr = (String)request.getParameter("courseid");
		int userType = ((User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT)).getRole();
		boolean isTeacher = true;
		if(userType == 1){
			isTeacher = false;
		}
		int courseid = -1;
		List<Course> courseForTeacher = new ArrayList<Course>();
		/*
		if(courseidstr==null){
			courseForTeacher = courseService.findCoursesByTeacherIdInSemester(userId);
		    if(courseForTeacher.size()>0)
			courseid = courseForTeacher.get(0).getCourseId();
		}else{
			courseid = Integer.parseInt(courseidstr);
		}
		*/
		List<Item> teacherItems = itemService.getItemByUser(userId);
		JSONArray json = JSONArray.fromObject(teacherItems,JSONUtil.filterPropertyInJsonData("creator"));
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json.toString());
	}
	
	@RequestMapping("finduserCourse")
	public void findUserCourse(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int userId = ((User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT)).getId();
		List<Class_Course> userCourse=itemService.getUserCourse(userId);
		JSONArray json = JSONArray.fromObject(userCourse,JSONUtil.filterPropertyInJsonData("creator"));
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json.toString());
	}
	
	
	@RequestMapping("addarrange")
	public String addArrange(HttpServletRequest request,Arrange arrange) throws Exception{
		arrange.setCreateDate(new Date());
		arrange.setValid(1);
		arrange.setHasSubmit(0);
		itemService.save(arrange);
		return null;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("addpatchitem")
	public String addPatchItem(HttpServletRequest request) throws Exception{
		String[] itemId=new String[10];
		String[] date=new String[30];
		String[] classCourseId=new String[10];
		ArrayList dat=new ArrayList();
		itemId=request.getParameterValues("checkbox"); 
		date=request.getParameterValues("date");
		classCourseId=request.getParameterValues("classcheckbox");
		String allDate=request.getParameter("alldate");
		if(allDate.isEmpty()){
			for(int i=0;i<date.length;i++)
				if(!date[i].isEmpty())
					dat.add(date[i]);
		}
		else
			for(int i=0;i<itemId.length;i++){
				dat.add(allDate);
			}
			itemService.addPatchArranges(itemId, classCourseId, dat);
		return null;
		
	}

	/*
	 * 提交作业
	 */
	@RequestMapping("/submitHomework")
	public String submitHomework(HttpServletRequest request,
			HttpServletResponse response) {
		String itemid = request.getParameter("itemid");
		try {
			Item item = itemService.get(Item.class, Integer.parseInt(itemid));
			request.setAttribute("itemid", request.getParameter("itemid"));
			request.setAttribute("topic", item.getTopic());
			request.setAttribute("filename", item.getMatherialPath().substring(item.getMatherialPath().lastIndexOf("\\")+1));
			request.setAttribute("matherialPath", item.getMatherialPath().replace("\\", "\\\\"));
			request.setAttribute("description", item.getDescription());
			request.setAttribute("arrangeid", request.getParameter("arrangeid"));
			request.setAttribute("option", request.getParameter("option"));
			request.setAttribute("courseid", request.getParameter("courseid"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/submitHomework";
	}
	
	@RequestMapping("findteacherarrange")
	public void showTeacherArrange(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int teacherId=Integer.parseInt(request.getParameter("teacherId"));
		List<Arrange> teacherArrange=itemService.getTeacherArranges(teacherId);
		JSONArray json = JSONArray.fromObject(teacherArrange);
		response.getWriter().print(json.toString());
	}
	
	@RequestMapping("showArrangeSubmit")
	public String showArrangeSubmit(HttpServletRequest request) throws Exception{
		int arrangeId=Integer.parseInt(request.getParameter("arrangeId"));
		Arrange arrange=itemService.get(Arrange.class, arrangeId);
		List<Submit> submits=itemService.getArrangeSubmits(arrange.getArrangeId());
		int hasSubmit=submits.size();
		List<User_Class> u_c=itemService.getArrangeClassUser(arrange.getClassCourse().getClassCourseId());
		int studentNum=u_c.size();
		int hasNotSubmit=studentNum-hasSubmit;
		List<User> unsubmitUser=itemService.getUnsubmitUser(u_c,submits);
		request.setAttribute("hasSubmit",hasSubmit);
		request.setAttribute("hasNotSubmit", hasNotSubmit);
		request.setAttribute("arrangeClassUser", u_c);
		request.setAttribute("arrange", arrange);
		request.setAttribute("submits", submits);
		request.setAttribute("unsubmitUser", unsubmitUser);
		return "showSubmits";
	}
	
	@RequestMapping("showsubmit")
	public String showSubmit(HttpServletRequest request) throws Exception{
		int submitId=Integer.parseInt(request.getParameter("submitId"));
		Submit submit=itemService.get(Submit.class, submitId);
		request.setAttribute("submit", submit);
		return "showSubmit";
	}
	 
	@RequestMapping("correctHomework")
	public String correctHomework(HttpServletRequest request) throws Exception{
		int submitId=Integer.parseInt(request.getParameter("submitId"));
		Submit submit=itemService.get(Submit.class, submitId);
		submit.setScore(Float.parseFloat(request.getParameter("score")));
		submit.setScoreTime(new Date());
		submit.setRemark(request.getParameter("remark"));
		itemService.update(submit);
		return "redirect:showArrangeSubmit?arrangeId="+submit.getArrange().getArrangeId();
	}
	@RequestMapping("patchCorrectHomework")
	public String pathCorrectHomework(HttpServletRequest request) throws Exception{
		int arrangeId=Integer.parseInt(request.getParameter("arrangeId"));
		System.out.println(arrangeId);
		Arrange arrange=itemService.get(Arrange.class, arrangeId);
		List<Submit> submits=itemService.getArrangeSubmits(arrange.getArrangeId());
		request.setAttribute("submits", submits);
		request.setAttribute("arrangeId", submits.get(0).getArrange().getArrangeId());
		return "patchCorrectHomework";
	}
	
	@RequestMapping("giveScoreRandom")
	public String giveScoreRandom(HttpServletRequest request)throws Exception{
		int minindex=Integer.parseInt(request.getParameter("minindex"));
		int maxindex=Integer.parseInt(request.getParameter("maxindex"));
		int minScore=Integer.parseInt(request.getParameter("minscore"));
		int maxScore=Integer.parseInt(request.getParameter("maxscore"));
		int arrangeId=Integer.parseInt(request.getParameter("arrangeId"));
		String[] accountId=request.getParameterValues("accountId");
		itemService.giveScoreRandom(minindex,maxindex,minScore,maxScore,arrangeId,accountId);
		request.setAttribute("arrangeId", arrangeId);
		return "redirect:patchCorrectHomework?arrangeId="+arrangeId ;
	}
	

	/*
	 * 查看作业
	 */
	@RequestMapping("/throughHomework")
	public String throughHomework(HttpServletRequest request,
			HttpServletResponse response) {
		String itemid = request.getParameter("itemid");
		try {
			Item item = itemService.get(Item.class, Integer.parseInt(itemid));
			Submit submit=itemService.getUserSubmit(Integer.parseInt(request.getParameter("arrangeid")),Integer.parseInt(request.getParameter("userid")));
			request.setAttribute("submit", submit);
			request.setAttribute("topic", item.getTopic());
			request.setAttribute("description", item.getDescription());
			request.setAttribute("arrangeid", request.getParameter("arrangeid"));
			request.setAttribute("option", request.getParameter("option"));
			request.setAttribute("courseid", request.getParameter("courseid"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/homeworkResult";
	}
	
}
