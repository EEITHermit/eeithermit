package com.hermit.iii.house.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.hermit.iii.util.HibernateUtil;

public class HouseDAO_hibernate implements HouseDAO_interface_hibernate {

	private static final String GET_ALL_STMT =	"from HouseVO order by houseNO";
	
	@Override
	public void insert(HouseVO houseVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(houseVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public void update(HouseVO houseVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(houseVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}		
	}

	@Override
	public void delete(Integer houseNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			HouseVO houseVO = (HouseVO) session.get(HouseVO.class, houseNO);
			session.delete(houseVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}		
	}

	@Override
	public HouseVO findByPrimaryKey(Integer houseNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		HouseVO houseVO = null;
		try {
			session.beginTransaction();
			houseVO = (HouseVO) session.get(HouseVO.class, houseNO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return houseVO;
	}

	@Override
	public List<HouseVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<HouseVO> list = session.createQuery(GET_ALL_STMT).getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return null;
	}

//未寫
	@Override
	public ArrayList<HouseVO> autoCompleteH(String address) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return null;
	}
	
	//未寫
	@Override
	public Integer findAreaNoByHouseNo(Integer houseNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return null;
	}

//未寫
	@Override
	public List<HouseVO> GET_ALL_JOIN_FK() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return null;
	}

	@Override
	public HouseVO GET_ONE_HOUSE_FK(Integer houseNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return null;
	}

	@Override
	public String advencedSearch(String searchStr) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//			List list = session.createNativeQuery(searchStr).list();
			Query query=session.createSQLQuery(searchStr); 
			System.out.println(query);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> list=query.list(); 
			for(Map m : list ){
				System.out.println(m);
//				System.out.println(m.get("houseNO"));
			}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return null;
	}
	public static void main (String args[]){
		HouseDAO_hibernate dao = new HouseDAO_hibernate();
		HouseVO vo = new HouseVO();
		
//		vo.setHouseTitle("東區忠孝復興站,極簡設計師裝潢");
//		vo.setCityNO(1);
//		vo.setBoroughNO(1);
//		vo.setHighestFloor(13);
//		vo.setNowFloor(5);
//		vo.setHouseStatus("未出租");
//		vo.setHouseRent(20000);
//		vo.setHouseCharge(40000);     //押金
//		vo.setWaterRate("依帳單繳費");
//		vo.setPowerRate("依帳單繳費");
//		vo.setHouseVideo("http://www.youtube.com");
//		vo.setTypeNO(2010);
//		vo.setFormNO(2010);
//		vo.setHouseAddr("新北市板橋區大馬路3號");
//		vo.setHouseSize(10.32);
//		dao.insert(vo);
//		System.out.println("success");
//		dao.advencedSearch("SELECT DISTINCT h.houseNO,h.houseTitle,c.cityName,b.boroughName,h.previewPic,h.highestFloor,h.nowFloor,h.houseRent,t.hType,f.hForm,h.houseAddr,h.houseSize FROM house h JOIN equipmentCondition eq ON h.houseNO = eq.houseNO JOIN City c ON h.cityNO = c.cityNO JOIN Boroughs b ON h.boroughNO = b.boroughNO JOIN HouseType t ON h.typeNO = t.typeNO JOIN HouseForm f ON h.formNO = f.formNO WHERE h.houseStatus = '未出租' and (h.cityNO = 1)");
	}
}
