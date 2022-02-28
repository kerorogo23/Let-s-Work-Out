package com.course_order.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;

public class CourseOrderService {

	private CourseOrderDAO_interface dao;

	public CourseOrderService() {
		dao = new CourseOrderJDBCDAO();
	}
	
	public CourseOrderVO addCourseOrder(Integer courseId, Integer memId, Integer courseOrderHours,
			Integer courseOrderPrice, Integer courseOrderStatus, Timestamp createdTime) {

		CourseOrderVO courseOrderVO = new CourseOrderVO();
		
		try {
			System.out.println("CourseOrderService-addCourseOrder");
			
			courseOrderVO.setCourseId(courseId);
			courseOrderVO.setMemId(memId);
			courseOrderVO.setCourseOrderHours(courseOrderHours);
			courseOrderVO.setCourseOrderPrice(courseOrderPrice);
			courseOrderVO.setCourseOrderStatus(courseOrderStatus);
			courseOrderVO.setCreatedTime(createdTime);
		
			dao.insert(courseOrderVO); //資料存入資料庫

		} catch (Exception e) {
			 
			System.out.println("addCourseOrder catch"+e.toString());
		}
		
		return courseOrderVO;
	}
	
	public CourseOrderVO addCourseOrder(CourseOrderVO courseOrderVO) {
		
		Date now= new Date();
		java.sql.Timestamp time = new Timestamp(now.getTime());
		courseOrderVO.setCreatedTime(time);
		
		dao.insert(courseOrderVO);
		return courseOrderVO;
	}

	public CourseOrderVO updateCourseOrder(Integer courseOrderNo, Integer courseId, Integer memId, Integer courseOrderHours,
			Integer courseOrderPrice, Integer courseOrderStatus) {

		CourseOrderVO courseOrderVO = new CourseOrderVO();

		courseOrderVO.setCourseOrderNo(courseOrderNo);
		courseOrderVO.setCourseId(courseId);
		courseOrderVO.setMemId(memId);
		courseOrderVO.setCourseOrderHours(courseOrderHours);
		courseOrderVO.setCourseOrderPrice(courseOrderPrice);
		courseOrderVO.setCourseOrderStatus(courseOrderStatus);
	
		
		dao.update(courseOrderVO);

		return courseOrderVO;
	}

	public void deleteCourseOrder(Integer courseOrderNo) {
		dao.delete(courseOrderNo);
	}

	public CourseOrderVO getOneCourseOrder(Integer courseOrderNo) {
		return dao.findByPrimaryKey(courseOrderNo);
	}

	public List<CourseOrderVO> getAll() {
		return dao.getAll();
	}


	}

