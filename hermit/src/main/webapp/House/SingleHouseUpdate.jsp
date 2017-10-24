<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="/hermit/House.do?action=updateHouse">
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
			<input type="text" value="${vo.cityNO}" name="cityNO"> 
		</div>
		<div>
		<label>地區</label>
			<input type="text" value="${vo.boroughNO}" name="boroughNO"> 
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
			<input type="text" value="${vo.houseStatus}" name="houseStatus"> 
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
		<select name="">
		<option value=""></option>
		</select>
		</div>
		<div>
		<label>房屋形態</label>
		<select name="">
		<option value=""></option>
		</select>
		</div>
		<div>
		<label>地址</label>
		<input type="text" value="${vo.houseAddr}" name="houseAddr">
		</div>
		<div>
		<lable>坪數</lable>
		<input type="text" value="${vo.houseSize }" name="houseSize">
		</div>
		<input type="submit" value="修改">
		
	</form>
</body>
</html>