package com.hermit.iii.housetype.contorller;

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

import com.hermit.iii.housetype.model.HouseTypeService;
import com.hermit.iii.housetype.model.HouseTypeVO;
import com.hermit.iii.housetype.model.HouseTypeVO_original;

/**
 * Servlet implementation class HouseTypeServlet
 */
@WebServlet("/HouseTypeServlet.do")
public class HouseTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		HouseTypeService svc=new HouseTypeService();
		HouseTypeVO vo;
		
		Integer typeNO=null;
		String hType=null;
		
		if("getAllType".equals(action)){
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			List<HouseTypeVO> list = svc.getAllHouseType();
			List list2=new LinkedList();
			PrintWriter out=response.getWriter();
			
			for(int i=0;i<list.size();i++){
				Map m1=new LinkedHashMap();
				vo=list.get(i);
				m1.put("typeNO", vo.getTypeNO());
				m1.put("hType", vo.gethType());
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
