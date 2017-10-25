<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理頁面</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://code.jquery.com/ui/1.12.0/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/iEdit.min.css">
<style>
#resultImg {
	display: none;
}

.error-data-style {
	color: red;
	width: 75px;
}
</style>
</head>
<body>
	<jsp:include page="/fragment/back_side_page.jsp" />
	<div class="container">
		<div class="row">
			<form method="post" action="<%=request.getContextPath()%>/member.do"
				name="myForm">
				<table id="myTable1" class="table table-bordered">
					<thead>
						<tr>
							<th>會員編號</th>
							<th>會員頭像</th>
							<th>會員電話</th>
							<th>會員帳號</th>
							<th>會員密碼</th>
							<th>會員姓名</th>
							<th>會員性別</th>
							<th>會員信箱</th>
							<th>註冊時間</th>
							<th>會員身分</th>
							<th>累計違規</th>
							<th>編輯操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
					</tfoot>
				</table>
				<div>
					<button type="reset" title="重置" id="buttonReset"
						class="btn btn-warning">
						<span class="glyphicon glyphicon-repeat"></span>
					</button>
				</div>
				<table id="myTable2" class="table table-bordered">
					<thead>
					</thead>
					<tbody>
						<tr>
							<!-- value表設定真值，<span>表同時需要純顯示(與form系統不同) -->
							<td><input type="hidden" id="memNO" name="memNO"
								value="${param.memNO}"><span>${param.memNO}</span></td>
							<td><input type="hidden" id="memImage" name="memImage"><span
								id="presentImg"><c:if test="${not empty param.memNO}">
										<img height="50" width="50" src="">
									</c:if></span><span><img height="50" width="50" id="resultImg"></span></td>
							<td><input type="text" style="width: 75px"
								class="form-control" id="memTel" name="memTel"
								placeholder="會員電話" value="${param.memTel}">
								<div class="error-data-style">
									<small>${ErrorMsgKey.TelEmptyError}</small><small>${ErrorMsgKey.TelFormatError}</small>
								</div></td>
							<td><input type="text" style="width: 75px"
								class="form-control" id="memAccount" name="memAccount"
								placeholder="會員帳號" value="${param.memAccount}">
								<div class="error-data-style">
									<small id="ajax-check-account"></small><small>${ErrorMsgKey.AccountEmptyError}</small><small>${ErrorMsgKey.AccountFormatError}</small>
								</div></td>
							<td><input type="text" style="width: 75px"
								class="form-control" id="memPwd" name="memPwd"
								placeholder="會員密碼" value="${param.memPwd}">
								<div class="error-data-style">
									<small>${ErrorMsgKey.PwdEmptyError}</small><small>${ErrorMsgKey.PwdFormatError}</small>
								</div></td>
							<td><input type="text" style="width: 75px"
								class="form-control" id="memName" name="memName"
								placeholder="會員姓名" value="${param.memName}">
								<div class="error-data-style">
									<small>${ErrorMsgKey.NameEmptyError}</small><small>${ErrorMsgKey.NameFormatError}</small>
								</div></td>
							<td><select class="form-control" id="memGender"
								name="memGender" style="width: 60px">
									<option value="male"
										<c:if test="${param.memGender eq 'male'}">selected="selected"</c:if>>男</option>
									<option value="female"
										<c:if test="${param.memGender eq 'female'}">selected="selected"</c:if>>女</option>
							</select></td>
							<td><input type="text" style="width: 75px"
								class="form-control" id="memEmail" name="memEmail"
								placeholder="會員信箱" value="${param.memEmail}">
								<div class="error-data-style">
									<small>${ErrorMsgKey.EmailEmptyError}</small><small>${ErrorMsgKey.EmailFormatError}</small>
								</div></td>
							<td><input type="text" style="width: 75px"
								class="form-control" id="memRegister" name="memRegister"
								placeholder="註冊時間" value="${param.memRegister}">
								<div class="error-data-style">
									<small>${ErrorMsgKey.RegisterEmptyError}</small>
								</div></td>
							<td><select class="form-control" id="memStatus"
								name="memStatus" style="width: 90px">
									<option value="general"
										<c:if test="${param.memStatus eq 'general'}">selected="selected"</c:if>>一般會員驗證</option>
									<option value="facebook"
										<c:if test="${param.memStatus eq 'facebook'}">selected="selected"</c:if>>FB驗證</option>
									<option value="google"
										<c:if test="${param.memStatus eq 'google'}">selected="selected"</c:if>>Google驗證</option>
									<option value="infraction"
										<c:if test="${param.memStatus eq 'infraction'}">selected="selected"</c:if>>黑名單會員</option>
									<option value="fresh"
										<c:if test="${param.memStatus eq 'fresh'}">selected="selected"</c:if>>未驗證會員</option>
							</select></td>
							<td><input type="text" style="width: 75px"
								class="form-control" id="memInfract" name="memInfract"
								placeholder="累計違規" value="${param.memInfract}">
								<div class="error-data-style">
									<small>${ErrorMsgKey.InfractFormatError}</small>
								</div></td>
							<td><button type="button" title="新增" id="buttonAdd"
									class="btn btn-primary" data-toggle="modal"
									data-target="#myModal">
									<span class="glyphicon glyphicon-ok"></span>
								</button>
								<button type="button" title="修改" id="buttonUpdate"
									class="btn btn-success" data-toggle="modal"
									data-target="#myModal">
									<span class="glyphicon glyphicon-edit"></span>
								</button></td>
						</tr>
						<input type="hidden" name="action">
					</tbody>
					<tfoot>
					</tfoot>
				</table>
				<div>
					<input type="file" id="fileImg" name="fileImg" value="上傳圖片">
				</div>
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
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/iEdit.min.js"></script>
	<script>
		$(function() {
			var txres = null;
			
			loadProduct();

			function loadProduct() {
				$.getJSON('<%=request.getContextPath()%>/member.do',{'action' : 'management_getAll_Action'},function(datas) {
					var fragment = $(document.createDocumentFragment());
					$.each(datas,function(k, v) {
						var cell1 = $('<td></td>').text(v.memNO);
						var cell2 = $('<td></td>').html('<img height="50" width="50" src="'+v.memImage+'">');
						var cell3 = $('<td></td>').text(v.memTel);
						var cell4 = $('<td></td>').text(v.memAccount);
						var cell5 = $('<td></td>').text(v.memPwd);
						var cell6 = $('<td></td>').text(v.memName);
						var cell7 = $('<td></td>').text(v.memGender);
						var cell8 = $('<td></td>').text(v.memEmail);
						var cell9 = $('<td></td>').text(v.memRegister);
						var cell10 = $('<td></td>').text(v.memStatus);
						var cell11 = $('<td></td>').text(v.memInfract);
						var cell12 = $('<td></td>').html('<button type="button" title="移除" class="btn btn-danger" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-remove"></span></button> <button type="button" title="選取" class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span></button>');

						var row = $('<tr></tr>').append([cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12]);
						fragment.append(row);
					});
					$('#myTable1>tbody').html(fragment);
					$('#myTable1').DataTable( {
				        "language": {
				            "lengthMenu": "每頁顯示 _MENU_ 筆",
				            "zeroRecords": "Nothing found - sorry",
				            "info": "現在正顯示   _PAGE_  共有 _PAGES_ 頁",
				            "infoEmpty": "No records available",
				            "infoFiltered": "(filtered from _MAX_ total records)",
				            "search": "查詢:",
				            "paginate": {
				        		"first":      "首頁",
				        		"last":       "末頁",
				        		"next":       "下頁",
				        		"previous":   "前頁"
				        	}
				        }
					});
				});
				$( "#memRegister" ).datepicker({
					dateFormat: "yy-mm-dd"
				});
			}

			// 刪除
			$('#myTable1>tbody').on('click','td>button:nth-child(1)',function() {
						var id = $(this).parents('tr').find('td:nth-child(1)')
								.text();
						$('input[name="action"]').val(
								'management_delete_Action');
						$('#memNO').val(id);
						$('.modal-body b').text('請確認是否刪除');
			});

			// 選取
			$('#myTable1>tbody').on('click', 'td>button:nth-child(2)',function() {
						var tds = $(this).parents('tr').find('td');
						// PK設值，但這設值是為了回傳，還是hidden無顯示
						$('#memNO').val(tds.eq(0).text());
						// 為了顯示PK埋標籤<span></span>，使用者維持無法修改
						$('#memNO+span').text(tds.eq(0).text());
						// memImage設值，但這設值是為了回傳，還是hidden無顯示
						$('#memImage').val(tds.eq(1).text());
						// 為了顯示memImage埋標籤<span></span>，使用者維持無法修改
						$('#presentImg').html(tds.eq(1).html());
						$('#memTel').val(tds.eq(2).text());
						$('#memAccount').val(tds.eq(3).text());
						$('#memPwd').val(tds.eq(4).text());
						$('#memName').val(tds.eq(5).text());
						if (tds.eq(6).text() == '女')
							$('#memGender option:eq(1)').prop('selected',true);
						else
							$('#memGender option:eq(0)').prop('selected',true);
						$('#memEmail').val(tds.eq(7).text());
						$('#memRegister').val(tds.eq(8).text());
						if (tds.eq(9).text() == '一般會員驗證')
							$('#memStatus option:eq(0)').prop('selected',true);
						else if (tds.eq(9).text() == 'FB驗證')
							$('#memStatus option:eq(1)').prop('selected',true);
						else if (tds.eq(9).text() == 'Google驗證')
							$('#memStatus option:eq(2)').prop('selected',true);
						else
							$('#memStatus option:eq(3)').prop('selected',true);
						$('#memInfract').val(tds.eq(10).text());
						$('#resultImg').css('display','none');
						$('#presentImg').css('display','inline');
						txres = tds.find('img').attr('src');
			});

			// 重置(button type="reset"回復原始值，在本程式需利用新頁面進階清空EL)
			$('#buttonReset').click(function() {
				location.replace('<%=request.getContextPath()%>/management/manage_member_page.jsp');
			});

			// 新增
			$('#buttonAdd').click(function() {
				$('input[name="action"]').val('management_insert_Action');
				$('.modal-body b').text('請確認是否新增');
			});

			// 修改
			$('#buttonUpdate').click(function() {
				$('input[name="action"]').val('management_update_Action');
				$('.modal-body b').text('請確認是否修改');
			});

			// 圖檔操作
			$("#fileImg").change(function(e) {

				var img = e.target.files[0];

				if (!img.type.match('image.*')) {
					alert("Whoops! That is not an image.");
					return;
				}
				iEdit.open(img, true, function(res) {
					$('#resultImg').css('display', 'inline');
					$('#presentImg').css('display', 'none');
					$("#resultImg").attr("src", res);
					txres = res;
				});
			});

			$('#buttonConfirm').click(function() {
				$('input[name="memImage"]').val(txres);
				$('form[name="myForm"]').submit();
			});
		})
		
		// AJAX 帳號檢查
		$('#memAccount').blur(function() {
			var account = this.value;
			var msg = document.querySelector("#ajax-check-account");

			$.ajax({
				url:'<%=request.getContextPath()%>/member.do?',
				method : 'post',
				data : {
					'action' : 'register_check_account_Action',
					'memAccount' : account
				},
				dataType : 'text',
				success : function(data) {
					if (data == "帳號已存在") {
						msg.innerHTML = data;
						$('#buttonAdd').prop('disabled', true);
						$('#buttonUpdate').prop('disabled', true);
					} else {
						msg.innerHTML = "";
						$('#buttonAdd').prop('disabled', false);
						$('#buttonUpdate').prop('disabled', false);
					}
				},
				error : function() {
					alert("您的瀏覽器不支援Ajax!!");
				}
			});
		});
	</script>
</body>
</html>