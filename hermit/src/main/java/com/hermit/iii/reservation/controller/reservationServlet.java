package com.hermit.iii.reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.hermit.iii.calendar.model.CalendarEventService;
import com.hermit.iii.calendar.model.CalendarEventVO;
import com.hermit.iii.house.model.HouseDAO;
import com.hermit.iii.reservation.model.ReservationService;
import com.hermit.iii.reservation.model.ReservationVO;


@WebServlet("/reservationServlet")
public class reservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public reservationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		PrintWriter out = response.getWriter();
		String mission = request.getParameter("mission");
		if(mission.equals("queryReservation")){
			CalendarEventService resDAO = new CalendarEventService();
			ArrayList<CalendarEventVO> array;
			Integer memberNo = Integer.valueOf(request.getParameter("memberNo"));
			array = resDAO.selectByMember(memberNo);
			out.print(new JSONArray(array).toString());
			return;
		};
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		PrintWriter out = response.getWriter();
		String mission = request.getParameter("mission");
		if (mission.equals("reservation")) {
			Integer memberNo = Integer.valueOf(request.getParameter("memberNo"));
			Integer houseNo = Integer.valueOf(request.getParameter("houseNo"));
			ReservationService rlDAO = new ReservationService();
			if (!(rlDAO.checkExist(houseNo, memberNo))) {
				int result = 0;
				HouseDAO mgDAO = new HouseDAO();
				ReservationVO rlVO = new ReservationVO();
				Timestamp currentTime = new Timestamp(System.currentTimeMillis());
				Integer areaNo = mgDAO.findAreaNoByHouseNo(Integer.valueOf(houseNo));
				String times[] = request.getParameterValues("Time");
				String expectTime = "";
				for (String time : times) {
					expectTime = expectTime + time + "; ";
				}
				rlVO.getMemberVO().setMemNO(Integer.valueOf(memberNo));
				rlVO.getHouseVO().setHouseNO(Integer.valueOf(houseNo));
				rlVO.setBoroughNO(areaNo);
				rlVO.setApplyTime(currentTime);
				rlVO.setExceptTime(expectTime);
				result = rlDAO.insert(rlVO);
				if (result == 1) {
					out.print("預約成功，業務將會跟您連絡，若24小時內無專人連絡您，請洽客服");
					return;
				} else {
					out.print("預約失敗，請重新預約");
					return;
				}
			} else {
				out.print("已預約，請等待業務與您接洽");
			}
		}
	}

}
