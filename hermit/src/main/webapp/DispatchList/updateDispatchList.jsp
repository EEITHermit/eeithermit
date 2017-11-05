<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>派工單</title>
<jsp:include page="/fragment/back_side_page.jsp" />
<script src="<%=request.getContextPath()%>/js/jSignature.min.js"></script>
<Style>
	.jSignature{
		background-color: #ffffff
	}
</Style>
</head>
<body>
<div class="container" style="background-color: #ADADAD">
<a href="<%= request.getContextPath()%>/DispatchList/DispatchList.jsp"><span  style="margin-bottom: 50px" class="btn btn-primary">回到派工清單</span></a>
	<form id="ListForm" method="post" action="<%= request.getContextPath() %>/Dispatch">
		<div class="form-group">
			<label for="dempno">派工單號</label>
			<input type="text" class="form-control" name="dlNO" value=${dlVO.dlNO}>
		</div>
		<div class="form-group">
			<label for="dempno">指派人</label>
			<input type="hidden" class="form-control"readonly="readonly" name="dempNO" value=${dlVO.dempVO.empNO}>
			<input type="text" class="form-control"readonly="readonly" name="dempName" value=${dlVO.dempVO.empName}>
		</div>
		<div class="form-group">
		 	<label for="aempno">負責人</label>
		 	<input type="hidden" class="form-control"  readonly="readonly" name="aempNO" value=${dlVO.aempVO.empNO}>
		 	<input type="text" class="form-control"  readonly="readonly" name="aempName" value=${dlVO.aempVO.empName}>
		</div>
		<div class="form-group">
		 	<label for="qano">問答單號</label>
		 	<input type="text" class="form-control"  readonly="readonly"  name="qaNO" value=${dlVO.qaVO.qaNO}>
		</div>
		<div class="form-group">
		 	<label for="qano">問題內容</label>
		 	<textarea class="form-control" rows="8" cols="75" wrap="hard" id="dlNote" style="border-radius: 5px; resize: none" readonly="readonly">${dlVO.qaVO.qDetail}</textarea>
		</div>
		<div class="form-group">
		 	<label for="stime">派工時間</label>
		 	<input type="text" class="form-control"  readonly="readonly"  name="dlStime" value=${dlVO.dlStime}>
		</div>
 		<label for="elesign">簽名</label><br>
		<div class="form-group" style="background-color: white;height:250px;width:800px">
		 	<img id="elesignImg" src=${dlVO.elesign}>
		</div>
		<div class="form-group">
		<label for="signature">簽名板</label>
			<div id="signature"></div>
			<input type="hidden" id="base64" name="elesign">
			<input type="hidden" class="form-control" name="action" value="endDispatchList">
		</div>
		<div class="form-group">
		 	<label for="qano">備註</label><br>
		 	<textarea rows="8" cols="75" wrap="hard" id="dlNote" style="border-radius: 5px;resize: none" name="dlnote">${dlVO.dlNote}</textarea>
		</div>
			<button type="button" class="btn btn-default" id="clear">清除簽名</button>
			<button type="submit" class="btn btn-default" id="submit">送出</button>
	</form>
	<script>
	$(function(){
		var jSig = $("#signature");
		var base64 = $("#base64");
		var eleImg = $("#elesignImg");
    	jSig.jSignature({'height':250,'width':800,'decor-color': 'transparent'});
    	
 	 
 
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