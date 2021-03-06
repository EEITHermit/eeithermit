<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊頁面</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/iEdit.min.css">
<style>
.background-image {
	background-image:
		url("<%=request.getContextPath()%>/images/hermit_register_bg.png");
	background-position: center;
	background-repeat: no-repeat;
	border-radius: 2em;
}

.label-style {
	width: 95px;
	float: left;
	text-align: right;
}

.image-style {
	margin-left: 20%;
	margin-bottom: 2em;
}

.data-style {
	margin-left: 65%;
}

.error-data-style {
	color: red;
	padding-left: 1em;
	margin-bottom: 1em;
}

.point-data-style {
	color: red;
}

.radio-style {
	text-align: center;
}

.send-style {
	text-align: center;
}

.progress-text {
	margin-bottom: 10px;
}
.container {
	margin-top: 60px;
}
</style>
</head>
<body>
	<jsp:include page="/fragment/member_page.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="progress-text">
				<span class="label label-danger">1.填寫會員訊息</span>
			</div>
			<div class="progress">
				<div
					class="progress-bar progress-bar-danger progress-bar-striped active"
					role="progressbar" aria-valuenow="40" aria-valuemin="0"
					aria-valuemax="100" style="width: 33%">33%</div>
			</div>

			<form method="post" action="<%=request.getContextPath()%>/MemberNewServlet/registerInsert.do"
				name="myForm">
				<fieldset class="background-image">
					<legend>
						註冊會員資料 <small class="point-data-style">(&check;)為必填項目</small>
					</legend>
					<div class="data-style">
						<div>
							<label for="memTel" class="label-style"><small
								class="point-data-style">(&check;)</small> 行動電話&nbsp;:&nbsp;</label> <input
								type="text" id="memTel" name="memTel" value="${param.memTel}"
								autofocus />
						</div>
						<div class="error-data-style">
							<small>${ErrorMsgKey.TelEmptyError}</small><small>${ErrorMsgKey.TelFormatError}</small>
						</div>
						<div>
							<label for="memAccount" class="label-style"><small
								class="point-data-style">(&check;)</small> 帳號&nbsp;:&nbsp;</label> <input
								type="text" id="memAccount" name="memAccount"
								value="${param.memAccount}" />
						</div>
						<div class="error-data-style">
							<small id="ajax-check-account"></small><small>${ErrorMsgKey.AccountEmptyError}</small><small>${ErrorMsgKey.AccountFormatError}</small>
						</div>
						<div>
							<label for="memPwd" class="label-style"><small
								class="point-data-style">(&check;)</small> 密碼&nbsp;:&nbsp;</label> <input
								type="password" id="memPwd" name="memPwd" autocomplete="off" />
						</div>
						<div class="error-data-style">
							<small>${ErrorMsgKey.PwdEmptyError}</small><small>${ErrorMsgKey.PwdFormatError}</small>
						</div>
						<div>
							<label for="repwd" class="label-style"><small
								class="point-data-style">(&check;)</small> 密碼確認&nbsp;:&nbsp;</label> <input
								type="password" id="repwd" name="repwd" autocomplete="off" />
						</div>
						<div class="error-data-style">
							<small>${ErrorMsgKey.RepwdEmptyError}</small>
						</div>
						<div>
							<label for="memName" class="label-style"><small
								class="point-data-style">(&check;)</small> 姓名&nbsp;:&nbsp;</label> <input
								type="text" id="memName" name="memName" value="${param.memName}" />
						</div>
						<div class="radio-style">
							<input type="radio" name="memGender" value="male" id="male"
								checked="checked" /> <label for="male">先生&nbsp;&nbsp;&nbsp;</label>
							<input type="radio" name="memGender" value="female" id="female" />
							<label for="female">女士</label>
						</div>
						<div class="error-data-style">
							<small>${ErrorMsgKey.NameEmptyError}</small><small>${ErrorMsgKey.NameFormatError}</small>
						</div>
						<div>
							<label for="memEmail" class="label-style"><small
								class="point-data-style">(&check;)</small> Email&nbsp;:&nbsp;</label> <input
								type="text" id="memEmail" name="memEmail"
								value="${param.memEmail}" />
						</div>
						<div class="error-data-style">
							<small>${ErrorMsgKey.EmailEmptyError}</small><small>${ErrorMsgKey.EmailFormatError}</small>
						</div>
						<div>
							<label for="fileImg" class="label-style">頭像設定&nbsp;:&nbsp;</label>
							<input type="file" id="fileImg" name="fileImg" value="上傳圖片">
						</div>
						<div class="image-style">
							<input type="hidden" id="memImage" name="memImage"> <img
								height="150" width="150" id="resultImg">
						</div>
						<div>
							<input type="checkbox" name="agree" value="agree" id="agree" />
							<label for="agree">我已仔細閱讀並明瞭「<a
								href="law_service_page.jsp" target="_blank" style="padding: 0; color: blue;">服務條款</a>」、「<a
								href="law_duty_page.jsp" target="_blank" style="padding: 0; color: blue;">免責聲明</a>」、「<a
								href="law_privacy_page.jsp" target="_blank" style="padding: 0; color: blue;">隱私權聲明</a>」等所載內容及其意義，茲同意該等條款規定，並願遵守網站現今、嗣後規範的各種規則
							</label>
						</div>
						<div class="error-data-style">
							<small>${ErrorMsgKey.AgreeEmptyError}</small>
						</div>
						<div class="send-style">
							<!-- "新增"button有被AJAX控制 -->
							<button type="button" title="新增" id="buttonAdd"
								class="btn btn-primary" data-toggle="modal"
								data-target="#myModal">
								<span class="glyphicon glyphicon-ok"></span>
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" title="重置" id="buttonReset"
								class="btn btn-warning">
								<span class="glyphicon glyphicon-repeat"></span>
							</button>
						</div>
						<input type="hidden" name="action" value="register_insert_Action">
					</div>
				</fieldset>
				<!-- Modal -->
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog modal-sm">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h2 class="modal-title" style="color: red; text-align: center">注意</h2>
							</div>
							<div class="modal-body">
								<p style="text-align: center">
									<b></b>
								</p>
							</div>
							<div class="modal-footer">
								<button type="button" id="buttonConfirm" class="btn btn-default"
									data-dismiss="modal">確認</button>
								<button type="button" id="buttonCancel" class="btn btn-default"
									data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /footer -->
	<footer class="w3-bottom w3-black container-fluid text-center">
		<div>
			<ul class="nav nav-pills w3-centered " style="display: flex;font-size:13px;justify-content: center;">
				<li role="presentation"><a href="<%=request.getContextPath()%>/index.jsp">關於我們</a></li>
				<li role="presentation"><a href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></li>
				<li role="presentation"><a href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">服務條款</a></li>
				<li role="presentation"><a href="<%=request.getContextPath()%>/register/law_service_page.jsp">隱私權聲明</a></li>
			</ul>
		</div>
	    <span class="text-center"><p style="font-size:10px">赫米特開發團隊  Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
	</footer>
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
	<script>
	var txres = null;

	// 新增
	$('#buttonAdd').click(function() {
		$('.modal-body b').text('請確認是否新增');
	});

	// 重置(button type="reset"回復原始值，在本程式需利用新頁面進階清空EL)
	$('#buttonReset').click(function() {
		location.replace('<%=request.getContextPath()%>/register/register_page.jsp');
	});

	// 圖檔操作
	$("#fileImg").change(function(e) {

		var img = e.target.files[0];

		if (!img.type.match('image.*')) {
			alert("Whoops! That is not an image.");
			return;
		}
		iEdit.open(img, false, function(res) {
			$("#resultImg").attr("src", res);
			txres = res;
		});
	});

	$('#buttonConfirm').click(function() {
		$('input[name="memImage"]').val(txres);
		$('form[name="myForm"]').submit();
	});

	// AJAX 帳號檢查
	$('#memAccount').blur(function() {
		var account = this.value;
		var msg = document.querySelector("#ajax-check-account");

		$.ajax({
			url:'<%=request.getContextPath()%>/member.do?',
			method:'post',
			data:{'action':'register_check_account_Action','memAccount':account},
			dataType:'text',
			success:function(data) {
				if(data == "帳號已存在") {
					msg.innerHTML = data;
					$('#buttonAdd').prop('disabled',true);
				}
				else {
					msg.innerHTML = "";
					$('#buttonAdd').prop('disabled',false);
				}
			},
			error:function() {
				alert("您的瀏覽器不支援Ajax!!");
			}
		});
	});
	</script>
</body>
</html>