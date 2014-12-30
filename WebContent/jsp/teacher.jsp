<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <% String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    border-right: 1px solid #C1DAD7; 
    border-bottom: 1px solid #C1DAD7; 
    background: #fff; 
    font-size:11px; 
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
html>body td{ font-size:11px;} 
</style> 

<style type="text/css">
    .center{
    margin: 0 auto;
}
    .left{
    float:right;
    }
</style>


<!-- 设置标题 -->
<title>
  教师主界面             </title> 
<link rel='stylesheet' id='zanblog-style-css'  href='<%=request.getContextPath()%>/css/style.2.0.7.css' type='text/css' media='all' />
<link rel='stylesheet' id='bootstrap-style-css'  href='<%=request.getContextPath()%>/css/bootstrap.3.0.0.css' type='text/css' media='all' />
<link rel='stylesheet' id='fontawesome-style-css'  href='<%=request.getContextPath()%>/css/font-awesome.min.4.0.1.css' type='text/css' media='all' />
<link rel='stylesheet' id='icheck-style-css'  href='<%=request.getContextPath()%>/css/red.3.6.css' type='text/css' media='all' />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.1.10.2.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/embed.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.3.0.0.js"></script>   
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zanblog.2.0.7.js"></script>   
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.1.9.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.icheck.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/teacher.js"></script>
<script type="text/javascript">
	setInterval("showtime.innerHTML='登录日期：'+new Date().toLocaleString();",1000);
</script>
<link rel="stylesheet" href="http://www.jsctrlc.com/download/2013-9-3/static/stylesheets/bootstrap-switch.css" />
<script src="http://www.jsctrlc.com/download/2013-9-3/static/js/bootstrap-switch.js"></script>

</head>
<body class="home blog">
<jsp:include page="TeacherPublicJsp.jsp" />
	<div class="container">
	<div id="arrangehomework">
		 <h4><i class="fa fa-list-ul"></i>  当前位置 ：作业布置</h4>

		<section class="well">
	<ul class="nav nav-tabs" role="tablist" id="myTab">
  <li class="active"><a href="#home" role="tab" data-toggle="tab"><font size="3px">布置作业</font></a></li>
  <li><a href="#profile" role="tab" data-toggle="tab"><font size="3px">添加新题目</font></a></li>
</ul>

<div class="tab-content">
  <div class="tab-pane active" id="home">
  <hr>
  	  <form action="item/addpatchitem" method="post">
  	  <div class="col-md-12">
           <font size="3px">选择默认题目 :</font>
             <input type="checkbox" id="switch1" onchange="showNo1()" />
      	   <hr>
        	<div id="NO1" style="display:none">
			<div class="ui-datatable-tablewrapper" id="defaulttable">
			</div>
			<hr>			<hr>
		</div>
 	</div>
 	  <div class="col-md-12">
    	  <font size="3px">选择题库题目  : </font>
    	    <input type="checkbox" id="switch2" onchange="showNo2()" />
          <div id="NO2" style="display:none">
               <hr>
					<div class="ui-datatable-tablewrapper" id="teacheritem">
					</div>
					<hr>
				</div>
      </div>
      <hr>
        <div class="col-md-12">
    	  <font size="3px">&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;选择课程 : </font>
    	    <select name="j_idt44" size="1" id="courseforteacher" style="width:40" onchange="onCourseSelectChange(this);">					
		    </select>
		    <div id="courseclasslist">
               <hr>
					<div class="ui-datatable-tablewrapper" id="classforcourse">
					</div>
					<hr>
				</div>
         </div>
      <hr>  
      <font size="3px">&nbsp;&nbsp;统一截止时间：</font><input type="date" name="alldate"/>
     <div style="margin-left:46%;">
      <input class="btn btn-info" type="submit" value="布置作业 ">
      </div>		
      </form>
	</div> 
	<div class="tab-pane" id="profile">
	 <div style="width:100%; height:100%;">
	 		 <h4><i class="fa fa-list-ul"></i>  当前位置 ：&amp;添加作业</h4>

		<section class="well" style="padding-bottom: 50px;">
		<!-- 编辑作业 -->
		<div id="edithomework" >
		<div class="search">
		<button title="返回" onclick="location.href='/Program/file/showarrange?courseid=${courseid}&option=${option}'" class="btn btn-default"><span class="fa fa-mail-reply"></span>    返回</button>
		</div>
		
		<hr>
		<div style="width:85%; height:50%;" class="center">
		<div class="col-md-4 col-md-offset-4">
			<div class="form-group">
    			<div>
    				<label style="display:inline">作业标题</label>
    	  			<input type="text" class="form-control" id="itemtopic" name="itemtopic">
				</div>
 			</div>
 			<div class="form-group">
 				<label>作业章节</label>
    	 		<input type="text" class="form-control" id="itemchapter" name="itemchapter"/>	
  			</div>	
		</div>
		<div class="col-md-10 col-md-offset-1 form-group" class="form-group">
			<label>题目要求</label>
			<textarea id="itemdescription" rows="20" cols="100%" class="form-control" name="itemdescription"></textarea>
		</div>
		<div class="col-md-1 col-md-offset-5">
			<input type="file" id="itemfile" name="myfiles">
		</div>
	    <div style="width:10%; height:10%;">
				<input type="submit" class="btn btn-primary" onclick="ajaxFileUpload();" type="button" value="提交"/>		
		</div>
		</div>
		</div>
	<!-- 提交结果 -->
	
		<!-- 1 成功提交 -->
			<div id="success" style="display:none;">
			<br><br><br><br>
				<div class="row" style="height: 286px;">
				<br><br>
					<div class="col-md-5">
						<img  style="float:right;height: 161px;" src="<%=request.getContextPath()%>/image/checked.png">
					</div>
					<div class="col-md-6">
						<h1><strong>作业提交成功!</strong></h1><br><br>
						<font size="2px">&nbsp;若要查看提交结果请点击按钮&nbsp;&nbsp;</font><input class="btn btn-info" type="button" onclick="location='/Program/file/throughHomework?itemid=${itemid}&arrangeid=${arrangeid}&courseid=${courseid}&option=${option}&userid=1'" value="查看"/>
					</div>
				</div>
			</div>
		<!-- 2 提交失败 -->
		  	<div id="false" style="display:none;">
		  	<br><br><br><br>
				<div class="row" style="height: 286px;">
				<br><br>
					<div class="col-md-5">
						<img  style="float:right;height: 120px;" src="<%=request.getContextPath()%>/image/error.png">
					</div>
					<div class="col-md-6">
						<h1><strong>作业提交失败!</strong></h1><br><br>
						<font size="2px">&nbsp;若要刷新页面重新提交请点击按钮&nbsp;&nbsp;</font><input onclick="locaton='/Program/file/submitHomework?itemid=${itemid}&arrangeid=${arrangeid}&courseid=${courseid}&option=${option}'" class="btn btn-info" type="button" value="刷新"/>
					</div>
				</div>
		  	</div>
		
		<form id="AA">
		</form>
		
		
		</section>
		 </div>
		 </div>
	
  	</div>
  </section>
	</div>
	</div>
	
<footer id="zan-footer">	      	
	Copyright © 2014 <a target="_blank" title="华南农业大学" href="">java小组</a> Just do it, <a target="_blank" title="华南农业大学信息学院" href="">华南农业大学信息学院</a>. All Rights Reserved.
</footer>
</body>
</html>
