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
<script src="<%=request.getContextPath()%>/js/appstore.js"></script>
<script src="<%=request.getContextPath()%>/js/lazyload.js"></script>

<style>
	a:link, a:visited, a:hover ,a:active{
		color : white;
	    padding: 14px 25px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	}
	h4 a {
		font-family: Microsoft JhengHei;
	}
	.breadBox {
    	width: 100%;
    	display: block;
    	box-sizing: border-box;
	}
	.breadBox .breadNav {
	    width: 1200px;
	    margin: auto;
	    padding: 0;
	}
	.breadBox .breadNav .breadList,.conditionShow{
	    float: left;
	    margin: 0;
	    padding: 0;
	}
	.breadBox .breadNav a {
	    font-size: 13px;
	    line-height: 55px;
	}
	.fa {
	    display: inline-block;
	    font: normal normal normal 14px/1 FontAwesome;
	    font-size: inherit;
	    text-rendering: auto;
	    -webkit-font-smoothing: antialiased;
	    -moz-osx-font-smoothing: grayscale;
	}
	.conditionShow {
	    margin-left: 15px;
	    width: 867px;
	}
	.breadBox .breadNav span {
	    font-size: 12px;
	    color: #A3A3A3;
	}
	.conditionShow span {
	    display: block;
	    float: left;
	    margin-top: 15px;
	    margin-right: 10px;
	    border: 1px solid #D4D4D4;
	    padding: 0px 10px;
	    padding-right: 0px;
	    color: #666666;
	}

	
	
</style>
</head>
<body id="body">
<!-- 	 <section class="breadBox"> -->
<!--         <div class="breadNav clearfix"> -->
<!--             <div class="breadList" id="breadList"> -->
<%--                 <a href="<%= request.getContextPath()%>/index.jsp" style="color:black;padding:0;">Hermit</a> --%>
<!--                 <i class="glyphicon glyphicon-chevron-right" aria-hidden="true"></i>&nbsp;&nbsp; -->
<!--             </div> -->
<!--             <div class="conditionShow clearfix"> -->
<!-- 				<span>中正區&nbsp;&nbsp;</span>             -->
<!--             </div> -->
<!--         </div> -->
<!--     </section> -->
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
			
			if(houseItems== null){
				location.replace("<%= request.getContextPath()%>/index.jsp");
			};
			console.log(houseItems.items)
			console.log(houseItems.items.length)
			if(houseItems.items.length == 0){
				alert("找不到房屋物件")
			}
		    $.appstore({json:houseItems});
		})
	</script>
</body>

</html>