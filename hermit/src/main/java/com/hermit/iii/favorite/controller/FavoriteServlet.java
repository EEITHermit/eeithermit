package com.hermit.iii.favorite.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.hermit.iii.favorite.model.*;

@WebServlet("/FavoriteServlet")
public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		if ("favorite_getAJAX_Action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			try {
				/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
				Integer memNO = new Integer(request.getParameter("memNO").trim());
				/**** 2.開始查詢資料 ****/
				FavoriteService favoriteSvc = new FavoriteService();
				String jsonString = favoriteSvc.accountFavoriteAJAX(memNO);
				/**** 3.查詢完成 ****/
				out.write(jsonString);
			} catch (Exception e) {
				errorMsgMap.put("Exception", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/memberbackstage/mem_back_favorite.jsp");
				failureView.forward(request, response);
			}
		}

		if ("house_checkAJAX_Action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			Integer houseNO = null;

			try {
				/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
				Integer memNO = new Integer(request.getParameter("memNO").trim());
				houseNO = new Integer(request.getParameter("houseNO").trim());
				/**** 2.開始查詢資料 ****/
				FavoriteService favoriteSvc = new FavoriteService();
				Integer favNO = favoriteSvc.checkFavoriteAJAX(memNO, houseNO);
				/**** 3.查詢完成 ****/
				out.write(favNO);
			} catch (Exception e) {
				errorMsgMap.put("Exception", e.getMessage());
				if (houseNO == null) {
					RequestDispatcher failureView = request.getRequestDispatcher("/index.jsp");
					failureView.forward(request, response);
				} else {
					RequestDispatcher failureView = request.getRequestDispatcher("/HousePage.jsp?NO=" + houseNO);
					failureView.forward(request, response);
				}
			}
		}

		if ("favorite_delete_Action".equals(action)) {
			// 準備存放錯誤訊息的Map物件
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			try {
				/**** 1.接收請求參數 - 輸入格式的錯誤處理 ****/
				Integer favNO = new Integer(request.getParameter("favNO").trim());
				/**** 2.開始刪除資料 ****/
				FavoriteService favoriteSvc = new FavoriteService();
				favoriteSvc.deleteFavorite(favNO);
				/**** 3.刪除完成 ****/
				response.sendRedirect("memberbackstage/mem_back_favorite.jsp");
			} catch (Exception e) {
				errorMsgMap.put("Exception", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/memberbackstage/mem_back_favorite.jsp");
				failureView.forward(request, response);
			}
		}
	}
}