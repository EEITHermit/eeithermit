<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberBackFavorite</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<link
	href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/font-awesome.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/adminia.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/adminia-responsive.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/pages/dashboard.css"
	rel="stylesheet" />
<body>
	<jsp:include page="/fragment/member_page.jsp"></jsp:include>

<style>
a:link, a:visited, a:hover, a:active {
	text-align: left;
}

#feedback {
	font-size: 1.4em;
}

#selectable .ui-selecting {
	background: #FECA40;
}

#selectable .ui-selected {
	background: #F39814;
	color: white;
}

#selectable {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 95%;
}

#selectable li {
	margin: 3px;
	padding: 0.4em;
	font-size: 1.4em;
}

.favtitle {
	font-size: 1.4em;
	margin-left: 3%;
}

.favstatus, .favrent, .favsize {
	font-size: 0.7em;
	margin-left: 3%;
}

.favaddr {
	font-size: 1em;
	font-style: italic;
	margin-left: 10%;
}
/* Snackbar / Toast */
#snackbar {
    visibility: hidden;
    min-width: 250px;
    margin-left: -125px;
    background-color: #333;
    color: #fff;
    text-align: center;
    border-radius: 2px;
    padding: 16px;
    position: fixed;
    z-index: 1;
    left: 50%;
    bottom: 100px;
    font-size: 17px;
}

#snackbar.show {
    visibility: visible;
    -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
    animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

@-webkit-keyframes fadein {
    from {bottom: 0; opacity: 0;} 
    to {bottom: 100px; opacity: 1;}
}

@keyframes fadein {
    from {bottom: 0; opacity: 0;}
    to {bottom: 100px; opacity: 1;}
}

@-webkit-keyframes fadeout {
    from {bottom: 100px; opacity: 1;} 
    to {bottom: 0; opacity: 0;}
}

