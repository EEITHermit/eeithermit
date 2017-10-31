<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/w3.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/component.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/demo.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css"/>
<link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
<link href="http://fonts.googleapis.com/earlyaccess/cwtexyen.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/flashcanvas.js"></script>
<script src="<%=request.getContextPath()%>/js/jSignature.min.js"></script>
<script src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/component.js"></script>

</head>
<body>
<div class="container">
			<!-- Top Navigation -->
			<div class="codrops-top clearfix">
				<a class="codrops-icon codrops-icon-prev" href="http://tympanus.net/Development/WobblySlideshowEffect/"><span>Previous Demo</span></a>
				<span class="right"><a class="codrops-icon codrops-icon-drop" href="http://tympanus.net/codrops/?p=20603"><span>Back to the Codrops Article</span></a></span>
			</div>
			<div class="content">
				<header class="codrops-header">
					<h1>Image Resizing &amp; Cropping <br /><span>with Canvas</span></h1>
				</header>
				<div class="component">
					<div class="overlay">
						<div class="overlay-inner">
						</div>
					</div>
					<!-- This image must be on the same domain as the demo or it will not work on a local file system -->
					<!-- http://en.wikipedia.org/wiki/Cross-origin_resource_sharing -->
					<img class="resize-image" src="img/image.jpg" alt="image for resizing">
					<button class="btn-crop js-crop">Crop<img class="icon-crop" src="img/crop.svg"></button>
				</div>
				<div class="a-tip">
					<p><strong>Hint:</strong> hold <span>SHIFT</span> while resizing to keep the original aspect ratio.</p>
				</div>
				<p>Image by <a href="https://www.flickr.com/photos/janoma/4492768410">Alejandro Mallea</a></p>
			</div><!-- /content -->
			<!-- Related demos -->
			<section class="related">
				<p>If you enjoyed this demo you might also like:</p>
				<a href="http://tympanus.net/Development/AnimatedHeaderBackgrounds/">
					<img src="img/related/AnimatedHeaderBackgrounds.png" />
					<h3>Animated Header Backgrounds</h3>
				</a>
				<a href="http://tympanus.net/Tutorials/TexturedText/">
					<img src="img/related/TexturedText.jpg" />
					<h3>Techniques for Creating Textured Text</h3>
				</a>
			</section>
		</div> <!-- /container -->
</body>
</html>