package com.power_list.model;

import java.util.*;
import java.sql.*;

public class Power_listJDBCDAO implements Power_listDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	String userid = "John";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO power_list (power_id, power_name) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM power_list order by power_id";
	private static final String GET_ONE_STMT = "SELECT * FROM power_list where power_id = ?";
	private static final String DELETE = "DELETE FROM power_list where power_id = ?";
	private static final String UPDATE = "UPDATE power_list set POWER_NAME=?  where POWER_ID = ?";
	
	@Override
	public void insert(Power_listVO power_listVO) {

		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, power_listVO. getPower_id());
			pstmt.setString(2, power_listVO.getPower_name());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void update(Power_listVO power_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, power_listVO.getPower_name());
			pstmt.setInt(2, power_listVO.getPower_id());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void delete(Integer power_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, power_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public Power_listVO findByPrimaryKey(Integer power_id) {

		Power_listVO powerlistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, power_id);

			rs = pstmt.executeQuery();
			// empVo �]�٬� Domain objects
			while (rs.next()) {
				powerlistVO = new Power_listVO();
				powerlistVO.setPower_id(rs.getInt("POWER_ID"));
				powerlistVO.setPower_name(rs.getString("POWER_NAME"));
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//				 Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return powerlistVO;
	}
	@Override
	public List<Power_listVO> getAll() {

		List<Power_listVO> list = new ArrayList<Power_listVO>();
		Power_listVO powerlistVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				powerlistVO = new Power_listVO();
				powerlistVO.setPower_id(rs.getInt("POWER_ID"));
				powerlistVO.setPower_name(rs.getString("POWER_NAME"));
				list.add(powerlistVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
//	public static void main(String[] args) {
//
//		Power_listJDBCDAO dao = new Power_listJDBCDAO();
//
//		// 新增
//		Power_listVO powerlistVO1 = new Power_listVO();
//		powerlistVO1.setPower_id(9007);
//		powerlistVO1.setPower_name("OO");
//		dao.insert(powerlistVO1);

	
		// 修改
//		Power_listVO powerlistVO1 = new Power_listVO();
//		powerlistVO1.setPower_name("XX");
//		powerlistVO1.setPower_id(9007);
//		dao.update(powerlistVO1);
		
		
		// 刪除
//		dao.delete(9007);
		
		// 查詢
//		Power_listVO powerlistVO3 = dao.findByPrimaryKey(9001);
//		System.out.print(powerlistVO3.getPower_id() + ",");
//		System.out.print(powerlistVO3.getPower_name() + ",");
//		System.out.println("---------------------");
//		
		// 查詢
//		List<Power_listVO> list = dao.getAll();
//		for (Power_listVO powerlistVO : list) {
//		System.out.print(powerlistVO.getPower_id() + ",");
//		System.out.print(powerlistVO.getPower_name() + ",");
//		System.out.println();}
//}
}
