<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mx" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<style type="text/css">
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
	width: 1140px;
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
<style type="text/css" id="syntaxhighlighteranchor"></style>
<title>教师主界面</title>
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
<link rel='stylesheet' id='custom-style-css'
	href='css/techManageStudent.css' type='text/css' media='all' />
<script type="text/javascript">
	var duoshuoQuery = {
		"short_name" : "yeahzan",
		"sso" : {
			"login" : "http:\/\/www.yeahzan.com\/zanblog\/wp-login.php?action=duoshuo_login",
			"logout" : "http:\/\/www.yeahzan.com\/zanblog\/wp-login.php?action=logout&_wpnonce=bc6e754532"
		},
		"theme" : "default",
		"stylePatch" : "wordpress\/Zanblog"
	};
	duoshuoQuery.sso.login += '&redirect_to='
			+ encodeURIComponent(window.location.href);
	duoshuoQuery.sso.logout += '&redirect_to='
			+ encodeURIComponent(window.location.href);
</script>
<script type="text/javascript" src="http://static.duoshuo.com/embed.js"
	charset="UTF-8" async="async"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.1.10.2.js"></script>
<script type="text/javascript" src="<%=path%>/js/embed.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.3.0.0.js"></script>
<script type="text/javascript" src="<%=path%>/js/zanblog.2.0.7.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.icheck.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=path%>/js/courseClassMan.js"></script>
<script type="text/javascript">
	
</script>
<script type="text/javascript">
	var courseType = ('${courseid}' != -1 ? '${courseid}' : '0');
</script>
<script src="http://v3.bootcss.com/dist/js/bootstrap.js"></script>
<script src="http://cdn.bootcss.com/holder/2.0/holder.min.js"></script>
<script src="http://cdn.bootcss.com/highlight.js/7.3/highlight.min.js"></script>
<script>
	hljs.initHighlightingOnLoad();
</script>
<script src="http://v3.bootcss.com/docs-assets/js/application.js"></script>
<script type="text/javascript">
	var metaslider_411 = function($) {
		$('#metaslider_411').flexslider({
			slideshowSpeed : 3000,
			animation : "slide",
			controlNav : false,
			directionNav : true,
			pauseOnHover : true,
			direction : "vertical",
			reverse : false,
			animationSpeed : 600,
			prevText : "<",
                nextText:">",
			easing : "easeOutExpo",
			slideshow : true,
			useCSS : false
		});
	};
	var timer_metaslider_411 = function() {
		var slider = !window.jQuery ? window.setTimeout(timer_metaslider_411,
				100) : !jQuery.isReady ? window.setTimeout(
				timer_metaslider_411, 100) : metaslider_411(window.jQuery);
	};
	timer_metaslider_411();
</script>
<script type="text/javascript">
	var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
			: " http://");
	document
			.write(unescape("%3Cscript src='"
					+ _bdhmProtocol
					+ "hm.baidu.com/h.js%3Fb8510898b22f723bada9539bb4177030' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type='text/javascript'>
	backTop = function(btnId) {
		var btn = document.getElementById(btnId);
		var d = document.documentElement;
		var b = document.body;
		window.onscroll = set;
		btn.onclick = function() {
			btn.style.display = "none";
			window.onscroll = null;
			this.timer = setInterval(function() {
				d.scrollTop -= Math.ceil((d.scrollTop + b.scrollTop) * 0.1);
				b.scrollTop -= Math.ceil((d.scrollTop + b.scrollTop) * 0.1);
				if ((d.scrollTop + b.scrollTop) == 0)
					clearInterval(btn.timer, window.onscroll = set);
			}, 10);
		};
		function set() {
			btn.style.display = (d.scrollTop + b.scrollTop > 100) ? 'block'
					: "none"
		}
	};
	backTop('gotop');
</script>
<script type='text/javascript'>
	/*           */
	var infinite_scroll = "{\"loading\":{\"msgText\":\"\",\"finishedMsg\":\"<p class=\\\"alert alert-danger\\\"><i class=\\\"fa fa-warning\\\"><\\\/i> \\u5185\\u5bb9\\u52a0\\u8f7d\\u5b8c\\u6bd5<\\\/p>\",\"img\":\"http:\\\/\\\/localhost\\\/zanblog\\\/wp-content\\\/plugins\\\/infinite-scroll\\\/img\\\/ajax-loader.gif\"},\"nextSelector\":\"#load-more\",\"navSelector\":\"#load-more\",\"itemSelector\":\".article\",\"contentSelector\":\"#mainstay\",\"debug\":false,\"behavior\":\"twitter\",\"callback\":\"jQuery(\\\"#load-more\\\").removeClass(\\\"disabled\\\");\\r\\njQuery(\\\"#load-more i\\\").removeClass(\\\"fa fa-spinner\\\");\\r\\njQuery(\\\"#load-more attr\\\").text(\\\"\\u52a0\\u8f7d\\u66f4\\u591a\\\");\",\"theme\":\"zanblog\"}";
	/*     */
</script>
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
	$('#example').popover('show');
</script>
</head>
<body class="home blog">
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
			<section class="well" style="padding:0;padding-top:7px;">
			<div id="btn-css">
				<!--  用户管理表格 -->
				<div>
					<div id="submits" class="ui-datatable ui-widget">
						<div class="ui-datatable-tablewrapper">
							<div
								style="margin-top: 10px; margin-bottom: 10px; margin-left: 5px;">
								开设课程 : &nbsp;<select name="j_idt44" size="1"
									id="courseforteacher" style="width: 40"
									onchange="onCourseSelectChange(this);">
								</select>&nbsp;&nbsp;&nbsp;&nbsp;

								<button class="btn btn-primary" onclick="addClass('')">添加新班级</button>


							</div>
							<table id="mytable" cellspacing="0"
								summary="The technical specifications of the Apple PowerMac G5 series">
								<tr align="center">
									<th scope="col" abbr="Configurations"><input
										type="checkbox" id="selectUser" onclick="switchSelectBox()" /></th>
									<th scope="col" abbr="Dual 1.8" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级名称</th>
									<th scope="col" abbr="Dual 1.8">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级类型</th>

									<th scope="col" abbr="Dual 2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操&nbsp;&nbsp;作</th>
								</tr>
								<c:forEach var="tempclass" items="${classList}">
									<tr align="center">
										<th scope="row" abbr="Model" class="spec"><input
											type="checkbox" id="selectUser" name="selectUserCk"
											value="${tempclass.className}" /></th>
										<td>${tempclass.className}</td>

										<td><c:if test="${tempclass.type==1}">教学班</c:if><c:if test="${tempclass.type==2}">行政班</c:if></td>

										<td>
											<button class="btn btn-primary"
												onclick="addClass('${tempclass.classId}')">修改</button>
											<button class="btn btn-primary"
												onclick="deClass('${tempclass.classId}')">删除</button>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<div
					style="width: 50%; margin-left: 30px; margin-left: 30px; margin-top: 10px; margin-bottom: 8px; font-size: 1.2em;"
					class="center">
					<mx:PageBar pageUrl="${pageUrl}" pageAttrKey="${pageAttrKey}" />
				</div>
			</section>
		</div>
	</div>
	<footer id="zan-footer"> Copyright © 2014 <a target="_blank"
		title="华南农业大学" href="">java小组</a> Just do it, <a target="_blank"
		title="华南农业大学信息学院" href="">华南农业大学信息学院</a>. All Rights Reserved. <!--统计代码开始-->
	</footer>
	<div style="display: none;" class="fa fa-angle-up" id="gotop"></div>
</body>
</html>
