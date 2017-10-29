package com.hermit.iii.equipmentcondition.model;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.hermit.iii.house.model.HouseVO;
import com.hermit.iii.util.HibernateUtil;

public class EquipmentConditionDAO_hibernate implements EquipmentConditionDAO_interface_hibernate{
		
	private static final String GET_ALL_STMT =	"from EquipmentConditionVO order by houseNO";
	@Override
	public void insert(EquipmentConditionVO eqiupmentConditionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(eqiupmentConditionVO);;
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(EquipmentConditionVO eqiupmentConditionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(eqiupmentConditionVO);;
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
			EquipmentConditionVO eqiupmentConditionVO = (EquipmentConditionVO) session.get(EquipmentConditionVO.class, houseNO);
			session.delete(eqiupmentConditionVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public EquipmentConditionVO findByPrimaryKey(Integer houseNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			EquipmentConditionVO eqiupmentConditionVO = (EquipmentConditionVO) session.get(EquipmentConditionVO.class, houseNO);
			session.getTransaction().commit();
			return eqiupmentConditionVO;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public List<EquipmentConditionVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<EquipmentConditionVO> list = session.createQuery(GET_ALL_STMT).getResultList();
			session.getTransaction().commit();
			return list;
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	public static void main(String args[]){
		EquipmentConditionVO vo = new EquipmentConditionVO();
		EquipmentConditionDAO_hibernate dao = new EquipmentConditionDAO_hibernate();
//		vo.setHouseNO(20001);
//		vo.setTV((byte) 1);
//		vo.setAircondition((byte) 1);
//		vo.setRefrigerator((byte) 1);
//		vo.setWaterHeater((byte) 1);
//		vo.setGas((byte) 1);
//		vo.setTheFourthStation((byte) 1);
//		vo.setNet((byte) 1);
//		vo.setWashing((byte) 1);
//		vo.setBed((byte) 1);
//		vo.setWardrobe((byte) 1);
//		vo.setSofa((byte) 1);
//		vo.setParking((byte) 1);
//		vo.setElevator((byte) 1);
//		vo.setBalcony((byte) 1);
//		vo.setPermitCook((byte) 1);
//		vo.setPet((byte) 1);
//		vo.setCloseMRT((byte) 1);
//		dao.insert(vo);

		// 修改初始資料第一筆
//		EquipmentConditionVO EquipmentConditionVO2 = new EquipmentConditionVO();
//		EquipmentConditionVO2.setHouseNO(20001);
//		EquipmentConditionVO2.setTV((byte) 1);
//		EquipmentConditionVO2.setAircondition((byte) 1);
//		EquipmentConditionVO2.setRefrigerator((byte) 1);
//		EquipmentConditionVO2.setWaterHeater((byte) 1);
//		EquipmentConditionVO2.setGas((byte) 1);
//		EquipmentConditionVO2.setTheFourthStation((byte) 1);
//		EquipmentConditionVO2.setNet((byte) 1);
//		EquipmentConditionVO2.setWashing((byte) 1);
//		EquipmentConditionVO2.setBed((byte) 1);
//		EquipmentConditionVO2.setWardrobe((byte) 1);
//		EquipmentConditionVO2.setSofa((byte) 1);
//		EquipmentConditionVO2.setParking((byte) 1);
//		EquipmentConditionVO2.setElevator((byte) 1);
//		EquipmentConditionVO2.setBalcony((byte) 1);
//		EquipmentConditionVO2.setPermitCook((byte) 1);
//		EquipmentConditionVO2.setPet((byte) 1);
//		EquipmentConditionVO2.setCloseMRT((byte) 1);
//		dao.update(EquipmentConditionVO2);
//
//		// 查詢初始資料第一筆
//		EquipmentConditionVO EquipmentConditionVO3 = dao.findByPrimaryKey(20001);
//		System.out.print(EquipmentConditionVO3.getHouseNO() + ",");
//		System.out.print(EquipmentConditionVO3.getTV() + ",");
//		System.out.print(EquipmentConditionVO3.getAircondition() + ",");
//		System.out.print(EquipmentConditionVO3.getRefrigerator() + ",");
//		System.out.print(EquipmentConditionVO3.getWaterHeater() + ",");
//		System.out.print(EquipmentConditionVO3.getGas() + ",");
//		System.out.print(EquipmentConditionVO3.getTheFourthStation() + ",");
//		System.out.print(EquipmentConditionVO3.getNet() + ",");
//		System.out.print(EquipmentConditionVO3.getWashing() + ",");
//		System.out.print(EquipmentConditionVO3.getBed() + ",");
//		System.out.print(EquipmentConditionVO3.getWardrobe() + ",");
//		System.out.print(EquipmentConditionVO3.getSofa() + ",");
//		System.out.print(EquipmentConditionVO3.getParking() + ",");
//		System.out.print(EquipmentConditionVO3.getElevator() + ",");
//		System.out.print(EquipmentConditionVO3.getBalcony() + ",");
//		System.out.print(EquipmentConditionVO3.getPermitCook() + ",");
//		System.out.print(EquipmentConditionVO3.getPet() + ",");
//		System.out.println(EquipmentConditionVO3.getCloseMRT());
//		System.out.println("---------------------");
//
//		// 查詢全部
//		List<EquipmentConditionVO> list = dao.getAll();
//		for (EquipmentConditionVO EquipmentConditionVO : list) {
//			System.out.print(EquipmentConditionVO.getHouseNO() + ",");
//			System.out.print(EquipmentConditionVO.getTV() + ",");
//			System.out.print(EquipmentConditionVO.getAircondition() + ",");
//			System.out.print(EquipmentConditionVO.getRefrigerator() + ",");
//			System.out.print(EquipmentConditionVO.getWaterHeater() + ",");
//			System.out.print(EquipmentConditionVO.getGas() + ",");
//			System.out.print(EquipmentConditionVO.getTheFourthStation() + ",");
//			System.out.print(EquipmentConditionVO.getNet() + ",");
//			System.out.print(EquipmentConditionVO.getWashing() + ",");
//			System.out.print(EquipmentConditionVO.getBed() + ",");
//			System.out.print(EquipmentConditionVO.getWardrobe() + ",");
//			System.out.print(EquipmentConditionVO.getSofa() + ",");
//			System.out.print(EquipmentConditionVO.getParking() + ",");
//			System.out.print(EquipmentConditionVO.getElevator() + ",");
//			System.out.print(EquipmentConditionVO.getBalcony() + ",");
//			System.out.print(EquipmentConditionVO.getPermitCook() + ",");
//			System.out.print(EquipmentConditionVO.getPet() + ",");
//			System.out.println(EquipmentConditionVO.getCloseMRT());
//			System.out.println();
//		}
//
//		// 刪除初始資料一筆
		dao.delete(20001);

		System.out.println("Done");
	}
}
