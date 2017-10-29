<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>
<title>A&Q</title>
<style>
	#answerForm{
		display:none;
	}
</style>
<script>
	$(document).ready(work);
	
	function work(){
		$("#showTable>tbody button").on("click",function(){
			$("#qaNO").val($(this).parents("tr").find("td").eq(0).text());
			$("#answerForm").toggle(true);
		})
	}
	
	function cancel(){
		$("#answerForm").toggle(true);
	}
	
	function check(){
		if(confirm("是否確認送出此回覆?")){
			document.getElementById("answerForm").submit();
		}
	}
</script>
</head>
<body>
	<!-- 顯示未回覆回報 -->
	<table id="showTable">
		<thead>
			<tr>
				<th>留言編號</th><th>留言時間</th><th>留言會員</th><th>房屋連結</th><th>留言內容</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="qaVO" items="${array}">
				<tr>
					<td>${qaVO.qaNO}</td>
					<td>${qaVO.qTime}</td>
					<td>${qaVO.memberVO.memName}</td>
					<td><a href="${qaVO.houseVO.houseNO}">${qaVO.houseVO.houseTitle}</a></td>
					<td>${qaVO.qDetail}</td>
					<td><button type="button">回覆</button></tr>
				<tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 回覆欄位 -->
	<form action="<%=request.getContextPath()%>/QAndAServlet?mission=update" method="POST" id="answerForm">
		留言編號：<input type="text" readonly="readonly" name="qaNO" value="" id="qaNO"/>
		回覆內容：<textarea name="aDetail" style="resize:none;"></textarea>
		<button type="button" onclick="check()">提交</button>
		<button type="button" onclick="cancel()">取消</button>
	</form>
</body>
</html>