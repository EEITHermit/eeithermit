package com.hermit.iii.lease.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.util.HibernateUtil;

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
	//Q&A用 用會員編號找所屬房子
	private static String FIND_BY_MEMBERNO = "from LeaseVO where memNO = ?";
	@Override
	public ArrayList<HouseVO> findHouseBymemNO(Integer memNO) {
		ArrayList<HouseVO> array = new ArrayList<HouseVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.getTransaction().begin();
			Query query = session.createQuery(FIND_BY_MEMBERNO);
			query.setParameter(0, memNO);
			List<LeaseVO> list= query.list();
			for(LeaseVO leaseVO :list){
				array.add(leaseVO.getHouseVO());
			}
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return array;
	}
	//推播用 找尋快到期的房子
	private static String FIND_BY_BOROUGHNO = "from LeaseVO where houseVO.boroughsVO.boroughNO = ? AND leaseEndDate <= getDate()+60";
	@Override 
	public ArrayList<LeaseVO> getAllByBoroughNO(Integer boroughNO){
		ArrayList<LeaseVO> array = new ArrayList<LeaseVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.getTransaction().begin();
			Query query = session.createQuery(FIND_BY_BOROUGHNO);
			query.setParameter(0, boroughNO);
			List<LeaseVO> list = query.list();
			array.addAll(list);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return array;
	}
	private static String GET_ONE_LEASE_BY_MEMNO="from LeaseVO where memNO=?";
	@Override
	public List<LeaseVO> getAllLease(Integer memNO) {
		List<LeaseVO>list=new LinkedList();
		Session session =HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query=session.createQuery(GET_ONE_LEASE_BY_MEMNO);
			query.setParameter(0, memNO);
			List<LeaseVO>list2=query.list();
			list.addAll(list2);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			ex.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}
	public static void main(String[] args) {
		LeaseDAO_hibernate dao = new LeaseDAO_hibernate();

		// 新增
//		LeaseVO leaseVO1 = new LeaseVO();
//		HouseVO houseVO1 = new HouseVO();
//		houseVO1.setHouseNO(20001);
//		leaseVO1.setHouseVO(houseVO1);
//		leaseVO1.setLeaseBeginDate(java.sql.Date.valueOf("2015-10-10"));
//		leaseVO1.setLeaseEndDate(java.sql.Date.valueOf("2017-01-01"));
//		leaseVO1.setMemNO(40001);
//		leaseVO1.setEmpNO(30001);
//		leaseVO1.setLeaseRent(1000);
//		leaseVO1.setLeaseDeposit(1000);
//		leaseVO1.setLeaseRelief(1000);
//		leaseVO1.setLeaseDate(java.sql.Date.valueOf("2017-04-01"));
//		leaseVO1.setLeasePic(null); // not use
//		leaseVO1.setHouseNote("備註123");
//		leaseVO1.setLeaseRefund((byte) 0);
//		dao.insert(leaseVO1);

		// 修改初始資料第一筆
//		LeaseVO leaseVO2 = new LeaseVO();
//		leaseVO2.setLeaseNO(200002);
//		HouseVO houseVO = new HouseVO();
//		houseVO.setHouseNO(20001);
//		leaseVO2.setHouseVO(houseVO);
//		leaseVO2.setLeaseBeginDate(java.sql.Date.valueOf("2014-10-10"));
//		leaseVO2.setLeaseEndDate(java.sql.Date.valueOf("2016-01-01"));
//		leaseVO2.setMemNO(40001);
//		leaseVO2.setEmpNO(30001);
//		leaseVO2.setLeaseRent(1000);
//		leaseVO2.setLeaseDeposit(1000);
//		leaseVO2.setLeaseRelief(1000);
//		leaseVO2.setLeaseDate(java.sql.Date.valueOf("2018-04-01"));
//		leaseVO2.setLeasePic(null); // not use
//		leaseVO2.setHouseNote("備註123");
//		leaseVO2.setLeaseRefund((byte) 1);
//		dao.update(leaseVO2);

		// 查詢初始資料第一筆
//		LeaseVO LeaseVO3 = dao.findByPrimaryKey(200001);
//		System.out.print(LeaseVO3.getLeaseNO() + ",");
//		System.out.print(LeaseVO3.getHouseVO().getHouseNO() + ",");
//		System.out.print(LeaseVO3.getLeaseBeginDate() + ",");
//		System.out.print(LeaseVO3.getLeaseEndDate() + ",");
//		System.out.print(LeaseVO3.getMemNO() + ",");
//		System.out.print(LeaseVO3.getEmpNO() + ",");
//		System.out.print(LeaseVO3.getLeaseRent() + ",");
//		System.out.print(LeaseVO3.getLeaseDeposit() + ",");
//		System.out.print(LeaseVO3.getLeaseRelief() + ",");
//		System.out.print(LeaseVO3.getLeaseDate() + ",");
//		System.out.print(LeaseVO3.getLeasePic() + ",");
//		System.out.print(LeaseVO3.getHouseNote() + ",");
//		System.out.println(LeaseVO3.getLeaseRefund());
//		System.out.println("---------------------");

		// 查詢全部
//		Set<LeaseVO> set = dao.getAll();
//		for (LeaseVO leaseVO : set) {
//			System.out.print(leaseVO.getLeaseNO() + ",");
//			System.out.print(leaseVO.getHouseVO().getHouseNO() + ",");
//			System.out.print(leaseVO.getLeaseBeginDate() + ",");
//			System.out.print(leaseVO.getLeaseEndDate() + ",");
//			System.out.print(leaseVO.getMemNO() + ",");
//			System.out.print(leaseVO.getEmpNO() + ",");
//			System.out.print(leaseVO.getLeaseRent() + ",");
//			System.out.print(leaseVO.getLeaseDeposit() + ",");
//			System.out.print(leaseVO.getLeaseRelief() + ",");
//			System.out.print(leaseVO.getLeaseDate() + ",");
//			System.out.print(leaseVO.getLeasePic() + ",");
//			System.out.print(leaseVO.getHouseNote() + ",");
//			System.out.println(leaseVO.getLeaseRefund());
//			System.out.println();
//		}
		

		// 刪除初始資料一筆
//		dao.delete(200001);

		System.out.println("Done");
		 //找尋房屋物件BY memberNO
		ArrayList<HouseVO> array = dao.findHouseBymemNO(40001);
		for(HouseVO h :array){
			//System.out.println(h.getCityNO());
		}

//		ArrayList<HouseVO> array = dao.findHouseBymemNO(40001);
//		for(HouseVO h :array){
//			System.out.println(h.getCityVO().getCityNO());
//		}
		
	}



	

}