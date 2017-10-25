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
<script src="/hermit/js/jquery-3.2.1.min.js"></script>
<script src="/hermit/js/bootstrap.js"></script>
<script src="/hermit/js/flashcanvas.js"></script>
<script src="/hermit/js/jSignature.min.js"></script>
<script src="/hermit/js/datatables.min.js"></script>
</head>
<body>
	<form method="POST" action="/hermit/House.do?action=updateHouse">
		<div>
			<label>房屋編號</label> 
			<input type="text" readonly value="${vo.houseNO}" name="houseNO">
		</div>
		<div>
			<label>房屋標題</label>
		    <input type="text" value="${vo.houseTitle}" name="houseTitle">
		</div>
		<div>
			<label>縣市</label>
			<select id="cityName" name="cityNO"></select> 
			<input type="hidden" value="${vo.cityNO}" id="cityNO" name="cityNO">
		</div>
		<div>
			<label>地區</label> 
			<select id="boroughName" name="boroughNO"></select>
			<input type="hidden" value="${vo.boroughNO}" name="boroughNOe" id="boroughNO">
		</div>
		<div>
			<label>最高樓層</label> 
			<input type="text" value="${vo.highestFloor}" name="highestFloor">
		</div>
		<div>
			<label>現在樓層</label> 
			<input type="text" value="${vo.nowFloor}" name="nowFloor">
		</div>
		<div>
			<label>房屋狀態</label>
			<select name="houseStatus" id="SelectStatus">
			<option>未出租</option>
			<option>已出租</option>
			<option>修繕中</option>
			
			</select> 
<%-- 			<input type="hidden" value="${vo.houseStatus}" name="houseStatus"> --%>
		</div>
		<div>
			<label>租金</label> 
			<input type="text" value="${vo.houseRent}" name="houseRent">
		</div>
		<div>
			<label>押金</label> 
			<input type="text" value="${vo.houseCharge}" name="housecharge">
		</div>
		<div>
			<label>水費</label> 
			<input type="text" value="${vo.waterRate}" name="waterRate">
		</div>
		<div>
			<label>電費</label> 
			<input type="text" value="${vo.powerRate}" name="powerRate">
		</div>
		<div>
			<label>影片</label>
			 <input type="text" value="${vo.houseVideo}" name="houseVideo">
		</div>
		<div>
			<label>房屋類型</label> 
			<select id="houseType" name="typeNO"></select>
			<input id="typeNO" type="hidden" value="${vo.typeNO}" name="typeNO">
		</div>
		
		<div>
		<lable>形態</lable>
			<select id="houseForm" name="formNO"></select>
			<input id="formNO" type="hidden" value="${vo.formNO}" name="formNO">
		</div>
		
		<div>
			<label>地址</label> <input type="text" value="${vo.houseAddr}"
				name="houseAddr">
		</div>
		<div>
			<lable>坪數</lable>
			<input type="text" value="${vo.houseSize }" name="houseSize">
		</div>
		<input type="submit" value="修改">
		
		
		
	</form>

	<script>
	$(document).ready(function(){

			var dataJson;
			var selectForm= $("#houseForm");
			var formNO = $("#formNO").val();
			
			var selectType=$("#houseType");
			var typeNO=$("#typeNO").val();
			
			var selectCity=$("#cityName")
			var cityNO=$("#cityNO").val();
			
			var selectBorough=$("#boroughName");
			var boroughNO=$("#boroughNO").val();
			
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
			$.post("/hermit/HouseTypeServlet.do",{action:"getAllType"},function(data){
				dataJson=$.parseJSON(data).list;
// 				console.log(data);
				$.each(dataJson,function(index,VO){
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
//	 				console.log(data);
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
			$.post("/hermit/BoroughsServlet.do",{action:"getAllborough",cityNO:cityNO},function(data){
				var cityNO = ($("#cityName").val());
					dataJson=$.parseJSON(data).list;
//	 				console.log(data);
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
		})
	</script>
</body>
</html>