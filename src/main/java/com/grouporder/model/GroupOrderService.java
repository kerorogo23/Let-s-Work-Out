package com.grouporder.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class GroupOrderService {
	private GroupOrderDAO_interface dao;
		
	public GroupOrderService() {
		dao = new GroupOrderJDBCDAO();
	}
	
	public GroupOrderVO addGroupOrder(Integer memId, Integer groupTimeNo, Timestamp groupOrderTime) {
		GroupOrderVO groupOrderVO = new GroupOrderVO();
		groupOrderVO.setMemId(memId);
		groupOrderVO.setGroupTimeNo(groupTimeNo);
		groupOrderVO.setGroupOrderTime(groupOrderTime);
		dao.insert(groupOrderVO);
		
		return groupOrderVO;
	}
	
	public GroupOrderVO updateGroupOrder(Integer groupOrderNo, Integer memId, Integer groupTimeNo, Timestamp groupOrderTime, Integer groupOrderStatus) {
		GroupOrderVO groupOrderVO = new GroupOrderVO();
		
		groupOrderVO.setGroupOrderNo(groupOrderNo);
		groupOrderVO.setMemId(memId);;
		groupOrderVO.setGroupTimeNo(groupTimeNo);
		groupOrderVO.setGroupOrderTime(groupOrderTime);;
		groupOrderVO.setGroupOrderStatus(groupOrderStatus);
		
		dao.update(groupOrderVO);
		return groupOrderVO;
	}
	public void updateGroupOrderStatus(Integer groupTimeNo, Integer groupOrderStatus) {		
		dao.updateGroupOrderStatus(groupTimeNo, groupOrderStatus);
	}
	
	public void deleteGroupOrder(Integer groupOrderNo) {
		dao.delete(groupOrderNo);
	}

	public GroupOrderVO getOneGroupOrder(Integer groupOrderNo) {
		return dao.findByPrimaryKey(groupOrderNo);
	}
	public GroupOrderVO getOneGroupOrder2(Integer memId, Integer groupTimeNo, Timestamp groupOrderTime) {
		return dao.findGroupOrder( memId, groupTimeNo, groupOrderTime);
	}
	public Set<GroupOrderVO> getOneGroupOrderCount(Integer groupTimeNo, Integer groupOrderStatus) {
		return dao.findGroupOrderCount(groupTimeNo, groupOrderStatus);
	}
	public List<GroupOrderVO> getAll() {
		return dao.getAll();
	}
	public Set<GroupOrderVO> getGroupOrdersByMemId(Integer MemId) {
		return dao.getGroupOrdersByMem(MemId);
	}
	
	public Set<GroupOrderVO> getGroupOrdersByGroupOrderStatus(Integer groupOrderStatus){
		return dao.getGroupOrdersByGroupOrderStatus(groupOrderStatus);
	}
	
}
