package com.course_order.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pro.model.ProVO;


public class CourseOrderJDBCDAO implements CourseOrderDAO_interface {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	private static String userid = "John";
	private static String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO course_order(COURSE_ID, MEM_ID, COURSE_ORDER_HOURS, COURSE_ORDER_PRICE, COURSE_ORDER_STATUS, CREATED_TIME) VALUES(?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT COURSE_ORDER_NO, COURSE_ID, MEM_ID, COURSE_ORDER_HOURS, COURSE_ORDER_PRICE, COURSE_ORDER_STATUS, CREATED_TIME FROM course_order order by CREATED_TIME desc ";
	private static final String GET_ONE_STMT = "SELECT COURSE_ORDER_NO, COURSE_ID, MEM_ID, COURSE_ORDER_HOURS, COURSE_ORDER_PRICE, COURSE_ORDER_STATUS, CREATED_TIME FROM course_order where COURSE_ORDER_NO = ? ";

//	private static final String DELETE = "DELETE FROM course_order where COURSE_ORDER_NO = ?";
	private static final String UPDATE = "UPDATE course_order "
			+ "SET COURSE_ID = ?, MEM_ID = ?, COURSE_ORDER_HOURS = ?, COURSE_ORDER_PRICE = ?, COURSE_ORDER_STATUS= ? WHERE COURSE_ORDER_NO = ?";

	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void insert(CourseOrderVO courseOrderVO) {

		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);
			System.out.println("insert" + INSERT_STMT);
			
			//Timestamp reserveTime = null;
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date time = new Date();
			Timestamp createdTime = new Timestamp(time.getTime());
			
			pstmt.setInt(1, courseOrderVO.getCourseId());
			pstmt.setInt(2, courseOrderVO.getMemId());
			pstmt.setInt(3, courseOrderVO.getCourseOrderHours());
			pstmt.setInt(4, courseOrderVO.getCourseOrderPrice());
			pstmt.setInt(5, courseOrderVO.getCourseOrderStatus());
			//pstmt.setTimestamp(6, courseOrderVO.getCreatedTime());
			pstmt.setTimestamp(6, createdTime);

			System.out.println(INSERT_STMT);
			pstmt.execute();
			

		} catch (SQLException e) {
			e.printStackTrace();
			
			System.out.println(INSERT_STMT+"catch"+e.toString());
		}

	}

	@Override
	public void update(CourseOrderVO courseOrderVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, courseOrderVO.getCourseId());
			pstmt.setInt(2, courseOrderVO.getMemId());
			pstmt.setInt(3, courseOrderVO.getCourseOrderHours());
			pstmt.setInt(4, courseOrderVO.getCourseOrderPrice());
			pstmt.setInt(5, courseOrderVO.getCourseOrderStatus());
//			pstmt.setTimestamp(6, courseOrderVO.getCreatedTime());
			pstmt.setInt(6, courseOrderVO.getCourseOrderNo());
			

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer CourseOrderNo) {

	}
			

	@Override
	public CourseOrderVO findByPrimaryKey(Integer courseOrderNo) {
		CourseOrderVO courseOrderVO = null;
		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, courseOrderNo);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				courseOrderVO = getCourseOrderVO (rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return courseOrderVO;
			
	}
	
	
	private CourseOrderVO getCourseOrderVO(ResultSet rs) throws SQLException {
		CourseOrderVO courseOrderVO = new CourseOrderVO();
		courseOrderVO.setCourseOrderNo(rs.getInt("COURSE_ORDER_NO"));
		courseOrderVO.setCourseId(rs.getInt("COURSE_ID"));
		courseOrderVO.setMemId(rs.getInt("MEM_ID"));
		courseOrderVO.setCourseOrderHours(rs.getInt("COURSE_ORDER_HOURS"));
		courseOrderVO.setCourseOrderPrice(rs.getInt("COURSE_ORDER_PRICE"));
		courseOrderVO.setCourseOrderStatus(rs.getInt("COURSE_ORDER_STATUS"));
		courseOrderVO.setCreatedTime(rs.getTimestamp("CREATED_TIME"));
		return courseOrderVO;
	}
	
	
	@Override
	public List<CourseOrderVO> getAll() {

		List<CourseOrderVO> courseOrderList = new ArrayList<CourseOrderVO>();
		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CourseOrderVO courseOrderVO = getCourseOrderVO(rs);
				courseOrderList.add(courseOrderVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseOrderList;
	}
	
	//可以從list.size()判斷list all有無資料
				
	public static void main(String[] args) {

		CourseOrderDAO_interface dao = new CourseOrderJDBCDAO();

		CourseOrderVO vo = new CourseOrderVO();
		
		vo.setCourseId(11);
		vo.setMemId(1011);
		vo.setCourseOrderHours(1);
		vo.setCourseOrderPrice(800);
		vo.setCourseOrderStatus(1);
		vo.setCreatedTime(java.sql.Timestamp.valueOf("2025-12-13 8:00:00"));
		vo.setCourseOrderNo(10);
		
		dao.insert(vo);
		dao.update(vo);
		
		CourseOrderVO vo2 = dao.findByPrimaryKey(1);
		System.out.println(vo2);
		
		List<CourseOrderVO> list = dao.getAll();
		for(CourseOrderVO vo3:list) {
			
			System.out.println(vo3);
			
		}
	}

}	