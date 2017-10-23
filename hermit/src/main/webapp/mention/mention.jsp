<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>員工首頁</title>
<script src='<%= request.getContextPath() %>/js/jquery.min.js'></script>
<script>
	document.addEventListener("DOMContentLoader",work);
	
	function work(){
		var exceptTime = $($("#resTable>tbody>tr").find("td")[2]).text();
		console.log(exceptTime);
	};
</script>
</head>
<body>
	<table id="resTable">
		<thead>
		<tr>
			<th>預約編號</th>
			<th>預約人</th>
			<th>期望時間</th>
			<th>房屋地址</th>
			<th>預約日期</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="resVO" items="${resArray}">
			<tr>
				<td>${resVO.reservationNo}</td>
				<td>${resVO.memberVO.memName}</td>
				<td>${resVO.exceptTime}</td>
				<td>${resVO.houseVO.houseAddr}</td>
				<td>${resVO.applyTime}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>