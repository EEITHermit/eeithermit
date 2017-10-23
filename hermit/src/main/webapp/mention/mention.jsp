<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>員工首頁</title>
<script>
	
</script>
</head>
<body>
	<c:forEach var="resVO" items="${resArray}">
	<p>${resVO.reservationNo}</p><br/>
	<p>${resVO.applyTime}</p><br/>
	<p>${resVO.houseVO}</p><br/>
	<p>${resVO.memberVO}</p><br/>
	</c:forEach>
</body>
</html>