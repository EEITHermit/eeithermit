package com.hermit.iii.housetype.model;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.hermit.iii.util.HibernateUtil;

public class HouseTypeDAO_hibernate implements HouseTypeDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "from HouseTypeVO_hibernate order by typeNO";

	@Override
	public void insert(HouseTypeVO houseTypeVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(houseTypeVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(HouseTypeVO houseTypeVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(houseTypeVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer typeNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			// 【注意多方不可(不宜)採用cascade聯級刪除】
			session.beginTransaction();
			HouseTypeVO houseTypeVO = (HouseTypeVO) session
					.get(HouseTypeVO.class, typeNO);
			session.delete(houseTypeVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public HouseTypeVO findByPrimaryKey(Integer typeNO) {
		HouseTypeVO houseTypeVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			houseTypeVO = (HouseTypeVO) session.get(HouseTypeVO.class, typeNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return houseTypeVO;
	}

	@Override
	public List<HouseTypeVO> getAll() {
		List<HouseTypeVO> list = null;
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
		return list;

	}

	public static void main(String args[]) {
		// HouseTypeVO vo = new HouseTypeVO();
		HouseTypeDAO_hibernate dao = new HouseTypeDAO_hibernate();
		// List<HouseTypeVO> list;

		// test insert
		 HouseTypeVO houseTypeVO1 = new
		 HouseTypeVO();
		 houseTypeVO1.sethType("華廈");
		 dao.insert(houseTypeVO1);
		
		// vo.sethType("華廈");
		// dao.insert(vo);
		// 測試insertOK

		// test update
//		 HouseTypeVO houseTypeVO2 = new
//		 HouseTypeVO();
//		 houseTypeVO2.setTypeNO(2020);
//		 houseTypeVO2.sethType("茅房");
//		 dao.update(houseTypeVO2);
		// 測試updateOK

		//
		// test delete
		// dao.delete(2050);
		// 測試deleteOK
		
		// HouseTypeVO_hibernate houseTypeVO_hibernate3 =
		// dao.findByPrimaryKey(2010);
		// System.out.println(houseTypeVO_hibernate3.gethType());
		// System.out.println(houseTypeVO_hibernate3.getTypeNO());
		// 測試findByPrimaryKeyOK

//		List<HouseTypeVO_hibernate> list = dao.getAll();
//		for (int i = 0; i < list.size(); i++) {
//			for (HouseTypeVO_hibernate houseTypeVO_hibernate : list) {
//				// houseTypeVO_hibernate2 = list.get(i);
//				System.out.println(houseTypeVO_hibernate.gethType());
//				System.out.println(houseTypeVO_hibernate.getTypeNO());
//				
//			}
//		}
//		測試查詢全部OK
	}

}
