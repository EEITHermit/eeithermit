<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>員工登入</title>
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#submitBtn").click(function(){
		var box;
		if($("#remember").prop("checked")){
			box = "on";
		}
		$.post('/Hermit/Login/emplogin.do',{account:$("#account").val(),pwd:$("#pwd").val(),remember:box},function(data){
			
			if(data == "ok"){
				window.location = "/hermit/EmpLogin/LoginSuccess.jsp";
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
					$("#loginErr").text(a);
				}
			}
		})
	})
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
		<form action=<c:url value='/Login/emplogin.do'/> method="POST">
			<table width="400" border="1">
				<tr>
					<th colspan='2'>
						<H1>員工登入</H1>
					</th>
				</tr>

				<tr>
					<td align="right">帳號:</td>
					<td align="left" width="250"><input type="text" id="account"
						value="${cookie.account.value}" size="10"><small><font
							color="red" size="-1" id="putacc"></font></small></td>
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
					<td colspan="2" align="center"><button type="button"
							id="submitBtn" value="送出">送出</button></td>
				</tr>
			</table>
			<div id="rememberBtn" class="remember-box">
				<span>記住密碼</span><input type="checkbox" id="remember" ${cookie.flag.value}>
			</div>
		</form>
	</center>

</body>
</html>