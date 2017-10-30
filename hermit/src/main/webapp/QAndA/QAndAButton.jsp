<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員對房屋提問按鈕</title>
<link href='<%=request.getContextPath() %>/css/jquery-ui.min.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/css/bootstrap.min.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/css/jquery-ui.structure.min.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/css/jquery-ui.theme.min.css' rel='stylesheet' />
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>
<script src='<%=request.getContextPath() %>/js/jquery-ui.min.js'></script>
<script>
	$(document).ready(work)
	
	function work(){
		var dialog = $("#dialog");
		dialog.dialog({
			 autoOpen: false,
			 height: 330,
		     width: 350,
		     modal: true,
		     resizable:false,
		     buttons:{
		    	 "送出":function(){
		    		 //memberNO houseNO為假資料
		    		 $.post("<%=request.getContextPath()%>/QAndAServlet?mission=question"
		    				 ,{question:$("#question").val(),member:40001,house:20001}
		    		 ,function(data){
		    			 alert(data);
		    			 $("#question").val("");
		    			 dialog.dialog("close");	 
		    		 })
		    	 },
		    	 "取消":function(){
		    		 dialog.dialog("close");
		    	 }
		     }
		})
		
		$("#questionBT").click(function(){
			dialog.dialog("open");
			
		})
	}
</script>
</head>
<body>
<button id="questionBT" >我要發問</button>
	
<div id="dialog" title="提問方塊">
	<form class="form-group">
	<label for="question">請輸入提問內容：</label>
	<textarea name="question"class="form-control"id="question" style="resize:none;height:150px"></textarea>
	</form>
</div>
</body>
</html>