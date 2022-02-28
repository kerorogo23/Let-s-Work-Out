package com.worker.model;

import java.util.*;

public interface WorkerDAO_interface {
	public void insert(WorkerVO workerVO);
	public void update(WorkerVO workerVO);
    public void updatePw(Integer work_id, String w_pw);
	public void delete(Integer work_id);
	public WorkerVO findByPrimaryKey(Integer work_id);
    public WorkerVO findByAcc(String w_acc);
	public List<WorkerVO> getAll();
}
