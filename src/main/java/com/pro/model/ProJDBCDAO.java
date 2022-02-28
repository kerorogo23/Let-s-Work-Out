package com.pro.model;

import java.util.*;

import com.course.model.CourseVO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class ProJDBCDAO implements ProDAO_interface {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	private static String userid = "John";
	private static String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO pro(WORK_ID, PRO_RESUME, EXPR, PRO_PHOTO) VALUES(?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT PRO_ID, WORK_ID, PRO_RESUME, EXPR, PRO_PHOTO FROM pro order by PRO_ID ";
	private static final String GET_ONE_STMT = "SELECT PRO_ID, WORK_ID, PRO_RESUME, EXPR, PRO_PHOTO FROM pro where PRO_ID = ? ";

	private static final String DELETE = "DELETE FROM pro where PRO_ID = ?";
	private static final String UPDATE = "UPDATE pro "
			+ "SET WORK_ID = ?, PRO_RESUME = ?, EXPR = ?, PRO_PHOTO = ? WHERE PRO_ID = ?";

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(ProVO proVO) {

		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, proVO.getWorkId());
			pstmt.setString(2, proVO.getProResume());
			pstmt.setString(3, proVO.getExpr());
			pstmt.setBytes(4, proVO.getProPhoto());

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(ProVO proVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, proVO.getWorkId());
			pstmt.setString(2, proVO.getProResume());
			pstmt.setString(3, proVO.getExpr());
			pstmt.setBytes(4, proVO.getProPhoto());
			pstmt.setInt(5, proVO.getProId());

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer ProId) {

	}

	@Override
	public ProVO findByPrimaryKey(Integer ProId) {
		ProVO proVO = null;
		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, ProId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
//				proVO = new ProVO();
//				proVO.setProId(rs.getInt("PRO_ID"));
//				proVO.setWorkId(rs.getInt("WORK_ID"));
//				proVO.setProResume(rs.getString("PRO_RESUME"));
//				proVO.setExpr(rs.getString("EXPR"));
//				proVO.setProPhoto(rs.getBytes("PRO_PHOTO"));
				proVO = getProVO(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proVO;
	}

	private ProVO getProVO(ResultSet rs) throws SQLException {
		ProVO proVO = new ProVO();
		proVO.setProId(rs.getInt("PRO_ID"));
		proVO.setWorkId(rs.getInt("WORK_ID"));
		proVO.setProResume(rs.getString("PRO_RESUME"));
		proVO.setExpr(rs.getString("EXPR"));
		proVO.setProPhoto(rs.getBytes("PRO_PHOTO"));
		return proVO;
	}

	@Override
	public List<ProVO> getAll() {

		List<ProVO> proList = new ArrayList<ProVO>();
		try (Connection con = DriverManager.getConnection(url, userid, passwd);) {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProVO proVO = getProVO(rs);
				proList.add(proVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proList;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	public static void main(String[] args) throws IOException {

		ProDAO_interface dao = new ProJDBCDAO();
//
//		ProVO vo = new ProVO();
//		vo.setWorkId(2006);
//		vo.setProResume("跑步111");
//		vo.setExpr("跑步吧");
//		byte[] pic = getPictureByteArray("images/3001.jpg");
//		vo.setProPhoto(pic);
//		vo.setProId(3052);
//		dao.insert(vo);
//		dao.update(vo);

//		ProVO vo2 = dao.findByPrimaryKey(1);
//		System.out.println(vo2);
		
		List<ProVO> list = dao.getAll();
		for(ProVO vo:list) {
			
			System.out.println(vo);
			
		}

	}

}