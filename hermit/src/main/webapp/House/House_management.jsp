<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../css/datatables.min.css"/>
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/flashcanvas.js"></script>
<script src="../js/jSignature.min.js"></script>
<script src="../js/datatables.min.js"></script>
<style>

</style>
</head>
<body>
<table id="myTable">
		<thead>
			<tr>
				<th>房屋編號</th>
				<th>房屋標題</th>
				<th>縣市</th>
				<th>地區</th>
				<th>最高樓層</th>
				<th>房屋樓層</th>
				<th>房屋狀態</th>
				<th>租金</th>
				<th>押金</th>
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
				<th>押金</th>
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

<form action="<%=request.getContextPath()%>/House.do" method="POST">
<table class="insertTable">
	<thead>
	<tr>
			<th>房屋標題</th>
				<th>縣市</th>
				<th>地區</th>
				<th>最高樓層</th>
				<th>房屋樓層</th>
				<th>房屋狀態</th>
				<th>租金</th>
				<th>押金</th>
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
		<tr>
				<td>
				<input type="text" style="width: 75px" value="${param.houseTitle}" name="houseTitle">
				</td>
				
				<td>
				<select name="cityNO" id="cityNO">
				<option>請選擇</option>
				</select>
				</td>
				
				<td>
				<select name="boroughNO" id="boroughNO">
				
				</select>				
				</td>
				<td>
				<input type="text" style="width: 75px" value="${param.hightestFloor}" name="highestFloor">
				</td>
				<td>
				<input type="text" style="width: 75px" value="${param.nowFloor}" name="nowFloor">
				</td>
				<td>
				<select name="houseStatus" id="SelectStatus">
				<option>未出租</option>
				<option>已出租</option>
				<option>修繕中</option>
				</select> 
				</td>
				<td>
				<input type="text" style="width: 75px" value="${param.houseRent}" name="houseRent">
				</td>
				<td>
				<input type="text" style="width: 75px" value="${param.houseCharge}" name="houseCharge">
				</td>
				<td>
				<input type="text" style="width: 75px" value="${param.waterRate}" name="waterRate">
				</td>
				<td>
				<input type="text" style="width: 75px" value="${param.powerRate}" name="powerRate">
				</td>
				<td>
				<input type="text" style="width: 75px" value="${param.houseVideo}" name="houseVideo">
				</td>
				<td>
				<select id="houseType" name="typeNO"></select>
				</td>
				<td>
				<select id="houseForm" name="formNO"></select>
				</td>
				<td>
				<input type="text" style="width: 75px" value="${param.houseAddr}" name="houseAddr">
				</td>
				<td>
				<input type="text" style="width: 75px" value="${param.houseSize}" name="houseSize">
				</td>
				<td>
				<button id="addHouse">新增</button>
				</td>
				
		</tr>
		<input type="hidden" name=action>
	</tbody>
</table>
</form>
		
		
	<form action="/hermit/House.do" method="get" id="modify">
	<input type="hidden" name="action" value="getOneHouse_FK">
	<input type="hidden" id="houseNO" name="houseNO">
	</form>
	
	
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
			console.log(dataJson);
			tbody.empty();
			$.each(dataJson,function(index,VO){
				var cell1=$("<td></td>").text(VO.houseNO);
				var cell2=$("<td></td>").text(VO.houseTitle)
				var cell3=$("<td></td>").text(VO.cityName)
				var cell4=$("<td></td>").text(VO.boroughName);
				var cell5=$("<td></td>").text(VO.highestFloor);
				var cell6=$("<td></td>").text(VO.nowFloor);
				var cell7=$("<td></td>").text(VO.houseStatus);
				var cell8=$("<td></td>").text(VO.houseRent);
				var cell9=$("<td></td>").text(VO.houseCharge);
				var cell10=$("<td></td>").text(VO.waterRate);
				var cell11=$("<td></td>").text(VO.powerRate)
				var cell12=$("<td></td>").text(VO.houseVideo);
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
		})
		$.post("/hermit/CityServlet.do",{action:"getAllCity"},function(data){
				dataJson=$.parseJSON(data).list;
// 				console.log(data);
				$.each(dataJson,function(index,VO){
					var cell1=$("<option></option>").text(VO.cityName);
					cell1.val(VO.cityNO);
					console.log(VO.cityName);
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
			
			$("#addHouse").click(function(){
				$('input[name="action"]').val("insertHouse");
			})
		
})
</script>

</body>
</html>