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
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/font-awesome.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/adminia.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/adminia-responsive.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/pages/dashboard.css" rel="stylesheet" />
</head>
<body>
	<div id="content">

		<div class="container">

			<div class="row">

				<div class="span3">

					<div class="account-container">

						<div class="account-avatar">
							<img src="./css/images/god.ico" alt="" class="thumbnail" />
						</div>
						<!-- /account-avatar -->

						<div class="account-details">
							<span class="account-name">eeit9704</span> <span
								class="account-name">徐漢勳</span>
						</div>
						<!-- /account-details -->
					</div>
					<!-- /account-container -->

					<hr />

					<ul id="main-nav" class="nav nav-tabs nav-stacked">
						<li class="active"><a href="./"> <i class="glyphicon glyphicon-home"
								style="height: 30px;"></i> <span style="font-size: 15px">首頁</span>
						</a></li>

						<li><a href="./faq.html"> <i class="glyphicon glyphicon-heart"
								style="height: 30px;"></i> <span style="font-size: 15px">收藏</span>
						</a></li>

						<li><a href="./plans.html"> <i class="glyphicon glyphicon-calendar"
								style="height: 30px;"></i> <span style="font-size: 15px">預約</span>
						</a></li>

						<li><a href="./grid.html"> <i class="glyphicon glyphicon-comment"
								style="height: 30px;"></i> <span style="font-size: 15px">Q&A</span>
								<span class="label label-warning pull-right"
								style="font-size: 15px">5</span>
						</a></li>

						<li><a href="./account.html"> <i class="glyphicon glyphicon-file"
								style="height: 30px;"></i> <span style="font-size: 15px">租賃紀錄</span>
						</a></li>

						<li><a href="./charts.html"> <i class="glyphicon glyphicon-edit"
								style="height: 30px;"></i> <span style="font-size: 15px">修改會員資料</span>
						</a></li>
					</ul>

					<hr />
				</div>
				<!-- /span3 -->



				<div class="span8">
					<h1 class="page-title">
						<i class="glyphicon glyphicon-home"></i> 首頁
					</h1>

					<div class="widget widget-table">

						<div class="widget-header">
							<i class="glyphicon glyphicon-list" ></i>
							<h3>會員基本資料</h3>
						</div>
						<!-- /widget-header -->

						<div class="widget-content">

							<table class="table table-striped table-bordered">
								<tbody>
								</tbody>
							</table>
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

		<div class="container">
			<hr />
			<p style="text-align: center">Hermit House for Rent &reg;</p>
		</div>
		<!-- /container -->
	</div>
	<!-- /footer -->




	<!-- Le javascript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/excanvas.min.js"></script>
<%-- 		<script src="<%=request.getContextPath()%>/js/jquery.flot.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.flot.pie.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.flot.orderBars.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.flot.resize.js"></script> --%>
	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<%-- 	<script src="<%=request.getContextPath()%>/js/charts/bar.js"></script> --%>
</body>
</html>