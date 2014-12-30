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
<title>修改个人信息</title>

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
		var slider = !window.jQuery ? window.setTimeout(
				timer_metaslider_411, 100) : !jQuery.isReady ? window
				.setTimeout(timer_metaslider_411, 100)
				: metaslider_411(window.jQuery);
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
<!--统计代码结束-->
<script type='text/javascript'>
		backTop = function(btnId) {
			var btn = document.getElementById(btnId);
			var d = document.documentElement;
			var b = document.body;
			window.onscroll = set;
			btn.onclick = function() {
				btn.style.display = "none";
				window.onscroll = null;
				this.timer = setInterval(
						function() {
							d.scrollTop -= Math
									.ceil((d.scrollTop + b.scrollTop) * 0.1);
							b.scrollTop -= Math
									.ceil((d.scrollTop + b.scrollTop) * 0.1);
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
<script type="text/javascript" src="<%=path%>/js/jquery.1.10.2.js"></script>
<script type="text/javascript" src="<%=path%>/js/embed.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.3.0.0.js"></script>
<script type="text/javascript" src="<%=path%>/js/zanblog.2.0.7.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.icheck.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
<script type='text/javascript' src='<%=path%>/js/editPersonalInfo.js'></script>
<script type="text/javascript">
var save = '<%=savemsg != null ? savemsg + "" : ""%>';
	if (save.trim().length > 0) {
		alert(save);
	}
</script>
<script type="text/javascript">
	setInterval("showtime.innerHTML='登录日期：'+new Date().toLocaleString();",1000);
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
			<section class="well">
			<div id="btn-css">
				<center>
					<jsp:include page="userInfoForm.jsp"></jsp:include>
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
