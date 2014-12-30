<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="mx" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica,
		sans-serif;
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
html>body td {
	font-size: 15px;
}
</style>

<style type="text/css">
.center {
	margin: 0 auto;
}

.left {
	float: right;
}
</style>

<script type="text/javascript">
	var semesterid = ('${semesterid}' != -1 ? '${semesterid}' : '0');
	function show() {
		//alert("AAA");
		document.getElementById('updatehomework').style.display = "";
	}

	function ajaxFileUpload() {

		$.ajaxFileUpload({
			url : '/Program/file/uploadfile?description='
					+ document.getElementById("description").value + "'",
			secureuri : false, //是否启用安全提交,默认为false 
			fileElementId : 'myBlogImage', //文件选择框的id属性
			dataType : 'text', //服务器返回的格式,可以是json或xml等
			success : function(data, status) { //服务器响应成功时的处理函数
				data = data.replace(/<pre.*?>/g, ''); //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
				data = data.replace(/<PRE.*?>/g, '');
				data = data.replace("<PRE>", '');
				data = data.replace("</PRE>", '');
				data = data.replace("<pre>", '');
				data = data.replace("</pre>", '');//本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]

				if (data.substring(0, 1) == 0) { //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
					alert("上传成功！");
				} else {
					alert("上传失败！");
				}
			},
			error : function(data, status, e) { //服务器响应失败时的处理函数
				alert("上传失败！请重试");
			}
		});
	}
</script>

<!-- 设置标题 -->
<title>查看课程</title>


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

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.1.10.2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/embed.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap.3.0.0.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/zanblog.2.0.7.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.icheck.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js//My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/editSemesterCourse.js"></script>


</head>
<body>
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
	<div class="container">
		<c:if test="${!empty success}">
			<font color="red"><c:out value="${success}" /></font>
		</c:if>
		<!-- Breadcrumb NavXT 4.4.0 -->
		<h4>
			<i class="fa fa-list-ul"></i>当前位置 ：添加课程
		</h4>
		<section class="well">
			<center>
				<table style="font-size: 15px;" cellpadding="5" cellspacing="5">
					<tr>
						<td><input type="hidden" id="semestercourseid"
							name="semestercourseid"
							value="${semestercourse.semesterCourseId}" /></td>
					</tr>
					<h2>
						以下带<font style="color: red;"> * </font>号为必填项
					</h2>
					<tr>
						<td><font style="color: red;"> * </font>课程编号：</td>
						<td><input type="text" id="courseNo" name="courseNo"
							value="${semestercourse.course.courseNo}" maxLength="15" />
						<td><div id="coursenoerror" style="color: red; display: none"></div></td>
						</td>
					</tr>
					<tr>
						<td><font style="color: red;"> * </font>课程名称：</td>
						<td><input type="text" id="courseName" name="courseName"
							value="${semestercourse.course.courseName}" maxLength="15" /></td>
						<td><div id="coursenameerror"
								style="color: red; display: none"></div></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;学年学期：</td>
						<td><select id="semester" name="semester">
						</select></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;课程类型：</td>
						<td><select id="coursetype" name="coursetype">
								<c:choose>
									<c:when test="${semestercourse.course.courseType == 1}">
										<option value='1' selected="selected">选修课</option>
										<option value='2'>必修课</option>
									</c:when>
									<c:otherwise>
										<option value='1'>选修课</option>
										<option value='2' selected="selected">必修课</option>
									</c:otherwise>
								</c:choose>
						</select></td>
					</tr>
					<tr>
						<td><font style="color: red;"> * </font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学分：</td>
						<td><input type="text" id="credit" name="credit"
							value="${semestercourse.course.credit}" maxLength="15" /></td>
						<td><div id="crediterror" style="color: red; display: none"></div></td>
					</tr>
					<tr>
						<td></td>
						<td><text id="save_button" name="save_button"
								class="btn btn-primary" onclick="modifySemesterCourseInfo()"
								type="submit"> 保存 </text>&nbsp;&nbsp; <text id="cancel_button"
								name="cancel_button" class="btn btn-primary" onclick="cancel()"
								type="submit"> 取消 </text></td>
					</tr>
				</table>
			</center>
		</section>
	</div>
</body>
</html>