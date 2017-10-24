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
		<tbody></tbody>
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
	<form action="/hermit/House.do" method="get" id="modify">
	<input type="hidden" name="action" value="getOneHouse_FK">
	<input type="hidden" id="houseNO" name="houseNO">
	</form>
<script>
$(document).ready(function(){
	var dataJson;
	
	var table=$("#myTable");
	var tbody=$("#myTable>tbody");
	
		$.post("/hermit/House.do",{action:"getAllHouseForJson"},function(data){
			dataJson=$.parseJSON(data).list;
			console.log(data);
			console.log(dataJson);
			tbody.empty();
			$.each(dataJson,function(index,VO){
				var cell1=$("<td></td>").text(VO.houseNO);
				var cell2=$("<td></td>").text(VO.houseTitle)
				var cell3=$("<td></td>").text(VO.cityNO)
				var cell4=$("<td></td>").text(VO.boroughNO);
				var cell5=$("<td></td>").text(VO.highestFloor);
				var cell6=$("<td></td>").text(VO.nowfloor);
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
	
	
})
</script>

</body>
</html>