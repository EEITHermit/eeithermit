package com.hermit.iii.dispatchlist.model;

import java.util.ArrayList;
import java.util.List;

public interface DispatchListDAO_interface_hibernate {
	 public void insert(DispatchListVO dispatchListVO);
     public void update(DispatchListVO dispatchListVO);
     public void delete(Integer dlNO);
     public DispatchListVO findByPrimaryKey(Integer dlNO);
     public List<DispatchListVO> getAll();
     public String getAllForJson();
     //漢勳加
     public ArrayList<DispatchListVO> getAllByEmpNO(Integer empNO);
}
