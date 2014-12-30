<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="com.zhst.Bean.User"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String savemsg = (String) request.getAttribute("savemsg");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta charset="utf-8">

<style type="text/css">
/* CSS Document */
body {
	font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica,
		sans-serif;
	color: #4f6b72;
	background: #E6EAE9;
}

a {
	color: #c75f3e;
}

#mytable {
	width: 1500px;
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
	background: #CAE8EA url(images/bg-header.jpg) no-repeat;
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
	font-size: 11px;
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
html>body td,th {
	font-size: 15px;
}
</style>

<style type="text/css">
.recentcomments a {
	display: inline !important;
	padding: 0 !important;
	margin: 0 !important;
}
</style>

<style type="text/css" id="syntaxhighlighteranchor">
</style>

<!-- 设置标题 -->
<title>管理员主界面</title>

<link rel='stylesheet' id='zanblog-style-css'
	href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/style.css?ver=2.0.7'
	type='text/css' media='all' />
<link rel='stylesheet' id='bootstrap-style-css'
	href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/ui/css/bootstrap.css?ver=3.0.0'
	type='text/css' media='all' />
<link rel='stylesheet' id='fontawesome-style-css'
	href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/ui/font-awesome/css/font-awesome.min.css?ver=4.0.1'
	type='text/css' media='all' />
<link rel='stylesheet' id='icheck-style-css'
	href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/ui/css/flat/red.css?ver=3.6'
	type='text/css' media='all' />
<link rel='stylesheet' id='custom-style-css'
	href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/ui/css/custom.css?ver=2.0.7'
	type='text/css' media='all' />
<link rel="EditURI" type="application/rsd+xml" title="RSD"
	href="http://www.yeahzan.com/zanblog/xmlrpc.php?rsd" />
<link rel="wlwmanifest" type="application/wlwmanifest+xml"
	href="http://www.yeahzan.com/zanblog/wp-includes/wlwmanifest.xml" />

<script type="text/javascript" src="http://static.duoshuo.com/embed.js"
	charset="UTF-8" async="async"></script>
<script src="http://v3.bootcss.com/dist/js/bootstrap.js"></script>
<script src="http://cdn.bootcss.com/holder/2.0/holder.min.js"></script>
<script src="http://cdn.bootcss.com/highlight.js/7.3/highlight.min.js"></script>
<script>
	hljs.initHighlightingOnLoad();
</script>
<script src="http://v3.bootcss.com/docs-assets/js/application.js"></script>
<script type='text/javascript'
	src='http://www.yeahzan.com/zanblog/wp-content/plugins/infinite-scroll/js/front-end/jquery.infinitescroll.js?ver=2.6.1'></script>
<script type='text/javascript'
	src='http://www.yeahzan.com/zanblog/wp-content/plugins/infinite-scroll/behaviors/manual-trigger.js?ver=2.6.1'></script>
<script type='text/javascript'
	src='http://www.yeahzan.com/zanblog/wp-content/plugins/ml-slider/assets/sliders/flexslider/jquery.flexslider-min.js?ver=2.3'></script>
<script type='text/javascript'
	src='http://www.yeahzan.com/zanblog/wp-content/plugins/ml-slider/assets/easing/jQuery.easing.min.js?ver=2.3'></script>
<script type="text/javascript">
		// Because the `wp_localize_script` method makes everything a string
		infinite_scroll = jQuery.parseJSON(infinite_scroll);

		jQuery(infinite_scroll.contentSelector).infinitescroll(infinite_scroll,
				function(newElements, data, url) {
					eval(infinite_scroll.callback);
				});
</script>
<script type="text/javascript">
       var courseid = ('${courseid}' != null? '${courseid}':null);
       var classid = ('${classid}' != null? '${classid}':null);
       var teachclassid = ('${teachclassid}' != null? '${teachclassid}':null);
		$('#example').popover('show');
</script>
<script type="text/javascript" src="<%=path%>/js/jquery.1.10.2.js"></script>
<script type="text/javascript" src="<%=path%>/js/embed.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.3.0.0.js"></script>
<script type="text/javascript" src="<%=path%>/js/zanblog.2.0.7.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.icheck.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
<script type='text/javascript' src='<%=path%>/js/editStudentInfo.js'></script>
<script type="text/javascript">
</script>
<!-- +' 星期'+'日一二三四五六'.charAt(new Date().getDay())
<script type="text/javascript">
	setInterval(
			"showtime.innerHTML=new Date().toLocaleString();",
			1000);
</script>
-->
</head>
<body class="home blog" background="">
<c:choose>
<c:when test="${USER_CONTEXT.role == 1}">
	<jsp:include page="adminPublicJsp.jsp" />
</c:when>
<c:when test="${USER_CONTEXT.role == 2}">
   <jsp:include page="TeacherPublicJsp.jsp" />  
</c:when>
<c:otherwise>
   <jsp:include page="StudentPublicJsp.jsp" />
</c:otherwise>
</c:choose>
	<!--  个人信息管理   -->
	<div id="zan-bodyer">
		<div class="container">
			<h4>
				<i class="fa fa-list-ul"></i> 当前位置 ：
				<nobr id="curLocation"></nobr>
			</h4>
			<section class="well">
			<div id="btn-css">
				<center>
					<form id="userForm" name="userForm" method="post" action="">
						<input type="hidden" id="userId" name="userId" value="${student.userId}"/>
						<input type="hidden" id="teachClassId" name="teachClassId" value="${teachclassid}"/>
						<table style="font-size: 15px;border: none;" cellpadding="5" cellspacing="5" >

							<h2>
								以下带<font style="color: red;"> * </font>号为必填项
							</h2>

							<tr>
								<td><font style="color: red;"> * </font>学&nbsp; 号 :</td>
								<td><input type="text" id="accountId" name="accountId" value="${student.accountId}"/></td>
								<td><div id="accounterror" style="color:red;display: none"></div></td>
							</tr>
							<tr>
								<td><font style="color: red;"> * </font>姓&nbsp; 名 :</td>
								<td><input type="text" id="userName" name="userName" value="${student.userName}" maxLength="15"/></td>
								<td><div id="nameerror" style="color:red;display: none"></div></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;行政班 :</td>
								<td> <select id="classSelect" name="classSelect" class="select" size="1">
    						     </select></td>
							    <td></td>
		                    </tr>
							<tr>
								<td>&nbsp;&nbsp;课程 :</td>
								<td> <select id="courseSelect" name="courseSelect" class="select" size="1" onchange="onCourseSelectChange(this);">
    						     </select></td>
							    <td></td>
		                    </tr>
		                    <tr>
								<td>&nbsp;&nbsp;教学班 :</td>
								<td> <select id="teachclassSelect" name="teachclassSelect" class="select" size="1">
    						     </select></td>
							    <td></td>
		                    </tr>	       
							<tr>
								<td></td>
								<td><text id="save_button" name="save_button"
										class="btn btn-primary"
										onclick="modifyStduentInfo()" type="submit">
										保存
									</text>&nbsp;&nbsp;&nbsp;
									<text id="cancel_button" name="cancel_button"
										class="btn btn-primary"
										onclick="cancel()" type="submit">
										取消
									</text>							
								</td>
							</tr>
						</table>
					</form>
				</center>
			</div>
			</section>
		</div>
	</div>
	<footer id="zan-footer"> Copyright © 2014 <a target="_blank"
		title="华南农业大学" href="">java小组</a> Just do it, <a target="_blank"
		title="华南农业大学信息学院" href="">华南农业大学信息学院</a>. All Rights Reserved.
	 </footer>
</body>
</html>
