<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯廣告</title>
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/flashcanvas.js"></script>
<script src="../js/jSignature.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/iEdit.min.css">
</head>
<body>
<div class="container">
<form method="post" action="ADManagerServlet" enctype="multipart/form-data" >
<div class="form-group">
<div>
			<label for="adNO">廣告編號</label><br>
			<input id="text" name="adNO" type="text" value=${adVO.adNO}>
			<p/><br>
		</div>
		<div>
			<label for="adImage">廣告圖片</label><br>
			<img id="result" src=${adVO.adImage} height="300" width="350" ><br>
			<input id="file" name="adImage" type="file" value=${adVO.adImage}>
			<input type="hidden" name="adImage" id="adImage">
			<p/><br>
		</div>
		<div>
			<label for="adLink">圖片連結網址</label><br>
			<input type="text" value=${adVO.adLink}  size="60" placeholder="請輸入範例格式:http://www.example.com.tw" name="adLink" ><p/><br>
		</div>
		<div>
			<label for="adMessage">廣告上顯示的訊息(限20字內)</label><br>
			<textarea name="adMessage" rows="6" cols="40"></textarea><p/><br>
			<input id="adMessage" type="hidden" value=${adVO.adMessage}>
		</div>
		<div>
			<label for="adTimeStart">廣告上架時間</label>
			<input type="date" name="adTimeStart" value="${adVO.adTimeStart}" id="date1" size="60" placeholder="2014-09-18"><p/><br>
			<label for="adTimeEnd">廣告下架時間</label>
			<input type="date" name="adTimeEnd" value="${adVO.adTimeEnd}" id="date1" size="60" placeholder="2015-09-18"><p/><br>
		</div>
		<div>
			<label for="adStatus">狀態 </label>
			<input type="radio" name="adStatus" value="0" checked>上架
  		</div>
		<div>
			<label for="adModify">修改人</label>
			<input type='text' name='adModify' size="60" placeholder="請輸入員工代號  ex:00000"><P/><br>
		</div>
			<input type="hidden" name="action" value="updateADManager">	
			<button type="submit" class="btn btn-default" id="submit" onclick="javascrtpt:window.location.href='back-adindex.jsp'">確認</button>
			<button type="reset" class="btn btn-default">清除</button>	
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