<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/reset.css">
<!-- CSS reset -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<!-- Gem style -->
<script src="<%=request.getContextPath()%>/js/modernizr.js"></script>
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>
<!-- Modernizr -->

<script type="text/javascript">
	function refresh() {
		document.getElementById("image").src = "../MemberLogin/Image.jsp?"
				+ new Date();
	}
	
	$(document).ready(function(){
		$("#submitBtn").click(function(){
			var box;
			// 清除錯誤訊息
			$("#putacc").text("");
			$("#putpwd").text("");
			$("#putver").text("");
			$("#loginErr").text("");
			if($("#remember").prop("checked")){
				box = "on";
			}
			$.post('/hermit/Login/memlogin.do',{account:$("#account").val(),pwd:$("#pwd").val(),code:$("#code").val(),remember:box},function(data){
				
				if(data == "ok"){
					window.location = "/hermit/MemberLogin/LoginSuccess.jsp";
				}
				
				var datas = data.split(";");
				for(var d of datas){
					var s = d.split(".")[0];
					var a = d.split(".")[1];
					if(s == "1"){
						$("#putacc").text(a);
					}else if(s == "2"){
						$("#putpwd").text(a);
					}else if(s == "3"){
						$("#putver").text(a);
					}else if(s == "4"){
						$("#putver").text(a);
					}else if(s=="5"){
						$("#loginErr").text(a);
					}
				}
			})
		})
	})
	
	$(function(){
		
		var G_CLIENT_ID = "538877171960-djc145ihldt91ec28hajlt5m66sis16g.apps.googleusercontent.com";
		var G_REDIRECT_URL = "http://localhost:8081/hermit/identity.do?action=google_login_Action";
		var G_SCOPE = 'https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.profile';
		
		var F_CLIENT_ID = "1719931494697481";
		var F_REDIRECT_URL = "http://localhost:8081/hermit/identity.do?action=facebook_login_Action";
		var F_SCOPE = 'email';
		
		
		$("#google").click(function(){
			window.location='https://accounts.google.com/o/oauth2/auth?response_type=code&state=/profile&client_id='+G_CLIENT_ID+'&redirect_uri='+G_REDIRECT_URL+'&scope='+G_SCOPE;
		})
		
		$("#facebook").click(function(){
			window.location='https://www.facebook.com/v2.10/dialog/oauth?response_type=code&state=/profile&client_id='+F_CLIENT_ID+'&redirect_uri='+F_REDIRECT_URL+'&scope='+F_SCOPE;
		})

		
		/* $('#google').attr('href','https://accounts.google.com/o/oauth2/auth?response_type=code&state=/profile&client_id='+G_CLIENT_ID+'&redirect_uri='+G_REDIRECT_URL+'&scope='+G_SCOPE);
		$('#facebook').attr('href','https://www.facebook.com/v2.10/dialog/oauth?response_type=code&state=/profile&client_id='+F_CLIENT_ID+'&redirect_uri='+F_REDIRECT_URL+'&scope='+F_SCOPE); */
	})
</script>
<style type="text/css">
body {
	padding: 2em;
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

.divstyle {
	margin: 0px auto;
}
</style>
</head>
<body>
	<header role="banner">
	<div id="cd-logo">
		<a href="#0"><img
			src="<%=request.getContextPath()%>/images/cd-logo.svg" alt="Logo"></a>
	</div>

	<nav class="main-nav">
	<ul>
		<!-- inser more links here -->
		<li><a class="cd-signin" href="#0">Sign in</a></li>
		<li><a class="cd-signup" href="#0">Sign up</a></li>
	</ul>
	</nav> </header>

	<%
		if (request.getSession().getAttribute("LoginOK") != null) {
			out.print("<script>window.location='LoginSuccess.jsp'</script>");
		}
	%>

	<div class="cd-user-modal">
		<!-- this is the entire modal form, including the background -->
		<div class="cd-user-modal-container">
			<!-- this is the container wrapper -->
			<ul class="cd-switcher">
				<li><a href="#0">Sign in</a></li>
			</ul>

			<div id="cd-login">
				<!-- log in form -->
				<form class="cd-form" action=<c:url value='/Login/login.do'/>
					method="POST">
					<p class="fieldset">
						<label class="image-replace cd-username" for="signup-username">Username</label>
						<input class="full-width has-padding has-border" name="account"
							id="account" type="text" placeholder="Username"
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
						<input class="full-width" type="submit" value="Login"
							id="submitBtn">
						<!-- <button class="full-width" type="button" id="submitBtn" value="Login">Login</button> -->
					</p>
				</form>

				<p class="cd-form-bottom-message">
					<a href="#0">Forgot your password?</a>
				</p>

				<p class="fieldset">
					<button class="loginBtn loginBtn--facebook" type="button"
						id="facebook" style="margin-left: 88px">Login with
						Facebook</button>

					<button class="loginBtn loginBtn--google" type="button" id="google">Login
						with Google</button>
				</p>
				<!-- <a href="#0" class="cd-close-form">Close</a> -->
			</div>

			<div id="cd-reset-password">
				<!-- reset password form -->
				<p class="cd-form-message">Lost your password? Please enter your
					email address. You will receive a link to create a new password.</p>

				<form class="cd-form">
					<p class="fieldset">
						<label class="image-replace cd-email" for="reset-email">E-mail</label>
						<input class="full-width has-padding has-border" id="reset-email"
							type="email" placeholder="E-mail"> <span
							class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<input class="full-width has-padding" type="submit"
							value="Reset password">
					</p>
				</form>

				<p class="cd-form-bottom-message">
					<a href="#0">Back to log-in</a>
				</p>
			</div>
			<!-- cd-reset-password -->
			<a href="#0" class="cd-close-form">Close</a>
		</div>
		<!-- cd-user-modal-container -->
	</div>
	<!-- cd-user-modal -->
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/main.js"></script>
	<!-- Gem jQuery -->

</body>
</html>