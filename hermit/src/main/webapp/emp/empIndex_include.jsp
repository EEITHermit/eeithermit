<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.btn {
    border: none;
    color: white;
    padding: 14px 28px;
    font-size: 16px;
    cursor: pointer;
}

img {
	width:150px;
	height:150px;
	padding: 5px 5px 5px 5px;
}

td {
	width:450px;
	height:150px;
}

th {
	width:300px;
	height:100px;
}

tr {
	width:200px;
	height:100px;
}

.success {background-color: #4CAF50;} /* Green */
.success:hover {background-color: #46a049;}

.info {background-color: #2196F3;} /* Blue */
.info:hover {background: #0b7dda;}

.warning {background-color: #ff9800;} /* Orange */
.warning:hover {background: #e68a00;}

.danger {background-color: #f44336;} /* Red */ 
.danger:hover {background: #da190b;}

.default {background-color: #e7e7e7; color: black;} /* Gray */ 
.default:hover {background: #ddd;}
/* 按鈕樣式 */
</style>
<head>
<meta charset="UTF-8">
<title>員工資料管理</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<jsp:include page="/fragment/back_side_page.jsp" />
<%-- <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/js/bootstrap.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/js/flashcanvas.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/js/jSignature.min.js"></script> --%>
<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
</head>
<body>
<h1 style="text-align: center;margin-bottom:50px;margin-left:70px;margin-top:0px;">員工資料管理</h1>
<div class="container" >
<button class="btn info" onclick="javascrtpt:window.location.href='empInsert_include.jsp'">新增員工資料</button><p>
	<table id="myTable"style="width:1130px;height:600px;" border="1">
		<thead>
			<tr>
				<th>員工編號</th>
				<th>員工帳號</th>
<!-- 				<th>員工密碼</th> -->
				<th>員工電話</th>
				<th>員工姓名</th>
				<th>職位</th>
				<th>員工狀態</th>
				<th>編輯</th>
			</tr>
		</thead>
		<tbody></tbody>
		<tfoot>
			<tr>
				<th>員工編號</th>
				<th>員工帳號</th>
<!-- 				<th>員工密碼</th> -->
				<th>員工電話</th>
				<th>員工姓名</th>
				<th>職位</th>
				<th>員工狀態</th>
				<th>編輯</th>
			</tr>
		</tfoot>
	</table>
	<form id="modify" method="post" action="EmpServlet">	
		<input type="hidden" name="action" value="getOneEmp">
		<input type="hidden" id="empNO" name="empNO" >
	</form>
</div>
<!-- <script type="text/javascript"> -->
// 	function setFontSize(){
// 		document.getElementById("p1").style.fontSize="larger";
// 	}
<!-- </script> -->
<script> 
	$(document).ready(function(){
		var dataJson;
		
		var table = $("#myTable");
		var tbody = $("#myTable>tbody");
		
		function ajaxPost(){
			$.post("../emp/EmpServlet",{"action":"getAllEmpJson"},function(data){
 				console.log("000000000="+(data));
				dataJson = JSON.parse(data);//$.parseJSON(data);
 				console.log(dataJson);
 				
// 					console.dir(data["list"]);
				tbody.empty();
				$.each(dataJson["list"],function(index,VO){
					
					var cell1 = $("<td></td>").text(VO.empNO);
					var cell2 = $("<td></td>").text(VO.empAccount);
// 					var cell3 = $("<td></td>").text(VO.empPwd);
					var cell4 = $("<td></td>").text(VO.empPhone);
					var cell5 = $("<td></td>").text(VO.empName);
					var cell6 = $("<td></td>").text(VO.postNO);
					var cell7 = $("<td></td>").text(VO.empStatus == true ? "在職" : "停用");
					var cell8 = $("<td></td>").html('<button class="btn btn-primary" onclick="toUpdate('+VO.empNO+');">修改</button>  <button class="btn btn-danger" onclick="javascrtpt:window.location.reload()">刪除</button>');
					var row = $("<tr></tr>").append([cell1,cell2,cell4,cell5,cell6,cell7,cell8]);
					tbody.append(row);
				})
// 				刪除
				$(".btn-danger").on("click",function(){
					var tr = $(this).parents("tr");
					var empNO = $(this).parents("tr").children("td:eq(0)").text();
					if (confirm("您確定要刪除?") == true) {
						$.get("../emp/EmpServlet",{"action":"deleteEmp","empNO":empNO},function(){
							window.location.reload();
						})  
						
					} else {
						
					}
				})
// 				修改
				$(".btn-primary").on("click",function(){
					var tr = $(this).parents("tr");
					var empNO = $(this).parents("tr").children("td:eq(0)").text();
					$("#empNO").val(empNO);
					$("#modify").submit();
				})
				
				$("#myTable").DataTable({
					columnDefs: [{ width: 200, targets: 6 }],
					 "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
					 "pagingType": "full_numbers",
					 "language": {
				            "lengthMenu": "每一頁顯示 _MENU_ 筆資料",
				            "zeroRecords": "查無資料",
				            "info": "現在正在第 _PAGE_ 頁，總共有 _PAGES_ 頁",
				            "infoEmpty": "無資料",
				            "infoFiltered": "(總共搜尋了 _MAX_  筆資料)",
				            "search" : "搜尋：",
				            "paginate": {
								"first":"第一頁",
				                "previous": "上一頁",
				                "next" : "下一頁",
				                "last":"最末頁"
				              }
				        }
				        
				})
				
			})
		}
		ajaxPost();

	});
	
	function toUpdate(id){
		
		$("#empNO").val(id);
		$("#modify").submit();
	}
	
</script>
</body>
</html>