<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<!-- 房屋表格用↓ -->
<link rel="stylesheet" 
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 房屋表格用↑ class="table"	 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/iEdit.min.css">
<link
	href='<%=request.getContextPath()%>/css/jqueryText/jquery-te-1.4.0.css'
	rel='stylesheet' />
<style>
.HousePictureDiv{
padding:50px;
}
.insertHouseDiv{
padding:30px;
}
textarea{
    padding: 20px; 
    width: 280px;
    resize: none;
    overflow: auto;
}
#quickinsert{
font-size: 50px;

color: 	#F9F900;
}
#houseeq{
font-size:18px;
}
.error{
	color: red;
}
</style>-
</head>
<body>
	<!-- 載入框架 -->
	<jsp:include page="/fragment/back_side_page.jsp" />
	<!-- bootstrap -->
	
<div class="container col-md-10 col-md-offset-1">
	<table id="myTable" class="table">
		<thead>
			<tr>
				<th>編號</th>
				<th>標題</th>
				<th>縣市</th>
				<th>地區</th>
				<th>最高樓層</th>
				<th>房屋樓層</th>
				<th>狀態</th>
				<th>租金</th>
				<th>管理費</th>
				<th>水費</th>
				<th>電費</th>
				<th>影片</th>
				<th>類型</th>
				<th>形態</th>
				<th>地址</th>
				<th>坪數</th>
				<th>編輯</th>
			</tr>
		</thead>
			<tbody>
			</tbody>
				<tfoot>
					<tr>
						<th>房屋編號</th>
						<th>房屋標題</th>
						<th>縣市</th>
						<th>地區</th>
						<th>最高樓層</th>
						<th>房屋樓層</th>
						<th>房屋狀態</th>
						<th>租金</th>
						<th>管理費</th>
						<th>水費</th>
						<th>電費</th>
						<th>影片</th>
						<th>類型</th>
						<th>形態</th>
						<th>地址</th>
						<th>坪數</th>
						<th>編輯</th>
					</tr>
				</tfoot>
	</table>
