package tool;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.members.model.MembersVO;



public class LoginFilter implements Filter {
	
	ServletContext context = null;
	
	public void init (FilterConfig config) {
		context = config.getServletContext();
	}
	
	public void destory() {
		context = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		MembersVO membersVO = (MembersVO)session.getAttribute("membersVOLogin");
		System.out.println("test_Filter");
		if(membersVO == null) {
			session.setAttribute("location", req.getServletPath());
			System.out.println(req.getServletPath());
			res.sendRedirect(req.getContextPath() + "/front-end/members/signin.jsp");
			return;
		
		}else {
			chain.doFilter(request, response);
		}	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
