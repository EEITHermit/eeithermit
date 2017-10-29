package com.hermit.iii.equipmentcondition.model;

import java.util.*;

public interface EquipmentConditionDAO_interface {

	public void insert(EquipmentConditionVO_orignal eqiupmentConditionVO_orignal);
					   
	public void update(EquipmentConditionVO_orignal eqiupmentConditionVO_orignal);

	public void delete(Integer houseNO);

	public EquipmentConditionVO_orignal findByPrimaryKey(Integer houseNO);

	public Set<EquipmentConditionVO_orignal> getAll();
}
