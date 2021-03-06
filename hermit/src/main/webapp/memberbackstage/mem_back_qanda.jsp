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
<link href='<%=request.getContextPath()%>/css/bootstrap.min.css'
	rel='stylesheet' />
<link
	href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/datatables.min.css" />
<link href="<%=request.getContextPath()%>/css/font-awesome.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/adminia.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/adminia-responsive.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/pages/dashboard.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/jquery-ui.min.css"
	rel="stylesheet" />
<link
	href='<%=request.getContextPath()%>/css/jqueryText/jquery-te-1.4.0.css'
	rel='stylesheet' />

<style>
#queryDiv {
	display: none;
}

#formDiv {
  	display:inline-block;
  	position:static;
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

						<li class="active"><a
							href="<%=request.getContextPath()%>/memberbackstage/mem_back_qanda.jsp">
								<i class="glyphicon glyphicon-comment"
								style="height: 30px; font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">Q&A</span>
						</a></li>

						<li><a
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
							<div>
								<div class="container col-md-12">
									<button id="queryBT" class="btn btn-primary btn-lg btn-block">查詢留言</button>
								</div>
								<!--查詢留言區域 -->
								<div id="queryDiv">
									<table id="queryTable"
										class="table table-striped table-bordered" cellspacing="0" style="table-layout:fixed;word-break:break-all;">
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
								<style>
								a:hover{
									color : blue;
	    							text-decoration: none;
	    							padding: 0px;
	    							text-align: left;	
	    							text-decoration: none;
	    							display: inline-block;
								}
								</style>
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
													<td><a
														style="color: blue; text-decoration: underline;"
														href="<%=request.getContextPath()%>/HousePage?NO=${qaVO.houseVO.houseNO}">${qaVO.houseVO.houseTitle}</a></td>
													<c:if test="${qaVO.qDetail.length()>10}">
														<td>
															<div><span>${qaVO.qDetail.substring(0,10)}...</span><a onclick="more(event)" style="font-size:10px;float:right;">展開內容</a></div>
															<div style="display:none;"><span>${qaVO.qDetail}</span><a onclick="less(event)" style="font-size:10px;float:right;">收起</a></div>
														</td>
													</c:if>
													<c:if test="${qaVO.qDetail.length()<=10}">
														<td>${qaVO.qDetail}</td>
													</c:if>
													<td>${qaVO.aTime}</td>
													<c:if test="${qaVO.aDetail.length()>10}">
														<td>
															<div><span>${qaVO.aDetail.substring(0,10)}...</span><a onclick="more(event)" style="font-size:10px;float:right;">展開內容</a></div>
															<div style="display:none;"><span>${qaVO.aDetail}</span><a onclick="less(event)" style="font-size:10px;float:right;">收起</a></div>
														</td>
													</c:if>
													<c:if test="${qaVO.aDetail.length()<=10||qaVO.aDetail==null}">
														<td>${qaVO.aDetail}</td>
													</c:if>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!--客服申請區域 -->
							<div>
								<div class="container col-md-12">
									<button id="commentBT" class="btn btn-primary btn-lg btn-block">客服申請</button>
								</div>

							<div id="formDiv" class="row">
								<form id="commentForm" class="form-group" style="margin-top:0px"
									action="<%=request.getContextPath()%>/QAndAServlet?mission=insert&type=0"
									method="POST">
									<div class="col-md-8">
										<label for="houseNO" class="form-label" style="margin-top:0px">請選擇房屋：</label> <select
											name="houseNO" class="form-control" id="houseNO"
											style="background-color: #DDDDDD;">
											<option>請選擇房屋</option>
											<!--filter會傳來houseArray 為此會員所租賃的房屋 -->
											<c:forEach var="houseVO" items="${houseArray}">
												<option value="${houseVO.houseNO}">${houseVO.houseAddr}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-md-12">
										<div>
										<label for="qDetail" class="form-label"> 申訴內容： </label>
										<textarea id="commentArea" class="commentArea" name="qDetail"
											style="resize: none;"></textarea>
									</div>
									</div>
									<div class="col-md-4 col-md-offset-5">
										<button class="btn btn-secondary btn-lg" type="button"
											onclick="check()">提交</button>
									</div>
								</form>
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
	<div style="height: 50px"></div>
