<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A</title>
<link href='<%=request.getContextPath()%>/css/bootstrap.min.css'
	rel='stylesheet' />
<link
	href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/datatables.min.css" />
</head>
<body>
<!-- 載入框架 -->
	<jsp:include page="/fragment/back_side_page.jsp" />
	<div class="container">
		<div class="row">
		<div style="border-bottom: 1px solid #F0F0F0">
			<p style="font-size:40px;color:	#0066CC;font-weight: 20px;">留言查詢</p>
		</div>
			<div class="col-md-5 col-md-offset-4" style="border-bottom: 1px solid #F0F0F0">
				<div style="margin: 20px">
					<label class="form-label">選擇查詢留言種類</label> <select id="querySelect"
						name="select" class="form-control">
						<option value="">請選擇</option>
						<option value="all">全部留言</option>
						<option value="notDeal">未處理留言</option>
						<option value="dealed">已處理留言</option>
					</select>
				</div>
			</div>
			
		</div>
		<div class="col-md-12">
			<div id="showDiv" style="display:none;">
				<table id="showTable" class="hover" style="background-color: #02F78E">
<!-- 				<thead> -->
<!-- 					<tr> -->
<!-- 						<th>留言編號</th><th>留言類型</th><th>留言會員</th><th>留言房屋</th><th>提問時間</th> -->
<!-- 						<th>留言內容</th><th>回覆時間</th><th>回覆員工</th><th>回覆內容</th> -->
<!-- 					</tr> -->
<!-- 				</thead> -->
<!-- 				<tbody></tbody> -->
				</table>
			</div>
		</div>
	</div>
<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
<script>
	$(document).ready(work);
	
	function work(){
		//套用datatable
		
		//選擇
		$("#querySelect").on("change",function(){
			var mission = $(this).val();
			var qrs = [];
			$.get("<%=request.getContextPath()%>/QAndAServlet",{mission:mission},function(data){
				$("#showDiv").toggle(true);
// 				$("#showTable>tbody").html("");
				var divBody = $("#showTable>tbody");
				var json = JSON.parse(data);
				var datas = [];
				for(var vo of json){
					var type;
					if(vo.qaType == 0){
						type="客服";
					}else if(vo.qaType == 1){
						type="問題"
					};
					var q ;
					var qr;
					if(vo.QDetail.length > 8){
						q = vo.QDetail.substring(0,8)+"...<a><span style='font-size:10px;float:right;'>[更多內容]</span></a>";
						qr = vo.QDetail+"<a><span style='font-size:10px;float:right;'>[收起]</span></a>";
					}else{
						q = vo.QDetail;
						qr = "";
					}
					qrs.push(qr);
					var data = [vo.qaNO
								,type
								,vo.memberVO.memNO+" "+vo.memberVO.memName
								,"<a href='"+vo.houseVO.houseNO+"'>"+vo.houseVO.houseTitle+"</a>"
								,vo.QTime
								,q
								,vo.ATime
								,vo.empNO
								,vo.ADetail];
					datas.push(data);
				};
				$("#showTable").DataTable({
					"destroy":true,
					"language" : {
						"lengthMenu" : "每頁顯示 _MENU_ 筆",
						"zeroRecords" : "Nothing found - sorry",
						"info" : "現在正顯示   _PAGE_  共有 _PAGES_ 頁",
						"infoEmpty" : "No records available",
						"infoFiltered" : "(filtered from _MAX_ total records)",
						"search" : "查詢:",
						"paginate" : {
							"first" : "首頁",
							"last" : "末頁",
							"next" : "下頁",
							"previous" : "前頁"
						}
					},
					data:datas,
					columns: [
			            { title: "留言編號" ,width:"10%"},
			            { title: "留言類型" ,width:"6%"},
			            { title: "留言會員" ,width:"10%"},
			            { title: "留言房屋" ,width:"10%"},
			            { title: "提問時間" ,width:"10%"},
			            { title: "留言內容" ,width:"17%"},
			            { title: "回覆時間" ,width:"10%","defaultContent": "----"},
			            { title: "回覆員工" ,width:"10%","defaultContent": "----"},
			            { title: "回覆內容" ,width:"17%","defaultContent": "尚未回覆"}
			        ]
				})
			});
		});
		
	};
</script>
</body>
</html>