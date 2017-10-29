package com.hermit.iii.boroughs.contorller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;

import com.hermit.iii.boroughs.model.BoroughsService;
import com.hermit.iii.boroughs.model.BoroughsVO_original;

@WebServlet("/BoroughsServlet.do")
public class BoroughsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
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
			BoroughsVO_original vo = new BoroughsVO_original();
			vo = bs.getOne(Integer.valueOf(request.getParameter("boroughNO")));
			request.setAttribute("boroughVO", vo);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request,response);
		}
		if("getAllBorough".equals(action)){
			List<BoroughsVO_original> list = bs.getAll();
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request,response);
		}
		if("getAllBoroughByCity".equals(action)){
			cityNO=Integer.valueOf(request.getParameter("cityNO"));
			List<BoroughsVO_original> list=bs.getAll_cityNO(cityNO);
			List list2=new LinkedList();
			PrintWriter out=response.getWriter();
			for(int i=0;i<list.size();i++){
				Map m1=new LinkedHashMap();
				BoroughsVO_original vo=list.get(i);
				m1.put("boroughNO", vo.getBoroughNO());
				m1.put("boroughName", vo.getBoroughName());
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
