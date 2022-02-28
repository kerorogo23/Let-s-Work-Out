package com.grouphour.model;

import java.util.List;
import java.util.Set;
import com.grouporder.model.GroupOrderVO;

public interface GroupHourDAO_interface {
	public void insert(GroupHourVO groupHourVO);
	public void update(GroupHourVO groupHourVO);
	public void delete(Integer groupTimeNo);
	public GroupHourVO findByPrimaryKey(Integer groupTimeNo);
	public List<GroupHourVO> getAll();
	public Set<GroupOrderVO> getGroupOrdersByGroupHour(Integer groupTimeNo);
	public Set<GroupHourVO> getGroupHoursByCourseStatus(Integer courseStatus);
}
