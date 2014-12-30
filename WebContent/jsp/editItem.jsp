<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <% String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %> 
<%@ taglib prefix="mx" tagdir="/WEB-INF/tags" %>  
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@page import="java.util.Date"%> 
<!DOCTYPE html>
<!--[if IE 7]>
<html class="ie ie7" lang="zh-CN">
<![endif]-->
<!--[if IE 8]>
<html class="ie ie8" lang="zh-CN">
<![endif]-->
<!--[if !(IE 7) | !(IE 8)  ]><!-->
<html lang="zh-CN">
<!--<![endif]-->
<head>
<meta charset="utf-8">

<style type="text/css"> 
/* CSS Document */ 
.center{
    margin: 0 auto;
}

.left{
    float:right;
}

body { 
    font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    color: #4f6b72; 
    background: #E6EAE9; 
} 

a { 
    color: #c75f3e; 
} 

#mytable { 
    width: 100%; 
    padding: 0; 
    margin: 0; 
} 

caption { 
    padding: 0 0 5px 0; 
    width: 700px;      
    font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    text-align: right; 
} 

th { 
    font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    color: #4f6b72; 
    font-size:15px;
    border-right: 1px solid #C1DAD7; 
    border-bottom: 1px solid #C1DAD7; 
    border-top: 1px solid #C1DAD7; 
    letter-spacing: 2px; 
    text-transform: uppercase; 
    text-align: left; 
    padding: 6px 6px 6px 12px; 
    background: #CAE8EA url(images/bg_header.jpg) no-repeat; 
} 

th.nobg { 
    border-top: 0; 
    border-left: 0; 
    border-right: 1px solid #C1DAD7; 
    background: none; 
} 

td { 
	letter-spacing: 2px; 
    text-align: left; 
	font-size:15px;
    border-right: 1px solid #C1DAD7; 
    border-bottom: 1px solid #C1DAD7; 
    background: #fff; 
    padding: 6px 6px 6px 12px; 
    color: #4f6b72; 
} 


td.alt { 
    
    background: #F5FAFA; 
    color: #797268; 
} 

th.spec { 
    border-left: 1px solid #C1DAD7; 
    border-top: 0; 
    background: #fff url(images/bullet1.gif) no-repeat; 
    font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
} 

th.specalt { 
    border-left: 1px solid #C1DAD7; 
    border-top: 0; 
    background: #f5fafa url(images/bullet2.gif) no-repeat; 
    font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    color: #797268; 
} 
/*---------for IE 5.x bug*/ 
html>body td{ font-size:13px;} 
</style> 
<!-- 设置标题 -->
<title>查看布置作业 </title> 


<link rel='stylesheet' id='zanblog-style-css'  href='<%=request.getContextPath()%>/css/style.2.0.7.css' type='text/css' media='all' />
<link rel='stylesheet' id='bootstrap-style-css'  href='<%=request.getContextPath()%>/css/bootstrap.3.0.0.css' type='text/css' media='all' />
<link rel='stylesheet' id='fontawesome-style-css'  href='<%=request.getContextPath()%>/css/font-awesome.min.4.0.1.css' type='text/css' media='all' />
<link rel='stylesheet' id='icheck-style-css'  href='<%=request.getContextPath()%>/css/red.3.6.css' type='text/css' media='all' />
<script type="text/javascript">
	setInterval("showtime.innerHTML='登录日期：'+new Date().toLocaleString();",1000);
	var courseType = ('${courseid}' != -1 ? '${courseid}':'0');
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.1.10.2.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/embed.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.3.0.0.js"></script>   
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zanblog.2.0.7.js"></script>   
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.icheck.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>  
<script type="text/javascript" src="<%=request.getContextPath()%>/js//My97DatePicker/WdatePicker.js"></script>  
<script type="text/javascript" src="<%=request.getContextPath()%>/js/editItem.js"></script>

