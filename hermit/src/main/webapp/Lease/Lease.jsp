<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/iEdit.min.css">
<!-- 合約表格用↓ -->
<link rel="stylesheet" 
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tmplt-default.css">
<!-- 合約表格用↑ class="table"	 -->
</head>
<style>
textarea{
    padding: 20px; 
    width: 280px;
    resize: none;
    overflow: auto;
}
.all-fonts{
font-family:Microsoft JhengHei;
font-size: 16px;
}
span{
	
}
</style>
<body>
	<!-- 載入框架 -->
	<jsp:include page="/fragment/back_side_page.jsp" />
	<!-- bootstrap -->
<div class="container col-md-10 col-md-offset-1">
<div class="all-fonts">
<table id="leaseTable" class="table">
	<thead>
		<tr>
			<th>合約編號</th>
			<th>房屋編號</th>
			<th>合約起始日</th>
			<th>合約到期日</th>
			<th>承租會員</th>
			<th>簽約員工</th>
			<th>租金</th>
			<th>押金</th>
			<th>折扣</th>
			<th>簽約日期</th>
			<th>備註</th>
			<th>押金返還</th>
			<th>編輯</th>
		</tr>
	</thead>
	<tbody>
	
	</tbody>
	<tfoot>
		<tr>
			<th>合約編號</th>
			<th>房屋編號</th>
			<th>合約起始日</th>
			<th>合約到期日</th>
			<th>承租會員</th>
			<th>簽約員工</th>
			<th>租金</th>
			<th>押金</th>
			<th>折扣</th>
			<th>簽約日期</th>
			<th>備註</th>
			<th>押金返還</th>
			<th>編輯</th>
		</tr>
	</tfoot>
</table>
<form action="/hermit/LeaseServlet.do" method="get" id="modify">
<input type="hidden" name="action" value=getOneLease>
<input type="hidden" id="leaseNO" name="leaseNO">
</form>

<form action="<%=request.getContextPath()%>/LeaseServlet.do" method="POST" id="form">
	<table id="addLease" class="table">
		<thead>
			<tr>
				<th>房屋編號</th>
				<th>合約起始日</th>
				<th>合約到期日</th>
				<th>承租會員</th>
				<th>簽約員工</th>
				<th>租金</th>
				<th>押金</th>
				<th>折扣</th>
				<th>簽約日期</th>
				<th>備註</th>
				<th>押金返還</th>
				<th>編輯</th>
			</tr>
		</thead>
			<tbody>
				<tr>
					<td><input type="text" style="width:70px" value="${param.houseNO}" name="houseNO" class="form-control" id="houseNO"><span>${ErrorMsgKey.houseNO}</span></td>
					<td><input type="date" style="width:140px" value="${param.leaseBeginDate}" name="leaseBeginDate" class="form-control"></td>
					<td><input type="date" style="width:140px" value="${param.leaseEndDate}" name="leaseEndDate" class="form-control"></td>
					<td><input type="text" style="width:70px" value="${param.memNO}" name="memNO" class="form-control" id="memNO"><span id="memName"></span></td>
					<td><input type="text" style="width:70px" value="${param.empNO}" name="empNO" class="form-control" id="empNO"><span id="empName"></span></td>
					<td><input type="text" style="width:90px" value="${param.leaseRent}" name="leaseRent" class="form-control" id="leaseRent" onkeypress='return event.charCode >= 48 && event.charCode <= 57'></td>
					<td><input type="text" style="width:90px" value="${param.leaseDeposit}" name="leaseDeposit" class="form-control" id="leaseDeposit" onkeypress='return event.charCode >= 48 && event.charCode <= 57'></td>
					<td><input type="text" style="width:90px" value="${param.leaseRelief}" name="leaseRelief" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57'></td>
					<td><input type="date" style="width:140px" value="${param.leaseDate}" name="leaseDate" class="form-control"></td>
					<td><textarea name="houseNote"></textarea></td>
<%-- 					<td><input type="text" style="width:70px" value="${param.houseNote}" name="houseNote"></td> --%>
					<td><select name="leaseRefund" id="selectleaseRefund"><option value=1>是</option><option value=0>否</option></select></td>
					<td><button id="addBtn" class="btn">新增</button></td>
				</tr>
			</tbody>		
	</table>
	<table class="table">
				<tr>
					<th>合約圖片
					<input type="file" style="width:80px" value="${param.leasePic}" id="file">
					<input type="hidden" id="leasePic" name="leasePic">
					<img id="result" width="200" src="" name="leasePic">
					</th>
				</tr>
					<input type="hidden" name=action>
	</table>
</form>
</div>
</div>
<%-- <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script> --%>
<script src="../js/bootstrap.js"></script>
<script src="../js/flashcanvas.js"></script>
<script src="../js/jSignature.min.js"></script>
<script src="../js/datatables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
<script src="<%=request.getContextPath()%>/js/ajsr-jq-confirm.js"></script>
		
