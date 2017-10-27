package com.hermit.iii.admanager.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
public class ADManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// public ADManagerServlet() {
	// super();
	// }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		ADManagerService ads = null;
		Integer adNo = null;
		String adImage = req.getParameter("adImage");
		System.out.println("adImage:" + adImage);
		String adLink = null;
		String adMessage = null;
		Date adTimeStart = null;
		Date adTimeEnd = null;
		Boolean adStatus = null;
		Integer adBrowse = null;
		Integer adModify = null;

		if ("InsertADManager".equals(action)) {
			ads = new ADManagerService();
			adImage = String.valueOf(req.getParameter("adImage"));
			adLink = String.valueOf(req.getParameter("adLink"));
			adMessage = String.valueOf(req.getParameter("adMessage"));
			adTimeStart = java.sql.Date.valueOf(req.getParameter("adTimeStart").toString());
			adTimeEnd = java.sql.Date.valueOf(req.getParameter("adTimeEnd").toString());
			adStatus = Boolean.valueOf(req.getParameter("adStatus"));
			adBrowse = Integer.valueOf(0);
			adModify = Integer.valueOf(req.getParameter("adModify"));

			ads.insertADManager(adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify);
			System.out.println("Insert OK");
		}

		if ("updateADManager".equals(action)) {
			ads = new ADManagerService();
			adNo = Integer.valueOf(req.getParameter("adNo"));
			adImage = req.getParameter("adImage");
			adLink = String.valueOf(req.getParameter("adLink"));
			adMessage = String.valueOf(req.getParameter("adMessage"));
			adTimeStart = java.sql.Date.valueOf("adTimeStart");
			adTimeEnd = java.sql.Date.valueOf("adTimeEnd");
			adStatus = Boolean.valueOf(true);
			adBrowse = Integer.valueOf(req.getParameter("adBrowse"));
			adModify = Integer.valueOf(req.getParameter("adModify"));
			ads.updateADManager(adNo, adImage, adLink, adMessage, adTimeStart, adTimeEnd, adStatus, adBrowse, adModify);
			System.out.println("Update OK");
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
		}

		if ("deleteADManager".equals(action)) {
			ads = new ADManagerService();
			ads.deleteADManager(Integer.valueOf(req.getParameter("adNo")));
			resp.sendRedirect("back-adindex.jsp");
			// System.out.println("delete OK");
		}

		if ("getOneADManager".equals(action)) {
			System.out.println("Get one OK");
			ADManagerVO adVO = new ADManagerVO();
			ads = new ADManagerService();
			adVO = ads.getOneADManager(Integer.valueOf(req.getParameter("adNo")));
			req.setAttribute("adVO", adVO);
			RequestDispatcher rd = req.getRequestDispatcher("back-adindex.jsp");
			rd.forward(req, resp);
		}
		if ("getAllADManager".equals(action)) {
			ads = new ADManagerService();
			List<ADManagerVO> list = ads.getAllADManager();
			req.setAttribute("list", list);
			// System.out.println("Get All OK");
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
		}
		if ("getAllADManagerForJson".equals(action)) {
			resp.setHeader("content-type", "text/html;charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			ads = new ADManagerService();
			String stringjson = ads.getAllForJson();
			out.println(stringjson);
			out.flush();
			out.close();
			System.out.println("Get All For JSON");
		}

	}

}
