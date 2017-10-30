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
	#footer {
		margin-top:30px;
		margin-bottom:0px;
		width:100%;
		height:100px;
		position:absolute;
		bottom:0;
		left:0;
	}
	}
	.breadBox {
    	width: 100%;
    	display: block;
    	box-sizing: border-box;
	}
	.breadBox .breadNav {
	    width: 1200px;
	    margin: auto;
	    padding: 0;
	}
	.breadBox .breadNav .breadList,.conditionShow{
	    float: left;
	    margin: 0;
	    padding: 0;
	}
	.breadBox .breadNav a {
	    font-size: 13px;
	    line-height: 55px;
	}
	.fa {
	    display: inline-block;
	    font: normal normal normal 14px/1 FontAwesome;
	    font-size: inherit;
	    text-rendering: auto;
	    -webkit-font-smoothing: antialiased;
	    -moz-osx-font-smoothing: grayscale;
	}
	.conditionShow {
	    margin-left: 15px;
	    width: 867px;
	}
	.breadBox .breadNav span {
	    font-size: 12px;
	    color: #A3A3A3;
	}
	.conditionShow span {
	    display: block;
	    float: left;
	    margin-top: 15px;
	    margin-right: 10px;
	    border: 1px solid #D4D4D4;
	    padding: 0px 10px;
	    padding-right: 0px;
	    color: #666666;
	}
	
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
	<div id="top" class="masthead" role="main" style="height:auto;padding-top:80px;padding-bottom:0">
		<div class="container" style="overflow:hidden">
			<span class="w3-jumbo glfont" >Hermit<span>
			</div>
			<div class="row" style="height:auto">
				<div class="col-md-6 col-sm-12 col-md-offset-3 subscribe">
					<form class="form-horizontal" role="form" action="<%= request.getContextPath()%>/index.jsp" id="subscribeForm" method="POST">
						<div class="form-group"  style="overflow:hidden">
							<div class="input-group">
								<input class="form-control input-lg" name="houstTitle" id="houstTitle" placeholder="請輸入您想尋找的關鍵字..." value=${sessionScope.houseTitle}>
								<span class="input-group-addon" style="margin:0;padding:0;background-color: rgba(0,0,0,0)"><button id="submit" type="button" class="btn btn-success btn-lg">搜尋</button></span>
							</div>
								 <div style="height:38vh; width:100%;overflow:hidden">
									<div id="effect" class="form-control" style="background-color:rgba(255,255,255,0.3);height:75%;overflow:auto">
											<div class=" col-md-2" style="">
												<select id="city" name="cityNO" class="form-control form-control-sm" style="border: 1px,solid,gray;">
													<option value="-1">&gt;&nbsp;縣市&nbsp;&lt;</option>
												</select>
											</div>
											<div class=" col-md-2">
												<select id="borough" class="form-control form-control-sm" style="border: 1px,solid,gray;">
												<option value="-1">&gt;&nbsp;鄉鎮區&nbsp;&lt;</option>
												</select>
											</div>
											<div class=" col-md-2">
												<select id="houseType" class="form-control form-control-sm" style="border: 1px,solid,gray;">
												<option value="-1">&gt;&nbsp;房屋類型&nbsp;&lt;</option>
												</select>
											</div>
											<div class=" col-md-2">
												<select id="houseForm" class="form-control form-control-sm" style="border: 1px,solid,gray;">
												<option value="-1">&gt;&nbsp;房屋型態&nbsp;&lt;</option>
												</select>
											</div>
											<div class=" col-md-2">
												<select id="houseSize" class="form-control form-control-sm" style="border: 1px,solid,gray;">
													<option value="-1">&gt;&nbsp;房屋大小&nbsp; &lt;</option>
													<option value="0">10坪以下</option>
													<option value="1">10-15坪</option>
													<option value="2">15-20坪</option>
													<option value="3">20-30坪</option>
													<option value="4">30坪以上</option>
												</select>
											</div>
										<div class="col-md-11  w3-margin text-left" style="background-color: rgba(255,255,255,0.6);height:auto;overflow:auto">
											<div style="height:30px">
												<div class="w3-left" style="width:92%;">
													<label class="radio-inline" style="margin-right:6%" >
														<span class="glcwTeXYen" style="font-size:1.3em">租金 &gt;</span>
													</label>
													<label class="radio-inline">
												      <input type="radio" name="houseRent">不限
												    </label>
													<label class="radio-inline">
												      <input type="radio" name="houseRent">5000以下
												    </label>
												    <label class="radio-inline">
												      <input type="radio" name="houseRent">5000-10000元
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
												    <div class="col-md-2">
													    <label class="radio-inline">
													      <button type="button" id="clearCheckBox" class="btn btn-primary">清除全部</button>
													    </label>
												    </div>
												</div>
											</div>
										</div>									
									</div>
									<span id="advencedSearch" class="w3-right" style="line-height:25px;width:80px;font-size:10px;background-color: rgba(255,255,255,0.7);color:black;border-bottom-left-radius:10px;border-bottom-right-radius:10px;">進階搜尋<span class="glyphicon glyphicon-chevron-up"></span></span>
								</div>
							<div class="col-md-5 col-sm-4">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="w3-black w3-margin-bottom" style="height:3vh;border-bottom-left-radius:15px;border-bottom-right-radius:3px;"></div>
	<section class="breadBox">
        <div class="breadNav clearfix">
            <div class="breadList" id="breadList">
                <a href="<%= request.getContextPath()%>/index.jsp" style="color:black;padding:0;">Hermit</a>
                <i class="glyphicon glyphicon-chevron-right" aria-hidden="true"></i>&nbsp;&nbsp;
            </div>
            <div class="conditionShow clearfix">
				<span>台北市&nbsp;&nbsp;</span>
				<span>中正區&nbsp;&nbsp;</span>
				<span>電梯大樓&nbsp;&nbsp;</span>
				<span>獨立套房&nbsp;&nbsp;</span>
				<span>10坪以下&nbsp;&nbsp;</span>
				<span>有電視&nbsp;&nbsp;</span>
				<span>有床&nbsp;&nbsp;</span>
				<span>洗衣機&nbsp;&nbsp;</span>
				<span>熱水器&nbsp;&nbsp;</span>
				<span>網路&nbsp;&nbsp;</span>
				<span>停車位&nbsp;&nbsp;</span>
				<span>冷氣&nbsp;&nbsp;</span>
				<span>冰箱&nbsp;&nbsp;</span>
				<span>有瓦斯&nbsp;&nbsp;</span>
            </div>
        </div>
    </section>
	<script>
		function openLeftMenu() {
		    document.getElementById("leftMenu").style.display = "block";
		    
		}
		function closeLeftMenu() {
		    document.getElementById("leftMenu").style.display = "none";
		}
		$( function() {
			var sessionHouseTitle = "<%= session.getAttribute("houseTitle") %>";
			var sessionCityNO = <%= session.getAttribute("cityNO") %> ;
			var sessionBoroughNO = <%= session.getAttribute("boroughNO") %>;
			var sessionTypeNO = <%= session.getAttribute("typeNO") %>;
			var sessionFormNO = <%= session.getAttribute("formNO") %>;
			var sessionHouseSize = <%= session.getAttribute("houseSize") %>;
			var sessionHouseRent = <%= session.getAttribute("houseRent") %>;
			var sessionEquid = <%= session.getAttribute("equid") %>

			var spanArrow = $(".glyphicon-chevron-up"); 
			var advencedSearch = $( "#advencedSearch" );
			var effect = $( "#effect" );
			var leftMenu = $("#leftMenu");
			var path = "<%= request.getContextPath()%>";
			var city = $("#city");
			var borough = $("#borough");
			var houseForm= $("#houseForm");
			var houseType= $("#houseType");
			var houseSize = $("#houseSize");
			var houseTitle = $("#houstTitle");
			var BoroughInit = 0;
			var radioButtons = $("input:radio[name='houseRent']");
			var chkboxButtions = $("#equid input:checkbox");
			var indexCheck = "<%= request.getRequestURI() %>" == "/hermit/index.jsp" | "<%= request.getRequestURI() %>" == "/hermit/";
			
			function runEffect(){
				effect.toggle( "blind",  500 );
				if(spanArrow.attr('class') == "glyphicon glyphicon-chevron-down"){
					spanArrow.attr("class","glyphicon glyphicon-chevron-up");
				}else if(spanArrow.attr('class') == "glyphicon glyphicon-chevron-up"){
					spanArrow.attr("class","glyphicon glyphicon-chevron-down")
				}
			};
			
			advencedSearch.on( "click", function() {
			      runEffect();
			});
			
			if(indexCheck){
				effect.hide();
				houseTitle.val("");
				spanArrow.attr("class","glyphicon glyphicon-chevron-down")
			}
		    $.post(path+"/HouseFormServlet.do",{"action":"getAllForm"},function(data){
				var formData = $.parseJSON(data).list;
				houseForm.empty();
				houseForm.append($("<option></option>").text("> 房屋型態 <").val(-1));
					$.each(formData,function(index,value){
						var opt = $("<option></option>").text(value.hForm);
						opt.val(value.formNO);
						houseForm.append(opt);
					})
				if(sessionFormNO != null){
					houseForm.val(sessionFormNO);			
				}
		    })
		    $.post(path+"/HouseTypeServlet.do",{"action":"getAllType"},function(data){
				var typeData = $.parseJSON(data).list;
				houseType.empty();
				houseType.append($("<option></option>").text("> 房屋類型 <").val(-1));
				$.each(typeData,function(index,value){
					var opt = $("<option></option>").text(value.hType);
					opt.val(value.typeNO);
					houseType.append(opt);					
				})
				if(sessionTypeNO != null && (!indexCheck)){
					houseType.val(sessionTypeNO);			
				}
		    })
		    $.post(path+"")
		    
			
			function getCity(){  
				$.post(path+"/CityServlet.do",{"action":"getAllCity"},function(data){
					var cityData = $.parseJSON(data).list;
					city.empty();
					$.each(cityData,function(index,value){
					var opt = $("<option></option>").text(value.cityName);
					opt.val(value.cityNO)
					city.append(opt); 
					})
					if(sessionCityNO != null && (!indexCheck)){
						city.val(sessionCityNO);
					}
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
					if(sessionBoroughNO != null && BoroughInit == 0  && (!indexCheck)){
						borough.val(sessionBoroughNO);
					}
				});
			}
			if(sessionHouseSize != null && (!indexCheck)){
				houseSize.val(sessionHouseSize);
			}
			if(sessionHouseRent != null && sessionHouseRent != -1 && (!indexCheck)){
				$.each(radioButtons,function(index,button){
					if(index == sessionHouseRent){
						button.checked = true;
					}
				})
			}
			if(!indexCheck){
				$.each(sessionEquid,function(key,value){
					if(value){
						$.each(chkboxButtions,function(index,box){
							if(key == box.name){
								box.checked = true;
							}
						})
					}
				})
			}
// 			初始化下拉選單
			$("#clearCheckBox").on("click",function(){
				$.each(chkboxButtions,function(index,box){
					box.checked = false;
				})
			})
			city.on("change",function(){
				BoroughInit = 1;
				getBorough();
				borough.val(-1);
			})
			getCity();	




//			送出查詢條件			
		
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
						houseTitle:houseTitle.val(),
						cityNO:city.val(),
						boroughNO:borough.val(),
						typeNO:houseType.val(),
						formNO:houseForm.val(),
						houseSize:houseSize.val(),
						houseRent:radioButtons.index(radioButtons.filter(':checked')),
						equid:jsonStr
				}
			$.post("<%=request.getContextPath()%>/AdvancedSearch",searchStr,function(data){
					location.replace("<%= request.getContextPath()%>/advancedSearch/search.jsp");
				})
			})
			
		});
			
	</script>
</body>
</html>