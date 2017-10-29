package com.hermit.iii.calendar.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hermit.iii.util.HibernateUtil;

public class CalendarEventJNDIDAO_hibernate implements CalendarEventDAO_interface_hibernate{
	Session session;
	public CalendarEventJNDIDAO_hibernate(){
		session = HibernateUtil.getSessionFactory().getCurrentSession(); 
	}
	//查詢員工時段內的預約，測試完畢
	String selectByEandT="select * from CalendarEvent C "
			+ "join Emp E ON C.empNO = E.empNO "
			+ "join member M ON C.memNO = M.memNO "
			+ "join house H ON C.houseNO = H.houseNO "
			+ "where E.empNO=? and (C.eventStartTime between ? and ?)";
	String selectByEandT_h = "from CalendarEventVO "
			+ "where (eventStartTime between ? and ?)";
	@Override
	public ArrayList<CalendarEventVO> selectByEmpAndTime(Integer empID, Timestamp start, Timestamp end) {
		ArrayList<CalendarEventVO> array = new ArrayList<CalendarEventVO>();
		try{
			session.getTransaction().begin();
			Query query = session.createQuery(selectByEandT_h);
			query.setParameter(0, start);
			query.setParameter(1, end);
			List list = query.list();
			array.addAll(list);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return array;
	}
	//查詢時間是否衝突，測試完畢
	String checkE = "select * from CalendarEvent where (empNO = ?) AND (eventNO != ?)"
					+ "AND(eventStartTime between ? and ?) "
					+ "AND((?>=eventStartTime AND ?<eventEndTime)"
					+ "or(?>eventStartTime AND ?<=eventEndTime)"
					+ "or(?<=eventStartTime AND ?>=eventEndTime))";
	String checkE_h = "from CalendarEventVO where (empNO = ?) AND (eventNO != ?)"
					+ "AND(eventStartTime between ? and ?) "
					+ "AND((?>=eventStartTime AND ?<eventEndTime)"
					+ "or(?>eventStartTime AND ?<=eventEndTime)"
					+ "or(?<=eventStartTime AND ?>=eventEndTime))";
	@Override
	public boolean checkExist(Integer empID, Timestamp start, Timestamp end,Integer eventNo) {
		boolean result = true;
		String day = start.toString().substring(0,10);
		Date date1 = Date.valueOf(day);
		Date date2 = new Date(Timestamp.valueOf(day+" 00:00:00").getTime()+60*60*24*1000);
		try {
			session.getTransaction().begin();
			Query query = session.createQuery(checkE_h);
			query.setParameter(0, empID);
			query.setParameter(1, eventNo);
			query.setParameter(2, date1);
			query.setParameter(3, date2);
			query.setParameter(4, start);
			query.setParameter(5, start);
			query.setParameter(6, end);
			query.setParameter(7, end);
			query.setParameter(8, start);
			query.setParameter(9, end);
			Iterator iterator = query.iterate();
			if (iterator.hasNext()) {
				result = true;
			} else {
				result = false;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
		return result;
	}
	//查詢當日最大ID，測試完畢
//	String checkMax = "select MAX(預約ID) from reservation where 員工ID=? AND 預約ID like ?";
//	@Override
//	public Integer checkMaxID(String ID,Integer empID) {
//		Integer max = null;
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		try(Connection conn = DriverManager.getConnection(url,"sa","P@ssw0rd")){
//			PreparedStatement ps = conn.prepareStatement(checkMax);
//			ps.setInt(1, empID);
//			ps.setString(2, ID.substring(0,8)+"%");
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()){
//				String maxId = rs.getString(1).substring(9);
//				max = new Integer(maxId);
//			}else{
//				max = 0;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return max;
//	}
	//更新資料，測試完畢
	String update = "update CalendarEvent set "
			+ "empNO = ? , memNO = ? , houseNO = ? ,"
			+ "eventStartTime= ?, eventEndTime=?,ps=? "
			+ "where eventNO = ?";
	@Override
	public Integer update(CalendarEventVO resVO) {
		Integer result = 0;
		try{
			session.getTransaction().begin();
			session.saveOrUpdate(resVO);
			session.getTransaction().commit();
			result = 1;
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return result;
	}
	//新增資料，測試OK
	String insert = "insert into CalendarEvent(empNO,memNO,houseNO,eventStartTime,"
			+ "eventEndTime,ps) values(?,?,?,?,?,?)";
	@Override
	public Integer insert(CalendarEventVO resVO) {
		Integer result = 0;
		try{
			session.getTransaction().begin();
			session.saveOrUpdate(resVO);
			session.getTransaction().commit();
			result=1;
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return result;
	}
	//刪除資料
	String delete = "delete from CalendarEvent where eventNO = ?";
	@Override
	public Integer delete(int ID) {
		Integer result = 1;
		CalendarEventVO ch = session.get(CalendarEventVO.class, ID);
		session.delete(ch);
		return result;
	}
	//查詢員工時段內的預約，測試完畢
		String selectByMember="select * from CalendarEvent C "
				+ "join Emp E ON C.empNO = E.empNO "
				+ "join member M ON C.memNO = M.memNO "
				+ "join house H ON C.houseNO = H.houseNO "
				+ "where M.memNO=? and (C.eventStartTime between getDate() and (getDate()+365))"; //+1為加一天
		@Override
		public ArrayList<CalendarEventVO> selectByMember(Integer memberNo){
			ArrayList<CalendarEventVO> array = new ArrayList<CalendarEventVO>();
//			try (Connection conn = ds.getConnection();
//				PreparedStatement ps = conn.prepareStatement(selectByMember);){
//				ps.setInt(1, memberNo);
//				ResultSet rs = ps.executeQuery();
//				while(rs.next()){
//					CalendarEventVO resVO = new CalendarEventVO();
//					resVO.setEventNO(rs.getInt("eventNO"));
//					resVO.getEmpVO().setEmpName(rs.getString("empName"));
//					resVO.getEmpVO().setEmpPhone(rs.getString("empPhone"));
//					resVO.getHouseVO().setHouseTitle(rs.getString("houseTitle"));
//					resVO.getHouseVO().setHouseAddr(rs.getString("houseAddr"));
//					resVO.setEventStartTime(rs.getTimestamp("eventStartTime"));
//					array.add(resVO);
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
			return array;
		}
	
}	
