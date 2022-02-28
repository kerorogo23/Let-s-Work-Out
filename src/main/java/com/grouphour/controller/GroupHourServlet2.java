package com.grouphour.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.groupclass.model.GroupClassService;
import com.groupclass.model.GroupClassVO;
import com.grouphour.model.GroupHourService;
import com.grouphour.model.GroupHourVO;

public class GroupHourServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("application/json;charset=utf-8");
		
		if("getOne_For_Display".equals(action)) {	
			try { 
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				System.out.print("111111");
				String str = req.getParameter("groupTimeNo");
				Integer groupTimeNo = new Integer(str);

				/***************************2.開始查詢資料*****************************************/
				GroupHourService groupHourSvc = new GroupHourService();
				GroupHourVO groupHourVO = groupHourSvc.getOneGroupHour(groupTimeNo);
//				System.out.print(groupHourVO);
				/***************************3.查詢完成，準備轉交(Send the Success view)*************/
				Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json = gsonBuilder.toJson(groupHourVO);
				PrintWriter out = null;
				try {
					out = res.getWriter();
					out.write(json);
					System.out.print("111111");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (out != null) {
						out.close();
					}
				}
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupclass/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
