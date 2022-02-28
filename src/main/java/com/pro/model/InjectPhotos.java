package com.pro.model;

import java.io.FileInputStream;
import java.util.List;

public class InjectPhotos {
	public static void main(String[] args) throws Exception {
		
		ProJDBCDAO dao = new ProJDBCDAO();
		List<ProVO> list = dao.getAll();
		
		
		for(ProVO proVO:list) {
			
			byte[] photo= getPhoto(proVO.getProId());
			proVO.setProPhoto(photo);
			dao.update(proVO);
			
		}
		
	}

	private static byte[] getPhoto(Integer proId) throws Exception {
		String pathTemplate = "./photos/%s.jpg";
		FileInputStream fis = new FileInputStream(String.format(pathTemplate, proId));
		
		byte[] photo = new byte[fis.available()];
		fis.read(photo);
		fis.close();
		
		return photo;
	}
}
