package com.zhst.Web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhst.Bean.PageBean;
import com.zhst.Bean.User;
import com.zhst.Service.AnnounceService;
import com.zhst.cons.CommonConstant;

@Controller
@RequestMapping("/announceinfo")
public class AnnounceInfoController extends BaseController{

	@Autowired
	private AnnounceService announceService;
	
	@RequestMapping("/getAllInfo")
	public String getAllInfo(HttpServletRequest request) {
		int pagesize = 10;
		PageBean page = new PageBean();
		User creator = (User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
		String status = request.getParameter("status");
		String username = request.getParameter("username");
	    page.setAllRow(announceService.getInfoCount(creator));
		page.setPageSize(pagesize);
		page.setTotalPage(page.countTotalPage(pagesize,
				announceService.getInfoCount(creator)));
		if(username != null){
		try {
			username = URLDecoder.decode(username,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		}
		if (request.getParameter("pageNo") != null) {
			page.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
			page.init();
			request.setAttribute("infoList", announceService.getInfoItem(
					((Integer.parseInt(request.getParameter("pageNo"))) - 1)
							* pagesize, pagesize,status,username,creator));
		} else {
			page.setCurrentPage(1);
			page.init();
			request.setAttribute("infoList",
					announceService.getInfoItem(0, pagesize,status,username,creator));
		}
		if(status != null){
			request.setAttribute("status",status);				
		}else{
			request.setAttribute("status","0");
		}
		request.setAttribute("pageUrl", "/Program/announceinfo/getAllInfo");
		request.setAttribute("pageAttrKey", page);
		return "WEB-INF/jsp/infoManage";
	}
	
	@RequestMapping("/delInfo")
	public void delInfo(String infoId, HttpServletResponse response) {

		String result = "{\"result\":\"error\"}";

		if (announceService.delInfo(infoId)) {
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
	
	@RequestMapping("/toAddInfo")
	public String toAddInfo() {
		return "WEB-INF/jsp/addInfo";
	}
	
	@RequestMapping("/addInfo")
	public void addInfo(HttpServletRequest request, HttpServletResponse response) {
	}
}
