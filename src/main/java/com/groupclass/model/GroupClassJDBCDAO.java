package com.groupclass.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.grouphour.model.GroupHourVO;

public class GroupClassJDBCDAO implements GroupClassDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	String userid = "John";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO group_class(pro_id, c_type_no, c_name, loc, g_max, g_min, g_class_price, g_class_detail, g_class_pic) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT g_class_no, pro_id, c_type_no, c_name, loc, g_max, g_min, g_class_price, g_class_detail, g_class_pic FROM group_class order by g_class_no";
	private static final String GET_ONE_STMT = "SELECT g_class_no, pro_id, c_type_no, c_name, loc, g_max, g_min, g_class_price, g_class_detail, g_class_pic FROM group_class WHERE g_class_no = ?";
	private static final String GET_GroupHours_ByGroupClassNo_STMT = "SELECT * FROM group_hour where g_class_no = ? order by g_time_no";
	private static final String DELETE_GroupHours = "DELETE FROM group_hour where g_class_no = ?";
	private static final String DELETE_GroupClass = "DELETE FROM group_class WHERE g_class_no = ?";
	private static final String UPDATE = "UPDATE group_class set pro_id=?, c_type_no=?, c_name=?, loc=?, g_max=?, g_min=?, g_class_price=?, g_class_detail=?, g_class_pic=? WHERE g_class_no = ?";
	private static final String GET_GroupClasses_ByProId_STMT = 
			"SELECT * FROM group_class where pro_id = ? order by g_class_no";
	
	
	@Override
	public void insert(GroupClassVO groupClassVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, groupClassVO.getProId());
			pstmt.setInt(2, groupClassVO.getClassTypeNo());
			pstmt.setString(3, groupClassVO.getClassName());
			pstmt.setString(4, groupClassVO.getLoc());
			pstmt.setInt(5, groupClassVO.getGroupMax());
			pstmt.setInt(6, groupClassVO.getGroupMin());
			pstmt.setInt(7, groupClassVO.getGroupClassPrice());
			pstmt.setString(8, groupClassVO.getGroupClassDetail());
			pstmt.setBytes(9, groupClassVO.getGroupClassPic());

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
	public void update(GroupClassVO groupClassVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, groupClassVO.getProId());
			pstmt.setInt(2, groupClassVO.getClassTypeNo());
			pstmt.setString(3, groupClassVO.getClassName());
			pstmt.setString(4, groupClassVO.getLoc());
			pstmt.setInt(5, groupClassVO.getGroupMax());
			pstmt.setInt(6, groupClassVO.getGroupMin());
			pstmt.setInt(7, groupClassVO.getGroupClassPrice());
			pstmt.setString(8, groupClassVO.getGroupClassDetail());
			pstmt.setBytes(9, groupClassVO.getGroupClassPic());
			pstmt.setInt(10, groupClassVO.getGroupClassNo());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error ocurred." + se.getMessage());
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
	public void delete(Integer groupClassNo) {
		int updateCount_GroupHours = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE_GroupHours);
			pstmt.setInt(1, groupClassNo);
			updateCount_GroupHours = pstmt.executeUpdate();

			pstmt = con.prepareStatement(DELETE_GroupClass);
			pstmt.setInt(1, groupClassNo);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除團課編號" + groupClassNo + "時,共有" + updateCount_GroupHours + "團課時間同時被刪除");

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

	@Override
	public GroupClassVO findByPrimaryKey(Integer groupClassNo) {
		GroupClassVO groupClassVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, groupClassNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				groupClassVO = new GroupClassVO();
				groupClassVO.setGroupClassNo(rs.getInt("g_class_no"));
				groupClassVO.setProId(rs.getInt("pro_id"));
				groupClassVO.setClassTypeNo(rs.getInt("c_type_no"));
				groupClassVO.setClassName(rs.getString("c_name"));
				groupClassVO.setLoc(rs.getString("loc"));
				groupClassVO.setGroupMax(rs.getInt("g_max"));
				groupClassVO.setGroupMin(rs.getInt("g_min"));
				groupClassVO.setGroupClassPrice(rs.getInt("g_class_price"));
				groupClassVO.setGroupClassDetail(rs.getString("g_class_detail"));
				groupClassVO.setGroupClassPic(rs.getBytes("g_class_pic"));

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return groupClassVO;
	}

	@Override
	public List<GroupClassVO> getAll() {
		List<GroupClassVO> list = new ArrayList<GroupClassVO>();
		GroupClassVO groupClassVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupClassVO = new GroupClassVO();
				groupClassVO.setGroupClassNo(rs.getInt("g_class_no"));
				groupClassVO.setProId(rs.getInt("pro_id"));
				groupClassVO.setClassTypeNo(rs.getInt("c_type_no"));
				groupClassVO.setClassName(rs.getString("c_name"));
				groupClassVO.setLoc(rs.getString("loc"));
				groupClassVO.setGroupMax(rs.getInt("g_max"));
				groupClassVO.setGroupMin(rs.getInt("g_min"));
				groupClassVO.setGroupClassPrice(rs.getInt("g_class_price"));
				groupClassVO.setGroupClassDetail(rs.getString("g_class_detail"));
				groupClassVO.setGroupClassPic(rs.getBytes("g_class_pic"));
				list.add(groupClassVO);

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return list;
	}

	@Override
	public Set<GroupHourVO> getGroupHoursByGroupClassNo(Integer groupClassNo) {
		Set<GroupHourVO> set = new LinkedHashSet<GroupHourVO>();
		GroupHourVO groupHourVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GroupHours_ByGroupClassNo_STMT);
			pstmt.setInt(1, groupClassNo);
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
	
	@Override
	public Set<GroupClassVO> getGroupClassesByProId(Integer proId) {
		Set<GroupClassVO> set = new LinkedHashSet<GroupClassVO>();
		GroupClassVO groupClassVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GroupClasses_ByProId_STMT);
			pstmt.setInt(1, proId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				groupClassVO = new GroupClassVO();
				groupClassVO.setGroupClassNo(rs.getInt("g_class_no"));
				groupClassVO.setProId(rs.getInt("pro_id"));
				groupClassVO.setClassTypeNo(rs.getInt("c_type_no"));
				groupClassVO.setClassName(rs.getString("c_name"));
				groupClassVO.setLoc(rs.getString("loc"));
				groupClassVO.setGroupMax(rs.getInt("g_max"));
				groupClassVO.setGroupMin(rs.getInt("g_min"));
				groupClassVO.setGroupClassPrice(rs.getInt("g_class_price"));
				groupClassVO.setGroupClassDetail(rs.getString("g_class_detail"));
				groupClassVO.setGroupClassPic(rs.getBytes("g_class_pic"));
				set.add(groupClassVO);
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	public static void main(String[] args) throws IOException {
		GroupClassJDBCDAO dao = new GroupClassJDBCDAO();

		// 新增
//		GroupClassVO groupClassVO1 = new GroupClassVO();
//		groupClassVO1.setProId(3002);
//		groupClassVO1.setClassTypeNo(2);
//		groupClassVO1.setClassName("進階有氧");
//		groupClassVO1.setLoc("有氧教室");
//		groupClassVO1.setGroupMax(5);
//		groupClassVO1.setGroupMin(10);
//		groupClassVO1.setGroupClassPrice(600);
//		groupClassVO1.setGroupClassDetail("要記得來上課!");
//		FileInputStream fis = new FileInputStream("images\\AerobicExercise.jpg");
//		groupClassVO1.setGroupClassPic(fis);
//		
//		dao.insert(groupClassVO1);

		// 修改
//		GroupClassVO groupClassVO2 = new GroupClassVO();
//		groupClassVO2.setGroupClassNo(5006);
//		groupClassVO2.setProId(3002);
//		groupClassVO2.setClassTypeNo(2);
//		groupClassVO2.setClassName("進階有氧");
//		groupClassVO2.setLoc("有氧教室");
//		groupClassVO2.setGroupMax(5);
//		groupClassVO2.setGroupMin(10);
//		groupClassVO2.setGroupClassPrice(600);
//		groupClassVO2.setGroupClassDetail("要記得來上課!");
//		FileInputStream fiss = new FileInputStream("images\\AerobicExercise.jpg");
//		groupClassVO1.setGroupClassPic(fiss);
//		dao.update(groupClassVO2);

		// 刪除
//		dao.delete(5006);

		// 查詢
//		GroupClassVO groupClassVO3 = dao.findByPrimaryKey(5002);
//		System.out.println(groupClassVO3.getGroupClassNo() + ",");
//		System.out.println(groupClassVO3.getProId() + ",");
//		System.out.println(groupClassVO3.getClassTypeNo() + ",");
//		System.out.println(groupClassVO3.getClassName() + ",");
//		System.out.println(groupClassVO3.getLoc() + ",");
//		System.out.println(groupClassVO3.getGroupMax() + ",");
//		System.out.println(groupClassVO3.getGroupMin() + ",");
//		System.out.println(groupClassVO3.getGroupClassPrice() + ",");
//		System.out.println(groupClassVO3.getGroupClassDetail());
//		System.out.println(groupClassVO3.getGroupClassPic());
//		System.out.println("-------------------------------");

		// 查詢
//		List<GroupClassVO> list = dao.getAll();
//		for (GroupClassVO aGroupClassVO : list) {
//			System.out.println(aGroupClassVO.getGroupClassNo() + ",");
//			System.out.println(aGroupClassVO.getProId() + ",");
//			System.out.println(aGroupClassVO.getClassTypeNo() + ",");
//			System.out.println(aGroupClassVO.getClassName() + ",");
//			System.out.println(aGroupClassVO.getLoc() + ",");
//			System.out.println(aGroupClassVO.getGroupMax() + ",");
//			System.out.println(aGroupClassVO.getGroupMin() + ",");
//			System.out.println(aGroupClassVO.getGroupClassPrice() + ",");
//			System.out.println(aGroupClassVO.getGroupClassDetail() + ",");
//			System.out.println(aGroupClassVO.getGroupClassPic());
//			System.out.println();
//		}
//		
//	}
//		Set<GroupHourVO> set = dao.getGroupHoursByGroupClassNo(5002);
//		for (GroupHourVO aGroupHourVO : set) {
//			System.out.println(aGroupHourVO.getGroupTimeNo() + ",");
//			System.out.println(aGroupHourVO.getGroupClassNo() + ",");
//			System.out.println(aGroupHourVO.getGroupDate() + ",");
//			System.out.println(aGroupHourVO.getGroupStartingTime() + ",");
//			System.out.println(aGroupHourVO.getRegistStartTime() + ",");
//			System.out.println(aGroupHourVO.getRegistEndTime() + ",");
//			System.out.println(aGroupHourVO.getCourseStatus());
//			System.out.println("============================");
//		}
		
		Set<GroupClassVO> set = dao.getGroupClassesByProId(3001);
		for (GroupClassVO aGroupClassVO : set) {
			System.out.println(aGroupClassVO.getGroupClassNo() + ",");
			System.out.println(aGroupClassVO.getProId() + ",");
			System.out.println(aGroupClassVO.getClassTypeNo() + ",");
			System.out.println(aGroupClassVO.getClassName() + ",");
			System.out.println(aGroupClassVO.getLoc() + ",");
			System.out.println(aGroupClassVO.getGroupMax() + ",");
			System.out.println(aGroupClassVO.getGroupMin() + ",");
			System.out.println(aGroupClassVO.getGroupClassPrice() + ",");
			System.out.println(aGroupClassVO.getGroupClassDetail() + ",");
			System.out.println(aGroupClassVO.getGroupClassPic());
			System.out.println("--------------------------------");
		}
		
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//	}

	}

	

}
