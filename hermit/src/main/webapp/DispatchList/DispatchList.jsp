<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>維護派工清單</title>
<jsp:include page="/fragment/back_side_page.jsp" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/w3.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<script src="<%=request.getContextPath()%>/js/jSignature.min.js"></script>
<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
<style>
	table td{
		text-overflow:ellipsis;
		overflow:hidden;
		white-space:nowrap;
	}
</style>
</head>
<body>
	<h1 style="text-align: center;margin-bottom:50px;margin-left:70px;margin-top:0px">維護派工清單</h1>
	<div class="container-fluid">
	<div class="col-md-1"></div>
	<div class="col-md-11">
		<table id="dlTable" style="table-layout:fixed;">
			<thead>
				<tr>
					<th>派工單號</th>
					<th>指派人</th>
					<th>受派人</th>
					<th>問答編號</th>
					<th>問答內容</th>
					<th>指派時間</th>
					<th>結束時間</th>
					<th>處理狀況</th>
					<th>備註</th>
					<th>操作編輯</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
			<tfoot>
				<tr>
					<th>派工單號</th>
					<th>指派人</th>
					<th>受派人</th>
					<th>問答編號</th>
					<th>問答內容</th>
					<th>指派時間</th>
					<th>結束時間</th>
					<th>處理狀況</th>
					<th>備註</th>
					<th>操作編輯</th>
				</tr>
			</tfoot>		
		</table>
		</div>
		<form id="modify" method="get" action="<%= request.getContextPath() %>/Dispatch">	
			<input type="hidden" name="action" value="getOneDispatchList">
			<input type="hidden" id="dlno" name="dlno" >
		</form>
		<form id="query" method="get" action="<%= request.getContextPath() %>/Dispatch">	
			<input type="hidden" name="action" value="queryDispatchList">
			<input type="hidden" id="queryDlno" name="dlno" >
		</form>
	</div>
	
	
	<script>
		$(function(){
			var dlTableBody = $("#dlTable>tbody")
			$.post("<%= request.getContextPath() %>/Dispatch",{"action":"getAllDispatchListForJson"},function(data){
				var dlJSON = $.parseJSON(data).list;
				$.each(dlJSON,function(index,dl){
					var dlNO = $("<td></td>").text(dl.dlNO);
					var dEmp = $("<td></td>").text(dl.dempName);
					var aEmp = $("<td></td>").text(dl.aempName);
					var qaNO = $("<td></td>").text(dl.qaNO);
					var qDetail = $("<td></td>").text(dl.qDetail);
					var sTime = $("<td></td>").text(dl.dlStime);
					if(dl.dlEtime != null){
						var eTime = $("<td></td>").text(dl.dlEtime);
						var status = $("<td></td>").text("已處理")
					}else{
						var eTime = $("<td></td>").text("----")
						var status = $("<td></td>").text("未處理")
					}
					if(dl.dlNote != null){
						var dlNote = $("<td></td>").text(dl.dlNote);
					}else{
						var dlNote = $("<td></td>").text("無備註");
					}
					var mod = $("<td></td>").html('<button class="btn btn-info">查詢</button>  <button class="btn btn-primary">修改</button>'); 
					
					// 上面的html加入  <button class="btn btn-danger">刪除</button> 並開啟下面on語法就可以有刪除功能
					var tr = $("<tr></tr");
					tr.append(dlNO,dEmp,aEmp,qaNO,qDetail,sTime,eTime,status,dlNote,mod);
					dlTableBody.append(tr);
				})
// 				$(".btn-danger").on("click",function(){
// 					var tr = $(this).parents("tr");
// 					var dlno = $(this).parents("tr").children("td:eq(0)").text();
// 					if (confirm("您確定要刪除?") == true) {
<%-- 						$.get("<%= request.getContextPath() %>/Dispatch",{"action":"deleteDispatchList","dlno":dlno},function(){ --%>
// 							window.location.reload();
// 						})  
// 					} else {
						
// 					}
// 				})
				
				$(".btn-primary").on("click",function(){
					var tr = $(this).parents("tr");
					var dlno = $(this).parents("tr").children("td:eq(0)").text();
					$("#dlno").val(dlno);
					$("#modify").submit();
				})
				$(".btn-info").on("click",function(){
					var tr = $(this).parents("tr");
					var dlno = $(this).parents("tr").children("td:eq(0)").text();
					$("#queryDlno").val(dlno)
					$("#query").submit();
				})
				
				$("#dlTable").DataTable({
				columnDefs: [{ width: 100, targets: 0 },
					{ width: 60, targets: 1 },
					{ width: 60, targets: 2 },
					{ width: 100,targets: 3 },
					{ width: 100,targets: 4 },
					{ width: 70, targets: 5 },
					{ width: 70, targets: 6 },
					{ width: 70, targets: 7 },
					{ width: 100,targets: 8},
					{ width: 120,targets: 9}],
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
		})
	</script>
</body>
</html>