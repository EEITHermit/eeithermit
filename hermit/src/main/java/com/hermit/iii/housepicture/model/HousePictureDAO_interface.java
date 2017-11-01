package com.hermit.iii.housepicture.model;

import java.util.List;

public interface HousePictureDAO_interface {
	public void insert(HousePictureVO housePictureVO);
    public void update(HousePictureVO housePictureVO);
    public void delete(Integer housePictureNO);
    public HousePictureVO findByPrimaryKey(Integer housePictureNO);
    public List<HousePictureVO> getAll();
    public List<HousePictureVO> getHousePictures(Integer houseNO);
}
