<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberBackFReset</title>
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/iEdit.min.css">
</head>
<body>
	<jsp:include page="/fragment/member_page.jsp"></jsp:include>
	<style>
a:link, a:visited, a:hover, a:active {
	text-align: left;
}

label {
	font-family: 標楷體;
	font-size: 20px;
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
								style="font-size: 15px; font-family: Microsoft JhengHei">首頁</span>
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

						<li><a
							href="<%=request.getContextPath()%>/memberbackstage/mem_back_lease.jsp">
								<i class="glyphicon glyphicon-file"
								style="height: 30px; font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">租賃紀錄</span>
						</a></li>

						<li class="active"><a
							href="<%=request.getContextPath()%>/memberbackstage/mem_back_reset.jsp">
								<i class="glyphicon glyphicon-edit"
								style="height: 30px; font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">修改會員資料</span>
						</a></li>
					</ul>

				</div>
				<!-- /span3 -->



				<div class="span8">
					<h1 class="page-title">
						<i class="glyphicon glyphicon-edit"></i> <span
							style="font-family: Microsoft JhengHei">修改會員資料</span>
					</h1>

					<div class="widget widget-table">

						<div class="widget-header">
							<i class="glyphicon glyphicon-list"
								style="font-size: 20px; padding-left: 8px"></i> <span
								style="font-weight: bold; font-size: 18px; font-family: Microsoft JhengHei">修改會員資料</span>
						</div>
						<!-- /widget-header -->


						<!-- 這邊是放你的資料 -->
						<div class="content">
							<form method="POST"
								action="<%=request.getContextPath()%>/member.do?action=update"
								id="form" class="form-horizontal">
								<div class="form-group">
									<label class="col-md-5 control-label">會員編號</label>
									<div class="col-xs-2">
										<input type="text" value="${LoginOK.memNO}" disabled
											class="form-control"> <input type="hidden" readonly
											name="memNO" value="${LoginOK.memNO}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">電話</label>
									<div class="col-xs-2">
										<input type="text" value="${LoginOK.memTel}" disabled
											class="form-control"> <input type="hidden"
											name="memTel" value="${LoginOK.memTel}"> <font
											size="-1" color="#FF0000">${MsgMap.memTel}</font>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">帳號</label>
									<div class="col-xs-2">
										<input type="text" value="${LoginOK.memAccount}" disabled
											class="form-control"> <input type="hidden"
											name="memAccount" value="${LoginOK.memAccount}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">密碼</label>
									<div class="col-xs-2">
										<input type="password" value="${realPwd}" name="memPwd"
											class="form-control"> <font size="-1" color="#FF0000">${MsgMap.memPwd}</font>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">姓名</label>
									<div class="col-xs-2">
										<input type="text" value="${LoginOK.memName}" name="memName"
											class="form-control"> <font size="-1" color="#FF0000">${MsgMap.memName}</font>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">性別</label>
									<div class="col-xs-2">
										<input type="text" value="${LoginOK.memGender}" disabled
											class="form-control"> <input type="hidden" readonly
											name="memGender" value="${LoginOK.memGender}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">信箱</label>
									<div class="col-xs-4">
										<input type="text" value="${LoginOK.memEmail}" name="memEmail"
											class="form-control"> <font size="-1" color="#FF0000">${MsgMap.memEmail}</font>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">註冊時間</label>
									<div class="col-xs-2">
										<input type="text" value="${LoginOK.memRegister}" disabled
											class="form-control"> <input type="hidden" readonly
											name="memRegister" value="${LoginOK.memRegister}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">會員狀態</label>
									<div class="col-xs-2">
										<input type="text" value="${LoginOK.memStatus}" disabled
											class="form-control"> <input type="hidden" readonly
											name="memStatus" value="${LoginOK.memStatus}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">違規次數</label>
									<div class="col-xs-2">
										<input type="text" value="${LoginOK.memInfract}" disabled
											class="form-control"> <input type="hidden" readonly
											name="memInfract" value="${LoginOK.memInfract}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">圖片</label>
									<div class="col-md-4">
										<input type="file" id="file"> <input type="hidden"
											id="memImage" name="memImage" value="${LoginOK.memImage}">
										<img id="result" src="${LoginOK.memImage}" name="memImage"
											id="memImage" height="200" width="200">
									</div>
								</div>

								<div class="form-group">
									<div class="col-md-6 control-label">

										<input type="submit" value="修改">
									</div>
									<div style="margin-bottom: 100px"></div>
								</div>
							</form>

						</div>
					</div>
					<div class="col-md-4 mid"></div>
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
	<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
	<script type="text/javascript">
		$("#file").change(function(e) {

			var img = e.target.files[0];

			if (!img.type.match('image.*')) {
				alert("Whoops! That is not an image.");
				return;
			}
			iEdit.open(img, true, function(res) {
				$("#result").attr("src", res);
			});
			//在檔案送出前，讓image的src送到input裡
			$("#form").submit(function(event) {
				$("#memImage").val($("#result").attr("src"));
			})
		});
	</script>
</body>
</html>