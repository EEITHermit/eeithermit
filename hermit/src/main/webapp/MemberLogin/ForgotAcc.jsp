<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查詢帳號的申請</title>
</head>
<body>
	<center>
		<form action=<c:url value='/Login/forgotacc.do'/> method="POST">
			<table width="400" border="1">

				<tr>
					<td align="right" width="130">您註冊的手機:</td>
					<td align="left"><input type="text" name="findByTel" size="10"><br />
					<small><font color="red" size="-1">${requestScope.TelerrorMsg}</font></small></td>

				</tr>

				<tr>
					<td align="right" width="130">您註冊的E-mail:</td>
					<td align="left"><input type="text" name="findByEmail"
						size="10"><br /> <small><font color="red"
							size="-1">${requestScope.MailerrorMsg}</font></small></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交" /></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>