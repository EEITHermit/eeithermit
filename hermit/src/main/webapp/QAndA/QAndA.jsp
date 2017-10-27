<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A</title>
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>
<style>
	#queryTable{
 		display:none; 
	}
	#commentForm{
  		display:none;  
	}
</style>
<script>
	document.addEventListener("DOMContentLoaded",work);
	
	function work(){
		$("#queryBT").click(function(){
			$("#queryTable").toggle(true);
			$("#commentForm").toggle(false);
		});
		$("#commentBT").click(function(){
			$("#queryTable").toggle(false);
			$("#commentForm").toggle(true);
		});
	};
	
	function check(){
		if(confirm("是否確認送出投訴")){
			(document.getElementById("commentForm")).submit();
		}
	};
</script>
</head>
<body>
	<!-- 模式選擇按鈕 -->
	<button id="queryBT">查詢留言</button>
	<button id="commentBT">客服申請</button>
	<!-- 查詢留言區域 -->
	<table id="queryTable">
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
	<!-- 客服申請區域 -->
	<form id="commentForm" action="<%=request.getContextPath()%>/QAndAServlet?mission=insert&type=0" method="POST">
		請選擇房屋：<select name="houseNO">
			<option>請選擇</option>
			<!-- 假資料  待hibernate做完補上-->
			<option value="20001">大馬路</option>
			<!-- filter會傳來houseArray 為此會員所租賃的房屋 -->
			<c:forEach var="houseVO" items="${houseArray}">
				<option value="${houseVO.houseNO}">${houseVO.houseAddr}</option>
			</c:forEach>
		</select>
		申訴內容：<textarea name="qDetail" style="resize:none;"></textarea>
		<input type="button" value="提交" onclick="check()"/>
	</form>
</body>
</html>