<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Hermit</title>
<script src='<%=request.getContextPath()%>/js/jquery-3.2.1.min.js'></script>
<script src='<%=request.getContextPath()%>/js/tupianqiehuan.js'></script>
<link href='<%=request.getContextPath()%>/css/tupianqiehuan.css' rel='stylesheet' />
<style>

ul, li {
	padding: 0;
	margin: 0;
	list-style: none;
}
#abgne-block-20110317 {
	width: 1000px;	/* 圖片的寬 */
	height: 360px;	/* 圖片的高 + 30 */
}
.abgne-player {
	width: 1000px;	/* 圖片的寬 */
	height: 360px;	/* 圖片的高 */
	position: relative;
	overflow: hidden;
}
.abgne-player ul.abgne-list {
	position: absolute;
	width: 9999px;
	height: 100%;
}
.abgne-player ul.abgne-list li {
	float: left;
	width: 1000px;	/* 圖片的寬 */
	height: 100%;
}
.abgne-player ul.abgne-list img{
	width: 100%;
	height: 100%;
	border: 0;
}
.abgne-control {
	height: 24px;
	padding: 3px;
	color: #fff;
	font-size: 13px;
	background: #333;
}
.abgne-control ul {
	float: left;
}
.abgne-control ul li {
	float: left;
	padding: 0 5px;
	line-height: 20px;
	margin: 2px;
	background: #666;
	cursor: pointer;
}
.abgne-control ul.numbers {
	margin-left: 13px;
}
.abgne-control ul li.current {
	background: #fff;
	color:#000;
}
.abgne-control ul li.hover {
	background: #fff;
	color:#000;
}

</style>
</head>

<body>
	<h2>Hermit</h2>

	<div id="abgne-block-20110317">
		<div class="abgne-player">
			<ul id="adul" class="abgne-list">
<!-- 				<li><a target="_blank" href="#"><img src="images/a.jpg"></a></li> -->
<!-- 				<li><a target="_blank" href="#"><img src="images/b.jpg"></a></li> -->
<!-- 				<li><a target="_blank" href="#"><img src="images/c.jpg"></a></li> -->

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
 			$.post("../ADManagerServlet", {"action" : "getAllADManagerForJson"}, function(data) { //ul包在此function內
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
<!-- 	<div class="wrapper"> -->
<!-- 		<div id="focus"> -->
<!-- 			<ul id="adul"> -->
				
<!-- 				<li><a href="" target="_blank"><img -->
<!-- 						src="" alt="x" /></a></li> -->
<!-- 				<li><a href="" target="_blank"><img -->
<!-- 						src="" alt="x" /></a></li> -->
<!-- 				<li><a href="" target="_blank"><img -->
<!-- 						src="" alt="x" /></a></li> -->
<!-- 				<li><a href="" target="_blank"><img -->
<!-- 						src="" alt="x" /></a></li> -->

<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<div>
		<form id="modify" method="get" action="../ADManagerServlet">
			<input type="hidden" name="action" value="getOneADManager"> <input
				type="hidden" id="adNO" name="adNO">
		</form>
	</div>
<!-- 	<script> -->
<!-- // 		function ajaxPost() { -->
<!-- // 			var ul = $("#adul"); -->
<!-- // 			$.post("../ADManagerServlet", { -->
<!-- // 				"action" : "getAllADManagerForJson" -->
<!-- // 			}, function(data) { -->
<!-- // 				dataJson = $.parseJSON(data).list; -->
<!-- // 				ul.empty(); -->
<!-- // 				$.each(dataJson, function(index, VO) { -->
<!-- // 					var li = $("<li></li>"); -->
<!-- // 					var aTag = $("<a></a>").attr("href", VO.adLink); -->
<!-- // 					var img1 = $("<img>").attr("src", VO.adImage); -->
<!-- // 					aTag.append(img1); -->
<!-- // 					li.append(aTag); -->
<!-- // 					ul.append(li); -->
<!-- // 				}) -->
<!-- // 			}) -->
<!-- // 		} -->
<!-- // 		ajaxPost(); -->
<!-- 	</script> -->
</body>
</html>