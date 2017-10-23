<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊頁面</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.center-control {
	margin-left: auto;
	margin-right: auto;
	/* 	border: 3px solid red; */
}

.selectItem1 {
	margin-left: 20%;
	width: 16%;
}

.selectItem2 {
	margin-left: 10%;
	width: 12%;
}

.selectItem3 {
	margin-left: 20%;
	width: 12%;
}
</style>
</head>
<body>
	<header> <nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#myNavbar" aria-expanded="ture">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Hermit</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span>
						Home</a></li>
			</ul>
		</div>
	</div>
	</nav></header>
	<div class="container">
		<div class="row">
			<div>
				<h2>請選擇註冊方式</h2>
			</div>
			<div class="center-control">
				<span class="carouselItem"> <a href="#" id="google"><img class="selectItem2 carouselItemInner"
						src="<%=request.getContextPath()%>/images/googleplus-reflection.png" /></a>
				</span> <span class="carouselItem"> <a href="register_page.jsp"><img
						class="selectItem1 carouselItemInner"
						src="<%=request.getContextPath()%>/images/register-reflection.png" /></a>
				</span> <span class="carouselItem"> <a href="#" id="facebook"><img
						class="selectItem3 carouselItemInner"
						src="<%=request.getContextPath()%>/images/facebook-reflection.png" /></a>
				</span>
			</div>
		</div>
	</div>

	<footer class="footer"> <small>&copy; IIIEDU.GARYHSU</small> </footer>
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/TweenMax.min.js"></script>
	<script>
	var item, radius, itemLength, rY;

	$(function() {
		var G_CLIENT_ID = '538877171960-djc145ihldt91ec28hajlt5m66sis16g.apps.googleusercontent.com';
		var G_REDIRECT_URL = 'http://localhost:8081/hermit/identity.do?action=google_process_Action';
		var G_SCOPE = 'https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.profile';

		var F_CLIENT_ID = '1719931494697481';
		var F_REDIRECT_URL = 'http://localhost:8081/hermit/identity.do?action=facebook_process_Action';
		var F_SCOPE = 'email';

		$('#google').attr('href','https://accounts.google.com/o/oauth2/auth?response_type=code&state=/profile&client_id='+G_CLIENT_ID+'&redirect_uri='+G_REDIRECT_URL+'&scope='+G_SCOPE);
		
		$('#facebook').attr('href','https://www.facebook.com/v2.10/dialog/oauth?response_type=code&state=/profile&client_id='+F_CLIENT_ID+'&redirect_uri='+F_REDIRECT_URL+'&scope='+F_SCOPE);

		init();
	})

	function init() {
		item = $( '.carouselItem' );
		itemLength = $( '.carouselItem' ).length;
		rY = 360/itemLength;
		radius = Math.round((250)/Math.tan(Math.PI/itemLength));
		
		for ( var i = 0; i < itemLength; i++ )
		{
			var $item = item.eq(i);
			var $block = $item.find('.carouselItemInner');

			TweenMax.set($item, {rotationY:rY * i, z:radius, transformOrigin:"50% 50% " + radius + "px"});
			animateIn( $item, $block )						
		}
	}

	function animateIn( $item, $block ) {
		var $nrX = 360 * getRandomInt(2);
		var $nrY = 360 * getRandomInt(2);
		var $nx = -(2000) + getRandomInt( 4000 );
		var $ny = -(2000) + getRandomInt( 4000 );
		var $nz = -4000 +  getRandomInt( 4000 );

		var $s = 1.5 + (getRandomInt( 10 ) * .1);
		var $d = 1 - (getRandomInt( 8 ) * .1);

		TweenMax.set( $item, { autoAlpha:1, delay:$d } )	
		TweenMax.set( $block, { z:$nz, rotationY:$nrY, rotationX:$nrX, x:$nx, y:$ny, autoAlpha:0} );
		TweenMax.to( $block, $s, { delay:$d, rotationY:0, rotationX:0, z:0,  ease:Expo.easeInOut} );
		TweenMax.to( $block, $s-.5, { delay:$d, x:0, y:0, autoAlpha:1, ease:Expo.easeInOut} );
	}
	
	function getRandomInt( $n ) {
		return Math.floor((Math.random()*$n)+1);	
	}
	</script>
</body>
</html>