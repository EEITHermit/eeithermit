<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberBackIndex</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<link
	href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/font-awesome.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/adminia.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/adminia-responsive.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/pages/dashboard.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="/fragment/member_page.jsp"></jsp:include>
	<style>
a:link, a:visited, a:hover, a:active {
	text-align: left;
}

.panel panel-primary{
	border-color: #000000;
}
.panel-heading{
	background:#000000;
}
</style>
	<div id="content">
		<div class="container">
			<div class="row">
				<div class="">
					<div class="account-container" style="margin: auto;">
						<div class="account-avatar">
							<img src="${LoginOK.memImage}" alt="memImage" class="thumbnail" />
							<p class="account-name"
								style="font-family: Microsoft JhengHei; text-align: center; font-size: 24px">${LoginOK.memName}<br>您好
							</p>
						</div>
					</div>
				</div>
				<!-- /span3 -->
			</div>

			<div class="row" style="padding-top: 5%">
				<div class="col-md-3">
					<div class="panel panel-primary" style="border-color: #000000">
						<div class="panel-heading" style="background:#000000">
							<i class="glyphicon glyphicon-heart" style="font-size: 24px"></i><a
								href="<%=request.getContextPath()%>/memberbackstage/mem_back_favorite.jsp"
								style="font-size: 24px">收藏</a>
						</div>
						<div class="panel-body">Panel Content</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-calendar" style="font-size: 24px"></i><a
								href="<%=request.getContextPath()%>/memberbackstage/mem_back_calendar.jsp"
								style="font-size: 24px">預約</a>
						</div>
						<div class="panel-body">Panel Content</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-comment" style="font-size: 24px"></i><a
								href="<%=request.getContextPath()%>/memberbackstage/mem_back_qanda.jsp"
								style="font-size: 24px">問與答</a>
						</div>
						<div class="panel-body">Panel Content</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-file" style="font-size: 24px"></i><a
								href="<%=request.getContextPath()%>/memberbackstage/mem_back_lease.jsp"
								style="font-size: 24px">租賃紀錄</a>
						</div>
						<div class="panel-body">Panel Content</div>
					</div>
				</div>
			</div>


			<div class="">
				<!-- 這邊是放你的資料 -->
				<div>
					<div class="container"></div>
				</div>
				<!-- /widget-content -->
			</div>
			<!-- /widget -->
		</div>
		<!-- /span9 -->
	</div>
	<!-- /container -->


	<div id="footer"></div>
	<!-- /footer -->
	<footer
		class="navbar-fixed-bottom w3-black container-fluid text-center">
	<div>
		<ul class="nav nav-pills w3-centered "
			style="display: flex; font-size: 13px; justify-content: center;">
			<li role="presentation"><a
				href="<%=request.getContextPath()%>/index.jsp">關於我們</a></li>
			<li role="presentation"><a
				href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></li>
			<li role="presentation"><a
				href="<%=request.getContextPath()%>/register/law_service_page.jsp">服務條款</a></li>
			<li role="presentation"><a
				href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">隱私權聲明</a></li>
		</ul>
	</div>
	<span class="text-center"><p style="font-size: 10px">赫米特開發團隊
			Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
	</div>
	</footer>



	<!-- Le javascript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/excanvas.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
</body>
</html>