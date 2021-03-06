<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="shortcut icon" href="<%= request.getContextPath() %>/favicon.ico">
<jsp:include page="/fragment/member_page.jsp" />
<link href='<%= request.getContextPath() %>/css/jquery-ui.structure.min.css' rel='stylesheet' />
<link href='<%= request.getContextPath() %>/css/jquery-ui.theme.min.css' rel='stylesheet' />
<link href="<%=request.getContextPath()%>/css/jquery.scrolling-tabs.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/jquery.otg-carousel.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/jquery.scrolling-tabs.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.otg-carousel.js"></script>
<style>
	
	.col-md-2 span{
		vertical-align:sub;
		font-family: Microsoft JhengHei;
		font-weight: bold;
	}
	#addressTag span{
			font-size:0.8em; 
		}
	#map {
		height: 400px;
		width: 100%;
	}
	
	.myScroll {
		height: 150px;
		overflow: scroll;
	}
	
	#accordion a {
		color: black;
	}
	.nearMap a{
		font-family: Microsoft JhengHei;
	}
	/* 	Google Map CSS End */
	/* 	配合AJAX的bootstrap特效snackbar CSS Start */
	#snackbar {
	    visibility: hidden;
	    min-width: 250px;
	    margin-left: -125px;
	    background-color: #333;
	    color: #fff;
	    text-align: center;
	    border-radius: 2px;
	    padding: 16px;
	    position: fixed;
	    z-index: 1;
	    left: 50%;
	    bottom: 100px;
	    font-size: 17px;
	}
	.houseInformation{
		font-family: 'cwTeXYen', sans-serif;
	}
	
	#snackbar.show {
	    visibility: visible;
	    -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
	    animation: fadein 0.5s, fadeout 0.5s 2.5s;
	}
	
	@-webkit-keyframes fadein {
	    from {bottom: 0; opacity: 0;} 
	    to {bottom: 100px; opacity: 1;}
	}
	
	@keyframes fadein {
	    from {bottom: 0; opacity: 0;}
	    to {bottom: 100px; opacity: 1;}
	}
	
	@-webkit-keyframes fadeout {
	    from {bottom: 100px; opacity: 1;} 
	    to {bottom: 0; opacity: 0;}
	}
	
	@keyframes fadeout {
	    from {bottom: 100px; opacity: 1;}
	    to {bottom: 0; opacity: 0;}
	}
	/* 	配合AJAX的bootstrap特效snackbar CSS End */
	h5{
		font-size: 0.83em
	}
/* 	reservation css */
	#reservationDiv div>div{
		width:90px;
		border:1px solid gray;
		background-color: #46A3FF;
		position:absolute;
		clear:left;
		display:none;
	}
 	#reservationDiv div{ 
 		float:left;
 		padding:3px;
 		
 	}
 	#reservationIn *{
  		margin:0;
  		padding:0;
	}
 	#bt {
 		box-sizing:border-box;
  		transition:0.4s ease;
  		position: relative;
  		display:block; width:140px; height:50px; line-height: 50px; text-decoration: none; color:#2dcb70;
  		font-weight: bolder;
  		padding-left: 0px; margin:0 auto;
  		font-family: 'Noto Sans TC', sans-serif;
  		font-weight:800;
  		font-size:30px;
  		background: url(./images/arrow.png) no-repeat 100px center;
  		background-size:30px 30px;
	}
	#bt:hover {
 		 background-position: 140px center;
	}
	#bt .line {
  		position: absolute; background: none; transition:0.4s;
	}
	#bt:hover .line {
 		 background: #019858;
	}
 
	#bt .line-top {
 		 width:0px; height:2px; top:-2px; left:-110%;
	}
	#bt:hover .line-top {
  		width:140px; left:-2px;
	}
 
	#bt .line-right {
  		width:2px; height:0px; right:-2px; top:-110%;
	}
	#bt:hover .line-right {
  		height:50px; top:-2px;
	}
 
	#bt .line-bottom {
	  width:0px; height:2px; bottom:-2px; right:-110%;
	}
	#bt:hover .line-bottom {
 		 width:140px; right:-2px;
	}
 
	#bt .line-left {
  		width:2px; height:0px; left:-2px; bottom:-110%;
	}
	#bt:hover .line-left {
  		height:50px; bottom:-2px;
	}
 	/* 	reservation css end*/
 	/*  question css */
 	#QandAIn *{
  		margin:0;
  		padding:0;
	}
 	#questionBT {
 		box-sizing:border-box;
  		transition:0.4s ease;
  		position: relative;
  		display:block; width:140px; height:50px; line-height: 50px; text-decoration: none; color:#f00;
  		font-weight: bolder;
  		padding-left: 0px; margin:0 auto;
  		font-family: 'Noto Sans TC', sans-serif;
  		font-weight:800;
  		font-size:30px;
  		background: url(./images/arrow.png) no-repeat 100px center;
  		background-size:30px 30px;
	}
	#questionBT:hover {
 		 background-position: 140px center;
	}
	#questionBT .line {
  		position: absolute; background: none; transition:0.4s;
	}
	#questionBT:hover .line {
 		 background: #f00;
	}
 
	#questionBT .line-top {
 		 width:0px; height:2px; top:-2px; left:-110%;
	}
	#questionBT:hover .line-top {
  		width:140px; left:-2px;
	}
 
	#questionBT .line-right {
  		width:2px; height:0px; right:-2px; top:-110%;
	}
	#questionBT:hover .line-right {
  		height:50px; top:-2px;
	}
 
	#questionBT .line-bottom {
	  width:0px; height:2px; bottom:-2px; right:-110%;
	}
	#questionBT:hover .line-bottom {
 		 width:140px; right:-2px;
	}
 
	#questionBT .line-left {
  		width:2px; height:0px; left:-2px; bottom:-110%;
	}
	#questionBT:hover .line-left {
  		height:50px; bottom:-2px;
	}
 	/*  question css end*/
 	
