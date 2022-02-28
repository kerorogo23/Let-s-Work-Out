package com.power_list.model;

import java.util.List;

public class Power_listService {
	
	private Power_listDAO_interface dao;

	public Power_listService() {
		dao = new Power_listJDBCDAO();
	}
	public Power_listVO addPower_list(Integer power_id, String power_name) {
		
		Power_listVO power_listVO = new Power_listVO();
		power_listVO.setPower_id(power_id);
		power_listVO.setPower_name(power_name);
		dao.insert(power_listVO);
		
		return power_listVO;
	}
	public Power_listVO updatePower_list(Integer power_id, String power_name) {
		
		Power_listVO power_listVO = new Power_listVO();
		power_listVO.setPower_id(power_id);
		power_listVO.setPower_name(power_name);
		dao.update(power_listVO);
		
		return power_listVO;
	}
	public void deletePower_list(Integer power_id) {
		dao.delete(power_id);
	}

	public Power_listVO getOnePower_list(Integer power_id) {
		return dao.findByPrimaryKey(power_id);
	}

	public List<Power_listVO> getAll() {
		return dao.getAll();
	}
//	public static void main(String[] args) {
//	Power_listService as = new Power_listService();
//	as.addPower_list(9007);
//	
//}
}
