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

import com.hermit.iii.emp.model.EmpDAO_hibernate;
import com.hermit.iii.emp.model.EmpDAO_interface_hibernate;
import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.mention.model.MentionService;
import com.hermit.iii.qanda.model.QandAService;
import com.hermit.iii.qanda.model.QandAVO;
import com.hermit.iii.reservation.model.ReservationService;
import com.hermit.iii.reservation.model.ReservationVO;

@WebFilter(urlPatterns="/mention/*")
public class MentionFilter implements Filter {

    public MentionFilter() {
 
    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		MentionService mention = new MentionService();
		EmpDAO_interface_hibernate empService = new EmpDAO_hibernate();
		ReservationService reservation = new ReservationService();
		QandAService qaService = new QandAService();
		HttpServletRequest req = null;
		HttpServletResponse resp = null;
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse){
			req = (HttpServletRequest)request;
			resp = (HttpServletResponse)response;
		}else{
			return;
		}
		//暫定從session內取得員工編號
		HttpSession session = req.getSession();	
		EmpVO empVO1 = (EmpVO)session.getAttribute("LoginOK");
		Integer empNO = empVO1.getEmpNO();
		ArrayList<Integer> boroughNOs = mention.getBoroughNOByEmpNO(empNO);
		EmpVO empVO= empService.findByPrimaryKey(30001);
		Integer postNO = empVO.getPostVO().getPostNO();
		ArrayList<ReservationVO> resArray = new ArrayList<ReservationVO>();
		ArrayList<QandAVO> qaArray = new ArrayList<QandAVO>();
		if(!boroughNOs.isEmpty()){
			for(Integer boroughNO : boroughNOs){
				//若是業務
				if(postNO == 320){
					resArray.addAll(reservation.selectByArea(boroughNO));
					qaArray.addAll(qaService.getAllByBoroughNO1(boroughNO));
				//若是客服
				}else if(postNO == 330){
					qaArray.addAll(qaService.getAllByBoroughNO0(boroughNO));
				}else if(postNO == 310){
					resArray.addAll(reservation.selectByArea(boroughNO));
					qaArray.addAll(qaService.getAllByBoroughNO1(boroughNO));
					qaArray.addAll(qaService.getAllByBoroughNO0(boroughNO));
				}
			}
		}
		request.setAttribute("qaArray", qaArray);
		request.setAttribute("resArray", resArray);
		request.setAttribute("resSize",resArray.size());
		request.setAttribute("empVO",empVO);
		chain.doFilter(request, response);
		return;	
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