</style>
</head>
<body>
	<div class="container" style="margin-top:2%;margin-bottom:2%;padding-top:3%;">
		<div class="col-md-12" id="addressTag"><a style="color:black;padding-right:0px;" href="<%= request.getContextPath() %>">Hermit&nbsp;&nbsp;&gt;&nbsp;&nbsp;</a><span id="cityName"></span><span id="boroughName"></span><span id="houseAddr"></span></div>
		<div class="col-md-12" id="houseTitle" style="font-family:Microsoft JhengHei;text-align: center;"></div>
		<div class="col-md-12" style="height:550px">
			<div id="carousel">
			</div>
		</div>
		<!-- favorite HTML Start -->
		<div class="col-md-12" style="margin-top:1.5em;">
			<div class="col-md-4"><input type="hidden" id="memNO" name="memNO" value="${LoginOK.memNO}"></div>
			<div class="col-md-4"></div>
			<div class="col-md-4" id="myFavStar"><img height="50" width="50" src="<%=request.getContextPath()%>/images/like_n.png" /><span style="font-size: 1.5em; font-weight: 700; margin-left: 3%;font-family:Microsoft JhengHei;vertical-align: -webkit-baseline-middle;">收藏物件</span></div>
		</div>
		<!-- favorite HTML End -->
		<div class="col-md-12 houseInformation" style="height:250px;width:100%;border-radius: 10px;margin-top:10px;margin-bottom:40px;float:right;padding:15px">
			<div class="col-md-12" style="margin:10px 3px;" id="Rent"></div>
			<div class="col-md-4" style="margin:10px 3px;" id="Size"></div>
			<div class="col-md-4" style="margin:10px 3px;" id="Charge"></div>
			<div class="col-md-3" style="margin:10px 3px;" id="reservation"><div id="reservationIn"><a id="bt"><span class="line line-top"></span><span class="line line-right"></span><span class="line line-bottom"></span><span class="line line-left"></span>預約</a></div></div>
			<div class="col-md-4" style="margin:10px 3px;" id="floor"></div>
			<div class="col-md-4" style="margin:10px 3px;" id="hFormType"></div>
			<div class="col-md-3" style="margin:10px 3px;" id="QandA"><div id="QandAIn"><a id="questionBT"><span class="line line-top"></span><span class="line line-right"></span><span class="line line-bottom"></span><span class="line line-left"></span>詢問</a></div></div>
			<div class="col-md-4" style="margin:10px 3px;" id="water"></div>
			<div class="col-md-4" style="margin:10px 3px;" id="elePower"></div>
		</div>
		<div class="col-md-12" style="margin-top:10px;float:left;display:block;">
			<ul class="nav nav-tabs" role="tablist" style="font-family: Microsoft JhengHei;">
			  <li role="presentation" style="color:black" class="active"><a style="color:black"  href="#hInfo" aria-controls="hInfo" role="tab" data-toggle="tab">屋況介紹</a></li>
			  <li role="presentation" style="color:black" ><a style="color:black"  href="#houseContent" aria-controls="houseContent" role="tab" data-toggle="tab">房屋配備</a></li>
			  <li role="presentation" style="color:black" ><a style="color:black" href="#houseVideo" aria-controls="houseVideo" role="tab" data-toggle="tab">房屋影片</a></li>
			</ul>
			<div class="tab-content" style="margin-top:5px">
			  <div role="tabpanel" style="border-radius:10px;padding:40px;height:auto;width:100%" class="tab-pane active" id="hInfo"></div>
			  <div role="tabpanel" style="border-radius:10px;padding:40px;height:auto;width:100%"  class="tab-pane" id="houseContent">
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
			  <div role="tabpanel" style="border-radius:10px;padding:40px;height:auto;width:100%"  class="tab-pane" id="houseVideo">
			  		<div>
 						 <iframe  id="youtube" width="640" height="480" src="https://www.youtube.com/embed/"></iframe>
					</div>
			  </div>
			</div>
		</div>
	</div>

	<!--  Google Map HTML Start -->
	<div class="container nearMap" style="margin-bottom: 10em;">
		<div class="row">
			<div class="col-md-4">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse1" style="text-decoration: none;"
									id="buttonTraffic">周邊交通概況</a>
							</h4>
						</div>
						<div id="collapse1" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="panel-group" id="accordion1">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion1"
													href="#collapse1-1" style="text-decoration: none;">公車站</a>
											</h4>
										</div>
										<div id="collapse1-1" class="panel-collapse collapse">
											<div class="panel-body myScroll">附近無公車站內容</div>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion1"
													href="#collapse1-2" style="text-decoration: none;">捷運站</a>
											</h4>
										</div>
										<div id="collapse1-2" class="panel-collapse collapse">
											<div class="panel-body myScroll">附近無捷運站內容</div>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion1"
													href="#collapse1-3" style="text-decoration: none;">火車站</a>
											</h4>
										</div>
										<div id="collapse1-3" class="panel-collapse collapse">
											<div class="panel-body myScroll">附近無火車站內容</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse2" style="text-decoration: none;"
									id="buttonSchool">周邊校區概況</a>
							</h4>
						</div>
						<div id="collapse2" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="panel-group" id="accordion2">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion2"
													href="#collapse2-1" style="text-decoration: none;">學校</a>
											</h4>
										</div>
										<div id="collapse2-1" class="panel-collapse collapse">
											<div class="panel-body myScroll">附近無學校內容</div>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion2"
													href="#collapse2-2" style="text-decoration: none;">大學</a>
											</h4>
										</div>
										<div id="collapse2-2" class="panel-collapse collapse">
											<div class="panel-body myScroll">附近無大學內容</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse3" style="text-decoration: none;"
									id="buttonShopping">周邊餐廳商場</a>
							</h4>
						</div>
						<div id="collapse3" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="panel-group" id="accordion3">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion3"
													href="#collapse3-1" style="text-decoration: none;">餐廳</a>
											</h4>
										</div>
										<div id="collapse3-1" class="panel-collapse collapse">
											<div class="panel-body myScroll">附近無餐廳內容</div>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion3"
													href="#collapse3-2" style="text-decoration: none;">百貨公司</a>
											</h4>
										</div>
										<div id="collapse3-2" class="panel-collapse collapse">
											<div class="panel-body myScroll">附近無百貨公司內容</div>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion3"
													href="#collapse3-3" style="text-decoration: none;">購物中心</a>
											</h4>
										</div>
										<div id="collapse3-3" class="panel-collapse collapse">
											<div class="panel-body myScroll">附近無購物中心內容</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse4" style="text-decoration: none;"
									id="buttonCustomer">周邊生活設施</a>
							</h4>
						</div>
						<div id="collapse4" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="panel-group" id="accordion4">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion4"
													href="#collapse4-1" style="text-decoration: none;">銀行</a>
											</h4>
										</div>
										<div id="collapse4-1" class="panel-collapse collapse">
											<div class="panel-body myScroll">附近無銀行內容</div>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion4"
													href="#collapse4-2" style="text-decoration: none;">醫院</a>
											</h4>
										</div>
										<div id="collapse4-2" class="panel-collapse collapse">
											<div class="panel-body myScroll">附近無醫院內容</div>
										</div>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion4"
													href="#collapse4-3" style="text-decoration: none;">公園</a>
											</h4>
										</div>
										<div id="collapse4-3" class="panel-collapse collapse">
											<div class="panel-body myScroll">附近無公園內容</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-8">
				<!-- Google Map JS Code Start -->
				<script>
								var map;
								var infowindow;
								var markers = [];
								var pyrmont;
								var jsonlat, jsonlng;
								var activeFlag = '';
								var timeout = 1000;
