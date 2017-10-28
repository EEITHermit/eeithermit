package com.hermit.iii.housepicture.model;

import java.util.*;

import org.hibernate.*;

import com.hermit.iii.util.*;

import com.hermit.iii.util.HibernateUtil;

public class HousePictureDAO_hibernate implements HousePictureDAO_interface {
		
	private static final String GET_ALL_STMT="from HousePictureVO";

	@Override
	public void insert(HousePictureVO housePictureVO) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(housePictureVO);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public void update(HousePictureVO housePictureVO) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(housePictureVO);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer housePictureNO) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			HousePictureVO vo=session.get(HousePictureVO.class, housePictureNO);
			session.delete(vo);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public HousePictureVO findByPrimaryKey(Integer housePictureNO) {
		HousePictureVO vo=null;
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		vo=session.get(HousePictureVO.class, housePictureNO);
		session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
		
		return vo;
	}

	@Override
	public List<HousePictureVO> getAll() {
		List<HousePictureVO>list=null;
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query=session.createQuery(GET_ALL_STMT);
			list=query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
//			ex.printStackTrace();
			throw ex;
		}
		return list;
	}
	
	public static void main(String[] args) {
		HousePictureDAO_hibernate dao=new HousePictureDAO_hibernate();
		
		//新增
//		HousePictureVO vo=new HousePictureVO();
//		vo.sethPicture("0x123456");
//		vo.setHouseNO(20001);
//		dao.insert(vo);
		
		//修改
//		HousePictureVO vo=new HousePictureVO();
//		vo.setHousePictureNO(11);
//		vo.sethPicture("123");
//		vo.setHouseNO(20001);
//		dao.update(vo);
	
		//刪除
//		dao.delete(5);
		
		//查詢
//		HousePictureVO vo=dao.findByPrimaryKey(6);
//		System.out.print(vo.getHousePictureNO()+",");
//		System.out.print(vo.gethPicture()+",");
//		System.out.println(vo.getHouseNO());
		
		//查詢全部
//		List<HousePictureVO>list=dao.getAll();
//		for(HousePictureVO vo:list){
//			System.out.print(vo.getHousePictureNO()+",");
//			System.out.print(vo.gethPicture()+",");
//			System.out.println(vo.getHouseNO());
//		}
	}

}
