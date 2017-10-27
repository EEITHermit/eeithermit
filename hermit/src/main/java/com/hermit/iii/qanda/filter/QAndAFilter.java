package com.hermit.iii.qanda.filter;

import java.io.IOException;
import java.util.ArrayList;

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

import com.hermit.iii.qanda.model.QandAService;
import com.hermit.iii.qanda.model.QandAVO;

@WebFilter(value="/QAndA/*")
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
		//取得登入後session裡的memberNO
//		HttpSession session = req.getSession();
//		Integer memberNO = (Integer) session.getAttribute("member");
		//memNO假資料
		Integer memNO = 40001;
		QandAService qaService = new QandAService();
		QandAVO qaVO = qaService.getOneQandA(memNO);
		array = qaService.getAllByMemberNO(memNO);
		request.setAttribute("array",array);
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
