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
</head>
<body class="loading">
	<div id="wrapper">
		<div id="bg"></div>
		<div id="overlay"></div>
		<div id="main">

			<!-- Header -->
			<header id="header">
			<h1>Adam Jensen</h1>
			<p>Security Chief &nbsp;&bull;&nbsp; Cyborg &nbsp;&bull;&nbsp;
				Never asked for this</p>
			<nav>
			<ul>
				<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
				<li><a href="#" class="icon fa-facebook"><span
						class="label">Facebook</span></a></li>
				<li><a href="#" class="icon fa-dribbble"><span
						class="label">Dribbble</span></a></li>
				<li><a href="#" class="fa fa-user-circle-o"><span class="label">Github</span></a></li>
				<li><a href="#" class="icon fa-bell-o"><span
						class="label">Email</span></a></li>
			</ul>
			</nav> </header>

			<!-- Footer -->
			<footer id="footer"> <span class="copyright">&copy;
				Untitled. Design: <a href="http://html5up.net">HTML5 UP</a>.
			</span> </footer>

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