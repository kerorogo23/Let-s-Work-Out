package com.product_photo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@WebServlet("/product_photo/upload.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); 



		Part part = req.getPart("pic"); 
			String filename = getFileNameFromPart(part);
			if (filename!= null && part.getContentType()!=null) {
				
				InputStream in = part.getInputStream();
				byte[] pic = new byte[in.available()];
				in.read(pic);
				req.setAttribute("pic", pic);
				in.close();
			}

	}


	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}