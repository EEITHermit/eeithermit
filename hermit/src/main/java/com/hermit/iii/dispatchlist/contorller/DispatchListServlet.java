package com.hermit.iii.dispatchlist.contorller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.hermit.iii.dispatchlist.model.DispatchListService;
import com.hermit.iii.dispatchlist.model.DispatchListVO;
import com.hermit.iii.dispatchlist.model.DispatchListVO_orignal;

@WebServlet("/Dispatch")
public class DispatchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		DispatchListService dls = null;
		Integer dlno =null;
		Integer dempno = null;
		Integer aempno = null;
		Integer qano = null;
		Date dlstime = null; 
		Date dletime = null;
		String elesign = null;
		String dlnote = null;
		
		if("InsertDispatchList".equals(action)){
			dls = new DispatchListService();
			dempno = Integer.valueOf(request.getParameter("dempNO"));
			aempno = Integer.valueOf(request.getParameter("aempNO"));
			qano = Integer.valueOf(request.getParameter("qaNO"));
			if(request.getParameter("dlNote") != null){
				dlnote =request.getParameter("dlNote"); 
			}else{
				dlnote = "";
			}
			dlstime = java.sql.Date.valueOf(LocalDate.now());
			
			dls.addDispatchList(dempno, aempno, qano, dlstime,dlnote);
			response.sendRedirect(request.getContextPath()+"/DispatchList/DispatchList.jsp");
		}
		
		if("updateDispatchList".equals(action)){
			dls = new DispatchListService();
			dlno = Integer.valueOf(request.getParameter("dlNO"));
			dempno = Integer.valueOf(request.getParameter("dempNO"));
			aempno = Integer.valueOf(request.getParameter("aempNO"));
			qano = Integer.valueOf(request.getParameter("qaNO"));
			dlstime = java.sql.Date.valueOf(request.getParameter("dlStime"));
			dletime = java.sql.Date.valueOf(request.getParameter("dlEtime"));
			elesign = request.getParameter("elesign");
			dlnote = request.getParameter("dlNote");
			dls.updateDispatchList(dlno, dempno, aempno, qano, dlstime, dletime, elesign, dlnote);
			RequestDispatcher rd = request.getRequestDispatcher("/DispatchList/DispatchList.jsp");
			rd.forward(request, response);
		}
		if("endDispatchList".equals(action)){
			dls = new DispatchListService();
			
			dlno = Integer.valueOf(request.getParameter("dlNO"));
			dempno = Integer.valueOf(request.getParameter("dempNO"));
			aempno = Integer.valueOf(request.getParameter("aempNO"));
			qano = Integer.valueOf(request.getParameter("qaNO"));
			dlstime = java.sql.Date.valueOf(request.getParameter("dlStime"));
			dletime = java.sql.Date.valueOf(LocalDate.now());
			elesign = request.getParameter("elesign");
			dlnote = request.getParameter("dlnote");
			dls.updateDispatchList(dlno, dempno, aempno, qano, dlstime, dletime, elesign, dlnote);
			RequestDispatcher rd = request.getRequestDispatcher("/DispatchList/DispatchList.jsp");
			rd.forward(request, response);
		}
		
		if("deleteDispatchList".equals(action)){
			dls = new DispatchListService();
			dls.deleteDispatchList(Integer.valueOf(request.getParameter("dlno")));
		}
		
		if("getOneDispatchList".equals(action)){
			DispatchListVO dlVO = new DispatchListVO();
			dls = new DispatchListService();
			dlVO = dls.getOneDispatchList(Integer.valueOf(request.getParameter("dlno")));
			request.setAttribute("dlVO",dlVO);
			RequestDispatcher rd = request.getRequestDispatcher("/DispatchList/updateDispatchList.jsp");
			rd.forward(request,response);
		}
		if("queryDispatchList".equals(action)){
			DispatchListVO dlVO = new DispatchListVO();
			dls = new DispatchListService();
			dlVO = dls.getOneDispatchList(Integer.valueOf(request.getParameter("dlno")));
			request.setAttribute("dlVO",dlVO);
			RequestDispatcher rd = request.getRequestDispatcher("/DispatchList/queryDispatchList.jsp");
			rd.forward(request,response);
		}

		if("getAllDispatchList".equals(action)){
			dls = new DispatchListService();
			List<DispatchListVO> list = dls.getAllDispatchList();
			request.setAttribute("list",list);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request,response);
			
		}
		
		if("getAllDispatchListForJson".equals(action)){
			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			dls = new DispatchListService();
			String stringjson = dls.getAllForJson();
			System.out.println(stringjson);
			out.println(stringjson);
			out.flush();
			out.close();
		}
		
	}
}
