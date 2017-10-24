package com.hermit.iii.mention.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hermit.iii.mention.model.MentionService;
import com.hermit.iii.reservation.model.ReservationService;
import com.hermit.iii.reservation.model.ReservationVO;

@WebFilter(urlPatterns="/mention/mention.jsp")
public class MentionFilter implements Filter {

    public MentionFilter() {
 
    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		MentionService mention = new MentionService();
		ReservationService reservation = new ReservationService();
		HttpServletRequest req = null;
		HttpServletResponse resp = null;
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse){
			req = (HttpServletRequest)request;
			resp = (HttpServletResponse)response;
		}else{
			return;
		}
		//暫定從session內取得員工編號
//		HttpSession session = req.getSession();	
//		Integer empNO = Integer.valueOf((String)session.getAttribute("empNO"));
		//測試用假資料 員工40001
		Integer empNO = 30001;
		Integer boroughNO = mention.getBoroughNOByEmpNO(empNO);
		ArrayList<ReservationVO> resArray = null;
		if(boroughNO != null){
		resArray = reservation.selectByArea(boroughNO);
		}
		request.setAttribute("resArray", resArray);
		request.setAttribute("resSize",resArray.size());
		chain.doFilter(request, response);
		return;	
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
