package com.work_power.model;

import java.util.*;
import java.sql.*;

public class Work_powerJDBCDAO implements Work_powerDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	String userid = "John";
	String passwd = "123456";
	
	    private static final String INSERT = 
			"INSERT INTO WORK_POWER (WORK_ID,POWER_ID) VALUES (?, ?)";
		private static final String GET_ALL = 
			"SELECT * FROM WORK_POWER";
		private static final String GET_BY_WORKER = 
			"SELECT WORK_ID, POWER_ID FROM WORK_POWER WHERE WORK_ID = ?";
		private static final String GET_BY_POWER = 
			"SELECT POWER_ID, WORK_ID FROM WORK_POWER WHERE POWER_ID = ?";
		private static final String DELETE_BY_WORKER = 
			"DELETE FROM WORK_POWER where WORK_ID = ?";
	@Override
	public void insert(Work_powerVO work_powerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, work_powerVO.getWork_id());
			pstmt.setInt(2, work_powerVO.getPower_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(Work_powerVO work_powerVO) {
			
	}
	@Override
	public void deleteAllByWork(Integer work_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_BY_WORKER);

			pstmt.setInt(1, work_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<Work_powerVO> getByWorker(Integer work_id) {
		List<Work_powerVO> list = new ArrayList<Work_powerVO>();
		Work_powerVO workpowerVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_WORKER);

			pstmt.setInt(1, work_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO �]�٬� Domain objects
				workpowerVO = new Work_powerVO();
				workpowerVO.setWork_id(rs.getInt("work_id"));
				workpowerVO.setPower_id(rs.getInt("power_id"));
				list.add(workpowerVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	@Override
	public List<Work_powerVO> getByPower(Integer power_id) {
		List<Work_powerVO> list = new ArrayList<Work_powerVO>();
		Work_powerVO work_powerVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_POWER);

			pstmt.setInt(1, power_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO �]�٬� Domain objects
				work_powerVO = new Work_powerVO();
				work_powerVO.setPower_id(rs.getInt("power_id"));
				work_powerVO.setWork_id(rs.getInt("work_id"));

				list.add(work_powerVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	@Override
	public List<Work_powerVO> getAll() {
		List<Work_powerVO> list = new ArrayList<Work_powerVO>();
		Work_powerVO work_powerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// workerVO �]�٬� Domain objects
				work_powerVO = new Work_powerVO();
				work_powerVO.setWork_id(rs.getInt("work_id"));
				work_powerVO.setPower_id(rs.getInt("power_id"));
				list.add(work_powerVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
//		Work_powerJDBCDAO dao = new Work_powerJDBCDAO();
//
//		// 新增
//		Work_powerVO workerVO1 = new Work_powerVO();
//		workerVO1.setWork_id(2033);
//		workerVO1.setPower_id(9001);
//
//		dao.insert(workerVO1);

	
		// 修改
//		Work_powerVO workerVO1 = new Work_powerVO();
//		workerVO1.setWork_id(2001);
//		workerVO1.setPower_id(9004);
//
//		dao.update(workerVO1);

		
		// 刪除
//		dao.delete(2010);
		
//		 查詢
//		WorkerVO workerVO3 = dao.findByPrimaryKey(2005);
//		System.out.print(workerVO3.getWork_id() + ",");
//		System.out.print(workerVO3.getW_name() + ",");
//		System.out.print(workerVO3.getW_acc() + ",");
//		System.out.print(workerVO3.getEmail() + ",");
//		System.out.print(workerVO3.getReg_date() + ",");
//		System.out.print(workerVO3.getAll_auth() + ",");
//		System.out.println("---------------------");
		
		// 查詢
//		List<WorkerVO> list = dao.getAll();
//		for (WorkerVO workerVO : list) {
//		System.out.print(workerVO.getWork_id() + ",");
//		System.out.print(workerVO.getW_name() + ",");
//		System.out.print(workerVO.getW_acc() + ",");
//		System.out.print(workerVO.getEmail() + ",");
//		System.out.print(workerVO.getReg_date() + ",");
//		System.out.print(workerVO.getAll_auth() + ",");
//		System.out.println();}
//}

}
