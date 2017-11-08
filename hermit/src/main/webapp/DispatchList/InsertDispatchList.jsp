<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/fragment/back_side_page.jsp" />
<title>新增派工單</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/w3.css" />
<style>

</style>
</head>
<body>
	<div class="container">
		<a href="<%= request.getContextPath()%>/DispatchList/DispatchList.jsp"><span class="btn btn-primary">回到派工清單</span></a>
		<h1 style="text-align: center;font-size:Microsoft JhengHei;font-weight: bold;margin-bottom:50px">新增維護派工</h1>
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<form action="<%= request.getContextPath()%>/Dispatch" method="get">
				<div class="form-group">
					<label for="exampleInputEmail1">指派人</label>
					<input type="hidden" class="form-control" id="dempNO" readonly="readonly" name="dempNO" readonly="readonly" placeholder="指派人" value="${empLoginOK.empNO}">
					<input type="text" class="form-control" readonly="readonly" disabled="disabled"  placeholder="指派人" value="${empLoginOK.empName}">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">受派人</label>
					<select class="form-control" id="aemp" name="aempNO" required="required">
					</select>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">房屋代號</label>
					<input type="text" class="form-control"  disabled="disabled" readonly="readonly" id="houseNO"  name="houseNO"  placeholder="房屋代號" value="${param.houseNO}">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">房屋地址</label>
					<input type="text" class="form-control"  disabled="disabled"  readonly="readonly"  id="houseAddr"  readonly="readonly" placeholder="房屋地址" >
				</div>
				<div class="form-group">
				 	<label for="qano">問答單號</label>
				 	<input type="text" class="form-control" readonly="readonly"  id="qano" name="qaNO" readonly="readonly" value="${param.qaNO}">
				</div>
				<div class="form-group">
				 	<label for="qano">問題內容</label>
				 	<br>
				 	<textarea rows="8" cols="75"  readonly="readonly" disabled="disabled"  readonly="readonly"  wrap="hard" id="QnADetail" style="border-radius: 5px;resize: none"></textarea>
				</div>
				<div class="form-group">
				 	<label for="dlNote">備註</label>
				 	<br>
				 	<textarea rows="8" cols="75" wrap="hard" id="dlNote" maxlength="199" style="border-radius: 5px;resize: none" name="dlNote"></textarea>
				</div>
				<input type="hidden" name="action" value="InsertDispatchList">
				<button class="btn btn-primary" style="margin:auto" type="sumbit">送出</button>&nbsp;&nbsp;
			</form>
		</div>
		<div class="col-md-3"></div>
	</div>
	<script>
		$(function(){
			var qaNO = "<%= request.getParameter("qaNO")%>";
			var houseNO = "<%= request.getParameter("houseNO")%>";
			var aemp = $("#aemp");
			function getQA(){
				if($("#qano").val() != null){
					$.post("<%=request.getContextPath()%>/QAndAServlet",{"mission":"getOneQA","qaNO":qaNO},function(data){
						$("#QnADetail").text($.parseJSON(data).qDetail);
					})
				}
				$.post("<%=request.getContextPath()%>/House.do",{"action":"getOneHouseToJSON","houseNO":houseNO},function(data){
					var houseData = $.parseJSON(data);
					$("#houseAddr").val(houseData.houseAddress)
					$.post("<%=request.getContextPath()%>/emp/EmpServlet",{"action":"findByFixPostAndBorough","post":340,"borough":houseData.borough},function(data){
						console.log(data);
						var fixEmp = $.parseJSON(data).fixEmp;
						aemp.empty();
						$.each(fixEmp,function(index,emp){
							var opt = $("<option></option>").val(emp.empNO);
							opt.text(emp.empName);
							
							aemp.append(opt)
						})
					})
				})
			}
			getQA();
			$("#houseNO").on("change",function(){
				houseNO = $(this).val();
				getQA();
			});
		})
	</script>
</body>
</html>