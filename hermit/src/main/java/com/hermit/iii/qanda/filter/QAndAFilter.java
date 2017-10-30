package com.hermit.iii.qanda.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.lease.model.LeaseService;
import com.hermit.iii.mention.model.MentionService;
import com.hermit.iii.qanda.model.QandAService;
import com.hermit.iii.qanda.model.QandAVO;

//@WebFilter(value="/QAndA/*")
public class QAndAFilter implements Filter {

   
    public QAndAFilter() {
    }
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = null;
		HttpServletResponse resp = null;
		ArrayList<QandAVO> array ;
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse){
			req = (HttpServletRequest) request;
			resp = (HttpServletResponse) response;
		}else{
			return;
		}
		String servletPath = (req.getServletPath().split("/")[2]).trim();
		if("QAndA.jsp".equals(servletPath) || "mem_back_qanda.jsp".equals(servletPath)){
			//取得登入後session裡的memberNO
//			HttpSession session = req.getSession();
//			Integer memberNO = (Integer) session.getAttribute("member");
			//memNO假資料
			Integer memNO = 40001;
			QandAService qaService = new QandAService();
			QandAVO qaVO = qaService.getOneQandA(memNO);
			array = qaService.getAllByMemberNO(memNO);
			//取得會員所擁有房屋資訊
			ArrayList<HouseVO> houseArray = new ArrayList<HouseVO>();
			LeaseService leaseService = new LeaseService();
			houseArray = leaseService.findHouseBymemNO(memNO);
			request.setAttribute("array",array);
			request.setAttribute("houseArray", houseArray);
			chain.doFilter(request, response);
		}else if("AAndQ.jsp".equals(servletPath)){
			//取得登入後session裡的empNO
//			HttpSession session = req.getSession();
//			Integer empNO = (Integer) session.getAttribute("emp");
			//empNO假資料
			Integer empNO = 30001;
			//取得boroughNO
			MentionService mention = new MentionService();
			QandAService qaService = new QandAService();
			ArrayList<Integer> boroughNO = mention.getBoroughNOByEmpNO(empNO);
			array = new ArrayList<QandAVO>();
			for(Integer boroNO :boroughNO){
				array.addAll(qaService.getAllByBoroughNO(boroNO));
			}
			request.setAttribute("array",array);
			chain.doFilter(request, response);
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
