package com.hermit.iii.admanager.model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.json.simple.JSONValue;

import com.hermit.iii.util.HibernateUtil;


public class ADManagerDAO_hebernate implements ADManagerDAO_interface_hibernate{
	private static final String GET_ALL_STMT =	"from ADManagerVO order by adNO";
		
	@Override
	public void insert(ADManagerVO adManagerVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(adManagerVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}


	@Override
	public void update(ADManagerVO adManagerVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(adManagerVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}


	@Override
	public void delete(Integer adNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ADManagerVO adManagerVO = (ADManagerVO) session.get(ADManagerVO.class,adNO);
			session.delete(adManagerVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}


	@Override
	public ADManagerVO findByPrimaryKey(Integer adNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ADManagerVO adManagerVO = null;
		try {
			session.beginTransaction();
			adManagerVO = (ADManagerVO) session.get(ADManagerVO.class,adNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return adManagerVO;
	}

	@Override
	public List<ADManagerVO> getAll() {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try{
				session.beginTransaction();
				@SuppressWarnings("unchecked")
				List<ADManagerVO> resultList = session.createQuery(GET_ALL_STMT).getResultList();
				session.getTransaction().commit();
				return resultList;
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
	}
	
		@Override
		public String getAllForJson() {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			String jsonString = null;
		
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<ADManagerVO> resultList = session.createQuery(GET_ALL_STMT).getResultList();
			session.getTransaction().commit();
			List list = new LinkedList();
			for (ADManagerVO vo : resultList) {
				Map m1 = new LinkedHashMap();
				m1.put("adNO", vo.getadNO());
				m1.put("adImage", vo.getAdImage());
				m1.put("adLink", vo.getAdLink());
				m1.put("adMessage",vo.getAdMessage());
				m1.put("adTimeStart",vo.getAdTimeStart().toString());
				m1.put("adTimeEnd",vo.getAdTimeEnd().toString());
				m1.put("adStatus",vo.getAdStatus());
				m1.put("adBrowse",vo.getAdBrowse());
				m1.put("adModify",vo.getAdModify());
				list.add(m1);
			}
			Map m2 = new LinkedHashMap();
			m2.put("list",list);
			jsonString = JSONValue.toJSONString(m2);
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return jsonString;
	}
		

		public static void main (String args[]){
			ADManagerVO adVO = new ADManagerVO();
			ADManagerDAO_hebernate adDAO = new ADManagerDAO_hebernate();
			
			//Insert Test Start
			
			
//			FileInputStream fis;
//			
//				try {
//					fis = new FileInputStream("D://WebSite//imgs//test5.png");
//					adVO.setAdImage("date:image/png;base64,1234");
//					adVO.setAdLink("www.yahoo.com.tw");
//					adVO.setAdMessage("哈囉大家好");
//					adVO.setAdTimeStart(java.sql.Date.valueOf("2017-10-23"));
//					adVO.setAdTimeEnd(java.sql.Date.valueOf("2017-10-25"));
//					adVO.setAdStatus(true);
//					adVO.setAdBrowse(1);
//					adVO.setAdModify(30001);
//					adDAO.insert(adVO);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println("insert success");
//		
//			}
			
	////Insert Test End
	////Update Test Start

			
//			FileInputStream fis;
//			try {
//			
//			fis = new FileInputStream("D://WebSite//imgs//test2.jpg");
//			aVO.setAdNo(5002);
//			aVO.setAdImage(fis);
//			aVO.setAdLink("www.google.com");
//			aVO.setAdMessage("這是測試圖片2");
//			aVO.setAdTimeStart(java.sql.Date.valueOf("2017-10-17"));
//			aVO.setAdTimeEnd(java.sql.Date.valueOf("2017-10-25"));
//			aVO.setAdStatus(true);
//			aVO.setAdBrowse(1);
//			aVO.setAdModify(30001);
//			aDAO.update(aVO);
//			System.out.println("update success");
//			
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
			
	//Update Test End
	//Delete Test Start
			
			System.out.println("delete start");
			adDAO.delete(5029);
			System.out.println("delete success");
}
	//Delete Test End		
	//Search One Test Start
			
//			System.out.println("Search One Start");
//			aVO = aDAO.findByPrimaryKey(5003);
//			System.out.println("adImage \t= " + aVO.getAdImage());
//			System.out.println("adLink \t= " + aVO.getAdLink());
//			System.out.println("adMessage \t= " + aVO.getAdMessage());
//			System.out.println("adTimeStart \t= " + aVO.getAdTimeStart());
//			System.out.println("adTimeEnd \t= " + aVO.getAdTimeEnd());
//			System.out.println("adStatus = " + aVO.getAdStatus());
//			System.out.println("adBrowse \t= " + aVO.getAdBrowse());
//			System.out.println("adModify \t= " + aVO.getAdModify());
//			System.out.println("adNo \t= " + aVO.getAdNo());
//			System.out.println("Search One success");
			
	//Search One Test End	

	//Search All Test Start
			
//			System.out.println("-----------Search All Start------------");	
//			List<ADManagerVO> list = aDAO.getAll();
//			for(int i=0;i<list.size();i++){
//				aVO = list.get(i);
//				aVO = aDAO.findByPrimaryKey(5003);
//				System.out.println("adImage \t= " + aVO.getAdImage());
//				System.out.println("adLink \t= " + aVO.getAdLink());
//				System.out.println("adMessage \t= " + aVO.getAdMessage());
//				System.out.println("adTimeStart \t= " + aVO.getAdTimeStart());
//				System.out.println("adTimeEnd \t= " + aVO.getAdTimeEnd());
//				System.out.println("adStatus = " + aVO.getAdStatus());
//				System.out.println("adBrowse \t= " + aVO.getAdBrowse());
//				System.out.println("adModify \t= " + aVO.getAdModify());
//				System.out.println("adNo \t= " + aVO.getAdNo());
//				System.out.println("Search One success");
//			}
//			System.out.println("-----------Search All success------------");	
//			
//	Search All Test End		
//		}


		
		
}
	
	
	
		
	


