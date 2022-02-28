package com.members.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.members.model.*;

public class MembersServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//test_1-1

		//登出
		if ("logout".equals(action)) { // 來自index.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
						
			HttpSession session = req.getSession();
			session.invalidate();
			String url = "/front-end/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMem.jsp
			successView.forward(req, res);
		}
		
		//登入
		if ("login".equals(action)) { // 來自signin.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//帳號
				String memAcc = req.getParameter("memAcc").trim();
				memAcc = memAcc.toLowerCase();
				if (memAcc == null || memAcc.length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				}
				
				//密碼
				String memPsw = req.getParameter("memPsw").trim();
				String memPswReg = "^[(a-zA-Z0-9)]{2,10}$";
				if (memPsw == null || memPsw.length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if(!memPsw.matches(memPswReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼: 只能是英文字母、數字 , 且長度必需在2到10之間");
	            }
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/members/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MembersService membersSvc = new MembersService();
				MembersVO membersVO = membersSvc.getOneMembersByMemAcc(memAcc);
				if (membersVO == null ) {
					errorMsgs.add("無此帳號");
				}else if (!(membersVO.getMemPsw().equals(memPsw))) {
System.out.println("membersVO.getMemPsw()="+membersVO.getMemPsw());					
System.out.println("memPsw="+memPsw);					
					errorMsgs.add("密碼錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/members/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				ProService proSvc = new ProService();
//			    ProVO proVO = proSvc.getProByMembers(membersVO.getMemId());
			    
			    /***************************3.查詢完成,準備轉交(Send the Success view)*************/
			    HttpSession session = req.getSession();
			    session.setAttribute("membersVOLogin", membersVO);
//			    if(proVO != null) {
//			    	if(proVO.getPro_auth()==1) {
//			    session.setAttribute("proVOLogin", proVO);
//			    }
//			    }
			    
			    String url = (String)session.getAttribute("location");
			    System.out.println( "location =" + url);
			    if(url == "" || url == null) {
			    	url = "/front-end/index/index.jsp";
			    }
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMembers.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/members/signin.jsp");
				failureView.forward(req, res);
			}
		}
	

//test_1-2
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("memId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/members/test99.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
			
				Integer memId = null;
				try {
					memId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/members/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MembersService membersSve = new MembersService();
				MembersVO membersVO = membersSve.getOneMembers(memId);
				if (membersVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/index/members_only2.jsp"); 
					//index的_ACTION="<%=request.getContextPath()%>/members/members.do" →會抓到這裡
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("membersVO", membersVO); // 資料庫取出的membersVO物件,存入req
				String url = "/back-end/members/listOneMembers.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMembers.jsp
				successView.forward(req, res);
			
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());

				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/members/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMembers.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer memId = new Integer(req.getParameter("memId"));
				
				/***************************2.開始查詢資料****************************************/
				MembersService membersSve = new MembersService();
				MembersVO membersVO = membersSve.getOneMembers(memId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("membersVO", membersVO);         // 資料庫取出的membersVO物件,存入req
				String url = "/back-end/members/update_members_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_members_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/members/listAllMembers.jsp");
				failureView.forward(req, res);
			}
		}
		
	
		if ("update".equals(action)) { // 來自update_members_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer memId = new Integer(req.getParameter("memId").trim());
				
				String memName = req.getParameter("memName");
				String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memName.trim().matches(memNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String memAcc = req.getParameter("memAcc").trim();
				String memAccReg = "^[(a-zA-Z0-9)]{8,12}$";
				if (memAcc == null || memAcc.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				} else if(!memAcc.trim().matches(memAccReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("帳號必須是: 英文字母、數字, 且長度必需在8到12之間");
	            }
				
				String memPsw = req.getParameter("memPsw").trim();
				String memPswReg = "^[(a-zA-Z0-9)]{6,12}$";
				if (memPsw == null || memPsw.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				} else if(!memPsw.trim().matches(memPswReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼必須是: 英文字母、數字, 且長度必需在6到12之間");
		        }

				java.sql.Date memBir = null;
				try {
					memBir = java.sql.Date.valueOf(req.getParameter("memBir").trim());
				} catch (IllegalArgumentException e) {
					memBir=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String sex = req.getParameter("sex");
			
				String memAddr = req.getParameter("memAddr").trim();
//				String memAddrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
//				if (memAddr == null || memAddr.trim().length() == 0) {
//					errorMsgs.add("地址請勿空白");
//				} 
//				else if(!memAddr.trim().matches(memAddrReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("地址必須是: 中文字、數字");
//		        }

				String memEmail = req.getParameter("memEmail").trim();
				String memEmailReg = "^([a-zA-Z0-9_]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,4})*$";
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				} else if(!memEmail.trim().matches(memEmailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("信箱格式錯誤");
				}
				String memPhone = req.getParameter("memPhone").trim();
				String memPhoneReg = "^(09)[(0-9)]{8}$";
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("手機請勿空白");
				} else if(!memPhone.trim().matches(memPhoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機必須是: 09開頭+8碼");
		        }
				
				String memResume = req.getParameter("memResume");
//				String memResumeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_，。)]{2,10000}$";
				if (memResume == null || memResume.trim().length() == 0) {
					errorMsgs.add("簡介請勿空白");
				}
//				else if (!memResume.trim().matches(memResumeReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("簡介: 只能是中、英文字母、數字和_ , 可輸入長度在1到10000之間");
//				}
	
				java.sql.Date memRegDate = null;
				try {
					memRegDate = java.sql.Date.valueOf(req.getParameter("memRegDate").trim());
				} catch (IllegalArgumentException e) {
					memRegDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
//會員_權限狀態allAuth
				Integer allAuth = new Integer(req.getParameter("allAuth").trim());		
//會員_發文權限artAuth
				Integer artAuth = new Integer(req.getParameter("artAuth").trim());
//會員_留言權限comAuth
				Integer comAuth = new Integer(req.getParameter("comAuth").trim());
				
				MembersVO membersVO = new MembersVO();
				membersVO.setMemId(memId);
				membersVO.setMemName(memName);
				membersVO.setMemAcc(memAcc);
				membersVO.setMemPsw(memPsw);
				membersVO.setMemBir(memBir);
				membersVO.setSex(sex);
				membersVO.setMemAddr(memAddr);
				membersVO.setMemEmail(memEmail);
				membersVO.setMemPhone(memPhone);
				membersVO.setMemResume(memResume);
				membersVO.setMemRegDate(memRegDate);
				membersVO.setAllAuth(allAuth); //會員_權限狀態
				membersVO.setArtAuth(artAuth); //會員_發文權限
				membersVO.setComAuth(comAuth); //會員_留言權限

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.getSession().setAttribute("membersVOLogin", membersVO); // 含有輸入格式錯誤的membersVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/index/members_only3.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MembersService membersSve = new MembersService();
				membersVO = membersSve.updateMembers(memId, memName, memAcc, memPsw, memBir, sex, memAddr, memEmail, memPhone, memResume, memRegDate, allAuth, artAuth, comAuth);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("membersVO", membersVO); // 資料庫update成功後,正確的的membersVO物件,存入req
//test_2-1			
				HttpSession session = req.getSession();
			    session.setAttribute("membersVOLogin", membersVO);
//test_2-2
				String url = "/front-end/index/members_only2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMembers.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/index/members_only3.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // 來自addMembers.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String memName = req.getParameter("memName");
				String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} 
				else if(!memName.trim().matches(memNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String memAcc = req.getParameter("memAcc").trim();
				String memAccReg = "^[(a-zA-Z0-9)]{8,12}$";
				if (memAcc == null || memAcc.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				} else if(!memAcc.trim().matches(memAccReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("帳號必須是: 英文字母、數字, 且長度必需在8到12之間");
	            }
				
				String memPsw = req.getParameter("memPsw").trim();
				String memPswReg = "^[(a-zA-Z0-9)]{6,12}$";
				if (memPsw == null || memPsw.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				} 
				else if(!memPsw.trim().matches(memPswReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼必須是: 英文字母、數字, 且長度必需在6到12之間");
		        }

				java.sql.Date memBir = null;
				try {
					memBir = java.sql.Date.valueOf(req.getParameter("memBir").trim());
				} catch (IllegalArgumentException e) {
					memBir=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String sex = req.getParameter("sex");
			
				String memAddr = req.getParameter("memAddr").trim();
				String memAddrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
				if (memAddr == null || memAddr.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				} 
//				else if(!memAddr.trim().matches(memAddrReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("地址必須是: 中文字、數字");
//		        }

				String memEmail = req.getParameter("memEmail").trim();
				String memEmailReg = "^([a-zA-Z0-9_]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,4})*$";
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				} 
				else if(!memEmail.trim().matches(memEmailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("信箱格式錯誤");
				}
				String memPhone = req.getParameter("memPhone").trim();
				String memPhoneReg = "^(09)[(0-9)]{8}$";
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("手機請勿空白");
				} 
				else if(!memPhone.trim().matches(memPhoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機必須是: 09開頭+8碼");
		        }

				MembersVO membersVO = new MembersVO();
				membersVO.setMemName(memName);
				membersVO.setMemAcc(memAcc);
				membersVO.setMemPsw(memPsw);
				membersVO.setMemBir(memBir);
				membersVO.setSex(sex);
				membersVO.setMemAddr(memAddr);
				membersVO.setMemEmail(memEmail);
				membersVO.setMemPhone(memPhone);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("membersVO", membersVO); // 含有輸入格式錯誤的membersVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/members/signup.jsp");
					System.out.println("test_錯誤1_輸入內容格式不正確");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MembersService membersSve = new MembersService();
				membersVO = membersSve.addMembers(memName, memAcc, memPsw, memBir, sex, memAddr, memEmail, memPhone);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/members/signin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMembers.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/members/signup.jsp");
				System.out.println("test_錯誤_2_其他錯誤");
				failureView.forward(req, res);
			}
		}
			
		
		if ("delete".equals(action)) { // 來自listAllMembers.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer memId = new Integer(req.getParameter("memId"));
				
				/***************************2.開始刪除資料***************************************/
				MembersService membersSve = new MembersService();
				membersSve.deleteMembers(memId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/members/listAllMembers.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/members/listAllMembers.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
