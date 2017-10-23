<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hermit廣告後台</title>

<script src='<%=request.getContextPath() %>/js/jquery-3.2.1.min.js'></script>
<script src='<%=request.getContextPath() %>/js/script.js'></script>
<link href='<%=request.getContextPath() %>/css/bootstrap.min.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/css/iEdit.min.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/css/bootstrap-theme.min.css' rel='stylesheet' />
<script src='<%=request.getContextPath() %>/js/bootstrap.js'></script>
<script src='<%=request.getContextPath() %>/js/flashcanvas.js'></script>
<script src='<%=request.getContextPath() %>/js/iEdit.js'></script>


</head>
<body>
<div style="margin-right:auto;">
<h1>後台廣告管理系統</h1><br><br><br>
	<div class="container">
		<div class="imgs">
			<form method="get" action="ADManagerServlet">
			<h6>廣告圖片</h6>
				<label class="custom-file">
				<input id="file" type="file" class="custom-file-input">
				<span class="custom-file-control"></span>
				<img id="result" src="" height="360" width="1000" >
				</label><p/><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<h6>圖片連結網址</h6>			
				<input type="text" class="form-control" size="60" placeholder="請輸入範例格式:http://www.example.com.tw" name="adno" ><p/><br>
			<h6>請輸入廣告訊息(限20字內)</h6>
			<textarea name="message" rows="6" cols="40"></textarea><p/><br>
			<h6>廣告上架時間</h6>
			<label for="bookdate">日期：</label>
			<input type="date" name="AdTimeStart" id="date1" size="60" placeholder="2014-09-18"><p/><br>
			<h6>廣告下架時間</h6>
			<label for="bookdate">日期：</label>
			<input type="date" name="AdTimeEnd" id="date2" size="60" placeholder="2015-09-18"><p/><br>
			<h6>廣告圖片狀態修改</h6>
  					<input type="radio" name="true" value="0" checked>上架<br>
  					<input type="radio" name="true" value="1">下架<br>
     		  	修改人:<input type='text' name='admodify' size="60" placeholder="請輸入員工代號  ex:00000"><P/><br>
      		${Msg}
      		 <button type="submit" class="btn btn-primary btn-lg" id="submit">確認</button>
			 <button type="reset" class="btn btn-secondary btn-lg">清除</button>
      		 </form>
		</div>
	</div>
</div>
<!-- <script> -->
// 	function convertToISO(timebit) {
// 	  // remove GMT offset
// 	  timebit.setHours(0, -timebit.getTimezoneOffset(), 0, 0);
// 	  // format convert and take first 10 characters of result
// 	  var isodate = timebit.toISOString().slice(0,10);
// 	  return isodate;
// 	}
// 	var bookdate = document.getElementById('bookdate');
// 	var currentdate = new Date();
// 	bookdate.min = convertToISO(currentdate);
// 	bookdate.placeholder = bookdate.min;
// 	var futuredate = new Date();
// 	// go forward 7 days into the future
// 	futuredate.setDate(futuredate.getDate() + 7);
// 	bookdate.max = convertToISO(futuredate);
<!-- </script> -->

<script type="text/javascript"> 
	$(function(){
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
	 			  $("#memImage").val($("#result").attr("src"));
	 		  }) 
	 		});
	})
	</script> 
</body>
</html>