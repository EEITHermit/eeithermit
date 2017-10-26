<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>後台管理頁面</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href="https://code.jquery.com/ui/1.12.0/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/eeit-rainbow.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/back_side.css">
</head>
<style>
	body{
/* 		background-color: #AAFFEE; */
	}
	.main{
/* 		background-color:#AAFFEE; */
	}
</style>
<body class="bg-danger">
	<div>
	<aside>
	<div class="navBtn">+</div>
	<nav> <a href="<%=request.getContextPath()%>/mention/mentionIndex.jsp">首頁</a>
	<a href="">房屋產品維護</a> 
	<a href="">房屋租賃維護</a> 
	<a href="">合約租賃管理</a> 
	<a href="<%=request.getContextPath()%>/calendar/calendar.jsp">預約行程管理</a> 
	<a href="">廣告輪播管理</a> 
	<a href="">派工回報系統</a> 
	<a href="">會員留言管理</a> 
	<a href="<%=request.getContextPath()%>/management/manage_member_page.jsp">會員資料管理</a>
	<a href="">員工資料管理</a> 
	<a href="<%=request.getContextPath()%>/infraction/infraction.jsp">黑名單申請</a> </nav> </aside>

	<div class="main">
		<!-- do it !!!! -->
		<header> <nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#myNavbar" aria-expanded="ture">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-home"></span>
							浩天登入</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
							浩天登出</a></li>
				</ul>
			</div>
		</div>
		</nav></header>
		<p class="text-rainbow">Hermit</p>
	</div>

	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/back_side.js"></script>
	</div>
</body>
</html>