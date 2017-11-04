<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>黑名單申請</title>
<link href='<%= request.getContextPath() %>/css/jquery-ui.min.css' rel='stylesheet' />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/datatables.min.css" />


<style>
	#queryDiv{
		display:none;
	}
</style>
</head>
<body>
	
	<!-- 載入框架 -->
	<jsp:include page="/fragment/back_side_page.jsp" />
	
	<div class="container">
	<div class="row">
	<h1>黑名單申請單</h1>
	<form class="form-group" style="font-size:20px;">
		會員資料:<input  class="form-control" type="text" id="memNO" name="memNO" value="${param.member}"/><br/>
		<p style="float:left">申請原因:<p><textarea class="form-control" style="resize:none" id="reason" name="reason"></textarea><br/>
		<input   type="hidden" value="${empLoginOK.empNO}" id="empNO">
		<button class="btn btn-primary" type="button" id="bt">提交</button><br/>
		
	</form>
		<p style="color:#97CBFF">___________________________________________________________________________________________________</p><br/>
		<button type="button" class="btn btn-primary btn-lg" id="query">查看黑名單事件</button><br/><br/>
		<div id = "queryDiv">
			<table id="queryTable" class="hover" style="background-color:orange;">
			<thead>
				<tr>
					<th>事件編號</th><th>會員編號</th><th>會員姓名</th><th>申請原由</th><th>申請時間</th><th>申請員工</th>
				</tr>
			</thead>
			<tbody></tbody>
			</table>
		</div>
	</div>
	</div>
<script src='<%= request.getContextPath() %>/js/jquery.min.js'></script>
<script src='<%=request.getContextPath() %>/js/jquery-ui.min.js'></script>
<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
<script>
	document.addEventListener("DOMContentLoaded",work);
	
	function work(){
		$("#memNO").autocomplete({
			source:function(request,response){
				var term = request.term;
				$.get("<%= request.getContextPath() %>/calendarServlet?mission=queryMember"
				,{member:term}
				,function(data){
					response(JSON.parse(data));
				});
			}
		})
		
		$("#bt").on("click",function(){
			var memNO = $("#memNO").val();
			var reason = $("#reason").val();
			//驗證輸入資料
			var error = "";
			var memReg = /[0-9]{5}	.*/;
			if(memNO.trim().length == 0){
				error = error + "請輸入會員資料\n";
			}else if(! memReg.test(memNO.trim())){
				error = error + "輸入格式錯誤\n"
			};
			if(error != ""){
				alert(error);
				return;
			};
			//確認資料無誤後送出資料
			if(confirm("確定送出黑名單申請")){
				$.post("<%= request.getContextPath() %>/infractionServlet?mission=insert"
						,{memNO:memNO,empNO:$("#empNO").val(),reason:reason}
						,function(data){
							alert(data);
							if(data == "申請成功"){
								$("#memNO").val("");
								$("#reason").val("");
							}
						});
			}
		})
		
		$("#query").click(function(){
			$.get("<%=request.getContextPath()%>/infractionServlet"
					,{mission:"query"}
			,function(data){
				var set = JSON.parse(data);
				for(var s of set){
					var inNO = $("<td></td>").append(s.inNO);
					var memNO = $("<td></td>").append(s.memberVO.memNO);
					var memName = $("<td></td>").append(s.memberVO.memName);
					var reason = $("<td></td>").append(s.reason);
					var inDate = $("<td></td>").append(s.inDate);
					var empNO = $("<td></td>").append(s.empNO);
					var tr = $("<tr></tr>").append(inNO).append(memNO).append(memName)
										   .append(reason).append(inDate).append(empNO);
					$("#queryTable>tbody").append(tr);
				}
				$("#queryDiv").toggle(true);
				$("#queryTable").DataTable({
					"language": {
			            "lengthMenu": "每頁顯示 _MENU_ 筆",
			            "zeroRecords": "Nothing found - sorry",
			            "info": "現在正顯示   _PAGE_  共有 _PAGES_ 頁",
			            "infoEmpty": "No records available",
			            "infoFiltered": "(filtered from _MAX_ total records)",
			            "search": "查詢:",
			            "paginate": {
			        		"first":      "首頁",
			        		"last":       "末頁",
			        		"next":       "下頁",
			        		"previous":   "前頁"
			        	}
			        },
			        "distory":true
				});
			});
		});
	};
</script>
</body>
</html>