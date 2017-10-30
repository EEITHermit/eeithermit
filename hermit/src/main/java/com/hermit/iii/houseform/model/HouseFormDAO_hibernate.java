package com.hermit.iii.houseform.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.hermit.iii.util.HibernateUtil;

public class HouseFormDAO_hibernate implements HouseFormDAO_interface_hibernate {

	private static final String GET_ALL_STMT = "from HouseFormVO order by formNO";
	
	@Override
	public void insert(HouseFormVO houseFormVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(houseFormVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	@Override
	public void update(HouseFormVO houseFormVO){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(houseFormVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer formNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			HouseFormVO houseFormVO = (HouseFormVO) session
					.get(HouseFormVO.class, formNO);
			session.delete(houseFormVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public HouseFormVO findByPrimaryKey(Integer formNO) {
		HouseFormVO houseFormVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			houseFormVO = (HouseFormVO) session.get(HouseFormVO.class, formNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return houseFormVO;
	}


	@Override
	public List<HouseFormVO> getAll() {
		List<HouseFormVO> list = null;
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
	
	public static void main(String[] args){
//		HouseFormVO_hibernate vo = new HouseFormVO_hibernate();
		HouseFormDAO_hibernate dao = new HouseFormDAO_hibernate();
//		List<HouseFormVO_hibernate> list;
		
		
//		test insert 
		
		HouseFormVO houseFormVO1 = new HouseFormVO();
 		houseFormVO1.sethForm("稻草屋");
 		dao.insert(houseFormVO1);
// 		測試insertOK		
		

//		 test update
		
//		 HouseFormVO_hibernate houseFormVO_hibernate2 = new
//				 HouseFormVO_hibernate();
//		 houseFormVO_hibernate2.setFormNO(2050);
//		 houseFormVO_hibernate2.sethForm("茅草屋");
//		 dao.update(houseFormVO_hibernate2);
//		 測試updateOK
		
		
// 		 test delete
//		 dao.delete(2050);
// 		 測試deleteOK
		
//		HouseFormVO_hibernate houseFormVO_hibernate3 =
//		 dao.findByPrimaryKey(2010);
//		 System.out.println(houseFormVO_hibernate3.gethForm());
//		 System.out.println(houseFormVO_hibernate3.getFormNO());
//		 測試findByPrimaryKeyOK

//				List<HouseFormVO_hibernate> list = dao.getAll();
//				for (int i = 0; i < list.size(); i++) {
//					for (HouseFormVO_hibernate houseFormVO_hibernate : list) {
//						System.out.println(houseFormVO_hibernate.gethForm());
//						System.out.println(houseFormVO_hibernate.getFormNO());
//						
//					}
//				}
//				測試查詢全部OK
		
	}
}
