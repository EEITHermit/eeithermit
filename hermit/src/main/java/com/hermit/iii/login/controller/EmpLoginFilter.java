package com.hermit.iii.login.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hermit.iii.emp.model.EmpVO;

public class EmpLoginFilter implements Filter {

	Collection<String> url = new ArrayList<String>();
	String servletPath;
	String contextPath;
	String requestURI;

	public void init(FilterConfig fConfig) throws ServletException {
		Enumeration<String> e = fConfig.getInitParameterNames();
		while (e.hasMoreElements()) {
			String path = e.nextElement();
			url.add(fConfig.getInitParameter(path));
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean isRequestedSessionIdValid = false;
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;

			servletPath = req.getServletPath();
			contextPath = req.getContextPath();
			requestURI = req.getRequestURI();
			isRequestedSessionIdValid = req.isRequestedSessionIdValid();

			if (checkLogin(req)) { // 需要登入，已經登入
				chain.doFilter(request, response);
			} else { // 需要登入，尚未登入
				HttpSession session = req.getSession();
				session.setAttribute("requestURI", requestURI);
				if (!isRequestedSessionIdValid) {
					session.setAttribute("timeOut", "使用逾時，請重新登入");
				}
				resp.sendRedirect(contextPath + "/back_index_page.jsp");
				return;
			}
		} else {
			throw new ServletException("Request / Response 型態錯誤");
		}
	}

	private boolean checkLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		EmpVO loginToken = (EmpVO) session.getAttribute("empLoginOK");
		if (loginToken == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void destroy() {

	}
}
