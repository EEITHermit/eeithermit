<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>赫米特租屋管理</title>
<link rel="shortcut icon" href="<%= request.getContextPath() %>/favicon.ico">
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
<link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'
	rel='stylesheet' type='text/css'>
<!-- CSS reset -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/reset.css">
<!-- Gem style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<!-- Modernizr -->
<script src="<%=request.getContextPath()%>/js/modernizr.js"></script>
<script src="<%=request.getContextPath()%>/js/main.js"></script>
<!-- Action panel -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/st.action-panel.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.min.css">
<script src="<%=request.getContextPath()%>/js/st.action-panel.js"></script>
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
  #footer {
  	margin-top:30px;
  	margin-bottom:0px;
	width:100%;
	height:100px;
	position:absolute;
	bottom:0;
	left:0;
	}
</style>
</head>
<body>
	
	<div class="w3-bar w3-black navbar-fixed-top glcwTeXYen">
		<button class="w3-button w3-dark-grey w3-xlarge w3-left" onclick="openLeftMenu()">&#9776;</button>
		<a href="<%=request.getContextPath()%>/index.jsp" class="w3-bar-item w3-button w3-xlarge w3-left glfont"><span id="hermitHome">Hermit</span></a>
		
 		<!-- 如果有登入就顯示登出 -->
		<c:if test="${!empty LoginOK}">
 			<a href="http://localhost:8081/hermit/MemberLogin/Logout.jsp" class="w3-bar-item w3-button w3-xlarge w3-right w3-margin-right" ><span id="hermitHome">登出</span></a>
 		</c:if>
 		
		<div class="w3-sidebar w3-bar-block w3-animate-left navbar-fixed-top w3-dark-gray" style="color:white;display:none;font-size:20px;font-family:Microsoft JhengHei;" id="leftMenu">
		<button onclick="closeLeftMenu()" class="w3-bar-item w3-button w3-large"><span>Close &times</span></button>
		<a href="<%=request.getContextPath()%>/index.jsp" class="w3-bar-item w3-button"><span>首頁</span></a>
		<a href="<%=request.getContextPath()%>/memberbackstage/mem_back_index.jsp" class="w3-bar-item w3-button"><span>會員中心</span></a>
		<a href="<%=request.getContextPath()%>/memberbackstage/mem_back_favorite.jsp" class="w3-bar-item w3-button"><span>我的收藏</span></a>
		</div>	   
	</div>
	
	<!-- Action panel -->
	<div class="st-actionContainer right-bottom">
		<div class="st-panel">
			<div class="st-panel-header">小天使視窗</div>
			<div class="st-panel-contents">
				<textarea id="talkarea" style="font-size: 1em; resize: none; color: black;"
					readonly="readonly" rows="20em" cols="40em"></textarea>
				<hr />
				<input type="text" id="talktext" size="32em" style="color: black" /><input
					id="sendmsg" style="color: black" type="button" value="送出" />
			</div>
		</div>
		<div class="st-btn-container right-bottom" >
			<div class="st-button-main" id="icon-button">
				<i class="fa fa-bell" aria-hidden="true"></i>
			</div>
		</div>
	</div>

	<script>
	// Action panel -1
	$('st-actionContainer').launchBtn();
	
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
	    
		// Action panel -2
		$('#icon-button').click(function() {
			var area = document.getElementById('talkarea');
			var text = document.getElementById('talktext');
			var websocket = new WebSocket("ws://"+location.host+"/hermit/websocket.do");

			websocket.onopen = function processOpen() {
			};

			websocket.onmessage = function(message) {
				var jsonData = JSON.parse(message.data);
				if (jsonData.message != null) {
					area.value += jsonData.message + "\n";
				}
			};

			websocket.onclose = function (evt) {
		        websocket.close();
		    };
			
			websocket.onerror = function (evt) {
		        websocket.close();
		    };
		    
			$(function() {
				$('#sendmsg').click(function() {
					websocket.send(text.value);
					text.value = "";
				});
			});
		});
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