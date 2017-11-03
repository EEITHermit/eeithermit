<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/fragment/back_side_page.jsp" />
<title>新增派工單</title>
</head>
<body>
	<div class="container">
		<h3 style="text-align: center">新增維護派工</h3>
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<form>
				<div class="form-group">
					<label for="exampleInputEmail1">指派人</label>
					<input type="hidden" class="form-control" id="dempNO" disabled="disabled" readonly="readonly" placeholder="指派人" value="${empLoginOK.empNO}">
					<input type="text" class="form-control" id="dempName" disabled="disabled" readonly="readonly" placeholder="指派人" value="${empLoginOK.empName}">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">受派人</label>
					<input type="text" class="form-control" id="aemp" placeholder="請輸入受派人" value="">
				</div>
				<div class="form-group">
				 	<label for="qano">問答單號</label>
				 	<input type="text" class="form-control" id="qano" disabled="disabled" value="${param.qaNO}">
				</div>
				<div class="form-group">
				 	<label for="qano">問答內容</label>
				 	<br>
				 	<textarea rows="8" cols="75" readonly="readonly" disabled="disabled"  wrap="hard" id="QnADetail" style="border-radius: 5px;resize: none"></textarea>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">房屋物件</label>
					<input type="text" class="form-control" id="houseName" placeholder="房屋物件" value="">
				</div>
			</form>
		</div>
		<div class="col-md-3"></div>
	</div>
	<script>
		$(function(){
			var qaNO = "<%= request.getParameter("qaNO")%>"
			console.log(qaNO);
			$.post("<%=request.getContextPath()%>/QAndAServlet",{"mission":"getOneQA","qaNO":qaNO},function(data){
				$("#QnADetail").text($.parseJSON(data).qDetail)
			})
		})
	</script>
</body>
</html>