<%-- 								console.log('<%= request.getAttribute("House")%>') --%>
								var hInfo = $.parseJSON('<%= request.getAttribute("House")%>');
								
								// 一開始啟動Google API的CB
								function initAdd() {							
									//抓經緯度
									$.getJSON('https://maps.google.com/maps/api/geocode/json', {
										'address' : hInfo.cityName+hInfo.boroughName+hInfo.houseAddr
									}, function(datas) {
										jsonlat = datas.results[0].geometry.location.lat;
										jsonlng = datas.results[0].geometry.location.lng;
										// 呼叫map初始化
										initMap();
									})
								}

								// 初始化地圖after經緯度JSON
								function initMap() {
									// 定位值
									pyrmont = {
										lat : jsonlat,
										lng : jsonlng
									};
									// 加入定位與縮放值
									map = new google.maps.Map(document.getElementById('map'), {
										center : pyrmont,
										zoom : 16
									});
									// 載入地圖資訊
									infowindow = new google.maps.InfoWindow();
									
									// 清空目前標記
									clearMarker();
									// 加入預設Marker定位
									var marker = new google.maps.Marker({
										map : map,
										position : pyrmont,
									});
									// markers.push(marker);
									// Marker點擊事件
									google.maps.event.addListener(marker, 'click', function() {
										// infowindow.setContent(place.name + thisResult);
										infowindow.setContent('<strong>'+hInfo.houseTitle+'：</strong><hr/><img height="45" width="45" src="'+hInfo.previewPic+'"><i>'+hInfo.cityName+hInfo.boroughName+hInfo.houseAddr+'</i>');
										infowindow.open(map, this);
									});	
								}

								// (bus_station公車，subway_station捷運，train_station火車站)
								$('#buttonTraffic').click(function() {
									
									// 重複點擊問題
									if (activeFlag == 'buttonTraffic')
									{
										return;
									}
									
									// 清空目前標記
									clearMarker();
									activeFlag = 'buttonTraffic';
									// 載入地點服務
									var placeservice = new google.maps.places.PlacesService(map);
									// 使用地點服務的"附近地點搜尋"功能
									placeservice.nearbySearch({
										// "附近地點搜尋"定位
										location : pyrmont,
										// "附近地點搜尋"搜尋半徑
										radius : 350,
										// "附近地點搜尋"搜尋物件
										type : [ 'bus_station' ]
									}, bus_station_placeCallback);

									placeservice.nearbySearch({
										// "附近地點搜尋"定位
										location : pyrmont,
										// "附近地點搜尋"搜尋半徑
										radius : 350,
										// "附近地點搜尋"搜尋物件
										type : [ 'subway_station' ]
									}, subway_station_placeCallback);

									placeservice.nearbySearch({
										// "附近地點搜尋"定位
										location : pyrmont,
										// "附近地點搜尋"搜尋半徑
										radius : 350,
										// "附近地點搜尋"搜尋物件
										type : [ 'train_station' ]
									}, train_station_placeCallback);
								});

								// (school學校，university大學)
								$('#buttonSchool').click(function() {
									
									// 重複點擊問題
									if (activeFlag == 'buttonSchool')
									{
										return;
									}
									
									// 清空目前標記
									clearMarker();
									activeFlag = 'buttonSchool';
									// 載入地點服務
									var placeservice = new google.maps.places.PlacesService(map);
									// 使用地點服務的"附近地點搜尋"功能
									placeservice.nearbySearch({
										// "附近地點搜尋"定位
										location : pyrmont,
										// "附近地點搜尋"搜尋半徑
										radius : 350,
										// "附近地點搜尋"搜尋物件
										type : [ 'school' ]
									}, school_placeCallback);

									placeservice.nearbySearch({
										// "附近地點搜尋"定位
										location : pyrmont,
										// "附近地點搜尋"搜尋半徑
										radius : 350,
										// "附近地點搜尋"搜尋物件
										type : [ 'university' ]
									}, university_placeCallback);
								});

								// (restaurant餐廳，department_store百貨，shopping_mall購物中心，gym健身，movie_theater電影院)
								$('#buttonShopping').click(function() {
									
									// 重複點擊問題
									if (activeFlag == 'buttonShopping')
									{
										return;
									}
									
									// 清空目前標記
									clearMarker();
									activeFlag = 'buttonShopping';
									// 載入地點服務
									var placeservice = new google.maps.places.PlacesService(map);
									// 使用地點服務的"附近地點搜尋"功能
									placeservice.nearbySearch({
										// "附近地點搜尋"定位
										location : pyrmont,
										// "附近地點搜尋"搜尋半徑
										radius : 350,
										// "附近地點搜尋"搜尋物件
										type : [ 'restaurant' ]
									}, restaurant_placeCallback);

									placeservice.nearbySearch({
										// "附近地點搜尋"定位
										location : pyrmont,
										// "附近地點搜尋"搜尋半徑
										radius : 350,
										// "附近地點搜尋"搜尋物件
										type : [ 'department_store' ]
									}, department_store_placeCallback);
									
									placeservice.nearbySearch({
										// "附近地點搜尋"定位
										location : pyrmont,
										// "附近地點搜尋"搜尋半徑
										radius : 350,
										// "附近地點搜尋"搜尋物件
										type : [ 'shopping_mall' ]
									}, shopping_mall_placeCallback);

									// 資料量太大，免費版放棄
									/*			
									placeservice.nearbySearch({
									// "附近地點搜尋"定位
									location : pyrmont,
									// "附近地點搜尋"搜尋半徑
									radius : 350,
									// "附近地點搜尋"搜尋物件
									type : [ 'gym' ]
									}, gym_placeCallback);

									placeservice.nearbySearch({
									// "附近地點搜尋"定位
									location : pyrmont,
									// "附近地點搜尋"搜尋半徑
									radius : 350,
									// "附近地點搜尋"搜尋物件
									type : [ 'movie_theater' ]
									}, movie_theater_placeCallback);
									*/
								});

								// (bank銀行，hospital醫院，park公園，parking停車場)
								$('#buttonCustomer').click(function() {
									
									// 重複點擊問題
									if (activeFlag == 'buttonCustomer')
									{
										return;
									}
									
									// 清空目前標記
									clearMarker();
									activeFlag = 'buttonCustomer';
									// 載入地點服務
									var placeservice = new google.maps.places.PlacesService(map);
									// 使用地點服務的"附近地點搜尋"功能
									placeservice.nearbySearch({
										// "附近地點搜尋"定位
										location : pyrmont,
										// "附近地點搜尋"搜尋半徑
										radius : 350,
										// "附近地點搜尋"搜尋物件
										type : [ 'bank' ]
									}, bank_placeCallback);

									placeservice.nearbySearch({
										// "附近地點搜尋"定位
										location : pyrmont,
										// "附近地點搜尋"搜尋半徑
										radius : 350,
										// "附近地點搜尋"搜尋物件
										type : [ 'hospital' ]
									}, hospital_placeCallback);

									placeservice.nearbySearch({
										// "附近地點搜尋"定位
										location : pyrmont,
										// "附近地點搜尋"搜尋半徑
										radius : 350,
										// "附近地點搜尋"搜尋物件
										type : [ 'park' ]
									}, park_placeCallback);
									
									// 資料量太大，免費版放棄
									/*
									placeservice.nearbySearch({
										// "附近地點搜尋"定位
										location : pyrmont,
										// "附近地點搜尋"搜尋半徑
										radius : 350,
										// "附近地點搜尋"搜尋物件
										type : [ 'parking' ]
									}, parking_placeCallback);
									*/
								});

								/**** "附近地點搜尋"功能的CB結果 ****/
								function bus_station_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										var fragment = $(document.createDocumentFragment());
										for (var i = 0; i < placeResults.length; i++) {
											var cell = $('<h5></h5>').text(placeResults[i].name);
											var row = $('<div></div>').append(cell);
											fragment.append(row);
											bus_station_createMarker(placeResults[i]);
										}
										$('#collapse1-1 div').html(fragment);
									}
								}

								function subway_station_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										var fragment = $(document.createDocumentFragment());
										for (var i = 0; i < placeResults.length; i++) {
											var cell = $('<h5></h5>').text(placeResults[i].name);
											var row = $('<div></div>').append(cell);
											fragment.append(row);
											subway_station_createMarker(placeResults[i]);
										}
										$('#collapse1-2 div').html(fragment);
									}
								}

								function train_station_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										var fragment = $(document.createDocumentFragment());
										for (var i = 0; i < placeResults.length; i++) {
											var cell = $('<h5></h5>').text(placeResults[i].name);
											var row = $('<div></div>').append(cell);
											fragment.append(row);
											train_station_createMarker(placeResults[i]);
										}
										$('#collapse1-3 div').html(fragment);
									}
								}

								function school_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										var fragment = $(document.createDocumentFragment());
										for (var i = 0; i < placeResults.length; i++) {
											var cell = $('<h5></h5>').text(placeResults[i].name);
											var row = $('<div></div>').append(cell);
											fragment.append(row);
											school_createMarker(placeResults[i]);
										}
										$('#collapse2-1 div').html(fragment);
									}
								}

								function university_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										var fragment = $(document.createDocumentFragment());
										for (var i = 0; i < placeResults.length; i++) {
											var cell = $('<h5></h5>').text(placeResults[i].name);
											var row = $('<div></div>').append(cell);
											fragment.append(row);
											university_createMarker(placeResults[i]);
										}
										$('#collapse2-2 div').html(fragment);
									}
								}

								function restaurant_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										var fragment = $(document.createDocumentFragment());
										for (var i = 0; i < placeResults.length; i++) {
											var cell = $('<h5></h5>').text(placeResults[i].name);
											var row = $('<div></div>').append(cell);
											fragment.append(row);
											restaurant_createMarker(placeResults[i]);
										}
										$('#collapse3-1 div').html(fragment);
									}
								}

								function department_store_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										var fragment = $(document.createDocumentFragment());
										for (var i = 0; i < placeResults.length; i++) {
											var cell = $('<h5></h5>').text(placeResults[i].name);
											var row = $('<div></div>').append(cell);
											fragment.append(row);
											department_store_createMarker(placeResults[i]);
										}
										$('#collapse3-2 div').html(fragment);
									}
								}

								function shopping_mall_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										var fragment = $(document.createDocumentFragment());
										for (var i = 0; i < placeResults.length; i++) {
											var cell = $('<h5></h5>').text(placeResults[i].name);
											var row = $('<div></div>').append(cell);
											fragment.append(row);
											shopping_mall_createMarker(placeResults[i]);
										}
										$('#collapse3-3 div').html(fragment);
									}
								}
								/*
								function gym_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										for (var i = 0; i < placeResults.length; i++) {
											gym_createMarker(placeResults[i]);
										}
									}
								}

								function movie_theater_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										for (var i = 0; i < placeResults.length; i++) {
											movie_theater_createMarker(placeResults[i]);
										}
									}
								}
								*/
								function bank_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										var fragment = $(document.createDocumentFragment());
										for (var i = 0; i < placeResults.length; i++) {
											var cell = $('<h5></h5>').text(placeResults[i].name);
											var row = $('<div></div>').append(cell);
											fragment.append(row);
											bank_createMarker(placeResults[i]);
										}
										$('#collapse4-1 div').html(fragment);
									}
								}

								function hospital_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										var fragment = $(document.createDocumentFragment());
										for (var i = 0; i < placeResults.length; i++) {
											var cell = $('<h5></h5>').text(placeResults[i].name);
											var row = $('<div></div>').append(cell);
											fragment.append(row);
											hospital_createMarker(placeResults[i]);
										}
										$('#collapse4-2 div').html(fragment);
									}
								}

								function park_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										var fragment = $(document.createDocumentFragment());
										for (var i = 0; i < placeResults.length; i++) {
											var cell = $('<h5></h5>').text(placeResults[i].name);
											var row = $('<div></div>').append(cell);
											fragment.append(row);
											park_createMarker(placeResults[i]);
										}
										$('#collapse4-3 div').html(fragment);
									}
								}
								/*
								function parking_placeCallback(placeResults, status) {
									if (status === google.maps.places.PlacesServiceStatus.OK) {
										for (var i = 0; i < placeResults.length; i++) {
											parking_createMarker(placeResults[i]);
										}
									}
								}
								*/
								/**** 建立 Marker by placeCallback ****/
								function bus_station_createMarker(place) {
									
									if (activeFlag != 'buttonTraffic')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url : '<%=request.getContextPath()%>/images/google-icon/Traffic/bus_station.png',
										    // pixels size.
										    // size: new google.maps.Size(20, 32),
										    // pixels scaled size.
										    scaledSize: new google.maps.Size(35, 35),
										    // image左上起始
										    origin: new google.maps.Point(0, 0),
										    // image在地圖錨點位置
										    anchor: new google.maps.Point(0, 32)
									};
									// 加入Marker定位
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon: image
									});
									markers.push(marker);

									// 載入距離服務
									var directionService = new google.maps.DirectionsService();
									// 距離結果 (怕Google算太慢...預設先給最大，同nearbySearch的radius設定350=0.35公里)
									var thisResult = '0.35公里';
									// 使用距離服務的"計算"功能
									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										// 交通模式
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										// 單位交給預設
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);


									// "計算"功能的CB結果
									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											// 避免後續有OVER_QUERY_LIMIT問題，Google的計算有限制
											if (activeFlag == 'buttonTraffic') {
												setTimeout(function() {
													bus_station_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									// Marker點擊事件
									google.maps.event.addListener(marker, 'click', function() {
										// infowindow.setContent(place.name + thisResult);
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}

								function subway_station_createMarker(place) {
									
									if (activeFlag != 'buttonTraffic')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url : '<%=request.getContextPath()%>/images/google-icon/Traffic/subway_station.png',
										// pixels size.
										// size: new google.maps.Size(20, 32),
										scaledSize : new google.maps.Size(35, 35),
										origin : new google.maps.Point(0, 0),
										anchor : new google.maps.Point(0, 32)
									};
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon : image
									});
									markers.push(marker);

									var directionService = new google.maps.DirectionsService();
									var thisResult = '0.35公里';

									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);

									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											if (activeFlag == 'buttonTraffic') {
												setTimeout(function() {
													subway_station_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									google.maps.event.addListener(marker, 'click', function() {
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}

								function train_station_createMarker(place) {
									
									if (activeFlag != 'buttonTraffic')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url: '<%=request.getContextPath()%>/images/google-icon/Traffic/train_station.png',
										// pixels size.
										// size: new google.maps.Size(20, 32),
										scaledSize : new google.maps.Size(35, 35),
										origin : new google.maps.Point(0, 0),
										anchor : new google.maps.Point(0, 32)
									};
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon : image
									});
									markers.push(marker);

									var directionService = new google.maps.DirectionsService();
									var thisResult = '0.35公里';

									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);

									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											if (activeFlag == 'buttonTraffic') {
												setTimeout(function() {
													train_station_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									google.maps.event.addListener(marker, 'click', function() {
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}

								function school_createMarker(place) {
									
									if (activeFlag != 'buttonSchool')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url: '<%=request.getContextPath()%>/images/google-icon/School/school.png',
										// pixels size.
										// size: new google.maps.Size(20, 32),
										scaledSize : new google.maps.Size(35, 35),
										origin : new google.maps.Point(0, 0),
										anchor : new google.maps.Point(0, 32)
									};
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon : image
									});
									markers.push(marker);

									var directionService = new google.maps.DirectionsService();
									var thisResult = '0.35公里';

									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);

									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											if (activeFlag == 'buttonSchool') {
												setTimeout(function() {
													school_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									google.maps.event.addListener(marker, 'click', function() {
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}
								
								function university_createMarker(place) {
									
									if (activeFlag != 'buttonSchool')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url: '<%=request.getContextPath()%>/images/google-icon/School/university.png',
										// pixels size.
										// size: new google.maps.Size(20, 32),
										scaledSize : new google.maps.Size(35, 35),
										origin : new google.maps.Point(0, 0),
										anchor : new google.maps.Point(0, 32)
									};
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon : image
									});
									markers.push(marker);

									var directionService = new google.maps.DirectionsService();
									var thisResult = '0.35公里';

									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);

									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											if (activeFlag == 'buttonSchool') {
												setTimeout(function() {
													university_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									google.maps.event.addListener(marker, 'click', function() {
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}

								function restaurant_createMarker(place) {
									
									if (activeFlag != 'buttonShopping')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url: '<%=request.getContextPath()%>/images/google-icon/Shopping/restaurant.png',
										// pixels size.
										// size: new google.maps.Size(20, 32),
										scaledSize : new google.maps.Size(35, 35),
										origin : new google.maps.Point(0, 0),
										anchor : new google.maps.Point(0, 32)
									};
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon : image
									});
									markers.push(marker);

									var directionService = new google.maps.DirectionsService();
									var thisResult = '0.35公里';

									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);

									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											if (activeFlag == 'buttonShopping') {
												setTimeout(function() {
													restaurant_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									google.maps.event.addListener(marker, 'click', function() {
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}

								function department_store_createMarker(place) {
									
									if (activeFlag != 'buttonShopping')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url: '<%=request.getContextPath()%>/images/google-icon/Shopping/department_store.png',
										// pixels size.
										// size: new google.maps.Size(20, 32),
										scaledSize : new google.maps.Size(35, 35),
										origin : new google.maps.Point(0, 0),
										anchor : new google.maps.Point(0, 32)
									};
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon : image
									});
									markers.push(marker);

									var directionService = new google.maps.DirectionsService();
									var thisResult = '0.35公里';

									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);

									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											if (activeFlag == 'buttonShopping') {
												setTimeout(function() {
													department_store_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									google.maps.event.addListener(marker, 'click', function() {
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}

								function shopping_mall_createMarker(place) {
									
									if (activeFlag != 'buttonShopping')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url: '<%=request.getContextPath()%>/images/google-icon/Shopping/shopping_mall.png',
										// pixels size.
										// size: new google.maps.Size(20, 32),
										scaledSize : new google.maps.Size(35, 35),
										origin : new google.maps.Point(0, 0),
										anchor : new google.maps.Point(0, 32)
									};
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon : image
									});
									markers.push(marker);

									var directionService = new google.maps.DirectionsService();
									var thisResult = '0.35公里';

									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);

									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											if (activeFlag == 'buttonShopping') {
												setTimeout(function() {
													shopping_mall_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									google.maps.event.addListener(marker, 'click', function() {
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}

								function gym_createMarker(place) {
									
									if (activeFlag != 'buttonShopping')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url: '<%=request.getContextPath()%>/images/google-icon/Shopping/gym.png',
										// pixels size.
										// size: new google.maps.Size(20, 32),
										scaledSize : new google.maps.Size(35, 35),
										origin : new google.maps.Point(0, 0),
										anchor : new google.maps.Point(0, 32)
									};
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon : image
									});
									markers.push(marker);

									var directionService = new google.maps.DirectionsService();
									var thisResult = '0.35公里';

									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);

									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											if (activeFlag == 'buttonShopping') {
												setTimeout(function() {
													gym_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									google.maps.event.addListener(marker, 'click', function() {
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}
								
								function movie_theater_createMarker(place) {
									
									if (activeFlag != 'buttonShopping')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url: '<%=request.getContextPath()%>/images/google-icon/Shopping/movie_theater.png',
										// pixels size.
										// size: new google.maps.Size(20, 32),
										scaledSize : new google.maps.Size(35, 35),
										origin : new google.maps.Point(0, 0),
										anchor : new google.maps.Point(0, 32)
									};
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon : image
									});
									markers.push(marker);

									var directionService = new google.maps.DirectionsService();
									var thisResult = '0.35公里';

									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);

									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											if (activeFlag == 'buttonShopping') {
												setTimeout(function() {
													movie_theater_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									google.maps.event.addListener(marker, 'click', function() {
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}
								
								function bank_createMarker(place) {
									
									if (activeFlag != 'buttonCustomer')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url: '<%=request.getContextPath()%>/images/google-icon/Customer/bank.png',
										// pixels size.
										// size: new google.maps.Size(20, 32),
										scaledSize : new google.maps.Size(35, 35),
										origin : new google.maps.Point(0, 0),
										anchor : new google.maps.Point(0, 32)
									};
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon : image
									});
									markers.push(marker);

									var directionService = new google.maps.DirectionsService();
									var thisResult = '0.35公里';

									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);

									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											if (activeFlag == 'buttonCustomer') {
												setTimeout(function() {
													bank_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									google.maps.event.addListener(marker, 'click', function() {
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}

								function hospital_createMarker(place) {
									
									if (activeFlag != 'buttonCustomer')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url: '<%=request.getContextPath()%>/images/google-icon/Customer/hospital.png',
										// pixels size.
										// size: new google.maps.Size(20, 32),
										scaledSize : new google.maps.Size(35, 35),
										origin : new google.maps.Point(0, 0),
										anchor : new google.maps.Point(0, 32)
									};
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon : image
									});
									markers.push(marker);

									var directionService = new google.maps.DirectionsService();
									var thisResult = '0.35公里';

									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);

									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											if (activeFlag == 'buttonCustomer') {
												setTimeout(function() {
													hospital_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									google.maps.event.addListener(marker, 'click', function() {
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}
								
								function park_createMarker(place) {
									
									if (activeFlag != 'buttonCustomer')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url: '<%=request.getContextPath()%>/images/google-icon/Customer/park.png',
										// pixels size.
										// size: new google.maps.Size(20, 32),
										scaledSize : new google.maps.Size(35, 35),
										origin : new google.maps.Point(0, 0),
										anchor : new google.maps.Point(0, 32)
									};
									var marker = new google.maps.Marker({
										map : map,
										position : placeLoc,
										icon : image
									});
									markers.push(marker);

									var directionService = new google.maps.DirectionsService();
									var thisResult = '0.35公里';

									directionService.route({
										origin : pyrmont,
										destination : placeLoc,
										travelMode : google.maps.DirectionsTravelMode.DRIVING,
										unitSystem : google.maps.UnitSystem.METRIC
									}, directionCallback);

									function directionCallback(directionResults, status) {
										if (status == google.maps.DirectionsStatus.OK) {
											thisResult = directionResults.routes[0].legs[0].distance.text;
										} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
											console.log('OVER_QUERY_LIMIT');
											if (activeFlag == 'buttonCustomer') {
												setTimeout(function() {
													park_createMarker(place);
												}, (timeout * 5));
											} else {
												return;
											}
										} else {
											thisResult = '無相關資訊';
										}
									}

									google.maps.event.addListener(marker, 'click', function() {
										infowindow.setContent('<strong>' + place.name
												+ '：</strong><hr/><i>' + thisResult + '</i>');
										infowindow.open(map, this);
									});
								}
								
								function parking_createMarker(place) {
									
									if (activeFlag != 'buttonCustomer')
									{
										return;
									}
									
									// Marker定位值
									// 	var placeLoc = place.geometry.location; 簡易法
									var placeLoc = {
										lat : place.geometry.location.lat(),
										lng : place.geometry.location.lng()
									};

									var image = {
										url: '<%=request.getContextPath()%>/images/google-icon/Customer/parking.png',
							// pixels size.
							// size: new google.maps.Size(20, 32),
							scaledSize : new google.maps.Size(35, 35),
							origin : new google.maps.Point(0, 0),
							anchor : new google.maps.Point(0, 32)
						};
						var marker = new google.maps.Marker({
							map : map,
							position : placeLoc,
							icon : image
						});
						markers.push(marker);

						var directionService = new google.maps.DirectionsService();
						var thisResult = '0.35公里';

						directionService
								.route(
										{
											origin : pyrmont,
											destination : placeLoc,
											travelMode : google.maps.DirectionsTravelMode.DRIVING,
											unitSystem : google.maps.UnitSystem.METRIC
										}, directionCallback);

						function directionCallback(directionResults, status) {
							if (status == google.maps.DirectionsStatus.OK) {
								thisResult = directionResults.routes[0].legs[0].distance.text;
							} else if (status == google.maps.DirectionsStatus.OVER_QUERY_LIMIT) {
								console.log('OVER_QUERY_LIMIT');
								if (activeFlag == 'buttonCustomer') {
									setTimeout(function() {
										parking_createMarker(place);
									}, (timeout * 5));
								} else {
									return;
								}
							} else {
								thisResult = '無相關資訊';
							}
						}

						google.maps.event.addListener(marker, 'click',
								function() {
									infowindow.setContent('<strong>'
											+ place.name + '：</strong><hr/><i>'
											+ thisResult + '</i>');
									infowindow.open(map, this);
								});
					}

					/**** 清空標記 ****/
					function clearMarker() {
						console.log('CLEAR');
						for (var i = 0; i < markers.length; i++) {
							markers[i].setMap(null);
						}
						markers = [];
						activeFlag = '';
					}
				</script>
				<!-- Google Map JS Code End -->
				<!-- 建立div -->
				<div id="map"></div>
				<script
					src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBKtb7wW_ZjCFVxl19nppUyj4b8z1GKMIs&libraries=places&callback=initAdd"
					async defer></script>
			</div>
		</div>
	</div>
	<!--  Google Map HTML End -->
	<!-- 配合AJAX的bootstrap特效snackbar HTML Start-->
	<div id="snackbar">已經成功操作</div>
	<!-- 配合AJAX的bootstrap特效snackbar HTML End-->
	<footer class="w3-black container-fluid text-center">
		<div>
			<ul class="nav nav-pills w3-centered " style="display: flex;font-size:13px;justify-content: center;">
			  <li role="presentation"><a href="<%=request.getContextPath()%>/index.jsp">關於我們</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">服務條款</a></li>
			  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_service_page.jsp">隱私權聲明</a></li>
			</ul>
		</div>
	    <span class="text-center"><p style="font-size:10px">赫米特開發團隊  Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
	</footer>
		<!-- 預約彈出div -->
		<div id="reservationDiv" title="預約時間" style="background-color:lemonchiffon;">
		<form id="reservationForm" action="<%= request.getContextPath() %>/reservationServlet?mission=reservation" method="POST">
			<!-- 取得會員帳號 -->
			<input type="hidden" name="memberNo" value="${LoginOK.memNO}"/>
			<!-- 取得房屋帳號-->
			<input type="hidden" name="houseNo" value="${param.NO}"/>
			<h3>請選擇期望預約時間</h3>
			<div id="MonDiv">
				<input type="checkbox" name="week" value="一" id="Mon"class="custom-control-input"/><label for="Mon">星期一</label> 
				<div id="MonTime">
					<input type="checkbox" name="Time" value="一上" id="MonMon"class="custom-control-input"/><label for="MonMon">上午</label><br/>
					<input type="checkbox" name="Time" value="一下" id="MonAf"class="custom-control-input"/><label for="MonAf">下午</label>
				</div>
			</div>
			<div id="TuesDiv">
				<input type="checkbox" name="week" value="二"id="Tues"class="custom-control-input"/><label for="Tues">星期二</label> 
				<div id="TuesTime">
					<input type="checkbox" name="Time" value="二上" id="TuesMon"class="custom-control-input"/><label for="TuesMon">上午</label><br/>
					<input type="checkbox" name="Time" value="二下" id="TuesAf"class="custom-control-input"/><label for="TuesAf">下午</label>
				</div>
			</div>
			<div id="WednDiv">
				<input type="checkbox" name="week" value="三"id="Wedn"class="custom-control-input"/><label for="Wedn">星期三 </label>
				<div id="WednTime">
					<input type="checkbox" name="Time" value="三上" id="WednMon"class="custom-control-input"/><label for="WednMon">上午</label><br/>
					<input type="checkbox" name="Time" value="三下" id="WednAf"class="custom-control-input"/><label for="WednAf">下午</label>
				</div>
			</div>
			<div id="ThurDiv">
				<input type="checkbox" name="week" value="四"id="Thur"class="custom-control-input"/><label for="Thur">星期四 </label>
				<div id="ThurTime">
					<input type="checkbox" name="Time" value="四上" id="ThurMon"class="custom-control-input"/><label for="ThurMon">上午</label><br/>
					<input type="checkbox" name="Time" value="四下" id="ThurAf"class="custom-control-input"/><label for="ThurAf">下午</label>
				</div>
			</div>
			<div id="FriDiv">
				<input type="checkbox" name="week" value="五" id="Fri"class="custom-control-input"/><label for="Fri">星期五 </label>
				<div id="FriTime">
					<input type="checkbox" name="Time" value="五上" id="FriMon"class="custom-control-input"/><label for="FriMon">上午</label><br/>
					<input type="checkbox" name="Time" value="五下" id="FriAf"class="custom-control-input"/><label for="FriAf">下午</label>
				</div>
			</div>
			<div id="SatDiv">
				<input type="checkbox" name="week" value="六" id="Sat"class="custom-control-input"/><label for="Sat">星期六 </label>
				<div id="SatTime">
					<input type="checkbox" name="Time" value="六上" id="SatMon"class="custom-control-input"/><label for="SatMon">上午</label><br/>
					<input type="checkbox" name="Time" value="六下" id="SatAf"class="custom-control-input"/><label for="SatAf">下午</label>
				</div>
			</div>
			<div id="SunDiv">
				<input type="checkbox" name="week" value="日" id="Sun"class="custom-control-input"/><label for="Sun">星期日 </label>
				<div id="SunTime">
					<input type="checkbox" name="Time" value="日上" id="SunMon"class="custom-control-input"/><label for="SunMon">上午</label><br/>
					<input type="checkbox" name="Time" value="日下" id="SunAf"class="custom-control-input"/><label for="SunAf">下午</label>
				</div>
			</div>
			<div style="clear:both;margin-top:50px">
			<button type="button"id="btSubmit" class="btn btn-info"	>確認</button>
			<button type="reset"id="btCancel" class="btn btn-info">取消</button>
			</div>
		</form>
	</div>
	<!-- 預約彈出表格end -->
	<!-- 詢問彈出表格 -->
	<div id="dialog" title="提問方塊">
		<form class="form-group">
		<label for="question">請輸入提問內容：</label>
		<textarea name="question"class="form-control"id="question" style="resize:none;height:150px"></textarea>
		</form>
	</div>
	<!-- 詢問彈出表格end -->
	<script src="<%= request.getContextPath() %>/js/jquery-form.js"></script>
	<script>
	// 大家一起用
	var house = $.parseJSON('<%= request.getAttribute("House")%>');
		function loadCarousel(){
			var hPics = $.parseJSON('<%= request.getAttribute("hPics")%>');
			var eqStatus = $.parseJSON('<%= request.getAttribute("eq")%>');
			var hContentImg = $("#houseContent img");
			document.title = house.cityName+house.boroughName+house.houseAddr+"";
			if(house != null){
				$("#cityName").text(house.cityName+"  >  ");
				$("#boroughName").text(house.boroughName+"  >  ");
				$("#houseAddr").text(house.houseAddr);
				$("#houseTitle").html("<h2>"+house.houseTitle+"</h2>");
				$(".houseInformation>#Rent").html("<span style='font-size:20px'>租金:&nbsp;&nbsp;</span><span style='font-size:2.3em;color:red;'>"+house.houseRent+"</span><span  style='font-size:20px'>&nbsp;&nbsp;元/月</span>")
				$(".houseInformation>#Size").html("<span style='font-size:20px'>房屋大小:&nbsp;&nbsp;</span><span style='font-size:1.5em;;'>"+house.houseSize+"</span><span  style='font-size:20px'>&nbsp;&nbsp;坪</span>")
				$(".houseInformation>#Charge").html("<span style='font-size:20px'>管理費:&nbsp;&nbsp;</span><span style='font-size:1.5em;;'>"+house.houseCharge+"</span><span  style='font-size:20px'>&nbsp;&nbsp;元/月</span>")
				$(".houseInformation>#floor").html("<span style='font-size:20px'>房屋樓層:&nbsp;&nbsp;</span><span style='font-size:1.5em;;'>"+house.nowFloor+"/"+house.highestFloor+"</span><span  style='font-size:20px'>&nbsp;&nbsp;樓</span>")
				$(".houseInformation>#water").html("<span style='font-size:20px'>水費:&nbsp;&nbsp;</span><span style='font-size:1.5em;;'>"+house.waterRate+"</span><span  style='font-size:20px'>&nbsp;&nbsp;</span>")
				$(".houseInformation>#elePower").html("<span style='font-size:20px'>電費:&nbsp;&nbsp;</span><span style='font-size:1.5em;;'>"+house.powerRate+"</span><span  style='font-size:20px'>&nbsp;&nbsp;</span>")
				$(".houseInformation>#hFormType").html("<span style='font-size:20px'>房屋型態:&nbsp;&nbsp;</span><span style='font-size:1.5em;;'>"+house.hForm+"/"+house.hType+"</span><span  style='font-size:20px'>&nbsp;&nbsp;</span>")
			}
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
		if(hPics.length ==0){
			hPics = [
					{src:"<%= request.getContextPath()%>/images/no-img-1.jpg"}
					];
		}				
			$('#carousel').carousel({
			  images: hPics,  
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

		/* favorite JS code Start */
		var starStatus ="dark";
		var no = $("#memNO").val();
		var hno = location.search.split('NO=')[1] ? location.search.split('NO=')[1] : null;
		var fno = -1;
		
		loadFavorite();

		function loadFavorite() {
			if (no) {
				console.log('會員編號:'+no+', 房屋編號:'+hno);
				$.ajax({
					url:'<%=request.getContextPath()%>/FavoriteServlet?',
					method : 'post',
					data : {
						'action' : 'house_checkAJAX_Action',
						'memNO' : no,
						'houseNO' : hno
					},
					dataType : 'text',
					success : function(data) {
						fno = data;
						if (data != -1) {
							$('#myFavStar img').attr('src','<%=request.getContextPath()%>/images/like_y.png');
							starStatus ="red";
						}
					},
					error : function() {
						alert("您的瀏覽器不支援Ajax!!");
					}
				});
			}
			// heart control
			$('#myFavStar img').click(function() {
				if (starStatus == "dark"){
					if (no) {
						console.log('會員編號:'+no+', 房屋編號:'+hno);
						$.ajax({
							url:'<%=request.getContextPath()%>/FavoriteServlet?',
							method : 'post',
							data : {
								'action' : 'house_insertAJAX_Action',
								'memNO' : no,
								'houseNO' : hno
							},
							dataType : 'text',
							success : function(data) {
								fno = data;
								var thebar = document.getElementById("snackbar");
								$('#snackbar').text("已新增此房屋至收藏庫");
								thebar.className = "show";
								setTimeout(function(){ thebar.className = thebar.className.replace("show", ""); }, 1000);
							},
							error : function() {
								alert("您的瀏覽器不支援Ajax!!");
							}
						});
					}
					$('#myFavStar img').attr('src','<%=request.getContextPath()%>/images/like_y.png');
					starStatus ="red";
				}else{
					if (no && (fno != -1)) {
						console.log('開刪');
						$.ajax({
							url:'<%=request.getContextPath()%>/FavoriteServlet?',
							method : 'post',
							data : {
								'action' : 'house_deleteAJAX_Action',
								'favNO' : fno,
								'houseNO' : hno
							},
							dataType : 'text',
							success : function(data) {
								fno = -1;
								var thebar = document.getElementById("snackbar");
								$('#snackbar').text("已從收藏庫移除此房屋");
								thebar.className = "show";
								setTimeout(function(){ thebar.className = thebar.className.replace("show", ""); }, 1000);
							},
							error : function() {
								alert("您的瀏覽器不支援Ajax!!");
							}
						});
					}
					$('#myFavStar img').attr('src','<%=request.getContextPath()%>/images/like_n.png');
					starStatus ="dark";
				}
			});
		}
		/* favorite JS code End */
		//reservation js code
		//設定顯示上午下午
		$("#reservationForm>div").hover(function(){
			$(this).children("div").toggle("blind",100)
		});
		//設定星期與上午下午checked連動
		$("#reservationForm>div>input").on("click",function(){
			var th = $(this);
			th.parent("div").children("div").children("input").prop("checked",th.prop("checked"));
		});
		//設定上午下午與星期checked連動
		$("#reservationForm>div>div>input").on("click",function(){
			$(this).parent("div").parent("div").children("input").prop("checked",true);
		});
		//設定預約跳出視窗
		var dialog = $("#reservationDiv").dialog({
			autoOpen:false,
			modal: true,
			resizable: false
		});	
		//跳出按鈕
		$("#bt").on("click",function(){
			dialog.dialog("open");
		});
		//取消按鈕
		$("#btCancel").on("click",function(){
			dialog.dialog("close");
		});
		//送出表單資料設定
		$("#btSubmit").on("click",function(){
			if("${LoginOK.memNO}" == ""){
				alert("請先登入");
			}else{
			//jquery的form插件，太神啦
					$("#reservationForm").ajaxSubmit(function(data){
					alert(data);
					dialog.dialog("close");
				});
			}
		});
		//reservation js code end
		//question js code
		var dialogQ = $("#dialog");
		dialogQ.dialog({
			 autoOpen: false,
			 height: 330,
		     width: 350,
		     modal: true,
		     resizable:false,
		     buttons:{
		    	 "送出":function(){
		    		 if("${LoginOK.memNO}" == ""){
		    			 alert("請先登入");
		    		 }else if($("#question").val().trim().length == 0){
		    			 alert("請輸入問題");
		    		 }else{
		    			 $.post("<%=request.getContextPath()%>/QAndAServlet?mission=question"
		    					 ,{question:$("#question").val(),member:"${LoginOK.memNO}",house:"${param.NO}"}
		    		 	,function(data){
		    				 alert(data);
		    				 $("#question").val("");
		    				 dialogQ.dialog("close");	 
		    		 	});
		    		 };
		    	 },
		    	 "取消":function(){
		    		 dialogQ.dialog("close");
		    	 }
		     }
		})
		
		$("#questionBT").click(function(){
			dialogQ.dialog("open");
			
		});
		//question js code end
		//youtube程式碼
		if(house.houseVideo == "" || house.houseVideo == null){
			$("#youtube").parent("div").html("<h2>此房屋無建立影片</h2>");
		}else{
			$("#youtube").attr("src",$("#youtube").attr("src") + house.houseVideo);
		}
		//youtube程式碼 end
	</script>
</body>
</html>