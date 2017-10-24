<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Home Work</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/iEdit.min.css">
<style>
.mid {
	height: 500px;
	text-align: center; 
}
</style>
</head>
<body>
	<div class="glyphicon glyphicon-plane"></div>
	<!-- Single button -->
	<div class="btn-group">
		<button type="button" class="btn btn-info dropdown-toggle"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			Action <span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=request.getContextPath()%>/Member/index.jsp">首頁</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					
							<li><a href="login.jsp">登入</a></li>
							<li><a href="#">註冊</a></li>
							
							<!-- member=40001是假資料，等統整時要拿掉 -->
							<li><a href="<%=request.getContextPath()%>/member.do?action=member_search&member=40001">會員管理</a></li>
							
							<li><a href="#">登出</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="row">
		<div class="col-md-4 mid"></div>
		<div class="col-md-4 mid">
			
		<form method="POST" action="<%=request.getContextPath()%>/member.do?action=update" id="form">
			
			編號<input type="text" value="${memNO}" disabled><br>
			   <input type="hidden" readonly value="${memNO}" name="memNO">
			
			電話 <input type="text" value="${memTel}" disabled>
			   <input type="hidden" value="${memTel}" name="memTel">
			<font size="-1" color="#FF0000">${MsgMap.memTel}</font>
			<br>
			
			帳號<input type="text" value="${memAccount}" disabled>
			<input type="hidden"  value="${memAccount}" name="memAccount"><br>
			
			密碼<input type="password" value="${memPwd}" name="memPwd">
			<font size="-1" color="#FF0000">${MsgMap.memPwd}</font>
			<br>
			
			姓名<input type="text" value="${memName}" name="memName">
            <font size="-1" color="#FF0000">${MsgMap.memName}</font>
			<br>
			
			性別<input type="text" value="${memGender}" disabled>
			   <input type="hidden" readonly value="${memGender}" name="memGender" ><br>
			   
			信箱<input type="text" value="${memEmail}" name="memEmail">
			<font size="-1" color="#FF0000">${MsgMap.memEmail}</font>
			<br>
			註冊時間<input type="text" value="${memRegister}" disabled>
			<input type="hidden" readonly value="${memRegister}" name="memRegister"><br>
			
			會員狀態<input type="text" value="${memStatus}" disabled>
			<input type="hidden" readonly value="${memStatus}" name="memStatus"><br>
			
			違規次數<input type="text" value="${memInfract}" disabled>
			<input type="hidden" readonly value="${memInfract}" name="memInfract"><br>
			
			<div>
			圖片<input type="file" id="file" > 
			<input type="hidden" id="memImage" name="memImage" value="${memImage}"  >
			
			<img id="result" src="${memImage}" name="memImage" id="memImage" height="200" width="200">
			</div>
			<input type="submit" value="修改">
			
			</form>
		
		
	
		</div>
		<div class="col-md-4 mid"></div>
	</div>
	
<!-- 	</table> -->
	<%-- <%@ include file="page2.file" %> --%>
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
	<script type="text/javascript">
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
	</script>
</body>

</html>