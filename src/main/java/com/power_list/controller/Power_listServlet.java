package com.power_list.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.power_list.model.Power_listService;
import com.power_list.model.Power_listVO;

public class Power_listServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@SuppressWarnings("removal")
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		

		if ("getOne_For_Display".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer power_id = Integer.parseInt(req.getParameter("power_id"));
			

				/*************************** 2.開始查詢資料 ****************************************/
				Power_listService power_listSvc = new Power_listService();
				Power_listVO power_listVO = power_listSvc.getOnePower_list(power_id);


				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("power_listVO", power_listVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/powerlist/listOnePowerlist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/powerlist/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String str = req.getParameter("power_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put( "power_id","請輸入功能編號" );
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("LWO/back-end/powerlist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer power_id = null;
				try {
					power_id = new Integer(req.getParameter("power_id"));
				} catch (Exception e) {
					errorMsgs.put("四位數字","員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("LWO/back-end/powerlist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				String power_name = req.getParameter("power_name");
				String power_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{4,10}$";
				if (power_name == null || power_name.trim().length() == 0) {
					errorMsgs.put("power_name", "功能名稱: 請勿空白");
				} else if (!power_name.trim().matches(power_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("power_name", "名稱: 只能是英文字母、數字和_ , 且長度必需在4到10之間");
				}

			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("LWO/back-end/powerlist/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				Power_listService power_listSvc = new Power_listService();
				power_listSvc.addPower_list(power_id, power_name);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "LWO/back-end/powerlist/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("LWO/back-end/powerlist/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String str = req.getParameter("power_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put( "power_id","請輸入功能編號" );
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/powerlist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer power_id = null;
				try {
					power_id = new Integer(req.getParameter("power_id"));
				} catch (Exception e) {
					errorMsgs.put("四位數字","員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/powerlist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				String power_name = req.getParameter("power_name");
				String power_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{4,10}$";
				if (power_name == null || power_name.trim().length() == 0) {
					errorMsgs.put("power_name", "功能名稱: 請勿空白");
				} else if (!power_name.trim().matches(power_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("power_name", "名稱: 只能是英文字母、數字和_ , 且長度必需在4到10之間");
				}

			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/powerlist/addWorker.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				Power_listService power_listSvc = new Power_listService();
				power_listSvc.addPower_list(power_id, power_name);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/powerlist/listAllPowerlist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/powerlist/addpowerlist.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("delete".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				int power_id = Integer.parseInt(req.getParameter("power_id"));
				
			
				/*************************** 2.開始新增資料 ***************************************/
				Power_listService power_listSvc = new Power_listService();
				power_listSvc.deletePower_list(power_id);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/powerlist/listAllPowerlist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("Exception", "無法刪除");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/powerlist/listAllPowerlist.jsp");
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
				Integer power_id = Integer.parseInt(req.getParameter("power_id"));
				System.out.println(power_id);
				/***************************2.開始查詢資料****************************************/
				Power_listService power_listSvc = new Power_listService();
				Power_listVO power_listVO = power_listSvc.getOnePower_list(power_id);
				System.out.println(power_listVO.getPower_id());

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("power_listVO", power_listVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/powerlist/update_powerlist_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/powerlist/listAllPowerlist.jsp");
				failureView.forward(req, res);
			}
		}
		
	if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String power_name = req.getParameter("power_name");
				String power_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (power_name == null || power_name.trim().length() == 0) {
					errorMsgs.put("power_name", "功能名稱: 請勿空白");
				} else if (!power_name.trim().matches(power_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("power_name", "功能名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
			
				
				Integer power_id = Integer.parseInt(req.getParameter("power_id"));
				


				Power_listVO power_listVO = new Power_listVO();
				power_listVO.setPower_id(power_id);
				power_listVO.setPower_name(power_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("power_listVO", power_listVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/powerlist/addpowerlist.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				Power_listService power_listSvc = new Power_listService();
				power_listSvc.updatePower_list(power_id, power_name);
				
			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("power_listVO", power_listVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/powerlist/listOnePowerlist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("","修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/powerlist/update_powerlist_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		}
	}
