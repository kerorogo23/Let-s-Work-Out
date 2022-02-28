package com.course.model;
import java.util.*;

public interface CourseDAO_interface {
    public void insert(CourseVO coursePriceVO);
    public void update(CourseVO coursePriceVO);
    public void delete(Integer couserNO);
    public CourseVO findByPrimaryKey(Integer couserNO);
    public List<CourseVO> getAll();

}

