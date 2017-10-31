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
<link
	href='<%=request.getContextPath()%>/css/jqueryText/jquery-te-1.4.0.css'
	rel='stylesheet' />

<style>
 #queryTable { 
 	display: none; 
 } 

 #commentForm { 
 	display: none; 
} 

#queryBT {
	border-top: 1px solid #000000; background : #15d48b; background :
	-webkit-gradient( linear, left top, left bottom, from( #228f1d), to(
	#15d48b)); background : -webkit-linear-gradient( top, #228f1d, #15d48b);
	background : -moz-linear-gradient( top, #228f1d, #15d48b); background :
	-ms-linear-gradient( top, #228f1d, #15d48b); background :
	-o-linear-gradient( top, #228f1d, #15d48b); padding : 11.5px 23px;
	-webkit-border-radius : 25px; -moz-border-radius : 25px; border-radius
	: 25px; -webkit-box-shadow : rgba( 0, 0, 0, 1) 0 1px 0; -moz-box-shadow
	: rgba( 0, 0, 0, 1) 0 1px 0; box-shadow : rgba( 0, 0, 0, 1) 0 1px 0;
	text-shadow : rgba( 0, 0, 0, .4) 0 1px 0;
	color: #a67219; font-size : 22px; font-family : 'Lucida Grande',
	Helvetica, Arial, Sans-Serif; text-decoration : none;
	vertical-align: middle;
	background: #15d48b;
	background: -webkit-gradient(linear, left top, left bottom, from(#228f1d),
		to(#15d48b));
	background: -webkit-linear-gradient(top, #228f1d, #15d48b);
	background: -moz-linear-gradient(top, #228f1d, #15d48b);
	background: -ms-linear-gradient(top, #228f1d, #15d48b);
	background: -o-linear-gradient(top, #228f1d, #15d48b);
	padding: 11.5px 23px;
	-webkit-border-radius: 25px;
	-moz-border-radius: 25px;
	border-radius: 25px;
	-webkit-box-shadow: rgba(0, 0, 0, 1) 0 1px 0;
	-moz-box-shadow: rgba(0, 0, 0, 1) 0 1px 0;
	box-shadow: rgba(0, 0, 0, 1) 0 1px 0;
	text-shadow: rgba(0, 0, 0, .4) 0 1px 0; color : #a67219;
	font-size: 22px;
	font-family: 'Lucida Grande', Helvetica, Arial, Sans-Serif;
	text-decoration: none;
}

#queryBT:hover {
	border-top-color: #bd6c49; background : #bd6c49;
	color: #ccc;
	background: #bd6c49;
}

#queryBT:active {
	border-top-color: #456f8a;
	background: #456f8a;
}

#commentBT {
	border-top: 1px solid #000000; background : #15d48b; background :
	-webkit-gradient( linear, left top, left bottom, from( #228f1d), to(
	#15d48b)); background : -webkit-linear-gradient( top, #228f1d, #15d48b);
	background : -moz-linear-gradient( top, #228f1d, #15d48b); background :
	-ms-linear-gradient( top, #228f1d, #15d48b); background :
	-o-linear-gradient( top, #228f1d, #15d48b); padding : 11.5px 23px;
	-webkit-border-radius : 25px; -moz-border-radius : 25px; border-radius
	: 25px; -webkit-box-shadow : rgba( 0, 0, 0, 1) 0 1px 0; -moz-box-shadow
	: rgba( 0, 0, 0, 1) 0 1px 0; box-shadow : rgba( 0, 0, 0, 1) 0 1px 0;
	text-shadow : rgba( 0, 0, 0, .4) 0 1px 0;
	color: #a67219; font-size : 22px; font-family : 'Lucida Grande',
	Helvetica, Arial, Sans-Serif;
	text-decoration: none;
	vertical-align: middle;
	background: #15d48b;
	background: -webkit-gradient(linear, left top, left bottom, from(#228f1d),
		to(#15d48b));
	background: -webkit-linear-gradient(top, #228f1d, #15d48b);
	background: -moz-linear-gradient(top, #228f1d, #15d48b);
	background: -ms-linear-gradient(top, #228f1d, #15d48b);
	background: -o-linear-gradient(top, #228f1d, #15d48b);
	padding: 11.5px 23px;
	-webkit-border-radius: 25px;
	-moz-border-radius: 25px;
	border-radius: 25px;
	-webkit-box-shadow: rgba(0, 0, 0, 1) 0 1px 0;
	-moz-box-shadow: rgba(0, 0, 0, 1) 0 1px 0;
	box-shadow: rgba(0, 0, 0, 1) 0 1px 0;
	text-shadow: rgba(0, 0, 0, .4) 0 1px 0; color : #a67219;
	font-size: 22px;
	font-family: 'Lucida Grande', Helvetica, Arial, Sans-Serif;
}

#commentBT:hover {
	border-top-color: #bd6c49; background : #bd6c49;
	color: #ccc;
	background: #bd6c49;
}

#commentBT:active {
	border-top-color: #456f8a;
	background: #456f8a;
}
</style>

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
						<span class="account-name"
							style="font-family: Microsoft JhengHei;padding-left: 75px">${LoginOK.memAccount}</span>
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

						<li><a href="./mem_back_calendar.jsp"> <i
								class="glyphicon glyphicon-calendar" style="height: 30px;"></i>
								<span style="font-size: 15px; font-family: Microsoft JhengHei">預約</span>
						</a></li>

						<li class="active"><a href="./mem_back_qanda.jsp"> <i
								class="glyphicon glyphicon-comment" style="height: 30px;"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">Q&A</span>
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
						<i class="glyphicon glyphicon-comment"></i> <span
							style="font-family: Microsoft JhengHei">問與答</span>
					</h1>

					<div class="widget widget-table">

						<div class="widget-header">
							<i class="glyphicon glyphicon-list"
								style="font-size: 20px; padding-left: 8px"></i> <span
								style="font-weight: bold; font-size: 18px; font-family: Microsoft JhengHei">問與答記錄</span>
						</div>
						<!-- /widget-header -->


						<!-- 這邊是放你的資料 -->
						<div class="widget-content">
						<!--模式選擇按鈕 -->
							<div class="row">
								<div class="container col-md-6 col-md-offset-3">
									<button id="queryBT">查詢留言</button>
									<button id="commentBT">客服申請</button>
								</div>
							</div>
						<!--查詢留言區域 -->
							<div>
								<table id="queryTable">
									<thead>
										<tr>
											<th>留言時間</th>
											<th>留言類型</th>
											<th>房屋連結</th>
											<th>留言內容</th>
											<th>回覆時間</th>
											<th>回覆內容</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="qaVO" items="${array}">
											<tr>
												<td>${qaVO.qTime}</td>
												<c:if test="${qaVO.qaType == 0}">
													<td>客服</td>
												</c:if>
												<c:if test="${qaVO.qaType == 1}">
													<td>詢問</td>
												</c:if>
												<td><a style="color: blue; text-decoration: underline;"
													href="${qaVO.houseVO.houseNO}">${qaVO.houseVO.houseTitle}</a></td>
												<td>${qaVO.qDetail}</td>
												<td>${qaVO.aTime}</td>
												<td>${qaVO.aDetail}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!--客服申請區域 -->
								<form id="commentForm"
									action="<%=request.getContextPath()%>/QAndAServlet?mission=insert&type=0"
									method="POST">
									請選擇房屋：<select name="houseNO" class="custom-select">
										<option>請選擇</option>
								<!--filter會傳來houseArray 為此會員所租賃的房屋 -->
										<c:forEach var="houseVO" items="${houseArray}">
											<option value="${houseVO.houseNO}">${houseVO.houseAddr}</option>
										</c:forEach>
									</select> 申訴內容：
									<textarea id="commentArea" class="commentArea" name="qDetail"
										style="resize: none;"></textarea>
									<input type="button" value="提交" onclick="check()" />
								</form>
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
	<script src='<%=request.getContextPath()%>/js/jquery-te-1.4.0.min.js'></script>
	<script src="<%=request.getContextPath()%>/js/excanvas.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
	<script>
		document.addEventListener("DOMContentLoaded", work);

		function work() {
			//跳出查詢畫面
			$("#queryBT").click(function() {
				$("#queryTable").toggle(true, 1000);
				$("#commentForm").toggle(false, 1000);
			});
			//跳出投訴頁面
			$("#commentBT").click(function() {
				$("#queryTable").toggle(false, 1000);
				$("#commentForm").toggle(true, 1000);
			});
			//產生jqueyText
			$('#commentArea').jqte();
		};

		function check() {
			if (confirm("是否確認送出投訴")) {
				(document.getElementById("commentForm")).submit();
			}
		};
	</script>
</body>
</html>