package com.hermit.iii.infraction.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hermit.iii.infraction.model.InfractionService;
import com.hermit.iii.infraction.model.InfractionVO;
import com.hermit.iii.member.model.*;
@WebServlet(value="/infractionServlet")
public class infractionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		try {
			int result = 0;
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String mission = request.getParameter("mission");
			if("insert".equals(mission)){
				InfractionService inDAO = new InfractionService();
				InfractionVO inVO = new InfractionVO();
				MemberService memDAO = new MemberService();
				int memNO = Integer.valueOf(request.getParameter("memNO").split("\t")[0]);
				inVO.setEmpNO(Integer.valueOf(request.getParameter("empNO")));
				MemberVO memberVO = new MemberVO();
				memberVO.setMemNO(memNO);
				inVO.setMemberVO(memberVO);
				inVO.setReason(request.getParameter("reason"));
				if(memDAO.infractPlus1(memNO) == 1){
					result = inDAO.insert(inVO);
				}
				if(result==1){
					memDAO.checkInfraction(memNO);
					out.print("申請成功");
					return;
				}else{
					out.print("此會員不存在或已為黑名單成員");
					return;
				}
			}else if("query".equals(mission)){
				InfractionService inService = new InfractionService();
				Set<InfractionVO> set = inService.getAll();
				ArrayList<InfractionVO> array = new ArrayList<InfractionVO>(set);
				 JSONArray json = new JSONArray(array);
				 out.println(json.toString());
				 return;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
