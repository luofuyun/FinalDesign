package com.zhst.Web;

import javax.servlet.http.HttpServletRequest;

import com.zhst.Bean.User;
import com.zhst.cons.CommonConstant;

/*
 * controller基类
 * 1 得到session用户
 * 2 设置session用户
 */
public class BaseController {

	/*
	 * 获取session中的用户
	 * @param httpServletRequest
	 * @return User
	 */
  public User getSessionUser(HttpServletRequest request){
	  return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
  }
	
  /*
   * 设置session中的用户
   * @param httpServletRequest
   * @param User
   */
  public void setSessionUser(HttpServletRequest request,User user){
	  request.getSession().setAttribute(CommonConstant.USER_CONTEXT,user);
  }
  
}
