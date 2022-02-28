package com.createProductPhoto;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class InitPhotos  {
	
	
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/LWO?serverTimezone=Asia/Taipei";
	private static final String userid = "John";
	private static final String passwd = "123456";
	private static final String SQL = "INSERT INTO product_photo (product_no, p_photo) VALUES ( ?, ?)";
	
	public static void main(String[] args) {
		

		
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(SQL);



			pstmt.setInt(1, 4001);
			byte[] pic = getPictureByteArray("C:/CFA103_WorkSpace/CFA103G1/src/main/webapp/front-end/pic/4001-乳清.jpg");
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();

			pstmt.setInt(1, 4002);
			pic = getPictureByteArray("C:/CFA103_WorkSpace/CFA103G1/src/main/webapp/front-end/pic/4002-健身組.jpg");
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();

			pstmt.setInt(1, 4003);
			pic = getPictureByteArray("C:/CFA103_WorkSpace/CFA103G1/src/main/webapp/front-end/pic/4003-壺鈴.jpg");
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 4004);
			pic = getPictureByteArray("C:/CFA103_WorkSpace/CFA103G1/src/main/webapp/front-end/pic/4004-可調節啞鈴組.jpg");
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 4005);
			pic = getPictureByteArray("C:/CFA103_WorkSpace/CFA103G1/src/main/webapp/front-end/pic/4005-啞鈴.jpg");
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 4006);
			pic = getPictureByteArray("C:/CFA103_WorkSpace/CFA103G1/src/main/webapp/front-end/pic/4006-滑輪多功能訓練架.jpg");
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 4007);
			pic = getPictureByteArray("C:/CFA103_WorkSpace/CFA103G1/src/main/webapp/front-end/pic/4007-瑜珈墊.jpg");
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 4008);
			pic = getPictureByteArray("C:/CFA103_WorkSpace/CFA103G1/src/main/webapp/front-end/pic/4008-瑜珈球.jpg");
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 4009);
			pic = getPictureByteArray("C:/CFA103_WorkSpace/CFA103G1/src/main/webapp/front-end/pic/4009-彈力帶.jpg");
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 4010);
			pic = getPictureByteArray("C:/CFA103_WorkSpace/CFA103G1/src/main/webapp/front-end/pic/4010-按摩滾輪.jpg");
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();

			System.out.println("新增成功");

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
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

}
