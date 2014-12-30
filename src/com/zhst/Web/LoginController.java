package com.zhst.Web;

import com.zhst.Bean.Course;
import com.zhst.Bean.PageBean;
import com.zhst.Bean.User;
import com.zhst.Dao.UserDao;
import com.zhst.Service.CourseService;
import com.zhst.Service.UserService;

import java.lang.String;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhst.Util.MD5;
import com.zhst.cons.CommonConstant;

/**
 * 登录Controller
 * @author Zhst
 *
 */
@Controller("loginController")
public class LoginController extends BaseController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/login")	
    public ModelAndView loginCheck(HttpServletRequest request,User user,String yanzhengma){
		User newUser = userDao.getUserByAccountId(user.getAccountId());
		String md5Password = MD5.getMD5(user.getPassword());
        boolean isValidUser=
                userService.hasMatchUser(user.getAccountId(), md5Password);
        if(!yanzhengma.toLowerCase().equals(request.getSession().getAttribute("validateCode").toString().toLowerCase()))
        	return new ModelAndView("Login","error","验证码错误");
        if(!isValidUser){
            return new ModelAndView("Login","error","用户名或密码错误");
        }else if(newUser == null){
        	return new ModelAndView("Login","error","用户名或密码错误");
        }else{
        	setSessionUser(request,newUser);
        	request.getSession().setAttribute("user", newUser);
        	switch(newUser.getRole()){
        	case 1:return new ModelAndView("jsp/admin");
        	case 2:return new ModelAndView("jsp/teacher");
        	case 3:return new ModelAndView("jsp/student");
        	default:return new ModelAndView("/Login");
        	}
        }
    }

	@RequestMapping(value="/loginout")
	public String loginout(HttpSession session){
		session.removeAttribute(CommonConstant.USER_CONTEXT);
		return "/Login";
	}
	
}

