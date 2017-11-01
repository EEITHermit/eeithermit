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
		<form action=<c:url value='/Login/resetpwd.do'/> method="POST">
			<div style="padding-top: 80px">
				<table width="400" border="0">
					<tr>
						<td align="center">帳號:</td>
						<td width="250"><input type="text" name="account"
							value="<%=request.getParameter("account")%>" readonly="readonly" />
							<font>&nbsp;</font></td>
					</tr>

					<tr>
						<td><font>&nbsp;</font></td>
					</tr>


					<tr>
						<td align="center"><font style="color: red;">*</font>請輸入新密碼:</td>
						<td><input type="password" name="newPassword" /><br /> <small><font
								color="red" size="-1">${errors.newPassword}</font></small></td>
					</tr>

					<tr>
						<td><font>&nbsp;</font></td>
					</tr>

					<tr>
						<td align="center"><font style="color: red;">*</font>請確認新密碼:</td>
						<td><input type="password" name="newPassword2" /><font>&nbsp;</font><br />
							<small><font color="red" size="-1">${errors.newPassword2}</font></small></td>
					</tr>

					<tr>
						<td><font>&nbsp;</font></td>
					</tr>

					<tr>
						<td colspan="2" align="center"><small><font
								color="red" size="-1">${errors.passwordError}&nbsp;</font></small></td>
					</tr>

					<tr>
						<td><font>&nbsp;</font></td>
					</tr>

					<tr>
						<td colspan="2" align="center"><input
							style="width: 50px; height: 30px" type="submit" value="修改"></td>
					</tr>
				</table>
			</div>
		</form>
	</center>
</body>
</html>