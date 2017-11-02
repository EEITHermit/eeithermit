<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/fragment/back_side_page.jsp" />
<title>新增派工單</title>
</head>
<body>
	<div class="container">
		<form>
			<div class="form-group">
				<label for="exampleInputEmail1">指派人</label>
				<input type="text" class="form-control" id="demp" placeholder="指派人" value="">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">受派人</label>
				<input type="text" class="form-control" id="aemp" placeholder="請輸入受派人" value="">
			</div>
			<div class="form-group">
			 	<label for="qano">問答單號</label>
			 	<input type="text" class="form-control" id="qano" value="10000001">
			 	<input type="hidden" class="form-control" id="action" value="InsertDispatchList">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">受派人</label>
				<input type="text" class="form-control" id="houseName" placeholder="房屋物件" value="">
			</div>
		</form>
	</div>



	<script>
	
	</script>
</body>
</html>