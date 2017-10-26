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
	<center>
		<form action=<c:url value='/Login/resetpwd.do'/> method="POST">
			<table width="600" border="1">

				<tr>
					<td align="center">帳號:</td>
					<td width="400"><input type="text" name="account"
						value="<%=request.getParameter("account")%>" readonly="readonly" /></td>
				</tr>

				<tr>
					<td align="center"><font style="color: red;">*</font>請輸入新密碼:</td>
					<td><input type="password" name="newPassword" /><small><font
							color="red" size="-1">${errors.newPassword}</font></small></td>
				</tr>

				<tr>
					<td align="center"><font style="color: red;">*</font>請確認新密碼:</td>
					<td><input type="password" name="newPassword2" /><small><font
							color="red" size="-1">${errors.newPassword2}</font></small></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><small><font
							color="red" size="-1">${errors.passwordError}</font></small></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit" value="修改"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>