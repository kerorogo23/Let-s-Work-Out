package com.work_power.model;

import java.util.List;

public class Work_powerService {
	private Work_powerDAO_interface dao;

	public Work_powerService() {
		dao = new Work_powerJDBCDAO();
  }
	public Work_powerVO addWork_power(Integer work_id, Integer power_id) {

		Work_powerVO worker_powerVO = new Work_powerVO();
		worker_powerVO.setWork_id(work_id);
		worker_powerVO.setPower_id(power_id);		
		dao.insert(worker_powerVO);

		return worker_powerVO;
	}

	public void delete(Integer work_id) {
		dao.deleteAllByWork(work_id);
	}

	public List<Work_powerVO> getByWorker(Integer work_id) {
		return dao.getByWorker(work_id);
	}
	
	public List<Work_powerVO> getByPower(Integer power_id) {
		return dao.getByPower(power_id);
	}

	public List<Work_powerVO> getAll() {
		return dao.getAll();
	}
}