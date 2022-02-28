package com.grouporder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.grouphour.model.GroupHourVO;

public class GroupOrderJDBCDAO implements GroupOrderDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	String userid = "John";
	String passwd = "123456";
	
	public static final String INSERT_STMT = 
			"INSERT INTO group_order (mem_id, g_time_no, go_time) values (?, ?, ?)";
	public static final String GET_ALL_STMT = 
			"SELECT g_order_no, mem_id, g_time_no, go_time, go_status FROM group_order order by g_order_no";
	public static final String GET_ONE_STMT = 
			"SELECT g_order_no, mem_id, g_time_no, go_time, go_status FROM group_order WHERE g_order_no = ?";
	public static final String GET_ONE_GroupOrderNo_STMT =
			"select * from group_order where mem_id=? and g_time_no=? and go_time=?";
	public static final String GET_GroupOrderCount_STMT =
			"select * from group_order where g_time_no=? and go_status = ?";
	public static final String DELETE = 
			"DELETE FROM group_order WHERE g_order_no = ?";
	public static final String UPDATE = 
			"UPDATE group_order set mem_id=?, g_time_no=?, go_time=?, go_status=? WHERE g_order_no=?";
	public static final String UPDATE_GroupOrderStatus = 
			"UPDATE group_order set go_status=? WHERE g_time_no=?";
	public static final String GET_GroupOrders_ByMem_STMT =
			"SELECT * FROM group_order where mem_id = ? order by g_order_no";
	public static final String GET_GroupOrders_ByGroupOrderStatus_STMT =
			"SELECT * FROM group_order where go_status = ? order by g_order_no";
	
	@Override
	public void insert(GroupOrderVO groupOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, groupOrderVO.getMemId());
			pstmt.setInt(2, groupOrderVO.getGroupTimeNo());
			pstmt.setTimestamp(3, groupOrderVO.getGroupOrderTime());
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver."
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error ocurred."
					+ se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void update(GroupOrderVO groupOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, groupOrderVO.getMemId());
			pstmt.setInt(2, groupOrderVO.getGroupTimeNo());
			pstmt.setTimestamp(3, groupOrderVO.getGroupOrderTime());
			pstmt.setInt(4, groupOrderVO.getGroupOrderStatus());
			pstmt.setInt(5, groupOrderVO.getGroupOrderNo());
			
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error ocurred."
					+se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public void updateGroupOrderStatus(Integer groupTimeNo, Integer groupOrderStatus) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_GroupOrderStatus);
			pstmt.setInt(1, groupOrderStatus);
			pstmt.setInt(2, groupTimeNo);
			
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error ocurred."
					+se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	@Override
	public void delete(Integer groupOrderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, groupOrderNo);
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error ocurred."
					+ se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public GroupOrderVO findByPrimaryKey(Integer groupTimeNo) {
		GroupOrderVO groupOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, groupTimeNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				groupOrderVO = new GroupOrderVO();
				groupOrderVO.setGroupOrderNo(rs.getInt("g_order_no"));
				groupOrderVO.setMemId(rs.getInt("mem_id"));
				groupOrderVO.setGroupTimeNo(rs.getInt("g_time_no"));
				groupOrderVO.setGroupOrderTime(rs.getTimestamp("go_time"));
				groupOrderVO.setGroupOrderStatus(rs.getInt("go_status"));
			}
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error ocurred."
					+ se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return groupOrderVO;
	}
	
	@Override
	public GroupOrderVO findGroupOrder(Integer memId, Integer groupTimeNo, Timestamp groupOrderTime) {
		GroupOrderVO groupOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_GroupOrderNo_STMT);
			pstmt.setInt(1, memId);
			pstmt.setInt(2, groupTimeNo);
			pstmt.setTimestamp(3, groupOrderTime);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				groupOrderVO = new GroupOrderVO();
				groupOrderVO.setGroupOrderNo(rs.getInt("g_order_no"));
				groupOrderVO.setMemId(rs.getInt("mem_id"));
				groupOrderVO.setGroupTimeNo(rs.getInt("g_time_no"));
				groupOrderVO.setGroupOrderTime(rs.getTimestamp("go_time"));
				groupOrderVO.setGroupOrderStatus(rs.getInt("go_status"));
			}
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error ocurred."
					+ se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return groupOrderVO;
	}
	@Override
	public Set<GroupOrderVO> findGroupOrderCount(Integer groupTimeNo, Integer groupOrderStatus) {
		Set<GroupOrderVO> set = new LinkedHashSet<GroupOrderVO>();
		GroupOrderVO groupOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GroupOrderCount_STMT);
			pstmt.setInt(1, groupTimeNo);
			pstmt.setInt(2, groupOrderStatus);
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
	public List<GroupOrderVO> getAll() {
		List<GroupOrderVO> list = new ArrayList<GroupOrderVO>();
		GroupOrderVO groupOrderVO = new GroupOrderVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			 while(rs.next()) {
				 groupOrderVO = new GroupOrderVO();
				 groupOrderVO.setGroupOrderNo(rs.getInt("g_order_no"));
				 groupOrderVO.setMemId(rs.getInt("mem_id"));
				 groupOrderVO.setGroupTimeNo(rs.getInt("g_time_no"));
				 groupOrderVO.setGroupOrderTime(rs.getTimestamp("go_time"));
				 groupOrderVO.setGroupOrderStatus(rs.getInt("go_status"));
				 list.add(groupOrderVO);
			 }
			 
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occurred."
					+ se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	@Override
	public Set<GroupOrderVO> getGroupOrdersByMem(Integer memId) {
		Set<GroupOrderVO> set = new LinkedHashSet<GroupOrderVO>();
		GroupOrderVO groupOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GroupOrders_ByMem_STMT);
			pstmt.setInt(1, memId);
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
	public Set<GroupOrderVO> getGroupOrdersByGroupOrderStatus(Integer groupOrderStatus) {
		Set<GroupOrderVO> set = new LinkedHashSet<GroupOrderVO>();
		GroupOrderVO groupOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GroupOrders_ByGroupOrderStatus_STMT);
			pstmt.setInt(1, groupOrderStatus);
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
	
	
	public static void main(String[] args) throws ParseException {
		GroupOrderJDBCDAO dao = new GroupOrderJDBCDAO();
		
		// 新增
//		GroupOrderVO groupOrderVO = new GroupOrderVO();
//		groupOrderVO.setMemId(1002);
//		groupOrderVO.setGroupTimeNo(20);
//		Long datetime = System.currentTimeMillis();
//        Timestamp groupOrderTime = new Timestamp(datetime);
//		groupOrderVO.setGroupOrderTime(groupOrderTime);
//		dao.insert(groupOrderVO);
		
		// 修改
//		GroupOrderVO groupOrderVO2 = new GroupOrderVO();
//		groupOrderVO2.setGroupOrderNo(1);
//		groupOrderVO2.setMemId(1005);
//		groupOrderVO2.setGroupTimeNo(20);
//		groupOrderVO2.setGroupOrderTime(java.sql.Timestamp.valueOf("2021-12-02 16:46:10"));
//		groupOrderVO2.setGroupOrderStatus(1);
//		dao.update(groupOrderVO2);
//		System.out.println("111");
		
		// 修改
		dao.updateGroupOrderStatus(5, 1);

		
		// 刪
//		dao.delete(31);
		
		// 查詢
//		GroupOrderVO groupOrderVO3 = dao.findByPrimaryKey(5);
//		System.out.println(groupOrderVO3.getGroupOrderNo() + ",");
//		System.out.println(groupOrderVO3.getMemId() + ",");
//		System.out.println(groupOrderVO3.getGroupTimeNo() + ",");
//		System.out.println(groupOrderVO3.getGroupOrderTime() + ",");
//		System.out.println(groupOrderVO3.getGroupOrderStatus());
//		System.out.println("--------------------------------");
		
		// 查詢
		Set<GroupOrderVO> set = dao.findGroupOrderCount(5, 0);
		for(GroupOrderVO aGroupOrder : set) {
			System.out.println(aGroupOrder.getGroupOrderNo() + ",");
			System.out.println(aGroupOrder.getMemId() + ",");
			System.out.println(aGroupOrder.getGroupTimeNo()+ ",");
			System.out.println(aGroupOrder.getGroupOrderTime() + ",");
			System.out.println(aGroupOrder.getGroupOrderStatus());
			System.out.println("---------------------------------------");
		}

		
		// 查詢
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = sdf.parse("2021-12-21 14:58:44");
//		long time = date.getTime();
//		Timestamp timestamp = new Timestamp(time);
//		GroupOrderVO groupOrderVO3 = dao.findGroupOrder(1001, 37, timestamp);
//		System.out.println(groupOrderVO3.getGroupOrderNo() + ",");
//		System.out.println(groupOrderVO3.getMemId() + ",");
//		System.out.println(groupOrderVO3.getGroupTimeNo() + ",");
//		System.out.println(groupOrderVO3.getGroupOrderTime() + ",");
//		System.out.println(groupOrderVO3.getGroupOrderStatus());
//		System.out.println("--------------------------------");
		
		
		// 查詢
//		List<GroupOrderVO> list = dao.getAll();
//		for(GroupOrderVO aGroupOrderVO : list) {
//			System.out.println(aGroupOrderVO.getGroupOrderNo() + ",");
//			System.out.println(aGroupOrderVO.getMemId() + ",");
//			System.out.println(aGroupOrderVO.getGroupTimeNo() + ",");
//			System.out.println(aGroupOrderVO.getGroupOrderTime() + ",");
//			System.out.println(aGroupOrderVO.getGroupOrderStatus());
//			System.out.println("--------------------------------");
//		}
//		Set<GroupOrderVO> set = dao.getGroupOrdersByMem(1002);
//		for(GroupOrderVO aGroupOrder : set) {
//			System.out.println(aGroupOrder.getGroupOrderNo() + ",");
//			System.out.println(aGroupOrder.getMemId() + ",");
//			System.out.println(aGroupOrder.getGroupTimeNo()+ ",");
//			System.out.println(aGroupOrder.getGroupOrderTime() + ",");
//			System.out.println(aGroupOrder.getGroupOrderStatus());
//			System.out.println("---------------------------------------");
//		}
		
//		Set<GroupOrderVO> set = dao.getGroupOrdersByGroupOrderStatus(0);
//		for(GroupOrderVO aGroupOrder : set) {
//			System.out.println(aGroupOrder.getGroupOrderNo() + ",");
//			System.out.println(aGroupOrder.getMemId() + ",");
//			System.out.println(aGroupOrder.getGroupTimeNo()+ ",");
//			System.out.println(aGroupOrder.getGroupOrderTime() + ",");
//			System.out.println(aGroupOrder.getGroupOrderStatus());
//			System.out.println("---------------------------------------");
//		}
	}

	
	
	
	

}
