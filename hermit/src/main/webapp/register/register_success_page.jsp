<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊頁面</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<style>
.progress-text {
	margin-bottom: 10px;
}

.success-countdown {
	text-align: center;
}
</style>
</head>
<body>
	<header> <nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#myNavbar" aria-expanded="ture">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Hermit</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span>
						Home</a></li>
			</ul>
		</div>
	</div>
	</nav> </header>
	<div class="container">
		<div class="row">
			<div class="progress-text">
				<span class="label label-success">3.註冊完成</span>
			</div>
			<div class="progress">
				<div
					class="progress-bar progress-bar-success progress-bar-striped active"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 100%">100%</div>
			</div>
			<div class="success-countdown">
				<h1>
					<span class="label label-success">恭喜您!!已完成註冊手續</span>
				</h1>
				<div id="countdown"></div>
				<a href="register_page.jsp">8秒後將自動跳轉，或是點此連結直接前往首頁</a>
			</div>
		</div>
	</div>
	<footer class="footer"> <small>&copy; IIIEDU.GARYHSU</small> </footer>
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/jquery.countdown360.min.js"></script>
	<script>
		$("#countdown").countdown360({
			radius : 100,
			seconds : 8,
			fontColor : '#FFFFFF',
			autostart : false,
			onComplete : function() {
				location.href = "register_page.jsp";
			}
		}).start()
	</script>
</body>
</html>