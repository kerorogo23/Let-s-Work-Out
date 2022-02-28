package com.course_order.model;

import java.util.*;

public interface CourseOrderDAO_interface {
    public void insert(CourseOrderVO courseOrderVO);
    public void update(CourseOrderVO courseOrderVO);
    public void delete(Integer courseOrderNo);
    public CourseOrderVO findByPrimaryKey(Integer courseOrderNo);
    public List<CourseOrderVO> getAll();

}

