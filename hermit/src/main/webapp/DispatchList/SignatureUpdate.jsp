<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/flashcanvas.js"></script>
<script src="<%=request.getContextPath()%>/js/jSignature.min.js"></script>
<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
</head>
<body>
<div class="container">
	<form id="ListForm" method="post" action="DispatchListServlet">
		<div class="form-group">
			<label for="dempno">派工單號</label>
			<input type="text" class="form-control" name="dlno" value=${dlVO.dlNO}>
		</div>
		<div class="form-group">
			<label for="dempno">指派人</label>
			<input type="text" class="form-control" name="dempno" value=${dlVO.dempNO}>
		</div>
		<div class="form-group">
		 	<label for="aempno">負責人</label>
		 	<input type="text" class="form-control" name="aempno" value=${dlVO.aempNO}>
		</div>
		<div class="form-group">
		 	<label for="qano">問答單號</label>
		 	<input type="text" class="form-control" name="qano" value=${dlVO.qaNO}>
		</div>
		<div class="form-group">
		 	<label for="stime">派工時間</label>
		 	<input type="text" class="form-control" name="dlstime" value=${dlVO.dlStime}>
		</div>
		<div class="form-group">
		 	<label for="etime">結束時間</label>
		 	<input type="text" class="form-control" name="dletime" value=${dlVO.dlEtime}>
		</div>
		<div class="form-group">
		 	<label for="elesign">簽名</label>
		 	<img id="elesignImg" src=${dlVO.elesign}>
		</div>
		<p>簽名板</p>
		<div class="form-group" style="border: 1px solid gray;">
			<div id="signature"></div>
			<input type="hidden" id="base64" name="elesign">
			<input type="hidden" class="form-control" name="action" value="updateDispatchList">
		</div>
		<div class="form-group">
		 	<label for="qano">備註</label>
		 	<textarea rows="4" cols="50" name="dlnote">${dlVO.dlNote}</textarea>
		</div>
			<button type="button" class="btn btn-default" id="clear">清除簽名</button>
			<button type="submit" class="btn btn-default" id="submit">送出</button>
	</form>
	<script>
	$(function(){
		var jSig = $("#signature");
		var base64 = $("#base64");
		var eleImg = $("#elesignImg");
    	jSig.jSignature({'height':'100%','width':'100%','decor-color': 'transparent'});
    	
 	 
 
		$("#submit").on("click",function(){
			base64.val(jSig.jSignature("getData"));
			
		})
		$("#clear").on("click",function(){
			jSig.jSignature("clear");
		})
	})
	</script>
</div>
	
</body>
</html>