package com.hermit.iii.dispatchlist.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.simple.JSONValue;

import com.hermit.iii.emp.model.EmpVO;
import com.hermit.iii.qanda.model.QandAVO;
import com.hermit.iii.util.HibernateUtil;

public class DispatchListDAO_hibernate implements DispatchListDAO_interface_hibernate {
	
	private static final String GET_ALL_STMT =	"from DispatchListVO order by dlNO";
	
	@Override
	public void insert(DispatchListVO dispatchListVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(dispatchListVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(DispatchListVO dispatchListVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(dispatchListVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer dlNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			DispatchListVO dispatchListVO = (DispatchListVO) session.get(DispatchListVO.class,dlNO);
			session.delete(dispatchListVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public DispatchListVO findByPrimaryKey(Integer dlNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		DispatchListVO dispatchListVO = null;
		try {
			session.beginTransaction();
			dispatchListVO = (DispatchListVO) session.get(DispatchListVO.class,dlNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return dispatchListVO;
	}

	@Override
	public List<DispatchListVO> getAll() {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try{
				session.beginTransaction();
				@SuppressWarnings("unchecked")
				List<DispatchListVO> resultList = session.createQuery(GET_ALL_STMT).getResultList();
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
		try{
			session.beginTransaction();
			List<DispatchListVO> resultList = session.createQuery(GET_ALL_STMT).getResultList();
			List list = new LinkedList();
			for(DispatchListVO vo : resultList){
				Map m1 = new LinkedHashMap();
				m1.put("dlNO", vo.getDlNO());
				m1.put("dempNO", vo.getDempVO().getEmpNO());
				m1.put("dempName", vo.getDempVO().getEmpName());
				m1.put("aempNO", vo.getAempVO().getEmpNO());
				m1.put("aempName", vo.getAempVO().getEmpName());
				m1.put("qaNO", vo.getQaVO().getQaNO());
				m1.put("qDetail", vo.getQaVO().getqDetail());
				m1.put("dlStime", vo.getDlStime().toString());
				if(vo.getDlEtime() != null){
					m1.put("dlEtime", vo.getDlEtime().toString());
				}
				m1.put("elesign", vo.getElesign());
				m1.put("dlNote", vo.getDlNote());
				list.add(m1);
			}
			Map m2 = new LinkedHashMap();
			m2.put("list",list);
			jsonString = JSONValue.toJSONString(m2);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return jsonString;
	}
	private static String GET_ALL_BY_EMP_NO = "from DispatchListVO where aempNO = ? And elesign is null";
	@Override
	public ArrayList<DispatchListVO> getAllByEmpNO(Integer empNO){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ArrayList<DispatchListVO> array = new ArrayList<DispatchListVO>();
		try{
			session.getTransaction().begin();
			Query query = session.createQuery(GET_ALL_BY_EMP_NO);
			query.setParameter(0, empNO);
			List<DispatchListVO> list = query.list();
			array.addAll(list);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return array;
	}
	public static void main (String args[]){
		DispatchListVO dvo = new DispatchListVO();
		DispatchListDAO_hibernate dao= new DispatchListDAO_hibernate();
		EmpVO dempVO = new EmpVO();
		dvo.setDempVO(dempVO);
		EmpVO aempVO = new EmpVO();
		dvo.setAempVO(aempVO);
		QandAVO qaVO = new QandAVO();
		dvo.setQaVO(qaVO);
		
//Insert Test Start
//		System.out.println("insert start");
//		dvo.getDempVO().setEmpNO(30001);
//		dvo.getAempVO().setEmpNO(30002);
//		dvo.getQaVO().setQaNO(60000001);;
//		dvo.setDlStime(java.sql.Date.valueOf("2017-10-28"));
//		dao.insert(dvo);
//		System.out.println("insert success");
//		
////Insert Test End
////Update Test Start

//		System.out.println("update start");
//		dvo.setDlNO(70000014);
//		dvo.getDempVO().setEmpNO(30001);
//		dvo.getAempVO().setEmpNO(30002);
//		dvo.getQaVO().setQaNO(60000001);;
//		dvo.setDlStime(java.sql.Date.valueOf("2017-10-28"));
//		dvo.setDlEtime(java.sql.Date.valueOf("2017-11-17"));
//		dvo.setElesign("date:image/png;base64,1234");
//		dvo.setDlNote("測試修復完畢+-*/");
//		dao.update(dvo);
//		System.out.println("update success");
		
//Update Test End
//Delete Test Start
		
//		System.out.println("delete start");
//		dao.delete(70000014);
//		System.out.println("delete success");
		
//Delete Test End		
//Search One Test Start
		
//		System.out.println("Search One Start");
//		dvo = dao.findByPrimaryKey(70000001);
//		System.out.println("dlNO \t= " + dvo.getDlNO());
//		System.out.println("dempNO \t= " + dvo.getDempVO().getEmpNO());
//		System.out.println("dempName = " + dvo.getDempVO().getEmpName());
//		System.out.println("aempNO \t= " + dvo.getAempVO().getEmpNO());
//		System.out.println("aempName = " + dvo.getAempVO().getEmpName());
//		System.out.println("qaNO \t= " + dvo.getQaVO().getQaNO());
//		System.out.println("qDetial = " + dvo.getQaVO().getQDetail());
//		System.out.println("dlStime = " + dvo.getDlStime());
//		System.out.println("dlEtime = " + dvo.getDlEtime());
//		System.out.println("elesign = " + dvo.getElesign());
//		System.out.println("dlNote \t= " + dvo.getDlNote());
//		System.out.println("Search One success");
		
//Search One Test End	

//Search All Test Start
//		
//		System.out.println("-----------Search All Start------------");	
//		List<DispatchListVO> list = dao.getAll();
//		for(int i=0;i<list.size();i++){
//			dvo = list.get(i);
//			System.out.println("dlNO \t= " + dvo.getDlNO());
//			System.out.println("dempNO \t= " + dvo.getDempVO().getEmpNO());
//			System.out.println("dempName = " + dvo.getDempVO().getEmpName());
//			System.out.println("aempNO \t= " + dvo.getAempVO().getEmpNO());
//			System.out.println("aempName = " + dvo.getAempVO().getEmpName());
//			System.out.println("qaNO \t= " + dvo.getQaVO().getQaNO());
//			System.out.println("qDetial = " + dvo.getQaVO().getQDetail());
//			System.out.println("dlStime = " + dvo.getDlStime());
//			System.out.println("dlEtime = " + dvo.getDlEtime());
//			System.out.println("elesign = " + dvo.getElesign());
//			System.out.println("dlNote \t= " + dvo.getDlNote());
//		}
//		System.out.println("-----------Search All success------------");	
		
//Search All Test End
//		System.out.println(dao.getAllForJson());
		
	}
}
