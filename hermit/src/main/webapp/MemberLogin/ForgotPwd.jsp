<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重新設置密碼申請</title>
</head>
<body>
	<center>
		<form action=<c:url value='/Login/forgotpwd.do'/> method="POST">
			<table width="400" border="1">

				<tr>
					<td align="right" width="130">請輸入您的帳號:</td>
					<td align="left"><input type="text" name="findByAccount"
						size="10"><small><font color="red" size="-1">${requestScope.errorMsg}</font></small></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交" /></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>