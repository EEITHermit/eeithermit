package com.hermit.iii.admanager.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hermit.iii.admanager.model.ADManagerService;
import com.hermit.iii.admanager.model.ADManagerVO;






@WebServlet("/ADManagerServlet")
public class ADManagerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");		
		ADManagerService ads = null;
		Integer adNo = null;
		String adImage = null;
		String adLink = null;
		String adMessage = null;
		Date adTimeStart = null;
		Date adTimeEnd = null;
		Boolean adStatus = null;
		Integer adBrowse = null;
		Integer adModify = null;
		
		if("InsertADManager".equals(action)){
			ads = new ADManagerService();
					    
		    adImage = String.valueOf(req.getParameter("adImage"));
			adLink = String.valueOf(req.getParameter("adLink"));
			adMessage = String.valueOf(req.getParameter("adMessage"));
			adTimeStart = java.sql.Date.valueOf(req.getParameter("adTimeStart"));
			adTimeEnd = java.sql.Date.valueOf(req.getParameter("adTimeEnd"));
			adStatus = Boolean.valueOf(true);
			adBrowse = Integer.valueOf(req.getParameter("adBrowse"));
			adModify = Integer.valueOf(req.getParameter("adModify"));
			
			ads.addADManager(adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify);
			System.out.println("Insert OK");
		}
		
		if("updateADManager".equals(action)){
			ads = new ADManagerService();
					    
		    adImage = String.valueOf(req.getParameter("adImage"));
			adLink = String.valueOf(req.getParameter("adLink"));
			adMessage = String.valueOf(req.getParameter("adMessage"));
			adTimeStart = java.sql.Date.valueOf(req.getParameter("adTimeStart"));
			adTimeEnd = java.sql.Date.valueOf(req.getParameter("adTimeEnd"));
			adStatus = Boolean.valueOf(true);
			adBrowse = Integer.valueOf(req.getParameter("adBrowse"));
			adModify = Integer.valueOf(req.getParameter("adModify"));
			ads.updateADManager(adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify);
			System.out.println("Update OK");
			RequestDispatcher rd = req.getRequestDispatcher("back-adindex.jsp");
			rd.forward(req, resp);
		}
		
		if("deleteADManager".equals(action)){
			ads = new ADManagerService();
			ads.deleteADManager(Integer.valueOf(req.getParameter("adNo")));
			System.out.println("delete OK");
		}
			
		if("getOneADManager".equals(action)){
			System.out.println("Get one OK");
			ADManagerVO adVO = new ADManagerVO();
			ads = new ADManagerService();
			adVO = ads.getOneADManager(Integer.valueOf(req.getParameter("adNo")));
			req.setAttribute("adVO",adVO);
			RequestDispatcher rd = req.getRequestDispatcher("back-adindex.jsp");
			rd.forward(req,resp);
		}
		if("getAllADManager".equals(action)){
			ADManagerVO adVO = new ADManagerVO();
			ads = new ADManagerService();
			List<ADManagerVO> list = ads.getAllADManager();
			System.out.println("Get All OK");
			RequestDispatcher rd = req.getRequestDispatcher("back-adindex.jsp");
			rd.forward(req,resp);
		}
		
		}
		
	}
	


