package com.hermit.iii.equipmentcondition.model;

import java.util.*;

public class EquipmentConditionService {

	private EquipmentConditionDAO_interface dao;

	public EquipmentConditionService() {
		dao = new EquipmentConditionJNDIDAO();
	}

	// 新增service
	public EquipmentConditionVO addEquipmentCondition(Integer houseNO, Byte TV, Byte aircondition, Byte refrigerator,
			Byte waterHeater, Byte gas, Byte theFourthStation, Byte net, Byte washing, Byte bed, Byte wardrobe,
			Byte sofa, Byte parking, Byte elevator, Byte balcony, Byte permitCook, Byte pet, Byte closeMRT) {

		EquipmentConditionVO equipmentConditionVO = new EquipmentConditionVO();

		equipmentConditionVO.setHouseNO(houseNO);
		equipmentConditionVO.setTV(TV);
		equipmentConditionVO.setAircondition(aircondition);
		equipmentConditionVO.setRefrigerator(refrigerator);
		equipmentConditionVO.setWaterHeater(waterHeater);
		equipmentConditionVO.setGas(gas);
		equipmentConditionVO.setTheFourthStation(theFourthStation);
		equipmentConditionVO.setNet(net);
		equipmentConditionVO.setWashing(washing);
		equipmentConditionVO.setBed(bed);
		equipmentConditionVO.setWardrobe(wardrobe);
		equipmentConditionVO.setSofa(sofa);
		equipmentConditionVO.setParking(parking);
		equipmentConditionVO.setElevator(elevator);
		equipmentConditionVO.setBalcony(balcony);
		equipmentConditionVO.setPermitCook(permitCook);
		equipmentConditionVO.setPet(pet);
		equipmentConditionVO.setCloseMRT(closeMRT);

		dao.insert(equipmentConditionVO);

		return equipmentConditionVO;
	}

	// 修改service
	public EquipmentConditionVO updateEquipmentCondition(Integer houseNO, Byte TV, Byte aircondition, Byte refrigerator,
			Byte waterHeater, Byte gas, Byte theFourthStation, Byte net, Byte washing, Byte bed, Byte wardrobe,
			Byte sofa, Byte parking, Byte elevator, Byte balcony, Byte permitCook, Byte pet, Byte closeMRT) {

		EquipmentConditionVO equipmentConditionVO = new EquipmentConditionVO();

		equipmentConditionVO.setHouseNO(houseNO);
		equipmentConditionVO.setTV(TV);
		equipmentConditionVO.setAircondition(aircondition);
		equipmentConditionVO.setRefrigerator(refrigerator);
		equipmentConditionVO.setWaterHeater(waterHeater);
		equipmentConditionVO.setGas(gas);
		equipmentConditionVO.setTheFourthStation(theFourthStation);
		equipmentConditionVO.setNet(net);
		equipmentConditionVO.setWashing(washing);
		equipmentConditionVO.setBed(bed);
		equipmentConditionVO.setWardrobe(wardrobe);
		equipmentConditionVO.setSofa(sofa);
		equipmentConditionVO.setParking(parking);
		equipmentConditionVO.setElevator(elevator);
		equipmentConditionVO.setBalcony(balcony);
		equipmentConditionVO.setPermitCook(permitCook);
		equipmentConditionVO.setPet(pet);
		equipmentConditionVO.setCloseMRT(closeMRT);

		dao.update(equipmentConditionVO);

		return equipmentConditionVO;
	}

	// 刪除service
	public void deleteEquipmentCondition(Integer houseNO) {
		dao.delete(houseNO);
	}

	// 查詢一筆service
	public EquipmentConditionVO getOneEquipmentCondition(Integer houseNO) {
		return dao.findByPrimaryKey(houseNO);
	}

	// 查詢全部service
	public Set<EquipmentConditionVO> getAll() {
		return dao.getAll();
	}
}