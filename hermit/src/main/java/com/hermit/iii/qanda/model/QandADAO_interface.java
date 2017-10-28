package com.hermit.iii.qanda.model;

import java.util.*;

public interface QandADAO_interface {

	public void insert(QandAVO_original qandaVO);

	public void update(QandAVO_original qandaVO);

	public void delete(Integer qaNO);

	public QandAVO_original findByPrimaryKey(Integer qaNO);

	public Set<QandAVO_original> getAll();
	//漢勳加
	public ArrayList<QandAVO_original> getAllByMemberNO(Integer memNO);
	//漢勳加
	public ArrayList<QandAVO_original> getAllByBoroughNO(Integer boroughNO);
}
