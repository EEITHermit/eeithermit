<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberBackCalendar</title>
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
	a:link, a:visited, a:hover ,a:active{
		color:white;
	    text-decoration: none;
	    padding: 14px 25px;
	    text-align: left;
	    text-decoration: none;
	    display: inline-block;
	}
	</style>
	<div id="content">

		<div class="container">

			<div class="row">

				<div class="span3">

					<div class="account-container">

						<div class="account-avatar">
							<img src="<%=request.getContextPath()%>/css/images/god.ico" alt="" class="thumbnail" />
						</div>
						<!-- /account-avatar -->


						<!-- /account-details -->
					</div>
					<div class="account-details">
						<span class="account-name"
							style="font-family: Microsoft JhengHei;">eeit9744 徐漢勳</span>
						<!-- 	<span class="account-name" style="font-family: Microsoft JhengHei">徐漢勳</span> -->
					</div>
					<!-- /account-container -->

					<hr />

					<ul id="main-nav" class="nav nav-tabs nav-stacked">
						<li><a href="./mem_back_index.jsp"> <i
								class="glyphicon glyphicon-home" style="height: 30px;"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">首頁</span>
						</a></li>

						<li><a href="./mem_back_favorite.jsp"> <i
								class="glyphicon glyphicon-heart" style="height: 30px;"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">收藏</span>
						</a></li>

						<li class="active"><a href="./mem_back_calendar.jsp"> <i
								class="glyphicon glyphicon-calendar" style="height: 30px;"></i>
								<span style="font-size: 15px; font-family: Microsoft JhengHei">預約</span>
						</a></li>

						<li><a href="./mem_back_qanda.jsp"> <i
								class="glyphicon glyphicon-comment" style="height: 30px;"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">Q&A</span>
								<span class="label label-warning pull-right"
								style="font-size: 15px; font-family: Microsoft JhengHei">3</span>
						</a></li>

						<li><a href="./mem_back_lease.jsp"> <i
								class="glyphicon glyphicon-file" style="height: 30px;"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">租賃紀錄</span>
						</a></li>

						<li><a href="./mem_back_reset.jsp"> <i
								class="glyphicon glyphicon-edit" style="height: 30px;"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">修改會員資料</span>
						</a></li>
					</ul>

				</div>
				<!-- /span3 -->



				<div class="span8">
					<h1 class="page-title">
						<i class="glyphicon glyphicon-calendar"></i> <span
							style="font-family: Microsoft JhengHei">預約</span>
					</h1>

					<div class="widget widget-table">

						<div class="widget-header">
							<i class="glyphicon glyphicon-list"
								style="font-size: 20px; padding-left: 8px"></i> <span
								style="font-weight: bold; font-size: 18px; font-family: Microsoft JhengHei">我的預約</span>
						</div>
						<!-- /widget-header -->


						<!-- 這邊是放你的資料 -->
						<div class="widget-content">
						</div>
						<!-- /widget-content -->
						
					</div>
					<!-- /widget -->
				</div>
				<!-- /span9 -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /content -->


	<div id="footer">

		<!-- 		<div class="container">
			<hr />
			<p style="text-align: center">Hermit House for Rent &reg;</p>
		</div> -->
		<!-- /container -->
	</div>
	<!-- /footer -->




	<!-- Le javascript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/excanvas.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
</body>
</html>