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

<style>
table {
	font-size: 24px;
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
	position: absolute;
}

#qaForm {
	display: none;
}
#resDiv {
	text-align: center;
	display: none;
	float: left;
	position: absolute;
}
#resTable {
	
}
#eventDiv{
	display: none;
	float: left;
	position: absolute;
}
#dispatchDiv{
	display: none;
	float: left;
	position: absolute;
}
</style>
</head>
<body>
	<!-- 載入框架 -->
	<jsp:include page="/fragment/back_side_page.jsp" />
	<!-- 取得員工編號 -->
	<input type="hidden" id="empNO" value="${empLoginOK.empNO}" />


	<!-- bootstrap -->

	<div class="container">
		<!-- Breadcrumbs-->

		<h1>HermitのHome</h1>
		<hr>
		<div class="row">
			<!-- 預約功能-->
			<div class="col-md-3">
				<div class="card card-inverse" style="border-radius: 10px;background-color: #00BBFF">
				<div class="card-block">
					<h3 class="card-title">您有<span style="font-size: 36px; color: red"> ${resSize} </span>筆未處理預約</h3>
					<a href="#" class="btn btn-primary" id="resButton">展開</a>
				</div>
				</div>
			</div>
		
			<!-- Q&A推播 -->
			<div class="col-md-3">
				<div class="card card-inverse"
				 style="border-radius: 10px;background-color: #00DDAA">
				<div class="card-block">
					<h3 class="card-title">您有<span style="font-size: 36px; color: red"> ${qaArray.size()} </span>筆未回覆留言</h3>
					<a href="#" class="btn btn-primary" id="qaButton">展開</a>
				</div>
				</div>
			</div>
			<!-- 取消預約申請的推播 -->
			<div class="col-md-3">
				<div class="card card-inverse"
				 style="border-radius: 10px;background-color: #FF8000">
				<div class="card-block">
					<h3 class="card-title">您有<span style="font-size: 36px; color: red"> ${eventArray.size()} </span>筆申請取消預約</h3>
					<a href="#" class="btn btn-primary" id="eventButton">展開</a>
				</div>
				</div>
			</div>
			<!-- 派工未處理的推播 -->
			<div class="col-md-3">
				<div class="card card-inverse"
				 style="border-radius: 10px;background-color: white">
				<div class="card-block">
					<h3 class="card-title">您有<span style="font-size: 36px; color: red"> ${dispatchArray.size()} </span>筆派工未處理</h3>
					<a href="#" class="btn btn-primary" id="dispatchButton">展開</a>
				</div>
				</div>
			</div>
		</div>
		<!-- 顯示內容位置 -->
		<div class="row">
			<div id="containDiv" class="col-md-12">
				<!-- 預約的內容 -->
				<div id="resDiv">
					<table id="resTable" class="table table-hover">
						<thead>
							<tr>
								<th style="width:15%">預約編號</th>
								<th style="width:15%">預約人</th>
								<th style="width:15%">期望時間</th>
								<th style="width:40%">房屋地址</th>
								<th style="width:15%">申請時間</th>
							</tr>
						</thead>
						<tbody style="background-color:rgba(255, 255, 255, 0.6)">
							<c:forEach var="resVO" items="${resArray}">
								<tr>
									<td>${resVO.reservationNO}</td>
									<td style="display: none;">${resVO.memberVO.memNO}</td>
									<td>${resVO.memberVO.memName}</td>
									<td><button class="btn btn-primary btn-lg" type="button"
											id="check">查看</button></td>
									<td style="display: none;">${resVO.exceptTime}</td>
									<td style="display: none;">${resVO.houseVO.houseNO}</td>
									<td><a href="<%=request.getContextPath()%>/HousePage?NO=${resVO.houseVO.houseNO}">${resVO.houseVO.houseAddr}</a></td>
									<td>${resVO.applyTime}</td>
									<td><button class="btn btn-primary btn-lg" type="button"
											id="takeBT">接案</button></td>
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
					<table id="qaTable" class="table table-hover" style="table-layout:fixed;">
						<thead>
							<tr>
								<th>留言編號</th>
								<th>留言時間</th>
								<th>留言會員</th>
								<th>房屋連結</th>
								<th width="200px">留言內容</th>
							</tr>
						</thead>
						<tbody style="background-color:rgba(255, 255, 255, 0.6)">
							<c:forEach var="qaVO" items="${qaArray}">
								<tr>
									<td style="width:15%">${qaVO.qaNO}</td>
									<td style="width:15%">${qaVO.qTime}</td>
									<td style="width:15%">${qaVO.memberVO.memName}</td>
									<td style="display:none">${qaVO.memberVO.memNO}</td>
									<td style="display:none">${qaVO.houseVO.houseNO}</td>
									<td style="width:15%"><a href="<%=request.getContextPath()%>/HousePage?NO=${qaVO.houseVO.houseNO}">${qaVO.houseVO.houseTitle}</a></td>
									<c:if test = "${qaVO.qDetail.length() > 5}">
									<td style="width:30%;word-break:break-all;">${qaVO.qDetail.substring(0,5)}...<br/><a><span style="font-size:10px;float:right">[查看更多]</span></a></td>
									<td style="width:30%;word-break:break-all;display:none">${qaVO.qDetail}<br/><a><span style="font-size:10px;float:right">[收起]</span></a></td>
									</c:if>
									<c:if test = "${qaVO.qDetail.length() <= 5}">
									<td style="width:30%;word-break:break-all;">${qaVO.qDetail}</td>
									</c:if>
									<c:if test="${qaVO.qaType == 1}">
										<td style="width:10%"><button type="button" class="btn btn-primary btn-lg"
												name="answer">回覆</button></td>
									</c:if>
									<c:if test="${qaVO.qaType == 0}">
										<td style="width:10%"><button type="button" class="btn btn-primary btn-lg"
												name="dispatch">派工</button>
											<br />
											<button type="button" class="btn btn-primary btn-lg"
												name="answer">回覆</button></td>
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
						留言編號：<input class="form-control" type="text" readonly="readonly"
							name="qaNO" value="" id="qaNO" /> 回覆內容：
						<textarea class="form-control" name="aDetail" id="aDetail"
							style="resize: none; height: 100px"></textarea>
						<button class="btn btn-primary" type="button" onclick="check()">提交</button>
						<button class="btn btn-primary" type="button" onclick="cancel()">取消</button>
					</form>
				</div>
				
				<!-- 取消預約 -->
				<div id="eventDiv">
					<table id="eventTable" class="table table-hover">
						<thead>
							<tr>
								<th style="width:15%">預約編號</th>
								<th style="width:15%">預約會員</th>
								<th style="width:15%">房屋連結</th>
								<th style="width:55%">取消事由</th>
								<th></th>
							</tr>
						</thead>
						<tbody style="background-color:rgba(255, 255, 255, 0.6)">
							<c:forEach var="eventVO" items="${eventArray}">
								<tr>
									<td>${eventVO.eventNO}</td>
									<td>${eventVO.memberVO.memName}</td>
									<td><a href="<%=request.getContextPath()%>/HousePage?NO=${eventVO.houseVO.houseNO}">${eventVO.houseVO.houseTitle}</a></td>
									<c:if test="${eventVO.ps.length()>30}">
									<td style="word-break:break-all;">${eventVO.ps.substring(0,30)}...<br/><a><span style="font-size:10px;float:right">[查看更多]</span></a></td>
									<td style="word-break:break-all;display:none;">${eventVO.ps}<a><span style="font-size:10px;float:right">[收起]</span></a></td>
									</c:if>
									<c:if test="${eventVO.ps.length()<=30}">
										<td style="word-break:break-all;">${eventVO.ps}</td>
									</c:if>
										<td><button type="button" class="btn btn-primary btn-lg"
												name="known">我知道了</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 取消預約end -->
				<!-- 未處理派工單 -->
				<div id="dispatchDiv">
					<table id="dispatchTable" class="table table-hover">
						<thead>
							<tr>
								<th style="width:15%">派工編號</th>
								<th style="width:15%">申請會員</th>
								<th style="width:15%">申請房屋</th>
								<th style="width:15%">申請時間</th>
								<th style="width:15%">派工人員</th>
								<th style="width:25%">派工備註</th>
							</tr>
						</thead>
						<tbody  style="background-color:rgba(255, 255, 255, 0.6)">
							<c:forEach var="dispatchVO" items="${dispatchArray}">
								<tr>
									<td>${dispatchVO.dlNO}</td>
									<td>${dispatchVO.qaVO.memberVO.memName}</td>
									<td><a href="<%=request.getContextPath()%>/HousePage?NO=${dispatchVO.qaVO.houseVO.houseNO}">${dispatchVO.qaVO.houseVO.houseTitle}</a></td>
									<td>${dispatchVO.dlStime}</td>
									<td>${dispatchVO.dempVO.empName}</td>
									<c:if test="${dispatchVO.dlNote.length()<=7}">
									<td>${dispatchVO.dlNote}</td>
									</c:if>
									<c:if test="${dispatchVO.dlNote.length()>7}">
									<td>${dispatchVO.dlNote.substring(0,7)}...<br/><a><span style="font-size:10px;float:right">[查看更多]</span></a></td>
									<td style="display:none">${dispatchVO.dlNote}<br/><a><span style="font-size:10px;float:right">[收起內容]</span></a></td>
									</c:if>
									<td><button class="btn btn-primary btn-lg" type="button"
											id="dispatchBT">前往處理此派工</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 租賃到期 END-->
			</div>
		</div>
	</div>
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
		var eventDiv = $("#eventDiv"); //取消預約的div
		var dispatchDiv = $("#dispatchDiv"); //租賃到期的div
		//展開預約
		$("#resButton").on("click",function(){
			var resDiv = $("#resDiv");
			if($(this).text() == "展開"){
				$("#eventButton").text("展開");
				$("#qaButton").text("展開");
				$("#dispatchButton").text("展開");
				$(this).text("收起");
				eventDiv.hide("drop",1000);
				qaDiv.hide("drop",1000);
				dispatchDiv.hide("drop",1000);
				resDiv.show("drop",1000);
			}else{
				$(this).text("展開");
				resDiv.hide("drop",1000);
			}
		});
		//展開Q&A
		$("#qaButton").on("click",function(){
			
			if($(this).text() == "展開"){
				$("#eventButton").text("展開");
				$("#resButton").text("展開");
				$("#dispatchButton").text("展開");
				$(this).text("收起");
				eventDiv.hide("drop",1000);
				resDiv.hide("drop",1000);
				dispatchDiv.hide("drop",1000);
				qaDiv.show("drop",1000);
			}else{
				$(this).text("展開");
				qaDiv.hide("drop",1000);
			}
		});
		//展開取消預約
		$("#eventButton").on("click",function(){
			if($(this).text() == "展開"){
				$("#qaButton").text("展開");
				$("#resButton").text("展開");
				$("#dispatchButton").text("展開");
				$(this).text("收起");
				resDiv.hide("drop",1000);
				qaDiv.hide("drop",1000);
				dispatchDiv.hide("drop",1000);
				eventDiv.show("drop",1000);
			}else{
				$(this).text("展開");
				eventDiv.hide("drop",1000);
			};
		});
		//展開租賃到期
		$("#dispatchButton").on("click",function(){
			if($(this).text() == "展開"){
				$("#qaButton").text("展開");
				$("#eventButton").text("展開");
				$("#resButton").text("展開");
				$(this).text("收起");
				resDiv.hide("drop",1000);
				qaDiv.hide("drop",1000);
				eventDiv.hide("drop",1000);
				dispatchDiv.show("drop",1000);
			}else{
				$(this).text("展開");
				dispatchDiv.hide("drop",1000);
			};
		});
		
