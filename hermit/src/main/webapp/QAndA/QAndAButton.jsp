<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員對房屋提問按鈕</title>
<link href='<%=request.getContextPath() %>/css/jquery-ui.min.css' rel='stylesheet' />
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>
<script src='<%=request.getContextPath() %>/js/jquery-ui.min.js'></script>
<script>
	$(document).ready(work)
	
	function work(){
		$("#dialog").dialog({
			 autoOpen: false,
			 height: "auto",
		     width: 350,
		     modal: true,
		     resizable:false,
		     buttons:{
		    	 "送出":function(){
		    		 
		    	 },
		    	 "取消":function(){
		    		 $("#dialog").dialog("close");
		    	 }
		     }
		})
		
		$("#questionBT").click(function(){
			$("#dialog").dialog("open");
			
		})
	}
</script>
</head>
<body>
<button id="questionBT" title="提問方塊">我要發問</button>
	
<div id="dialog">
	
</div>
</body>
</html>