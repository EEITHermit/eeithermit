package com.hermit.iii.businTeam.contorller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hermit.iii.businTeam.model.BusinTeamService;
import com.hermit.iii.businTeam.model.BusinTeamVO;
import com.hermit.iii.emp.model.EmpVO;

/**
 * Servlet implementation class businTeam
 */
@WebServlet("/businTeam")
public class businTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");	
		
		if("insertBusinTeam".equals(action)){
			BusinTeamService bts = new BusinTeamService();
			BusinTeamVO btVO = new BusinTeamVO();
			EmpVO evo = new EmpVO();
			evo.setEmpNO(Integer.valueOf(request.getParameter("manager")));
			btVO.setBusinName(request.getParameter("businName"));
			btVO.setManagerVO(evo);
			bts.insert(btVO);
			RequestDispatcher rd = request.getRequestDispatcher("/BusinTeam/BusinTeamManage.jsp");
			rd.forward(request, response);
			
		}
	}
}
