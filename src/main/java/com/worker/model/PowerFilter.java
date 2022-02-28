package com.worker.model;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.work_power.model.*;

public class PowerFilter implements Filter {
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.setConfig(config);
	}

	public void destroy() {
		setConfig(null);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		WorkerVO workerVO = (WorkerVO)session.getAttribute("workerVOLogin");
				
		if (workerVO == null) {
			res.sendRedirect(req.getContextPath() + "/back-end/worker/login.jsp");
			return;
		} else {
			String requrl = req.getRequestURI();
			boolean power00001 = false, power00002 = false, power00003 = false, 
					power00004 = false, power00005 = false, power00006 = false;
				   			
			// 01員工 02會員 03課程 04商城05檢舉06食物
			int work_id = workerVO.getWork_id();
			Work_powerService work_powerSvc = new Work_powerService();
			List<Work_powerVO> list = work_powerSvc.getByWorker(work_id);
			for (Work_powerVO workpowerVO : list) {
				if ( 9001 == workpowerVO.getPower_id()) {
					power00001 = true;
				}
				if ( 9002 == workpowerVO.getPower_id()) {
					power00002 = true;
				}
				if ( 9003 == workpowerVO.getPower_id()) {
					power00003 = true;
				}
				if ( 9004 == workpowerVO.getPower_id()) {
					power00004 = true;
				}
				if ( 9005 == workpowerVO.getPower_id()) {
					power00005 = true;
				}
				if ( 9006 == workpowerVO.getPower_id()) {
					power00006 = true;
				}
			}

			if ((req.getContextPath()+"/back-end/worker/listAllWorker.jsp").equals(requrl)) {
				if (power00001) {
					chain.doFilter(req, res);
				} else {
					toIndex(req, res);
				}
			} else if ((req.getContextPath()+"/back-end/worker/addWorker.jsp").equals(requrl)) {
				if (power00001) {
					chain.doFilter(req, res);
				} else {
					toIndex(req, res);
				}
			} else if ((req.getContextPath()+"/back-end/members/listAllMembers.jsp").equals(requrl)) {
				if (power00002) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			} else if ((req.getContextPath()+"/back-end/grouporder/listAllGroupOrder.jsp").equals(requrl)) {
				if (power00003) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/grouphour/listAllGroupHour.jsp").equals(requrl)) {
				if (power00003) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/groupclass/listAllGroupClass.jsp").equals(requrl)) {
				if (power00003) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/groupclass/listAllGroupClass.jsp").equals(requrl)) {
				if (power00003) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/classtype/listAllClassType.jsp").equals(requrl)) {
				if (power00003) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/product/listAllProduct.jsp").equals(requrl)) {
				if (power00004) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/purchase_order/listAllOrder.jsp").equals(requrl)) {
				if (power00004) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/pro/select_page.jsp").equals(requrl)) {
				if (power00003) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/course_schedule/select_page.jsp").equals(requrl)) {
				if (power00003) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else if ((req.getContextPath()+"/back-end/course_order/select_page.jsp").equals(requrl)) {
				if (power00003) {
					chain.doFilter(request, response);
				} else {
					toIndex(req, res);
				}
			}else {
				chain.doFilter(request, response);
			}
		}
	}

	public void toIndex(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.sendRedirect(req.getContextPath() + "/back-end/index.jsp");
		return;
	}

	public FilterConfig getConfig() {
		return config;
	}

	public void setConfig(FilterConfig config) {
		this.config = config;
	}
}