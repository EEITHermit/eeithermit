package com.hermit.iii.city.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.simple.JSONValue;

import com.hermit.iii.util.HibernateUtil;

public class CityDAO_hibernate implements CityDAO_interface {

	private static final String GET_ALL_STMT="from CityVO";
	
	@Override
	public void insert(CityVO cityVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(cityVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(CityVO cityVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(cityVO);
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
			CityVO cityVO = session.get(CityVO.class, cityNO);
			session.delete(cityVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public CityVO findByPrimaryKey(Integer cityNO) {
		CityVO cityVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			cityVO = session.get(CityVO.class, cityNO);
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return cityVO;
	}

	@Override
	public List<CityVO> getAll() {
		List<CityVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query=session.createQuery(GET_ALL_STMT);
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
		List list=new LinkedList();
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		String boroughJSON="";
		try{
			session.beginTransaction();
			Query query=session.createQuery(GET_ALL_STMT);
			List<CityVO> listQ=query.list();
			for(CityVO cityVO:listQ){
				Map m1=new LinkedHashMap();
				m1.put("cityNO", cityVO.getCityNO());
				m1.put("cityName", cityVO.getCityName());
				list.add(m1);
			}
			session.getTransaction().commit();
			Map m2=new LinkedHashMap();
			m2.put("list", list);
			boroughJSON=JSONValue.toJSONString(m2);
			
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
			throw ex;
		}
		return boroughJSON;
		
	}

	public static void main(String[] args) {
		CityDAO_hibernate dao_hibernate=new CityDAO_hibernate();
		
		//新增
//		CityVO_hibernate cityVO_hibernate=new CityVO_hibernate();
//		cityVO_hibernate.setCityName("測試");
//		dao_hibernate.insert(cityVO_hibernate);
		
		//修改
//		CityVO_hibernate cityVO_hibernate=new CityVO_hibernate();
//		cityVO_hibernate.setCityNO(10);
//		cityVO_hibernate.setCityName("雲林");
//		dao_hibernate.update(cityVO_hibernate);
		
		//刪除
//		dao_hibernate.delete(23);  //刪除測試
		
		//查詢
//		CityVO_hibernate cityVO_hibernate=dao_hibernate.findByPrimaryKey(2);
//		System.out.print(cityVO_hibernate.getCityNO()+", ");
//		System.out.println(cityVO_hibernate.getCityName());
		
		
		//查詢
//		List<CityVO_hibernate>list=dao_hibernate.getAll();
//		for(CityVO_hibernate cityVO_hibernate:list){
//			System.out.print(cityVO_hibernate.getCityNO()+",");
//			System.out.println(cityVO_hibernate.getCityName());
//		}
		//GetAllForJson
		String Json=dao_hibernate.getAllForJson();
		System.out.println(Json);
	}
}
