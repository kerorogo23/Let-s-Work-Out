package com.worker.model;

import java.util.*;
import java.sql.*;

public class WorkerJDBCDAO implements WorkerDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	String userid = "John";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO worker (w_name, w_acc, w_pw, email) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM worker order by work_id";
	private static final String GET_ONE_STMT = "SELECT * FROM worker where work_id = ?";
	private static final String GET_ONE_ACC = "SELECT WORK_ID,W_NAME,W_ACC,W_PW,EMAIL FROM WORKER WHERE W_ACC = ?";
	private static final String DELETE = "DELETE FROM worker where work_id = ?";
	private static final String UPDATE = "UPDATE worker set W_NAME=?, W_ACC=?, W_PW=?, EMAIL=?, ALL_AUTH=?  where WORK_ID = ?";
	private static final String UPDATE_PW = "UPDATE WORKER SET W_PW=? WHERE WORK_ID = ?";
	

	@Override
	public void insert(WorkerVO workerVO) {
		
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT_STMT);
		pstmt.setString(1, workerVO.getW_name());
		pstmt.setString(2, workerVO.getW_acc());
		pstmt.setString(3, workerVO.getW_pw());
		pstmt.setString(4, workerVO.getEmail());
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
	public void update(WorkerVO workerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, workerVO.getW_name());
			pstmt.setString(2, workerVO.getW_acc());
			pstmt.setString(3, workerVO.getW_pw());
			pstmt.setString(4, workerVO.getEmail());
			pstmt.setInt(5, workerVO.getAll_auth());
			pstmt.setInt(6, workerVO.getWork_id());
			
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
	public void delete(Integer work_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, work_id);

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
	public WorkerVO findByPrimaryKey(Integer work_id) {

		WorkerVO workerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, work_id);

			rs = pstmt.executeQuery();
			// empVo 嚙稽嚙誶穿蕭 Domain objects
			while (rs.next()) {
				workerVO = new WorkerVO();
				workerVO.setWork_id(rs.getInt("WORK_ID"));
				workerVO.setW_name(rs.getString("W_NAME"));
				workerVO.setW_acc(rs.getString("W_ACC"));
				workerVO.setW_pw(rs.getString("W_PW"));
				workerVO.setEmail(rs.getString("EMAIL"));
				workerVO.setReg_date(rs.getTimestamp("REG_DATE"));
				workerVO.setAll_auth(rs.getInt("ALL_AUTH"));
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
		return workerVO;
	}
	@Override
	public List<WorkerVO> getAll() {
	
			List<WorkerVO> list = new ArrayList<WorkerVO>();
			WorkerVO workerVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 嚙稽嚙誶穿蕭 Domain objects
					workerVO = new WorkerVO();
					workerVO.setWork_id(rs.getInt("WORK_ID"));
					workerVO.setW_name(rs.getString("W_NAME"));
					workerVO.setW_acc(rs.getString("W_ACC"));
					workerVO.setW_pw(rs.getString("W_PW"));
					workerVO.setEmail(rs.getString("EMAIL"));
					workerVO.setReg_date(rs.getTimestamp("REG_DATE"));
					workerVO.setAll_auth(rs.getInt("ALL_AUTH"));
					list.add(workerVO); // Store the row in the list
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
public WorkerVO findByAcc(String w_acc) {
		
		WorkerVO workerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(GET_ONE_ACC);
			
			pstmt.setString(1, w_acc);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// workerVO 也稱為 Domain objects
				workerVO = new WorkerVO();
				workerVO.setWork_id(rs.getInt("work_id"));
				workerVO.setW_name(rs.getString("w_name"));
				workerVO.setW_acc(rs.getString("w_acc"));
				workerVO.setW_pw(rs.getString("w_pw"));
				workerVO.setEmail(rs.getString("email"));

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
			return workerVO;
		}
public void updatePw(String work_id, String w_pw) {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try {
		
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(UPDATE_PW);
		
		pstmt.setString(1, w_pw);
		pstmt.setString(2, work_id);
		
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
public void updatePw(Integer work_id, String w_pw) {
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try {
		
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(UPDATE_PW);
		
		pstmt.setString(1, w_pw);
		pstmt.setInt(2, work_id);
		
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
}
	
//	public static void main(String[] args) {
//
//		WorkerJDBCDAO dao = new WorkerJDBCDAO();

		// �憓�
//		WorkerVO workerVO1 = new WorkerVO();
//		workerVO1.setWork_id(2011);
//		workerVO1.setW_name("OOOO");
//		workerVO1.setW_acc("AB123");
//		workerVO1.setW_pw("CD123");
//		workerVO1.setEmail("123@yahoo.com");
//		workerVO1.setReg_date(java.sql.Timestamp.valueOf("2000-02-22 03:33:33"));
//		dao.insert(workerVO1);

	
		// 靽格
//		WorkerVO workerVO1 = new WorkerVO();
//		workerVO1.setW_name("XXXX");
//		workerVO1.setW_acc("WEEE");
//		workerVO1.setW_pw("NOOOO");
//		workerVO1.setEmail("456@yahoo.com");
//		workerVO1.setAll_auth(0);
//		workerVO1.setWork_id(2011);
//		dao.update(workerVO1);
		
		// ��
//		dao.delete(2011);
		
//		 �閰�
//		WorkerVO workerVO3 = dao.findByPrimaryKey(2005);
//		System.out.print(workerVO3.getWork_id() + ",");
//		System.out.print(workerVO3.getW_name() + ",");
//		System.out.print(workerVO3.getW_acc() + ",");
//		System.out.print(workerVO3.getEmail() + ",");
//		System.out.print(workerVO3.getReg_date() + ",");
//		System.out.print(workerVO3.getAll_auth() + ",");
//		System.out.println("---------------------");
		
		// �閰�
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
