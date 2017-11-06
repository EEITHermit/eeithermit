<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>imgAreaSelect測試</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/imgareaselect-default.css" />
</head>
<body>

	<label for="fileImg" class="label-style">頭像設定&nbsp;:&nbsp;</label>
	<input type="file" id="fileImg" name="fileImg" value="上傳圖片">
	<div>
		<canvas id="layoutImg"></canvas>
	</div>
	<div>
		<canvas id="resultImg" style="display:none"></canvas>
	</div>
	<img id="test">
	<button type="button" title="OK" id="buttonOK">DONE</button>
	
	
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/jquery.imgareaselect.min.js"></script>
	<script>
		// 圖檔操作
		var canvas_width_max = 300;
		var canvas_height_max = 300;

		$('#fileImg').change(
			function(e) {

				var fimg = e.target.files[0];

				if (!fimg.type.match('image.*')) {
					alert('Whoops! That is not an image.');
					return;
				}

				var img = new Image;
				img.src = URL.createObjectURL(fimg);

				var ctx = document.getElementById('layoutImg').getContext("2d");

				img.onload = function() {
					var canvas_width = this.width;
					var canvas_height = this.height;

					if (this.width > this.height) {
						if (this.width > canvas_width_max) {
							canvas_width = canvas_width_max;
							canvas_height = canvas_width_max / this.width
									* this.height;
						}
					} else {
						if (this.height > canvas_height_max) {
							canvas_width = canvas_height_max / this.height
									* this.width;
							canvas_height = canvas_height_max;
						}
					}
					$('#layoutImg').attr('width', canvas_width);
					$('#layoutImg').attr('height', canvas_height);

					ctx.drawImage(img, 0, 0, this.width, this.height, 0, 0,
							canvas_width, canvas_height);
				}

				$('#layoutImg').imgAreaSelect( {
					handles : true,
					onSelectChange : function(img, selection) {
						$('#buttonOK').click(function() {
							var cty = document.getElementById('resultImg').getContext("2d");
							$('#resultImg').attr('width', selection.x2);
							$('#resultImg').attr('height', selection.y2);
							cty.drawImage(img, selection.x1, selection.y1, selection.width, selection.height, 0, 0, selection.width, selection.height);

							var resultCanvas = document.getElementById('resultImg');
							var log = resultCanvas.toDataURL();
							console.log(log);
							$('#test').attr('src', log);
						});
					}
				});
			});
	</script>
</body>
</html>