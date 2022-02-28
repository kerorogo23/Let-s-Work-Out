package com.classtype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.groupclass.model.GroupClassVO;

public class ClassTypeJDBCDAO implements ClassTypeDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	String userid = "John";
	String passwd = "123456";
	
	public static final String INSERT_STMT = 
			"INSERT INTO class_type (c_name) values (?)";
	public static final String GET_ALL_STMT = 
			"SELECT c_type_no, c_name FROM class_type ORDER BY c_type_no";
	public static final String GET_ONE_STMT = 
			"SELECT c_type_no, c_name FROM class_type WHERE c_type_no =?";
	private static final String GET_GroupClasses_ByClassTypeNo_STMT = 
			"SELECT * FROM group_class where c_type_no = ? order by g_class_no";
	private static final String DELETE_GroupClass = 
			"DELETE FROM group_class where c_type_no = ?";
	private static final String DELETE_ClassType = 
			"DELETE FROM class_type where c_type_no = ?";	
	public static final String UPDATE = 
			"UPDATE class_type set c_name=? WHERE c_type_no=?";
	@Override
	public void insert(ClassTypeVO classTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, classTypeVO.getClassTypeName());
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(ClassTypeVO classTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, classTypeVO.getClassTypeName());
			pstmt.setInt(2, classTypeVO.getClassTypeNo());
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(Integer classTypeNo) {
		int updateCount_GroupClasses = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			con.setAutoCommit(false);
			// 先刪除團課
			pstmt = con.prepareStatement(DELETE_GroupClass);
			pstmt.setInt(1, classTypeNo);
			updateCount_GroupClasses = pstmt.executeUpdate();
			
			// 再刪除類別
			pstmt = con.prepareStatement(DELETE_ClassType);
			pstmt.setInt(1, classTypeNo);
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除類別編號" + classTypeNo + "時,共有團課" + updateCount_GroupClasses
					+ "同時被刪除");
		}catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public ClassTypeVO findByPrimaryKey(Integer ClassTypeNo) {
		ClassTypeVO classTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, ClassTypeNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				classTypeVO = new ClassTypeVO();
				classTypeVO.setClassTypeNo(rs.getInt("c_type_no"));
				classTypeVO.setClassTypeName(rs.getString("c_name"));
			}
		}catch (ClassNotFoundException e) {
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
		return classTypeVO;	
	}
	@Override
	public List<ClassTypeVO> getAll() {
		List<ClassTypeVO> list = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ClassTypeVO classTypeVO = new ClassTypeVO();
				classTypeVO.setClassTypeName(rs.getString("c_name"));
				classTypeVO.setClassTypeNo(rs.getInt("c_type_no"));
				list.add(classTypeVO);
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
	public Set<GroupClassVO> getGroupClassesByClassTypeNo(Integer classTypeNo) {
		Set<GroupClassVO> set = new LinkedHashSet<GroupClassVO>();
		GroupClassVO groupClassVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GroupClasses_ByClassTypeNo_STMT);
			pstmt.setInt(1, classTypeNo);
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
	public static void main(String[] args) {
		ClassTypeJDBCDAO dao = new ClassTypeJDBCDAO();
		
		// 新增
//		ClassTypeVO classTypeVO = new ClassTypeVO();
//		classTypeVO.setClassTypeName("爬山");
//		dao.insert(classTypeVO);
		
		// 修改
//		ClassTypeVO classTypeVO1 = new ClassTypeVO();
//		classTypeVO1.setClassTypeNo(6);
//		classTypeVO1.setClassTypeName("爬山");
//		dao.update(classTypeVO1);
		
		// 刪除
		dao.delete(6);
		
		// 查詢
//		ClassTypeVO classTypeVO2 = dao.findByPrimaryKey(4);
//		System.out.println(classTypeVO2.getClassTypeNo() + ",");
//		System.out.println(classTypeVO2.getClassTypeName());
//		System.out.println("--------------------------------");
//		
		// 查詢
//		List<ClassTypeVO> list = dao.getAll();
//		for (ClassTypeVO aClassTypeVO : list) {
//			System.out.println(aClassTypeVO.getClassTypeNo()+ ",");
//			System.out.println(aClassTypeVO.getClassTypeName());
//			System.out.println();
//		}
		
		// 查詢
//		Set<GroupClassVO> set = dao.getGroupClassesByClassTypeNo(4);
//		for (GroupClassVO aGroupClassVO : set) {
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
//			System.out.println("--------------------------------");
//		}
		
	}
	
}
