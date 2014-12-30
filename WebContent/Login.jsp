<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.awt.*,
java.awt.image.*,java.util.*,javax.imageio.*"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 	
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>
<meta charset="utf-8">
<title>登陆</title>
<style type="text/css">
	img.bottommargin {margin-bottom: 0.5cm;}
</style>

<link rel="stylesheet" href="https://d17f28g3dsa4vh.cloudfront.net/CACHE/css/151229ae59ef.css" type="text/css" />
<link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap-theme.min.css">
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= basePath %>/js/controljs.js"></script>
<script type="text/javascript">
    
    function changeImg(){   
        var imgSrc = $("#imgObj");   
        var src = imgSrc.attr("src");   
        imgSrc.attr("src",chgUrl(src));   
    }   
    //时间戳   
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
    function chgUrl(url){   
        var timestamp = (new Date()).valueOf();   
        url = url.substring(0,100);   
        if((url.indexOf("&")>=0)){   
            url = url + "×tamp=" + timestamp;   
        }else{   
            url = url + "?timestamp=" + timestamp;   
        }   
        return url;   
    }   
    
    </script>
</head>

<body class="page login" > 
 <!--div style="width:1366px; height:592px;background-image: url('image/background.jpg')"-->
<div id="main">       
<div class="container">
    <div id="login" class="accounts-form row">
        <div class="span4 offset2 social">
            <div >
           		<h1><B><img src="image/logo.png"><font style="FONT-FAMILY: 华文行楷;">华南农业大学</font></B></h1>
		   		<img src="image/school1.jpg"/>
            </div>
        </div>
        <div class="span6">
            <form action="/FinalDesign/login" method="post" id="auth-form">
            	<div style='display:none'><input type='hidden' name='csrfmiddlewaretoken' value='aHJ88cmySJNcdaAgprkdCP39ktVY33UM' />
        		</div>
        		<div class="input">
     				<h2>欢迎使用作业管理系统</h2>
     				<c:if test="${!empty error}">
						<font color="red"><c:out value="${error}"/></font>
					</c:if>
        		</div>
        		<div class="input">
            		<input id="id_username" type="text" name="accountId" value="00000002" maxlength="75" placeholder="学号"  />
        		</div>
        		<div class="input">
            		<input type="password" name="password" id="id_password" value="123456" placeholder="密码" />       
        		</div>
				<div class="input">
            		<input style="width:150px" type="text" name="yanzhengma" id="yanzhengma" maxlength="4" placeholder="验证码" />
            		<img class="bottommargin" name="imgObj" id="imgObj"  src="<%=request.getContextPath()%>/Image.jsp" onclick="changeImg()">点击图片换一张
        		</div>
        		<div class="actions">
            		<input type="submit" value="Log In" class="btn btn-primary" />
            		<a href="/accounts/password/reset">Forgot your password?</a> 
        		</div>
			</form>
		</div>
	</div>
</div>
<div id="push">
</div>
</div>
<!--/div-->
</body>
</html>
