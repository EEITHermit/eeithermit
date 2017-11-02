<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>員工首頁</title>
<link href='<%=request.getContextPath() %>/css/jquery-ui.min.css' rel='stylesheet' />
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/portfolio-item.css" rel="stylesheet">
<script src='<%= request.getContextPath() %>/js/jquery.min.js'></script>
<script src='<%=request.getContextPath() %>/js/jquery-ui.min.js'></script>

<script>
	document.addEventListener("DOMContentLoaded",work);
	
	function work(){
		//展開預約
		$("#resButton").on("click",function(){
			
			if($(this).text() == "展開"){
				$(this).text("收起");
				$("#resTable").toggle(true,500);
			}else{
				$(this).text("展開");
				$("#resTable").toggle(false,500);
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
</script>
<style>
	#resTable{
		text-align: center;
		display:none;
	}
	#exceptDiv{
		display:none;
		border:1px solid black;
		float:left;	
		position: absolute;
	}
	#exceptDiv table{
		border-collapse:collapse;
		text-align: center;
		background-color:orange;
		}
	#exceptDiv td{
		border:2px solid black;
	}
</style>
</head>
<body>
	<input type="hidden" id="empNO" value="${empLoginOK.empNO}"/>
	<h3>您有 ${resSize} 筆未處理預約  <button type="button" id="resButton">展開</button></h3>
	<table id="resTable">
		<thead>
		<tr>
			<th>預約編號</th>
			<th>預約人</th>
			<th>期望時間</th>
			<th>房屋地址</th>
			<th>預約日期</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="resVO" items="${resArray}">
			<tr>
				<td>${resVO.reservationNo}</td>
				<td>${resVO.memberVO.memName}</td>
				<td><button type="button" id="check">查看</button></td>
				<td style="display:none;">${resVO.exceptTime}</td>
				<td>${resVO.houseVO.houseAddr}</td>
				<td>${resVO.applyTime}</td>
				<td><button type="button" id="takeBT">接案</button></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- 期望時間的div table -->
	<div id="exceptDiv">
		<table>
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
					<td>上午</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td>下午</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>