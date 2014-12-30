<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<!--      导航      -->
	<header id="zan-header" class="navbar navbar-inverse bs-docs-nav">
	<nav class="container">
	<div class="navbar-collapse bs-navbar-collapse collapse">
		<ul id="menu-navbar" class="nav navbar-nav">
			<li id="menu-item-215"
				class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home menu-item-215"><a
				title="Admin首页"
				href="/FinalDesign/userinfo/toFirst?type=2"><i
					class="fa fa-home"></i> 首页</a></li>
			<li id="menu-item-676"
				class="dropdown menu-item menu-item-type-custom menu-item-object-custom menu-item-676"><a><i
					class="fa fa-book"></i> 课程管理</a>
				<ul class="dropdown-menu">
					<li id="menu-item-678"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-678"><a
						 href="/FinalDesign/course/getSemesterClassByUserId"><i
							class=" fa fa-briefcase"></i> 年度课程管理</a></li>
				</ul></li>
			<li id="menu-item-604"
				class="dropdown menu-item menu-item-type-custom menu-item-object-custom menu-item-604"><a
				title="相关下载" rel="nofollow"><i class="fa fa-file-o"></i> 作业管理</a>
				<ul class="dropdown-menu">
					<li id="menu-item-214"
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-214"><a
						title="个人题库管理" rel="nofollow"
						href="/FinalDesign/item/findPersonalItemsByUser"><i
							class="fa fa-truck"></i> 个人题库管理</a></li>
					<li id="menu-item-214"
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-214"><a
						title="默认作业" rel="nofollow"
						href="/FinalDesign/item/showDefaultItems"><i
							class="fa fa-eye"></i> 查看默认作业</a></li>
					<li id="menu-item-214"
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-214"><a
						title="班级作业布置" rel="nofollow"
						href="http://www.yeahzan.com/zanblog/download"><i
							class="fa fa-files-o"></i> 班级作业布置</a></li>
				    <li id="menu-item-214"
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-214"><a
						title="查看作业情况" rel="nofollow"
						href="http://www.yeahzan.com/zanblog/download"><i
							class="fa fa-files-o"></i> 查看作业情况</a></li>
					<li id="menu-item-214"
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-214"><a
						title="作业批改" rel="nofollow"
						href="http://www.yeahzan.com/zanblog/download"><i
							class="fa fa-pencil"></i> 作业批改</a></li>
					<li id="menu-item-214"
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-214"><a
						title="成绩管理" rel="nofollow"
						href="http://www.yeahzan.com/zanblog/download"><i
							class="fa fa-flag"></i> 成绩管理</a></li>
				</ul></li>
			<li id="menu-item-676"
				class="dropdown menu-item menu-item-type-custom menu-item-object-custom menu-item-676"><a><i
					class="fa fa-cog"></i>信息维护</a>
				<ul class="dropdown-menu">
					<li id="menu-item-677"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-677"><a
						 href="/FinalDesign/classinfo/courseClassMan"><i
							class=" fa fa-book"></i> 课程班级管理</a></li>
					<li id="menu-item-678"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-678"><a
						 href="/FinalDesign/userinfo/getStudentInfo"><i
							class=" fa fa-user"></i> 学生信息管理</a></li>
					<li id="menu-item-681"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-681"><a
						 href="/FinalDesign/userinfo/toEditPersonalInfo"><i
							class=" fa fa-search"></i>修改个人信息</a></li>
				</ul></li>
			<li id="menu-item-675"
				class="dropdown menu-item menu-item-type-custom menu-item-object-custom menu-item-675"><a><i
					class="fa fa-question-circle"></i>其他</a>
				<ul class="dropdown-menu">
					<li id="menu-item-213"
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-213"><a
						title="备份作业" rel="nofollow"
						href="http://www.yeahzan.com/zanblog/message"><i
							class="fa fa-pencil"></i> 备份作业</a></li>
					<li id="menu-item-683"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-683"><a
						title="教学文档"
						href="http://www.yeahzan.com/zanblog/archives/457.html"><i
							class="fa fa-cloud-download"></i>教学文档</a></li>
				</ul></li>
			<li id="menu-item-294"
				class="dropdown menu-item menu-item-type-custom menu-item-object-custom menu-item-294"><a
				title="退出登录" rel="nofollow" href="/FinalDesign/loginout"><i
					class="fa fa-sign-out"></i> 注销</a></li>
			<li><a title="登陆日期" id="showtime" rel="nofollow">登陆日期：</a></li>
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
	</nav>
	<div id="if-fixed" class="pull-right visible-lg visible-md">
		<i class="fa fa-thumb-tack"></i> <input type="checkbox">
	</div>
	</header>
	<!--头部      -->
	<div id="wrap">
		<div class="jumbotron jumbotron-ad hidden-print"
			style="background-image: url('<%=request.getContextPath()%>/image/body_head.jpg'); no-repeat fixed top left; background-size:100% 100%;">
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