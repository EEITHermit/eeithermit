<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工首頁</title>
<link href='<%=request.getContextPath()%>/css/jquery-ui.min.css'
	rel='stylesheet' />
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>
<script src='<%=request.getContextPath()%>/js/jquery-ui.min.js'></script>
<!-- Bootstrap core JavaScript-->
<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="<%=request.getContextPath()%>/js/jquery.easing.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="<%=request.getContextPath()%>/js/sb-admin.min.js"></script>
<script>
	document.addEventListener("DOMContentLoaded",work);
	
	function work(){
		var resDiv = $("#resDiv");  //預約功能的div
		var qaDiv = $("#qaDiv"); //Q&A功能的div
		//展開預約
		$("#resButton").on("click",function(){
			var resDiv = $("#resDiv");
			if($(this).text() == "展開"){
				$("#qaButton").text("展開");
				$(this).text("收起");
				qaDiv.hide("drop",1000);
				resDiv.show("drop",1000);
			}else{
				$(this).text("展開");
				resDiv.hide("drop",1000);
			}
		});
		//展開Q&A
		$("#qaButton").on("click",function(){
			
			if($(this).text() == "展開"){
				$("#resButton").text("展開");
				$(this).text("收起");
				resDiv.hide("drop",1000);
				qaDiv.show("drop",1000);
			}else{
				$(this).text("展開");
				qaDiv.hide("drop",1000);
			}
		});
		//設定table
		var tds = $("#qaTable>tbody td");
		$(tds[0]).css("width","15%");
		$(tds[1]).css("width","15%");
		$(tds[2]).css("width","15%");
		$(tds[3]).css("width","15%");
		$(tds[4]).css("width","30%");
		$(tds[5]).css("width","10%");
		//設定回應表單彈出視窗
		$("#qaForm").dialog({
			  autoOpen: false,
		      height: "auto",
		      width: 400,
		      modal: true,	//啟動時網頁其餘功能無法使用
		      resizable:false,
		})
		//Q&A用回應表單
		$('#qaTable>tbody button[name="answer"]').on("click",function(){
			$("#qaNO").val($(this).parents("tr").find("td").eq(0).text());
			$("#qaForm").dialog("open");
		});
		//Q&A連結至派工單
		$('#qaTable>tbody button[name="dispatch"]').on("click",function(){
			if(confirm("是否確認轉入派工單")){
				var number = $(this).parents("tr").find("td").eq(0).text();
				var memName = $(this).parents("tr").find("td").eq(2).text();
				window.location = "<%=request.getContextPath()%>/DispatchList/SignatureInsert.jsp?qaNO="+number+"&memName="+memName;
			}
		});
		//設定期望時間內容
		var button1 = $("#resTable>tbody button:even").on("click",function(){
			var exceptTime = $(this).parents("tr").find("td").eq(3).text();
			var bt = $(this);
			var div = $("#exceptDiv");
			if(bt.text() == "收回"){
				button1.text("查看");
				div.toggle(false);
			}else{
				button1.text("查看");
				bt.text("收回");
				//設定div出現位置
				div.toggle(true).position({
					of:bt,
					my:"left top",
					at:"left bottom",
					collision:"fit"
				});
			}
			//設定div內容
			var times = exceptTime.split(';');
			var td = $("#exceptDiv>table>tbody td");
			td.text("");
			td.eq(0).text("上午");
			td.eq(8).text("下午");
			for(var time of times){
				time = time.trim();
				if(time == "一上"){td.eq(1).text("V")};
				if(time == "二上"){td.eq(2).text("V")};
				if(time == "三上"){td.eq(3).text("V")};
				if(time == "四上"){td.eq(4).text("V")};
				if(time == "五上"){td.eq(5).text("V")};
				if(time == "六上"){td.eq(6).text("V")};
				if(time == "日上"){td.eq(7).text("V")};
				if(time == "一下"){td.eq(9).text("V")};
				if(time == "二下"){td.eq(10).text("V")};
				if(time == "三下"){td.eq(11).text("V")};
				if(time == "四下"){td.eq(12).text("V")};
				if(time == "五下"){td.eq(13).text("V")};
				if(time == "六下"){td.eq(14).text("V")};
				if(time == "日下"){td.eq(15).text("V")};
			};
			
		})
		
		//設定接管後程式
		var button2 = $("#resTable>tbody button:odd").on("click",function(){
			if(confirm("確認接管此預約?")){
				var resNO = $($(this).parents("tr").find("td")[0]).text();
				var empNO = $("#empNO").val();
				$.post("<%=request.getContextPath()%>/mentionServlet?mission=takeOver",{resNO:resNO,empNO:empNO},function(data){
					alert(data);
				});
				$(this).text("已處理").attr("disabled",true);
			}
		});
	};
	//回應表單取消按鈕
	function cancel(){
		$("#qaForm").dialog("close");
	}
	//回應表單確認按鈕
	function check(){
		if(confirm("是否確認送出此回覆?")){
			$("#qaForm>form")[0].submit();
		}
	}
</script>
<style>
table {
	font-size: 24px;
}

#resDiv {
	text-align: center;
	display: none;
	float: left;
	position: absolute;
}

#resTable {
	
}

#exceptDiv {
	display: none;
	float: left;
	position: absolute;
}

#exceptDiv table {
	background-color: #D1BBFF;
	text-align: center;
}
#qaDiv {
	display: none;
}
#qaForm{
	display:none;
}

