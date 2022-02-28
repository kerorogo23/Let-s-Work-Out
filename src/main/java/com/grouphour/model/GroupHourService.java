package com.grouphour.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.grouporder.model.GroupOrderJDBCDAO;
import com.grouporder.model.GroupOrderService;
import com.grouporder.model.GroupOrderVO;


public class GroupHourService {
	private GroupHourDAO_interface dao;
	
	public GroupHourService() {
		dao = new GroupHourJDBCDAO();
	}
	
	public GroupHourVO addGroupHour(Integer groupClassNo, Date groupDate, String groupStartingTime, Timestamp registStartTime, Timestamp registEndTime) {
		GroupHourVO groupHourVO = new GroupHourVO();
		
		groupHourVO.setGroupClassNo(groupClassNo);;
		groupHourVO.setGroupDate(groupDate);
		groupHourVO.setGroupStartingTime(groupStartingTime);
		groupHourVO.setRegistStartTime(registStartTime);
		groupHourVO.setRegistEndTime(registEndTime);
		
		dao.insert(groupHourVO);
		
		return groupHourVO;
	}
	
	public GroupHourVO updateGroupHour(Integer groupTimeNo, Integer groupClassNo, Date groupDate, String groupStartingTime, Timestamp registStartTime, Timestamp registEndTime, Integer courseStatus) {
		GroupHourVO groupHourVO = new GroupHourVO();
		groupHourVO.setGroupTimeNo(groupTimeNo);
		groupHourVO.setGroupClassNo(groupClassNo);;
		groupHourVO.setGroupDate(groupDate);
		groupHourVO.setGroupStartingTime(groupStartingTime);
		groupHourVO.setRegistStartTime(registStartTime);
		groupHourVO.setRegistEndTime(registEndTime);
		groupHourVO.setCourseStatus(courseStatus);
		dao.update(groupHourVO);
		
		GroupOrderService groupOrderSvc = new GroupOrderService();
		GroupHourService groupHourSvc = new GroupHourService();
		Set<GroupOrderVO> set = groupHourSvc.getGroupOrdersByGroupTimeNo(groupTimeNo);
		GroupOrderJDBCDAO dao2 = new GroupOrderJDBCDAO();
		if(courseStatus==1) {
			for(GroupOrderVO aGroupOrder : set) {
				if(aGroupOrder.getGroupOrderStatus()==0) {
					aGroupOrder.setGroupOrderStatus(courseStatus);
					dao2.update(aGroupOrder);
				}
			}
			
			
		}else if(courseStatus==-1) {
			for(GroupOrderVO aGroupOrder : set) {
				if(aGroupOrder.getGroupOrderStatus()==0) {
					if(aGroupOrder.getGroupOrderStatus()==0) {
						aGroupOrder.setGroupOrderStatus(courseStatus);
						dao2.update(aGroupOrder);
					}
				}
			}
		}
		return groupHourVO;
	}
	
	public void deleteGroupHour(Integer groupTimeNo) {
		dao.delete(groupTimeNo);
	}

	public GroupHourVO getOneGroupHour(Integer groupTimeNo) {
		return dao.findByPrimaryKey(groupTimeNo);
	}
	public Set<GroupOrderVO> getGroupOrdersByGroupTimeNo(Integer groupTimeNo) {
		return dao.getGroupOrdersByGroupHour(groupTimeNo);
	}

	public List<GroupHourVO> getAll() {
		return dao.getAll();
	}
	public Set<GroupHourVO> getGroupHoursByCourseStatus(Integer courseStatus) {
		return dao.getGroupHoursByCourseStatus(courseStatus);
	}
}
