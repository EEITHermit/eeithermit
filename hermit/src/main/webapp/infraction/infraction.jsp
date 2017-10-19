<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>黑名單申請</title>
<link href='<%= request.getContextPath() %>/css/jquery-ui.min.css' rel='stylesheet' />
<script src='<%= request.getContextPath() %>/js/jquery.min.js'></script>
<script src='<%=request.getContextPath() %>/js/jquery-ui.min.js'></script>
<script>
	document.addEventListener("DOMContentLoaded",work);
	
	function work(){
		$("#memNO").autocomplete({
			source:function(request,response){
				var term = request.term;
				$.get("<%= request.getContextPath() %>/calendarServlet?mission=queryMember"
				,{member:term}
				,function(data){
					response(JSON.parse(data));
				});
			}
		})
		
		$("#bt").on("click",function(){
			var memNO = $("#memNO").val();
			var reason = $("#reason").val();
			//驗證輸入資料
			var error = "";
			var memReg = /[0-9]{5}	.*/;
			if(memNO.trim().length == 0){
				error = error + "請輸入會員資料\n";
			}else if(! memReg.test(memNO.trim())){
				error = error + "輸入格式錯誤\n"
			};
			if(error != ""){
				alert(error);
				return;
			};
			//確認資料無誤後送出資料
			if(confirm("確定送出黑名單申請")){
				$.post("<%= request.getContextPath() %>/infractionServlet?mission=insert"
						,{memNO:memNO,empNO:$("#empNO").val(),reason:reason}
						,function(data){
							alert(data);
							if(data == "申請成功"){
								$("#memNO").val("");
								$("#reason").val("");
							}
						});
			}
		})
	};
</script>
</head>
<body>
	<h2>黑名單申請單</h2>
	<form>
		會員資料:<input type="text" id="memNO" name="memNO"/><br/>
		<p style="float:left">申請原因:<p><textarea style="resize:none" id="reason" name="reason"></textarea><br/>
		<!-- 會員帳號(假資料) -->
		<input type="hidden" value="30001" id="empNO">
		<button type="button" id="bt">提交</button>
	</form>
</body>
</html>