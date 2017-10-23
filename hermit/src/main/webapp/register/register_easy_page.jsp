<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊頁面</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
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
</style>
</head>
<body>
	<header> <nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#myNavbar" aria-expanded="ture">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Hermit</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span>
						Home</a></li>
			</ul>
		</div>
	</div>
	</nav></header>
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

			<form method="post" action="<%=request.getContextPath()%>/member.do"
				name="myForm">
				<fieldset class="background-image">
					<legend>
						註冊會員資料 <small class="point-data-style">(&check;)為必填項目</small>
					</legend>
					<div class="data-style">
						<div>
							<label for="memName" class="label-style">姓名&nbsp;:&nbsp;</label>
							<input type="text" id="memName" name="memName"
								value="${sessionScope.BearerMsgKey.memName}" disabled="disabled" />
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
							<label for="memTel" class="label-style"><small
								class="point-data-style">(&check;)</small> 行動電話&nbsp;:&nbsp;</label> <input
								type="text" id="memTel" name="memTel" value="${param.memTel}"
								autofocus />
						</div>
						<div class="error-data-style">
							<small>${ErrorMsgKey.TelEmptyError}</small><small>${ErrorMsgKey.TelFormatError}</small>
						</div>
						<c:if test="${empty sessionScope.BearerMsgKey.memEmail}">
							<div>
								<label for="memEmail" class="label-style"><small
									class="point-data-style">(&check;)</small> Email&nbsp;:&nbsp;</label> <input
									type="text" id="memEmail" name="memEmail"
									value="${param.memEmail}" />
							</div>
							<div class="error-data-style">
								<small>${ErrorMsgKey.EmailEmptyError}</small><small>${ErrorMsgKey.EmailFormatError}</small>
							</div>
						</c:if>
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
								href="law_service_page.jsp" target="_blank">服務條款</a>」、「<a
								href="law_duty_page.jsp" target="_blank">免責聲明</a>」、「<a
								href="law_privacy_page.jsp" target="_blank">隱私權聲明</a>」等所載內容
								及其意義，茲同意該等條款規定，並願遵守網站現今、嗣後規範的各種規則
							</label>
						</div>
						<div class="error-data-style">
							<small>${ErrorMsgKey.AgreeEmptyError}</small>
						</div>
						<div class="send-style">
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
						<input type="hidden" name="action"
							value="register_easy_insert_Action">
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
		<footer class="footer"> <small>&copy; IIIEDU.GARYHSU</small>
		</footer>
		<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
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
				iEdit.open(img, true, function(res) {
					$("#resultImg").attr("src", res);
					txres = res;
				});
			});

			$('#buttonConfirm').click(function() {
				$('input[name="memImage"]').val(txres);
				$('form[name="myForm"]').submit();
			});
		</script>
</body>
</html>