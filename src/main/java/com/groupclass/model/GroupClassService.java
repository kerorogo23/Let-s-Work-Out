package com.groupclass.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import com.grouphour.model.GroupHourVO;

public class GroupClassService {
	private GroupClassDAO_interface dao;
	
	public GroupClassService() {
		dao = new GroupClassJDBCDAO();
	}
	
	public GroupClassVO addGroupClass(Integer proId, Integer classTypeNo, String className, String loc, Integer groupMax, Integer groupMin, Integer groupClassPrice, String groupClassDetail, byte[] groupClassPic){
		GroupClassVO groupClassVO = new GroupClassVO();
		
		groupClassVO.setProId(proId);
		groupClassVO.setClassTypeNo(classTypeNo);
		groupClassVO.setClassName(className);
		groupClassVO.setLoc(loc);
		groupClassVO.setGroupMax(groupMax);
		groupClassVO.setGroupMin(groupMin);
		groupClassVO.setGroupClassPrice(groupClassPrice);
		groupClassVO.setGroupClassDetail(groupClassDetail);
		groupClassVO.setGroupClassPic(groupClassPic);
		
		dao.insert(groupClassVO);
		
		return groupClassVO;
	}
	public GroupClassVO updateGroupClass(Integer groupClassNo, Integer proId, Integer classTypeNo, String className, String loc, Integer groupMax, Integer groupMin, Integer groupClassPrice, String groupClassDetail, byte[] groupClassPic) {
		GroupClassVO groupClassVO = new GroupClassVO();
		
		groupClassVO.setGroupClassNo(groupClassNo);
		groupClassVO.setProId(proId);
		groupClassVO.setClassTypeNo(classTypeNo);
		groupClassVO.setClassName(className);
		groupClassVO.setLoc(loc);
		groupClassVO.setGroupMax(groupMax);
		groupClassVO.setGroupMin(groupMin);
		groupClassVO.setGroupClassPrice(groupClassPrice);
		groupClassVO.setGroupClassDetail(groupClassDetail);
		groupClassVO.setGroupClassPic(groupClassPic);
		
		dao.update(groupClassVO);
		return groupClassVO;
	}
	public void deleteGroupClass(Integer groupClassNo) {
		dao.delete(groupClassNo);
	}
	
	public GroupClassVO getOneGroupClass(Integer groupClassNo) {
		return dao.findByPrimaryKey(groupClassNo);
	}
	
	public List<GroupClassVO> getAll(){
		return dao.getAll();
	}
	public Set<GroupHourVO> getGroupHoursByGroupClassNo(Integer groupClassNo) {
		return dao.getGroupHoursByGroupClassNo(groupClassNo);
	}

	public Set<GroupClassVO> getGroupClassesByProId(Integer proId) {
		return dao.getGroupClassesByProId(proId);
	}
	
}
