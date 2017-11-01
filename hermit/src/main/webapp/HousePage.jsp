<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="shortcut icon" href="<%= request.getContextPath() %>/favicon.ico">
<jsp:include page="/fragment/member_page.jsp" />
<link href="<%=request.getContextPath()%>/css/jquery.scrolling-tabs.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/jquery.otg-carousel.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/jquery.scrolling-tabs.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.otg-carousel.js"></script>
<style>
	tabstyle{
		border:1px solid rgba(221,221,221,0.7);
		border-radius:10px;padding:40px;
		height:500px;
		width:100%;
	}
	equidDiv{
		height:50px;
	}
	.col-md-2 span{
		vertical-align:sub;
		font-family: Microsoft JhengHei;
		font-weight: bold;
	}
	.col-md-12 span,a{
		font-family: Microsoft JhengHei;
		font-size: 1.2em;
	}
	#addressTag span{
		font-size:0.8em; 
	}
</style>
</head>
<body>
	<div class="container" style="margin-top:2%;margin-bottom:2%;padding-top:3%;">
		<div class="col-md-12" id="addressTag"><a style="color:black;padding-right:0px;" href="<%= request.getContextPath() %>">Hermit&nbsp;&nbsp;&gt;&nbsp;&nbsp;</a><span id="cityName"></span><span id="boroughName"></span><span id="houseAddr"></span></div>
		<div class="col-md-12" style="height:550px">
			<div id="carousel">
			</div>
		</div>
		<div class="col-md-12" style="height:300px;width:100%;border:1px solid #dddddd;border-radius: 10px;margin-top:50px;float:right">
		</div>
		<div class="col-md-12" style="margin-top:10px;float:left;display:block;">
			<ul class="nav nav-tabs" role="tablist">
			  <li role="presentation" style="color:black" class="active"><a style="color:black"  href="#hInfo" aria-controls="hInfo" role="tab" data-toggle="tab">屋況介紹</a></li>
			  <li role="presentation" style="color:black" ><a style="color:black"  href="#houseContent" aria-controls="houseContent" role="tab" data-toggle="tab">房屋規格</a></li>
			  <li role="presentation" style="color:black" ><a style="color:black"  href="#nearMap" aria-controls="nearMap" role="tab" data-toggle="tab">附近地圖</a></li>
			  <li role="presentation" style="color:black" ><a style="color:black" href="#houseVideo" aria-controls="houseVideo" role="tab" data-toggle="tab">房屋影片</a></li>
			</ul>
			<div class="tab-content" style="margin-top:5px">
			  <div role="tabpanel" style="border:1px solid rgba(221,221,221,0.7);border-radius:10px;padding:40px;height:500px;width:100%" class="tab-pane active" id="hInfo"></div>
			  <div role="tabpanel" style="border:1px solid rgba(221,221,221,0.7);border-radius:10px;padding:40px;height:500px;width:100%"  class="tab-pane" id="houseContent">
					<div class="col-md-2"  style="height:32px;margin:9px auto;"><img style="height:32px;width:32px" id="TV" src='images/television.png'><span>&nbsp;電視</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="aircondition" src='images/air.png'><span>&nbsp;冷氣</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="refrigerator" src='images/refrigerator.png'><span>&nbsp;冰箱</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="waterHeater" src='images/waterheater.png'><span>&nbsp;熱水器</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="gas" src='images/gas.png'><span>&nbsp;瓦斯</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="theFourthStation" src='images/thefour.png'><span>&nbsp;第四台</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="net" src='images/wifi-connection-signal-symbol.png'><span>&nbsp;網路</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="washing" src='images/washing.png'><span>&nbsp;洗衣機</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="bed" src='images/bed.png'><span>&nbsp;床</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="wardrobe" src='images/wardrobe.png'><span>&nbsp;衣櫃</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="sofa" src='images/sofa.png'><span>&nbsp;沙發</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="parking" src='images/parking.png'><span>&nbsp;停車位</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="elevator" src='images/elevator.png'><span>&nbsp;電梯</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="balcony" src='images/balcony.png'><span>&nbsp;陽台</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="permitCook" src='images/cooking.png'><span>&nbsp;開伙</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="pet" src='images/pet.png'><span>&nbsp;養寵物</span></div>
					<div class="col-md-2"  style="height:32px;margin:9px auto"><img style="height:32px;width:32px" id="closeMRT" src='images/closeMRT.png'><span>&nbsp;近捷運</span></div>
					
			  </div>
			  <div role="tabpanel" style="border:1px solid rgba(221,221,221,0.7);border-radius:10px;padding:40px;height:500px;width:100%"  class="tab-pane" id="nearMap"></div>
			  <div role="tabpanel" style="border:1px solid rgba(221,221,221,0.7);border-radius:10px;padding:40px;height:500px;width:100%"  class="tab-pane" id="houseVideo"></div>
			</div>
		</div>
	</div>
	<footer class="w3-bottom w3-black container-fluid text-center" style=" position: static">
		<div>
			<ul class="nav nav-pills w3-centered " style="display: flex;font-size:13px;justify-content: center;">
			  <li role="presentation"><a href="<%=request.getContextPath()%>/index.jsp">關於我們</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">服務條款</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_service_page.jsp">隱私權聲明</a></li>
			</ul>
		</div>
	    <span class="text-center"><p style="font-size:10px">赫米特開發團隊  Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
		</div>
	</footer>
	<script>
		function loadCarousel(){
			var house = $.parseJSON('<%= request.getAttribute("House")%>');
			var hPics = $.parseJSON('<%= request.getAttribute("hPics")%>');
			var eqStatus = $.parseJSON('<%= request.getAttribute("eq")%>');
			var hContentImg = $("#houseContent img");
			$("#cityName").text(house.cityName+"  >  ");
			$("#boroughName").text(house.boroughName+"  >  ");
			$("#houseAddr").text(house.houseAddr);
			if(eqStatus != null)
				$.each(eqStatus,function(eqName,value){
					if(!value){
						$.each(hContentImg,function(index,eqImg){
							if(eqImg.id == eqName){
								eqImg.src="images/no.png";
							}
						})
					}
				})
			$('#carousel').carousel({
			  images:hPics,  
			  currentImageIndex: 0,
	          useDots: true,
	          useThumbnails: true,
	          useCaptions: true,
	          useArrows: false,
	          interval: 10000
			});
			$("#hInfo").html(house.houseInfo);
		}
		loadCarousel();	
		$('#tabs-container').scrollingTabs();

		
	</script>
</body>
</html>