<!-- 	<div id="footer"> -->

		<!-- 		<div class="container">
			<hr />
			<p style="text-align: center">Hermit House for Rent &reg;</p>
		</div> -->
		<!-- /container -->
<!-- 	</div> -->
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
	<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
	<script src='<%=request.getContextPath()%>/js/jquery-te-1.4.0.min.js'></script>
	<script src="<%=request.getContextPath()%>/js/excanvas.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
	<script>
		document.addEventListener("DOMContentLoaded", work);
		//縮減回覆及回報內容
		function more(event){
			$(event.target).parents("td").find("div").eq(0).toggle(false);
			$(event.target).parents("td").find("div").eq(1).toggle(true);
		}
		function less(event){
			$(event.target).parents("td").find("div").eq(0).toggle(true);
			$(event.target).parents("td").find("div").eq(1).toggle(false);
		}
		function work() {
			
			//跳出查詢畫面
			$("#queryBT").click(function() {
				$("#queryDiv").toggle("blind",400);
				$("#queryDiv").position({
					of:$("#queryBT"),
					my:"center top",
					at:"center bottom"
				});
			});
			
			//跳出投訴頁面
			$("#commentBT").click(function() {
// 				$("#formDiv").position({
// 					of:$("#commentBT").parent("div"),
// 					my:"center top",
// 					at:"center bottom"
// 				});
				$("#formDiv").toggle("blind",400);
			});
			//產生jqueyText
			$('#commentArea').jqte();
			//產生DataTable
			$('#queryTable').DataTable({
				"language" : {
					"lengthMenu" : "每頁顯示 _MENU_ 筆",
					"zeroRecords" : "Nothing found - sorry",
					"info" : "現在正顯示   _PAGE_  共有 _PAGES_ 頁",
					"infoEmpty" : "No records available",
					"infoFiltered" : "(filtered from _MAX_ total records)",
					"search" : "查詢:",
					"paginate" : {
						"first" : "首頁",
						"last" : "末頁",
						"next" : "下頁",
						"previous" : "前頁"
					}
				},
				"autoWidth" : false,
				//設定各個欄位屬性
				"columnDefs" : [ {
					"targets" : [ 0 ],
					"width" : "10%"
				},{
					"targets" : [ 1 ],
					"width" : "10%"
				}, {
					"targets" : [ 2 ],
					"width" : "15%"
				}, {
					"targets" : [ 3 ],
					"width" : "30%"
				}, {
					"targets" : [ 4 ],
					"width" : "20%"
				}, {
					"targets" : [ 5 ],
					"width" : "10%"
				}]
			});
			
// 			var qBT3 = $("#queryTable>tbody tr>td");
// 			qBT3.find("a").eq(0).on("click",function(){
// 				$(this).parents("td").find("p").eq(0).toggle(false);
// 				$(this).parents("td").find("p").eq(1).toggle(true);
// 			});
// 			qBT3.find("a").eq(1).on("click",function(){
// 				$(this).parents("td").find("p").eq(0).toggle(true);
// 				$(this).parents("td").find("p").eq(1).toggle(false);
// 			});
// 			var aBT7 = $("#queryTable>tbody td:nth-child(7)");
// 			aBT7.find("a").eq(0).on("click",function(){
// 				$(this).parents("td").find("p").eq(0).toggle(false);
// 				$(this).parents("td").find("p").eq(1).toggle(true);
// 			});
// 			aBT7.find("a").eq(1).on("click",function(){
// 				$(this).parents("td").find("p").eq(0).toggle(true);
// 				$(this).parents("td").find("p").eq(1).toggle(false);
// 			});
		};

		function check() {
			console.log($("#commentArea").val());
			if($("#commentArea").val().trim().length == 0){
				alert("請先輸入投訴內容");
				return;
			};
			if($("#houseNO").val() == "請選擇房屋"){
				alert("請先選擇房屋");
				return;
			};
			if (confirm("是否確認送出投訴")) {
				(document.getElementById("commentForm")).submit();
			};
		};
	</script>
</body>
</html>