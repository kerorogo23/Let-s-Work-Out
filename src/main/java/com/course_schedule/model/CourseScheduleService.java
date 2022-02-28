package com.course_schedule.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CourseScheduleService {

	private CourseScheduleDAO_interface dao;

	public CourseScheduleService() {
		dao = new CourseScheduleJDBCDAO();
	}
	
	public CourseScheduleVO addCourseSchedule(Integer courseOrderNo, Timestamp reserveTime, Integer courseStatus
			, Timestamp createdTime) {

		CourseScheduleVO courseScheduleVO = new CourseScheduleVO();

		courseScheduleVO.setCourseOrderNo(courseOrderNo);
		courseScheduleVO.setReserveTime(reserveTime);
		courseScheduleVO.setCourseStatus(courseStatus);
		courseScheduleVO.setCreatedTime(createdTime);
	
		dao.insert(courseScheduleVO); //資料存入資料庫

		return courseScheduleVO;
	}
	
	public CourseScheduleVO addCourseSchedule(CourseScheduleVO courseScheduleVO) {
		
		Date now= new Date();
		java.sql.Timestamp time = new Timestamp(now.getTime());
		courseScheduleVO.setCreatedTime(time);
		dao.insert(courseScheduleVO);
		return courseScheduleVO;
	}

	public CourseScheduleVO updateCourseSchedule(Integer scheduleNo, Integer courseOrderNo, Timestamp reserveTime, Integer courseStatus
			) {

		CourseScheduleVO courseScheduleVO = new CourseScheduleVO();

		courseScheduleVO.setScheduleNo(scheduleNo);
		courseScheduleVO.setCourseOrderNo(courseOrderNo);
		courseScheduleVO.setReserveTime(reserveTime);
		courseScheduleVO.setCourseStatus(courseStatus);
		
	
		
		dao.update(courseScheduleVO);

		return courseScheduleVO;
	}

	public void deleteCourseSchedule(Integer courseScheduleVO) {
		dao.delete(courseScheduleVO);
	}

	public CourseScheduleVO getOneCourseSchedule(Integer courseScheduleVO) {
		return dao.findByPrimaryKey(courseScheduleVO);
	}

	public List<CourseScheduleVO> getAll() {
		return dao.getAll();
	}


	}


