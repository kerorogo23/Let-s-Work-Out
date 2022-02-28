package com.course_schedule.model;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.course.model.CourseDAO_interface;
import com.course.model.CourseJDBCDAO;
import com.course.model.CourseVO;
import com.pro.model.ProVO;


public class CourseScheduleJDBCDAO implements CourseScheduleDAO_interface {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	private static String userid = "John";
	private static String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO course_schedule(COURSE_ORDER_NO, RESERVE_TIME, COURSE_STATUS, CREATED_TIME) VALUES(?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT SCHEDULE_NO, COURSE_ORDER_NO	, RESERVE_TIME, COURSE_STATUS, CREATED_TIME FROM course_schedule order by CREATED_TIME desc ";
	private static final String GET_ONE_STMT = "SELECT SCHEDULE_NO, COURSE_ORDER_NO	, RESERVE_TIME, COURSE_STATUS, CREATED_TIME FROM course_schedule where SCHEDULE_NO = ? ";

	private static final String DELETE = "DELETE FROM course_schedule where SCHEDULE_NO = ?";
	private static final String UPDATE = "UPDATE course_schedule "
			+ "SET COURSE_ORDER_NO = ?, RESERVE_TIME = ?, COURSE_STATUS = ? WHERE SCHEDULE_NO = ?";
	
	//, CREATED_TIME = ?

	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}				
	
	@Override
	public void insert(CourseScheduleVO courseScheduleVO) {

		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, courseScheduleVO.getCourseOrderNo());
			pstmt.setTimestamp(2, courseScheduleVO.getReserveTime());
			pstmt.setInt(3, courseScheduleVO.getCourseStatus());
			pstmt.setTimestamp(4, courseScheduleVO.getCreatedTime());

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(CourseScheduleVO courseScheduleVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, courseScheduleVO.getCourseOrderNo());
			pstmt.setTimestamp(2, courseScheduleVO.getReserveTime());
			pstmt.setInt(3, courseScheduleVO.getCourseStatus());
//			pstmt.setTimestamp(4, courseScheduleVO.getCreatedTime());
			pstmt.setInt(4, courseScheduleVO.getScheduleNo());
			
			System.out.println(UPDATE);

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			
			System.out.println(UPDATE+ "catch" +e.toString());
		}
		
	}

	@Override
	public void delete(Integer ScheduleNo) {

	}
	

	@Override
	public CourseScheduleVO findByPrimaryKey(Integer ScheduleNo) {
		CourseScheduleVO courseScheduleVO = null;
		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, ScheduleNo);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				courseScheduleVO = getCourseScheduleVO(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseScheduleVO;
	}
	
	private CourseScheduleVO getCourseScheduleVO(ResultSet rs) throws SQLException {
		CourseScheduleVO courseScheduleVO = new CourseScheduleVO();
		courseScheduleVO.setScheduleNo(rs.getInt("SCHEDULE_NO"));
		courseScheduleVO.setCourseOrderNo(rs.getInt("COURSE_ORDER_NO"));
		courseScheduleVO.setReserveTime(rs.getTimestamp("RESERVE_TIME"));
		courseScheduleVO.setCourseStatus(rs.getInt("COURSE_STATUS"));
		courseScheduleVO.setCreatedTime(rs.getTimestamp("CREATED_TIME"));
		return courseScheduleVO;
	}

	
	@Override
	public List<CourseScheduleVO> getAll() {

		List<CourseScheduleVO> courseScheduleList = new ArrayList<CourseScheduleVO>();
		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CourseScheduleVO courseScheduleVO = getCourseScheduleVO(rs);
				courseScheduleList.add(courseScheduleVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseScheduleList;
	}


	
	public static void main(String[] args) {

		CourseScheduleDAO_interface dao = new CourseScheduleJDBCDAO();

		CourseScheduleVO vo = new CourseScheduleVO();
		
		vo.setCourseOrderNo(12);
		vo.setReserveTime(java.sql.Timestamp.valueOf("2025-6-01 8:00:00"));
		vo.setCourseStatus(1);
		vo.setCreatedTime(java.sql.Timestamp.valueOf("2025-12-14 8:00:00"));
		vo.setScheduleNo(11);
	
		dao.insert(vo);
		dao.update(vo);
		

		
		List<CourseScheduleVO> list = dao.getAll();
		for(CourseScheduleVO vo3:list) {
			
			System.out.println(vo3);
			
		}
		
	}

		
	}
	
	
	