<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員登入</title>
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>
<script type="text/javascript">
	function refresh() {
		document.getElementById("image").src = "../MemberLogin/Image.jsp?"
				+ new Date();
	}
	
	$(document).ready(function(){
		$("#submitBtn").click(function(){
			var box;
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
	width: 13em;  /* - apply for fixed size */
	margin: 0.2em;
	padding: 0 15px 0 46px;
	border: none;
	text-align: left;
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
</style>
</head>
<body>
	<%
		if (request.getSession().getAttribute("LoginOK") != null) {
			out.print("<script>window.location='LoginSuccess.jsp'</script>");
		}
	%>
	<center>
		<form action=<c:url value='/Login/memlogin.do'/> method="POST">
			<table width="400" border="1">
				<tr>
					<th colspan='2'>
						<H1>會員登入</H1>
					</th>
				</tr>

				<tr>
					<td align="right">帳號:</td>
					<td align="left" width="250"><input type="text" id="account"
						value="${cookie.account.value}" size="10"><small><font
							color="red" size="-1" id="putacc"></font></small></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button
							class="loginBtn loginBtn--facebook" type="button" id="facebook">Login with Facebook
						</button>

						<button class="loginBtn loginBtn--google" type="button" id="google">Login with
							Google</button></td>
				</tr>

				<tr>
					<td align="right">密碼:</td>
					<td align="left"><input type="password" id="pwd"
						value="${cookie.pwd.value}" size="10"><small><font
							color="red" size="-1" id="putpwd"></font></small></td>
				</tr>

				<tr>
					<td align="center" colspan="3"><font color="red" size="-1"
						id="loginErr"></font></td>
				</tr>

				<tr>
					<td align="right">驗證碼:</td>
					<td><input type="text" id="code" maxlength="6" size="10"><img
						id="image" align="center" border="0" onclick="refresh()"
						src="../MemberLogin/Image.jsp" title="點擊更換圖片"><br /> <small><font
							color="red" size="-1" id="putver"></font></small></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><button type="button"
							id="submitBtn" value="送出">送出</button></td>
				</tr>
			</table>
			<div id="rememberBtn" class="remember-box">
				<span>記住密碼</span><input type="checkbox" id="remember"
					${cookie.flag.value}>
			</div>
			<div>
				<div>
					<a href="http://localhost:8081/Hermit/MemberLogin/ForgotAcc.jsp">忘記帳號?</a>
					<a href="http://localhost:8081/Hermit/MemberLogin/ForgotPwd.jsp">忘記密碼?</a>
				</div>
			</div>
		</form>
	</center>
</body>
</html>