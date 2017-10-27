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

						<li><a href="./mem_back_calendar.jsp"> <i
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

						<li class="active"><a href="./mem_back_reset.jsp"> <i
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
						<div class="widget-content">
						<div class="row">
		<div class="col-md-4 mid"></div>
		<div class="col-md-4 mid">
			
		<form method="POST" action="<%=request.getContextPath()%>/member.do?action=update" id="form">
			
			編號<input type="text" value="${memNO}" disabled><br>
			   <input type="hidden" readonly value="${memNO}" name="memNO">
			
			電話 <input type="text" value="${memTel}" disabled>
			   <input type="hidden" value="${memTel}" name="memTel">
			<font size="-1" color="#FF0000">${MsgMap.memTel}</font>
			<br>
			
			帳號<input type="text" value="${memAccount}" disabled>
			<input type="hidden"  value="${memAccount}" name="memAccount"><br>
			
			密碼<input type="password" value="${memPwd}" name="memPwd">
			<font size="-1" color="#FF0000">${MsgMap.memPwd}</font>
			<br>
			
			姓名<input type="text" value="${memName}" name="memName">
            <font size="-1" color="#FF0000">${MsgMap.memName}</font>
			<br>
			
			性別<input type="text" value="${memGender}" disabled>
			   <input type="hidden" readonly value="${memGender}" name="memGender" ><br>
			   
			信箱<input type="text" value="${memEmail}" name="memEmail">
			<font size="-1" color="#FF0000">${MsgMap.memEmail}</font>
			<br>
			註冊時間<input type="text" value="${memRegister}" disabled>
			<input type="hidden" readonly value="${memRegister}" name="memRegister"><br>
			
			會員狀態<input type="text" value="${memStatus}" disabled>
			<input type="hidden" readonly value="${memStatus}" name="memStatus"><br>
			
			違規次數<input type="text" value="${memInfract}" disabled>
			<input type="hidden" readonly value="${memInfract}" name="memInfract"><br>
			
			<div>
			圖片<input type="file" id="file" > 
			<input type="hidden" id="memImage" name="memImage" value="${memImage}"  >
			
			<img id="result" src="${memImage}" name="memImage" id="memImage" height="200" width="200">
			</div>
			<input type="submit" value="修改">
			
			</form>
		
		
	
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