<script>
$(document).ready(function(){
	var dataJson;
	
	var table=$("#leaseTable");
	var tbody=$("#leaseTable>tbody");
	//查全部合約 ===開始===
	$.post("/hermit/LeaseServlet.do",{action:"getAllLeaseForJson"},function(data){
// 		console.log(data);
		dataJson=$.parseJSON(data);
// 		console.log(dataJson);
		tbody.empty();
		$.each(dataJson,function(index,VO){
			var cell1=$("<td></td>").text(VO.leaseNO);
			var cell2=$("<td></td>").text(VO.houseNO);
			var cell3=$("<td></td>").text(VO.leaseBeginDate);
			var cell4=$("<td></td>").text(VO.leaseEndDate);
			var cell5=$("<td></td>").text(VO.memName);
			var cell6=$("<td></td>").text(VO.empName);
			var cell7=$("<td></td>").text(VO.leaseRent);
			var cell8=$("<td></td>").text(VO.leaseDeposit);
			var cell9=$("<td></td>").text(VO.leaseRelief);
			var cell10=$("<td></td>").text(VO.leaseDate);
			var cell11=$("<td></td>").text(VO.houseNote);
			var cell12=$("<td></td>").text(VO.leaseRefund);
			    cell12.attr("class","Refund");
			var cell13=$("<td></td>").html('<button class="btn btn-primary">查看</button>');
			var row=$("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5
				,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13,
				]);
			tbody.append(row);
		})
		$(".btn-primary").on("click",function(){
			var tr=$(this).parents("tr");
			var leaseNO=$(this).parents("tr").children("td:eq(0)").text();
			$("#leaseNO").val(leaseNO);
			$("#modify").submit();
		})
		var Refund=$(".Refund");
		$.each(Refund,function(index,Ref){
// 		console.log($(Ref).text());
// 		console.log(index);//告訴你到第幾個迴圈了
		if($(Ref).text()=="0"){
			$(Ref).text("否");
		}else if($(Ref).text()=="1"){
			$(Ref).text("是");
		}
		})
		//最上面查詢、選擇用
		$('#leaseTable').DataTable( {
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
	        }
		});
	})
	//查全部合約 ===結束===
		
	$("#addBtn").click(function(){
		$('input[name="action"]').val("insertLease");
	})
	
	
	$("#file").change(function(e){
		  var img = e.target.files[0];
		  if(!img.type.match('image.*')){
		    alert("Whoops! That is not an image.");
		    return;
		  }
		  iEdit.open(img, true, function(res){
		    $("#result").attr("src", res);
		  });
		  $("#form").submit(function(event){
			  $("#leasePic").val($("#result").attr("src"));
		  })
// 		  console.log($("#result").attr("src"));
	});
	$("#memNO").change(function(){
		var inputNO=$("#memNO");
		var NO=inputNO.val();
		$.post("<%= request.getContextPath() %>/member.do?action=queryMem",{memNO:NO},function(data){
// 			console.log(data);
			var memName=$("#memName");
			$.ajsrConfirm({
				  message: "你輸入的會員為:\n"+data,
				  okButton: "是",
				  cancelButton : "否",
				  onCancel:function(){
					  $("#memNO").val("");
				  },
			});
		})
	})
	$("#empNO").change(function(){
		var inputNO=$("#empNO");
		var NO=inputNO.val();
		$.post("<%= request.getContextPath() %>/emp/EmpServlet?action=queryEmp",{empNO:NO},function(data){
// 			console.log(data);
			var empName=$("#empName");
			$.ajsrConfirm({
				  message: "你輸入的員工為:\n"+data,
				  okButton: "是",
				  cancelButton : "否",
				  onCancel:function(){
					  $("#empNO").val("");
				  },
			});
		})
	})
	$("#houseNO").change(function(){
		var inputNO=$("#houseNO");
		var NO=inputNO.val();
		var leaseRent=$("#leaseRent");
		var leaseDeposit=$("#leaseDeposit");
		$.post("<%=request.getContextPath()%>/House.do?action=queryStatus",{houseNO:NO},function(Status){
			if(Status=="未出租"){
				$.post("<%=request.getContextPath()%>/House.do?action=queryHouse",{houseNO:NO},function(data){
					var houseAddr=$("#houseAddr");
					$.ajsrConfirm({	
						  message: "你輸入的房屋物件住址為:\n"+data,
						  okButton: "是",
						  cancelButton : "否",
						  onCancel:function(){
							  $("#houseNO").val("");
							  leaseDeposit.val("");
						  },
						  onConfirm: function() {
							  $.post("<%=request.getContextPath()%>/House.do?action=queryRent",{houseNO:NO},function(data){
									leaseRent.val(data);
									leaseDeposit.val(data*2);
								})
						  }
					})
				})
			}else{
				alert("房屋物件為不可出租狀態，請再確認房屋資訊是否正確。");
				 $("#houseNO").val("");
				 $("#leaseRent").val("");
				 leaseDeposit.val("");
			}
		})
	})
})
</script>
</body>
</html>