// 		//設定table
// 		var tds = $("#qaTable>tbody td");
// 		$(tds[0]).css("width","15%");
// 		$(tds[1]).css("width","15%");
// 		$(tds[2]).css("width","15%");
// 		$(tds[3]).css("width","15%");
// 		$(tds[4]).css("width","30%");
// 		$(tds[5]).css("width","10%");
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
				var memNO = $(this).parents("tr").find("td").eq(3).text();
				var houseNO = $(this).parents("tr").find("td").eq(4).text();
				window.location = "<%=request.getContextPath()%>/DispatchList/InsertDispatchList.jsp?qaNO="+number+"&memNO="+memNO+"&houseNO="+houseNO;
			}
		});
		//設定期望時間內容
		var button1 = $("#resTable>tbody button:even").on("click",function(){
			var exceptTime = $(this).parents("tr").find("td").eq(4).text();
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
				var memNO = $($(this).parents("tr").find("td")[1]).text();
				var memName = $($(this).parents("tr").find("td")[2]).text();
				var houseNO = $($(this).parents("tr").find("td")[5]).text();
				var houseAddr = $($(this).parents("tr").find("td")[6]).children("a").text();
				$.post("<%=request.getContextPath()%>/mentionServlet?mission=takeOver",{resNO:resNO,empNO:empNO},function(data){
					alert(data);
					window.location = "<%=request.getContextPath()%>/calendar/calendar.jsp?mission=mentionInsert&member="+memNO+"&memberName="+memName+"&houseAddr="+houseAddr+"&house="+houseNO;
				});
				$(this).text("已處理").attr("disabled",true);
			}
		});
		//設定取消預約之我知道了的按鈕
		$("#eventTable>tbody button").on("click",function(){
			var eventNO = $($(this).parents("tr").find("td")[0]).text();
			var thisButton = $(this);
			if(confirm("此筆資料將會永久刪除，是否確認?")){
				$.post("<%=request.getContextPath()%>/calendarServlet?mission=delete"
						,{"id":eventNO}
						,function(data){
							alert(data);
							if(data == "刪除成功"){
								thisButton.text("已刪除").attr("disabled",true);
							};
						}
				);
			}
		});
		$("#dispatchTable>tbody button").on("click",function(){
			var dlNO = $(this).parents("tr").children("td").eq(0).text();
			window.location = "<%=request.getContextPath()%>/Dispatch?action=getOneDispatchList&dlno="+dlNO;
		})
		//設定會員回報內容縮減
 		var qDetail7 = $("#qaTable>tbody td:nth-child(7)");
		var qDetail8 = $("#qaTable>tbody td:nth-child(8)");
		qDetail7.find("a").on("click",function(){
			$(this).parents("tr").find("td").eq(6).toggle(false);
			$(this).parents("tr").find("td").eq(7).toggle(true);
		});
		qDetail8.find("a").on("click",function(){
			$(this).parents("tr").find("td").eq(6).toggle(true);
			$(this).parents("tr").find("td").eq(7).toggle(false);
		})
		//設定會員取消預約事由縮減顯示
		var eventPs4 = $("#eventTable>tbody td:nth-child(4)");
		var eventPs5 = $("#eventTable>tbody td:nth-child(5)");
		eventPs4.find("a").on("click",function(){
			$(this).parents("tr").find("td").eq(3).toggle(false);
			$(this).parents("tr").find("td").eq(4).toggle(true);
		});
		eventPs5.find("a").on("click",function(){
			$(this).parents("tr").find("td").eq(3).toggle(true);
			$(this).parents("tr").find("td").eq(4).toggle(false);
		})
		//設定派工備註縮減顯示
		var dispatchPs6 = $("#dispatchTable>tbody td:nth-child(6)");
		var dispatchPs7 = $("#dispatchTable>tbody td:nth-child(7)");
		dispatchPs6.find("a").on("click",function(){
			$(this).parents("tr").find("td").eq(5).toggle(false);
			$(this).parents("tr").find("td").eq(6).toggle(true);
		});
		dispatchPs7.find("a").on("click",function(){
			$(this).parents("tr").find("td").eq(5).toggle(true);
			$(this).parents("tr").find("td").eq(6).toggle(false);
		})
	};
	//回應表單取消按鈕
	function cancel(){
		$("#qaForm").dialog("close");
	}
	//回應表單確認按鈕
	function check(){
		if($("#aDetail").val().trim().length == 0){
			alert("請先輸入回覆內容");
			return;
		}
		if(confirm("是否確認送出此回覆?")){
			$("#qaForm>form")[0].submit();
		}
	}
	//定時取值
	var t = setTimeout(match,3000);
	function match(){
		$.get("<%=request.getContextPath()%>/mentionServlet?mission=match",{empNO:"${empLoginOK.empNO}"},function(data){
			var json = JSON.parse(data);
			if(json["resSize"] != "${resArray.size()}"){
				location.reload();
			}
			if(json["qaSize"] != "${qaArray.size()}"){
				location.reload();
			}
			if(json["eventSize"] != "${eventArray.size()}"){
				location.reload();
			}
			if(json["dispatchSize"] != "${dispatchArray.size()}"){
				location.reload();
			}
		});
		setTimeout(match,3000);
	}
	
</script>
</body>
</html>