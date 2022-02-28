package com.grouphour.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import com.grouporder.model.GroupOrderVO;

public class GroupHourJDBCDAO implements GroupHourDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	String userid = "John";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO group_hour (g_class_no, g_date, g_starting_time, r_start_time, r_end_time) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT g_time_no, g_class_no, g_date, g_starting_time, r_start_time, r_end_time, course_status FROM group_hour order by g_time_no";
	private static final String GET_ONE_STMT = "SELECT g_time_no, g_class_no, g_date, g_starting_time, r_start_time, r_end_time, course_status FROM group_hour WHERE g_time_no = ?";
	private static final String GET_GroupOrders_ByGroupHour_STMT = "SELECT * FROM group_order where g_time_no = ? order by g_order_no";
	private static final String DELETE_GroupOrders = "DELETE FROM group_order where g_time_no = ?";
	private static final String DELETE_GroupHour = "DELETE FROM group_hour WHERE g_time_no = ?";
	private static final String UPDATE = "UPDATE group_hour set g_class_no=?, g_date=?, g_starting_time=?, r_start_time=?, r_end_time=?, course_status=? WHERE g_time_no=?";
	private static final String GET_GroupHours_ByCourseStatus_STMT = "SELECT * FROM group_hour where course_status = ? order by g_time_no";
	
	@Override
	public void insert(GroupHourVO groupHourVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, groupHourVO.getGroupClassNo());
			pstmt.setDate(2, groupHourVO.getGroupDate());
			pstmt.setString(3, groupHourVO.getGroupStartingTime());
			pstmt.setTimestamp(4, groupHourVO.getRegistStartTime());
			pstmt.setTimestamp(5, groupHourVO.getRegistEndTime());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred." + se.getMessage());
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
	public void update(GroupHourVO groupHourVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, groupHourVO.getGroupClassNo());
			pstmt.setDate(2, groupHourVO.getGroupDate());
			pstmt.setString(3, groupHourVO.getGroupStartingTime());
			pstmt.setTimestamp(4, groupHourVO.getRegistStartTime());
			pstmt.setTimestamp(5, groupHourVO.getRegistEndTime());
			pstmt.setInt(6, groupHourVO.getCourseStatus());
			pstmt.setInt(7, groupHourVO.getGroupTimeNo());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void delete(Integer groupTimeNo) {
		int updateCount_GroupHours = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_GroupOrders);
			pstmt.setInt(1, groupTimeNo);
			updateCount_GroupHours = pstmt.executeUpdate();

			pstmt = con.prepareStatement(DELETE_GroupHour);
			pstmt.setInt(1, groupTimeNo);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除時間編號" + groupTimeNo + "時,共有員工" + updateCount_GroupHours + "訂單同時被刪除");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