</head>
<body class="home blog">
<jsp:include page="TeacherPublicJsp.jsp" />	
<div class="container">
	<div id="showdefault">
	<div class="col-md-12">
	<section class="well">
		<h4><i class="fa fa-list-ul"></i>当前位置： 查看作业情况</h4>
		<div class="col-md-12">
		<center>
	      <table style="font-size:15px;" cellpadding="5" cellspacing="5">
	          <tr>
	            <td><input type="hidden" id="itemId" name="itemId" value="${item.itemId}"/>
			      </td>
			  </tr>
				<h2>以下带<font style="color:red;"> * </font>号为必填项</h2>
				<tr>         
                	<td><font style="color: red;"> * </font>课程名称：</td>
                	<td><select name="j_idt44" size="1" id="courseforteacher" name="course" style="width:40">					
					</select>
					</td>
                	<td></td>	
                </tr>
				<tr>         
                	<td><font style="color: red;"> * </font>章节：</td>
                	<td>
                	<input type="text" id="chapter" name="chapter" value="${item.chapter}" maxLength="15"/>
                	</td>
                	<td><div id="chaptererror" style="color:red;display: none"></div></td>      		
                </tr>
                <tr>         
                	<td><font style="color: red;"> * </font>题目：</td>
                	<td>
                	<input type="text" id="topic" name="topic" value="${item.topic}" maxLength="15"/>
                	</td>	
                	<td><div id="topicerror" style="color:red;display: none"></div></td>
                </tr>
                  <tr>         
                	<td>&nbsp;题目描述：</td>
                	<td>
                	<input type="text" style="width: 700px;height: 250px;" id="description" name="description" value="${item.description}" maxLength="15"/>
                	<td></td>
                	</td>	
                </tr>
               <tr>         
                	<td><font style="color: red;"> * </font>默认得分：</td>
                	<td>
                	<input type="text" id="goal" name="goal" value="${item.goal}" maxLength="15"/>
                	</td>
                	<td><div id="goalerror" style="color:red;display: none"></div></td>	
                </tr>
                <tr>
					<td>&nbsp;是否需要提交文档:</td>
							<td>
							<c:choose>
                			 <c:when test="${item.method == 1}">
                	            <input type="checkbox" id="checkMethod" name="checkMethod" onchange="showMethodArea()" checked="checked"/>&nbsp;是
                              </c:when>
                              <c:otherwise>
                	             <input type="checkbox" id="checkMethod" name="checkMethod" onchange="showMethodArea()" />&nbsp;是
                	          </c:otherwise>
                        </c:choose>
                    </td>
					<td></td>
				</tr>
                <tr>
                    <td><font style="color: red;"> * </font>文档后缀:</td>	
                	<td>
                	<c:choose>
                	<c:when test="${item.method == 1}">
                	<select id="suffix" name="suffix">
                	</c:when>
                	<c:otherwise>
                	<select id="suffix" name="suffix" disabled="disabled">
                	</c:otherwise>
                	</c:choose>
                	<c:choose>
                	<c:when test="${item.suffix == 'rar'}">
                	   <option value='rar' selected="selected">rar</option>
                       <option value='doc'>doc</option>
                       <option value='doc+rar'>doc+rar</option>
                     </c:when>
                     <c:when test="${item.suffix == 'doc'}">
                	   <option value='rar'>rar</option>
                       <option value='doc'  selected="selected">doc</option>
                       <option value='doc+rar'>doc+rar</option>
                     </c:when>
                     <c:otherwise>
                	   <option value='rar'>rar</option>
                       <option value='doc'>doc</option>
                       <option value='doc+rar'  selected="selected">doc+rar</option>
                     </c:otherwise>
                    </c:choose>
                	</select>
                	</td>
				    <td></td>
			    </tr>
			    <c:if test="${item.creator.role == 1 }">
			       <tr>
                    <td><font style="color: red;"> * </font>是否设置为默认作业:</td>	
                	<td>
                 	<c:choose>
                	<c:when test="${item.isDefault == 1}">
                	  <input type="checkbox" id="isDefault" name="isDefault" checked="checked" />&nbsp;是
                     </c:when>
                     <c:otherwise>
                	 <input type="checkbox" id="isDefault" name="isDefault"  />&nbsp;是
                	 </c:otherwise>
                    </c:choose>
                	</td>
				    <td></td>
			    </tr>
			    </c:if>
                <tr>
                    <td></td>
                    <td><text id="save_button" name="save_button"
										class="btn btn-primary"
										onclick="modifyItemInfo()" type="submit">
										保存
									</text>&nbsp;&nbsp;
									<text id="cancel_button" name="cancel_button"
										class="btn btn-primary"
										onclick="cancel()" type="submit">
										取消
									</text>
					</td>
                </tr> 
			</table>
	</center>

		</div>
	<footer id="zan-footer">	      	
	Copyright © 2014 <a target="_blank" title="华南农业大学" href="">java小组</a> Just do it, <a target="_blank" title="华南农业大学信息学院" href="">华南农业大学信息学院</a>. All Rights Reserved.
	</footer>
	</section>
	</div>
	</div>
	</div>
</body>
</html>
