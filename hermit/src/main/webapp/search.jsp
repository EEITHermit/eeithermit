<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>赫米特租屋管理</title>
<jsp:include page="/fragment/index_include.jsp" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/appstore.css">
<link href="http://fonts.googleapis.com/earlyaccess/cwtexhei.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/appstore.js"></script>
<link href='<%=request.getContextPath()%>/css/tupianqiehuan.css' rel='stylesheet' />
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

	<div class="container" style="margin:40px auto;height:600px">
		<div id="appstore-container" style="margin-bottom:150px"></div>
	</div>
	<footer class="w3-bottom w3-black container-fluid text-center">
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
			if(houseItems== null){
				location.replace("<%= request.getContextPath()%>/index.jsp");
			};
			if(houseItems.items.length == 0){
				$(".container").append("<h1 style='text-align:center'>找不到這個條件下的房屋物件，是否考慮再看看其他條件的房屋呢？</h1>")
			}
			$.appstore({json:houseItems});
		})
	</script>
</body>

</html>