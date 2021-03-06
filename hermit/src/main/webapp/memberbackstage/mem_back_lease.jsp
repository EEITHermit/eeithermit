<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberBackFQ&A</title>
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lightbox.css" >
</head>
<body>
	<jsp:include page="/fragment/member_page.jsp"></jsp:include>
	<style>
a:link, a:visited, a:hover, a:active {
	text-align: left;
}
</style>
	<div id="content">

		<div class="container">

			<div class="row">

				<div class="span3">

					<div class="account-container">

						<div class="account-avatar">
							<img src="${LoginOK.memImage}" alt="memImage" class="thumbnail" />
						</div>
						<!-- /account-avatar -->


						<!-- /account-details -->
					</div>
					<div class="account-details">
						<p class="account-name"
							style="font-family: Microsoft JhengHei; text-align: center; font-size: 24px">${LoginOK.memName}
						</p>
					</div>
					<!-- /account-container -->

					<hr />

					<ul id="main-nav" class="nav nav-tabs nav-stacked">
						<li><a
							href="<%=request.getContextPath()%>/memberbackstage/mem_back_index.jsp">
								<i class="glyphicon glyphicon-home"
								style="height: 30px; font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">會員中心</span>
						</a></li>

						<li><a
							href="<%=request.getContextPath()%>/memberbackstage/mem_back_favorite.jsp">
								<i class="glyphicon glyphicon-heart"
								style="height: 30px; font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">收藏</span>
						</a></li>

						<li><a
							href="<%=request.getContextPath()%>/memberbackstage/mem_back_calendar.jsp">
								<i class="glyphicon glyphicon-calendar"
								style="height: 30px; font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">預約</span>
						</a></li>

						<li><a
							href="<%=request.getContextPath()%>/memberbackstage/mem_back_qanda.jsp">
								<i class="glyphicon glyphicon-comment"
								style="height: 30px; font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">Q&A</span>
						</a></li>

						<li class="active"><a
							href="<%=request.getContextPath()%>/LeaseServlet.do?action=getAllLease&memNO=${LoginOK.memNO}">
								<i class="glyphicon glyphicon-file"
								style="height: 30px; font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">租賃紀錄</span>
						</a></li>

						<li><a
							href="<%=request.getContextPath()%>/member.do?action=getOneMember">
								<i class="glyphicon glyphicon-edit"
								style="height: 30px; font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">修改會員資料</span>
						</a></li>
					</ul>

				</div>
				<!-- /span3 -->



				<div class="span8">
					<h1 class="page-title">
						<i class="glyphicon glyphicon-file"></i> <span
							style="font-family: Microsoft JhengHei">租賃記錄</span>
					</h1>

					<div class="widget widget-table">

						<div class="widget-header">
							<i class="glyphicon glyphicon-list"
								style="font-size: 20px; padding-left: 8px"></i> <span
								style="font-weight: bold; font-size: 18px; font-family: Microsoft JhengHei">我的租賃記錄</span>
						</div>
						<!-- /widget-header -->
							

						<!-- 這邊是放你的資料 -->
						<div class="widget-content">
							<div id="leaseDiv">
								<div>
								<table border="1px" id="myTable" class="table">
									<tr>
										<td>合約編號</td>
										<td>合約起始日期</td>
										<td>合約結束日期</td>
										<td>員工編號</td>
										<td>租金</td>
										<td>押金</td>
										<td>折扣</td>
										<td>簽約日期</td>
										<td>備註</td>
										<td>是否退還押金</td>
									</tr>
									<c:forEach var="Lease" items="${list}">
										<tr>
											<td>${Lease.leaseNO}</td>
											<td>${Lease.leaseBeginDate}</td>
											<td>${Lease.leaseEndDate}</td>
											<td>${Lease.empVO.empName}</td>
											<td>${Lease.leaseRent}</td>
											<td>${Lease.leaseDeposit}</td>
											<td>${Lease.leaseRelief}</td>
											<td>${Lease.leaseDate}</td>
											<td>${Lease.houseNote}</td>
											<td id="leaseRefund">${Lease.leaseRefund}</td>
										</tr>
										<tr>
												<td>合約照片<a class="example-image-link" href="${Lease.leasePic}" data-lightbox="example-1"><img class="example-image" src="${Lease.leasePic}" alt="image-1" style="width:75px" id="result"/></a></td>
										</tr>
										<input type="hidden" value="${Lease.memberVO.memNO}" name="memNO" readonly>
										<input type="hidden" value="${Lease.leaseNO}" name="leaseNO">
									</c:forEach>
								</table>
								</div>
							</div>						
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
	<script src="<%=request.getContextPath()%>/js/lightbox.js"></script>
	<script>
	$(document).ready(function(){
		var leaseRefund =$("#leaseRefund");
		console.log(leaseRefund.text());
		if(leaseRefund.text()==0){
			leaseRefund.text("否");
		}else{
			leaseRefund.text("是");
		}
		lightbox.option({
		      'resizeDuration': 200,
		      'wrapAround': true,
		    })
	});
	</script>
</body>
</html>