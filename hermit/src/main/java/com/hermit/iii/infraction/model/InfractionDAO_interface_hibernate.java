package com.hermit.iii.infraction.model;

import java.util.*;

public interface InfractionDAO_interface_hibernate {
	public Integer insert(InfractionVO inVO);

	public void update(InfractionVO infractionVO);

	public void delete(Integer inNO);

	public InfractionVO findByPrimaryKey(Integer inNO);

	public Set<InfractionVO> getAll();
}
