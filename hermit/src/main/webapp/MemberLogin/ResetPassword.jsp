<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ResetPwd</title>
</head>
<body>
	<jsp:include page="/fragment/member_page.jsp"></jsp:include>
	<center>
		<div style="height: 80px"></div>
		<div class="container">
			<form action=<c:url value='/Login/resetpwd.do'/> method="POST">
				<table width="600">

					<tr>
						<td align="right">帳號:</td>
						<td width="400"><input type="text" name="account"
							value="<%=request.getParameter("account")%>" readonly="readonly" /></td>
					</tr>

					<tr>
						<td align="right"><font style="color: red;">*</font>請輸入新密碼:</td>
						<td><input type="password" name="newPassword" /><small><font
								color="red" size="-1">${errors.newPassword}</font></small></td>
					</tr>

					<tr>
						<td align="right"><font style="color: red;">*</font>請確認新密碼:</td>
						<td><input type="password" name="newPassword2" /><small><font
								color="red" size="-1">${errors.newPassword2}</font></small></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="修改"></td>
					</tr>
				</table>
					<label>帳號</label>
					<input>
					<label>請輸入新密碼</label>
					<input>
					<label>請確認新密碼</label>
					<input>
					
			</form>
		</div>
		<footer
			class="navbar-fixed-bottom w3-black container-fluid text-center">
		<div>
			<ul class="nav nav-pills w3-centered "
				style="display: flex; font-size: 13px; justify-content: center;">
				<li role="presentation"><a
					href="<%=request.getContextPath()%>/index.jsp">關於我們</a></li>
				<li role="presentation"><a
					href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></li>
				<li role="presentation"><a
					href="<%=request.getContextPath()%>/register/law_service_page.jsp">服務條款</a></li>
				<li role="presentation"><a
					href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">隱私權聲明</a></li>
			</ul>
		</div>
		<span class="text-center"><p style="font-size: 10px">赫米特開發團隊
				Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
		</div>
		</footer>
	</center>
</body>
</html>