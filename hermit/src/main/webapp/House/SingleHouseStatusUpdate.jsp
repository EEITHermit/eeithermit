<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/hermit/css/bootstrap.min.css">
<link rel="stylesheet" href="/hermit/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/hermit/css/datatables.min.css"/>
<!-- 房屋表格用↓ -->
<link rel="stylesheet" 
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 房屋表格用↑ class="table"	 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/iEdit.min.css">
<link
	href='<%=request.getContextPath()%>/css/jqueryText/jquery-te-1.4.0.css'
	rel='stylesheet' />


<style>
.eq1{
width:78px;
}
.eq2{
width:72px;
}
.eq3{
width:86px;
}
.eq4{
width:86px;
}
.eq5{
width:70px;
}
input{
width:100px;
}
.container{
padding-bottom:40px;
}
label{ 
 	font-family: 標楷體; 
 	font-size: 16px;
 	} 
 textarea{
    padding: 20px; 
    width: 280px;
    resize: none;
    overflow: auto;
}
.form-group{
padding-right:200px;
}

</style>
</head>
<body>
	<!-- 載入框架 -->
	<jsp:include page="/fragment/back_side_page.jsp" />
	<!-- bootstrap -->
	<div class="container">
	
	<form method="POST" action="/hermit/House.do?action=updateHouseStatus" id="form" class="form-horizontal">
	<div class="form-group">
		<div>
			<label class="col-md-5 control-label">房屋編號</label> 
			<div class="col-xs-2">
			 <input type="text" readonly value="${vo.houseNO}" name="houseNO" class="form-control" id="houseNO">
			</div>	
		</div>
		
	</div>
	<div class="form-group">
	<div>
			<label for="houseTitle" class="col-md-5 control-label">房屋標題</label>
			<div class="col-xs-2">
		    <input type="text" readonly value="${vo.houseTitle}" name="houseTitle" id="houseTitle" class="form-control">
		    </div>
		</div>
		
					<div class="col-xs-2">
						<input type="hidden" readonly value="${vo.cityVO.cityNO}" id="cityNO" name="cityNO">
					</div>
				<div>
					<input type="hidden" readonly value="${vo.boroughsVO.boroughNO}" name="boroughNO" id="boroughNO">
				</div>
		</div>
		<div class="form-group">
			<div>
					<div class="col-xs-2">
						<input type="hidden" readonly value="${vo.highestFloor}" name="highestFloor" id="highestFloor" class="form-control">
					</div>
			</div>
			<div>
					<div class="col-xs-2">
						<input type="hidden" value="${vo.nowFloor}" name="nowFloor" id="newfloor" class="form-control">
					</div>
			</div>
		</div>
		<div class="form-group">
		
		<div>
			<label for="houseStatus" class="col-md-5 control-label">房屋狀態</label>
				<div class="col-xs-2">
						<select name="houseStatus" id="SelectStatus" class="form-control">
							<option>未出租</option>
							<option>已出租</option>
							
							
						</select> 
				</div>
		</div>
			<div>
					<div class="col-xs-2">
						<input type="hidden" readonly value="${vo.houseRent}" name="houseRent" id="houserent"  class="form-control">
					</div> 
			</div>
		</div>
		<div class="form-group">
			<div>
					<div class="col-xs-2">
						<input type="hidden" readonly value="${vo.houseCharge}" name="housecharge" id="housecharge" class="form-control">
					</div>
			</div>
			<div>
					<div class="col-xs-2">
						<input type="hidden" readonly value="${vo.waterRate}" name="waterRate" id="waterRate" class="form-control">
					</div>
			</div>
		</div>
		<div class="form-group">
			<div>
					<div class="col-xs-2">
						<input type="hidden" readonly value="${vo.powerRate}" name="powerRate" class="form-control">
					</div>
			</div>
