package com.hermit.iii.boroughs.contorller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hermit.iii.boroughs.model.BoroughsService;
import com.hermit.iii.boroughs.model.BoroughsVO;

@WebServlet("/BoroughsServlet")
public class BoroughsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		BoroughsService bs = new BoroughsService();
		Integer boroughNO;
		String boroughName;
		Integer cityNO;
		
		if("insertBorough".equals(action)){
			boroughName = request.getParameter("boroughsName");
			cityNO = Integer.valueOf(request.getParameter("cityNO"));
			bs.insertBoroughs(boroughName, cityNO);
			System.out.println("Insert Success");
		}
		if("updateBorough".equals(action)){
			boroughNO = Integer.valueOf(request.getParameter("boroughNO"));
			boroughName = request.getParameter("boroughsName");
			cityNO = Integer.valueOf(request.getParameter("cityNO"));
			bs.updateBoroughs(boroughNO, boroughName, cityNO);
			System.out.println("Update Success");
		}
		if("deleteBorough".equals(action)){
			bs.deleteBoroughs(Integer.valueOf(request.getParameter("boroughNO")));
		}
		if("getOneBorough".equals(action)){
			BoroughsVO vo = new BoroughsVO();
			vo = bs.getOne(Integer.valueOf(request.getParameter("boroughNO")));
			request.setAttribute("boroughVO", vo);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request,response);
		}
		if("getAllBorough".equals(action)){
			List<BoroughsVO> list = bs.getAll();
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request,response);
			
		}
	}
}
