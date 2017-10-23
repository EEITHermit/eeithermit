package com.hermit.iii.housepicture.model;

import java.util.List;

public interface HousePictureDAO_interface {
	public void insert(HousePictureVO housePictureVO);
    public void update(HousePictureVO housePictureVO);
    public void delete(Integer formNO);
    public HousePictureVO findByPrimaryKey(Integer formNO);
    public List<HousePictureVO> getAll();
}
