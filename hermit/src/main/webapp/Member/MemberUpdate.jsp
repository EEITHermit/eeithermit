<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/iEdit.min.css">
</head>
<body>
<jsp:include page="/fragment/member_page.jsp"></jsp:include>
<div class="row">
		<div class="col-md-4 mid"></div>
		<div class="col-md-4 mid">
			
		<form method="POST" action="<%=request.getContextPath()%>/member.do?action=update" id="form">
			
			編號<input type="text" value="${memNO}" disabled><br>
			   <input type="hidden" readonly value="${memNO}" name="memNO">
			
			電話 <input type="text" value="${memTel}" disabled>
			   <input type="hidden" value="${memTel}" name="memTel">
			<font size="-1" color="#FF0000">${MsgMap.memTel}</font>
			<br>
			
			帳號<input type="text" value="${memAccount}" disabled>
			<input type="hidden"  value="${memAccount}" name="memAccount"><br>
			
			密碼<input type="password" value="${memPwd}" name="memPwd">
			<font size="-1" color="#FF0000">${MsgMap.memPwd}</font>
			<br>
			
			姓名<input type="text" value="${memName}" name="memName">
            <font size="-1" color="#FF0000">${MsgMap.memName}</font>
			<br>
			
			性別<input type="text" value="${memGender}" disabled>
			   <input type="hidden" readonly value="${memGender}" name="memGender" ><br>
			   
			信箱<input type="text" value="${memEmail}" name="memEmail">
			<font size="-1" color="#FF0000">${MsgMap.memEmail}</font>
			<br>
			註冊時間<input type="text" value="${memRegister}" disabled>
			<input type="hidden" readonly value="${memRegister}" name="memRegister"><br>
			
			會員狀態<input type="text" value="${memStatus}" disabled>
			<input type="hidden" readonly value="${memStatus}" name="memStatus"><br>
			
			違規次數<input type="text" value="${memInfract}" disabled>
			<input type="hidden" readonly value="${memInfract}" name="memInfract"><br>
			
			<div>
			圖片<input type="file" id="file" > 
			<input type="hidden" id="memImage" name="memImage" value="${memImage}"  >
			
			<img id="result" src="${memImage}" name="memImage" id="memImage" height="200" width="200">
			</div>
			<input type="submit" value="修改">
			
			</form>
		
		
	
		</div>
		<div class="col-md-4 mid"></div>
	</div>
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
	<script type="text/javascript">
	$("#file").change(function(e){
		  
		  var img = e.target.files[0];

		  if(!img.type.match('image.*')){
		    alert("Whoops! That is not an image.");
		    return;
		  }
		  iEdit.open(img, true, function(res){
		    $("#result").attr("src", res);
		  });
		  //在檔案送出前，讓image的src送到input裡
		  $("#form").submit(function(event){
			  $("#memImage").val($("#result").attr("src"));
		  }) 
		});
	</script>
</body>
</html>