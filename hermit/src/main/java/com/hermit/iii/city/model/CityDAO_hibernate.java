package com.hermit.iii.city.model;

import java.util.*;

import org.hibernate.*;

import com.hermit.iii.util.HibernateUtil;

public class CityDAO_hibernate implements CityDAO_interface_hibernate {

	private static final String GET_ALL_STMT="from CityVO_hibernate order by cityNO";
	
	@Override
	public void insert(CityVO_hibernate cityVO_hibernate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(cityVO_hibernate);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(CityVO_hibernate cityVO_hibernate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(cityVO_hibernate);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer cityNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			CityVO_hibernate cityVO_hibernate = session.get(CityVO_hibernate.class, cityNO);
			session.delete(cityVO_hibernate);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public CityVO_hibernate findByPrimaryKey(Integer cityNO) {
		CityVO_hibernate cityVO_hibernate = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			cityVO_hibernate = session.get(CityVO_hibernate.class, cityNO);
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return cityVO_hibernate;
	}

	@Override
	public List<CityVO_hibernate> getAll() {
		List<CityVO_hibernate> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query=session.createQuery("GET_ALL_STMT");
			list=query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public String getAllForJson() {
		
		return null;
	}

	public static void main(String[] args) {
		CityDAO_hibernate dao_hibernate=new CityDAO_hibernate();
		
		//新增
		CityVO_hibernate cityVO_hibernate=new CityVO_hibernate();
		cityVO_hibernate.setCityName("測試");
		dao_hibernate.insert(cityVO_hibernate);
		
	}
}
