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

#note {
	margin-top: 100px;
	font-size: 5em;
}

#countdown {
	margin-top: 50px;
}

.success-countdown {
	text-align: center;
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
				<span class="label label-success">3.註冊完成</span>
			</div>
			<div class="progress">
				<div
					class="progress-bar progress-bar-success progress-bar-striped active"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 100%">100%</div>
			</div>
			<div class="success-countdown">
				<h1 id="note">
					<span class="label label-success">恭喜您!!已完成註冊手續</span>
				</h1>
				<div id="countdown"></div>
				<a href="<%=request.getContextPath()%>/index.jsp" style="color: #00AA55;">8秒後將自動跳轉，或是點此連結直接前往首頁</a>
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
		</div>
	</footer>
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/jquery.countdown360.min.js"></script>
	<script>
		$("#countdown").countdown360({
			radius : 100,
			seconds : 8,
			fontColor : '#FFFFFF',
			autostart : false,
			onComplete : function() {
				location.href = "<%=request.getContextPath()%>/index.jsp";
			}
		}).start()
	</script>
</body>
</html>