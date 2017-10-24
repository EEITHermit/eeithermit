<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查詢全部</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/flashcanvas.js"></script>
<script src="<%=request.getContextPath()%>/js/jSignature.min.js"></script>
<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
</head>
<body>
<div class="container">
	<table id="myTable">
		<thead>
			<tr>
				<th>派工單號</th>
				<th>指派人</th>
				<th>負責人</th>
				<th>問答編號</th>
				<th>派工時間</th>
				<th>結束時間</th>
				<th>備註</th>
				<th>編輯</th>
			</tr>
		</thead>
		<tbody></tbody>
		<tfoot>
			<tr>
				<th>派工單號</th>
				<th>指派人</th>
				<th>負責人</th>
				<th>問答編號</th>
				<th>派工時間</th>
				<th>結束時間</th>
				<th>備註</th>
				<th>編輯</th>
			</tr>
		</tfoot>
	</table>
	<form id="modify" method="get" action="DispatchListServlet">	
		<input type="hidden" name="action" value="getOneDispatchList">
		<input type="hidden" id="dlno" name="dlno" >
	</form>
</div>
	<script>
	$(document).ready(function() {
		var dataJson;
		
		var table = $("#myTable");
		var tbody = $("#myTable>tbody");
		function ajaxPost(){
				$.post("DispatchListServlet",{"action":"getAllDispatchListForJson"},function(data){
					dataJson = $.parseJSON(data).list;
					console.log(data);
					console.log(dataJson);
					tbody.empty();
					$.each(dataJson,function(index,VO){
						var cell1 = $("<td></td>").text(VO.dlNO);
						var cell2 = $("<td></td>").text(VO.dempNO);
						var cell3 = $("<td></td>").text(VO.aempNO);
						var cell4 = $("<td></td>").text(VO.qaNO);
						var cell5 = $("<td></td>").text(VO.dlStime);
						var cell6 = $("<td></td>").text(VO.dlEtime);
						var cell7 = $("<td></td>").text(VO.dlNote);
						var cell8 = $("<td></td>").html('<button class="btn btn-primary">修改</button>  <button class="btn btn-danger">刪除</button>');
						var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8]);
						tbody.append(row);
					})
				$(".btn-danger").on("click",function(){
					var tr = $(this).parents("tr");
					var dlno = $(this).parents("tr").children("td:eq(0)").text();
					if (confirm("您確定要刪除?") == true) {
						$.get("DispatchListServlet",{"action":"deleteDispatchList","dlno":dlno},function(){
							window.location.reload();
						})  
					} else {
						
					}
				})
				
				$(".btn-primary").on("click",function(){
					var tr = $(this).parents("tr");
					var dlno = $(this).parents("tr").children("td:eq(0)").text();
					$("#dlno").val(dlno);
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