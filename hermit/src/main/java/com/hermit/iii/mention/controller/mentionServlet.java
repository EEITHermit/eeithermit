package com.hermit.iii.mention.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hermit.iii.reservation.model.ReservationService;

/**
 * Servlet implementation class mentionServlet
 */
@WebServlet("/mentionServlet")
public class mentionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public mentionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String mission = request.getParameter("mission");
		if("takeOver".equals(mission)){
			ReservationService res = new ReservationService();
			Integer resNO = Integer.valueOf(request.getParameter("resNO"));
			Integer empNO = Integer.valueOf(request.getParameter("empNO"));
			Integer result = res.updateStatus(resNO, empNO);
			if(result == 1){
				out.print("接案成功，即將前往預約頁面");
				return;
			}else{
				out.print("接案失敗，已有別人接管此案");
				return;
			}
		}
	}

}
