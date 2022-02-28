package com.worker.model;

import java.util.List;

public class WorkerService {

	private WorkerDAO_interface dao;

	public WorkerService() {
		dao = new WorkerJDBCDAO();
	}
	public WorkerVO addWorker(String w_name, String w_acc, String w_pw, String email) {

		WorkerVO workerVO = new WorkerVO();
		workerVO.setW_name(w_name);
		workerVO.setW_acc(w_acc);
		workerVO.setW_pw(w_pw);
		workerVO.setEmail(email);
		dao.insert(workerVO);

		return workerVO;
	}

	public WorkerVO updateWorker(String w_name, String w_acc, String w_pw, String email, Integer all_auth,
			Integer work_id) {

		WorkerVO workerVO = new WorkerVO();
		workerVO.setW_name(w_name);
		workerVO.setW_acc(w_acc);
		workerVO.setW_pw(w_pw);
		workerVO.setEmail(email);
		workerVO.setAll_auth(all_auth);
		workerVO.setWork_id(work_id);
		dao.update(workerVO);

		return workerVO;
	}
	public void deleteWorker(Integer work_id) {
		dao.delete(work_id);
	}

	public WorkerVO getOneWorker(Integer work_id) {
		return dao.findByPrimaryKey(work_id);
	}

	public List<WorkerVO> getAll() {
		return dao.getAll();
	}
	public WorkerVO getOneByAcc(String w_acc) {
		return dao.findByAcc(w_acc);
	}
	public void updatePw(Integer work_id, String w_pw) {
		dao.updatePw(work_id, w_pw);
	}


}