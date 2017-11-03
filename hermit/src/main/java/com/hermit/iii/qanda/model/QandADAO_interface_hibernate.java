package com.hermit.iii.qanda.model;

import java.util.*;

public interface QandADAO_interface_hibernate {

	public void insert(QandAVO qandaVO);

	public void update(QandAVO qandaVO);

	public void delete(Integer qaNO);

	public QandAVO findByPrimaryKey(Integer qaNO);

	public Set<QandAVO> getAll();
	//漢勳加
	public ArrayList<QandAVO> getAllByMemberNO(Integer memNO);
	//漢勳加
	public ArrayList<QandAVO> getAllByBoroughNO0(Integer boroughNO);
	//漢勳加
	public ArrayList<QandAVO> getAllByBoroughNO1(Integer boroughNO);
	//漢勳加
	public ArrayList<QandAVO> getAllNotDeal();
	//漢勳加
	public ArrayList<QandAVO> getAllDealed();
}
