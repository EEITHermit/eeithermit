package com.hermit.iii.dispatchlist.model;

import java.util.List;

public interface DispatchListDAO_interface {
	  public void insert(DispatchListVO_orignal DispatchListVO);
      public void update(DispatchListVO_orignal DispatchListVO);
      public void delete(Integer dlNO);
      public DispatchListVO_orignal findByPrimaryKey(Integer dlNO);
      public List<DispatchListVO_orignal> getAll();
      public String getAllForJson();
}