@keyframes fadeout {
    from {bottom: 100px; opacity: 1;}
    to {bottom: 0; opacity: 0;}
}
</style>
	<div id="content">

		<div class="container">

			<div class="row">

				<div class="span3">

					<div class="account-container">

						<div class="account-avatar">
							<img src="${LoginOK.memImage}" alt="memImage" class="thumbnail" />
						</div>
						<!-- /account-avatar -->


						<!-- /account-details -->
					</div>
					<div class="account-details">
						<span class="account-name"
							style="font-family: Microsoft JhengHei;padding-left: 75px">${LoginOK.memAccount}</span>
						<!-- 	<span class="account-name" style="font-family: Microsoft JhengHei">徐漢勳</span> -->
						<input type="hidden" id="memNO" name="memNO" value="${LoginOK.memNO}">
					</div>
					<!-- /account-container -->

					<hr />

					<ul id="main-nav" class="nav nav-tabs nav-stacked">
						<li><a href="./mem_back_index.jsp"> <i
								class="glyphicon glyphicon-home" style="height: 30px;font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">首頁</span>
						</a></li>

						<li class="active"><a href="./mem_back_favorite.jsp"> <i
								class="glyphicon glyphicon-heart" style="height: 30px;font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">收藏</span>
						</a></li>

						<li><a href="./mem_back_calendar.jsp"> <i
								class="glyphicon glyphicon-calendar" style="height: 30px;font-size: 30px"></i>
								<span style="font-size: 15px; font-family: Microsoft JhengHei">預約</span>
						</a></li>

						<li><a href="./mem_back_qanda.jsp"> <i
								class="glyphicon glyphicon-comment" style="height: 30px;font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">Q&A</span>
						</a></li>

						<li><a href="./mem_back_lease.jsp"> <i
								class="glyphicon glyphicon-file" style="height: 30px;font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">租賃紀錄</span>
						</a></li>

						<li><a href="<%=request.getContextPath()%>/member.do?action=getOneMember"> <i
								class="glyphicon glyphicon-edit" style="height: 30px;font-size: 30px"></i> <span
								style="font-size: 15px; font-family: Microsoft JhengHei">修改會員資料</span>
						</a></li>
					</ul>

				</div>
				<!-- /span3 -->



				<div class="span8">
					<h1 class="page-title">
						<i class="glyphicon glyphicon-heart"></i> <span
							style="font-family: Microsoft JhengHei">收藏</span>
					</h1>

					<div class="widget widget-table">

						<div class="widget-header">
							<i class="glyphicon glyphicon-list"
								style="font-size: 20px; padding-left: 8px"></i> <span
								style="font-weight: bold; font-size: 18px; font-family: Microsoft JhengHei">我的收藏</span>
						</div>
						<!-- /widget-header -->


						<!-- 這邊是放你的資料 -->
						<div class="widget-content">
							<ol id="selectable">
							</ol>
						</div>
						<!-- /widget-content -->
					</div>
					<!-- /widget -->
				</div>
				<!-- /span9 -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /content -->

	<div id="snackbar">已經成功移除</div>
	<div id="footer">

		<!-- 		<div class="container">
			<hr />
			<p style="text-align: center">Hermit House for Rent &reg;</p>
		</div> -->
		<!-- /container -->
	</div>
	<!-- /footer -->
	<footer
		class="navbar-fixed-bottom w3-black container-fluid text-center">
	<div>
		<ul class="nav nav-pills w3-centered "
			style="display: flex; font-size: 13px; justify-content: center;">
			<li role="presentation"><a
				href="<%=request.getContextPath()%>/index.jsp">關於我們</a></li>
			<li role="presentation"><a
				href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></li>
			<li role="presentation"><a
				href="<%=request.getContextPath()%>/register/law_service_page.jsp">服務條款</a></li>
			<li role="presentation"><a
				href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">隱私權聲明</a></li>
		</ul>
	</div>
	<span class="text-center"><p style="font-size: 10px">赫米特開發團隊
			Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
	</div>
	</footer>



	<!-- Le javascript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/excanvas.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<script>
		$(function() {
			
			loadFavorite();

		});
		
		function loadFavorite() {
			var no = $("#memNO").val();
			console.log(no);
			$.ajax({
				url:'<%=request.getContextPath()%>/FavoriteServlet?',
				method : 'post',
				data : {
					'action' : 'favorite_getAJAX_Action',
					'memNO' : no
				},
				dataType : 'JSON',
				success : function(data) {
					var fragment = $(document.createDocumentFragment());
					$.each(data,function(k, v) {
						var cell = $('<p></p>').html('<img width="40px" src="<%=request.getContextPath()%>/images/like_y.png"><span class="favtitle">'
														+ v.houseTitle
														+ '</span><span class="favstatus">'
														+ v.houseStatus
														+ '</span><button type="button" class="close">&times;</button><p class="favaddr">'
														+ v.houseAddr
														+ '</p><hr /><img height="50" width="50" src="'+v.previewPic+'"><span class="favrent">租金：'
														+ v.houseRent
														+ '</span><span class="favsize">坪數：'
														+ v.houseSize
														+ '</span><input type="hidden" name="favNO" value="'+v.favNO+'">');
						var row = $('<li class="ui-widget-content"></li>').append(cell);
						fragment.append(row);
					});
					$("#selectable").html(fragment);
					$("#selectable").selectable();
					//close
					$('.close').click(function() {
						var fav = $(this).parents('li').find('input').val();
						$.ajax({
							url:'<%=request.getContextPath()%>/FavoriteServlet?',
							method : 'post',
							data : {
								'action' : 'favorite_delete_Action',
								'favNO' : fav
							},
							dataType : 'text',
							success : function(data) {
								var thebar = document.getElementById("snackbar");
								thebar.className = "show";
								setTimeout(function(){ thebar.className = thebar.className.replace("show", ""); }, 3000);
							},
							error : function() {
								alert("您的瀏覽器不支援Ajax!!");
							}
						})
						$(this).parents('li').remove();
					});
				},
				error : function() {
					alert("您的瀏覽器不支援Ajax!!");
				}
			});
		}
	</script>
</body>
</html>