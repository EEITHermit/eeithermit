package com.hermit.iii.qanda.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hermit.iii.util.HibernateUtil;

public class QandAJNDIDAO_hibernate implements QandADAO_interface_hibernate {
	// qaNO在資料庫為自動流水號免新增，順序同資料庫表格(與VO/Bean)設定
	// (memNO,empNO,houseNO,qTime,aTime,qaType,qDetail,aDetail)
	private static final String INSERT_STMT = "INSERT INTO QandA VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	// 同上順序，全改通吃法(PK流水號當條件)
	private static final String UPDATE_STMT = "UPDATE QandA SET memNO=?, empNO=?, houseNO=?, qTime=?, aTime=?, qaType=?, qDetail=?, aDetail=? WHERE qaNO = ?";
	private static final String DELETE_STMT = "DELETE FROM QandA WHERE qaNO = ?";
	// (含PK流水號)全選，避免用＊(PK流水號當條件)
	private static final String GET_ONE_STMT = "SELECT qaNO,memNO,empNO,houseNO,qTime,aTime,qaType,qDetail,aDetail FROM QandA WHERE qaNO = ?";
	// (含PK流水號)全選，避免用＊(免PK流水號當條件全打包，PK流水號當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT qaNO,memNO,empNO,houseNO,qTime,aTime,qaType,qDetail,aDetail FROM QandA ORDER BY qaNO";
	//用memberNO查詢Q&A
	private static final String GET_ALL_BY_MEMBER_NO = "SELECT * FROM QandA Q "
			+ " JOIN house H ON Q.houseNO = H.houseNO where memNO = ? ORDER BY qaNO DESC";
	//用emp查詢boroughNO 來查詢Q&A
	private static final String GET_ALL_BY_BOROUGH_NO = "SELECT * FROM QandA Q "
			+ " JOIN house H ON Q.houseNO = H.houseNO "
			+ "JOIN Member M ON Q.memNO = M.memNO where boroughNO = ? AND empNO IS NULL ORDER BY qaNO";
	@Override
	public void insert(QandAVO qandaVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.saveOrUpdate(qandaVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void update(QandAVO qandaVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.getTransaction().begin();
			session.saveOrUpdate(qandaVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer qaNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.getTransaction().begin();
			QandAVO qaVO = session.get(QandAVO.class, qaNO);
			session.delete(qaVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

	}

	@Override
	public QandAVO findByPrimaryKey(Integer qaNO) {
		QandAVO qandaVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.getTransaction().begin();
			qandaVO = session.get(QandAVO.class, qaNO);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return qandaVO;
	}
	private static String GET_ALL_STMT_H = "from QandAVO";
	@Override
	public Set<QandAVO> getAll() {
		Set<QandAVO> set = new LinkedHashSet<QandAVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			Query query = session.createQuery(GET_ALL_STMT_H);
			List list = query.list();
			set.addAll(list);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return set;
	}
	private static String GET_ALL_BY_MEMBER_NO_H = "from QandAVO where memNO = ? ORDER BY qaNO DESC";
	public ArrayList<QandAVO> getAllByMemberNO(Integer memNO){
		ArrayList<QandAVO> array = new ArrayList<QandAVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.getTransaction().begin();
			Query query = session.createQuery(GET_ALL_BY_MEMBER_NO_H);
			query.setParameter(0, memNO);
			List list = query.list();
			array.addAll(list);
			session.getTransaction().commit();
		} catch (Exception se) { // Handle any SQL errors
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} 
		return array;
	}
	//OK
	private static String GET_ALL_BY_BOROUGH_NO0_H = "FROM QandAVO where qaType = 0 AND (houseVO.boroughsVO.boroughNO = ?) AND (empNO IS NULL) ORDER BY qaNO";
	@Override
	public ArrayList<QandAVO> getAllByBoroughNO0(Integer boroughNO) {
		ArrayList<QandAVO> array = new ArrayList<QandAVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.getTransaction().begin();
			Query query = session.createQuery(GET_ALL_BY_BOROUGH_NO0_H);
			query.setParameter(0, boroughNO);
			List<QandAVO> list = query.list();
			array.addAll(list);
			session.getTransaction().commit();
		}catch(Exception se) { // Handle any SQL errors
			session.getTransaction().rollback();
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} 
		return array;
	}
	//OK
		private static String GET_ALL_BY_BOROUGH_NO1_H = "FROM QandAVO where qaType = 1 AND houseVO.boroughsVO.boroughNO = ? AND empNO IS NULL ORDER BY qaNO";
		@Override
		public ArrayList<QandAVO> getAllByBoroughNO1(Integer boroughNO) {
			ArrayList<QandAVO> array = new ArrayList<QandAVO>();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try{
				session.getTransaction().begin();
				Query query = session.createQuery(GET_ALL_BY_BOROUGH_NO1_H);
				query.setParameter(0, boroughNO);
				List list = query.list();
				array.addAll(list);
				session.getTransaction().commit();
			}catch(Exception se) { // Handle any SQL errors
				session.getTransaction().rollback();
				throw new RuntimeException("A database error occured. " + se.getMessage());
			} 
			return array;
		}
		private static String GET_ALL_NOT_DEAL = "FROM QandAVO where empNO = null";
		@Override
		public ArrayList<QandAVO> getAllNotDeal(){
			ArrayList<QandAVO> array = new ArrayList<QandAVO>();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try{
				session.getTransaction().begin();
				Query query = session.createQuery(GET_ALL_NOT_DEAL);
				List<QandAVO> list = query.list();
				array.addAll(list);
				session.getTransaction().commit();
			}catch(Exception e){
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			return array;
		}
		
		private static String GET_ALL_DEALED = "FROM QandAVO where empNO != null";
		@Override
		public ArrayList<QandAVO> getAllDealed(){
			ArrayList<QandAVO> array = new ArrayList<QandAVO>();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try{
				session.getTransaction().begin();
				Query query = session.createQuery(GET_ALL_DEALED);
				List<QandAVO> list = query.list();
				array.addAll(list);
				session.getTransaction().commit();
			}catch(Exception e){
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			return array;
		}
}