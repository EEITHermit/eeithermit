package com.hermit.iii.mention.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

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

import com.hermit.iii.calendar.model.CalendarEventService;
import com.hermit.iii.calendar.model.CalendarEventVO;
import com.hermit.iii.dispatchlist.model.DispatchListService;
import com.hermit.iii.dispatchlist.model.DispatchListVO;
import com.hermit.iii.emp.model.EmpDAO_hibernate;
import com.hermit.iii.emp.model.EmpDAO_interface_hibernate;
import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.lease.model.LeaseService;
import com.hermit.iii.lease.model.LeaseVO;
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
		CalendarEventService eventService = new CalendarEventService();
		DispatchListService dispatchService = new DispatchListService();
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
		EmpVO empVO1 = (EmpVO)session.getAttribute("empLoginOK");
		Integer empNO = empVO1.getEmpNO();
		ArrayList<Integer> boroughNOs = mention.getBoroughNOByEmpNO(empNO);
		EmpVO empVO= empService.findByPrimaryKey(empNO);
		Integer postNO = empVO.getPostVO().getPostNO();
		ArrayList<ReservationVO> resArray = new ArrayList<ReservationVO>();
		ArrayList<QandAVO> qaArray = new ArrayList<QandAVO>();
		ArrayList<CalendarEventVO> eventArray = new ArrayList<CalendarEventVO>();
		ArrayList<DispatchListVO> dispatchArray = new ArrayList<DispatchListVO>();
		if(!boroughNOs.isEmpty()){
			for(Integer boroughNO : boroughNOs){
				//若是業務
				if(postNO == 320){
					resArray.addAll(reservation.selectByArea(boroughNO));
					qaArray.addAll(qaService.getAllByBoroughNO1(boroughNO));
				//若是客服
				}else if(postNO == 330){
					qaArray.addAll(qaService.getAllByBoroughNO0(boroughNO));
				//管理員帳戶
				}else if(postNO == 310){
					//管理員什麼都不給
				}
			}
		}
		//若是修繕人員，取得未完成之派工單
		if(postNO == 340){
			dispatchArray = dispatchService.getAllByEmpNO(empNO);
		}
		//取得員工所負責被取消的預約
		eventArray = eventService.selectDeleteNotice(empNO);
		//重新排序各個array
		resArray.sort(new Comparator<ReservationVO>(){
			public int compare(ReservationVO r1,ReservationVO r2){
				return r1.getReservationNO() - r2.getReservationNO();
			}
		});
		qaArray.sort(new Comparator<QandAVO>(){
			public int compare(QandAVO qa1,QandAVO qa2){
				return qa1.getQaNO() - qa2.getQaNO();
			}
		});
		request.setAttribute("qaArray", qaArray);
		request.setAttribute("resArray", resArray);
		request.setAttribute("eventArray", eventArray);
		request.setAttribute("resSize",resArray.size());
		request.setAttribute("dispatchArray", dispatchArray);
		request.setAttribute("empVO",empVO);
		chain.doFilter(request, response);
		return;	
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
