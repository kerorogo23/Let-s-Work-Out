package com.grouporder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grouphour.model.GroupHourService;
import com.grouporder.model.GroupOrderService;
import com.grouporder.model.GroupOrderVO;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

public class GroupOrderServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("application/json;charset=utf-8");
		if ("insert".equals(action)) {
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				HttpSession session = req.getSession();
//				Enumeration<String> enum1 = session.getAttributeNames();
//				while(enum1.hasMoreElements()) {
//					String name = enum1.nextElement();
//					System.out.println(name+":"+session.getAttribute(name));
//				}
				MembersVO membersVO = (MembersVO) session.getAttribute("membersVOLogin");
				Integer memId = membersVO.getMemId();
				System.out.println();
				System.out.print(memId);
				Integer groupTimeNo = new Integer(req.getParameter("groupTimeNo"));
				Long datetime = System.currentTimeMillis();
				Timestamp groupOrderTime = new Timestamp(datetime);
				GroupOrderVO groupOrderVO = new GroupOrderVO();
				groupOrderVO.setMemId(memId);
//				System.out.print(memId);
				groupOrderVO.setGroupTimeNo(groupTimeNo);
//				System.out.print(groupTimeNo);
				groupOrderVO.setGroupOrderTime(groupOrderTime);
				/***************************2.開始新增資料***************************************/
				GroupOrderService groupOrderSvc = new GroupOrderService();
				groupOrderVO = groupOrderSvc.addGroupOrder(memId, groupTimeNo, groupOrderTime);
				Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//				System.out.print(groupOrderVO);
				String json = gsonBuilder.toJson(groupOrderVO);
				PrintWriter out = null;
				try {
					out = res.getWriter();
					out.write(json);
//					System.out.print("2222");
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
		if("getOne_For_Display_B".equals(action)) {
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer memId = new Integer(req.getParameter("memId"));
				Integer groupTimeNo = new Integer(req.getParameter("groupTimeNo"));
				String time = req.getParameter("groupOrderTime");
				java.sql.Timestamp groupOrderTime = java.sql.Timestamp.valueOf((time).trim());
				
				/***************************2.開始查詢資料*****************************************/
				GroupOrderService groupOrderSvc = new GroupOrderService();
				GroupOrderVO groupOrderVO = groupOrderSvc.getOneGroupOrder2(memId, groupTimeNo, groupOrderTime);
				Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//				System.out.print(groupOrderVO);
				String json = gsonBuilder.toJson(groupOrderVO);
				PrintWriter out = null;
				try {
					out = res.getWriter();
					out.write(json);
					System.out.print("2222");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (out != null) {
						out.close();
					}
				}

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/grouporder/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listGroupOrders_ByGroupTimeNo_A".equals(action) || "listGroupOrders_ByGroupTimeNo_B".equals(action)) {
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer groupTimeNo = new Integer(req.getParameter("groupTimeNo"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				GroupHourService groupHourSvc = new GroupHourService();
				Set<GroupOrderVO> set = groupHourSvc.getGroupOrdersByGroupTimeNo(groupTimeNo);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				System.out.print(set);
				String json = gsonBuilder.toJson(set);
				PrintWriter out = null;
				try {
					out = res.getWriter();
					out.write(json);
					System.out.print("2222");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (out != null) {
						out.close();
					}
				}
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("getOneMem_For_Display".equals(action)) { // 來自select_page.jsp的請求

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer memId  = new Integer(req.getParameter("memId"));			
				/***************************2.開始查詢資料*****************************************/
				MembersService membersSve = new MembersService();
				MembersVO membersVO = membersSve.getOneMembers(memId);
				Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json = gsonBuilder.toJson(membersVO);
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
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/members/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("listGroupOrders_ByMemId_A".equals(action) || "listGroupOrders_ByMemId_B".equals(action)) {
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				HttpSession session = req.getSession();
				MembersVO membersVO = (MembersVO) session.getAttribute("membersVOLogin");
				Integer memId = membersVO.getMemId();

				/*************************** 2.開始查詢資料 ****************************************/
				GroupOrderService groupOrderSvc = new GroupOrderService();
				Set<GroupOrderVO> set = groupOrderSvc.getGroupOrdersByMemId(memId);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				System.out.print(set);
				String json = gsonBuilder.toJson(set);
				PrintWriter out = null;
				try {
					out = res.getWriter();
					out.write(json);
//					System.out.print("2222");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (out != null) {
						out.close();
					}
				}
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("update".equals(action)) { 

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer groupOrderNo = new Integer(req.getParameter("groupOrderNo"));
				
				HttpSession session = req.getSession();
				MembersVO membersVO = (MembersVO) session.getAttribute("membersVOLogin");
				Integer memId = membersVO.getMemId();
				
				Integer groupTimeNo = new Integer(req.getParameter("groupTimeNo"));
				Timestamp groupOrderTime = java.sql.Timestamp.valueOf(req.getParameter("groupOrderTime"));
				Integer groupOrderStatus = new Integer(req.getParameter("groupOrderStatus"));
				
				GroupOrderVO groupOrderVO = new GroupOrderVO();
				groupOrderVO.setGroupOrderNo(groupOrderNo);
				groupOrderVO.setMemId(memId);;
				groupOrderVO.setGroupTimeNo(groupTimeNo);
				groupOrderVO.setGroupOrderTime(groupOrderTime);
				groupOrderVO.setGroupOrderStatus(groupOrderStatus);
				
				/***************************2.開始修改資料*****************************************/
				GroupOrderService groupOrderSvc = new GroupOrderService();
				groupOrderVO = groupOrderSvc.updateGroupOrder(groupOrderNo, memId, groupTimeNo, groupOrderTime, groupOrderStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//				System.out.print(set);
				String json = gsonBuilder.toJson(groupOrderVO);
				PrintWriter out = null;
				try {
					out = res.getWriter();
					out.write(json);
//					System.out.print("2222");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (out != null) {
						out.close();
					}
				}

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/grouporder/update_groupOrder_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("listGroupOrderCount_ByGroupTimeNo_A".equals(action) || "listGroupOrderCount_ByGroupTimeNo_B".equals(action)) {
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer groupTimeNo = new Integer(req.getParameter("groupTimeNo"));
				Integer groupOrderStatus = new Integer(req.getParameter("groupOrderStatus"));
				/*************************** 2.開始查詢資料 ****************************************/
				GroupOrderService groupOrderSvc = new GroupOrderService();
				Set<GroupOrderVO> set = groupOrderSvc.getOneGroupOrderCount(groupTimeNo, groupOrderStatus);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				Gson gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				System.out.print(set);
				String json = gsonBuilder.toJson(set);
				PrintWriter out = null;
				try {
					out = res.getWriter();
					out.write(json);
					System.out.print("2222");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (out != null) {
						out.close();
					}
				}
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
