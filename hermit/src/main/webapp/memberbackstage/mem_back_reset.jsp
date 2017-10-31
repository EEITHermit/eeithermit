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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/iEdit.min.css">
</head>
<body>
	<jsp:include page="/fragment/member_page.jsp"></jsp:include>
	<style>
	a:link, a:visited, a:hover ,a:active{
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
						<li><a href="<%=request.getContextPath()%>/mem_back_index.jsp"> <i
								class="glyphicon glyphicon-home" style="height: 30px;"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">首頁</span>
						</a></li>

						<li><a href="<%=request.getContextPath()%>/mem_back_favorite.jsp"> <i
								class="glyphicon glyphicon-heart" style="height: 30px;"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">收藏</span>
						</a></li>

						<li><a href="<%=request.getContextPath()%>/mem_back_calendar.jsp"> <i
								class="glyphicon glyphicon-calendar" style="height: 30px;"></i>
								<span style="font-size: 15px; font-family: Microsoft JhengHei">預約</span>
						</a></li>

						<li><a href="<%=request.getContextPath()%>/mem_back_qanda.jsp"> <i
								class="glyphicon glyphicon-comment" style="height: 30px;"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">Q&A</span>
						</a></li>

						<li><a href="<%=request.getContextPath()%>/mem_back_lease.jsp"> <i
								class="glyphicon glyphicon-file" style="height: 30px;"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">租賃紀錄</span>
						</a></li>

						<li class="active"><a href="<%=request.getContextPath()%>/mem_back_reset.jsp"> <i
								class="glyphicon glyphicon-edit" style="height: 30px;"></i> <span
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
<div class="widget-content" style="padding:20px">
	<div class="row">
	<div class="col-md-10 col-md-offset-4 mid">
	<div class="col-md-4 mid">
		<form method="POST" action="<%=request.getContextPath()%>/member.do?action=update" id="form">
			
			會員編號<input type="text" value="${LoginOK.memNO}" disabled><br>
				 <input type="hidden" readonly name="memNO" value="${LoginOK.memNO}">
			
			電話 <input type="text" value="${LoginOK.memTel}" disabled>
			   <input type="hidden" name="memTel" value="${LoginOK.memTel}">
				<font size="-1" color="#FF0000">${MsgMap.memTel}</font>
				<br>
			
			帳號<input type="text" value="${LoginOK.memAccount}" disabled>
				<input type="hidden" name="memAccount" value="${LoginOK.memAccount}"><br>
			
			密碼<input type="password" value="${realPwd}" name="memPwd">
				<font size="-1" color="#FF0000">${MsgMap.memPwd}</font>
				<br>
			
			姓名<input type="text" value="${LoginOK.memName}" name="memName">
            	<font size="-1" color="#FF0000">${MsgMap.memName}</font>
				<br>
			
			性別<input type="text" value="${LoginOK.memGender}" disabled>
			   <input type="hidden" readonly name="memGender" value="${LoginOK.memGender}"><br>
			   
			信箱<input type="text" value="${LoginOK.memEmail}" name="memEmail">
				<font size="-1" color="#FF0000">${MsgMap.memEmail}</font>
				<br>
			註冊時間<input type="text" value="${LoginOK.memRegister}" disabled>
				<input type="hidden" readonly name="memRegister" value="${LoginOK.memRegister}"><br>
			
			會員狀態<input type="text" value="${LoginOK.memStatus}" disabled>
				<input type="hidden" readonly name="memStatus" value="${LoginOK.memStatus}"><br>
			
			違規次數<input type="text" value="${LoginOK.memInfract}" disabled>
				<input type="hidden" readonly name="memInfract" value="${LoginOK.memInfract}"><br>
			
				<div>
			圖片<input type="file" id="file" > 
				<input type="hidden" id="memImage" name="memImage" value="${LoginOK.memImage}">
				<img id="result" src="${LoginOK.memImage}" name="memImage" id="memImage" height="200" width="200">
				</div>
				<input type="submit" value="修改">
			
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



	<!-- Le javascript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/excanvas.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
	<script type="text/javascript">
	$("#file").change(function(e){
		  
		  var img = e.target.files[0];

		  if(!img.type.match('image.*')){
		    alert("Whoops! That is not an image.");
		    return;
		  }
		  iEdit.open(img, true, function(res){
		    $("#result").attr("src", res);
		  });
		  //在檔案送出前，讓image的src送到input裡
		  $("#form").submit(function(event){
			  $("#memImage").val($("#result").attr("src"));
		  }) 
		});
	</script>
</body>
</html>