package com.product_photo.model;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class ProductPhotoJDBCDAO implements ProductPhotoDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	String userid = "John";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PRODUCT_PHOTO (product_no,p_photo) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT p_photo_no,product_no,p_photo FROM product_photo order by p_photo_no";
	private static final String GET_ONE_STMT = "SELECT p_photo_no,product_no,p_photo FROM product_photo where p_photo_no = ?";
	private static final String DELETE = "DELETE FROM product_photo where p_photo_no = ?";
	private static final String UPDATE = "UPDATE product_photo set product_no=? , p_photo=? where p_photo_no = ?";

	@Override
	public void insert(ProductPhotoVO productphotoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productphotoVO.getProduct_no());
			pstmt.setBytes(2, productphotoVO.getP_photo());

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
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	@Override
	public void update(ProductPhotoVO productphotoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productphotoVO.getProduct_no());
			pstmt.setBytes(2, productphotoVO.getP_photo());
			pstmt.setInt(3, productphotoVO.getP_photo_no());

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
	public void delete(Integer p_photo_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, p_photo_no);

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
	public ProductPhotoVO findByPrimaryKey(Integer p_photo_no) {

		ProductPhotoVO productphotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, p_photo_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				productphotoVO = new ProductPhotoVO();
				productphotoVO.setP_photo_no(rs.getInt("p_photo_no"));
				productphotoVO.setProduct_no(rs.getInt("product_no"));
				productphotoVO.setP_photo(rs.getBytes("p_photo"));

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
		return productphotoVO;
	}

	@Override
	public List<ProductPhotoVO> getAll() {
		List<ProductPhotoVO> list = new ArrayList<ProductPhotoVO>();
		ProductPhotoVO productphotoVO = null;

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
				productphotoVO = new ProductPhotoVO();
				productphotoVO.setP_photo_no(rs.getInt("p_photo_no"));
				productphotoVO.setProduct_no(rs.getInt("product_no"));
				productphotoVO.setP_photo(rs.getBytes("p_photo"));

				list.add(productphotoVO); // Store the row in the list
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

	public static void main(String[] args) throws IOException {

		ProductPhotoJDBCDAO dao = new ProductPhotoJDBCDAO();

		// �s�W
//		ProductPhotoVO productphotoVO1 = new ProductPhotoVO();
//		
//		productphotoVO1.setProduct_no(4002);
//		byte[] pic = getPictureByteArray("pic/4002-健身組.jpg");
//		productphotoVO1.setP_photo(pic);
//
//		dao.insert(productphotoVO1);
////		
//
//	// �ק�
		ProductPhotoVO productphotoVO2 = new ProductPhotoVO();
		productphotoVO2.setP_photo_no(10);
		productphotoVO2.setProduct_no(4010);
		byte[] pic1 = getPictureByteArray("pic/4010-按摩滾輪.jpg");
		productphotoVO2.setP_photo(pic1);
//
//				
		dao.update(productphotoVO2);
////
//		// �R��
	dao.delete(13);
//
//	// �d��
//		ProductPhotoVO productphotoVO3 = dao.findByPrimaryKey(1);
//		System.out.print(productphotoVO3.getP_photo_no() + ",");
//		System.out.print(productphotoVO3.getProduct_no() + ",");
//		System.out.print(productphotoVO3.getP_photo());
//
//		System.out.println("---------------------");
//
//		// �d��
		List<ProductPhotoVO> list = dao.getAll();
		for (ProductPhotoVO aProductPhoto : list) {
			System.out.print(aProductPhoto.getP_photo_no() + ",");
			System.out.print(aProductPhoto.getProduct_no() + ",");
			System.out.print(aProductPhoto.getP_photo());

			System.out.println();
		}
	}
}
