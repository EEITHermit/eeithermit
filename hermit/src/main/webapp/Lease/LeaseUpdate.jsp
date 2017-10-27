<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/hermit/css/bootstrap.min.css">
<link rel="stylesheet" href="/hermit/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/hermit/css/datatables.min.css"/>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/iEdit.min.css">


</head>
<body>

	<form action="/hermit/LeaseServlet.do?action=update" id="form" method="post">
	<div>
	<lable>合約編號</lable>
	<input type="text" readonly value="${vo.leaseNO}" name="leaseNO">
	</div>
	
	<div>
	<lable>房屋編號</lable>
	<input type="text" value="${vo.houseNO}" name="houseNO">
	</div>
	
	<div>
	<lable>合約起始日期</lable>
	<input type="date" value="${vo.leaseBeginDate}" name="leaseBeginDate">
	</div>
	
	<div>
	<lable>合約結束日期</lable>
	<input type="date" value="${vo.leaseEndDate}" name="leaseEndDate">	
	</div>
	
	<div>
	<lable>承租會員編號</lable>
	<input type="text" value="${vo.memNO}" name="memNO">
	</div>
	
	<div>
	<lable>簽約員工編號</lable>
	<input type="text" value="${vo.empNO}" name="empNO">
	</div>
	
	<div>
	<label>租金</label>
	<input type="text" value="${vo.leaseRent}" name="leaseRent">
	</div>
	
	<div>
	<lable>押金</lable>
	<input type="text" value="${vo.leaseDeposit}" name="leaseDeposit">
	</div>
	
	<div>
	<lable>折扣</lable>
	<input type="text" value="${vo.leaseRelief}" name="leaseRelief">	
	</div>
	
	<div>
	<lable>簽約日起</lable>
	<input type="date" value="${vo.leaseDate}" name="leaseDate">
	</div>
	
	<div>
	<lable>合約照片</lable>
	<input type="file" style="width:75px" value="${vo.leasePic}" id="file">
	<input type="hidden" id="leasePic" name="leasePic">
	<img id="result" style="width:75px" src="${vo.leasePic}" />
	</div>
	
	<div>
	<lable>備註</lable>
	<input type="text" value="${vo.houseNote}" name="houseNote">
	</div>
	
	<div>
	<label>押金是否返還</label>
	<select name="leaseRefund" id="SelectRefund">
	<option value="1">是</option>
	<option value="0">否</option>
	</select>
	</div>
	
	<input type="submit" value="修改">
	
	
	</form>
<script src="/hermit/js/jquery-3.2.1.min.js"></script>
<script src="/hermit/js/bootstrap.js"></script>
<script src="/hermit/js/flashcanvas.js"></script>
<script src="/hermit/js/jSignature.min.js"></script>
<script src="/hermit/js/datatables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
<script>
$(document).ready(function(){
	var SelectVal=${vo.leaseRefund};
	var SelectRefund=$("#SelectRefund");
// 	console.log(SelectVal);
	
	//取全部，判斷狀態用
	if(SelectVal==0){
		SelectRefund.children("option").eq(1).prop("selected","true");
	}else if(SelectVal==1){
		SelectRefund.children("option").eq(0).prop("selected","true");
	}
	//
	
	
		
	
	
	
	

	
	//圖片↓↓↓
	$("#file").change(function(e){
		  var img = e.target.files[0];
		  if(!img.type.match('image.*')){
		    alert("Whoops! That is not an image.");
		    return;
		  }
		  iEdit.open(img, true, function(res){
		    $("#result").attr("src", res);
		  });
		  
	});
	//圖片↑↑↑
	$("#form").submit(function(event){
			  $("#leasePic").val($("#result").attr("src"));
		  })






})


</script>

</body>
</html>