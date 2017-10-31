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
<title>廣告輪播管理</title>
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
<div class="container" >
<button class="btn info" onclick="javascrtpt:window.location.href='adInsert_include.jsp'">新增廣告</button><p>
	<table id="myTable"style="width:1130px;height:600px;" border="1">
		<thead>
			<tr>
				<th>廣告編號</th>
				<th>廣告圖片</th>
				<th>連結網址</th>
				<th>廣告訊息</th>
				<th>上架日期</th>
				<th>下架日期</th>
				<th>狀態</th>
				<th>瀏覽次數</th>
				<th>修改人員編號</th>
				<th>編輯</th>
			</tr>
		</thead>
		<tbody></tbody>
		<tfoot>
			<tr>
				<th>廣告編號</th>
				<th>廣告圖片</th>
				<th>連結網址</th>
				<th>廣告訊息</th>
				<th>上架日期</th>
				<th>下架日期</th>
				<th>狀態</th>
				<th>瀏覽次數</th>
				<th>修改人員編號</th>
				<th>編輯</th>
			</tr>
		</tfoot>
	</table>
	<form id="modify" method="get" action="../ADManagerServlet">	
		<input type="hidden" name="action" value="getOneADManager">
		<input type="hidden" id="adNO" name="adNO" >
	</form>
</div>
<script> 
	$(document).ready(function(){
		var dataJson;
		
		var table = $("#myTable");
		var tbody = $("#myTable>tbody");
		
		function ajaxPost(){
			$.post("../ADManagerServlet",{"action":"getAllADManagerForJson"},function(data){
				dataJson = $.parseJSON(data).list;
//  				console.log(data);
//  				console.log(dataJson);
				tbody.empty();
				$.each(dataJson,function(index,VO){
					var cell1 = $("<td></td>").text(VO.adNO);
					var img1 = $("<img>").attr("src",VO.adImage);//1
					var cell2 = $("<td></td>").append(img1)//2
					var cell3 = $("<td></td>").text(VO.adLink);
					var cell4 = $("<td></td>").text(VO.adMessage);
					var cell5 = $("<td></td>").text(VO.adTimeStart);
					var cell6 = $("<td></td>").text(VO.adTimeEnd);
					var cell7 = $("<td></td>").text(VO.adStatus);
					var cell8 = $("<td></td>").text(VO.adBrowse);
					var cell9 = $("<td></td>").text(VO.adModify);
					var cell10 = $("<td></td>").html('<button class="btn btn-primary" onclick="javascrtpt:window.location.reload("adUpdate_include.jsp")">修改</button>  <button class="btn btn-danger" onclick="javascrtpt:window.location.reload()">刪除</button>');
					var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10]);
					tbody.append(row);
				})
// 				刪除
				$(".btn-danger").on("click",function(){
					var tr = $(this).parents("tr");
					var adNO = $(this).parents("tr").children("td:eq(0)").text();
					if (confirm("您確定要刪除?") == true) {
						$.get("../ADManagerServlet",{"action":"deleteADManager","adNO":adNO},function(){
							window.location.reload();
						})  
						
					} else {
						
					}
				})
// 				修改
				$(".btn-primary").on("click",function(){
					var tr = $(this).parents("tr");
					var adNO = $(this).parents("tr").children("td:eq(0)").text();
					$("#adNO").val(adNO);
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
	</script>
</body>
</html>