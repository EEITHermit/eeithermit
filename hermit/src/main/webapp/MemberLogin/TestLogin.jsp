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
			if($("#remember").prop("checked")){
				box = "on";
			}
			$.post('/Hermit/Login/memlogin.do',{account:$("#account").val(),pwd:$("#pwd").val(),code:$("#code").val(),remember:box},function(data){
				
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

		
		$('#google').attr('href','https://accounts.google.com/o/oauth2/auth?response_type=code&state=/profile&client_id='+G_CLIENT_ID+'&redirect_uri='+G_REDIRECT_URL+'&scope='+G_SCOPE);
		$('#facebook').attr('href','https://www.facebook.com/v2.10/dialog/oauth?response_type=code&state=/profile&client_id='+F_CLIENT_ID+'&redirect_uri='+F_REDIRECT_URL+'&scope='+F_SCOPE);
	})
</script>
</head>
<body>
	<header role="banner">
	<div id="cd-logo">
		<a href="#0"><img
			src="<%=request.getContextPath()%>/img/cd-logo.svg" alt="Logo"></a>
	</div>

	<nav class="main-nav">
	<ul>
		<!-- inser more links here -->
		<li><a class="cd-signin" href="#0">Sign in</a></li>
		<li><a class="cd-signup" href="#0">Sign up</a></li>
	</ul>
	</nav> </header>

	<div class="cd-user-modal">
		<!-- this is the entire modal form, including the background -->
		<div class="cd-user-modal-container">
			<!-- this is the container wrapper -->
			<ul class="cd-switcher">
				<li><a href="#0">Sign in</a></li>
				<li><a href="#0">New account</a></li>
			</ul>

			<div id="cd-login">
				<!-- log in form -->
				<form class="cd-form" action=<c:url value='/Login/login.do'/>
					method="POST">
					<p class="fieldset">
						<label class="image-replace cd-username" for="signup-username">Username</label>
						<input class="full-width has-padding has-border" name="account"
							id="signup-username" type="text" placeholder="Username">
						<span class="cd-error-message">${ErrorMsgKey.AccountEmptyError}</span>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="signin-password">Password</label>
						<input class="full-width has-padding has-border" name="pwd"
							id="signin-password" type="password" placeholder="Password">
						<span class="cd-error-message">${ErrorMsgKey.PwdEmptyError}</span>
					</p>

					<p class="fieldset">
						<span class="cd-error-message">${ErrorMsgKey.LoginError}&nbsp;</span>
					</p>

					<p class="fieldset">
						<label class="" for="signin-password">請輸入驗證碼:</label> <input
							type="text" name="code" maxlength="6" size="10"> <img
							id="image" align="middle" border="0" onclick="refresh()"
							src="<%=request.getContextPath()%>/MemberLogin/Image.jsp"
							title="點擊更換圖片">
					</p>

					<p id="rememberBtn" class="remember-box">
						<input type="checkbox" id="remember"> <label
							for="remember">Remember me</label>
					</p>

					<p class="fieldset">
						<input class="full-width" type="submit" value="Login" id="submitBtn">
						<!-- <button class="full-width" type="button" id="submitBtn" value="Login">Login</button> -->
					</p>
				</form>

				<p class="cd-form-bottom-message">
					<a href="#0">Forgot your password?</a>
				</p>
				<!-- <a href="#0" class="cd-close-form">Close</a> -->
			</div>
			<!-- cd-login -->

			<div id="cd-signup">
				<!-- sign up form -->
				<form class="cd-form">
					<p class="fieldset">
						<label class="image-replace cd-username" for="signup-username">Username</label>
						<input class="full-width has-padding has-border"
							id="signup-username" type="text" placeholder="Username">
						<span class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-email" for="signup-email">E-mail</label>
						<input class="full-width has-padding has-border" id="signup-email"
							type="email" placeholder="E-mail"> <span
							class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="signup-password">Password</label>
						<input class="full-width has-padding has-border"
							id="signup-password" type="text" placeholder="Password">
						<a href="#0" class="hide-password">Hide</a> <span
							class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<input type="checkbox" id="accept-terms"> <label
							for="accept-terms">I agree to the <a href="#0">Terms</a></label>
					</p>

					<p class="fieldset">
						<input class="full-width has-padding" type="submit"
							value="Create account">
					</p>
				</form>

				<!-- <a href="#0" class="cd-close-form">Close</a> -->
			</div>
			<!-- cd-signup -->

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