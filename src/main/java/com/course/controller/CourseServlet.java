package com.course.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.course.model.CourseService;
import com.course.model.CourseVO;
import com.pro.model.ProService;
import com.pro.model.ProVO;

@WebServlet("/CourseController")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static ProService proSvc = new ProService();
	private final static CourseService courseSvc = new CourseService();
	
	private final static String PRO_COURSE_PAGE= "/front-end/pro/ProCourse.jsp";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");

		if ("showProAndCourse".equals(action)) {
			// 1. 要有教練個人資料
			Integer proId = Integer.parseInt(req.getParameter("proId"));
			ProVO proVO = proSvc.getOnePro(proId);
			req.setAttribute("proVO", proVO);
			
			// 2. 要有教練課程
			List<CourseVO> courseList= courseSvc.findCourseListByProId(proId);
			req.setAttribute("list", courseList);
			
			// 3. 畫面要導向 ProCourse.jsp
			RequestDispatcher page = req.getRequestDispatcher(PRO_COURSE_PAGE);
			page.forward(req, res);
			
		}
	}

}
