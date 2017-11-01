<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>預約按鈕</title>
<link href='<%= request.getContextPath() %>/css/jquery-ui.min.css' rel='stylesheet' />
<link href='<%= request.getContextPath() %>/css/jquery-ui.structure.min.css' rel='stylesheet' />
<link href='<%= request.getContextPath() %>/css/jquery-ui.theme.min.css' rel='stylesheet' />
<script src='<%= request.getContextPath() %>/js/jquery.min.js'></script>
<script src='<%= request.getContextPath() %>/js/jquery-ui.min.js'></script>
<script src="<%= request.getContextPath() %>/js/jquery-form.js"></script>
<script>
	document.addEventListener("DOMContentLoaded",work);
	
	function work(){
		//設定顯示上午下午
		$("#reservationForm>div").hover(function(){
			$(this).children("div").toggle()
		});
		//設定星期與上午下午checked連動
		$("#reservationForm>div>input").on("click",function(){
			var th = $(this);
			th.parent("div").children("div").children("input").prop("checked",th.prop("checked"));
		});
		//設定上午下午與星期checked連動
		$("#reservationForm>div>div>input").on("click",function(){
			$(this).parent("div").parent("div").children("input").prop("checked",true);
		});
		//設定預約跳出視窗
		var dialog = $("#reservationDiv").dialog({
			autoOpen:false,
			modal: true
		});	
		//跳出按鈕
		$("#bt").on("click",function(){
			dialog.dialog("open");
		});
		//取消按鈕
		$("#btCancel").on("click",function(){
			dialog.dialog("close");
		});
		//送出表單資料設定
		$("#btSubmit").on("click",function(){
			//jquery的form插件，太神啦
			$("#reservationForm").ajaxSubmit(function(data){
				alert(data);
				dialog.dialog("close");
			});
		});
		
	}
</script>
<style>
	#reservationDiv div>div{
		width:90px;
		border:1px solid gray;
		background-color: #46A3FF;
		clear:left;
		position:absolute;
		display:none;
	}
 	#reservationDiv div{ 
 		float:left;
 		
 	} 
</style>
</head>
<body>
<button id="bt">預約</button>
<div id="reservationDiv">
	<form id="reservationForm" action="<%= request.getContextPath() %>/reservationServlet?mission=reservation" method="POST">
		<!-- 取得會員帳號 -->
		<input type="hidden" name="memberNo" value="${LoginOK.memNO}"/>
		<!-- 房屋帳號為假資料 -->
		<input type="hidden" name="houseNo" value="20001"/>
		<h3>請選擇期望預約時間</h3>
		<div id="MonDiv">
			<input type="checkbox" name="week" value="一" id="Mon"/><label for="Mon">星期一</label> 
			<div id="MonTime">
				<input type="checkbox" name="Time" value="一上" id="MonMon"/><label for="MonMon">上午</label><br/>
				<input type="checkbox" name="Time" value="一下" id="MonAf"/><label for="MonAf">下午</label>
			</div>
		</div>
		<div id="TuesDiv">
			<input type="checkbox" name="week" value="二"id="Tues"/><label for="Tues">星期二</label> 
			<div id="TuesTime">
				<input type="checkbox" name="Time" value="二上" id="TuesMon"/><label for="TuesMon">上午</label><br/>
				<input type="checkbox" name="Time" value="二下" id="TuesAf"/><label for="TuesAf">下午</label>
			</div>
		</div>
		<div id="WednDiv">
			<input type="checkbox" name="week" value="三"id="Wedn"/><label for="Wedn">星期三 </label>
			<div id="WednTime">
				<input type="checkbox" name="Time" value="三上" id="WednMon"/><label for="WednMon">上午</label><br/>
				<input type="checkbox" name="Time" value="三下" id="WednAf"/><label for="WednAf">下午</label>
			</div>
		</div>
		<div id="ThurDiv">
			<input type="checkbox" name="week" value="四"id="Thur"/><label for="Thur">星期四 </label>
			<div id="ThurTime">
				<input type="checkbox" name="Time" value="四上" id="ThurMon"/><label for="ThurMon">上午</label><br/>
				<input type="checkbox" name="Time" value="四下" id="ThurAf"/><label for="ThurAf">下午</label>
			</div>
		</div>
		<div id="FriDiv">
			<input type="checkbox" name="week" value="五" id="Fri"/><label for="Fri">星期五 </label>
			<div id="FriTime">
				<input type="checkbox" name="Time" value="五上" id="FriMon"/><label for="FriMon">上午</label><br/>
				<input type="checkbox" name="Time" value="五下" id="FriAf"/><label for="FriAf">下午</label>
			</div>
		</div>
		<div id="SatDiv">
			<input type="checkbox" name="week" value="六" id="Sat"/><label for="Sat">星期六 </label>
			<div id="SatTime">
				<input type="checkbox" name="Time" value="六上" id="SatMon"/><label for="SatMon">上午</label><br/>
				<input type="checkbox" name="Time" value="六下" id="SatAf"/><label for="SatAf">下午</label>
			</div>
		</div>
		<div id="SunDiv">
			<input type="checkbox" name="week" value="日" id="Sun"/><label for="Sun">星期日 </label>
			<div id="SunTime">
				<input type="checkbox" name="Time" value="日上" id="SunMon"/><label for="SunMon">上午</label><br/>
				<input type="checkbox" name="Time" value="日下" id="SunAf"/><label for="SunAf">下午</label>
			</div>
		</div>
		<div style="clear:both;margin-top:50px">
		<button type="button"id="btSubmit">確認</button>
		<button type="reset"id="btCancel">取消</button>
		</div>
	</form>
</div>
</body>
</html>