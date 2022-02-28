package com.members.model;

import java.util.*;
import java.sql.*;

public class MembersJDBCDAO implements MembersDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/lwo?serverTimezone=Asia/Taipei";
	String userid = "John";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO members (MEM_NAME, MEM_ACC, MEM_PSW, MEM_BIR, SEX, MEM_ADDR, MEM_EMAIL, MEM_PHONE, MEM_RESUME, M_REG_DATE, ALL_AUTH, ART_AUTH, COM_AUTH) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		private static final String GET_ALL_STMT = 
			"SELECT MEM_ID,MEM_NAME, MEM_ACC, MEM_PSW, MEM_BIR, SEX, MEM_ADDR, MEM_EMAIL, MEM_PHONE, MEM_RESUME, M_REG_DATE, ALL_AUTH, ART_AUTH, COM_AUTH FROM members order by MEM_ID";
		private static final String GET_ONE_STMT = 
			"SELECT MEM_ID,MEM_NAME, MEM_ACC, MEM_PSW, MEM_BIR, SEX, MEM_ADDR, MEM_EMAIL, MEM_PHONE, MEM_RESUME, M_REG_DATE, ALL_AUTH, ART_AUTH, COM_AUTH FROM members where MEM_ID = ?";
//test_1-1
		private static final String LOGIN = 
			"SELECT MEM_ID,MEM_NAME, MEM_ACC, MEM_PSW, MEM_BIR, SEX, MEM_ADDR, MEM_EMAIL, MEM_PHONE, MEM_RESUME, M_REG_DATE, ALL_AUTH, ART_AUTH, COM_AUTH FROM members where MEM_ACC = ?";
//test_1-2
		private static final String DELETE = 
			"DELETE FROM members where MEM_ID = ?";
		private static final String UPDATE = 
			"UPDATE members set MEM_NAME=?, MEM_ACC=?, MEM_PSW=?, MEM_BIR=?, SEX=?, MEM_ADDR=?, MEM_EMAIL=?, MEM_PHONE=?, MEM_RESUME=?, M_REG_DATE=?, ALL_AUTH=?, ART_AUTH=?, COM_AUTH=? where MEM_ID = ?";

		@Override
		public void insert(MembersVO membersVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, membersVO.getMemName());
				pstmt.setString(2, membersVO.getMemAcc());
				pstmt.setString(3, membersVO.getMemPsw());
				pstmt.setDate(4, membersVO.getMemBir());
				pstmt.setString(5, membersVO.getSex());
				pstmt.setString(6, membersVO.getMemAddr());
				pstmt.setString(7, membersVO.getMemEmail());
				pstmt.setString(8, membersVO.getMemPhone());
				pstmt.setString(9, membersVO.getMemResume());
				pstmt.setDate(10, membersVO.getMemRegDate());
				pstmt.setInt(11, membersVO.getAllAuth());
				pstmt.setInt(12, membersVO.getArtAuth());
				pstmt.setInt(13, membersVO.getComAuth());
				
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
		public void update(MembersVO membersVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, membersVO.getMemName());
				pstmt.setString(2, membersVO.getMemAcc());
				pstmt.setString(3, membersVO.getMemPsw());
				pstmt.setDate(4, membersVO.getMemBir());
				pstmt.setString(5, membersVO.getSex());
				pstmt.setString(6, membersVO.getMemAddr());
				pstmt.setString(7, membersVO.getMemEmail());
				pstmt.setString(8, membersVO.getMemPhone());
				pstmt.setString(9, membersVO.getMemResume());
				pstmt.setDate(10, membersVO.getMemRegDate());
				pstmt.setInt(11, membersVO.getAllAuth());
				pstmt.setInt(12, membersVO.getArtAuth());
				pstmt.setInt(13, membersVO.getComAuth());
				pstmt.setInt(14, membersVO.getMemId());

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
		public void delete(Integer memId) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, memId);

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
		public MembersVO findByPrimaryKey(Integer memId) {

			MembersVO membersVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, memId);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// membersVO 也稱為 Domain objects
					membersVO = new MembersVO();
					membersVO.setMemId(rs.getInt("MEM_ID"));
					membersVO.setMemName(rs.getString("MEM_NAME"));
					membersVO.setMemAcc(rs.getString("MEM_ACC"));
					membersVO.setMemPsw(rs.getString("MEM_PSW"));
					membersVO.setMemBir(rs.getDate("MEM_BIR"));
					membersVO.setSex(rs.getString("SEX"));
					membersVO.setMemAddr(rs.getString("MEM_ADDR"));
					membersVO.setMemEmail(rs.getString("MEM_EMAIL"));
					membersVO.setMemPhone(rs.getString("MEM_PHONE"));
					membersVO.setMemResume(rs.getString("MEM_RESUME"));
					membersVO.setMemRegDate(rs.getDate("M_REG_DATE"));
					membersVO.setAllAuth(rs.getInt("ALL_AUTH"));
					membersVO.setArtAuth(rs.getInt("ART_AUTH"));
					membersVO.setComAuth(rs.getInt("COM_AUTH"));
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
			return membersVO;
		}

		
