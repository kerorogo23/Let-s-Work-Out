package com.power_list.model;

import java.util.*;

public interface Power_listDAO_interface {
	public void insert(Power_listVO powerlistVO);
	public void update(Power_listVO powerlistVO);
	public void delete(Integer power_id);
	public Power_listVO findByPrimaryKey(Integer power_id);
	public List<Power_listVO> getAll();
}
