package com.pro.model;

import java.util.*;

public interface ProDAO_interface {
    public  void insert(ProVO proVO);
    public void update(ProVO proVO);
    public void delete(Integer proId);
    public ProVO findByPrimaryKey(Integer proId);
    public List<ProVO> getAll();

}