//test_2-1

		@Override
		public MembersVO findByPrimaryKeyByMemAcc(String memAcc) {
			MembersVO membersVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(LOGIN);

				pstmt.setString(1, memAcc);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// membersVo 也稱為 Domain objects
					membersVO = new MembersVO();
					membersVO.setMemId(rs.getInt("MEM_ID"));
					membersVO.setMemName(rs.getString("MEM_NAME"));
					membersVO.setMemAcc(rs.getString("MEM_ACC"));
					membersVO.setMemPsw(rs.getString("MEM_PSW"));
					membersVO.setMemBir(rs.getDate("MEM_BIR"));
					membersVO.setSex(rs.getString("SEX"));
					membersVO.setMemAddr(rs.getString("MEM_ADDR"));
					membersVO.setMemEmail(rs.getString("MEM_EMAIL"));
					membersVO.setMemPhone(rs.getString("MEM_PHONE"));
					membersVO.setMemResume(rs.getString("MEM_RESUME"));
					membersVO.setMemRegDate(rs.getDate("M_REG_DATE"));
					membersVO.setAllAuth(rs.getInt("ALL_AUTH"));
					membersVO.setArtAuth(rs.getInt("ART_AUTH"));
					membersVO.setComAuth(rs.getInt("COM_AUTH"));
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
			return membersVO;
		}

