package com.hermit.iii.boroughs.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hermit.iii.util.HibernateUtil;

public class BoroughsDAO_hibernate implements BoroughsDAO_interface {

	private static final String GET_ALL_STMT = "from BoroughsVO";

	@Override
	public void insert(BoroughsVO boroughsVO) {
		Session session =HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(boroughsVO);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(BoroughsVO boroughsVO) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(boroughsVO);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer boroughNO) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			BoroughsVO boroughsVO=session.get(BoroughsVO.class, boroughNO);
			session.delete(boroughsVO);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}

	}

	@Override
	public BoroughsVO findByPrimaryKey(Integer boroughNO) {
		BoroughsVO boroughsVO=null;
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			boroughsVO=session.get(BoroughsVO.class, boroughNO);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
		return boroughsVO;
	}

	@Override
	public List<BoroughsVO> getAll() {
		List<BoroughsVO>list=null;
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query=session.createQuery(GET_ALL_STMT);
			list=query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return list;
	}

	@Override
	public String getAllWhereCity(Integer cityNO) {
		
		return null;
	}

	
	@Override
	public List<BoroughsVO> getAll_cityNO(Integer cityNO) {
		//Json用
		return null;
	}

	public static void main(String[] args) {
		BoroughsDAO_hibernate dao=new BoroughsDAO_hibernate();
		
		//新增
//		BoroughsVO vo=new BoroughsVO();
//		vo.setBoroughName("測試");
//		vo.setCityNO(3);
//		dao.insert(vo);
		
		//修改
//		BoroughsVO vo=new BoroughsVO();
//		vo.setBoroughNO(42);
//		vo.setBoroughName("測試2");
//		vo.setCityNO(4);
//		dao.update(vo);
		
		//刪除
//		dao.delete(44);
		
		//查詢
//		BoroughsVO vo=dao.findByPrimaryKey(1);
//		System.out.print(vo.getBoroughNO()+",");
//		System.out.print(vo.getBoroughName()+",");
//		System.out.println(vo.getCityNO());
		
		//查詢全部
//		List<BoroughsVO>list=dao.getAll();
//		for(BoroughsVO vo:list){
//			System.out.print(vo.getBoroughNO()+",");
//			System.out.print(vo.getBoroughName()+",");
//			System.out.println(vo.getCityNO());
//		}
		
		
	}

}
