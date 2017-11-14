<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>calendar</title>
<link href='<%=request.getContextPath() %>/css/fullcalendar.min.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/css/scheduler.min.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/css/jquery-ui.min.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/css/jquery-ui.structure.min.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/css/jquery-ui.theme.min.css' rel='stylesheet' />
<link href='<%=request.getContextPath() %>/css/jquery-ui-timepicker-addon.min.css' rel='stylesheet'/>
<%-- <script src='<%=request.getContextPath() %>/js/jquery-3.2.1.min.js'></script> --%>
</head>

<body>
	<!-- 載入框架 -->
	<jsp:include page="/fragment/back_side_page.jsp" />
	<!-- 程式所需js檔 -->
	<script src='<%=request.getContextPath() %>/js/jquery-ui.min.js'></script>
	<script src='<%=request.getContextPath() %>/js/moment.min.js'></script>
	<script src='<%=request.getContextPath() %>/js/fullcalendar.min.js'></script>
	<script src='<%=request.getContextPath() %>/js/scheduler.min.js'></script>
	<script src='<%=request.getContextPath() %>/js/jquery-ui-timepicker-addon.js'></script>
	<script src='<%=request.getContextPath() %>/js/jquery-ui-sliderAccess.js'></script>
	<!-- 以下div為事件點擊彈出視窗 -->
	<div id="dialog-form" title="預約編輯">
		<form id="eventForm">
			<fieldset>
				<input id="eventId" name="eventId"type="hidden"/>
				預約會員：<input id="member"type="text"value="" disabled/><br/>
					   <input id="member"type="hidden" name="member"value="" readonly/>
				開始時間：<input id="startTime"type="text" name="start"value=""readonly/><br/>
				結束時間：<input id="endTime"type="text" name="end"value=""readonly/><br/>
				預約房屋：<input id="house"type="text" value="" disabled/><br/>
					   <input id="house"type="hidden" name="house"value="" readonly/>
				<p style="float:left">備註內容：</p><textarea id="ps" name="ps"style="resize:none" autofocus> </textarea><br/>
			</fieldset>
		</form>
	</div>
	<!-- 以下div為insert事件彈出視窗 -->
	<div id="dialog-insert" title="新增預約">
		<form id="insertForm">
			
				<input id="empNo" type="hidden" name="empNo"value="${empLoginOK.empNO}"/>
				預約會員：<input id="memberIn"type="text" name="member" value=""/><br/>
				開始時間：<input id="startTimeIn"type="text" name="startTime"value="" readonly/><br/>
				結束時間：<input id="endTimeIn"type="text" name="endTime" value="" readonly/><br/>
				預約房屋：<input id="houseIn"type="text" name="house" value=""/><br/>
				<p style="float:left">備註內容：</p><textarea id="psIn" name="ps" style="resize:none"> </textarea><br/>
			
		</form>
	</div>
	<!-- 以下div選擇calendar日期 -->
	<div style="margin-left:450px">
	<input type="button" id="datepicker" value="選擇日期"/>
	</div>
	<!-- calendar顯示div處 -->
	<div id='calendar' class="container" style="font-size:20px"></div>
	<!-- 存放拖拉暫存事件ID -->
	<div><form><input type="hidden" id="tempID" value=""></form></div>
	
