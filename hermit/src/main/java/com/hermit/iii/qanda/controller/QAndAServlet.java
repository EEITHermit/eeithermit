package com.hermit.iii.qanda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.member.model.MemberVO;
import com.hermit.iii.qanda.model.QandAService;
import com.hermit.iii.qanda.model.QandAVO;

/**
 * Servlet implementation class QAndAServlet
 */
@WebServlet("/QAndAServlet")
public class QAndAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public QAndAServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String mission = request.getParameter("mission");
		if("insert".equals(mission)){
			//取得登入後session裡的memberNO
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("LoginOK");
			Integer memNO = memberVO.getMemNO();
			QandAService qa = new QandAService();
			Integer houseNO = Integer.valueOf(request.getParameter("houseNO"));
			String qDetail = request.getParameter("qDetail");
			//qaType => 0為客服 1為提問
			Byte qaType = Byte.valueOf(request.getParameter("type"));
			Date qTime = new Date(System.currentTimeMillis());
			//新增時，無回應員工、內容、時間
			qa.addQandA(memNO, null, houseNO, qTime, null, qaType, qDetail, null);
			response.sendRedirect("/hermit/memberbackstage/mem_back_qanda.jsp");
			return;
		}else if("update".equals(mission)){
			//取得登入後session裡的empNO
			HttpSession session = request.getSession();
			EmpVO empVO = (EmpVO) session.getAttribute("empLoginOK");
			Integer empNO = empVO.getEmpNO();
			Integer qaNO = Integer.valueOf(request.getParameter("qaNO"));
			String aDetail = request.getParameter("aDetail");
			Date qTime = new Date(System.currentTimeMillis());
			QandAService qa = new QandAService();
			QandAVO qaVO = qa.getOneQandA(qaNO);
			qa.updateQandA(qaNO, qaVO.getMemberVO().getMemNO(), empNO, qaVO.getHouseVO().getHouseNO()
					,qaVO.getqTime(), qTime, qaVO.getQaType(), qaVO.getqDetail(), aDetail);
			response.sendRedirect("/hermit/mention/mentionIndex.jsp");
			return;
		}else if("question".equals(mission)){
			Integer memNO = Integer.valueOf(request.getParameter("member"));
			Integer houseNO = Integer.valueOf(request.getParameter("house"));
			String question = request.getParameter("question");
			Date date = new Date(System.currentTimeMillis());
			QandAService qa = new QandAService();
			qa.addQandA(memNO, null, houseNO, date, null, (byte)1, question, null);
			out.println("提問成功，工作人員將會盡快回答，請去Q&A區確認回復");
			return;
		}
		
	}

}
