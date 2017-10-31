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

#note {
	margin-top: 100px;
	font-size: 3em;
}

#note2 {
	margin-top: 60px;
	font-size: 3em;
}

#theForm {
	margin-top: 60px;
}

.container {
	margin-top: 50px;
}
</style>
</head>
<body>
	<jsp:include page="/fragment/member_page.jsp"></jsp:include>
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
				<h1 id="note">
					<span class="label label-warning">請收取簡訊並輸入驗證碼</span>
				</h1>
				<h1 id="note2">
					<span class="label label-warning">以便完成認證手續</span>
				</h1>
				<form method="post" action="../sms.do" id="theForm">
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

	<!-- /footer -->
	<footer class="navbar-fixed-bottom w3-black container-fluid text-center" >
		<div>
			<ul class="nav nav-pills w3-centered " style="display: flex;font-size:13px;justify-content: center;">
			  <li role="presentation"><a href="<%=request.getContextPath()%>/index.jsp">關於我們</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">服務條款</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_service_page.jsp">隱私權聲明</a></li>
			</ul>
		</div>
    	<span class="text-center"><p style="font-size:10px">赫米特開發團隊  Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
	</footer>
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		
	</script>
</body>
</html>