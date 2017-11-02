<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hermit租屋網免責聲明</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.background-image {
	background-image:
		url("<%=request.getContextPath()%>/images/hermit_register_bg.png");
	background-position: center;
	background-repeat: no-repeat;
	/* 	background-size: 100% 100%; */
}

header h1 {
	text-align: center;
	font-weight: bold;
	font-size: 2.5em;
}

.container {
	margin-top: 70px;
	margin-bottom: 5em;
}
</style>
</head>
<body>
	<jsp:include page="/fragment/member_page.jsp"></jsp:include>
	<div class="container">
		<header>
		<h1>
			<span class="label label label-default">免責聲明</span>
		</h1>
		</header>
		<div class="container background-image">
			<div class="row" style="line-height: 3em;">
				<article> <section>
				<p>當您成為Hermit租屋網(以下稱「本網站」)的用戶後，即表示你已詳細閱讀及明確瞭解本網站之服務條款、隱私權保護政策，並同意在下列情況時本網站毋需承擔任何責任：</p>
				<ul>
					<li>您使用本服務之風險由您個人負擔。用戶同意使用「本網站」各項服務系基於用戶的個人意願，並同意自負任何風險，包括因為自「本網站」下載資料或圖片，或自「本網站」服務中獲得之資料導致發生任何資源流失等結果。</li>
					<li>「本網站」就各項服務，不負任何明示或默示之擔保責任。「本網站」不保證各項服務之穩定、安全、無誤、及不中斷；用戶明示承擔使用本服務之所有風險及可能致生之任何損害。</li>
					<li>用戶在「本網站」上填寫的個人資料、物件資訊、上傳照片等行為，純屬用戶個人行為，「本網站」對其內容之真實性或完整性不負有任何責任。</li>
					<li>對於透過「本網站」銷售/出租之物件或其他服務，「本網站」對其交易過程及物件/服務本身，均不負任何瑕疵擔保責任。用戶瞭解您透過「本網站」所購得/承租之物件或服務，完全由物件或服務提供人負全責，若有任何瑕疵或擔保責任，均與「本網站」無關。</li>
					<li>「本網站」僅收取會員之廣告刊登費為用戶使用本站交易平台及本站客服在有限的範圍內協調交易等費用，不包含交易擔保費用。</li>
					<li>任何由於駭客攻擊、電腦病毒侵入或發作、因政府管制而造成的暫時性關閉等影響網路正常經營之不可抗力而造成的資料洩露、丟失、被盜用或被竄改等與「本網站」無關。</li>
					<li>由於與本網站連結的其他網站所造成之個人資料洩露及由此而導致的任何法律爭議和後果均與「本網站」無關。</li>
					<li>本網站上刊登的個人廣告、商業廣告及各種商品的促銷資訊，除非屬於本站所自行出售，其內容均係由該個人、廣告商或商品、服務提供人所為，因此產生的糾紛本網站概不負責，本網站僅係提供刊登的媒介。</li>
					<li>對於用戶透過「本網站」發佈虛假資訊、及欺騙、敲詐行為者，純屬用戶個人行為，「本網站」對因此而產生的一切糾紛不負有任何責任！特此申明！</li>
				</ul>
				</section> </article>
			</div>
		</div>
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
			Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span> </footer>
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		
	</script>
</body>
</html>