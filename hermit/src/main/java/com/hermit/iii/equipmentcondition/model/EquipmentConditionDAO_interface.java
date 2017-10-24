package com.hermit.iii.equipmentcondition.model;

import java.util.*;

public interface EquipmentConditionDAO_interface {

	public void insert(EquipmentConditionVO equipmentConditionVO);

	public void update(EquipmentConditionVO equipmentConditionVO);

	public void delete(Integer houseNO);

	public EquipmentConditionVO findByPrimaryKey(Integer houseNO);

	public Set<EquipmentConditionVO> getAll();
}
