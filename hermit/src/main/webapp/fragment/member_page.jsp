<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>赫米特租屋管理</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/w3.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css"/>
<link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
<link href="http://fonts.googleapis.com/earlyaccess/cwtexyen.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/flashcanvas.js"></script>
<script src="<%=request.getContextPath()%>/js/jSignature.min.js"></script>
<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
<style>
	*{
		margin:0;
		padding:0;
	}
	a:link, a:visited, a:hover ,a:active{
		color : white;
	    text-decoration: none;
	    padding: 14px 25px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	}
	#hermitHome {
		color : white;
	}
	.w3-sidebar span{
		color:white;
		font-family: 'cwTeXYen', sans-serif;
	}
	.main-gallery
	{	
	    width:100%;
	    height:50vh;
	    background:url("images/main.jpg");
		background-position:center;
	    background-size:cover;
	    margin-top:52px;
	}
	.w3-jumbo span{
		font-family:"Tangerine",serif;
	}
	.glfont {
    font-family: 'Pacifico', cursive;
	}
	.glcwTeXYen{
		font-family: 'cwTeXYen', sans-serif;
	}
  #button { padding: .5em 1em; text-decoration: none; }
</style>
</head>
<body>
	
	<div class="w3-bar w3-black navbar-fixed-top glcwTeXYen">
		<button class="w3-button w3-dark-grey w3-xlarge w3-left" onclick="openLeftMenu()">&#9776;</button>
		<a href="<%=request.getContextPath()%>/index.jsp" class="w3-bar-item w3-button w3-xlarge w3-left glfont"><span id="hermitHome">Hermit</span></a>
		<a href="#" class="w3-bar-item w3-button w3-xlarge w3-right w3-margin-right" ><span id="hermitHome">註冊</span></a>
		<span class="w3-bar-item  w3-xlarge w3-right" id="hermitHome">|</span>
		<a href="#" class="w3-bar-item w3-button w3-xlarge w3-right"><span id="hermitHome">登入</span></a>
		<div class="w3-sidebar w3-bar-block w3-animate-left navbar-fixed-top w3-dark-gray" style="color:white;display:none;font-size:20px;font-family:Microsoft JhengHei;" id="leftMenu">
		<button onclick="closeLeftMenu()" class="w3-bar-item w3-button w3-large"><span>Close &times</span></button>
		<a href="<%=request.getContextPath()%>/index.jsp" class="w3-bar-item w3-button"><span>首頁</span></a>
		<a href="<%=request.getContextPath()%>/memberbackstage/mem_back_index.jsp" class="w3-bar-item w3-button"><span>會員中心</span></a>
		<a href="<%=request.getContextPath()%>/memberbackstage/mem_back_favorite.jsp" class="w3-bar-item w3-button"><span>我的收藏</span></a>
		</div>	   
	</div>
	
	
	
	
	
	
	
	
	<footer class="w3-bottom w3-black container-fluid" >
		<div class="row nav">
		  <div class="col-md-4"></div>
		  <div class="col-md-1"><a href="<%=request.getContextPath()%>/index.jsp">關於我們</a></div>
		  <div class="col-md-1"><a href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></div>
		  <div class="col-md-1"><a href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">服務條款</a></div>
		  <div class="col-md-1"><a href="<%=request.getContextPath()%>/register/law_service_page.jsp">隱私權聲明</a></div>
		  <div class="col-md-4"></div>
		</div>
	    <span class="text-center"><p style="font-size:10px">赫米特開發團隊  Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
		</div>
	</footer>
	<script>
	$( function() {
		var spanArrow = $(".glyphicon-chevron-down"); 
		var btn = $( "#button" );
		var effect = $( "#effect" );
		var leftMenu = $("#leftMenu");
		function runEffect(){
			effect.toggle( "blind",  500 );
			console.log(spanArrow.attr('class'));
			if(spanArrow.attr('class') == "glyphicon glyphicon-chevron-up"){
				spanArrow.attr("class","glyphicon glyphicon-chevron-down");
			}else{
				spanArrow.attr("class","glyphicon glyphicon-chevron-up")
			}
		};
		btn.on( "click", function() {
		      runEffect();
		});
	    effect.hide(); 
	  });

		function openLeftMenu() {
		    document.getElementById("leftMenu").style.display = "block";
		    
		}
		function closeLeftMenu() {
		    document.getElementById("leftMenu").style.display = "none";
		}

	</script>

</body>
</html>