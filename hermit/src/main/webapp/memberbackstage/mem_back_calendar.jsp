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
<link href='<%=request.getContextPath()%>/css/jquery-ui.min.css'
	rel='stylesheet' />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link
	href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css"
	rel="stylesheet" />
<link
	href='<%=request.getContextPath()%>/css/jquery-ui.structure.min.css'
	rel='stylesheet' />
<link href='<%=request.getContextPath()%>/css/jquery-ui.theme.min.css'
	rel='stylesheet' />
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

						<li class="active"><a
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
							<div id="show" style="margin-top: 25px">
								<table id="showTable" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>預約編號</th>
											<th>業務姓名</th>
											<th>房屋名稱</th>
											<th>房屋住址</th>
											<th>業務電話</th>
											<th>預約時間</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
							<!-- 放置給刪除預約所需填方塊 -->
							<div id="reasonDiv" title="刪除預約">
								<form id="reasonDiv" class="form-group">
									<input id="resNO" type="hidden" value=""> <label
										class="form-label">請簡述取消預約之緣由：</label>
									<textarea class="form-control" id="reason"
										style="resize: none; height: 110px; background-color: #d0d0d0"> </textarea>
									<br />
									<p style="color: gray;">__________________________________</p>
									<div class="" style="float: left">
										<button type="button" class="btn btn-primary"
											id="reasonButton">送出</button>
									</div>
									<div class="" style="float: right">
										<button type="button" class="btn btn-primary" id="cancel">取消</button>
									</div>
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
	<script src="<%=request.getContextPath()%>/js/excanvas.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
	<script src='<%=request.getContextPath()%>/js/jquery-ui.min.js'></script>
	<script>
	$("document").ready(work);
	
	function work(){
		var body = $("#showTable>tbody");
			$.get("<%=request.getContextPath()%>/reservationServlet?mission=queryReservation",{memberNo:"${LoginOK.memNO}"},function(data){
				array = JSON.parse(data);
				for(var res of array){
					var tr = $("<tr></tr>");
					var tdId = $("<td>"+res["eventNO"]+"</td>");
					var tdName = $("<td>"+res["empVO"]["empName"]+"</td>");
					var tdTitle = $("<td>"+res["houseVO"]["houseTitle"]+"</td>");
					var tdAddr = $("<td>"+res["houseVO"]["houseAddr"]+"</td>");
					var tdTel = $("<td>"+res["empVO"]["empPhone"]+"</td>");
					var tdStart = $("<td>"+res["eventStartTime"]+"</td>");
					var tdButton = $("<td><button class='btn btn-primary'>刪除</button></td>")
					
					tr.append(tdId).append(tdName).append(tdTitle).append(tdAddr).append(tdTel).append(tdStart).append(tdButton);
					tr.appendTo(body);
					//產生DataTable
					$('#showTable').DataTable({
						"destroy": true,
						"paging": false,
						"searching": false,
						"info": false,
						"autoWidth" : false,
						//設定各個欄位屬性
						"columnDefs" : [ {
							"targets" : [ 0 ],
							"width" : "10%"
						}, {
							"targets" : [ 1 ],
							"width" : "10%"
						}, {
							"targets" : [ 2 ],
							"width" : "20%"
						}, {
							"targets" : [ 3 ],
							"width" : "20%"
						}, {
							"targets" : [ 4 ],
							"width" : "10%"
						} , {
							"targets" : [ 5 ],
							"width" : "20%"
						}, {
							"targets" : [ 6 ],
							"width" : "10%"
						}
						]
						
					});
				};
			});
		
		//會員刪除預約
		var thisButton;
		body.on("click","button",function(event){
			thisButton = $(this);
			$("#reasonDiv").dialog("open");
			$("#resNO").val($(event.target).parents("tr").children("td").eq(0).text());
		});
		//設定刪除預約之dialog
		$("#reasonDiv").dialog({
			autoOpen: false,
			 height: 330,
		     width: 350,
		     modal: true,
		     resizable:false
		});
		$("#cancel").click(function(){
			$("#reasonDiv").dialog("close");
		});
		$("#reasonButton").click(function(){
			if(confirm("確定要刪除此預約?")){
				$.post("<%=request.getContextPath()%>/calendarServlet?mission=deleteNotice"
			 			,{id:$("#resNO").val(),ps:$("#reason").val()}
			 			,function(data){
			 				alert(data);
			 				thisButton.text("已回報").attr("disabled",true);
			 				$("#reasonDiv").dialog("close");
			 				$("#reason").val("");
			 			})
			}
		});
		
	}
</script>
</body>
</html>