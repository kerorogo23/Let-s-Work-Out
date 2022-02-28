package com.course.model;

import java.util.*;

import com.pro.model.ProVO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class CourseJDBCDAO implements CourseDAO_interface {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	private static String userid = "John";
	private static String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO course(PRO_ID, COURSE_PRICE, COURSE_HOURS, COURSE_DETAIL) VALUES(?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT COURSE_ID, PRO_ID, COURSE_PRICE, COURSE_HOURS, COURSE_DETAIL FROM course order by COURSE_PRICE asc ";
	private static final String GET_ONE_STMT = "SELECT COURSE_ID, PRO_ID, COURSE_PRICE, COURSE_HOURS, COURSE_DETAIL FROM course where COURSE_ID = ? ";

	private static final String DELETE = "DELETE FROM course where COURSE_ID = ?";
	private static final String UPDATE = "UPDATE course "
			+ "SET PRO_ID = ?, COURSE_PRICE = ?, COURSE_HOURS = ?, COURSE_DETAIL = ? WHERE COURSE_ID = ?";
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(CourseVO coursePriceVO) {

		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, coursePriceVO.getProId());
			pstmt.setInt(2, coursePriceVO.getCoursePrice());
			pstmt.setInt(3, coursePriceVO.getCourseHours());
			pstmt.setString(4, coursePriceVO.getCourseDetail());

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(CourseVO coursePriceVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, coursePriceVO.getProId());
			pstmt.setInt(2, coursePriceVO.getCoursePrice());
			pstmt.setInt(3, coursePriceVO.getCourseHours());
			pstmt.setString(4, coursePriceVO.getCourseDetail());
			pstmt.setInt(5, coursePriceVO.getCourseId());

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer CourseId) {

	}

	@Override
	public CourseVO findByPrimaryKey(Integer CourseId) {
		CourseVO coursePriceVO = null;
		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, CourseId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
//				coursePriceVO = new CoursePriceVO();
//				coursePriceVO.setCourseId(rs.getInt("COURSE_ID"));
//				coursePriceVO.setProId(rs.getInt("PRO_ID"));
//				coursePriceVO.setCoursePrice(rs.getInt("COURSE_PRICE"));
//				coursePriceVO.setCourseHours(rs.getInt("COURSE_HOURS"));
//				coursePriceVO.setCourseDetail(rs.getString("COURSE_DETAIL"));
				coursePriceVO = getCoursePriceVO (rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coursePriceVO;
	}
	
	private CourseVO getCoursePriceVO(ResultSet rs) throws SQLException {
		CourseVO coursePriceVO = new CourseVO();
		coursePriceVO.setCourseId(rs.getInt("COURSE_ID"));
		coursePriceVO.setProId(rs.getInt("PRO_ID"));
		coursePriceVO.setCoursePrice(rs.getInt("COURSE_PRICE"));
		coursePriceVO.setCourseHours(rs.getInt("COURSE_HOURS"));
		coursePriceVO.setCourseDetail(rs.getString("COURSE_DETAIL"));
		return coursePriceVO;
	}
	
	

	@Override
	public List<CourseVO> getAll() {

		List<CourseVO> coursePriceList = new ArrayList<CourseVO>();
		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CourseVO coursePriceVO = getCoursePriceVO(rs);
				coursePriceList.add(coursePriceVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coursePriceList;
	}

	
	public static void main(String[] args) {

		CourseDAO_interface dao = new CourseJDBCDAO();

//		CoursePriceVO vo = new CoursePriceVO();
//		vo.setProId(3001);
//		vo.setCourseHours(1);
//		vo.setCoursePrice(1500);
//		vo.setCourseDetail("終於成功了-2");
//		vo.setCourseId(1);
//		dao.insert(vo);
//		dao.update(vo);
		
//		CoursePriceVO vo2 = dao.findByPrimaryKey(1);
//		System.out.println(vo2);
		
		List<CourseVO> list = dao.getAll();
		for(CourseVO vo:list) {
			
			System.out.println(vo);
			
		}
		
	}

}