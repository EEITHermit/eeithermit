<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增廣告</title>
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/flashcanvas.js"></script>
<script src="../js/jSignature.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/iEdit.min.css">
<style type="text/css">
#DIV2{
margin-top:35px;
width:800px;
padding:20px;

margin-right:10px;
float:right;
}
</style>
</head>
<body>
<jsp:include page="/fragment/back_side_page.jsp" />

<div class="container" id="DIV2">
	<form method="post" action="../EmpServlet" enctype="multipart/form-data" >

	
	<label for="EmpNO">員工編號</label><br>
	<input type="text" name="EmpNO" readonly value="${EmpNO}" id="text" size="20">為固定，不可修改<p/><br>
	
	<label for="empName">員工姓名</label><br>
	<input type="text" name="empName" value="${empName}" id="text" size="20"><p/><br>
	
	<label for="empAccount">員工帳號</label><br>
	<input type="text" value="${empAccount}"  size="20" name="empAccount" >(請輸入6~8位英文字含數字、不可輸入中文字及特殊字元)<p/><br>
	
	<label for="empPwd">員工密碼</label><br>
	<input type='text' name='empPwd' size="20">(請輸入8~10位大小寫英文含數字、大小寫區分)<P/><br>
	
	<label for="empPhone">員工電話</label><br>
	<input type="text" id="empPhone" size="20" value="${empPhone}">(請輸入手機號碼0987-654-321)<p/><br>
	
	<label for="postVO">職稱 </label>
	<select>
		<option value="310">系統管理員</option>
		<option value="320">業務人員</option>
		<option value="330">客服人員</option>
		<option value="340">修繕人員</option>
	</select>
<%-- 	<input type="radio" name="postVO" value="${postVO}">上架<p/><br> --%>

<label for="empStatus">員工狀態 </label>
	<select>
		<option value="0">可上班</option>
		<option value="1">已離職</option>
	</select><br><br><br>

	<input type="hidden" name="action" value="InsertEmp">	
	<button type="submit" class="btn btn-default" id="submit" onclick="javascrtpt:window.location.href='empIndex_include.jsp'">確認</button>
	<button type="reset" class="btn btn-default">清除</button>
</form>
</div>

<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
<script type="text/javascript">
// $("#file").change(function(e){
// 	  var img = e.target.files[0];
// 	  if(!img.type.match('image.*')){
// 	    alert("Whoops! That is not an image.");
// 	    return;
// 	  }
// 	  iEdit.open(img, true, function(res){
// 	    $("#result").attr("src", res);
//  		  $("#adImage").val($("#result").attr("src"));
// 	  });
// 	    $("#adImage").val($("#result").attr("src"));
// 	  }); 
// 	  $("#form").submit(function(event){
// 		  $("#adImage").val($("#result").attr("src"));
// 		  alert($("#file").val());
// 	  });
// 	function testSubmit(){
// 		 alert($("#file").val());
// 	}
</script>
</body>
</html>