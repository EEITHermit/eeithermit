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
	margin-top: 200px; margin-left : auto;
	margin-right: auto;
	margin-left: auto;
	/* 	border: 3px solid red; */
}

.selectItem1 {
	margin-left: 80%;
	width: 72%;
}

.selectItem2 {
	margin-left: 10%;
	width: 300%;
}

.selectItem3 {
	margin-left: 180%;
	width: 300%;
}
.container {
	margin-top: 60px;
}
</style>
</head>
<body>
	<jsp:include page="/fragment/member_page.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div>
				<h2>請選擇註冊方式</h2>
			</div>
			<div class="center-control">
				<span class="carouselItem"> <a href="#" id="mygoogle"><img class="selectItem2 carouselItemInner"
						src="<%=request.getContextPath()%>/images/googleplus-reflection.png" /></a>
				</span> <span class="carouselItem"> <a href="register_page.jsp"><img
						class="selectItem1 carouselItemInner"
						src="<%=request.getContextPath()%>/images/register-reflection.png" /></a>
				</span> <span class="carouselItem"> <a href="#" id="myfacebook"><img
						class="selectItem3 carouselItemInner"
						src="<%=request.getContextPath()%>/images/facebook-reflection.png" /></a>
				</span>
			</div>
		</div>
	</div>

	<!-- /footer -->
	<footer class="navbar-fixed-bottom w3-black container-fluid text-center" >
		<div>
			<ul class="nav nav-pills w3-centered " style="display: flex;font-size:13px;justify-content: center;">
			  <li role="presentation"><a href="<%=request.getContextPath()%>/index.jsp">關於我們</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_service_page.jsp">服務條款</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">隱私權聲明</a></li>
			</ul>
		</div>
    	<span class="text-center"><p style="font-size:10px">赫米特開發團隊  Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
	</footer>
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

		$('#mygoogle').attr('href','https://accounts.google.com/o/oauth2/auth?response_type=code&state=/profile&client_id='+G_CLIENT_ID+'&redirect_uri='+G_REDIRECT_URL+'&scope='+G_SCOPE);
		
		$('#myfacebook').attr('href','https://www.facebook.com/v2.10/dialog/oauth?response_type=code&state=/profile&client_id='+F_CLIENT_ID+'&redirect_uri='+F_REDIRECT_URL+'&scope='+F_SCOPE);

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