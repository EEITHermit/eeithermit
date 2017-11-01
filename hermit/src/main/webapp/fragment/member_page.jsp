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
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/css/reset.css"> --%>
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
	
	/* Shared */
	.loginBtn {
	box-sizing: border-box;
	position: relative;
	width: 13em; /* - apply for fixed size */
	margin: 0.2em;
	padding: 0 15px 0 46px;
	border: none;
	text-align: center;
	line-height: 34px;
	white-space: nowrap;
	border-radius: 0.2em;
	font-size: 16px;
	color: #FFF;
	}

	.loginBtn:before {
	content: "";
	box-sizing: border-box;
	position: absolute;
	top: 0;
	left: 0;
	width: 34px;
	height: 100%;
	}

	.loginBtn:focus {
	outline: none;
	}

	.loginBtn:active {
	box-shadow: inset 0 0 0 32px rgba(0, 0, 0, 0.1);
	}

	/* Facebook */
	.loginBtn--facebook {
	background-color: #4C69BA;
	background-image: linear-gradient(#4C69BA, #3B55A0);
	/*font-family: "Helvetica neue", Helvetica Neue, Helvetica, Arial, sans-serif;*/
	text-shadow: 0 -1px 0 #354C8C;
	}

	.loginBtn--facebook:before {
	border-right: #364e92 1px solid;
	background:
		url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/14082/icon_facebook.png')
		6px 6px no-repeat;
	}

	.loginBtn--facebook:hover, .loginBtn--facebook:focus {
	background-color: #5B7BD5;
	background-image: linear-gradient(#5B7BD5, #4864B1);
	}

	/* Google */
	.loginBtn--google {
	/*font-family: "Roboto", Roboto, arial, sans-serif;*/
	background: #DD4B39;
	}

	.loginBtn--google:before {
	border-right: #BB3F30 1px solid;
	background:
		url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/14082/icon_google.png')
		6px 6px no-repeat;
	}

	.loginBtn--google:hover, .loginBtn--google:focus {
	background: #E74B37;
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
<body>
	
	<div class="w3-bar w3-black navbar-fixed-top glcwTeXYen">
		<button class="w3-button w3-dark-grey w3-xlarge w3-left" onclick="openLeftMenu()">&#9776;</button>
		<a href="<%=request.getContextPath()%>/index.jsp" class="w3-bar-item w3-button w3-xlarge w3-left glfont"><span id="hermitHome">Hermit</span></a>
		
		<!-- 如果有登入就不顯示 -->
		<c:if test="${empty LoginOK}">
 		<a href="<%=request.getContextPath()%>/register/register_select_page.jsp" class="w3-bar-item w3-button w3-xlarge w3-right w3-margin-right" ><span id="hermitHome">註冊</span></a>
 		<span class="w3-bar-item  w3-xlarge w3-right" id="hermitHome">|</span>
 		</c:if>
		
 		<!-- 如果有登入就顯示登出 -->
		<c:if test="${!empty LoginOK}">
 			<a href="http://localhost:8081/hermit/MemberLogin/Logout.jsp" class="w3-bar-item w3-button w3-xlarge w3-right w3-margin-right" ><span id="hermitHome">登出</span></a>
 		</c:if>
 		
 		<!-- 如果有登入就不顯示 -->
		<c:if test="${empty LoginOK}">
			<nav class="main-nav">
				<ul>
					<li>
					 <a class="cd-signin" href="#0" style="font-size: 24px;margin-top: 8px;border:none;">登入</a>
					</li>
				</ul>
			</nav>
		</c:if>
 			
		<div class="w3-sidebar w3-bar-block w3-animate-left navbar-fixed-top w3-dark-gray" style="color:white;display:none;font-size:20px;font-family:Microsoft JhengHei;" id="leftMenu">
		<button onclick="closeLeftMenu()" class="w3-bar-item w3-button w3-large"><span>Close &times</span></button>
		<a href="<%=request.getContextPath()%>/index.jsp" class="w3-bar-item w3-button"><span>首頁</span></a>
		<a href="<%=request.getContextPath()%>/memberbackstage/mem_back_index.jsp" class="w3-bar-item w3-button"><span>會員中心</span></a>
		<a href="<%=request.getContextPath()%>/memberbackstage/mem_back_favorite.jsp" class="w3-bar-item w3-button"><span>我的收藏</span></a>
		<a href="<%=request.getContextPath()%>/memberbackstage/mem_back_calendar.jsp" class="w3-bar-item w3-button"><span>我的預約</span></a>
		<a href="<%=request.getContextPath()%>/memberbackstage/mem_back_qanda.jsp" class="w3-bar-item w3-button"><span>Q&A</span></a>
		<a href="<%=request.getContextPath()%>/memberbackstage/mem_back_lease.jsp" class="w3-bar-item w3-button"><span>租賃紀錄</span></a>
		<a href="<%=request.getContextPath()%>/memberbackstage/mem_back_reset.jsp" class="w3-bar-item w3-button"><span>修改會員資料</span></a>
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
	
	<!-- 登入 -->
	<div class="cd-user-modal" id="loginmodal">
		<!-- this is the entire modal form, including the background -->
		<div class="cd-user-modal-container">
			<!-- this is the container wrapper -->
			<ul class="cd-switcher">
				<li><a href="#0" class="selected">Sign in</a></li>
			</ul>

			<div id="cd-login">
				<!-- log in form -->
				<form class="cd-form" id="loginform" action="<c:url value='/Login/memlogin.do?action=login'/>" method="POST">
					<p class="fieldset">
						<label class="image-replace cd-username" for="signup-username">Account</label>
						<input class="full-width has-padding has-border" name="account"
							id="account" type="text" placeholder="Account"
							value="${cookie.account.value}"> <small><font
							color="red" size="-1" id="putacc"></font></small>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="signin-password">Password</label>
						<input class="full-width has-padding has-border" name="pwd"
							id="pwd" type="password" placeholder="Password"
							value="${cookie.pwd.value}"> <small><font
							color="red" size="-1" id="putpwd"></font></small>
					</p>

					<p class="fieldset">
						<small><font color="red" size="-1" id="loginErr"></font></small>
					</p>

					<div>
						<img id="image" align="center" style="margin-left: 30px"
							border="0" onclick="refresh()"
							src="<%=request.getContextPath()%>/MemberLogin/Image.jsp"
							title="點擊更換圖片"><br />
					</div>

					<p class="fieldset">
						<label class="" for="">請輸入驗證碼:</label> <input type="text"
							id="code" maxlength="6" size="10"><small><font
							color="red" size="-1" id="putver"></font></small>
					</p>

					<p id="rememberBtn" class="remember-box">
						<input type="checkbox" id="remember" ${cookie.flag.value}>
						<label for="remember">Remember me</label>
					</p>

					<p class="fieldset">
						<button class="loginBtn loginBtn--facebook" type="button"
							id="facebook" style="margin-left: 55px">Login with
							Facebook</button>

						<button class="loginBtn loginBtn--google" type="button"
							id="google">Login with Google</button>
					</p>

					<p class="fieldset">
						<input class="full-width" type="submit" value="Login"
							id="submitBtn">
						<!-- <button class="full-width" type="button" id="submitBtn" value="Login">Login</button> -->
					</p>
				</form>

				<p class="cd-form-bottom-message">
					<a href="#0">Forgot your password?</a>
				</p>
			</div>

			<div id="cd-reset-password">
				<!-- reset password form -->
				<p class="cd-form-message">Lost your password? Please enter your
					Account. You will receive a link to create a new password.</p>
					
				<form class="cd-form" method="POST">
				
					<p class="fieldset">
						<label class="image-replace cd-username" for="reset-account">Account</label>
						<input class="full-width has-padding has-border" name="findByAccount" id="resetAccount"
							type="text" placeholder="Account">
						<small><font color="red" size="-1" id="reseterror"></font></small>
					</p>

					<p class="fieldset">
						<input class="full-width has-padding" type="button" 
						value="Reset password" id="submitReset">
					</p>
				</form>

				<p class="cd-form-bottom-message">
					<a href="#0">Back to log-in</a>
				</p>
			</div>
			<!-- cd-reset-password -->
		</div>
		<!-- cd-user-modal-container -->
	</div>
	<!-- cd-user-modal -->

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