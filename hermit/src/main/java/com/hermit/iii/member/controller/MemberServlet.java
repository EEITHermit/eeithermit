package com.hermit.iii.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hermit.iii.member.model.MemberDAO;
import com.hermit.iii.member.model.MemberService;
import com.hermit.iii.member.model.MemberVO;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer MemberNo = Integer.valueOf(request.getParameter("member"));

		MemberService dao = new MemberService();
		MemberVO memberVO = dao.findByPrimaryKey(MemberNo);
		try {
			request.setAttribute("memNO", memberVO.getMemNO());
			request.setAttribute("memTel", memberVO.getMemTel());
			request.setAttribute("memAccount", memberVO.getMemAccount());
			request.setAttribute("memPwd", memberVO.getMemPwd());
			request.setAttribute("memName", memberVO.getMemName());
			request.setAttribute("memGender", memberVO.getMemGender());
			request.setAttribute("memEmail", memberVO.getMemEmail());
			request.setAttribute("memRegister", memberVO.getMemRegister());
			request.setAttribute("memStatus", memberVO.getMemStatus());
			request.setAttribute("memInfract", memberVO.getMemInfract());
			request.setAttribute("memImage", memberVO.getMemImage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/Member/member.jsp");
		rd.forward(request, response);

		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mission = request.getParameter("action");

		Map<String, String> errorMsg = new HashMap<String, String>();

		if ("update".equals(mission)) {
			MemberDAO dao = new MemberDAO();

			MemberVO memberVO = new MemberVO();
			String memTel = request.getParameter("memTel");
//			if (memTel.trim().length() == 0) {
//				errorMsg.put("memTel", "請輸入電話");
//			} 
//			else if (memTel != "memTel") {
//				errorMsg.put("memTel", "更改");    //這邊絕對有問題，要問漢勳
//				System.out.println(memTel);
//			}
			String memPwd = request.getParameter("memPwd");
			String memPwdReg = "^[(a-zA-Z0-9)]{2,10}$";
			if (memPwd.trim().length() == 0) {
				errorMsg.put("memPwd", "請輸入密碼");
				 }else if(!memPwd.trim().matches(memPwdReg)){
					 errorMsg.put("memPwd", "格式不符合");
				 }
			String memName = request.getParameter("memName");
			String memNameReg="^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
			if(memName.trim().length()==0){
				errorMsg.put("memName", "請輸入姓名");
			}else if(!memName.trim().matches(memNameReg)){
				errorMsg.put("memName", "格式不符合");
			}
			String memEmail = request.getParameter("memEmail");
			String memEmailReg="^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
			if(memEmail.trim().length()==0){
				errorMsg.put("memEmail", "請輸入信箱");
			}else if(!memEmail.trim().matches(memEmailReg)){
				errorMsg.put("memEmail", "格式不符合");
			}
				if (!errorMsg.isEmpty()) {
					request.setAttribute("MsgMap", errorMsg);
					RequestDispatcher rd = request.getRequestDispatcher("/Member/member.jsp");
					rd.forward(request, response);
					return;
				}
				MemberService memSvc = new MemberService();

				Integer memNO = Integer.valueOf(request.getParameter("memNO"));
				// String memTel=request.getParameter("memTel");
				String memAccount = request.getParameter("memAccount");
				// String memPwd=request.getParameter("memPwd");
//				String memName = request.getParameter("memName");
				String memGender = request.getParameter("memGender");
//				String memEmail = request.getParameter("memEmail");
				String memStatus = request.getParameter("memStatus");
				
				Integer memInfract = Integer.valueOf(request.getParameter("memInfract"));
				
				String memImage = request.getParameter("memImage");
				System.out.println(memImage);
				memberVO = memSvc.update(memNO, memTel, memAccount, memPwd, memName, memGender, memEmail, memStatus,
						memInfract,memImage);

				request.setAttribute("memberVO", memberVO);
				request.setAttribute("Msg", "修改成功");
				RequestDispatcher rd = request.getRequestDispatcher("/Member/index.jsp");
				rd.forward(request, response);
				return;
			}

			// request.setCharacterEncoding("UTF-8");
			// String mission = request.getParameter("mission");
			// Map<String, String> errorMsg = new HashMap<String, String>();
			// if ("update".equals(mission)) {
			// MemberDAO dao = new MemberDAO();
			//
			// MemberVO memberVO = new MemberVO();
			// String memTel = request.getParameter("memTel");
			// if (memTel.trim().length() == 0) {
			// errorMsg.put("memTel", "請輸入電話");
			// }
			// if (!errorMsg.isEmpty()) {
			// request.setAttribute("MsgMap", errorMsg);
			// RequestDispatcher rd =
			// request.getRequestDispatcher("member.jsp");
			// rd.forward(request, response);
			// return;
			// }
			// try {
			// memberVO.setMemNO(Integer.valueOf(request.getParameter("memNO")));
			// memberVO.setMemTel(request.getParameter("memTel"));
			// memberVO.setMemAccount(request.getParameter("memAccount"));
			// memberVO.setMemPwd(request.getParameter("memPwd"));
			// memberVO.setMemName(request.getParameter("memName"));
			// memberVO.setMemGender(request.getParameter("memGender"));
			// memberVO.setMemEmail(request.getParameter("memEmail"));
			// memberVO.setMemRegister(Date.valueOf(request.getParameter("memRegister")));
			// memberVO.setMemStatus(request.getParameter("memStatus"));
			// memberVO.setMemInfract(Integer.valueOf(request.getParameter("memInfract")));
			//
			//
			// dao.update(memberVO, null, 0);
			//
			// request.setAttribute("Msg", "修改成功");
			// RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			// rd.forward(request, response);
			//
			// return;
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// }

		}

	}

