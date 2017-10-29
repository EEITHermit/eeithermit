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
	
	public CalendarEventJNDIDAO_hibernate(){
		
	}
	//查詢員工時段內的預約，測試完畢
	String selectByEandT_h = "from CalendarEventVO "
			+ "where empNO=? AND (eventStartTime between ? and ?)";
	@Override
	public ArrayList<CalendarEventVO> selectByEmpAndTime(Integer empID, Timestamp start, Timestamp end) {
		ArrayList<CalendarEventVO> array = new ArrayList<CalendarEventVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
		try{
			session.getTransaction().begin();
			Query query = session.createQuery(selectByEandT_h);
			query.setParameter(0, empID);
			query.setParameter(1, start);
			query.setParameter(2, end);
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
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
	@Override
	public Integer update(CalendarEventVO resVO) {
		Integer result = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
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
	@Override
	public Integer insert(CalendarEventVO resVO) {
		Integer result = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
		Integer result = 1;
		try{
		session.getTransaction().begin();	
		CalendarEventVO ch = session.get(CalendarEventVO.class, ID);
		session.delete(ch);
		session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return result;
	}
	//查詢員工時段內的預約，測試完畢
		String selectByMember_h = "from CalendarEventVO where memNO = ? and (eventStartTime between getDate() and (getDate()+365))";
		@Override
		public ArrayList<CalendarEventVO> selectByMember(Integer memberNo){
			ArrayList<CalendarEventVO> array = new ArrayList<CalendarEventVO>();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
			try{
				session.getTransaction().begin();
				Query query = session.createQuery(selectByMember_h);
				query.setParameter(0, memberNo);
				List list = query.list();
				array.addAll(list);
				session.getTransaction().commit();
			} catch (Exception e1) {
				session.getTransaction().rollback();
				e1.printStackTrace();
			}
			return array;
		}
	
}	
