package com.groupclass.model;

import java.util.*;
import com.grouphour.model.GroupHourVO;

public interface GroupClassDAO_interface {
	public void insert(GroupClassVO groupClassVO);
	public void update(GroupClassVO groupClassVO);
	public void delete(Integer groupClassNo); 
	public GroupClassVO findByPrimaryKey(Integer groupClassNo); 
	public List<GroupClassVO> getAll();
	public Set<GroupHourVO> getGroupHoursByGroupClassNo(Integer groupClassNo);
	public Set<GroupClassVO> getGroupClassesByProId(Integer proId);
	
}
