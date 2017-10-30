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

import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.util.HibernateUtil;

public class ReservationJNDIDAO_hibernate implements ReservationDAO_interface_hibernate{
	 
	public ReservationJNDIDAO_hibernate(){
		
	}
	// 會員確認預約後的新增
	@Override
	public Integer insert(ReservationVO rlVO) {
		int result = 0;
		Session	session = HibernateUtil.getSessionFactory().getCurrentSession();
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
	String select_h = "from ReservationVO "
			+ "where (boroughNO = ?) AND (takedOver = 'false')";
	@Override
	public ArrayList<ReservationVO> selectByArea(Integer areaNo) {
		ArrayList<ReservationVO> array = new ArrayList<ReservationVO>();
		Session	session = HibernateUtil.getSessionFactory().getCurrentSession();
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
	String selectStatus_h = "from ReservationVO where reservationNO = ?";
	@Override
	public Integer updateStatus(Integer reservationNo , Integer empNo) {
		int result = 0;
		Session	session = HibernateUtil.getSessionFactory().getCurrentSession();
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
				EmpVO empVO = new EmpVO();
				empVO.setEmpNO(empNo);
				res.setEmpVO(empVO);
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
	String checkExist_h = "from ReservationVO where houseNO = ? AND memNO = ? AND takedOver = 'false'";
	@Override
	public boolean checkExist(Integer houseNo,Integer memberNo){
		boolean result = true;
		Session	session = HibernateUtil.getSessionFactory().getCurrentSession();
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
	
	public static void main(String[] args){
		ReservationJNDIDAO_hibernate res = new ReservationJNDIDAO_hibernate();
		ReservationVO re = new ReservationVO();
//		re.setApplyTime(Timestamp.valueOf("2017-10-10 00:00:00"));
//		re.getBoroughsVO().setBoroughNO(1);
//		re.setTakedOver(false);
//		re.setExceptTime("OK");
//		re.getEmpVO().setEmpNO(30001);
//		re.getMemberVO().setMemNO(40001);
//		re.getHouseVO().setHouseNO(20001);
//		res.insert(re);
//		for(ReservationVO r:res.selectByArea(1)){
//			System.out.println(r.getApplyTime());
//			System.out.println(r.getMemberVO().getMemName());
//		}
//		res.updateStatus(80000001, 30001);
	}
	
}
