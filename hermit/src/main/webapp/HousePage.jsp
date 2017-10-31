<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:include page="/fragment/member_page.jsp" />
<link href="<%=request.getContextPath()%>/css/jquery.scrolling-tabs.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/jquery.otg-carousel.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/jquery.scrolling-tabs.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.otg-carousel.js"></script>
</head>
<body>
	<div class="container" style="margin-top:5%">
		<div class="col-md-7">
			<div id="carousel">
			</div>
		</div>
		<div style="height:1500px;width:25%;border:1px solid;border-radius: 10px;margin:50px auto;float:right">
		</div>
	</div>
	<script>
		function loadCarousel(){
			$('#carousel').carousel({
			  images: [
			    {src: 'http://www.porterdavis.com.au/~/media/homes/vienna%20h/vienna%20h%2021/facades/vienna_21_albion.jpg?w=582&amp;h=320&amp;crop=1', caption: ''},
			    {src: 'https://i.pinimg.com/736x/1e/1e/ca/1e1eca7542a9ab50af82b6fc19594fc4--my-dream-house-future-house.jpg', caption: ''},
			    {src: 'https://content.usaa.com/mcontent/static_assets/Media/adv_advice-home-homeownershipcosts_img.jpg?cacheid\u003d2055174046_p', caption: ''},
			  ],  
			  currentImageIndex: 0,
	          useDots: true,
	          useThumbnails: true,
	          useCaptions: true,
	          useArrows: false,
	          interval: 10000
			});
			
			$('#carousel').carousel('stopTimer');;
		}
		loadCarousel();
	</script>
</body>
</html>