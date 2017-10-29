package com.hermit.iii.housepicture.model;

import java.util.List;

public interface HousePictureDAO_interface_original {
	public void insert(HousePictureVO_original housePictureVO);
    public void update(HousePictureVO_original housePictureVO);
    public void delete(Integer housePictureNO);
    public HousePictureVO_original findByPrimaryKey(Integer housePictureNO);
    public List<HousePictureVO_original> getAll();
}
