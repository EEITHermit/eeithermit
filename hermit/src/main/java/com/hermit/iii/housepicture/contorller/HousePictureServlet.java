package com.hermit.iii.housepicture.contorller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.hermit.iii.housepicture.model.HousePictureService;
import com.hermit.iii.housepicture.model.HousePictureVO;
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
		HousePictureService svc=new HousePictureService();
		String strBase64=null;
		int count=0;
		Integer houseNO =null;
		if("insertHousePicture".equals(action)){
			houseNO = Integer.valueOf(request.getParameter("houseNO"));
			Collection<Part> parts = request.getParts(); 
			for(Part item : request.getParts()){
				strBase64 = ctb.encode(item);
				if(strBase64 != null){

//					System.out.println(strBase64);
					HousePictureVO picVO=new HousePictureVO();
					System.out.println(request.getParameter("houseNO"));
					svc.insertHousePicture(strBase64, houseNO);
				}
			}
			response.sendRedirect("/hermit/housepicture/InsertHousePicture.jsp");
		}
		if("deleteHousePic".equals(action)){
			Integer housePictureNO=Integer.valueOf(request.getParameter("housePictureNO"));
			svc.deletePicture(housePictureNO);
		}
		if("updateHousPic".equals(action)){
			Integer housePictureNO=Integer.valueOf(request.getParameter("housePictureNO"));
			String hPicture=request.getParameter("hPicture");
			houseNO = Integer.valueOf(request.getParameter("houseNO"));
			svc.updateHousePicture(housePictureNO, hPicture, houseNO);
		}
	}
}
