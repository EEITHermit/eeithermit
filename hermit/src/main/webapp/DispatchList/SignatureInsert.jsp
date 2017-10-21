<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/flashcanvas.js"></script>
<script src="../js/jSignature.min.js"></script>
</head>
<body>
	<div class="container">
		<form method="get" action="DispatchListServlet">
			<div class="form-group">
				<label for="dempno">指派人</label>
				<input type="text" class="form-control" id="dempno" value="1001" >
			</div>
			<div class="form-group">
			 	<label for="aempno">負責人</label>
			 	<input type="text" class="form-control" id="aempno" value="1002">
			</div>
			<div class="form-group">
			 	<label for="qano">問答單號</label>
			 	<input type="text" class="form-control" id="qano" value="10000001">
			 	<input type="hidden" class="form-control" id="action" value="InsertDispatchList">
			</div>
			<button type="button" class="btn btn-default" id="submit">Submit</button>
		</form>
	</div>
	<script>
		$(function(){
			$("#submit").on("click",function(){
				$.post("DispatchListServlet",{"dempno":$("#dempno").val(),"aempno":$("#aempno").val(),"qano":$("#qano").val(),"action":$("#action").val()},function(date){
					alert("JSP Insert success");								
				})
			})
		})
	</script>
</body>
</html>