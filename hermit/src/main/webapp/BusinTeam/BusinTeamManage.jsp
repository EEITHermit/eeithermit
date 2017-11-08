<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>業務組別管理</title>
<jsp:include page="/fragment/back_side_page.jsp" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/accordion.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/accordion.min.js"></script> 
<style>
	.acc_head{
		font-family:  Microsoft JhengHei;
	}
	.acc_content{
		background-color: #FFFFFF;
	}
	.accordion{
		border-radius: 20px;
	}
</style>
</head>
<body>
		<div class="container">
			<div class="accordion">
					<div class="accordion_in">
						<div class="acc_head" style="background-color: #999999;font-size: 1.2em;font-weight:bold;">新增業務組別</div>
						<div class="acc_content" style="text-align: center;">
							<form method="get" action="<%=request.getContextPath() %>/businTeam">
								<div class="input-group col-md-6" style="margin:5px auto;">
								  <span class="input-group-addon" style="font-weight:bold;font-family: Microsoft JhengHei;">組別名稱</span>
								  <input type="text" class="form-control" placeholder="請輸入組別名稱" id="businName" name="businName">
								</div>
								<div class="input-group col-md-6" style="margin:5px auto;">
								  <span class="input-group-addon" style="font-weight:bold;font-family: Microsoft JhengHei;">組別經理</span>
								  <input type="text" class="form-control" id="manager" placeholder="請輸入員工代號" name="manager">
								  <span style="height:20px;width:70px;background-color: white"></span>
								</div>
								<input type="hidden" name="action" value="insertBusinTeam">
								  <button type="submit" style="margin:5px auto;text-align: center;">新增</button>
							</form>
						</div>
					</div>
				
					<div class="accordion_in">
					<div class="acc_head" style="background-color: #999999;font-size: 1.2em;font-weight:bold;">管理業務組別</div>
						<div class="acc_content">
							<p></p>
						</div>
					</div>
				
					<div class="accordion_in">
					<div class="acc_head" style="background-color: #999999;font-size: 1.2em;font-weight:bold;">指派業務組別</div>
						<div class="acc_content">
							<p></p>
						</div>
					</div>
			</div>
		</div>
	<script>
		$(function(){
			$(".accordion").accordionjs({
				  activeIndex : 1,     
				  closeAble: true, 
				  closeOther  : true,  
				  slideSpeed: 200 
				});
			
		})
	</script>
</body>
</html>