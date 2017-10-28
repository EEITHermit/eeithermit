package com.hermit.iii.reservation.model;

import java.sql.Connection;
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

public class ReservationJNDIDAO_hibernate implements ReservationDAO_interface_hibernate{
	Session session;
	public ReservationJNDIDAO_hibernate(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	// 會員確認預約後的新增
	String insert = "insert into reservation("
			+ "memNO,houseNO,boroughNO,exceptTime,applyTime,takedOver) values(?,?,?,?,?,?)";
	@Override
	public Integer insert(ReservationVO rlVO) {
		int result = 0;
		try{
		session.getTransaction().begin();
		session.saveOrUpdate(rlVO);
		session.getTransaction().commit();
		result = 1;
		}catch(Exception e){
			result = 0;
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}
	//推播功能用
	String select = "select * from reservation r join house h on h.houseNO = r.houseNO "
			+ "join member m on m.memNO = r.memNO "
			+ "where (r.boroughNO = ?) AND (takedOver = 'false')";
	//測
	String select_h = "from ReservationVO "
			+ "where (boroughNO = ?) AND (takedOver = 'false')";
	@Override
	public ArrayList<ReservationVO> selectByArea(Integer areaNo) {
		ArrayList<ReservationVO> array = new ArrayList<ReservationVO>();
		try{
			session.getTransaction().begin();
			Query query = session.createQuery(select_h);
			query.setParameter(0,areaNo);
			array.addAll(query.list());
			
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return array;
	}
	//員工接案後確認是否已被接案，再更新狀態
	String selectStatus = "select takedOver from reservation where reservationNO = ?";
	String selectStatus_h = "from ReservationVO where reservationNO = ?";
	String updateEmpNo = "update reservation set empNO = ?,takedOver = 'true' where reservationNO = ?";
	@Override
	public Integer updateStatus(Integer reservationNo , Integer empNo) {
		int result = 0;
		try{
			session.getTransaction().begin();
			Query query = session.createQuery(selectStatus_h);
			query.setParameter(0, reservationNo);
			List<ReservationVO> list = query.list();
			Boolean exist = true;
			for(ReservationVO res : list){
				exist = res.getTakedOver();
			}
			if(exist == false){
				ReservationVO res = session.get(ReservationVO.class, reservationNo);
				res.setEmpNO(empNo);
				res.setTakedOver(true);
				session.saveOrUpdate(res);
				result = 1;
			}else{
				return result;
			}
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	//確認是否有預約過此房屋
	String checkExist = "select * from reservation where houseNO= ? AND memNO = ? AND takedOver = 'false'";
	String checkExist_h = "from ReservationVO where houseNO = ? AND memNO = ? AND takedOver = 'false'";
	@Override
	public boolean checkExist(Integer houseNo,Integer memberNo){
		boolean result = true;
		try{
			session.getTransaction().begin();
			Query query = session.createQuery(checkExist_h);
			query.setParameter(0, houseNo);
			query.setParameter(1, memberNo);
			Iterator iterator = query.iterate();
			result = iterator.hasNext();
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}
	
}
