package com.product.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product.model.*;
import com.product_photo.model.ProductPhotoService;
import com.product_photo.model.ProductPhotoVO;
import com.product_category.model.ProductCategoryService;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ShoppingCar extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Integer product_no = Integer.valueOf(req.getParameter("product_no"));
		
		HttpSession session = req.getSession();
		Map<Integer,Integer> shoppingList = (Map<Integer,Integer>)session.getAttribute("shoppingList");
		
		shoppingList.put(product_no, shoppingList.get(product_no)+1);
//		session.setAttribute("shoppingList",shoppingList);
		
		
		System.out.println(shoppingList);

		Integer count=(Integer)session.getAttribute("count");
		
		session.setAttribute("count", ++count);
		System.out.println("count: "+ count);
		
	}
}
