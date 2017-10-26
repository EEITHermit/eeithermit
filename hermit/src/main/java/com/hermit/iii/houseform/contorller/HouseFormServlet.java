package com.hermit.iii.houseform.contorller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;

import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.houseform.model.HouseFormService;
import com.hermit.iii.houseform.model.HouseFormVO;

@WebServlet("/HouseFormServlet.do")
public class HouseFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HouseFormService svc = new HouseFormService();
		HouseFormVO vo;

		Integer formNO = null;
		String hForm = null;

		if ("getAllForm".equals(action)) {
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			List<HouseFormVO> list = svc.getAllHouseForm();
			List list2=new LinkedList();
			PrintWriter out=response.getWriter();
			
			for(int i=0;i<list.size();i++){
				Map m1=new LinkedHashMap();
				vo=list.get(i);
				m1.put("formNO", vo.getFormNO());
				m1.put("hForm", vo.gethForm());
				list2.add(m1);
			}
			Map m2=new LinkedHashMap();
			m2.put("list", list2);
			String str=JSONValue.toJSONString(m2);
			out.println(str);
			out.flush();
			out.close();
			
		}
	}
}
