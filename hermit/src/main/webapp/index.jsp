<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>赫米特租屋管理</title>
<jsp:include page="/fragment/index_include.jsp" />
</head>
<body id="body">

	<footer class="w3-bottom w3-black container-fluid text-center">
	<div>
		<ul class="nav nav-pills w3-centered " style="display: flex;font-size:13px;justify-content: center; position: static">
		  <li role="presentation"><a href="<%=request.getContextPath()%>/index.jsp">關於我們</a></li>
		  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_duty_page.jsp">免責聲明</a></li>
		  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_privacy_page.jsp">服務條款</a></li>
		  <li role="presentation"><a href="<%=request.getContextPath()%>/register/law_service_page.jsp">隱私權聲明</a></li>
		</ul>
	</div>
    <span class="text-center"><p style="font-size:10px">赫米特開發團隊  Copyright © 2017-2017 by Hermit Group EEIT97 All Rights reserved</p></span>
	</div>
</footer>

<script>
	
</script>
</body>
</html>