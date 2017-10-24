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
				<li><a href="https://tw.yahoo.com/" target="_blank"><img src="ad-backindex.jsp"
						alt="x" /></a></li>
				<li><a href="https://tw.yahoo.com/" target="_blank"><img src="images/e02.jpg"
						alt="x" /></a></li>
				<li><a href="https://tw.yahoo.com/" target="_blank"><img src="images/e03.jpg"
						alt="x" /></a></li>
				<li><a href="https://tw.yahoo.com/" target="_blank"><img src="images/e04.jpg"
						alt="x" /></a></li>
				<li><a href="https://tw.yahoo.com/" target="_blank"><img src="images/e05.jpg"
						alt="x" /></a></li>
				<li><a href="https://tw.yahoo.com/" target="_blank"><img src="images/e06.jpg"
						alt="x" /></a></li>
				<li><a class="ADManagerServlet" ><img src="http://farm9.staticflickr.com/8241/8589392310_7b6127e243_s.jpg" width="75" height="75"></a></li>
			</ul>
						
		</div>
	</div>
	<script>

// 	$(document).ready(function() {

// 		$('.image-popup-vertical-fit').magnificPopup({
// 			type: 'image',
// 			closeOnContentClick: true,
// 			mainClass: 'mfp-img-mobile',
// 			image: {
// 				verticalFit: true
// 			}
			
// 		});
// 		$(document).ready(function(){
// 			var dataJson;
			
// 			var table = $("#myTable");
// 			var tbody = $("#myTable>tbody");
// 			function ajaxPost(){
// 			$.post("ADManagerServlet",{"action":"getAllADManagerForJson"},function(data){
// 				dataJson = $.parseJSON(data).list;
// 				console.log(data);
// 				console.log(dataJson);
// 				tbody.empty();
// 			})	
// 		})
		
	</script>
</body>
</html>