</style>
</head>
<body>
	<!-- 載入框架 -->
	<jsp:include page="/fragment/back_side_page.jsp" />
	<!-- 取得員工編號 -->
	<input type="hidden" id="empNO" value="${LoginOK.empNO}" />


	<!-- bootstrap -->

	<div class="container">
		<!-- Breadcrumbs-->

		<h1>HermitのHome</h1>
		<hr>
		<div class="row">
			<!-- 預約功能-->
			<div class="col-xl-3 col-sm-6 mb-3">
				<div class="card text-white bg-primary o-hidden h-100" id="resCard">
					<div class="card-body">
						<div class="card-body-icon">
							<i class="fa fa-fw fa-comments"></i>
						</div>
						<div class="mr-5" style="font-size: 18px">
							您有<span style="font-size: 36px; color: red"> ${resSize} </span>筆未處理預約
						</div>
					</div>
					<a id="resButton">展開</a>
				</div>
			</div>


			<!-- Q&A推播 -->
			<div class="col-xl-3 col-sm-6 mb-3">
				<div class="card text-white bg-primary o-hidden h-100" id="resCard">
					<div class="card-body">
						<div class="card-body-icon">
							<i class="fa fa-fw fa-comments"></i>
						</div>
						<div class="mr-5" style="font-size: 18px">
							您有<span style="font-size: 36px; color: red">
								${qaArray.size()} </span>筆未回覆留言
						</div>
					</div>
					<a id="qaButton">展開</a>
				</div>
			</div>
			<!-- 其他功能的推播 -->
			<div class="col-xl-3 col-sm-6 mb-3">
				<div class="card text-white bg-success o-hidden h-100">
					<div class="card-body">
						<div class="card-body-icon">
							<i class="fa fa-fw fa-shopping-cart"></i>
						</div>
						<div class="mr-5">123 New Orders!</div>
					</div>
					<a class="card-footer text-white clearfix small z-1" href="#">
						<span class="float-left">View Details</span> <span
						class="float-right"> <i class="fa fa-angle-right"></i>
					</span>
					</a>
				</div>
			</div>
			<div class="col-xl-3 col-sm-6 mb-3">
				<div class="card text-white bg-danger o-hidden h-100">
					<div class="card-body">
						<div class="card-body-icon">
							<i class="fa fa-fw fa-support"></i>
						</div>
						<div class="mr-5">13 New Tickets!</div>
					</div>
					<a class="card-footer text-white clearfix small z-1" href="#">
						<span class="float-left">View Details</span> <span
						class="float-right"> <i class="fa fa-angle-right"></i>
					</span>
					</a>
				</div>
			</div>
		</div>
		<!-- 顯示內容位置 -->
		<div class="row">
			<div id="containDiv" class="col-xl-12 col-sm-6 mb-3">
				<!-- 預約的內容 -->
				<div id="resDiv">
					<table id="resTable" class="table table-hover">
						<thead>
							<tr>
								<th>預約編號</th>
								<th>預約人</th>
								<th>期望時間</th>
								<th>房屋地址</th>
								<th>申請時間</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="resVO" items="${resArray}">
								<tr>
									<td>${resVO.reservationNO}</td>
									<td>${resVO.memberVO.memName}</td>
									<td><button class="btn btn-primary btn-lg" type="button" id="check">查看</button></td>
									<td style="display: none;">${resVO.exceptTime}</td>
									<td>${resVO.houseVO.houseAddr}</td>
									<td>${resVO.applyTime}</td>
									<td><button class="btn btn-primary btn-lg"type="button" id="takeBT">接案</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 預約期望時間的div table -->
				<div id="exceptDiv">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th></th>
								<th>星期一</th>
								<th>星期二</th>
								<th>星期三</th>
								<th>星期四</th>
								<th>星期五</th>
								<th>星期六</th>
								<th>星期日</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>上午</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>下午</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- Q&A -->
				<div id="qaDiv">
					<table id="qaTable" class="table table-hover">
						<thead>
							<tr>
								<th>留言編號</th>
								<th>留言時間</th>
								<th>留言會員</th>
								<th>房屋連結</th>
								<th>留言內容</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="qaVO" items="${qaArray}">
								<tr>
									<td>${qaVO.qaNO}</td>
									<td>${qaVO.qTime}</td>
									<td>${qaVO.memberVO.memName}</td>
									<td><a href="${qaVO.houseVO.houseNO}">${qaVO.houseVO.houseTitle}</a></td>
									<td>${qaVO.qDetail}</td>
									<c:if test="${qaVO.qaType == 1}">
									<td><button type="button" class="btn btn-primary btn-lg" name="answer">回覆</button></td>
									</c:if>
									<c:if test="${qaVO.qaType == 0}">
									<td><button type="button" class="btn btn-primary btn-lg" name="dispatch">派工</button><br/>
										<button type="button" class="btn btn-primary btn-lg" name="answer">回覆</button>
									</td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 回復Q&A表格 -->
				<div id="qaForm" title="回覆會員">
					<form class="form-group"
						action="<%=request.getContextPath()%>/QAndAServlet?mission=update"
						method="POST" id="answerForm">
						留言編號：<input class="form-control"type="text" readonly="readonly" name="qaNO" value=""
							id="qaNO" /> 回覆內容：
						<textarea class="form-control" name="aDetail" style="resize: none;height:100px"></textarea>
						<button class="btn btn-primary" type="button" onclick="check()">提交</button>
						<button class="btn btn-primary" type="button" onclick="cancel()">取消</button>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>