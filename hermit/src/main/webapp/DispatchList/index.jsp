<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首頁</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/flashcanvas.js"></script>
<script src="../js/jSignature.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="col-md-4" style="margin:100px auto">
			<a href="SignatureInsert.jsp" class="btn btn-default" >新增</a><p/>
			<form method="get" action="DispatchListServlet">
				<div class="form-group">
					<input type="text" class="form-control" value="70000001" name="dlno">
					<input type="hidden" value="getOneDispatchList" name="action">
				</div>
				<button type="submit" id="search" >查詢</button>
			</form>
			<p/>
			
				<button type="submit" id="searchall" ><a type="submit" href="SignatureGetAll.jsp" >查詢全部</a></button>
				
			<p/>
		
		</div>
	</div>

</body>
</html>