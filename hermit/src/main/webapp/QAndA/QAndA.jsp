<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A</title>
</head>
<body>
	<!-- 模式選擇按鈕 -->
	<button id="queryBT">查詢留言</button>
	<button id="commentBT">客服申請</button>
	<!-- 查詢留言區域 -->
	<table>
		<thead>
			<tr>
				<th>留言時間</th><th>留言類型</th><th>房屋連結</th><th>留言內容</th><th>回覆時間</th><th>回覆內容</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="qaVO" items="${array}">
				<tr>
					<td>${qaVO.qTime}</td>
					<c:if test="${qaVO.qaType == 0}">
						<td>客服</td>
					</c:if>
					<c:if test="${qaVO.qaType == 1}">
						<td>詢問</td>
					</c:if>
					<td><a href="${qaVO.houseVO.houseNO}">${qaVO.houseVO.houseTitle}</a></td>
					<td>${qaVO.qDetail}</td>
					<td>${qaVO.aTime}</td>
					<td>${qaVO.aDetail}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>