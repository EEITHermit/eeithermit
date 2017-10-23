<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Advanced Search</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/flashcanvas.js"></script>
<script src="../js/jSignature.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<div class="form-group">
				<label for="exampleFormControlSelect1">Example select</label>
				<select class="form-control" id="exampleFormControlSelect1">
					<option>請選擇</option>
					<option value="1">台北市</option>
					<option value="2">新北市</option>
					<option value="3">基隆市</option>
					<option value="4">桃園市</option>
					<option value="5">新竹市</option>
					<option value="6">新竹縣</option>
					<option value="7">苗栗縣</option>
					<option value="8">台中市</option>
				</select>
			</div>
			<div class="form-check form-check-inline">
			</div>
			<button id="getCheck">GET</button>
		</div>
	</div>
	<script>
	$(function(){
		var divOfCheckBox = $(".form-check-inline");
		var boroughs = {"data":[
						{"boroughNO": 1 , "boroughName":"中正區"},
						{"boroughNO": 2 , "boroughName":"中山區"},
						{"boroughNO": 3 , "boroughName":"大安區"}]
						};
		var boroughJSON = $.parseJSON(JSON.stringify(boroughs.data));
		$.each(boroughJSON,function(key,value){
			var checkBox1 = $("<input class='form-check-input' type='checkbox'>").val(value.boroughNO).attr("name",value.boroughName);
			var span1 = $("<span></span>").text(value.boroughName);
			var label1 = $("<label class='form-check-label'></label>").append(checkBox1,span1);
			var div1 = $('<div class="form-check" style="display:inline;margin-left:10px"></div>').append(label1);
			divOfCheckBox.append(div1);
		})
		
		$("#getCheck").on("click",function(){
			var checkBox = $(".form-check-inline input");
			console.log(checkBox);
			$.each(checkBox,function(index,value){
				console.log("index = " + index);
				console.log("value = " + value.value);
				console.log("name = " + value.name);
				console.log("checked = " + value.checked);
			})
		})
	})
	</script>
</body>
</html>