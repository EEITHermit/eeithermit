package com.hermit.iii.equipmentcondition.model;

import java.util.List;
import java.util.Set;

public interface EquipmentConditionDAO_interface_hibernate {

	public void insert(EquipmentConditionVO eqiupmentConditionVO);
					   
	public void update(EquipmentConditionVO eqiupmentConditionVO);

	public void delete(Integer houseNO);

	public EquipmentConditionVO findByPrimaryKey(Integer houseNO);

	public List<EquipmentConditionVO> getAll();
}
