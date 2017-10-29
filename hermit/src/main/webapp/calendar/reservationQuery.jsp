<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>預約查詢</title>
<link href='<%= request.getContextPath() %>/css/jquery-ui.min.css' rel='stylesheet' />
<script src='<%= request.getContextPath() %>/js/jquery.min.js'></script>
<script>
	$("document").ready(work);
	
	function work(){
		var body = $("#showTable>tbody");
		$("#btQuery").on("click",query);
		function query(){
			var array;
			body.text("");
			//以會員id搜尋預約
			$.get("<%= request.getContextPath() %>/reservationServlet?mission=queryReservation",{memberNo:"40001"},function(data){
				array = JSON.parse(data);
				for(var res of array){
					var tr = $("<tr></tr>");
					var tdId = $("<td>"+res["eventNO"]+"</td>");
					var tdName = $("<td>"+res["empVO"]["empName"]+"</td>");
					var tdTitle = $("<td>"+res["houseVO"]["houseTitle"]+"</td>");
					var tdAddr = $("<td>"+res["houseVO"]["houseAddr"]+"</td>");
					var tdTel = $("<td>"+res["empVO"]["empPhone"]+"</td>");
					var tdStart = $("<td>"+res["eventStartTime"]+"</td>");
					var button = $("<button>刪除</button>");
					tr.append(tdId).append(tdName).append(tdTitle).append(tdAddr).append(tdTel).append(tdStart).append(button);
					tr.appendTo(body);
				};
			});
			
		}
		//會員刪除預約
		body.on("click","button",function(event){
			if(confirm("確定要刪除此預約?")){
				$.post("<%= request.getContextPath() %>/calendarServlet?mission=delete"
						,{id:$(event.target).parent("tr").children("td").eq(0).text()}
						,function(data){
							alert(data);
							query();  //重新查詢
						})
			}
		})
	}
</script>
<style>
	table{
		border-collapse:collapse;
	}
	th{
		border:3px blue double;
	}
	td{
		border:3px blue double;
	}

</style>
</head>
<body>
	<button type="button" id="btQuery">查詢預約</button>
	<div id="show" style="margin-top:50px">
		<table id="showTable">
			<thead>
				<tr>
					<th>預約編號</th>
					<th>業務姓名</th>
					<th>房屋名稱</th>
					<th>房屋住址</th>
					<th>業務電話</th>
					<th>預約時間</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</body>
</html>