<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>赫米特租屋管理</title>
<jsp:include page="/fragment/index_include.jsp" />
<link href='<%=request.getContextPath()%>/css/tupianqiehuan.css' rel='stylesheet' />
<script src='<%=request.getContextPath()%>/js/tupianqiehuan.js'></script>
<script src="<%=request.getContextPath()%>/js/jquery.scrolling-tabs.min.js"></script>
<script type="text/javascript" src="js/jquery-equal-height.min.js"></script>
<style>
	body{
		background-color: white;
	}
</style>
</head>
<body id="body">

	<div class="container" style="margin-bottom: 150px;">
		<div>

		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a style="color:#555" href="#newHouse" aria-controls="newHouse" role="tab" data-toggle="tab">新屋上架</a></li>
		    <li role="presentation"><a href="#hot" style="color:#555" aria-controls="hot" role="tab" data-toggle="tab">熱門物件</a></li>
		  </ul>
		
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" style="margin-bottom:20px;" id="newHouse">
		    </div>
		    <div role="tabpanel" class="tab-pane"  style="margin-bottom:20px;"  id="hot">
		    	<div class="row">
				  <div class="col-md-4 col-sm-6 jQueryEqualHeight1">
				      <div class="card">
				          <img class="card-img-top" src="http://via.placeholder.com/318x180" alt="Card image cap">
				          <div class="card-body">
				              <h4 class="card-title">Card title</h4>
				              <p class="card-text"></p>
				              <a href="#" class="btn btn-primary">看房去</a>
				          </div>
				      </div>
				  </div>
				  <div class="col-md-4 col-sm-6 jQueryEqualHeight1">
				      <div class="card">
				          <img class="card-img-top" src="http://via.placeholder.com/318x180" alt="Card image cap">
				          <div class="card-body">
				              <h4 class="card-title">Card title</h4>
				              <p class="card-text"></p>
				              <a href="#" class="btn btn-primary">看房去</a>
				          </div>
				      </div>
				  </div>
				
				  <div class="col-md-4 col-sm-6 jQueryEqualHeight1">
				      <div class="card">
				          <img class="card-img-top" src="http://via.placeholder.com/318x180" alt="Card image cap">
				          <div class="card-body">
				              <h4 class="card-title"></h4>
				              <p class="card-text"></p>
				              <a href="#" class="btn btn-primary">看房去</a>
				          </div>
				      </div>
				  </div>
	   		  </div>
		    </div>
		  </div>
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
		
		$('#tabs-container').scrollingTabs();
		
		$.post("<%= request.getContextPath()%>/House.do",{"action":"getThree"},function(data){
			var newThree = $.parseJSON(data).newHouse;
			console.log(newThree);
			var newHouseRow=$("<div class='row' style='margin-top:20px;padding:15px'><div>");
			$.each(newThree,function(index,newHouse){
				var jehDiv =$("<div class='col-md-4 col-sm-6 jQueryEqualHeight1'></div>");
				var cardDiv = $("<div class='card'></div>");
				var cardImg = $("<img style='height:200px;width:300px;' class='card-img-top'>").attr("src",newHouse.previewPic);
				var cardBody = $("<div class='card-body'></div>");
				var cardTitle = $("<h3 class='card-title' style='color:#666;font-weight:bold;font-family: Microsoft JhengHei;font-size:1.4em;margin:10px;'></h3>").text(newHouse.houseTitle);
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
	})
</script>
</body>
</html>