<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>赫米特租屋管理</title>
<jsp:include page="/fragment/index_include.jsp" />
<script src="<%=request.getContextPath()%>/js/jquery.scrolling-tabs.min.js"></script>
<script type="text/javascript" src="js/jquery-equal-height.min.js"></script>
<style>
	body{
		background-color: white;
	}

ul, li {padding: 0;	margin: 0; list-style: none;}
#abgne-block-20110317 {width: 1000px;	/* 圖片的寬 */	height: 360px;	/* 圖片的高 + 30 */}
.abgne-player {width: 1000px;	/* 圖片的寬 */	height: 360px;	/* 圖片的高 */	position: relative;	overflow: hidden;}
.abgne-player ul.abgne-list {position: absolute; width: 9999px;	height: 100%;}
.abgne-player ul.abgne-list li {float: left; width: 1000px;	/* 圖片的寬 */	height: 100%;}
.abgne-player ul.abgne-list img{width: 100%;height: 100%; border: 0;}
.abgne-control {height: 24px; padding: 3px;	color: #fff; font-size: 13px; background: #333;}
.abgne-control ul {float: left;}
.abgne-control ul li {float: left; padding: 0 5px; line-height: 20px; margin: 2px;	background: #666;	cursor: pointer;}
.abgne-control ul.numbers {	margin-left: 13px;}
.abgne-control ul li.current {	background: #fff; color:#000;}
.abgne-control ul li.hover { background: #fff; color:#000;}
</style>
</head>
<body id="body">

	<div class="container" style="margin-bottom: 150px;">
		<div>

		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a style="color:#555" href="#newHouse" aria-controls="newHouse" role="tab" data-toggle="tab">新屋上架</a></li>
		    <li role="presentation"><a href="#hotHouse" style="color:#555" aria-controls="hotHouse" role="tab" data-toggle="tab">熱門物件</a></li>
		  </ul>
		
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" style="margin-bottom:20px;" id="newHouse">
		    </div>
		    <div role="tabpanel" class="tab-pane"  style="margin-bottom:20px;"  id="hotHouse">
		    </div>
		  </div>
		</div>
		<div id="abgne-block-20110317">
		<div class="abgne-player">
			<ul id="adul" class="abgne-list">
			</ul>
		</div>
		<div class="abgne-control">
			<ul class="arrows"> 
				<li class="prev">&lt;</li> 
				<li class="next">&gt;</li> 
			</ul>
		</div>
	</div>
		<script>
	$(function(){
		function ajaxPost() {
 			var ul = $("#adul");
 			$.post("ADManagerServlet", {"action" : "getAllADManagerForJson"}, function(data) { //ul包在此function內
 				dataJson = $.parseJSON(data).list;
 				ul.empty();
 				$.each(dataJson, function(index, VO) {
 					var li = $("<li></li>");
 					var aTag = $("<a target='_blank'></a>").attr("href", VO.adLink);
 					var img1 = $("<img>").attr("src", VO.adImage);
 					aTag.append(img1);
 					li.append(aTag);
					ul.append(li);
				})			
		// 先取得必要的元素並用 jQuery 包裝
		// 再來取得 $block 的高度及設定動畫時間
		var $block = $('#abgne-block-20110317'),
			$slides = $block.find('ul.abgne-list'),
			_width = $block.width(),
			$li = $slides.find('li'),
			$control = $block.find('.abgne-control'),
			_animateSpeed = 600,
			// 加入計時器, 輪播時間及控制開關
			timer, _showSpeed = 3000, _stop = false;
	 
		// 設定 $slides 的寬(為了不讓 li 往下擠)
		$slides.css('width', ($li.length + 1) * _width);
		// 產生 li 選項
		var _str = '';
		for(var i=0, j=$li.length;i<j;i++){
			// 每一個 li 都有自己的 className = playerControl_號碼
			_str += '<li class="abgne-player-control_' + (i+1) + '">' + (i+1) + '</li>';
		}
	 
		// 產生 ul 並把 li 選項加到其中
		var $number = $('<ul class="numbers"></ul>').html(_str).appendTo($control),
			$numberLi = $number.find('li');
	 
		// 並幫 .numbers li 加上 click 事件
		$numberLi.click(function(){
			var $this = $(this);
			$this.addClass('current').siblings('.current').removeClass('current');
	 
			clearTimeout(timer);
			// 移動位置到相對應的號碼
			$slides.stop().animate({
				left: _width * $this.index() * -1
			}, _animateSpeed, function(){
				// 當廣告移動到正確位置後, 依判斷來啟動計時器
				if(!_stop) timer = setTimeout(move, _showSpeed);
			});
	 
			return false;
		}).eq(0).click();
	 
		// 幫 .arrows li 加上 click 事件
		$control.find('ul.arrows li').click(function(){
			var _index = $numberLi.filter('.current').index();
			$numberLi.eq((this.className.indexOf('next')>-1?_index+1:_index-1+$numberLi.length)%$numberLi.length).click();
	 
			return false;
		});
	 
		// 當滑鼠移到 $control li 上時, 加上 .hover 效果
		// 反之則移除
		$control.find('li').hover(function(){
			$(this).addClass('hover');
		}, function(){
			$(this).removeClass('hover');
		});
	 
		// 如果滑鼠移入 $slides 時
		$slides.hover(function(){
			// 關閉開關及計時器
			_stop = true;
			clearTimeout(timer);
		}, function(){
			// 如果滑鼠移出 $block 時
			// 開啟開關及計時器
			_stop = false;
			timer = setTimeout(move, _showSpeed);
		});
		// 計時器使用
		function move(){
			$control.find('ul.arrows li.next').click();
		}
 			})
	}
	ajaxPost();
});

	</script>
	<div>
		<form id="modify" method="get" action="ADManagerServlet">
			<input type="hidden" name="action" value="getOneADManager"> <input
				type="hidden" id="adNO" name="adNO">
		</form>
	</div>
	</div>
	<footer class="w3-bottom w3-black container-fluid text-center">
	<div>
		<ul class="nav nav-pills w3-centered " style="display: flex;font-size:13px;justify-content: center; position: static">
		  <li role="presentation"><a href="<%=request.getContextPath()%>/index.jsp">關於我們</a></li>
		  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></li>
		  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_service_page.jsp">服務條款</a></li>
		  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">隱私權聲明</a></li>
		</ul>
	</div>
    <span class="text-center"><p style="font-size:10px">赫米特開發團隊  Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
	</div>
</footer>

<script>
	$(function(){
		var newHouse = $("#newHouse");
		var hotHouse = $("#hotHouse");
		$('#tabs-container').scrollingTabs();
		function getNewestHouse(){
			$.post("<%= request.getContextPath()%>/House.do",{"action":"getThree"},function(data){
				var newThree = $.parseJSON(data).newHouse;
				var newHouseRow=$("<div class='row' style='margin-top:20px;padding:15px'><div>");
				$.each(newThree,function(index,newHouse){
					var jehDiv =$("<div class='col-md-4 col-sm-6 jQueryEqualHeight1'></div>");
					var cardDiv = $("<div class='card'></div>");
					var cardImg = $("<img style='height:200px;width:300px;' class='card-img-top'>").attr("src",newHouse.previewPic);
					var cardBody = $("<div class='card-body'></div>");
					var cardTitle = $("<h3 class='card-title' style='color:#666;font-weight:bold;font-family: Microsoft JhengHei;font-size:1.2em;margin:10px;'></h3>").text(newHouse.houseTitle);
					var cardText = $("<p style='margin:5px'></p>").html("<span class='glyphicon glyphicon-tag' style='color:red;height:0.5em;width;0.5em;'></span><small style='color:#888;'>"+newHouse.cityName+"</small> " +"<span class='glyphicon glyphicon-tag' style='color:red;height:0.5em;width;0.5em;'></span><small style='color:#888;'>"+newHouse.boroughName +"</small>");
					var cardText2 = $("<p style='margin:5px'></p>").html("<span class='glyphicon glyphicon-tag' style='color:red;height:0.5em;width;0.5em;'></span><small style='color:#888;'>"+ newHouse.houseSize +"坪</small> "+ "<span class='glyphicon glyphicon-tag' style='color:red;height:0.5em;width;0.5em;'></span><small style='color:#888;'>"+ newHouse.hType+"</small> " + "<span class='glyphicon glyphicon-tag' style='color:red;height:height:0.5em;width;0.5em;'></span><small style='color:#888;'>"+ newHouse.hForm+"</small>")
					var link = $("<a class='btn btn-primary'>看屋去</a>").attr("href","<%= request.getContextPath()%>/HousePage?NO="+newHouse.houseNO);
					cardBody.append(cardTitle,cardText,cardText2,link);
					cardDiv.append(cardImg,cardBody);
					jehDiv.append(cardDiv);
					newHouseRow.append(jehDiv)
				})
				newHouse.append(newHouseRow);
			})
		}

		function getHotHouse(){
			$.post("<%= request.getContextPath()%>/House.do",{"action":"getHotHouse"},function(data){
				var newThree = $.parseJSON(data).newHouse;
				var hotHouseRow=$("<div class='row' style='margin-top:20px;padding:15px'><div>");
				$.each(newThree,function(index,newHouse){
					var jehDiv =$("<div class='col-md-4 col-sm-6 jQueryEqualHeight1'></div>");
					var cardDiv = $("<div class='card'></div>");
					var cardImg = $("<img style='height:200px;width:300px;' class='card-img-top'>").attr("src",newHouse.previewPic);
					var cardBody = $("<div class='card-body'></div>");
					var cardTitle = $("<h3 class='card-title' style='color:#666;font-weight:bold;font-family: Microsoft JhengHei;font-size:1.2em;margin:10px;'></h3>").text(newHouse.houseTitle);
					var cardText = $("<p style='margin:5px'></p>").html("<span class='glyphicon glyphicon-tag' style='color:red;height:0.5em;width;0.5em;'></span><small style='color:#888;'>"+newHouse.cityName+"</small> " +"<span class='glyphicon glyphicon-tag' style='color:red;height:0.5em;width;0.5em;'></span><small style='color:#888;'>"+newHouse.boroughName +"</small>");
					var cardText2 = $("<p style='margin:5px'></p>").html("<span class='glyphicon glyphicon-tag' style='color:red;height:0.5em;width;0.5em;'></span><small style='color:#888;'>"+ newHouse.houseSize +"坪</small> "+ "<span class='glyphicon glyphicon-tag' style='color:red;height:0.5em;width;0.5em;'></span><small style='color:#888;'>"+ newHouse.hType+"</small> " + "<span class='glyphicon glyphicon-tag' style='color:red;height:height:0.5em;width;0.5em;'></span><small style='color:#888;'>"+ newHouse.hForm+"</small>")
					var link = $("<a class='btn btn-primary'>看屋去</a>").attr("href","<%= request.getContextPath()%>/HousePage?NO="+newHouse.houseNO);
					cardBody.append(cardTitle,cardText,cardText2,link);
					cardDiv.append(cardImg,cardBody);
					jehDiv.append(cardDiv);
					hotHouseRow.append(jehDiv)
				})
				hotHouse.append(hotHouseRow);
			})
		}
		getNewestHouse();
		getHotHouse();
	})
</script>
</body>
</html>