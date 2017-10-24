package com.hermit.iii.qanda.model;

import java.util.*;

public interface QandADAO_interface {

	public void insert(QandAVO qandaVO);

	public void update(QandAVO qandaVO);

	public void delete(Integer qaNO);

	public QandAVO findByPrimaryKey(Integer qaNO);

	public Set<QandAVO> getAll();
}