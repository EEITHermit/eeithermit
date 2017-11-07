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
<script	src="<%=request.getContextPath()%>/js/jquery.imgareaselect.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/iEdit.min.css">
<style type="text/css">
#DIV1{
margin-top:25px;
width:1200px;
padding:20px;
margin-right:10px;
float:left;
}
#DIV2{
width:1200px;
padding:20px;
margin-right:10px;
float:left
}
</style>
</head>
<body>
<jsp:include page="/fragment/back_side_page.jsp" />

<div class="container">
	<form method="post" action="../ADManagerServlet" enctype="multipart/form-data" >
		<div id="DIV1">
			<label for="adImage">廣告圖片</label><br>
			<img id="result" src="../images/admanager_photoicon.jpg" height="360" width="1000" ><br>
			<input id="file" src="" name="adImage" type="file" value="${adImage}">
			<input type="hidden" name="adImage" id="adImage"><p/><br>
		</div>
		
		<div class="form-group" id="DIV2">
			
			<table style="width:100%;">
				<tr>
					<td>
						<label for="adLink">圖片連結網址</label><br>
						<input type="text" value="${adLink}"  size="40" placeholder="請輸入範例格式:http://www.example.com.tw" name="adLink" ><p/><br>
						
						<label for="adModify">修改人員</label><br>
						<input type="text" readonly value="${empLoginOK.empName}" size="40"><br>
						<input type='hidden' class="form-control" id="adModify" name='adModify' readonly value="${empLoginOK.empNO}" size="40"><P/><br>
						
						<label for="adMessage">廣告訊息(限10字內)</label><br>
						<input type="text" id="adMessage" name="adMessage" size="40" value="${adMessage}" placeholder="請輸入訊息..."><br><br>
					</td>
					<td>
						<label for="adTimeStart">廣告上架日期</label><br>
						<input type="date" name="adTimeStart" value="${adTimeStart}" id="date1" size="60" placeholder="2014-09-18"><p/><br>
						
						<label for="adTimeEnd">廣告下架日期</label><br>
						<input type="date" name="adTimeEnd" value="${adTimeEnd}" id="date1" size="60" placeholder="2015-09-18"><p/><br>
						
						<label for="adStatus">狀態 </label><br>
						<input type="radio" name="adStatus" value="${adStatus}" checked>上架<br>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="hidden" name="action" value="InsertADManager">	
						<button type="submit" class="btn btn-default" id="submit" onclick="javascrtpt:window.location.href='back-adIndex_include.jsp'">確認</button>
						<button type="reset" class="btn btn-default">清除</button>
					</td>
				</tr>
			
			</table>
		
			
		</div>
		
	</form>
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
	  iEdit.open(img, false, function(res){
	    $("#result").attr("src", res);
 		  $("#adImage").val($("#result").attr("src"));
	  });
	    $("#adImage").val($("#result").attr("src"));
	  }); 
	  $("#form").submit(function(event){
		  $("#adImage").val($("#result").attr("src"));
	  });
</script>
</body>
</html>