<!-- 		<div class="form-group"> -->
<!-- 			<label>影片</label> -->
<%-- 			 <input type="text" value="${vo.houseVideo}" name="houseVideo"> --%>
<!-- 		</div> -->
			<div>
					<div class="col-xs-2">
							<input id="typeNO" type="hidden" readonly value="${vo.houseTypeVO.typeNO}" name="typeNO" class="form-control">
					</div> 
			</div>
		</div>
		
		<div class="form-group">
			<div>
					<div class="col-xs-2">
						<input id="formNO" type="hidden" readonly value="${vo.houseFormVO.formNO}" name="formNO" class="form-control">
					</div>
			</div>
			<div>
					<div class="col-xs-2">
						<input type="hidden" readonly value="${vo.houseAddr}" name="houseAddr" id="houseAddr" class="form-control">
					</div>
			</div>
		</div>
		<div class="form-group">
			<div>
					<div class="col-xs-2">
						<input type="hidden" readonly value="${vo.houseSize}" name="houseSize" id="houseSize" class="form-control">
					</div>
			</div>
			<div>
					<div class="col-xs-2">	
						<input type="hidden" id="previewPic" name="previewPic" value="${vo.previewPic}" />
					</div>
			</div>
		</div>
		<div class="form-group">
			<div>
					<div class="col-xs-2"  style="background-color:white;">
						<input type="hidden" readonly value="${vo.houseVideo}" name="houseVideo" id="houseVideo" class="form-control">
					</div>
			</div>
			<div>
					<div class="col-xs-2">	
					</div>
			</div>
		</div>
		<div class="form-group">
				<div class="col-md-6 col-offset-1">
					<input type="hidden" readonly value="{vo.houseInfo}" name="houseInfo">
				</div>
		</div>

		<div class="form-group" >
			<div class="col-md-6 control-label">
				<input type="submit" value="修改">
			</div>
		</div>
	<div id="houseeq">
		<input type="hidden" readonly name="TV" id="TV" class="eq">
		<input type="hidden" readonly name="aircondition" id="aircondition" class="eq">
		<input type="hidden" readonly name="refrigerator" id="refrigerator" class="eq">
		<input type="hidden" readonly name="gas" id="gas" class="eq">
		<input type="hidden" readonly name="net" id="net" class="eq">
		<input type="hidden" readonly name="wardrobe" id="wardrobe" class="eq">
		<input type="hidden" readonly name="bed" id="bed" class="eq">
		<input type="hidden" readonly name="sofa" id="sofa" class="eq">
		<input type="hidden" readonly name="elevator" id="elevator" class="eq">
		<input type="hidden" readonly name="balcony" id="balcony" class="eq">
		<input type="hidden" readonly name="permitCook" id="permitCook" class="eq">
		<input type="hidden" readonly name="theFourthStation" id="theFourthStation" class="eq">
		<input type="hidden" readonly name="pet" id="pet" class="eq2">
		<input type="hidden" readonly name="waterHeater" id="waterHeater" class="eq3">
		<input type="hidden" readonly name="closeMRT" id="closeMRT" class="eq4">
		<input type="hidden" readonly name="washing" id="washing" class="eq5">
		<input type="hidden" readonly name="parking" id="parking" class="eq6">
	</div>
		</form>
		<form id="insertPicForm" method="post" action="<%=request.getContextPath()%>/HousePictureServlet" enctype="multipart/form-data">
			<div class="form-group">
				<div class="col-md-12 control-label">
					<input type="hidden" readonly value="${vo.houseNO}" name="houseNO">
					<input type="hidden" name="action" action="insertHousePicture" >
				</div>
			</div>
		</form>
		
		<div class="form-group" id="HousePicture">
			<div>	
			</div>
		</div>	
	
	</div>
