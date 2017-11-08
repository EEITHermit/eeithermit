<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>派工單</title>
<jsp:include page="/fragment/back_side_page.jsp" />
<script src="<%=request.getContextPath()%>/js/jSignature.min.js"></script>
<script src="<%=request.getContextPath()%>/js/ajsr-jq-confirm.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tmplt-default.css">
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
			<input type="text" class="form-control" readonly="readonly" name="dlNO" value=${dlVO.dlNO}>
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
		<div class="form-group">
		<label for="signature">簽名板</label>
			<div id="signature"></div>
			<input type="hidden" id="base64" name="elesign" value="${dlVO.elesign}">
			<input type="hidden" class="form-control" name="action" value="endDispatchList">
		</div>
		<div class="form-group">
		 	<label for="qano">備註</label><br>
		 	<textarea rows="8" cols="75" wrap="hard" id="dlNote" maxlength="199" style="border-radius: 5px;resize: none" name="dlnote">${dlVO.dlNote}</textarea>
		</div>
			<button type="button" class="btn btn-default" id="clear">清除簽名</button>
			<button type="button" class="btn btn-default" id="submitBtn">送出</button>
	</form>
	<script>
	$(function(){
		var jSig = $("#signature");
		var base64 = $("#base64");
		jSig.jSignature({'height':250,'width':800,'decor-color': 'transparent'});
		if(base64.val() != ""){
			jSig.jSignature("setData",base64.val());
		}
		$("#clear").on("click",function(){
			jSig.jSignature("clear");
		})
		
		$("#submitBtn").on("click",function(){
			$.ajsrConfirm({
				  title: '修改派工單？',
				  message: '您是否要保存修改後的派工單？',
				  okButton: '是',
				  cancelButton: '否',
				  onConfirm: function(){ 
					  base64.val(jSig.jSignature("getData"));
					  $("#ListForm").submit();
				  }, onCancel: function(){ 
					  window.location ="<%= request.getContextPath()%>/DispatchList/DispatchList.jsp"
				  },
				  showCancel: true,
				  css:"tmplt-default",
				  nineCorners: false
				});
		})
	})
	</script>
</div>
	
</body>
</html>