<script>
	$(document).ready(createCalendar);
	function createCalendar(){
		//設定fullCalendar
		var calendar = $('#calendar');
		var temp = $("#tempID");
		calendar.fullCalendar({
			defaultView:"agendaWeek", //預設顯示為星期
			editable:true, //可手動編輯事件
			timezone:"local",//設定時區為客戶端時間
		    eventOverlap:false,//拖拉及resize無法重複
		    //自定按鈕
		    customButtons:{
		    	insert:{  //新增按鈕
		    		text:"新增",
		    		click:function(){dialogIn.dialog("open");}
		    	},
		    	check:{  //拖拉事件確定更改按鈕
		    		text:"確認更改",
		    		click:function(){
		    			var changes = temp.val().split('%'); //抓取暫存ID
		    			var setID = new Set(changes);	//去除重複值
		    			setID.delete("");
		    			var eventArray = [];
		    			for(var s of setID){ //取每一個ID的事件物件
		    				var event = calendar.fullCalendar('clientEvents',s)[0];
		    				var eventObject = {id:((event.id).toString()),title:event.title,start:(event.start).format("YYYY-MM-DD HH:mm:ss"),end:(event.end).format("YYYY-MM-DD HH:mm:ss")};
		    				eventArray.push(eventObject);
		    			}
		    			//傳送eventArray的JSON字串
		    			$.post("<%= request.getContextPath() %>/calendarServlet?mission=update",{"events":JSON.stringify(eventArray),"empNo":"${empLoginOK.empNO}"},function(data){
		    				alert(data);
		    				calendar.fullCalendar("refetchEvents"); //重取資料庫事件
		    			});
		    			//設定開始時拖拉更改按鈕不可使用
		    			calendar.find("button").eq(4).prop("disabled",true).css("color","gray");
		    		}
		    	}
		    },
			//設定header
			header:{
				left:"title",
				center:"insert check",
				right:"today prev,next"
			},
			//輸入範圍內事件(選擇時間改變則會重新執行)
			events:function(start,end,timezone,callback){
				var startTime = start.format("YYYY-MM-DD HH:mm:SS");
				var endTime = end.format("YYYY-MM-DD HH:mm:SS");
				$.get("<%= request.getContextPath() %>/calendarServlet?mission=query",{empNo:"${empLoginOK.empNO}",STime:startTime,ETime:endTime},function(data){
					temp.val(""); //切換畫面則清除暫存
					callback(JSON.parse(data)); //傳回data值輸入事件
				})
			},
			//點擊事件後顯示事件內容
			eventClick:function(event,jsEvent,view){
				dialog.dialog( "open" );
				var title = (event.title).split('\n');
				$("#eventId").val(event.id);
				$("#member").val(title[0]);
				$("#startTime").val((event.start).format("YYYY-MM-DD HH:mm:ss"));
				$("#endTime").val((event.end).format("YYYY-MM-DD HH:mm:ss"));
				$("#house").val(title[1]);
				$("#ps").text(title[2]);
			},
			//拖拉事件，儲存事件ID於暫存，開啟更改按鈕
			eventDrop:function(event){
				var tempID = temp.val();
				temp.val(tempID+"%"+event.id);
				calendar.find("button").eq(4).prop("disabled",false).css("color","black");
			},
			//resize事件，儲存事件ID於暫存，開啟更改按鈕
			eventResize:function(event){
				var tempID = temp.val();
				temp.val(tempID+"%"+event.id);
				calendar.find("button").eq(4).prop("disabled",false).css("color","black");
			}
		});
		//設定開始時拖拉更改按鈕不可使用
		calendar.find("button").eq(4).prop("disabled",true).css("color","gray");
		//設定選擇日期按鈕
		var datepicker = $("#datepicker");
		
		datepicker.datepicker({
			changeMonth: true,
		    changeYear: true
		});
		// 更改時間後觸發事件，前往當天表格
		datepicker.on("change",function(){
			calendar.fullCalendar("gotoDate",$(this).val())
		});
		
		//設定事件彈出視窗
		var dialog = $( "#dialog-form" ).dialog({
		      autoOpen: false,
		      height: "auto",
		      width: 400,
		      modal: true,	//啟動時網頁其餘功能無法使用
		      resizable:false,
		      //自訂按鈕
		      buttons:{
		    	"確認":function(){
		    		//檢查輸入資料
		    		var eventArray = [];
		    		var error = "";
		    		var id = $("#eventId").val();
		    		var member = $("#member").val();
		    		var start = $("#startTime").val();
		    		var end = $("#endTime").val();
		    		if(start >= end){
		    			error = error + "結束時間錯誤\n";
		    		}else if(start.substring(0,10) != end.substring(0,10)){
		    			error = error + "行程請勿跨日\n";
		    		}
		    		var house = $("#house").val();
		    		var ps = $("#ps").val();
		    		if(ps.trim().length == 0){
		    			error = error + "備註請勿空白\n";
		    		}
		    		if(error != ""){
		    			alert(error);
		    			return;
		    		}
		    		//資料無誤開始送出資料
	    			var eventObject = {id:id,title:(member+"\n"+house+"\n"+ps)
	    					,start:start,end:end};
	    			eventArray.push(eventObject);
	    			//傳送eventArray的JSON字串
	    			$.post("<%= request.getContextPath() %>/calendarServlet?mission=update",{"events":JSON.stringify(eventArray),"empNo":"${empLoginOK.empNO}"},function(data){
						alert(data);
	    				if(data=="更改成功"){
	    					calendar.fullCalendar("gotoDate",moment(start)) //前往該時間
	    					calendar.fullCalendar("refetchEvents");
	    					dialog.dialog("close");
	    				}
	    			});
		    	},
	    		
		    	"取消":function(){dialog.dialog("close");},
		    	"刪除":function(){
		    		if(confirm("確認刪除這筆預約?") == true){
		    			$.post("<%= request.getContextPath() %>/calendarServlet?mission=delete",{"id":$("#eventId").val()},function(data){
		    				calendar.fullCalendar("refetchEvents");
		    				alert(data);
		    			})
		    			dialog.dialog("close");
		    		}
		    	},
		    	"檢舉會員":function(){
		    		var member = $("#member").val();
		    		window.location = "<%=request.getContextPath()%>/infraction/infraction.jsp?member=" + encodeURI(member);
		    		
		    	}
		      }
		    });
		
		//設定insert事件彈出視窗
		var dialogIn = $( "#dialog-insert" ).dialog({
		      autoOpen: false,
		      height: "auto",
		      width: 400,
		      modal: true,  //啟動時網頁其餘功能無法使用
		      resizable:false,
		      //自訂按鈕
		      buttons:{
		    	"確認":function(){
		    		//驗證資料欄資料
		    		var error = "";
		    		var empNo = $("#empNo").val();
		    		if(empNo.trim().length == 0){
		    			error = error + "請重新登入\n";
		    		};
		    		var member = $("#memberIn").val();
		    		 var RegExp = /^[0-9]{5}	.*/;
		    		 if(member.trim().length == 0){
		    			error = error + "請輸入會員資料\n";
		    		}else if(!RegExp.test(member)){
		    			error = error + "會員資料型態錯誤\n";
		    		};
		    		var start = $("#startTimeIn").val();
		    		if(start.trim().length == 0){
		    			error = error + "請輸入開始時間\n";
		    		};
		    		var end = $("#endTimeIn").val();
		    		if(end.trim().length == 0){
		    			error = error + "請輸入結束時間\n";
		    		};
		    		if(start >= end){
		    			error = error + "結束時間錯誤\n";
		    		}else if(start.substring(0,10) != end.substring(0,10)){
		    			error = error + "行程請勿跨日\n";
		    		};
		    		var house = $("#houseIn").val();
		    		if(house.trim().length == 0){
		    			error = error + "請輸入房屋資訊\n";
		    		}else if(!RegExp.test(house)){
		    			error = error + "房屋資料型態錯誤\n";
		    		};
		    		var ps = $("#psIn").val();
		    		if(ps.trim().length == 0){
		    			error = error + "備註請勿空白\n";
		    		};
		    		if(error != ""){
		    			alert(error);
		    			return;
		    		}
		    		//資料無誤後開始送出
		    		$.post("<%= request.getContextPath() %>/calendarServlet?mission=insert",{empNo:empNo
		    			,member:member,startTime:start,endTime:end,house:house,ps:ps}
		    		,function(data){
		    			
		    			alert(data);
		    			if(data=="新增成功"){
			    			calendar.fullCalendar("gotoDate",moment(start)) //前往該時間
			    			calendar.fullCalendar("refetchEvents");
			    			$("#memberIn").val("");
			    			$("#startTimeIn").val("");
			    			$("#endTimeIn").val("");
			    			$("#houseIn").val("");
			    			$("#psIn").val("");
			    			dialogIn.dialog("close");
		    			}
		    		});
		    	 },
		    	"取消":function(){dialogIn.dialog("close");}
		      }
		    });
		//設定autoComplete
		$("#member").autocomplete({
			source:function(request,response){
				var term = request.term; //取得輸入值
				$.get("<%= request.getContextPath() %>/calendarServlet?mission=queryMember",{member:term},function(data){
					response(JSON.parse(data));  //回傳結果陣列
				});
			}
		});
		$("#house").autocomplete({
			source:function(request,response){
				var term = request.term; //取得輸入值
				$.get("<%= request.getContextPath() %>/calendarServlet?mission=queryHouse",{house:term},function(data){
					response(JSON.parse(data));  //回傳結果陣列
				});
			}
		});
		$("#memberIn").autocomplete({
			source:function(request,response){
				var term = request.term; //取得輸入值
				$.get("<%= request.getContextPath() %>/calendarServlet?mission=queryMember",{member:term},function(data){
					response(JSON.parse(data));  //回傳結果陣列
				});
			}
			
		});
		$("#houseIn").autocomplete({
			source:function(request,response){
				var term = request.term; //取得輸入值
				$.get("<%= request.getContextPath() %>/calendarServlet?mission=queryHouse",{house:term},function(data){
					response(JSON.parse(data)); //回傳結果陣列
				});
			}
		});
		//設定時間選擇器
		$("#startTime").datetimepicker({
			"dateFormat":"yy-mm-dd",	//設定格式
			"timeFormat":"HH:mm:ss",
			controlType:"select"  //選擇方式
		});
		$("#endTime").datetimepicker({
			"dateFormat":"yy-mm-dd",	//設定格式
			"timeFormat":"HH:mm:ss",
			controlType:"select"  //選擇方式
		});
		$("#startTimeIn").datetimepicker({
			"dateFormat":"yy-mm-dd",	//設定格式
			"timeFormat":"HH:mm:ss",
			controlType:"select"  //選擇方式
		});
		$("#endTimeIn").datetimepicker({
			"dateFormat":"yy-mm-dd",	//設定格式
			"timeFormat":"HH:mm:ss",
			controlType:"select"  //選擇方式
		});
		//設定推播頁面傳來之請求，開啟新增方塊
		if("${param.mission}" == "mentionInsert"){
			dialogIn.dialog("open");
			var memberIn = "${param.member}\t${param.memberName}";
			var houseIn = "${param.house}\t${param.houseAddr}";
			$("#memberIn").val(memberIn);
			$("#houseIn").val(houseIn);
		}
	}
	
	
</script>
</body>
</html>