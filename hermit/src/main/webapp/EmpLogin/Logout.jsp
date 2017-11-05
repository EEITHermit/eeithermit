<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:remove var="empLoginOK"/>
<%
	session.invalidate();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>員工登出</title>
<script>
window.location="<%=request.getContextPath()%>/back_index_page.jsp";
</script>
</head>
<body>
<a href="<%=request.getContextPath()%>/back_index_page.jsp">登入</a>
</body>
</html>