<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <% String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %> 
<%@ taglib prefix="mx" tagdir="/WEB-INF/tags" %>  
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>题库管理 </title> 
<link rel='stylesheet' id='zanblog-style-css'  href='<%=request.getContextPath()%>/css/style.2.0.7.css' type='text/css' media='all' />
<link rel='stylesheet' id='bootstrap-style-css'  href='<%=request.getContextPath()%>/css/bootstrap.3.0.0.css' type='text/css' media='all' />
<link rel='stylesheet' id='fontawesome-style-css'  href='<%=request.getContextPath()%>/css/font-awesome.min.4.0.1.css' type='text/css' media='all' />
<link rel='stylesheet' id='icheck-style-css'  href='<%=request.getContextPath()%>/css/red.3.6.css' type='text/css' media='all' />
<script type="text/javascript" src="<%=path%>/js/jquery.1.10.2.js"></script> 
<script type="text/javascript" src="<%=path%>/js/embed.js"></script> 
<script type="text/javascript" src="<%=path%>/js/bootstrap.3.0.0.js"></script>   
<script type="text/javascript" src="<%=path%>/js/zanblog.2.0.7.js"></script>   
<script type="text/javascript" src="<%=path%>/js/jquery.min.1.9.1.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.icheck.js"></script>
<script type="text/javascript" src="../js/manageItems.js"></script>
<script type="text/javascript">
	setInterval("showtime.innerHTML='登录日期：'+new Date().toLocaleString();",1000);
	var courseType = ('${courseid}' != -1 ? '${courseid}':'0');
</script>
</head>
<body class="home blog">
<jsp:include page="TeacherPublicJsp.jsp" />	
<div class="container">
	<div id="showdefault">
		<h4><i class="fa fa-list-ul"></i>当前位置：题库管理</h4>
			<section class="well" style="padding:0;padding-top:7px;">
			<div id="btn-css">
<!--  作业条目表格 -->
<div>
	<div id="submits" class="ui-datatable ui-widget">
		<div class="ui-datatable-tablewrapper">
		<div style="margin-top:10px;margin-bottom:10px;margin-left:5px;">
				        课程 : &nbsp;<select name="j_idt44" size="1" id="courseforteacher" style="width:40" onchange="onCourseSelectChange(this);">					
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- 先不做搜索功能
						<input type="text" id="searchusername" value="输入标题查询题库" style="color: gray;"
			          onfocus="if(value=='输入标题查询题库') {value='';}"
			          onblur="if (value=='') {value='输入标题查询题库';}"></input>
					<button class="btn btn-primary"  onclick="searchItemByTopic()">查询</button>
				    -->
					<button class="btn btn-primary" onclick="toEditItem('')">添加题库条目</button>
				    <button class="btn btn-primary" onclick="BatchDelItem()">批量删除题库条目</button>
		</div>
			<table id="mytable" cellspacing="0"
				summary="The technical specifications of the Apple PowerMac G5 series">
				<tr align="center">
					<th scope="col" abbr="Configurations"><input type="checkbox"
						id="selectUser" onclick="switchSelectBox()" /></th>
					<th scope="col" abbr="Dual 1.8">&nbsp;&nbsp;课程名称</th>
					<th scope="col" abbr="Dual 2">&nbsp;&nbsp;&nbsp;&nbsp;题目</th>
					<th scope="col" abbr="Dual 2.5">&nbsp;&nbsp;&nbsp;&nbsp;章节</th>
					<th scope="col" abbr="Dual 1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作业描述</th>
				    <th scope="col" abbr="Dual 2">&nbsp;&nbsp;&nbsp;&nbsp;操&nbsp;&nbsp;作</th>
				</tr>
				<c:forEach  var="tempitem" items="${itemlist}" >
	              <tr align="center">
	                 <th scope="row" abbr="Model" class="spec"><input
						type="checkbox" id="selectItemCk" name="selectItemCk" value="${tempitem.itemId}"/></th>
	                  <td align="center">${tempitem.course.courseName}</td>
	                  <td>${tempitem.topic}</td>
	                  <td>${tempitem.chapter}</td>
	                  <td>${tempitem.description}</td>
	                  <td>
	                    <button class="btn btn-primary" onclick="toEditItem('${tempitem.itemId}')">查看编辑更多信息</button>
						<button class="btn btn-primary" onclick="delItem('${tempitem.itemId}','${tempitem.topic}')">删除</button>           
	                  </td>
	                  </tr>
	          </c:forEach>	 
			</table>
		</div>
	</div>
</div>
</div>
	<div style="width:50%; margin-left: 30px;margin-left: 30px;margin-top:10px;margin-bottom: 8px;font-size:1.2em;" class="center" >
				<mx:PageBar pageUrl="${pageUrl}" pageAttrKey="${pageAttrKey}"/>
	</div>
 </section>
</div>
</div>
<footer id="zan-footer">	      	
	Copyright © 2014 <a target="_blank" title="华南农业大学" href="">java小组</a> Just do it, <a target="_blank" title="华南农业大学信息学院" href="">华南农业大学信息学院</a>. All Rights Reserved.	</footer>
</body>
</html>
