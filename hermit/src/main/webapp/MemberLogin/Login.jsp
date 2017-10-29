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

		
		$('#google').attr('href','https://accounts.google.com/o/oauth2/auth?response_type=code&state=/profile&client_id='+G_CLIENT_ID+'&redirect_uri='+G_REDIRECT_URL+'&scope='+G_SCOPE);
		$('#facebook').attr('href','https://www.facebook.com/v2.10/dialog/oauth?response_type=code&state=/profile&client_id='+F_CLIENT_ID+'&redirect_uri='+F_REDIRECT_URL+'&scope='+F_SCOPE);
	})
</script>
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
					<td colspan="2" align="center"><a href="#" id="google">google登入</a>
						<a href="#" id="facebook">fackbook登入</a></td>
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