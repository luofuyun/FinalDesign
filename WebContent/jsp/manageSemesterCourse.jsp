<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <% String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %> 
<%@ taglib prefix="mx" tagdir="/WEB-INF/tags" %>  
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
    font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    color: #4f6b72; 
    background: #E6EAE9; 
} 

a { 
    color: #c75f3e; 
} 

#mytable { 
    width: 1120px; 
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
    font-size:15px; 
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
html>body td{ font-size:15px;} 
</style> 

<style type="text/css">
    .center{
    margin: 0 auto;
}
    .left{
    float:right;
    }
</style>

<script type="text/javascript">

function show(){
	//alert("AAA");
	document.getElementById('updatehomework').style.display = ""; 
}

function ajaxFileUpload(){
	
	$.ajaxFileUpload({
		url:'/Program/file/uploadfile?description='+document.getElementById( "description" ).value+"'",
		secureuri:false,                       //是否启用安全提交,默认为false 
		fileElementId:'myBlogImage',           //文件选择框的id属性
		dataType:'text',                       //服务器返回的格式,可以是json或xml等
		success:function(data, status){        //服务器响应成功时的处理函数
			data = data.replace(/<pre.*?>/g, '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
			data = data.replace(/<PRE.*?>/g, ''); 
			 data = data.replace("<PRE>", '');
			 data = data.replace("</PRE>", '');  
			 data = data.replace("<pre>", '');  
			 data = data.replace("</pre>", '');//本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
		
			if(data.substring(0, 1) == 0){     //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
				alert("上传成功！");
			}else{
				alert("上传失败！");
			}
		},
		error:function(data, status, e){ //服务器响应失败时的处理函数
			alert("上传失败！请重试");
		}
	});
}

</script>  

<!-- 设置标题 -->
<title>教师主界面</title> 


<link rel='stylesheet' id='zanblog-style-css'  href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/style.css?ver=2.0.7' type='text/css' media='all' />
<link rel='stylesheet' id='bootstrap-style-css'  href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/ui/css/bootstrap.css?ver=3.0.0' type='text/css' media='all' />
<link rel='stylesheet' id='fontawesome-style-css'  href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/ui/font-awesome/css/font-awesome.min.css?ver=4.0.1' type='text/css' media='all' />
<link rel='stylesheet' id='icheck-style-css'  href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/ui/css/flat/red.css?ver=3.6' type='text/css' media='all' />
<script type="text/javascript" src="http://static.duoshuo.com/embed.js"
	charset="UTF-8" async="async"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.1.10.2.js"></script> 
<script type="text/javascript" src="<%=path%>/js/embed.js"></script> 
<script type="text/javascript" src="<%=path%>/js/bootstrap.3.0.0.js"></script>   
<script type="text/javascript" src="<%=path%>/js/zanblog.2.0.7.js"></script>   
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.icheck.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>     
<script type="text/javascript" src="../js/manageSemesterCourse.js"></script>
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
		<div id="showAllCourse">
		<h4><i class="fa fa-list-ul"></i>当前位置 ：年度课程管理</h4>
		<section class="well">
				<input type="hidden" name="conditionForm" value="conditionForm" />   
                <!-- 
                <div class="search">
			        关键字 :<input type="text" name="searchString" value=""></input>
					<button class="btn btn-primary" type="submit" >查询</button>
					 </div>
					  -->
					<label class="btn btn-primary" onclick="refresh()">刷新</label>  
					<label class="btn btn-primary" onclick="toEditSemesterCourse('')">添加年度课程</label>           
                <div>
					<div id="submits" class="ui-datatable ui-widget">
					<div class="ui-datatable-tablewrapper" style="margin-top: 5px;">
						<table id="mytable" > 
							<tr align="center"> 
								<th scope="col" abbr="Configurations" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp学年度</th> 
								<th scope="col" abbr="Dual 1.0">&nbsp;&nbsp;&nbsp;&nbsp;学期</th>
								<th scope="col" abbr="Dual 1.8">&nbsp;&nbsp;&nbsp;&nbsp;课程编号</th> 
								<th scope="col" abbr="Dual 2">&nbsp;&nbsp;&nbsp;&nbsp;课程名称</th> 
								<th scope="col" abbr="Dual 1.8">&nbsp;&nbsp;&nbsp;&nbsp;学分</th> 
								<th scope="col" abbr="Dual 2">&nbsp;&nbsp;&nbsp;&nbsp;状态</th> 
								<th scope="col" abbr="Dual 2">&nbsp;&nbsp;&nbsp;&nbsp;操作</th> 
							</tr>
							<c:forEach var="tempCourse" items="${courseList}" varStatus="tempStatus">
								<tr align="center">
									<th scope="row" abbr="Model" class="spec">${tempCourse.semester.term}</th>
									<td>${tempCourse.semester.semesterTime}</td>
									<td>${tempCourse.course.courseNo}</td>
									<td>${tempCourse.course.courseName}</td>
									<td>${tempCourse.course.credit}</td>
									<input type="hidden" id="${tempCourse.semesterCourseId}statuvalue" value="${tempCourse.isClose}"/>
									<td id="${tempCourse.semesterCourseId}status">
									<c:choose>
									<c:when test="${tempCourse.isClose == 1}">
									已关闭
									</c:when>
									<c:otherwise>
									已启用
									</c:otherwise>
									</c:choose>
									</td>
									<td align="left">
									   <button class="btn btn-primary" onclick="toEditSemesterCourse('${tempCourse.semesterCourseId}')">修改</button>
						               <button class="btn btn-primary" id="${tempCourse.semesterCourseId}openOrClose" onclick="openOrCloseSemesterCourse('${tempCourse.course.courseName}','${tempCourse.semesterCourseId}','${tempCourse.isClose}')">
						              <c:choose>
						               <c:when test="${tempCourse.isClose == 1}">                             
						                                             启用</c:when>
						                <c:otherwise>
						                                                 关闭
						                </c:otherwise>
						                </c:choose> 
						               </button>
									</td> 
								 </tr>
							</c:forEach>
						</table>
					</div>
					</div>
				</div>
			<div style="width:50%; margin-left: 30px;margin-left: 30px;margin-top:10px;margin-bottom: 8px;font-size:1.2em;" class="center" >
				<mx:PageBar pageUrl="${pageUrl}" pageAttrKey="${pageAttrKey}"/>
			</div>
		</section>
		</div>
	</div>
</body>
</html>