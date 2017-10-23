<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊頁面</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.progress-text {
	margin-bottom: 10px;
}

.notice-confirm {
	text-align: center;
}

.notice-confirm div {
	margin-left: auto;
	margin-right: auto;
}

.error-data-style {
	color: red;
	padding-left: 1em;
	margin-bottom: 1em;
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
	</nav></header>
	<div class="container">
		<div class="row">
			<div class="progress-text">
				<span class="label label-warning">2.收取認證通知</span>
			</div>
			<div class="progress">
				<div
					class="progress-bar progress-bar-warning progress-bar-striped active"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 66%">66%</div>
			</div>
			<div class="notice-confirm">
				<h1>
					<span class="label label-warning">請收取簡訊並輸入驗證碼</span>
				</h1>
				<h1>
					<span class="label label-warning">以便完成認證手續</span>
				</h1>
				<form method="post" action="../sms.do">
					<fieldset>
						<legend></legend>
						<div>
							<label for="code"> 驗證碼&nbsp;:&nbsp;</label> <input type="text"
								id="code" name="code" autofocus /><span> <input
								type="submit" value="送出" />
							</span>
						</div>
						<div class="error-data-style">
							<small>${ErrorMsgKey.CodeEmptyError}</small><small>${ErrorMsgKey.CodeFormatError}</small>
						</div>
						<input type="hidden" name="action" value="register_SMS_Action">
					</fieldset>
				</form>
				<form method="post" action="sms.do">
					<div>
						<input type="submit" value="重寄" />
					</div>
					<input type="hidden" name="action" value="register_reSMS_Action">
				</form>
			</div>
		</div>
	</div>
	<footer class="footer"> <small>&copy; IIIEDU.GARYHSU</small> </footer>
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		
	</script>
</body>
</html>