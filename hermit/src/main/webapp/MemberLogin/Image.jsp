<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Font"%>
<%@page import="java.awt.Graphics"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.util.Random"%>
<%@page import="java.awt.Color"%>
<%@ page language="java" contentType="image/JPEG; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%!//獲取隨機顏色
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);

		return new Color(r, g, b);
	}%>

<%
	//設置頁面不緩存
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

	//在內存中創建圖片
	int width = 140;
	int height = 40;
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	//獲取圖片上下文
	Graphics g = image.getGraphics();
	//隨機類
	Random random = new Random();
	//背景
	g.setColor(getRandColor(200, 250));
	g.fillRect(0, 0, width, height);
	//字體
	g.setFont(new Font("Time New Roman", Font.PLAIN, 35));
	//干擾線
	g.setColor(getRandColor(160, 200));
	for (int i = 0; i < 155; i++) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int x1 = random.nextInt(12);
		int y1 = random.nextInt(12);
		g.drawLine(x, y, x + x1, y + y1);
	}

	//隨機產生4位驗證碼
	String[] codes = { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K",
			"L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "", "", "a", "b", "c", "d",
			"e", "f", "g", "h", "i", "j", "k", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
			"z" };
	String code = "";
	for (int i = 0; i < 6; i++) {
		String str = codes[random.nextInt(codes.length)];
		code += str;
		//顯示到圖片中
		g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
		//調用函數出來的顏色相同，可能是因為種子太接近，所以只能直接生成
		g.drawString(str, i*(width/6),height-(height/3));
	}
	//生成的驗證碼存入session
	session.setAttribute("code", code);
	//生成圖片
	g.dispose();
	//輸出到頁面
	ImageIO.write(image, "JPEG", response.getOutputStream());
	//運行時不會出現異常
	response.getOutputStream().flush();
	response.getOutputStream().close();
	response.flushBuffer();
	out.clear();
	out = pageContext.pushBody();
%>