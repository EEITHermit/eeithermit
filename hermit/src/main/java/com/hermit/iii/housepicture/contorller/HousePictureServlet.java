package com.hermit.iii.housepicture.contorller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.hermit.iii.util.ConvertToBase64;

@WebServlet("/HousePictureServlet")
public class HousePictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		ConvertToBase64 ctb = new ConvertToBase64(); 
		if("insertHousePicture".equals(action)){
			Collection<Part> parts = request.getParts();
			for(Part item : request.getParts()){
				String strBase64 = ctb.encode(item);
				if(strBase64 != null){
					System.out.println(strBase64);
				}
			}
		}
		
		
	}

}
