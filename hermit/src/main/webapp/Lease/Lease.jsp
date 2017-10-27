<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../css/datatables.min.css"/>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/iEdit.min.css">




</head>
<body>
<table id="leaseTable">
	<thead>
		<tr>
			<th>合約編號</th>
			<th>房屋編號</th>
			<th>合約起始日</th>
			<th>合約到期日</th>
			<th>承租會員編號</th>
			<th>簽約員工編號</th>
			<th>租金</th>
			<th>押金</th>
			<th>折扣</th>
			<th>簽約日期</th>
			<th>備註</th>
			<th>押金返還</th>
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
			<th>承租會員編號</th>
			<th>簽約員工編號</th>
			<th>租金</th>
			<th>押金</th>
			<th>折扣</th>
			<th>簽約日期</th>
			<th>備註</th>
			<th>押金返還</th>
		</tr>
	</tfoot>
</table>
<form action="/hermit/LeaseServlet.do" method="get" id="modify">
<input type="hidden" name="action" value=getOneLease>
<input type="hidden" id="leaseNO" name="leaseNO">
</form>

<form action="<%=request.getContextPath()%>/LeaseServlet.do" method="POST" id="form">
	<table id="addLease">
		<thead>
			<tr>
				<th>房屋編號</th>
				<th>合約起始日</th>
				<th>合約到期日</th>
				<th>承租會員編號</th>
				<th>簽約員工編號</th>
				<th>租金</th>
				<th>押金</th>
				<th>折扣</th>
				<th>簽約日期</th>
				<th>備註</th>
				<th>押金返還</th>
			</tr>
		</thead>
			<tbody>
				<tr>
					<td><input type="text" style="width:75px" value="${param.houseNO}" name="houseNO"></td>
					<td><input type="date" style="width:100px" value="${param.leaseBeginDate}" name="leaseBeginDate"></td>
					<td><input type="date" style="width:100px" value="${param.leaseEndDate}" name="leaseEndDate"></td>
					<td><input type="text" style="width:75px" value="${param.memNO}" name="memNO"></td>
					<td><input type="text" style="width:75px" value="${param.empNO}" name="empNO"></td>
					<td><input type="text" style="width:75px" value="${param.leaseRent}" name="leaseRent"></td>
					<td><input type="text" style="width:75px" value="${param.leaseDeposit}" name="leaseDeposit"></td>
					<td><input type="text" style="width:75px" value="${param.leaseRelief}" name="leaseRelief"></td>
					<td><input type="date" style="width:100px" value="${param.leaseDate}" name="leaseDate"></td>
					<td><input type="text" style="width:75px" value="${param.houseNote}" name="houseNote"></td>
					<td><select name="leaseRefund" id="selectleaseRefund"><option value=1>是</option><option value=0>否</option></select></td>
					<td><button id="addBtn">新增</button></td>
					<th>合約圖片</th>
					<td><input type="file" style="width:75px" value="${param.leasePic}" id="file"></td>
					<td><input type="hidden" id="leasePic" name="leasePic"></td>
					<td><img id="result" width="75" src="" name="leasePic"></td>
				</tr>
					<input type="hidden" name=action>
			</tbody>		
	</table>
</form>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/flashcanvas.js"></script>
<script src="../js/jSignature.min.js"></script>
<script src="../js/datatables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>		
<script>
$(document).ready(function(){
	var dataJson;
	
	var table=$("#leaseTable");
	var tbody=$("#leaseTable>tbody");
	//查全部合約 ===開始===
	$.post("/hermit/LeaseServlet.do",{action:"getAllLeaseForJson"},function(data){
		console.log(data);
		dataJson=$.parseJSON(data);
// 		console.log(dataJson);
		tbody.empty();
		$.each(dataJson,function(index,VO){
			var cell1=$("<td></td>").text(VO.leaseNO);
			var cell2=$("<td></td>").text(VO.houseNO);
			var cell3=$("<td></td>").text(VO.leaseBeginDate);
			var cell4=$("<td></td>").text(VO.leaseEndDate);
			var cell5=$("<td></td>").text(VO.memNO);
			var cell6=$("<td></td>").text(VO.empNO);
			var cell7=$("<td></td>").text(VO.leaseRent);
			var cell8=$("<td></td>").text(VO.leaseDeposit);
			var cell9=$("<td></td>").text(VO.leaseRelief);
			var cell10=$("<td></td>").text(VO.leaseDate);
			var cell11=$("<td></td>").text(VO.houseNote);
			var cell12=$("<td></td>").text(VO.leaseRefund);
			    cell12.attr("class","Refund");
			var cell13=$("<td></td>").html('<button class="btn btn-primary">修改</button>');
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
		console.log($(Ref).text());
		console.log(index);//告訴你到第幾個迴圈了
		if($(Ref).text()=="0"){
			$(Ref).text("否");
		}else if($(Ref).text()=="1"){
			$(Ref).text("是");
		}
		})
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
		  console.log($("#result").attr("src"));
	});
})
</script>
</body>
</html>