//test_2-2		
		
		@Override
		public List<MembersVO> getAll() {
			List<MembersVO> list = new ArrayList<MembersVO>();
			MembersVO membersVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// membersVO 也稱為 Domain objects
					membersVO = new MembersVO();
					membersVO.setMemId(rs.getInt("MEM_ID"));
					membersVO.setMemName(rs.getString("MEM_NAME"));
					membersVO.setMemAcc(rs.getString("MEM_ACC"));
					membersVO.setMemPsw(rs.getString("MEM_PSW"));
					membersVO.setMemBir(rs.getDate("MEM_BIR"));
					membersVO.setSex(rs.getString("SEX"));
					membersVO.setMemAddr(rs.getString("MEM_ADDR"));
					membersVO.setMemEmail(rs.getString("MEM_EMAIL"));
					membersVO.setMemPhone(rs.getString("MEM_PHONE"));
					membersVO.setMemResume(rs.getString("MEM_RESUME"));
					membersVO.setMemRegDate(rs.getDate("M_REG_DATE"));
					membersVO.setAllAuth(rs.getInt("ALL_AUTH"));
					membersVO.setArtAuth(rs.getInt("ART_AUTH"));
					membersVO.setComAuth(rs.getInt("COM_AUTH"));
					list.add(membersVO); // Store the row in the list
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

		public static void main(String[] args) {

			MembersJDBCDAO dao = new MembersJDBCDAO();

			// 新增
			MembersVO membersVO1 = new MembersVO();
			membersVO1.setMemName("周大雄");
			membersVO1.setMemAcc("jjkkoe4567");
			membersVO1.setMemPsw("jj123456");
			membersVO1.setMemBir(java.sql.Date.valueOf("1987-04-06"));
			membersVO1.setSex("男");
			membersVO1.setMemAddr("台北市敦化南路一段190巷48號");
			membersVO1.setMemEmail("jjyomeoe@gmail.com");
			membersVO1.setMemPhone("0926846215");
			membersVO1.setMemResume("平時興趣是午睡、練翻花繩，最近喜歡上健身，決定要練出結實的六塊肌，現在每天下班後都是去健身房。");
			membersVO1.setMemRegDate(java.sql.Date.valueOf("2022-01-01"));
			membersVO1.setAllAuth(1);
			membersVO1.setArtAuth(1);
			membersVO1.setComAuth(1);
			dao.insert(membersVO1);

//			 修改
			MembersVO membersVO2 = new MembersVO();
			membersVO2.setMemId(1001);
			membersVO2.setMemName("周大雄");
			membersVO2.setMemAcc("jjkkoe4567");
			membersVO2.setMemPsw("jj123456");
			membersVO2.setMemBir(java.sql.Date.valueOf("1987-04-06"));
			membersVO2.setSex("男");
			membersVO2.setMemAddr("台北市敦化南路一段190巷48號");
			membersVO2.setMemEmail("jjyomeoe@gmail.com");
			membersVO2.setMemPhone("0926846215");
			membersVO2.setMemResume("平時興趣是午睡、練翻花繩，最近喜歡上健身，決定要練出結實的六塊肌，現在每天下班後都是去健身房。");
			membersVO2.setMemRegDate(java.sql.Date.valueOf("2022-01-01"));
			membersVO2.setAllAuth(1);
			membersVO2.setArtAuth(1);
			membersVO2.setComAuth(1);
			dao.update(membersVO2);

//			// 刪除
//			dao.delete();

			// 查詢
			MembersVO membersVO3 = dao.findByPrimaryKey(1001);
			System.out.print(membersVO3.getMemId() + ",");
			System.out.print(membersVO3.getMemName() + ",");
			System.out.print(membersVO3.getMemAcc() + ",");
			System.out.print(membersVO3.getMemPsw() + ",");
			System.out.print(membersVO3.getMemBir() + ",");
			System.out.print(membersVO3.getSex() + ",");
			System.out.print(membersVO3.getMemAddr() + ",");
			System.out.print(membersVO3.getMemEmail() + ",");
			System.out.print(membersVO3.getMemPhone() + ",");
			System.out.print(membersVO3.getMemResume() + ",");
			System.out.print(membersVO3.getMemRegDate() + ",");
			System.out.print(membersVO3.getAllAuth() + ",");
			System.out.print(membersVO3.getArtAuth() + ",");
			System.out.println(membersVO3.getComAuth());
			
			System.out.println("---------------------");

			// 查詢
			List<MembersVO> list = dao.getAll();
			for (MembersVO aMembers : list) {
				System.out.print(aMembers.getMemId() + ",");
				System.out.print(aMembers.getMemName() + ",");
				System.out.print(aMembers.getMemAcc() + ",");
				System.out.print(aMembers.getMemPsw() + ",");
				System.out.print(aMembers.getMemBir() + ",");
				System.out.print(aMembers.getSex() + ",");
				System.out.print(aMembers.getMemAddr() + ",");
				System.out.print(aMembers.getMemEmail() + ",");
				System.out.print(aMembers.getMemPhone() + ",");
				System.out.print(aMembers.getMemResume() + ",");
				System.out.print(aMembers.getMemRegDate() + ",");
				System.out.print(aMembers.getAllAuth() + ",");
				System.out.print(aMembers.getArtAuth() + ",");
				System.out.print(aMembers.getComAuth());
				System.out.println();
			}
		}
}