//	@Override
//	public List<GroupHourVO> findByForeignKey(Integer groupClassNo) {
//		List<GroupHourVO> list = new ArrayList<GroupHourVO>();
//		GroupHourVO groupHourVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//			pstmt.setInt(1, groupClassNo);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				groupHourVO = new GroupHourVO();
//				groupHourVO.setGroupTimeNo(rs.getInt("g_time_no"));
//				groupHourVO.setGroupClassNO(rs.getInt("g_class_no"));
//				groupHourVO.setGroupDate(rs.getDate("g_date"));
//				groupHourVO.setGroupStartingTime(rs.getString("g_starting_time"));
//				groupHourVO.setRegistStartTime(rs.getDate("r_start_time"));
//				groupHourVO.setRegistEndTime(rs.getDate("r_end_time"));
//				groupHourVO.setCourseStatus(rs.getInt("course_status"));
//				list.add(groupHourVO);
//			}
//		}catch(ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver."
//					+ e.getMessage());
//		}catch(SQLException se) {
//			throw new RuntimeException("A database error occured."
//					+ se.getMessage());
//		}finally {
//			if(rs != null) {
//				try {
//					rs.close();
//				}catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				}catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(con != null) {
//				try {
//					con.close();
//				}catch(Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}		
//		return list;
//	}
	@Override
	public GroupHourVO findByPrimaryKey(Integer groupTimeNo) {
		GroupHourVO groupHourVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, groupTimeNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupHourVO = new GroupHourVO();
				groupHourVO.setGroupTimeNo(rs.getInt("g_time_no"));
				groupHourVO.setGroupClassNo(rs.getInt("g_class_no"));
				groupHourVO.setGroupDate(rs.getDate("g_date"));
//				groupHourVO.setGroupStartingTime(rs.getString("g_starting_time"));
				groupHourVO.setGroupStartingTime(rs.getString("g_starting_time").indexOf("1"));
//						+":00~"+(new Integer(rs.getString("g_starting_time").indexOf("1"))+1) +":00");
				groupHourVO.setRegistStartTime(rs.getTimestamp("r_start_time"));
				groupHourVO.setRegistEndTime(rs.getTimestamp("r_end_time"));
				groupHourVO.setCourseStatus(rs.getInt("course_status"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
		return groupHourVO;
	}

	@Override
	public List<GroupHourVO> getAll() {
		List<GroupHourVO> list = new ArrayList<GroupHourVO>();
		GroupHourVO groupHourVO = new GroupHourVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupHourVO = new GroupHourVO();
				groupHourVO.setGroupTimeNo(rs.getInt("g_time_no"));
				groupHourVO.setGroupClassNo(rs.getInt("g_class_no"));
				groupHourVO.setGroupDate(rs.getDate("g_date"));
				groupHourVO.setGroupStartingTime(rs.getString("g_starting_time").indexOf("1")+":00~"+(new Integer(rs.getString("g_starting_time").indexOf("1"))+1) +":00");
				groupHourVO.setRegistStartTime(rs.getTimestamp("r_start_time"));
				groupHourVO.setRegistEndTime(rs.getTimestamp("r_end_time"));
				groupHourVO.setCourseStatus(rs.getInt("course_status"));

				list.add(groupHourVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred." + se.getMessage());
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
	public Set<GroupOrderVO> getGroupOrdersByGroupHour(Integer groupTimeNo) {
		Set<GroupOrderVO> set = new LinkedHashSet<GroupOrderVO>();
		GroupOrderVO groupOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GroupOrders_ByGroupHour_STMT);
			pstmt.setInt(1, groupTimeNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupOrderVO = new GroupOrderVO();
				groupOrderVO.setGroupOrderNo(rs.getInt("g_order_no"));
				groupOrderVO.setMemId(rs.getInt("mem_id"));
				groupOrderVO.setGroupTimeNo(rs.getInt("g_time_no"));
				groupOrderVO.setGroupOrderTime(rs.getTimestamp("go_time"));
				groupOrderVO.setGroupOrderStatus(rs.getInt("go_status"));
				set.add(groupOrderVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return set;
	}
	
	@Override
	public Set<GroupHourVO> getGroupHoursByCourseStatus(Integer courseStatus) {
		Set<GroupHourVO> set = new LinkedHashSet<GroupHourVO>();
		GroupHourVO groupHourVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GroupHours_ByCourseStatus_STMT);
			pstmt.setInt(1, courseStatus);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupHourVO = new GroupHourVO();
				groupHourVO.setGroupTimeNo(rs.getInt("g_time_no"));
				groupHourVO.setGroupClassNo(rs.getInt("g_class_no"));
				groupHourVO.setGroupDate(rs.getDate("g_date"));
				groupHourVO.setGroupStartingTime(rs.getString("g_starting_time").indexOf("1")+":00~"+(new Integer(rs.getString("g_starting_time").indexOf("1"))+1) +":00");
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String startTime = sdFormat.format(rs.getTimestamp("r_start_time"));
				groupHourVO.setRegistStartTime(startTime);
				groupHourVO.setRegistEndTime(rs.getTimestamp("r_end_time"));
				groupHourVO.setCourseStatus(rs.getInt("course_status"));
				set.add(groupHourVO);

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return set;
	}


	public static void main(String[] args) {

		GroupHourJDBCDAO dao = new GroupHourJDBCDAO();

		// 新增
//		GroupHourVO groupHourVO = new GroupHourVO();
//		groupHourVO.setGroupClassNO(5005);
//		groupHourVO.setGroupDate(java.sql.Date.valueOf("2022-10-25"));
//		groupHourVO.setGroupStartingTime("000000000010000000000000");
//		groupHourVO.setRegistStartTime(java.sql.Date.valueOf("2022-9-25 0:0:0"));
//		groupHourVO.setRegistEndTime(java.sql.Date.valueOf("2022-10-24 0:0:0"));
//
//		dao.insert(groupHourVO);

		// 修改
//		GroupHourVO groupHourVO1 = new GroupHourVO();
//
//		groupHourVO1.setGroupTimeNo(57);
//		groupHourVO1.setGroupClassNO(5005);
//		groupHourVO1.setGroupDate(java.sql.Date.valueOf("2022-10-25"));
//		groupHourVO1.setGroupStartingTime("000000000010000000000000");
//		groupHourVO1.setRegistStartTime(java.sql.Date.valueOf("2022-9-25 0:0:0"));
//		groupHourVO1.setRegistEndTime(java.sql.Date.valueOf("2022-10-24 0:0:0"));
//		groupHourVO1.setCourseStatus(-1);
//
//		dao.update(groupHourVO1);

		// 刪除
//		dao.delete(59);

		// 查詢
//		GroupHourVO groupHourVO2 = dao.findByPrimaryKey(5);
//		System.out.println(groupHourVO2.getGroupTimeNo() + ",");
//		System.out.println(groupHourVO2.getGroupClassNo() + ",");
//		System.out.println(groupHourVO2.getGroupDate() + ",");
//		System.out.println(groupHourVO2.getGroupStartingTime() + ",");
//		System.out.println(groupHourVO2.getRegistStartTime() + ",");
//		System.out.println(groupHourVO2.getRegistEndTime() + ",");
//		System.out.println(groupHourVO2.getCourseStatus());

		// 查詢

//		List<GroupHourVO> list = dao.findByForeignKey(5005);
//		for(GroupHourVO aGroupHour : list) {
//			System.out.println(aGroupHour.getGroupTimeNo() + ",");
//			System.out.println(aGroupHour.getGroupClassNO() + ",");
//			System.out.println(aGroupHour.getGroupDate() + ",");
//			System.out.println(aGroupHour.getGroupStartingTime() + ",");
//			System.out.println(aGroupHour.getRegistStartTime() + ",");
//			System.out.println(aGroupHour.getRegistEndTime() + ",");
//			System.out.println(aGroupHour.getCourseStatus());
//			System.out.println("---------------------------------------");
//		}
		// 查詢

//		List<GroupHourVO> list = dao.getAll();
//		for(GroupHourVO aGroupHour : list) {
//			System.out.println(aGroupHour.getGroupTimeNo() + ",");
//			System.out.println(aGroupHour.getGroupClassNo() + ",");
//			System.out.println(aGroupHour.getGroupDate() + ",");
//			System.out.println(aGroupHour.getGroupStartingTime() + ",");
//			System.out.println(aGroupHour.getRegistStartTime() + ",");
//			System.out.println(aGroupHour.getRegistEndTime() + ",");
//			System.out.println(aGroupHour.getCourseStatus());
//			System.out.println("---------------------------------------");
//		}
		
//		Set<GroupOrderVO> set = dao.getGroupOrdersByGroupHour(1);
//		for(GroupOrderVO aGroupOrder : set) {
//			System.out.println(aGroupOrder.getGroupOrderNo() + ",");
//			System.out.println(aGroupOrder.getMemId() + ",");
//			System.out.println(aGroupOrder.getGroupTimeNo()+ ",");
//			System.out.println(aGroupOrder.getGroupOrderTime() + ",");
//			System.out.println(aGroupOrder.getGroupOrderStatus());
//			System.out.println("---------------------------------------");
//		}
		
		Set<GroupHourVO> set = dao.getGroupHoursByCourseStatus(0);
		for (GroupHourVO aGroupHourVO : set) {
			System.out.println(aGroupHourVO.getGroupTimeNo() + ",");
			System.out.println(aGroupHourVO.getGroupClassNo() + ",");
			System.out.println(aGroupHourVO.getGroupDate() + ",");
			System.out.println(aGroupHourVO.getGroupStartingTime() + ",");
			System.out.println(aGroupHourVO.getRegistStartTime() + ",");
			System.out.println(aGroupHourVO.getRegistEndTime() + ",");
			System.out.println(aGroupHourVO.getCourseStatus());
			System.out.println("============================");
		}
	}

	

}
