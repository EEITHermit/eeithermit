package com.hermit.iii.infraction.model;

import java.util.*;

public interface InfractionDAO_interface {
	public Integer insert(InfractionVO_original inVO);

	public void update(InfractionVO_original infractionVO);

	public void delete(Integer inNO);

	public InfractionVO_original findByPrimaryKey(Integer inNO);

	public Set<InfractionVO_original> getAll();
}
