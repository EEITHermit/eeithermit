package com.hermit.iii.city.contorller;

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
import com.hermit.iii.city.model.CityService;
import com.hermit.iii.city.model.CityVO_original;
import java.io.IOException;


@WebServlet("/CityServlet.do")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");
		CityService svc=new CityService();
		CityVO_original vo;
		
		
		if("getAllCity".equals(action)){
			List<CityVO_original> list=svc.getAllCity();
			List list2=new LinkedList();
			PrintWriter out=response.getWriter();
			for(int i=0;i<list.size();i++){
				Map m1=new LinkedHashMap();
				vo=list.get(i);
				m1.put("cityNO", vo.getCityNO());
				m1.put("cityName", vo.getCityName());
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