<!-- 	<script src="/hermit/js/jquery-3.2.1.min.js"></script> -->
	<script src="/hermit/js/bootstrap.js"></script>
	<script src="/hermit/js/flashcanvas.js"></script>
	<script src="/hermit/js/jSignature.min.js"></script>
	<script src="/hermit/js/datatables.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
	<script src='<%=request.getContextPath()%>/js/jquery-te-1.4.0.min.js'></script>
	<script>
	$(document).ready(function(){
			var dataJson;
			var houseDiv=$("#HousePicture>div");
			var hPics = $.parseJSON('<%= request.getAttribute("hPics")%>');
			
			$.each(hPics,function(index,pic){
				var cell1 = $("<div class='col-xs-2'></div>");
				var cell2=$("<img style='border:none;height:200px;width:200px' id='hpic'>").attr("src",pic.housepic);
				var cell3 = $("<input type='hidden'>").val(pic.housePictureNO);
				var cell4 = $("<button type='button' class='delBtn'></button)").text("刪除");
				var imgDiv=$("<div class='col-md-4'></div>").append(cell1,cell2,cell3,cell4);
				houseDiv.append(imgDiv);
				})
				$("#addhPicture").click(function(){
					$('input[name="action"]').val("insertHousePicture");
					$("#insertPicForm").submit(function(e){
						if(confirm("確定要新增嗎?")==true){
							return;
						}else{
							e.preventDefault();
						}
					})
				})				
				
			$(".delBtn").on('click',function(){
				var picNO =$(this).parent().children("input").val();
				var picDiv = $(this).parent();
				if(confirm("確定要刪除嗎?")==true){
				$.post("/hermit/HousePictureServlet",{"action":"deleteHousePic","housePictureNO":picNO},function(){
					picDiv.remove();
				})
				}
			})
			var dataeq;
			var houseNO=$("#houseNO").val();
			var checkbox=$("#houseeq input:checkbox")
// 			console.log(houseNO);
			$.post("/hermit/EquipmentConditionServlet",{action:"getOneEquip","houseNO":houseNO},function(data){
// 				console.log(data);
				dataeq=$.parseJSON(data);
// 				console.log(dataeq);
				
				$.each(checkbox,function(index,box){
					//name=box.name
					a=box.name
					console.log(dataeq.a);
					$.each(dataeq,function(index1,box1){
						if(box.name==index1&&box1){
							box.checked = true;
						}
						
					})
					
				})
				
			})
			
			var dataJson;
			var selectForm= $("#houseForm");
			var formNO = $("#formNO").val();

			var selectCity=$("#cityName")
			var cityNO=$("#cityNO").val();
			
			var selectBorough=$("#boroughName");
			var boroughNO=$("#boroughNO").val();
			
			//從查全部取得房屋狀態
			var SelectStatus=$("#SelectStatus");
// 			console.log(selectCity);
			$.post("/hermit/HouseFormServlet.do", {action : "getAllForm"}, function(data) {
				dataJson = $.parseJSON(data).list;
// 				console.log(data);
				$.each(dataJson,function(index,VO){
					var cell1= $("<option></option>").text(VO.hForm);
					cell1.val(VO.formNO);
// 					console.log(formNO);
// 					console.log(VO.formNO);
					if(formNO == VO.formNO){
						cell1.prop("selected","true");
					}
					selectForm.append(cell1);
					
				})
				
			})
			var selectType=$("#houseType");
			var typeNO=$("#typeNO").val();
			$.post("/hermit/HouseTypeServlet.do",{action:"getAllType"},function(data){
// 				console.log(data);
				var dataJson2 = $.parseJSON(data).list;
//  				console.log(dataJson2);
				$.each(dataJson2,function(index,VO){
					var cell2=$("<option></option>").text(VO.hType);
					cell2.val(VO.typeNO);
// 					console.log(VO.typeNO);
					if(typeNO==VO.typeNO){
						cell2.prop("selected","true");
					}
					selectType.append(cell2);
				})
				
			})
			$.post("/hermit/CityServlet.do",{action:"getAllCity"},function(data){
				dataJson=$.parseJSON(data).list;
// 				console.log(data);
				$.each(dataJson,function(index,VO){
					var cell1=$("<option></option>").text(VO.cityName);
					cell1.val(VO.cityNO);
					if(cityNO==VO.cityNO){
						cell1.prop("selected","true");
					}
					selectCity.append(cell1);
				})
			})
			$("#cityName").change(function(){
				
				var cityNO = ($("#cityName").val());
				$("#boroughName").html("");
				$.post("/hermit/BoroughsServlet.do",{action:"getAllBoroughByCity",cityNO:cityNO},function(data){
					dataJson=$.parseJSON(data).list;
// 	 				console.log(data);
					$.each(dataJson,function(index,VO){
						var cell1=$("<option></option>").text(VO.boroughName);
						cell1.val(VO.boroughNO);
						if(boroughNO==VO.boroughNO){
							cell1.prop("selected","true");
						}
						selectBorough.append(cell1);
					})
				})
			})
			
			//從查全部的JSP過來，取值用
			$.post("/hermit/BoroughsServlet.do",{action:"getAllBoroughByCity",cityNO:cityNO},function(data){
				var cityNO = ($("#cityName").val());
// 	 				console.log(data);
					dataJson=$.parseJSON(data).list;
					$.each(dataJson,function(index,VO){
						var cell1=$("<option></option>").text(VO.boroughName);
						cell1.val(VO.boroughNO);
						if(boroughNO==VO.boroughNO){
							cell1.prop("selected","true");
						}
						selectBorough.append(cell1);
					})
				})
							
			var houseStatus = "${vo.houseStatus}";
			if(houseStatus == "已出租"){
				SelectStatus.find("option").eq(1).prop("selected","true");
			}else if(houseStatus == "未出租"){
				SelectStatus.find("option").eq(0).prop("selected","true");
			}
			$("#file").change(function(e){
				  
				  var img = e.target.files[0];

				  if(!img.type.match('image.*')){
				    alert("Whoops! That is not an image.");
				    return;
				  }
				  iEdit.open(img, true, function(res){
				    $("#result").attr("src", res);
				  });
				  //在檔案送出前，讓image的src送到input裡
				  $("#form").submit(function(event){
					  $("#previewPic").val($("#result").attr("src"));
				  }) 
				});
			$('#houseInfo').jqte();
			//設定擷取影片
			var houseNO = "${vo.houseNO}";
			$("#flash").on("click",function(){
				$.get("https://www.googleapis.com/youtube/v3/playlistItems",
						{"playlistId":"UUDSox71tKcU7rgORkVZg2Kg","part":"snippet",
						 "maxResults":"25",	
						 "key":"AIzaSyDGn6cCVOXBpeABaTbt_RINlOo1oNAla2U"},
						 function(data){
							console.log(data);
							var items = data.items;
							$.each(items,function(i,item){
								if(item.snippet.title == houseNO){
									var videoId = item.snippet.resourceId.videoId						
									$("#houseVideo").val(videoId);
									$("#link").toggle(true).attr("href","https://www.youtube.com/watch?v=" + videoId);
									$("#link").next().toggle(false);
									alert("擷取成功!");
									return false;
								};
								if(i == items.length-1){
									alert("無此房屋影片");
								}
							});
						});
			});
		})
	</script>
</body>
</html>