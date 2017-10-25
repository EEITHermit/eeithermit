<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hermit廣告後台</title>

<script src='<%=request.getContextPath() %>/js/jquery-3.2.1.min.js'></script>
<script src='<%=request.getContextPath() %>/js/script.js'></script>
<link href='<%=request.getContextPath() %>/css/bootstrap.min.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/css/iEdit.min.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/css/bootstrap-theme.min.css' rel='stylesheet' />
<script src='<%=request.getContextPath() %>/js/bootstrap.js'></script>
<script src='<%=request.getContextPath() %>/js/flashcanvas.js'></script>
<script src='<%=request.getContextPath() %>/js/iEdit.js'></script>


</head>
<body>
<div style="margin-right:auto;">
<h1>後台廣告管理系統</h1><br><br>
	<div>
		<div>
			<h6>廣告圖片</h6><br>
			
			<form method="post" action="ADManagerServlet" >
				<img id="result" src="" height="360" width="1000" ><br>
				<input id="fileimage" name="adImage" type="file" value="result">
				<p/><br><br>
			<h6>圖片連結網址</h6>			
				<input type="text" value="${adLink}" size="60" placeholder="請輸入範例格式:http://www.example.com.tw" name="adno" ><p/><br>
			<h6>請輸入廣告訊息(限20字內)</h6>
			<textarea name="message" rows="6" cols="40"></textarea><p/><br>
			<h6>廣告上架時間</h6>
			<label for="bookdate">日期：</label>
			<input type="date" name="AdTimeStart" id="date1" size="60" placeholder="2014-09-18"><p/><br>
			<h6>廣告下架時間</h6>
			<label for="bookdate">日期：</label>
			<input type="date" name="AdTimeEnd" id="date2" size="60" placeholder="2015-09-18"><p/><br>
			<h6>廣告圖片狀態修改</h6>
  					<input type="radio" name="true" value="0" checked>上架<br>
  					<input type="radio" name="true" value="1">下架<br>
     		  	修改人:<input type='text' name='admodify' size="60" placeholder="請輸入員工代號  ex:00000"><P/><br>
      		
      		 <button type="submit" class="btn btn-primary btn-lg" id="submit">確認</button>
			 <button type="reset" class="btn btn-secondary btn-lg">清除</button>
      		 </form>
		</div>
	</div>
</div>

<script type="text/javascript"> 
	$(function(){
	 	$("#fileimage").change(function(e){
	 		  var img = e.target.files[0];
	 		  if(!img.type.match('image.*')){
	 		    alert("123");
	 		    return;
	 		  }
	 		  iEdit.open(img, true, function(res){
	 		    $("#result").attr("src", res);
	 		  });
	 		  //在檔案送出前，讓image的src送到input裡
	 		  $("#form").submit(function(event){
	 			  $("#fileimage").val($("#result").attr("src"));
	 		  }) 
	 		});
	})
	</script> 
</body>
</html>