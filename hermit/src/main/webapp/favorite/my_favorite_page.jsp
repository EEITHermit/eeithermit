<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="demoBasic">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收藏頁面</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href="https://code.jquery.com/ui/1.12.0/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet"
	href="//rawgit.com/JanStevens/angular-growl-2/master/build/angular-growl.css" />
<style>
.overlay {
	width: 100%;
	background: #384047;
	opacity: .85;
	z-index: 1;
	height: 100%;
	position: fixed;
	display: none;
}

/* The CSS styles to animate the 'push' behavior */
.push {
	margin-left: 240px;
	margin-right: -240px;
	transition: .3s ease-in;
}

a {
	text-decoration: none;
	font-weight: bold;
}

header {
	width: 5%;
	height: 100%;
	position: fixed;
	overflow: hidden;
	transition: .32s ease-out;
	z-index: 2;
	background: #384047;
	font-size: 1.25em;
	line-height: 1.75em;
	color: #384047;
	background: #384047;
	font-family: 'Lato', 'Helvetica Neue', Helvetica, Arial;
	font-weight: 400;
}

header a, .navBtn {
	color: #fff;
}

.toggle {
	width: 312px;
}

.reveal {
	opacity: 1;
	left: 0;
}

nav {
	padding-left: 360px;
	overflow: hidden;
	position: absolute;
	transition: .3s ease-out;
	top: 80px;
	left: 0;
	width: 100%;
	opacity: 0;
	height: 100%;
}

nav a {
	float: left;
	padding: 8px 0;
	opacity: .5;
	width: 100%;
	transition: .3s ease;
	font-size: 1em;
	text-transform: uppercase;
	font-weight: bold;
	line-height: 1.85em;
}

nav a:hover, .current, .toggle .navBtn {
	opacity: 1;
}

.toggle nav {
	padding-left: 60px;
	opacity: 1;
}

.navBtn {
	font-size: 2em;
	font-weight: 700;
	margin: 30px 0 0;
	width: 100%;
	text-align: center;
	line-height: 1em;
	-webkit-transform: rotate(0deg);
	transition: .3s ease-out;
	display: block;
	cursor: pointer;
	position: absolute;
	z-index: 1000001;
	opacity: .5;
	left: 0;
}

.toggle .navBtn {
	-webkit-transform: rotate(224deg);
	color: #fff;
	left: -100px;
}

.navBtn:hover {
	opacity: 1;
}

.main {
	background: #f6f6f6;
	float: right;
	width: 90%;
	margin: 0 0 0 5%;
	padding: 0;
	padding-left: 3%;
	transition: .6s ease-in;
	overflow: hidden;
	z-index: 2;
	/* 	border: 3px solid red; */
}

/* sortable style */
#sortable {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 60%;
}

#sortable li {
	margin: 0 3px 3px 3px;
	padding: 0.4em;
	padding-left: 1.5em;
	font-size: 1.4em;
	/* 	height: 18px; */
}

#sortable li span {
	position: absolute;
	margin-left: -1.3em;
}
</style>
</head>
<body>
	<div class="overlay"></div>
	<header>
	<div class="navBtn">+</div>
	<nav> <a href="">Home</a> <a href="my_favorite_page.jsp">Favorite</a>
	</nav> </header>

	<div class="main">
		<!-- do it !!!! -->
		<div ng-controller="basicDemoCtrl">
			<ul id="sortable" style="width: 85%;">
				<li class="ui-state-default"><img width="40px"
					src="<%=request.getContextPath()%>/images/yellowstar.png">Item1
					<button type="button" class="close"
						ng-click="basicUsage('warning')">&times;</button></li>
				<li class="ui-state-default"><img width="40px"
					src="<%=request.getContextPath()%>/images/yellowstar.png">Item2
					<button type="button" class="close"
						ng-click="basicUsage('warning')">&times;</button></li>
				<li class="ui-state-default"><img width="40px"
					src="<%=request.getContextPath()%>/images/yellowstar.png">Item3
					<button type="button" class="close"
						ng-click="basicUsage('warning')">&times;</button></li>
				<li class="ui-state-default"><img width="40px"
					src="<%=request.getContextPath()%>/images/yellowstar.png">Item4
					<button type="button" class="close"
						ng-click="basicUsage('warning')">&times;</button></li>
				<li class="ui-state-default"><img width="40px"
					src="<%=request.getContextPath()%>/images/yellowstar.png">Item5
					<button type="button" class="close"
						ng-click="basicUsage('warning')">&times;</button></li>
			</ul>
			<div growl></div>
		</div>
		<footer class="footer"> <small>&copy; IIIEDU.GARYHSU</small>
		</footer>
	</div>

	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/TweenMax.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular-animate.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular-sanitize.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular-resource.js"></script>
	<script
		src="//rawgit.com/JanStevens/angular-growl-2/master/build/angular-growl.js"></script>
	<script>
		$(function() {

			$("#sortable").sortable();
			$("#sortable").disableSelection();

			var toggleMenu = function() {
				$('header').toggleClass('toggle');
				$('.main').toggleClass('push');
				$('.overlay').toggleClass('block');
				$('#social, .logo').toggleClass('reveal');
			};

			//Nav
			$('.navBtn').click(function() {
				toggleMenu();
			});

			$(document).keyup(function(e) {
				if (e.keyCode == 27) { // escape key maps to keycode `27`
					// <DO YOUR WORK HERE>
					toggleMenu();
				}
			});

			//close
			$('.close').click(function() {
				$(this).parents('li').remove();
			});
		});
		
		// angular
		var demoBasic = angular.module('demoBasic',['angular-growl','ngAnimate']);
		demoBasic.config(['growlProvider',function(growlProvider) {
			growlProvider.globalTimeToLive(5000);
		}]);

		demoBasic.controller('basicDemoCtrl',['$scope','growl',function($scope, growl) {
			$scope.basicUsage = function(type) {
				var config = {};
				switch (type) {
				case "success":
					growl.success("<b>I'm</b> a success message",config);
					break;
				case "info":
					growl.info("I'm an info message",config);
					break;
				case "warning":
					growl.warning("I'm the warning message",config);
					break;
				default:
					growl.error("Ups, error message here!",config);
				}
			};
		}]);
		
	</script>
</body>
</html>