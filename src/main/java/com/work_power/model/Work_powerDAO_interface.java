package com.work_power.model;

import java.util.*;


public interface Work_powerDAO_interface {
	public void insert(Work_powerVO work_powerVO);
	public void update(Work_powerVO work_powerVO);
	public void deleteAllByWork(Integer work_id);
    public List<Work_powerVO> getByWorker(Integer work_id);
    public List<Work_powerVO> getByPower(Integer power_id);
    public List<Work_powerVO> getAll();

}
