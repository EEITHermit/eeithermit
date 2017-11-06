<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯廣告</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/iEdit.min.css">
<style type="text/css">
#DIV1{
margin-top:35px;
width:500px;

padding:20px;

margin-right:10px;
float:left;
}
#DIV2{
margin-top:35px;
width:600px;

padding:20px;

margin-right:10px;
float:right;
}

</style>
</head>
<body>
<jsp:include page="/fragment/back_side_page.jsp" />

<div class="container">
	<form method="post" action="ADManagerServlet" enctype="multipart/form-data" >
<div id="DIV1">
	<label for="adImage">廣告圖片</label><br>
	<img id="result" src="${adVO.adImage}" height="450" width="450" ><br>
	<input id="file" name="adImage" type="file" value="${adVO.adImage}">
	<input type="hidden" name="adImage" id="adImage" ><p/><br>
</div>
<div class="form-group" id="DIV2">
	<label for="adNO">廣告編號</label><br>
	<input id="text" name="adNO"  readonly type="text" value="${adVO.adNO}" ><p/><br>
	<label for="adTimeStart">廣告上架日期</label><br>
	<input type="date" name="adTimeStart" value="${adVO.adTimeStart}" id="date1" size="60" placeholder="2014-09-18"><p/><br>
	
	<label for="adTimeEnd">廣告下架日期</label><br>
	<input type="date" name="adTimeEnd" value="${adVO.adTimeEnd}" id="date1" size="60" placeholder="2015-09-18"><p/><br>
	
	<label for="adLink">圖片連結網址</label><br>
	<input type="text" name="adLink" value="${adVO.adLink}"  size="40" placeholder="請輸入範例格式:http://www.example.com.tw" name="adLink" ><p/><br>
	
	<label for="adModify">修改人員編號</label><br>
	<input type='text' name='adModify' value="${adVO.adModify}" size="40" placeholder="請輸入員工代號  ex:00000"><P/><br>
	
	<label for="adMessage">廣告訊息(限10字內)</label><br>
	<input type="text" id="adMessage" name="adMessage" size="40" value="${adVO.adMessage}" placeholder="請輸入訊息..."><br><br>
	
	<label for="adStatus">狀態 </label><br>
	<input type="radio" name="adStatus" value="${adVO.adStatus}" checked>下架
	<input type="radio" name="adStatus" value="" checked>上架
	
</div>
	<input type="hidden" name="action" value="updateADManager">	
	<button type="submit" class="btn btn-default" id="submit" onclick="javascrtpt:window.location.href='back-adindex_include.jsp'">確認</button>
	<button type="reset" class="btn btn-default">清除</button>	
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
	  iEdit.open(img, true, function(res){
	    $("#result").attr("src", res);
 		  $("#adImage").val($("#result").attr("src"));
	  });
	    $("#adImage").val($("#result").attr("src"));
	  }); 
	  $("#form").submit(function(event){
		  $("#adImage").val($("#result").attr("src"));
// 		  alert($("#file").val());
	  });
// 	function testSubmit(){
// 		 alert($("#file").val());
// 	}
</script>
</body>
</html>