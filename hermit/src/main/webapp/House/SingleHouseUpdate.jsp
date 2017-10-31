<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src="/hermit/js/jquery-3.2.1.min.js"></script>
<script src="/hermit/js/bootstrap.js"></script>
<script src="/hermit/js/flashcanvas.js"></script>
<script src="/hermit/js/jSignature.min.js"></script>
<script src="/hermit/js/datatables.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/iEdit.min.css">
<style>
input{
width:100px;
}

</style>
</head>
<body>
	<!-- 載入框架 -->
	<jsp:include page="/fragment/back_side_page.jsp" />
	<!-- bootstrap -->
	<div class="container col-md-10 col-md-offset-5">
	
	<form method="POST" action="/hermit/House.do?action=updateHouse" id="form">
		<div class="form-group">
			<label>房屋編號</label> 
			<input type="text" readonly value="${vo.houseNO}" name="houseNO">
		</div>
		<div class="form-group">
			<label>房屋標題</label>
		    <input type="text" value="${vo.houseTitle}" name="houseTitle">
		</div>
		<div class="form-group">
			<label>縣市</label>
			<select id="cityName" name="cityNO"></select> 
			<input type="hidden" value="${vo.cityVO.cityNO}" id="cityNO" name="cityNO">
		</div>
		<div class="form-group">
			<label>地區</label> 
			<select id="boroughName" name="boroughNO"></select>
			<input type="hidden" value="${vo.boroughsVO.boroughNO}" name="boroughNOe" id="boroughNO">
		</div>
		<div class="form-group">
			<label>最高樓層</label> 
			<input type="text" value="${vo.highestFloor}" name="highestFloor">
		</div>
		<div class="form-group">
			<label>現在樓層</label> 
			<input type="text" value="${vo.nowFloor}" name="nowFloor">
		</div>
		<div class="form-group">
			<label>房屋狀態</label>
			<select name="houseStatus" id="SelectStatus">
			<option>未出租</option>
			<option>已出租</option>
			<option>修繕中</option>
			
			</select> 
<%-- 			<input type="hidden" value="${vo.houseStatus}" name="houseStatus"> --%>
		</div>
		<div class="form-group">
			<label>租金</label> 
			<input type="text" value="${vo.houseRent}" name="houseRent">
		</div>
		<div class="form-group">
			<label>押金</label> 
			<input type="text" value="${vo.houseCharge}" name="housecharge">
		</div>
		<div class="form-group">
			<label>水費</label> 
			<input type="text" value="${vo.waterRate}" name="waterRate">
		</div>
		<div class="form-group">
			<label>電費</label> 
			<input type="text" value="${vo.powerRate}" name="powerRate">
		</div>
		<div class="form-group">
			<label>影片</label>
			 <input type="text" value="${vo.houseVideo}" name="houseVideo">
		</div>
		<div class="form-group">
			<label>房屋類型</label> 
			<select id="houseType" name="typeNO"></select>
			<input id="typeNO" type="hidden" value="${vo.houseTypeVO.typeNO}" name="typeNO">
		</div>
		
		<div class="form-group">
		<lable>形態</lable>
			<select id="houseForm" name="formNO"></select>
			<input id="formNO" type="hidden" value="${vo.houseFormVO.formNO}" name="formNO">
		</div>
		
		<div class="form-group">
			<label>地址</label> <input type="text" value="${vo.houseAddr}"
				name="houseAddr">
		</div>
		<div class="form-group">
			<lable>坪數</lable>
			<input type="text" value="${vo.houseSize }" name="houseSize">
		</div>
		<div>
				<input type="file" id="file">
				<input type="hidden" id="previewPic" name="previewPic" />
				<img id="result" src="${vo.previewPic}" border="0" style="border:none;max-height:200px;max-width:200px;">
		</div>
		<input type="submit" value="修改">
	</form>
	</div>
	
	<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
	<script>
	$(document).ready(function(){

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
// 				console.log(dataJson);
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
 				console.log(dataJson2);
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
				console.log(SelectStatus.find("option").eq(2));
			}else if(houseStatus == "未出租"){
				SelectStatus.find("option").eq(0).prop("selected","true");
				console.log(SelectStatus.find("option").eq(1));
			}else if(houseStatus=="修繕中"){
				SelectStatus.find("option").eq(2).prop("selected","true");
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
		})
	</script>
</body>
</html>