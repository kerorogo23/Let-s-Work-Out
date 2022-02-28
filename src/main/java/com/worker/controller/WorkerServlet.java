package com.worker.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.work_power.model.Work_powerService;
import com.work_power.model.Work_powerVO;
import com.worker.model.WorkerService;
import com.worker.model.WorkerVO;

public class WorkerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		System.out.println(req.getContextPath());
		System.out.println(req.getRequestURI());
		System.out.println("02");
		System.out.println(action);

		if ("login".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String w_acc = req.getParameter("w_acc");
				String w_pw = req.getParameter("w_pw");

				//帳號
				if (w_acc == null || (w_acc.trim()).length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				}
				//密碼
				if (w_pw == null || (w_pw.trim()).length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} 
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/worker/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************2.開始查詢資料*****************************************/
				WorkerService workerSvc = new WorkerService();
				WorkerVO workerVO = workerSvc.getOneByAcc(w_acc);

				if (workerVO == null || !(workerVO.getW_pw().equals(w_pw))) {
					errorMsgs.add("帳號或密碼錯誤");
				}
				System.out.println(workerVO.getW_pw().equals(w_pw));
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/worker/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("workerVOLogin", workerVO);
				
				String url = "/back-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/login.jsp");
				failureView.forward(req, res);
			}
		}
		
		//登出
		if ("logout".equals(action)) { // 來自index.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
										
			HttpSession session = req.getSession();
			session.invalidate();
			String url = "/back-end/worker/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMem.jsp
			successView.forward(req, res);
		}
		
		//修改密碼
		if ("update_pw".equals(action)) { // 來自index.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//會員編號
				Integer work_id = Integer.parseInt(req.getParameter("work_id"));
				//舊密碼
				String w_pw = req.getParameter("w_pw");
				//新密碼
				String w_npw = req.getParameter("w_npw");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/index.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************2.開始查詢資料*****************************************/
				WorkerService workerSvc = new WorkerService();
				workerSvc.updatePw(work_id, w_npw);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				String url = "/back-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/index.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("getOne_For_Display".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer work_id = Integer.parseInt(req.getParameter("work_id"));
			

				/*************************** 2.開始查詢資料 ****************************************/
				WorkerService workerSvc = new WorkerService();
				WorkerVO workerVO = workerSvc.getOneWorker(work_id);


				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("workerVO", workerVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/worker/listOneWorker.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/worker/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求


			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String w_name = req.getParameter("w_name");
				String w_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (w_name == null || w_name.trim().length() == 0) {
					errorMsgs.put("w_name", "員工姓名: 請勿空白");
				} else if (!w_name.trim().matches(w_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("w_name", "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				String w_acc = req.getParameter("w_acc");
				String w_accReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (w_acc == null || w_acc.trim().length() == 0) {
					errorMsgs.put("w_acc", "員工帳號: 請勿空白");
				} else if (!w_acc.trim().matches(w_accReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("w_acc", "員工帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String w_pw = "111111";
				String w_pwReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (w_pw == null || w_pw.trim().length() == 0) {
					errorMsgs.put("w_pw", "密碼: 請勿空白");
				} else if (!w_pw.trim().matches(w_pwReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("w_pw", "密碼: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String email = req.getParameter("email");
				if (email == null || email.trim().length() == 0) {
					errorMsgs.put("email", "信箱: 請勿空白");
				} 
				
				WorkerVO workerVO = new WorkerVO();
				workerVO.setW_name(w_name);
				workerVO.setW_acc(w_acc);
				workerVO.setW_pw(w_pw);
				workerVO.setEmail(email);
				
				String power[] = req.getParameterValues("POWER");

				StringBuffer sb = new StringBuffer();
				if (power == null) {
					sb.append("");
				} else {
					for(String i:power) {
						sb.append(i);
					}
				}
				String wp = sb.toString();

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("workerVO", workerVO); // 含有輸入格式錯誤的empVO物件,也存入req
					req.setAttribute("wp", wp); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/worker/addWorker.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				WorkerService workerSvc = new WorkerService();
				workerSvc.addWorker(w_name, w_acc, w_pw, email);
				workerVO = workerSvc.getOneByAcc(w_acc);
				
				if (power != null) {
					Work_powerService work_powerSvc = new Work_powerService();
					for(String i:power){
						int j = Integer.parseInt(i);
						work_powerSvc.addWork_power(workerVO.getWork_id(), j);
					}
				}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/index.jsp";
				System.out.println("04");

				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/worker/addWorker.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				int work_id = Integer.parseInt(req.getParameter("work_id"));
				
			
				/*************************** 2.開始新增資料 ***************************************/
				WorkerService workerSvc = new WorkerService();
				workerSvc.deleteWorker(work_id);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/worker/listAllWorker.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/worker/addWorker.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer work_id = Integer.parseInt(req.getParameter("work_id"));
				System.out.println(work_id);
				/***************************2.開始查詢資料****************************************/
				WorkerService workerSvc = new WorkerService();
				WorkerVO workerVO = workerSvc.getOneWorker(work_id);
				System.out.println(workerVO.getWork_id());
				Work_powerService workpowerSvc = new Work_powerService();
				List<Work_powerVO> wpVO = workpowerSvc.getByWorker(work_id);
				StringBuffer sb = new StringBuffer();
				for(Work_powerVO i:wpVO) {
					sb.append(i.getPower_id());
				}
				String wp = sb.toString();
				

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("workerVO", workerVO);         // 資料庫取出的empVO物件,存入req
				req.setAttribute("wp",wp);
				String url = "/back-end/worker/update_worker_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/worker/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String w_name = req.getParameter("w_name");
				String w_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (w_name == null || w_name.trim().length() == 0) {
					errorMsgs.put("w_name", "員工姓名: 請勿空白");
				} else if (!w_name.trim().matches(w_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("w_name", "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				String w_acc = req.getParameter("w_acc");
				String w_accReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (w_acc == null || w_acc.trim().length() == 0) {
					errorMsgs.put("w_acc", "員工帳號: 請勿空白");
				} else if (!w_acc.trim().matches(w_accReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("w_acc", "員工帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String w_pw = req.getParameter("w_pw");
				String w_pwReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (w_pw == null || w_pw.trim().length() == 0) {
					errorMsgs.put("w_pw", "密碼: 請勿空白");
				} else if (!w_pw.trim().matches(w_pwReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("w_pw", "密碼: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String email = req.getParameter("email");
				if (email == null || email.trim().length() == 0) {
					errorMsgs.put("email", "信箱: 請勿空白");
				} 
					
				
				
				Integer work_id = Integer.parseInt(req.getParameter("work_id"));
				Integer all_auth = Integer.parseInt(req.getParameter("all_auth"));
				String reg_date = req.getParameter("reg_date");
				 Timestamp timestamp=null;
				try {
				    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				    java.util.Date parsedDate = dateFormat.parse(reg_date);
				    timestamp = new java.sql.Timestamp(parsedDate.getTime());
				} catch(Exception e) { //this generic but you can control another types of exception
					e.printStackTrace();
				}
				WorkerVO workerVO = new WorkerVO();
				workerVO.setWork_id(work_id);
				workerVO.setW_name(w_name);
				workerVO.setW_acc(w_acc);
				workerVO.setW_pw(w_pw);
				workerVO.setEmail(email);
				workerVO.setAll_auth(all_auth);
				workerVO.setReg_date(timestamp);
				
				
				String power[] = req.getParameterValues("POWER");
//				if (power == null) {
//					errorMsgs.add("請選擇權限");
//				}
				
				StringBuffer sb = new StringBuffer();
				if (power == null) {
					sb.append("");
				} else {
					for(String i:power) {
						sb.append(i);
					}
				}
				String wp = sb.toString();
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("workerVO", workerVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/worker/addWorker.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				WorkerService workerSvc = new WorkerService();
				workerSvc.updateWorker(w_name,w_acc, w_pw, email, all_auth,work_id);
				
				//取出員工權限,並轉成字串
				Work_powerService workpowerSvc = new Work_powerService();
				List<Work_powerVO> wpVO = workpowerSvc.getByWorker(work_id);
				

				if(power != null) {
					Work_powerVO workPowerVO = new Work_powerVO();
					workpowerSvc.delete(work_id);
					for(String i:power){
						int j = Integer.parseInt(i);
						workpowerSvc.addWork_power(workerVO.getWork_id(), j);
						}			
				}else {
					workpowerSvc.delete(work_id);
				}
				

			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("workerVO", workerVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/worker/listAllWorker.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("","修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/update_worker_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
