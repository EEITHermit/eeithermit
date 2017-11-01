<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Web Socket foreground Test</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
</head>
<body>
	<div id="websocketdiv">
		<textarea id="area"
			style="font-size: 20px; font-family: '微軟正黑體'; margin-top: 20px;"
			readonly="readonly" rows="10" cols="42"></textarea>
		<input type="text" id="text" size="53" /><input id="sendmsg"
			type="button" value="送出" />
	</div>
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script>
		var websocket = new WebSocket("ws://" + location.host
				+ "/hermit/websocket.do");
		websocket.onopen = function processOpen() {
		};
		websocket.onmessage = function(message) {
			var jsonData = JSON.parse(message.data);
			if (jsonData.message != null) {
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