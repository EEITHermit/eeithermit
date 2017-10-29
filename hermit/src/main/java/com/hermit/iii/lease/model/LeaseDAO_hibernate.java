package com.hermit.iii.lease.model;

import java.util.*;

import org.hibernate.*;

import com.hermit.iii.util.*;

public class LeaseDAO_hibernate implements LeaseDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "from LeaseVO order by leaseNO";

	@Override
	public void insert(LeaseVO leaseVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(leaseVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(LeaseVO leaseVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(leaseVO);
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
			LeaseVO leaseVO = (LeaseVO) session.get(LeaseVO.class, leaseNO);
			session.delete(leaseVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public LeaseVO findByPrimaryKey(Integer leaseNO) {
		LeaseVO leaseVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			leaseVO = (LeaseVO) session.get(LeaseVO.class, leaseNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return leaseVO;
	}

	@Override
	public Set<LeaseVO> getAll() {
		List<LeaseVO> list = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			list = session.createQuery(GET_ALL_STMT).getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return new LinkedHashSet<LeaseVO>(list);
	}

	public static void main(String[] args) {
		LeaseDAO_hibernate dao = new LeaseDAO_hibernate();

		// 新增
		LeaseVO leaseVO1 = new LeaseVO();
		leaseVO1.setHouseNO(20001);
		leaseVO1.setLeaseBeginDate(java.sql.Date.valueOf("2015-10-10"));
		leaseVO1.setLeaseEndDate(java.sql.Date.valueOf("2017-01-01"));
		leaseVO1.setMemNO(40001);
		leaseVO1.setEmpNO(30001);
		leaseVO1.setLeaseRent(1000);
		leaseVO1.setLeaseDeposit(1000);
		leaseVO1.setLeaseRelief(1000);
		leaseVO1.setLeaseDate(java.sql.Date.valueOf("2017-04-01"));
		leaseVO1.setLeasePic(null); // not use
		leaseVO1.setHouseNote("備註123");
		leaseVO1.setLeaseRefund((byte) 0);
		dao.insert(leaseVO1);

		// 修改初始資料第一筆
		LeaseVO leaseVO2 = new LeaseVO();
		leaseVO2.setLeaseNO(200001);
		leaseVO2.setHouseNO(20001);
		leaseVO2.setLeaseBeginDate(java.sql.Date.valueOf("2014-10-10"));
		leaseVO2.setLeaseEndDate(java.sql.Date.valueOf("2016-01-01"));
		leaseVO2.setMemNO(40001);
		leaseVO2.setEmpNO(30001);
		leaseVO2.setLeaseRent(1000);
		leaseVO2.setLeaseDeposit(1000);
		leaseVO2.setLeaseRelief(1000);
		leaseVO2.setLeaseDate(java.sql.Date.valueOf("2018-04-01"));
		leaseVO2.setLeasePic(null); // not use
		leaseVO2.setHouseNote("備註123");
		leaseVO2.setLeaseRefund((byte) 10);
		dao.update(leaseVO2);

		// 查詢初始資料第一筆
		LeaseVO LeaseVO3 = dao.findByPrimaryKey(200001);
		System.out.print(LeaseVO3.getLeaseNO() + ",");
		System.out.print(LeaseVO3.getHouseNO() + ",");
		System.out.print(LeaseVO3.getLeaseBeginDate() + ",");
		System.out.print(LeaseVO3.getLeaseEndDate() + ",");
		System.out.print(LeaseVO3.getMemNO() + ",");
		System.out.print(LeaseVO3.getEmpNO() + ",");
		System.out.print(LeaseVO3.getLeaseRent() + ",");
		System.out.print(LeaseVO3.getLeaseDeposit() + ",");
		System.out.print(LeaseVO3.getLeaseRelief() + ",");
		System.out.print(LeaseVO3.getLeaseDate() + ",");
		System.out.print(LeaseVO3.getLeasePic() + ",");
		System.out.print(LeaseVO3.getHouseNote() + ",");
		System.out.println(LeaseVO3.getLeaseRefund());
		System.out.println("---------------------");

		// 查詢全部
		Set<LeaseVO> set = dao.getAll();
		for (LeaseVO leaseVO : set) {
			System.out.print(leaseVO.getLeaseNO() + ",");
			System.out.print(leaseVO.getHouseNO() + ",");
			System.out.print(leaseVO.getLeaseBeginDate() + ",");
			System.out.print(leaseVO.getLeaseEndDate() + ",");
			System.out.print(leaseVO.getMemNO() + ",");
			System.out.print(leaseVO.getEmpNO() + ",");
			System.out.print(leaseVO.getLeaseRent() + ",");
			System.out.print(leaseVO.getLeaseDeposit() + ",");
			System.out.print(leaseVO.getLeaseRelief() + ",");
			System.out.print(leaseVO.getLeaseDate() + ",");
			System.out.print(leaseVO.getLeasePic() + ",");
			System.out.print(leaseVO.getHouseNote() + ",");
			System.out.println(leaseVO.getLeaseRefund());
			System.out.println();
		}

		// 刪除初始資料一筆
		dao.delete(200001);

		System.out.println("Done");
	}
}