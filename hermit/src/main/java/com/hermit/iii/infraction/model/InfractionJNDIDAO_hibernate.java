package com.hermit.iii.infraction.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hermit.iii.util.HibernateUtil;

public class InfractionJNDIDAO_hibernate implements InfractionDAO_interface_hibernate {
	
	private static final String INSERT = "insert into Infraction values(?,?,getDate(),?)";
	// 同上順序，全改通吃法(PK流水號當條件)
	private static final String UPDATE_STMT = "UPDATE Infraction SET memNO=?, reason=?, inDate=?, empNO=? WHERE inNO = ?";
	private static final String DELETE_STMT = "DELETE FROM Infraction WHERE inNO = ?";
	// (含PK流水號)全選，避免用＊(PK流水號當條件)
	private static final String GET_ONE_STMT = "SELECT inNO,memNO,reason,inDate,empNO FROM Infraction WHERE inNO = ?";
	// (含PK流水號)全選，避免用＊(免PK流水號當條件全打包，PK流水號當排序使得每次結果顯示方式統一)
	private static final String GET_ALL_STMT = "SELECT inNO,memNO,reason,inDate,empNO FROM Infraction ORDER BY inNO";
	
	// 新增，已測試
	@Override
	public Integer insert(InfractionVO inVO) {
		Integer result = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	try{
		session.getTransaction().begin();
		inVO.setInDate(new Date(System.currentTimeMillis()));
		session.saveOrUpdate(inVO);
		session.getTransaction().commit();
		result = 1;
	}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void update(InfractionVO infractionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.getTransaction().begin();
			session.saveOrUpdate(infractionVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer inNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.getTransaction().begin();
			InfractionVO inVO = session.get(InfractionVO.class, inNO);
			session.delete(inVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public InfractionVO findByPrimaryKey(Integer inNO) {
		InfractionVO infractionVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.getTransaction().begin();
			infractionVO = session.get(InfractionVO.class, inNO);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return infractionVO;
	}
	private static String GET_ALL_STMT_H = "from InfractionVO";
	@Override
	public Set<InfractionVO> getAll() {
		Set<InfractionVO> set = new LinkedHashSet<InfractionVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
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
	
}