</div>
<div class="container">
<label class="control-label" id="quickinsert">快速新增</label>
</div>
<div class="container col-md-10 col-md-offset-2">
<form action="<%=request.getContextPath()%>/House.do" method="POST" id="form" enctype="multipart/form-data">
	<div class="insertHouseDiv">
		<table class="insertTable" class="table">
			<thead>
				<tr>
					<th scope="col">房屋標題</th>
					<th scope="col">縣市</th>
					<th scope="col">地區</th>
					<th scope="col">最高樓層</th>
					<th scope="col">房屋樓層</th>
					<th scope="col">房屋狀態</th>
					<th scope="col">租金</th>
					<th scope="col">管理費</th>
					<th scope="col">水費</th>
					<th scope="col">電費</th>
					
					<th scope="col">類型</th>
					<th scope="col">形態</th>
					<th scope="col">地址</th>
					<th scope="col">坪數</th>
					<th scope="col">編輯</th>
				</tr>
			</thead>
				<tbody>
					<tr>
						<td><input type="text" style="width: 75px" value="${param.houseTitle}" name="houseTitle" class="form-control"></td>
						<td><select name="cityNO" id="cityNO" class="form-control"><option>請選擇</option></select></td>
						<td><select name="boroughNO" id="boroughNO" class="form-control"></select></td>
						<td><input type="text" style="width: 75px" value="${param.highestFloor}" name="highestFloor" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57' maxlength="2"></td>
						<td><input type="text" style="width: 75px" value="${param.nowFloor}" name="nowFloor" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57' maxlength="2"></td>
						<td><select name="houseStatus" id="SelectStatus" class="form-control">
								<option>未出租</option>
								<option>已出租</option>
								<option>修繕中</option>
							</select> 
						</td>
						<td><input type="text" style="width: 75px" value="${param.houseRent}" name="houseRent" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57'></td>
						<td><input type="text" style="width: 75px" value="${param.houseCharge}" name="houseCharge" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57'></td>
						<td><input type="text" style="width: 75px" value="${param.waterRate}" name="waterRate" class="form-control"></td>
						<td><input type="text" style="width: 75px" value="${param.powerRate}" name="powerRate" class="form-control"></td>
						<td><select id="houseType" name="typeNO" class="form-control"></select></td>
						<td><select id="houseForm" name="formNO" class="form-control"></select></td>
						<td><input type="text" style="width: 75px" value="${param.houseAddr}" name="houseAddr" class="form-control"></td>
						<td><input type="number" style="width: 75px" value="${param.houseSize}" name="houseSize" class="form-control" step="0.01"></td>
						<td><button id="addHouse" class="btn">新增</button></td>
					</tr>
				<tr>
					<td>
						<div class="error">
							<small>${ErrorMsgKey.title}</small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small>${ErrorMsgKey.city}</small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small>${ErrorMsgKey.borough}</small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small>${ErrorMsgKey.highestFloor}</small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small>${ErrorMsgKey.nowFloor}</small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small></small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small>${ErrorMsgKey.houseRent}</small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small>${ErrorMsgKey.houseCharge}</small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small>${ErrorMsgKey.waterRate}</small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small>${ErrorMsgKey.powerRate}</small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small></small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small></small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small>${ErrorMsgKey.houseAddr}</small>	
						</div>
					</td>
					<td>
						<div class="error">
							<small>${ErrorMsgKey.houseSize}</small>	
						</div>
					</td>
					
				</tr>
						<input type="hidden" name=action>
				</tbody>
		</table>
	</div>

	<div class="container col-md-10" id="houseeq">
		<input type="checkbox" name="TV" id="TV">
		<label for="TV">電視</label>
	
		<input type="checkbox" name="aircondition" id="aircondition">
		<label for="aircondition">冷氣</label>
	
		<input type="checkbox" name="refrigerator" id="refrigerator">
		<label for="refrigerator">冰箱</label>
	
		<input type="checkbox" name="waterHeater" id="waterHeater">
		<label for="waterHeater">熱水器</label>
	
		<input type="checkbox" name="gas" id="gas">
		<label for="gas">瓦斯</label>
	
		<input type="checkbox" name="theFourthStation" id="theFourthStation">
		<label for="theFourthStation">第四台</label>
	
		<input type="checkbox" name="net" id="net">
		<label for="net">網路</label>
	
		<input type="checkbox" name="washing" id="washing">
		<label for="washing">洗衣機</label>
	
		<input type="checkbox" name="bed" id="bed">
		<label for="bed">床</label>
	
		<input type="checkbox" name="wardrobe" id="wardrobe">
		<label for="wardrobe">衣櫃</label>
	
		<input type="checkbox" name="sofa" id="sofa">
		<label for="sofa">沙發</label>

		<input type="checkbox" name="parking" id="parking">
		<label for="parking">停車位</label>
	
		<input type="checkbox" name="elevator" id="elevator">
		<label for="elevator">電梯</label>
	
		<input type="checkbox" name="balcony" id="balcony">
		<label for="balcony">陽台</label>
	
		<input type="checkbox" name="permitCook" id="permitCook">
		<label for="permitCook">開伙</label>

		<input type="checkbox" name="pet" id="pet">
		<label for="pet">養寵物</label>
	
		<input type="checkbox" name="closeMRT" id="closeMRT">
		<label for="closeMRT">近捷運</label>
	</div>
	<div>
		<table class="table">
			<tr>
				<td>
					<div class="form-group col-md-10">
						<label>房屋介紹</label>
						<textarea name="houseInfo" class="form-control" id="houseInfo"></textarea>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<input type="file" id="file">
					<input type="hidden" id="previewPic" name="previewPic"/>
				</td>
				
				<td>
					<input type="file" name="imgFile" accept="image/png,image/gif,image/jpeg" multiple="multiple" >	
				</td>
			</tr>
			<tr>
				<td>
					<img id="result" border="0" style="border:none;max-height:200px;max-width:200px;">
				</td>
			</tr>
		</table>
	</div>
</form>	

	<form action="/hermit/House.do" method="get" id="modify">
	<input type="hidden" name="action" value="getOneHouse_FK">
	<input type="hidden" id="houseNO" name="houseNO">
	</form>
	
