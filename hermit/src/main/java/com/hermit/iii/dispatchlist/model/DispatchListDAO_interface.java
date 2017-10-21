package com.hermit.iii.dispatchlist.model;

import java.util.List;

public interface DispatchListDAO_interface {
	  public void insert(DispatchListVO DispatchListVO);
      public void update(DispatchListVO DispatchListVO);
      public void delete(Integer dlNO);
      public DispatchListVO findByPrimaryKey(Integer dlNO);
      public List<DispatchListVO> getAll();
      public String getAllForJson();
}
