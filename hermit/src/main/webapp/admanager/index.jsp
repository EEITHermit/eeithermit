<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Hermit</title>

<script src='<%=request.getContextPath() %>/js/jquery-3.2.1.min.js'></script>
<script src='<%=request.getContextPath() %>/js/tupianqiehuan.js'></script>
<link href='<%=request.getContextPath() %>/css/tupianqiehuan.css' rel='stylesheet' />
<link rel="stylesheet" href="iEdit.css">
<script src="iEdit.js"></script>

</head>

<body>
<h2>Hermit</h2>
<script>
$(document).ready(function(){
	  $('h2').css('color','red').css('background-color','pink');
  })
</script>



	<div class="wrapper">

		<div id="focus">
			<ul>
				<li>
<!-- 				<li><a href="https://tw.yahoo.com/" target="_blank"><img src="ad-backindex.jsp" -->
<!-- 						alt="x" /></a></li> -->
<!-- 				<li><a href="https://tw.yahoo.com/" target="_blank"><img src="images/e02.jpg" -->
<!-- 						alt="x" /></a></li> -->
<!-- 				<li><a href="https://tw.yahoo.com/" target="_blank"><img src="images/e03.jpg" -->
<!-- 						alt="x" /></a></li> -->
<!-- 				<li><a href="https://tw.yahoo.com/" target="_blank"><img src="images/e04.jpg" -->
<!-- 						alt="x" /></a></li> -->
<!-- 				<li><a href="https://tw.yahoo.com/" target="_blank"><img src="images/e05.jpg" -->
<!-- 						alt="x" /></a></li> -->
<!-- 				<li><a href="https://tw.yahoo.com/" target="_blank"><img src="images/e06.jpg" -->
<!-- 						alt="x" /></a></li> -->
<!-- 				<li><a class="ADManagerServlet" ><img src="http://farm9.staticflickr.com/8241/8589392310_7b6127e243_s.jpg" width="75" height="75"></a></li> -->
					
				</li>	
			</ul>
						
		</div>
	</div>
	<div>
	<form action="ADManagerServlet" >
		<input id="file" type="file" >
		<img id="result">
	</form>
	</div>
	<script>

	$("#file").change(function(e){
		  
		  var img = e.target.files[0];

		  if(!img.type.match('image.*')){
		    alert("Whoops! That is not an image.");
		    return;
		  }
		  iEdit.open(img, true, function(res){
		    $("#result").attr("src", res);
		  });
		  
		});
	
	$(function(){
	 	$("#file").change(function(e){
	 		  var img = e.target.files[0];
	 		  if(!img.type.match('image.*')){
	 		    alert("請放入廣告圖片");
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
	})
	</script>
</body>
</html>