</div>	
<%-- <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script> --%>
<!-- <script src="../js/bootstrap.js"></script> -->
<script src="<%=request.getContextPath()%>/js/flashcanvas.js"></script>
<script src="<%=request.getContextPath()%>/js/jSignature.min.js"></script>
<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
<script src='<%=request.getContextPath()%>/js/jquery-te-1.4.0.min.js'></script>
<script>
$(document).ready(function(){
	
	
	var dataJson;
	
	var table=$("#myTable");
	var tbody=$("#myTable>tbody");
	
	var selectCity=$("#cityNO")
	var cityNO=$("#cityNO").val();
	
	var selectBorough=$("#boroughNO");
	var boroughNO=$("#boroughNO").val();
	
	var selectForm= $("#houseForm");
	var formNO = $("#formNO").val();
	
	var selectType=$("#houseType");
	var typeNO=$("#typeNO").val();
	
		$.post("/hermit/House.do",{action:"getAllHouseForJson"},function(data){
			dataJson=$.parseJSON(data).list;
// 			console.log(data);
// 			console.log(dataJson);
			tbody.empty();
			$.each(dataJson,function(index,VO){
				var cell1=$("<td></td>").text(VO.houseNO);
				var cell2=$("<td></td>").text(VO.houseTitle)
				var cell3=$("<td></td>").text(VO.cityName)
				var cell4=$("<td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                </td>").text(VO.boroughName);
				var cell5=$("<td></td>").text(VO.highestFloor);
				var cell6=$("<td></td>").text(VO.nowFloor);
				var cell7=$("<td></td>").text(VO.houseStatus);
				var cell8=$("<td></td>").text(VO.houseRent);
				var cell9=$("<td></td>").text(VO.houseCharge);
				var cell10=$("<td></td>").text(VO.waterRate);
				var cell11=$("<td></td>").text(VO.powerRate);
				var cell12;
				if(VO.houseVideo != null){
					cell12=$("<td></td>").html("<a href='https://www.youtube.com/watch?v="+VO.houseVideo+"'>"+VO.houseNO+"</a>");
				}else{
					cell12=$("<td></td>").text("無影片");
				};
				var cell13=$("<td></td>").text(VO.hType);
				var cell14=$("<td></td>").text(VO.hForm);
				var cell15=$("<td></td>").text(VO.houseAddr);
				var cell16=$("<td></td>").text(VO.houseSize);
				var cell17=$("<td></td>").html('<button class="btn btn-primary">修改</button>');
				var row=$("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5
					,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13,
					cell14,cell15,cell16,cell17]);
				tbody.append(row);
			})
			$(".btn-primary").on("click",function(){
				var tr=$(this).parents("tr");
				var houseNO=$(this).parents("tr").children("td:eq(0)").text();
				$("#houseNO").val(houseNO);
				$("#modify").submit();
			})
			
			$('#myTable').DataTable( {
		        "language": {
		            "lengthMenu": "每頁顯示 _MENU_ 筆",
		            "zeroRecords": "Nothing found - sorry",
		            "info": "現在正顯示   _PAGE_  共有 _PAGES_ 頁",
		            "infoEmpty": "No records available",
		            "infoFiltered": "(filtered from _MAX_ total records)",
		            "search": "查詢:",
		            "paginate": {
		        		"first":      "首頁",
		        		"last":       "末頁",
		        		"next":       "下頁",
		        		"previous":   "前頁"
		        	}
		        }
			
			});
			
		})
		$.post("/hermit/CityServlet.do",{action:"getAllCity"},function(data){
				dataJson=$.parseJSON(data).list;
// 				console.log(data);
				$.each(dataJson,function(index,VO){
					var cell1=$("<option></option>").text(VO.cityName);
					cell1.val(VO.cityNO);
// 					console.log(VO.cityName);
					selectCity.append(cell1);
				})
			})
			$("#cityNO").change(function(){
				
				var cityNO = ($("#cityNO").val());
				$("#boroughNO").html("");
				$.post("/hermit/BoroughsServlet.do",{action:"getAllBoroughByCity",cityNO:cityNO},function(data){
					
					dataJson=$.parseJSON(data).list;
//	 				console.log(data);
					selectBorough.append($("<option>請選擇</option>"));
					$.each(dataJson,function(index,VO){
						
						var cell1=$("<option></option>").text(VO.boroughName);
						cell1.val(VO.boroughNO);
						selectBorough.append(cell1);
					})
				})
			})
			$.post("/hermit/HouseTypeServlet.do",{action:"getAllType"},function(data){
				dataJson=$.parseJSON(data).list;
// 				console.log(data);
				$.each(dataJson,function(index,VO){
					var cell2=$("<option></option>").text(VO.hType);
					cell2.val(VO.typeNO);
// 					console.log(VO.typeNO);
					selectType.append(cell2);
				})
				
			})
			$.post("/hermit/HouseFormServlet.do", {action : "getAllForm"}, function(data) {
				dataJson = $.parseJSON(data).list;
// 				console.log(data);
// 				console.log(dataJson);
				$.each(dataJson,function(index,VO){
					var cell1= $("<option></option>").text(VO.hForm);
					cell1.val(VO.formNO);
					selectForm.append(cell1);
				})
			})
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
		
			$("#addHouse").click(function(){
				$('input[name="action"]').val("insertHouse");
			})
			$('#houseInfo').jqte();
		
})
</script>

</body>
</html>