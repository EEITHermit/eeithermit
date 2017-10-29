package com.hermit.iii.house.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONValue;

import com.hermit.iii.equipmentcondition.model.EquipmentConditionVO;
import com.hermit.iii.util.HibernateUtil;

public class HouseDAO_hibernate implements HouseDAO_interface_hibernate {

	private static final String GET_ALL_STMT =	"from HouseVO order by houseNO";
	
	@Override
	public void insert(HouseVO houseVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(houseVO);
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
			session.update(houseVO);
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
			return list;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
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
		String jsonStr = null;
		try {
			session.beginTransaction();
//			List list = session.createNativeQuery(searchStr).list();
			Query query=session.createSQLQuery(searchStr); 
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> list=query.list(); 
			Map m2 = new LinkedHashMap();
			m2.put("list", list);
			jsonStr = JSONValue.toJSONString(m2);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return jsonStr;
	}
	
	public void insertHouseAndEquip(HouseVO houseVO,EquipmentConditionVO equipVO){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(houseVO);
			equipVO.setHouseNO(houseVO.getHouseNO());
			session.save(equipVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}
	public static void main (String args[]){
		HouseDAO_hibernate dao = new HouseDAO_hibernate();
		HouseVO vo = new HouseVO();
		EquipmentConditionVO evo = new EquipmentConditionVO();
		List<HouseVO> list = null;
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
//		
//		evo.setTV((byte) 1);
//		evo.setAircondition((byte) 1);
//		evo.setRefrigerator((byte) 1);
//		evo.setWaterHeater((byte) 1);
//		evo.setGas((byte) 1);
//		evo.setTheFourthStation((byte) 1);
//		evo.setNet((byte) 1);
//		evo.setWashing((byte) 1);
//		evo.setBed((byte) 1);
//		evo.setWardrobe((byte) 1);
//		evo.setSofa((byte) 1);
//		evo.setParking((byte) 1);
//		evo.setElevator((byte) 1);
//		evo.setBalcony((byte) 1);
//		evo.setPermitCook((byte) 1);
//		evo.setPet((byte) 1);
//		evo.setCloseMRT((byte) 1);
//		
//		dao.insertHouseAndEquip(vo,evo);
//		System.out.println("success");
		
		
		
		System.out.println(dao.advencedSearch("SELECT DISTINCT h.houseNO,h.houseTitle,c.cityName,b.boroughName,h.previewPic,h.highestFloor,h.nowFloor,h.houseRent,t.hType,f.hForm,h.houseAddr,h.houseSize FROM house h JOIN equipmentCondition eq ON h.houseNO = eq.houseNO JOIN City c ON h.cityNO = c.cityNO JOIN Boroughs b ON h.boroughNO = b.boroughNO JOIN HouseType t ON h.typeNO = t.typeNO JOIN HouseForm f ON h.formNO = f.formNO WHERE h.houseStatus = '未出租' and (h.cityNO = 1)"));
		

		//Update Test Start
//				vo.setHouseTitle("不甜蜜小套房");
//				vo.setCityNO(2);
//				vo.setBoroughNO(2);
//				vo.setHighestFloor(18);
//				vo.setNowFloor(18);
//				vo.setHouseStatus("出租中");
//				vo.setHouseRent(18000);
//				vo.setHouseCharge(36000);
//				vo.setWaterRate("依帳單繳費");
//				vo.setPowerRate("依帳單繳費");
//				vo.setHouseVideo("http://www.youtube.com/notsweethouse");
//				vo.setTypeNO(2020);
//				vo.setFormNO(2020);
//				vo.setHouseAddr("新北市板橋區大馬路2號");
//				vo.setHouseSize(18.87);
//				vo.setHouseNO(20002);
//				dao.update(vo);
//				System.out.println("Update Success");
		//Update Test End	
				
				
		//Delete Test Start
//				dao.delete(20001);
//				System.out.println("Delete Success");
		//Delete Test End
				
		//Get One Test Start
//				vo = dao.findByPrimaryKey(20002);	
//				System.out.println("getHouseTitle = \t" + vo.getHouseTitle());
//				System.out.println("getCityNO = \t\t" + vo.getCityNO());
//				System.out.println("getBoroughNO = \t\t" + vo.getBoroughNO());
//				System.out.println("getHighestFloor = \t" + vo.getHighestFloor());
//				System.out.println("getNowFloor = \t\t" + vo.getNowFloor());
//				System.out.println("getHouseStatus = \t" + vo.getHouseStatus());
//				System.out.println("getHouseRent = \t\t" + vo.getHouseRent());
//				System.out.println("getHouseCharge = \t" + vo.getHouseCharge());
//				System.out.println("getWaterRate = \t\t" + vo.getWaterRate());
//				System.out.println("getPowerRate = \t\t" + vo.getPowerRate());
//				System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//				System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//				System.out.println("getTypeNO = \t\t" + vo.getTypeNO());
//				System.out.println("getFormNO = \t\t" + vo.getFormNO());
//				System.out.println("getHouseAddr = \t\t" + vo.getHouseAddr());
//				System.out.println("getHouseSize = \t\t" + vo.getHouseSize());
//				System.out.println("getHouseNO = \t\t" + vo.getHouseNO());
//				System.out.println("Search Success");
		//Get One Test End	
				
		//Get All Test Start
//				list = dao.getAll();
//				for(int i=0;i<list.size();i++){
//					vo = list.get(i);
//					System.out.println("getHouseNO = \t\t" + vo.getHouseNO());
//					System.out.println("getHouseTitle = \t" + vo.getHouseTitle());
//					System.out.println("getCityNO = \t\t" + vo.getCityNO());
//					System.out.println("getBoroughNO = \t\t" + vo.getBoroughNO());
//					System.out.println("getHighestFloor = \t" + vo.getHighestFloor());
//					System.out.println("getNowFloor = \t\t" + vo.getNowFloor());
//					System.out.println("getHouseStatus = \t" + vo.getHouseStatus());
//					System.out.println("getHouseRent = \t\t" + vo.getHouseRent());
//					System.out.println("getHouseCharge = \t" + vo.getHouseCharge());
//					System.out.println("getWaterRate = \t\t" + vo.getWaterRate());
//					System.out.println("getPowerRate = \t\t" + vo.getPowerRate());
//					System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//					System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//					System.out.println("getTypeNO = \t\t" + vo.getTypeNO());
//					System.out.println("getFormNO = \t\t" + vo.getFormNO());
//					System.out.println("getHouseAddr = \t\t" + vo.getHouseAddr());
//					System.out.println("getHouseSize = \t\t" + vo.getHouseSize());
//					System.out.println();
//					System.out.println("------------------------------next---------------------------------------------");
//					System.out.println();
//				}
//				System.out.println("Search All Success");
		//Get All Test End		
				
				//GET ALL_FK TEST BEGIN
//				list = dao.GET_ALL_JOIN_FK();
//				for(int i=0;i<list.size();i++){
//					vo = list.get(i);
//					System.out.println("getHouseNO = \t\t" + vo.getHouseNO());
//					System.out.println("getHouseTitle = \t" + vo.getHouseTitle());
//					System.out.println("getCityNO = \t\t" + vo.getCityNO());
//					System.out.println("getBoroughNO = \t\t" + vo.getBoroughNO());
//					System.out.println("getHighestFloor = \t" + vo.getHighestFloor());
//					System.out.println("getNowFloor = \t\t" + vo.getNowFloor());
//					System.out.println("getHouseStatus = \t" + vo.getHouseStatus());
//					System.out.println("getHouseRent = \t\t" + vo.getHouseRent());
//					System.out.println("getHouseCharge = \t" + vo.getHouseCharge());
//					System.out.println("getWaterRate = \t\t" + vo.getWaterRate());
//					System.out.println("getPowerRate = \t\t" + vo.getPowerRate());
//					System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//					System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//					System.out.println("getTypeNO = \t\t" + vo.getTypeNO());
//					System.out.println("gethType = \t\t" + vo.gethType());
//					System.out.println("getFormNO = \t\t" + vo.getFormNO());
//					System.out.println("gethForm = \t\t" + vo.gethForm());
//					System.out.println("getHouseAddr = \t\t" + vo.getHouseAddr());
//					System.out.println("getHouseSize = \t\t" + vo.getHouseSize());
//					System.out.println();
//					System.out.println("------------------------------next---------------------------------------------");
//					System.out.println();
//				}
//				System.out.println("Search All Success");
				//GET ALL_FK TEST END
			
				//GET ONE_FK TEST BEGIN
//				vo = dao.GET_ONE_HOUSE_FK(20001);	
//				System.out.println("getHouseTitle = \t" + vo.getHouseTitle());
//				System.out.println("getCityNO = \t\t" + vo.getCityNO());
//				System.out.println("getBoroughNO = \t\t" + vo.getBoroughNO());
//				System.out.println("getHighestFloor = \t" + vo.getHighestFloor());
//				System.out.println("getNowFloor = \t\t" + vo.getNowFloor());
//				System.out.println("getHouseStatus = \t" + vo.getHouseStatus());
//				System.out.println("getHouseRent = \t\t" + vo.getHouseRent());
//				System.out.println("getHouseCharge = \t" + vo.getHouseCharge());
//				System.out.println("getWaterRate = \t\t" + vo.getWaterRate());
//				System.out.println("getPowerRate = \t\t" + vo.getPowerRate());
//				System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//				System.out.println("getHouseVideo = \t" + vo.getHouseVideo());
//				System.out.println("getTypeNO = \t\t" + vo.getTypeNO());
//				System.out.println("gethType = \t\t" + vo.gethType());
//				System.out.println("getFormNO = \t\t" + vo.getFormNO());
//				System.out.println("gethForm = \t\t" +vo.gethForm());
//				System.out.println("getHouseAddr = \t\t" + vo.getHouseAddr());
//				System.out.println("getHouseSize = \t\t" + vo.getHouseSize());
//				System.out.println("getHouseNO = \t\t" + vo.getHouseNO());
//				System.out.println("Search Success");
				//GET ONE_FK TEST END
			
			
	}
}
