<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Web Socket foreground Test</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<style>
.row {
	padding-left: 20%;
	padding-right: 10%;
}
</style>
</head>
<body>
	<jsp:include page="/fragment/back_side_page.jsp" />
	<div id="container">
		<div class="row">
			<textarea id="talkarea"
				style="font-size: 1em; resize: none; color: black;"
				readonly="readonly" rows="20em" cols=190%></textarea>
			<hr />
			<input type="text" id="talktext" size=180% style="color: black" /><input
				id="sendmsg" style="color: black" type="button" value="送出" />
		</div>
	</div>

	<script>
		var area = document.getElementById('talkarea');
		var text = document.getElementById('talktext');
		var websocket = new WebSocket("ws://" + location.host
				+ "/hermit/websocket.do");

		websocket.onopen = function processOpen() {
		};

		websocket.onmessage = function(message) {
			var jsonData = JSON.parse(message.data);
			if (jsonData.message != null) {
				console.log(area);
				area.value += jsonData.message + "\n";
			}
		};

		$(function() {
			$('#sendmsg').click(function() {
				websocket.send(text.value);
				text.value = "";
			});
		});
	</script>
</body>
</html>