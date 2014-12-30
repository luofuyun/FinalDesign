<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String savemsg = (String) request.getAttribute("savemsg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type='text/javascript' src='<%=path%>/js/admin.js'></script>
</head>
<body>
	<!--      导航      -->
	<header id="zan-header" class="navbar navbar-inverse bs-docs-nav">
	<div class="container">
	<div class="navbar-collapse bs-navbar-collapse collapse">
		<ul id="menu-navbar" class="nav navbar-nav">
			<li id="menu-item-215"
				class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home menu-item-215"><a
				title="Admin首页"
				href="/FinalDesign/userinfo/toFirst?type=1" onclick="returnToFirst()"><i
					class="fa fa-home"></i> 首页</a></li>
			<li id="menu-item-676"
				class="dropdown menu-item menu-item-type-custom menu-item-object-custom menu-item-676"><a><i
					class="fa fa-cog"></i> 信息维护</a>
				<ul class="dropdown-menu">
					<li id="menu-item-677"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-677"><a
						 href="/FinalDesign/userinfo/toEditPersonalInfo"><i
							class=" fa fa-pencil-square-o"></i> 修改个人信息</a></li>
					<li id="menu-item-678"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-678"><a
						href="/FinalDesign/userinfo/getAllUser"
						onclick="showCurLocationName('信息维护  >> 用户管理')"><i
							class=" fa fa-user"></i> 用户管理</a></li>
					<li id="menu-item-681"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-681"><a
						target="_blank" onclick="showCurLocationName('信息维护  >> 关键字查询用户')"><i
							class=" fa fa-search"></i> 关键字查询用户</a></li>
				</ul></li>
			<li id="menu-item-604"
				class="dropdown menu-item menu-item-type-custom menu-item-object-custom menu-item-604"><a
				title="相关下载" rel="nofollow"><i class=" fa fa-download"></i> 课程管理</a>
				<ul class="dropdown-menu">

					<li id="menu-item-605"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-605"><a
						rel="nofollow" href="/FinalDesign/course/getAllCourse"
						onclick="showCurLocationName('课程管理  >> 课程项目管理')"><i
							class="fa fa-wrench"></i> 课程信息管理</a></li>
					<li id="menu-item-546"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-546"><a
						title="ZanUI" onclick="showCurLocationName('课程管理  >> 年度课程管理')"><i
							class="fa fa-briefcase"></i> 年度课程管理</a></li>
					<li id="menu-item-683"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-683"><a
						onclick="showCurLocationName('课程管理  >> 教学文档管理')"><i
							class="fa fa-cloud-download"></i> 教学文档管理</a></li>
				</ul></li>
			<li id="menu-item-604"
				class="dropdown menu-item menu-item-type-custom menu-item-object-custom menu-item-604"><a
				title="相关下载" rel="nofollow"><i class=" fa fa-download"></i> 题库管理</a>
				<ul class="dropdown-menu">
					<li id="menu-item-214"
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-214"><a
						title="主题下载" rel="nofollow"
						onclick="showCurLocationName('题库管理  >> 全局题库管理')"><i
							class=" fa fa-book"></i> 全局题库管理</a></li>
					<li id="menu-item-605"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-605"><a
						target="_blank" rel="nofollow"
						onclick="showCurLocationName('题库管理  >> 课程默认作业')"><i
							class="fa fa-wrench"></i> 课程默认作业</a></li>

				</ul></li>
			<li id="menu-item-556"
				class="dropdown menu-item menu-item-type-post_type menu-item-object-page menu-item-556"><a
				href="http://www.yeahzan.com/zanblog/style"><i
					class="fa fa-envelope-o"></i> 消息管理</a>
				<ul class="dropdown-menu">
					<li id="menu-item-557"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-557"><a
						href="/FinalDesign/announceinfo/getAllInfo"
						onclick="showCurLocationName('消息管理  >> 公告管理')"><i
							class="fa fa-bullhorn"></i> 公告管理</a></li>
					<li id="menu-item-558"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-558"><a
						onclick="showCurLocationName('消息管理  >> 意见反馈')"><i
							class="fa fa-edit"></i> 意见反馈</a></li>
				</ul></li>
			<li id="menu-item-675"
				class="dropdown menu-item menu-item-type-custom menu-item-object-custom menu-item-675"><a><i
					class="fa fa-question-circle"></i> 其他</a>
				<ul class="dropdown-menu">
					<li id="menu-item-213"
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-213"><a
						title="问题反馈" rel="nofollow"
						onclick="showCurLocationName('其 他  >> 作业备份')"><i
							class="fa fa-pencil"></i> 作业备份</a></li>
				</ul></li>


			<li id="menu-item-294"
				class="dropdown menu-item menu-item-type-custom menu-item-object-custom menu-item-294"><a
				title="退出登录" rel="nofollow" href="/FinalDesign/loginout"><i class="fa fa-sign-out"></i> 注销</a>

			</li>
			<li><a title="登陆日期" id="showtime" rel="nofollow"></a></li>
		</ul>

		<div class="search visible-lg">
			<div class="textwidget"></div>
		</div>
		<div class="search visible-lg">
			<form method="get" id="searchform" class="form-inline"
				action="http://www.yeahzan.com/zanblog">
				<input class="form-control" type="text" name="s" id="s"
					placeholder="搜索..." />
				<button type="submit" class="btn btn-danger btn-small" name="搜索">
					<i class="fa fa-search"></i>
				</button>
			</form>
		</div>
	</div>
	</div>
	<div id="if-fixed" class="pull-right visible-lg visible-md">
		<i class="fa fa-thumb-tack"></i> <input type="checkbox" value="checked">
	</div>
	</header>
	<!-- 头部      -->
	<div id="wrap">
		<div class="jumbotron jumbotron-ad hidden-print"
			style="background-image: url('<%=request.getContextPath()%>/image/body_head.jpg')">
			<div class="container">
				<h2>
					<i class="fa fa-list-ul"></i>&nbsp; 欢迎您, ${USER_CONTEXT.userName}
				</h2>
					<div class="bs-example" style="padding-bottom: 24px;">
					<a href="#" class="btn btn-warning" data-toggle="popover"
						title="提示" data-content="右上方打钩之后可以使导航条固定哦！">温馨提示</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>