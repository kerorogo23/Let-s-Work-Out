package com.product.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductJDBCDAO implements ProductDAO_interface {

//一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO PRODUCT (p_category_no,p_name,p_price,p_detail,p_status,p_upload_time) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT product_no,p_category_no,p_price,p_status,p_name,p_detail,p_upload_time FROM product order by product_no";
	private static final String GET_ONE_STMT = "SELECT product_no,p_category_no,p_price,p_status,p_name,p_detail,p_upload_time FROM product where product_no = ?";
	private static final String DELETE = "DELETE FROM product where product_no = ?";
	private static final String UPDATE = "UPDATE product set p_category_no=?, p_price=?, p_status=?, p_name=?, p_detail=?, p_upload_time=? where product_no = ?";
	private static final String FRONT_END_GET_ALL_STMT = "SELECT * FROM product where p_status =0 order by product_no";

	
	public Integer insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer newProduct_no = 0;
		try {
			int newId = 1;
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,newId);

			pstmt.setInt(1, productVO.getP_category_no());
			pstmt.setString(2, productVO.getP_name());
			pstmt.setInt(3, productVO.getP_price());
			pstmt.setString(4, productVO.getP_detail());
			pstmt.setInt(5, productVO.getP_status());
			pstmt.setDate(6, productVO.getP_upload_time());

			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				newProduct_no = rs.getInt(1); // 嚙線嚙賭援嚙踝蕭嚙踝蕭嚙豬值剁蕭嚙緻嚙諛增嚙瘩嚙踝蕭嚙�
			}

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
		return newProduct_no;
	}

	@Override
	public void update(ProductVO ProductVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ProductVO.getP_category_no());
			pstmt.setInt(2, ProductVO.getP_price());
			pstmt.setInt(3, ProductVO.getP_status());
			pstmt.setString(4, ProductVO.getP_name());
			pstmt.setString(5, ProductVO.getP_detail());
			pstmt.setDate(6, ProductVO.getP_upload_time());
			pstmt.setInt(7, ProductVO.getProduct_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer product_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, product_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ProductVO findByPrimaryKey(Integer product_no) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, product_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				productVO = new ProductVO();
				productVO.setProduct_no(rs.getInt("product_no"));
				productVO.setP_category_no(rs.getInt("p_category_no"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_price(rs.getInt("p_price"));
				productVO.setP_detail(rs.getString("p_detail"));
				productVO.setP_status(rs.getInt("p_status"));
				productVO.setP_upload_time(rs.getDate("p_upload_time"));
			}

			// Handle any driver errors
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				productVO = new ProductVO();
				productVO.setProduct_no(rs.getInt("product_no"));
				productVO.setP_category_no(rs.getInt("p_category_no"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_price(rs.getInt("p_price"));
				productVO.setP_detail(rs.getString("p_detail"));
				productVO.setP_status(rs.getInt("p_status"));
				productVO.setP_upload_time(rs.getDate("p_upload_time"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
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

	public List<ProductVO> findByProductKeyword(String keyword) {

		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String productKeyword = "SELECT product_no,p_category_no,p_name,p_price,p_detail,p_status,p_upload_time FROM proudct where p_name like \"%"
				+ keyword + "%\" order by product_no";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(productKeyword);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_no(rs.getInt("product_no"));
				productVO.setP_category_no(rs.getInt("p_category_no"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_price(rs.getInt("p_price"));
				productVO.setP_detail(rs.getString("p_detail"));
				productVO.setP_status(rs.getInt("p_status"));
				productVO.setP_upload_time(rs.getDate("p_upload_time"));
				list.add(productVO);
			}

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

	public List<ProductVO> findByProduct_no(List<Integer> product_no) {

		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String str = "?";
		for (int i = 1; i < product_no.size(); i++) {
			str += ",?";
		}

		String searchInstruction = "select * from product where product_no in (" + str + ") order by product_no";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(searchInstruction);

			for (int i = 0; i < product_no.size(); i++) {
				pstmt.setInt(i + 1, product_no.get(i));
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_no(rs.getInt("product_no"));
				productVO.setP_category_no(rs.getInt("p_category_no"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_price(rs.getInt("p_price"));
				productVO.setP_detail(rs.getString("p_detail"));
				productVO.setP_status(rs.getInt("p_status"));
				productVO.setP_upload_time(rs.getDate("p_upload_time"));
				list.add(productVO);
			}

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

	public List<ProductVO> frontEndFindByProductKeyword(String keyword) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String itemKeyword = "SELECT * FROM product where p_name like \"%" + keyword + "%\" and p_ptatus = 0 order by product_no";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(itemKeyword);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_no(rs.getInt("product_no"));
				productVO.setP_category_no(rs.getInt("p_category_no"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_price(rs.getInt("p_price"));
				productVO.setP_detail(rs.getString("p_detail"));
				productVO.setP_status(rs.getInt("p_status"));
				productVO.setP_upload_time(rs.getDate("p_upload_time"));
				list.add(productVO);
			}

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

	public List<ProductVO> frontEndFindByProduct_no(List<Integer> product_no) {

		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String str = "?";
		for (int i = 1; i < product_no.size(); i++) {
			str += ",?";
		}

		String searchInstruction = "select * from product where product_no in (" + str
				+ ") and p_status!= 0 order by product_no";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(searchInstruction);

			for (int i = 0; i < product_no.size(); i++) {
				pstmt.setInt(i + 1, product_no.get(i));
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_no(rs.getInt("product_no"));
				productVO.setP_category_no(rs.getInt("p_category_no"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_price(rs.getInt("p_price"));
				productVO.setP_detail(rs.getString("p_detail"));
				productVO.setP_status(rs.getInt("p_status"));
				productVO.setP_upload_time(rs.getDate("p_upload_time"));
				list.add(productVO);
			}

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

	public List<ProductVO> frontEndGetAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FRONT_END_GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();

				productVO.setProduct_no(rs.getInt("product_no"));
				productVO.setP_category_no(rs.getInt("p_category_no"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_price(rs.getInt("p_price"));
				productVO.setP_detail(rs.getString("p_detail"));
				productVO.setP_status(rs.getInt("p_status"));
				productVO.setP_upload_time(rs.getDate("p_upload_time"));
				list.add(productVO);
			}
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
}
