<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>後台管理首頁</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/back_index.css" />
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#submitBtn").click(function(){
		var box;
		if($("#remember").prop("checked")){
			box = "on";
		}
		$.post('<%=request.getContextPath()%>/Login/emplogin.do',{account:$("#account").val(),pwd:$("#pwd").val(),remember:box},function(data){
			
			if(data == "ok"){
				window.location = "<%=request.getContextPath()%>/mention/mentionIndex.jsp";
			}
			
			var datas = data.split(";");
			for(var d of datas){
				var s = d.split(".")[0];
				var a = d.split(".")[1];
				if(s == "1"){
					$("#putacc").text(a);
				}else if(s == "2"){
					$("#putpwd").text(a);
				}else if(s == "3"){
					$("#loginErr").text(a);
				}
			}
		})
	})
})
</script>
</head>
<body class="loading">

	<div id="wrapper1">
		<div id="bg"></div>
		<div id="overlay"></div>
		<div id="main">

			<!-- Header -->
			<header id="header">
			<h1>Employee Login</h1>
			<nav>

			<center>
				<form action="<c:url value='<%=request.getContextPath()%>/Login/emplogin.do'/>"
					method="POST">
					<table width="400" border="1">
						<tr>
							<td align="right"><span style="padding-right: 5px">Account:</span></td>
							<td><input type="text"
								style="color: #D200D2; font-size: 20px; background-color: transparent; border-width: 1; font-family: Microsoft JhengHei;"
								id="account" value="${cookie.account.value}" size="13"><font
								color="red" id="putacc" style="font-weight: bold;"></font></td>
						</tr>

						<tr>
							<td align="right"><span style="padding-right: 5px">Password:</span></td>
							<td><input type="password"
								style="color: #D200D2; font-size: 20px; background-color: transparent; border-width: 1; font-family: Microsoft JhengHei;"
								id="pwd" value="${cookie.pwd.value}" size="13"><font
								color="red" id="putpwd" style="font-weight: bold;"></font></td>
						</tr>

						<tr>
							<td align="center" colspan="3"><font color="red" size="-1"
								id="loginErr"></font></td>
						</tr>
					</table>
					<div id="rememberBtn" class="remember-box">
						<span>Remember me</span><input type="checkbox" id="remember"
							${cookie.flag.value}>
					</div>
					<ul>
						<li><a href="#" class="icon fa-user-o" id="submitBtn"><span
								class="label">Login</span></a></li>
					</ul>
				</form>
			</center>
			</nav> </header>
			<!-- Footer -->
			<footer id="footer"> <span class="copyright">&copy;
				Hermit </span> </footer>

		</div>
	</div>
	<script>
		window.onload = function() {
			document.body.className = '';
		}
		window.ontouchmove = function() {
			return false;
		}
		window.onorientationchange = function() {
			document.body.scrollTop = 0;
		}
	</script>
</body>
</html>