package com.hermit.iii.mention.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.hermit.iii.calendar.model.CalendarEventService;
import com.hermit.iii.calendar.model.CalendarEventVO;
import com.hermit.iii.dispatchlist.model.DispatchListService;
import com.hermit.iii.dispatchlist.model.DispatchListVO;
import com.hermit.iii.emp.model.EmpDAO_hibernate;
import com.hermit.iii.emp.model.EmpDAO_interface_hibernate;
import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.lease.model.LeaseService;
import com.hermit.iii.mention.model.MentionService;
import com.hermit.iii.qanda.model.QandAService;
import com.hermit.iii.qanda.model.QandAVO;
import com.hermit.iii.reservation.model.ReservationService;
import com.hermit.iii.reservation.model.ReservationVO;

/**
 * Servlet implementation class mentionServlet
 */
@WebServlet("/mentionServlet")
public class mentionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public mentionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String mission = request.getParameter("mission");
		if("takeOver".equals(mission)){
			ReservationService res = new ReservationService();
			Integer resNO = Integer.valueOf(request.getParameter("resNO"));
			Integer empNO = Integer.valueOf(request.getParameter("empNO"));
			Integer result = res.updateStatus(resNO, empNO);
			if(result == 1){
				out.print("接案成功，即將前往預約頁面");
				return;
			}else{
				out.print("接案失敗，已有別人接管此案");
				return;
			}
		}else if("match".equals(mission)){
			MentionService mention = new MentionService();
			EmpDAO_interface_hibernate empService = new EmpDAO_hibernate();
			ReservationService reservation = new ReservationService();
			QandAService qaService = new QandAService();
			CalendarEventService eventService = new CalendarEventService();
			DispatchListService dispatchService = new DispatchListService();
			//暫定從session內取得員工編號
			Integer empNO = Integer.valueOf(request.getParameter("empNO"));
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
						resArray.addAll(reservation.selectByArea(boroughNO));
						qaArray.addAll(qaService.getAllByBoroughNO1(boroughNO));
						qaArray.addAll(qaService.getAllByBoroughNO0(boroughNO));
					
					}
				}
			}
			//若是修繕人員，取得未完成之派工單
			if(postNO == 340){
				dispatchArray = dispatchService.getAllByEmpNO(empNO);
			}
			//取得員工所負責被取消的預約
			eventArray = eventService.selectDeleteNotice(empNO);
			HashMap<String,Integer> h = new HashMap<String,Integer>();
			h.put("qaSize", qaArray.size());
			h.put("resSize", resArray.size());
			h.put("eventSize",eventArray.size());
			h.put("dispatchSize", dispatchArray.size());
			JSONObject json = new JSONObject(h);
			out.print(json);
		}
	}

}
