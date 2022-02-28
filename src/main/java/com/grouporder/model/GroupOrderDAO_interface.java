package com.grouporder.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface GroupOrderDAO_interface{
	public void insert(GroupOrderVO groupOrderVO);
	public void update(GroupOrderVO groupOrderVO);
	public void updateGroupOrderStatus(Integer groupTimeNo, Integer groupOrderStatus);
	public void delete(Integer groupOrderNo);
	public GroupOrderVO findByPrimaryKey(Integer groupOrderNo);
	public GroupOrderVO findGroupOrder(Integer memId, Integer groupTimeNo, Timestamp groupOrderTime);
	public Set<GroupOrderVO> findGroupOrderCount(Integer groupTimeNo, Integer groupOrderStatus);
	public List<GroupOrderVO> getAll();
	public Set<GroupOrderVO> getGroupOrdersByMem(Integer memId);
	public Set<GroupOrderVO> getGroupOrdersByGroupOrderStatus(Integer groupOrderStatus);
}
