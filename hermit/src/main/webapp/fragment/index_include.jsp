<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>赫米特租屋管理</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/w3.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css"/>
<link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
<link href="http://fonts.googleapis.com/earlyaccess/cwtexyen.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/flashcanvas.js"></script>
<script src="<%=request.getContextPath()%>/js/jSignature.min.js"></script>
<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
<style>
	*{
		margin:0;
		padding:0;
	}
	a:link, a:visited, a:hover ,a:active{
		color : white;
	    text-decoration: none;
	    padding: 14px 25px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	}
	#hermitHome {
		color : white;
	}
	.w3-sidebar span{
		color:white;
		font-family: 'cwTeXYen', sans-serif;
	}
	.main-gallery
	{	
	    width:100%;
	    height:50vh;
	    background:url("images/main.jpg");
		background-position:center;
	    background-size:cover;
	    margin-top:52px;
	}
	.w3-jumbo span{
		font-family:"Tangerine",serif;
	}
	.glfont {
    font-family: 'Pacifico', cursive;
	}
	.glcwTeXYen{
		font-family: 'cwTeXYen', sans-serif;
	}
  #button { padding: .5em 1em; text-decoration: none; }
</style>
</head>
<body>
	<div class="w3-bar w3-black navbar-fixed-top glcwTeXYen">
		<button class="w3-button w3-dark-grey w3-xlarge w3-left" onclick="openLeftMenu()">&#9776;</button>
		<a href="<%=request.getContextPath()%>/index.jsp" class="w3-bar-item w3-button w3-xlarge w3-left glfont"><span id="hermitHome">Hermit</span></a>
		<a href="#" class="w3-bar-item w3-button w3-xlarge w3-right w3-margin-right" ><span id="hermitHome">註冊</span></a>
		<span class="w3-bar-item  w3-xlarge w3-right" id="hermitHome">|</span>
		<a href="#" class="w3-bar-item w3-button w3-xlarge w3-right"><span id="hermitHome">登入</span></a>
		<div class="w3-sidebar w3-bar-block w3-animate-left navbar-fixed-top w3-dark-gray" style="color:white;display:none;font-size:20px;font-family:Microsoft JhengHei;" id="leftMenu">
		  <button onclick="closeLeftMenu()" class="w3-bar-item w3-button w3-large"><span>Close &times</span></button>
		  <a href="<%=request.getContextPath()%>/index.jsp" class="w3-bar-item w3-button"><span>首頁</span></a>
		  <a href="<%=request.getContextPath()%>/memberbackstage/mem_back_index.jsp" class="w3-bar-item w3-button"><span>會員中心</span></a>
		  <a href="<%=request.getContextPath()%>/memberbackstage/mem_back_favorite.jsp" class="w3-bar-item w3-button"><span>我的收藏</span></a>
		</div>	   
	</div>
	<div id="top" class="masthead" role="main">
		<div class="container" style="overflow:inherit">
			<span class="w3-jumbo glfont" >Hermit<span>
			</div>
			<div class="row">
				<div class="col-md-6 col-sm-12 col-md-offset-3 subscribe">
					<form class="form-horizontal" role="form" action="<%= request.getContextPath()%>/index.jsp" id="subscribeForm" method="POST">
						<div class="form-group"  style="overflow:inherit">
							<div class="input-group">
								<input class="form-control input-lg" name="houstTitle" id="houstTitle" placeholder="請輸入您想尋找的關鍵字...">
								<span class="input-group-addon" style="margin:0;padding:0;background-color: rgba(0,0,0,0)"><button id="submit" type="button" class="btn btn-success btn-lg">搜尋</button></span>
							</div>
								 <div style="height:38vh; width:100%;overflow:hidden">
									<div id="effect" class="form-control" style="background-color:rgba(255,255,255,0.3);height:80%;overflow:auto">
											<div class=" col-md-2" style="">
												<select id="city" name="cityNO" class="form-control form-control-sm" style="border: 1px,solid,gray;">
												</select>
											</div>
											<div class=" col-md-2">
												<select id="borough" class="form-control form-control-sm" style="border: 1px,solid,gray;">
												</select>
											</div>
											<div class=" col-md-2">
												<select id="houseType" class="form-control form-control-sm" style="border: 1px,solid,gray;">
												</select>
											</div>
											<div class=" col-md-2">
												<select id="houseForm" class="form-control form-control-sm" style="border: 1px,solid,gray;">
												</select>
											</div>
											<div class=" col-md-2">
												<select id="houseSize" class="form-control form-control-sm" style="border: 1px,solid,gray;">
													<option value="-1">&gt; 房屋大小  &lt;</option>
													<option value="0">10坪以下</option>
													<option value="1">10-15坪</option>
													<option value="2">15-20坪</option>
													<option value="3">20-30坪</option>
													<option value="4">30坪以上</option>
												</select>
											</div>
										<div class="col-md-11  w3-margin text-left" style="background-color: rgba(255,255,255,0.6);height:auto;overflow:auto">
											<div style="height:30px">
												<div class="w3-left" style="width:87%;">
													<label class="radio-inline" style="margin-right:6%" >
														<span class="glcwTeXYen" style="font-size:1.3em">租金 &gt;</span>
													</label>
													<label class="radio-inline">
												      <input type="radio" id="rent1" name="houseRent">5000以下
												    </label>
												    <label class="radio-inline">
												      <input type="radio" id="rent2" name="houseRent">5000-10000元
												    </label>
												    <label class="radio-inline">
												      <input type="radio" name="houseRent">10000-20000元
												    </label>
												    <label class="radio-inline">
												      <input type="radio" name="houseRent">20000-30000元
												    </label>
												    <label class="radio-inline">
												      <input type="radio" name="houseRent">30000元以上
												    </label>
												</div>
											</div>
											<div style="height:150px">
												<div class="col-md-12">
													<label class="radio-inline w3-left" style="display:block;padding-left:4px">
															<span class="glcwTeXYen" style="font-size:1.3em">其他設備 :</span>
													</label>
												</div>
												<div id="equid" style="width:86%;">
													<div class="col-md-2">
														<label class="radio-inline">
													      <input type="checkbox" name="TV">電視
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="aircondition">冷氣
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="refrigerator">冰箱
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="waterHeater">熱水器
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="gas">瓦斯
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="theFourthStation">第四台
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="net">網路
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name=washing>洗衣機
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="bed">床
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="wardrobe">衣櫃
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="sofa">沙發
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="parking">停車位
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="elevator">電梯
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="balcony">陽台
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="permitCook">可開伙
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="pet">養寵物
													    </label>
												    </div>
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <input type="checkbox" name="closeMRT">近捷運
													    </label>
												    </div>
												</div>
											</div>
										</div>									
									</div>
									<span id="advencedSearch" class="w3-right" style="line-height:20px;width:70px;font-size:10px;background-color: rgba(255,255,255,0.7);color:black;border-bottom-left-radius:10px;border-bottom-right-radius:10px;">進階搜尋<span class="glyphicon glyphicon-chevron-down"></span></span>
								</div>
							<div class="col-md-5 col-sm-4">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="w3-black" style="height:3vh;border-bottom-left-radius:15px;border-bottom-right-radius:3px;"></div>
	<footer class="w3-bottom w3-black container-fluid" >
		<div class="row nav">
		  <div class="col-md-4"></div>
		  <div class="col-md-1"><a href="<%=request.getContextPath()%>/index.jsp">關於我們</a></div>
		  <div class="col-md-1"><a href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></div>
		  <div class="col-md-1"><a href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">服務條款</a></div>
		  <div class="col-md-1"><a href="<%=request.getContextPath()%>/register/law_service_page.jsp">隱私權聲明</a></div>
		  <div class="col-md-4"></div>
		</div>
	    <span class="text-center"><p style="font-size:10px">赫米特開發團隊  Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
		</div>
	</footer>
	<script>
		$( function() {
			var spanArrow = $(".glyphicon-chevron-down"); 
			var advencedSearch = $( "#advencedSearch" );
			var effect = $( "#effect" );
			var leftMenu = $("#leftMenu");
			var path = "<%= request.getContextPath()%>";
			var city = $("#city");
			var borough = $("#borough");
			var houseForm= $("#houseForm");
			var houseType= $("#houseType");
			var houseSize = $("#houseSize");
			function runEffect(){
				effect.toggle( "blind",  500 );
				if(spanArrow.attr('class') == "glyphicon glyphicon-chevron-up"){
					spanArrow.attr("class","glyphicon glyphicon-chevron-down");
				}else{
					spanArrow.attr("class","glyphicon glyphicon-chevron-up")
				}
			};
			
			advencedSearch.on( "click", function() {
			      runEffect();
			});
		    effect.hide(); 
		    $.post(path+"/HouseFormServlet.do",{"action":"getAllForm"},function(data){
				var formData = $.parseJSON(data).list;
				houseForm.append($("<option></option>").text("> 房屋型態 <").val(-1));
				$.each(formData,function(index,value){
					var opt = $("<option></option>").text(value.hForm);
					opt.val(value.formNO);
					houseForm.append(opt);
				})
		    })
		    $.post(path+"/HouseTypeServlet.do",{"action":"getAllType"},function(data){
				var typeData = $.parseJSON(data).list;
				houseType.append($("<option></option>").text("> 房屋類型 <").val(-1));
				$.each(typeData,function(index,value){
					var opt = $("<option></option>").text(value.hType);
					opt.val(value.typeNO);
					houseType.append(opt);					
				})
		    })
		    $.post(path+"")
		    
			
			function getCity(){  
				$.post(path+"/CityServlet.do",{"action":"getAllCity"},function(data){
					var cityData = $.parseJSON(data).list;
					$.each(cityData,function(index,value){
					var opt = $("<option></option>").text(value.cityName);
					opt.val(value.cityNO)
					city.append(opt); 
					})
					getBorough();
				});
			}
			function getBorough(){
				$.post(path+"/BoroughsServlet.do",{"action":"getAllBoroughByCity","cityNO":city.val()},function(data){
					var boroughData = $.parseJSON(data).list;
					borough.empty();
					borough.append($("<option></option>").text("> 鄉鎮區 <").val(-1));
					$.each(boroughData,function(index,value){
						var opt = $("<option></option>").text(value.boroughName);
						opt.val(value.boroughNO)
						borough.append(opt); 
					})
				});
			}
			city.on("change",function(){
				getBorough();
			})
// 			初始化下拉選單
			getCity();	
			var radioButtons = $("input:radio[name='houseRent']");
			var chkboxButtions = $("input:checkbox");
			$("#submit").on("click",function(){
				var jsonStr = JSON.stringify({
						TV:$("#equid label input[name='TV']").prop('checked'),
						aircondition:$("#equid label input[name='aircondition']").prop('checked'),
						refrigerator:$("#equid label input[name='refrigerator']").prop('checked'),
						waterHeater:$("#equid label input[name='waterHeater']").prop('checked'),
						gas:$("#equid label input[name='gas']").prop('checked'),
						theFourthStation:$("#equid label input[name='theFourthStation']").prop('checked'),
						net:$("#equid label input[name='net']").prop('checked'),
						washing:$("#equid label input[name='washing']").prop('checked'),
						bed:$("#equid label input[name='bed']").prop('checked'),
						wardrobe:$("#equid label input[name='wardrobe']").prop('checked'),
						sofa:$("#equid label input[name='sofa']").prop('checked'),
						parking:$("#equid label input[name='parking']").prop('checked'),
						elevator:$("#equid label input[name='elevator']").prop('checked'),
						balcony:$("#equid label input[name='balcony']").prop('checked'),
						permitCook:$("#equid label input[name='permitCook']").prop('checked'),
						pet:$("#equid label input[name='pet']").prop('checked'),
						closeMRT:$("#equid label input[name='closeMRT']").prop('checked'),	
				});
				var searchStr = {
						cityNO:city.val(),
						boroughNO:borough.val(),
						typeNO:houseType.val(),
						formNO:houseForm.val(),
						houseSize:houseSize.val(),
						houseRent:radioButtons.index(radioButtons.filter(':checked')),
						equid:jsonStr
				}
			$.post("<%=request.getContextPath()%>/AdvancedSearch",searchStr,function(data){
					console.log(data);
				})
			})
			
		});
			function openLeftMenu() {
			    document.getElementById("leftMenu").style.display = "block";
			    
			}
			function closeLeftMenu() {
			    document.getElementById("leftMenu").style.display = "none";
			}
	</script>
</body>
</html>