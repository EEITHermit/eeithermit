package com.hermit.iii.boroughs.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONValue;

import com.hermit.iii.util.HibernateUtil;

public class BoroughsDAO_hibernate implements BoroughsDAO_interface {

	private static final String GET_ALL_STMT = "from BoroughsVO";

	private static final String GET_ALL_STMT_CITYNO="from BoroughsVO where cityNO=?";
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
		List list=new LinkedList();
		Session session =HibernateUtil.getSessionFactory().getCurrentSession();
		String strJson="";
		try{
			session.beginTransaction();
			Query query=session.createQuery(GET_ALL_STMT_CITYNO);
			query.setParameter(0,1);
			List<BoroughsVO>listQ=query.list();
			for(BoroughsVO vo:listQ){
				Map m1=new LinkedHashMap();
				m1.put("boroughsNO", vo.getBoroughNO());
				m1.put("boroughsName", vo.getBoroughName());
				m1.put("cityNO", vo.getCityNO());
				list.add(m1);
			}
			session.getTransaction().commit();
			Map m2 =new LinkedHashMap();
			m2.put("list", list);
			strJson=JSONValue.toJSONString(m2);
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return strJson;
	}

	
	@Override
	public List<BoroughsVO> getAll_cityNO(Integer cityNO) {
		List<BoroughsVO> list=new LinkedList<BoroughsVO>();
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		String strJson="";
		try{
			session.beginTransaction();
			Query query =session.createQuery(GET_ALL_STMT_CITYNO);
			query.setParameter(0, 2);//先取值
			list =query.list();
			
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		
		return list;
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
		
		//查詢for Json
//		List<BoroughsVO>list=dao.getAll_cityNO(1);
//		for(BoroughsVO vo:list){
//			System.out.print(vo.getBoroughNO()+",");
//			System.out.print(vo.getBoroughName()+",");
//			System.out.println(vo.getCityNO());
//		}
		
		//查詢 getAllWhereCity
//		String strJson=dao.getAllWhereCity(1);
//		System.out.println(strJson);
	}

}
