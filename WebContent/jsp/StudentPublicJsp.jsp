<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!-- 导航      -->
	<header id="zan-header" class="navbar navbar-inverse bs-docs-nav">
	<nav class="container">
	<div class="navbar-collapse bs-navbar-collapse collapse">
		<ul id="menu-navbar" class="nav navbar-nav">
			<li id="menu-item-215"
				class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home menu-item-215"><a
				title="Admin首页"
				href="/FinalDesign/userinfo/toFirst?type=3"><i
					class="fa fa-home"></i> 首页</a></li>
			<li id="menu-item-676"
				class="dropdown menu-item menu-item-type-custom menu-item-object-custom menu-item-676"><a><i
					class="fa fa-book"></i> 我的作业</a>
				<ul class="dropdown-menu">
					<li id="menu-item-677"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-677"><a
						href="/FinalDesign/item/studentShowArrangeById?courseid=0&option=0"><i
							class=" fa fa-pencil-square-o"></i> 查看作业情况</a></li>
					<li id="menu-item-681"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-681"><a
						href="http://www.yeahzan.com/blog/item/42-58.html"><i
							class=" fa fa-download"></i> 教学文档下载</a></li>
				</ul></li>
			<li id="menu-item-604"
				class="dropdown menu-item menu-item-type-custom menu-item-object-custom menu-item-604"><a
				title=" 信息维护" rel="nofollow"><i class=" fa fa-cog"></i> 信息维护</a>
				<ul class="dropdown-menu">
					<li id="menu-item-214"
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-214"><a
						title="个人信息管理" rel="nofollow" href="/FinalDesign/userinfo/toEditPersonalInfo"><i
							class=" fa fa-edit"></i> 修改个人信息</a></li>
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
			<form method="get" id="searchform" class="form-inline" action="">
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

	<!--      头部      -->
	<div id="wrap">
		<div class="jumbotron jumbotron-ad hidden-print"
			style="background-image: url('<%=request.getContextPath()%>/image/body_head.jpg'); no-repeat fixed top left; background-size:100% 100%;">
			<div class="container">
				<h2>
					<i class="fa fa-list-ul"></i>&nbsp; 欢迎您，, ${USER_CONTEXT.userName}
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