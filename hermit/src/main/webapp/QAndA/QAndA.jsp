<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A</title>
<link href='<%= request.getContextPath() %>/css/jqueryText/jquery-te-1.4.0.css' rel='stylesheet' />
<link href='<%= request.getContextPath() %>/css/bootstrap.min.css' rel='stylesheet' />
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>
<script src='<%=request.getContextPath()%>/js/jquery-te-1.4.0.min.js'></script>
<script src='<%=request.getContextPath()%>/js/bootstrap.min.js'></script>
<link href="//cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">

<!-- <script type="text/javascript" src="//code.jquery.com/jquery-2.2.4.min.js"></script> -->
<script type="text/javascript" src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<style>
	#queryTable{
 		display:none; 
	}
	#commentForm{
  		display:none;  
	}
</style>
<script>
	function check(){
		if(confirm("是否確認送出投訴")){
			(document.getElementById("commentForm")).submit();
		}
	};
	
	var dlang = {
		    "sProcessing": "Loading...",
		    "sLengthMenu": "每頁顯示 _MENU_ 筆",
		    "sZeroRecords": "沒有符合結果",
		    "sInfo": "從 _START_ 到 _END_ /共 _TOTAL_ 筆",
		    "sInfoEmpty": "無任何資料",
		    "sInfoFiltered": "(過濾總筆數 _MAX_)",
		    "sInfoPostFix": "",
		    "sSearch": "搜索:",
		    "sUrl": "",
		    "sEmptyTable": "無任何資料",
		    "sLoadingRecords": "Loading...",
		    "sInfoThousands": ",",
		    "oPaginate": {
		        "sFirst": "首頁",
		        "sPrevious": "上頁",
		        "sNext": "下頁",
		        "sLast": "末頁",
		        "sJump": "跳頁"
		    },
		    "oAria": {
		        "sSortAscending": ": 以升序排列此列",
		        "sSortDescending": ": 以降序排列此列"
		    }
		};

		$(document).ready(function() {
			//跳出查詢畫面
			$("#queryBT").click(function(){
				$("#queryTable").toggle(true);
				$("#commentForm").toggle(false);
			});
			//跳出投訴頁面
			$("#commentBT").click(function(){
				$("#queryTable").toggle(false);
				$("#commentForm").toggle(true);
			});
			//產生jqueyText
			$('.commentArea').jqte();
			//產生dataTable
			$('#queryTable').DataTable( {
		        "language":dlang,  //提示信息
		         "processing": true,
		        "serverSide": true,
		        //"bSort": false,//是否有排序
		        "pagingType": "full_numbers",  //分页样式： simple,simple_numbers,full,full_numbers
		        "searching": false,  //禁用原生搜索
		        //"orderMulti": true,  //啟用多列排序
		        "deferRender": true,//延遲渲染數據，提高處理能力。用於大數據時。
		        
		        //"destroy" : true,
		        "bFilter" : false,               
		        "bLengthChange": false
		        //"pageLength": 20
		        
		    } );


		});
</script>
</head>
<body>
	<!-- 模式選擇按鈕 -->
	<button id="queryBT">查詢留言</button>
	<button id="commentBT">客服申請</button>
	<!-- 查詢留言區域 -->
	<table id="queryTable">
		<thead>
			<tr>
				<th>留言時間</th><th>留言類型</th><th>房屋連結</th><th>留言內容</th><th>回覆時間</th><th>回覆內容</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="qaVO" items="${array}">
				<tr>
					<td>${qaVO.qTime}</td>
					<c:if test="${qaVO.qaType == 0}">
						<td>客服</td>
					</c:if>
					<c:if test="${qaVO.qaType == 1}">
						<td>詢問</td>
					</c:if>
					<td><a href="${qaVO.houseVO.houseNO}">${qaVO.houseVO.houseTitle}</a></td>
					<td>${qaVO.qDetail}</td>
					<td>${qaVO.aTime}</td>
					<td>${qaVO.aDetail}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 客服申請區域 -->
	<form id="commentForm" action="<%=request.getContextPath()%>/QAndAServlet?mission=insert&type=0" method="POST">
		請選擇房屋：<select name="houseNO">
			<option>請選擇</option>
			<!-- filter會傳來houseArray 為此會員所租賃的房屋 -->
			<c:forEach var="houseVO" items="${houseArray}">
				<option value="${houseVO.houseNO}">${houseVO.houseAddr}</option>
			</c:forEach>
		</select>
		申訴內容：<textarea id="commentArea" class="commentArea" name="qDetail" style="resize:none;"></textarea>
		<input type="button" value="提交" onclick="check()"/>
	</form>
	
</body>
</html>