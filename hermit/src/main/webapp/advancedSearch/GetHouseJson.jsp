<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>House Test</title>
<jsp:include page="/fragment/index_include.jsp" />
<link rel="stylesheet" href="../css/appstore.css">
<link href="http://fonts.googleapis.com/earlyaccess/cwtexhei.css" rel="stylesheet">
<!-- <script src="../js/jquery-3.2.1.min.js"></script> -->
<script src="../js/appstore.js"></script>

<style>
	a:link, a:visited, a:hover ,a:active{
		color : white;
	    padding: 14px 25px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	}
	h3 a {
		font-family: Microsoft JhengHei;
	}
</style>
</head>
<body id="body">
	<div class="container" style="margin:40px auto">
		<div id="appstore-container"></div>
	</div>

	<footer class="w3-bottom w3-black container-fluid text-center" style=" position: static">
		<div>
			<ul class="nav nav-pills w3-centered " style="display: flex;font-size:13px;justify-content: center;">
			  <li role="presentation"><a href="<%=request.getContextPath()%>/index.jsp">關於我們</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">服務條款</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_service_page.jsp">隱私權聲明</a></li>
			</ul>
		</div>
	    <span class="text-center"><p style="font-size:10px">赫米特開發團隊  Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
		</div>
	</footer>
	
	<script>
		$(function(){
			
			var houseItems = <%= session.getAttribute("houseItems") %>
			var appstore = {
				    "items": [
				        {
				            "title"         :"甜蜜小套房",
				            "description"   :"租金：90,000/月",
				            "link"          :"https://tw.yahoo.com/",
				            "previewPic"     :"https://hp1.591.com.tw/house/active/2017/10/25/150894090255548700_765x517x733302.jpg",
				            "tag"           :"台北市",
				            "date"          :"2017-10-30"
				        },{
				            "title"         :"甜蜜小套房",
				            "description"   :"租金：90,000/月",
				            "link"          :"https://tw.yahoo.com/",
				            "previewPic"     :"https://hp1.591.com.tw/house/active/2017/10/25/150894090255548700_765x517x733302.jpg",
				            "tag"           :"台北市",
				            "date"          :"2017-10-30"
				        }]
				}
		    $.appstore({json:appstore});
		})
	</script>
</body>

</html>