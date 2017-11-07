<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改廣告</title>
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
width:1100px;
padding:20px;

margin-right:10px;
float:right;
}
</style>
</head>
<body>
<jsp:include page="/fragment/back_side_page.jsp" />

<div class="container" id="DIV2">
	<form method="post" action="empServlet" >
		<label for="EmpNO">員工編號</label><br>
		<span>${empVO.empNO}</span>
		<input type="hidden" name="empNO" readonly value="${empVO.empNO}" id="text" size="20">&nbsp;<br>
		
		<label for="empName">員工姓名</label><br>
		<input type="text" name="empName" value="${empVO.empName}" id="text" size="20"><p/><br>
		
		<label for="empAccount">員工帳號</label><br>
		<input type="text" value="${empVO.empAccount}"  size="20" name="empAccount" >(請輸入6~12位英文字含數字、不可輸入中文字及特殊字元)<p/><br>
		
		<label for="empPwd">員工密碼</label><br>
		<input type='text' name='empPwd' size="20" value="${empVO.empPwd}">(請輸入6~12位大小寫英文含數字、大小寫區分)<P/><br>
		
		<label for="empPhone">員工電話</label><br>
		<input type="text" name="empPhone" id="empPhone" size="20" value="${empVO.empPhone}">(請輸入手機號碼0987-654-321)<p/><br>
		
		<label for="postVO">職稱 </label>
		<select name="empPostVO" id="empPostVO">
			<option value="310">系統管理員</option>
			<option value="320">業務人員</option>
			<option value="330">客服人員</option>
			<option value="340">修繕人員</option>
		</select>
	<%-- 	<input type="radio" name="postVO" value="${postVO}">上架<p/><br> --%>
	
		<label for="empStatus">員工狀態 </label>
		<select name="empStatus" id="empStatus">
			<option value="0">可上班</option>
			<option value="1">已離職</option>
		</select><br><br><br>
	
		<input type="hidden" name="action" value="UpdatetEmp">	
		<button type="button" class="btn btn-default" id="submit" onclick="doSubmit(this);">確認</button>
		<button type="reset" class="btn btn-default">清除</button>
		
		<div style="display:none">
			<button type="submit" id="submitBtn"></button>
		</div>
		
	</form>
</div>

<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
<script type="text/javascript">

$(function(){
	$("#empPostVO option[value='${empVO.postVO.postNO}']").prop("selected", true);
// 	$("#empPostVO option[value='${empVO.postVO.postNO}']").attr("selected", "selected");
// var status = ${emp.empStatus}
// if(!status){
// 		$("#empStatus option[value='1']").prop("selected", true);
// 	}
// });


	if('${emp.empStatus}' === '1'){
		$("#empStatus option[value='1']").prop("selected", true);
	}
});

var mapping = {
		"empName" : "員工姓名", 
		"empAccount" : "員工帳號",
		"empPwd" : "員工密碼", 
		"empPhone" : "員工電話", 
}
function doSubmit(src){
	//lock button
	$(src).prop("disabled", true);
	
	// begin check data
	var ele = $("[name^='emp']");
	
	var value;
	$.each(ele, function(index, obj){
		value = $(obj).val();
		if(!$.trim(value)){
			// something is blank, return and unlock button
			var id = $(obj).attr("name");
			var text = mapping[id];
			alert(text+"為空")	//modify here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			$(src).prop("disabled", false);
			return false;
			
		}
	});
	$("#submitBtn").click();
}
</script>
</body>
</html>