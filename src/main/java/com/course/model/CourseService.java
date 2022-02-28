package com.course.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pro.model.ProVO;

public class CourseService {

	private CourseDAO_interface dao;

	public CourseService() {
		dao = new CourseJDBCDAO();
	}

	public List<CourseVO> findCourseListByProId(Integer proId) {
		List<CourseVO> list = dao.getAll();
		List<CourseVO> newList = new ArrayList<CourseVO>();
		
		for(CourseVO vo:list) {
			if(vo.getProId().intValue() == proId) {
				newList.add(vo);
			}
		}
		
		return newList;
	}
	
	public CourseVO findByPrimaryKey(Integer CourseId) {
		return dao.findByPrimaryKey(CourseId);
	}

}
