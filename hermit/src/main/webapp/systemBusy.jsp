<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系統忙碌中</title>
<jsp:include page="/fragment/member_page.jsp"></jsp:include>
<style>
	a:hover{
		font-size: 1em;
	}
</style>
</head>
<body>
	<div class="container"  style="text-align: center">
		<div>
			<img style="hight:256px;width:256px;margin:10% auto"  src="<%= request.getContextPath()%>/images/speech-balloon-outline-with-exclamation-mark.png">
		</div>
		<div>
			<h3><span>系統現在正在忙碌中，3 秒後自動為您切換回上一頁，若未跳轉請點擊</span><a id="back" style="color:#00BBFF;margin-left: 0px;padding:0px" href="javascript:;">這裡</a></h3>
		</div>
	</div>
	<script>
		$(function(){
			$("#back").on("click",function(){
				history.back();
			})
			function goBack(){
				history.back();
			}
			
			setTimeout(goBack,3000);
			
		})
	</script>
</body>
</html>