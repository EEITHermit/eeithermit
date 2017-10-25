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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css"/>
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
		font-family:fantasy;
	}
	.main-gallery
	{
	    width:100%;
	    height:50vh;
	    background:url("../images/main.jpg");
	    background-position:center;
	    background-size:cover;
	    margin-top:52px;
	}

  #button { padding: .5em 1em; text-decoration: none; }
</style>
</head>
<body>
	
	<div class="w3-bar w3-black navbar-fixed-top">
		<button class="w3-button w3-dark-grey w3-xlarge w3-left" onclick="openLeftMenu()">&#9776;</button>
		<a href="#" class="w3-bar-item w3-button w3-xlarge w3-left"><span id="hermitHome">Hermit</span></a>
		
		<a href="#" class="w3-bar-item w3-button w3-xlarge w3-right w3-margin-right" ><span id="hermitHome">註冊</span></a>
		<span class="w3-bar-item  w3-xlarge w3-right" id="hermitHome">|</span>
		<a href="#" class="w3-bar-item w3-button w3-xlarge w3-right"><span id="hermitHome">登入</span></a>
		<div class="w3-sidebar w3-bar-block w3-animate-left navbar-fixed-top w3-dark-gray" style="color:white;display:none;font-size:20px;font-family:Microsoft JhengHei;" id="leftMenu">
		  <button onclick="closeLeftMenu()" class="w3-bar-item w3-button w3-large"><span>Close &times</span></button>
		  <a href="index.jsp" class="w3-bar-item w3-button">首頁</a>
		  <a href="#" class="w3-bar-item w3-button">會員中心</a>
		  <a href="#" class="w3-bar-item w3-button">我的收藏</a>
		</div>	   
	</div>
	<section class="main-gallery">
			<div class="container text-center" >
					<h2>Hermit租屋顧問網</h2>
				<div class="row" style="margin-top:10%;padding:3% 2% 2% 2%;background-color: black;border-radius: 20px">
					  <div class="input-group">
						  <input class="form-control input-lg" type="text">
						  <span class="input-group-btn">
						    <button class="btn btn-default btn-lg" type="button">搜尋</button>
						  </span>
					  </div>
					  <div>
						  <div id="effect" class="form-control" style="background-color: white;height:20vh;">
						  </div>
						  <span id="button" class="w3-right" style="font-size:10px;background-color: white;border-bottom-left-radius:10px;border-bottom-right-radius:10px;">進階搜尋<span class="glyphicon glyphicon-chevron-down"></span></span>
					  </div>
				</div>
			</div>
	</section>
	<div class="w3-black" style="height:3vh;border-bottom-left-radius:15px;border-bottom-right-radius:3px;"></div>
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