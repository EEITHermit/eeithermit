<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>Home Work</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<style>
.mid {
	height: 500px;
	text-align: center;
}
</style>
</head>
<body>
	<div class="glyphicon glyphicon-plane"></div>
	<!-- Single button -->
	<div class="btn-group">
		<button type="button" class="btn btn-info dropdown-toggle"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			Action <span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=request.getContextPath()%>/Member/index.jsp">首頁</a>
				<li><a href="<%=request.getContextPath()%>/MemberServlet?member=40001">會員管理</a></li>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					
							<li><a href="login.jsp">登入</a></li>
							<li><a href="register.jsp">註冊</a></li> 
							<!-- member=40001是假資料，等統整時要拿掉 -->
							<li><a href="<%=request.getContextPath()%>/MemberServlet?member=40001">會員管理</a></li> 
							<li><a href="#">登出</a></li>
						</ul>
			</div>
		</div>
	</nav>
	<div class="row">
		<div class="col-md-4 mid"></div>
		<div class="col-md-4 mid">
			<h2>Hello Welcome</h2>
			${Msg}
		</div>
		<div class="col-md-4 mid"></div>
	</div>
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>

</body>

</html>