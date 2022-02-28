package com.course_schedule.model;

import java.util.*;

public interface CourseScheduleDAO_interface {
    public void insert(CourseScheduleVO courseScheduleVO);
    public void update(CourseScheduleVO courseScheduleVO);
    public void delete(Integer ScheduleNo);
    public CourseScheduleVO findByPrimaryKey(Integer ScheduleNo);
    public List<CourseScheduleVO> getAll();

}
