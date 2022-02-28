package com.pro.model;

import java.io.IOException;
import java.util.List;

public class ProService {

	private ProDAO_interface dao;

	public ProService() {
		dao = new ProJDBCDAO();
	}

	public ProVO getOnePro(Integer proId) {
		ProVO proVO = dao.findByPrimaryKey(proId);
		
		return proVO;
	}
	
	
	public ProVO updatePro(ProVO proVO) {
		
		dao.update(proVO);
		return proVO;
		
	}
	
	public ProVO addPro(ProVO proVO) {
		dao.insert(proVO);
		return proVO;
		//TODO 需解釋
	}
	
	public void deletePro(Integer proId) {
		dao.delete(proId);
		
	}
	
	
	public List<ProVO> getAll(){
		return dao.getAll();
	}
}
