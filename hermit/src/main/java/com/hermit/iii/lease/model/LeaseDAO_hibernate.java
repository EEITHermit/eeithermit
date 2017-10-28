package com.hermit.iii.lease.model;

import java.util.*;

import org.hibernate.*;

import com.hermit.iii.util.*;

public class LeaseDAO_hibernate implements LeaseDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "from LeaseVO_hibernate order by leaseNO";

	@Override
	public void insert(LeaseVO_hibernate leaseVO_hibernate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(leaseVO_hibernate);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(LeaseVO_hibernate leaseVO_hibernate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(leaseVO_hibernate);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer leaseNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			// 【注意多方不可(不宜)採用cascade聯級刪除】
			session.beginTransaction();
			LeaseVO_hibernate leaseVO_hibernate = (LeaseVO_hibernate) session.get(LeaseVO_hibernate.class, leaseNO);
			session.delete(leaseVO_hibernate);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public LeaseVO_hibernate findByPrimaryKey(Integer leaseNO) {
		LeaseVO_hibernate leaseVO_hibernate = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			leaseVO_hibernate = (LeaseVO_hibernate) session.get(LeaseVO_hibernate.class, leaseNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return leaseVO_hibernate;
	}

	@Override
	public Set<LeaseVO_hibernate> getAll() {
		List<LeaseVO_hibernate> list = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list(); // getResultList()
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return new LinkedHashSet<LeaseVO_hibernate>(list);
	}

	public static void main(String[] args) {
		LeaseDAO_hibernate dao = new LeaseDAO_hibernate();

		// 新增
		LeaseVO_hibernate leaseVO_hibernate1 = new LeaseVO_hibernate();
		leaseVO_hibernate1.setHouseNO(20001);
		leaseVO_hibernate1.setLeaseBeginDate(java.sql.Date.valueOf("2015-10-10"));
		leaseVO_hibernate1.setLeaseEndDate(java.sql.Date.valueOf("2017-01-01"));
		leaseVO_hibernate1.setMemNO(40001);
		leaseVO_hibernate1.setEmpNO(30001);
		leaseVO_hibernate1.setLeaseRent(1000);
		leaseVO_hibernate1.setLeaseDeposit(1000);
		leaseVO_hibernate1.setLeaseRelief(1000);
		leaseVO_hibernate1.setLeaseDate(java.sql.Date.valueOf("2017-04-01"));
		leaseVO_hibernate1.setLeasePic(null); // not use
		leaseVO_hibernate1.setHouseNote("備註123");
		leaseVO_hibernate1.setLeaseRefund((byte) 0);
		dao.insert(leaseVO_hibernate1);

		// 修改初始資料第一筆
		LeaseVO_hibernate leaseVO_hibernate2 = new LeaseVO_hibernate();
		leaseVO_hibernate2.setLeaseNO(200001);
		leaseVO_hibernate2.setHouseNO(20001);
		leaseVO_hibernate2.setLeaseBeginDate(java.sql.Date.valueOf("2014-10-10"));
		leaseVO_hibernate2.setLeaseEndDate(java.sql.Date.valueOf("2016-01-01"));
		leaseVO_hibernate2.setMemNO(40001);
		leaseVO_hibernate2.setEmpNO(30001);
		leaseVO_hibernate2.setLeaseRent(1000);
		leaseVO_hibernate2.setLeaseDeposit(1000);
		leaseVO_hibernate2.setLeaseRelief(1000);
		leaseVO_hibernate2.setLeaseDate(java.sql.Date.valueOf("2018-04-01"));
		leaseVO_hibernate2.setLeasePic(null); // not use
		leaseVO_hibernate2.setHouseNote("備註123");
		leaseVO_hibernate2.setLeaseRefund((byte) 10);
		dao.update(leaseVO_hibernate2);

		// 查詢初始資料第一筆
		LeaseVO_hibernate LeaseVO_hibernate3 = dao.findByPrimaryKey(200001);
		System.out.print(LeaseVO_hibernate3.getLeaseNO() + ",");
		System.out.print(LeaseVO_hibernate3.getHouseNO() + ",");
		System.out.print(LeaseVO_hibernate3.getLeaseBeginDate() + ",");
		System.out.print(LeaseVO_hibernate3.getLeaseEndDate() + ",");
		System.out.print(LeaseVO_hibernate3.getMemNO() + ",");
		System.out.print(LeaseVO_hibernate3.getEmpNO() + ",");
		System.out.print(LeaseVO_hibernate3.getLeaseRent() + ",");
		System.out.print(LeaseVO_hibernate3.getLeaseDeposit() + ",");
		System.out.print(LeaseVO_hibernate3.getLeaseRelief() + ",");
		System.out.print(LeaseVO_hibernate3.getLeaseDate() + ",");
		System.out.print(LeaseVO_hibernate3.getLeasePic() + ",");
		System.out.print(LeaseVO_hibernate3.getHouseNote() + ",");
		System.out.println(LeaseVO_hibernate3.getLeaseRefund());
		System.out.println("---------------------");

		// 查詢全部
		Set<LeaseVO_hibernate> set = dao.getAll();
		for (LeaseVO_hibernate leaseVO_hibernate : set) {
			System.out.print(leaseVO_hibernate.getLeaseNO() + ",");
			System.out.print(leaseVO_hibernate.getHouseNO() + ",");
			System.out.print(leaseVO_hibernate.getLeaseBeginDate() + ",");
			System.out.print(leaseVO_hibernate.getLeaseEndDate() + ",");
			System.out.print(leaseVO_hibernate.getMemNO() + ",");
			System.out.print(leaseVO_hibernate.getEmpNO() + ",");
			System.out.print(leaseVO_hibernate.getLeaseRent() + ",");
			System.out.print(leaseVO_hibernate.getLeaseDeposit() + ",");
			System.out.print(leaseVO_hibernate.getLeaseRelief() + ",");
			System.out.print(leaseVO_hibernate.getLeaseDate() + ",");
			System.out.print(leaseVO_hibernate.getLeasePic() + ",");
			System.out.print(leaseVO_hibernate.getHouseNote() + ",");
			System.out.println(leaseVO_hibernate.getLeaseRefund());
			System.out.println();
		}

		// 刪除初始資料一筆
		dao.delete(200001);

		System.out.println("Done");
	}
}