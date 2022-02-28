package shopping;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import shopping.PRODUCT;

public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		
		@SuppressWarnings("unchecked")
		List<PRODUCT> buylist = (Vector<PRODUCT>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

		if (!action.equals("CHECKOUT")) {

			// �R���ʪ����������y
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
			}
			// �s�W���y���ʪ�����
			else if (action.equals("ADD")) {
				// ���o��ӷs�W�����y
				PRODUCT aproduct = getproduct(req);

				if (buylist == null) {
					buylist = new Vector<PRODUCT>();
					buylist.add(aproduct);
				} else {
					if (buylist.contains(aproduct)) {
						PRODUCT innerPRODUCT = buylist.get(buylist.indexOf(aproduct));
						innerPRODUCT.setQuantity(innerPRODUCT.getQuantity() + aproduct.getQuantity());
					} else {
						buylist.add(aproduct);
					}
				}
			}

			session.setAttribute("shoppingcart", buylist);
			String url = "/front-end/shoppingcart/EShop.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// ���b�A�p���ʪ������y�����`��
		else if (action.equals("CHECKOUT")) {
			double total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				PRODUCT order = buylist.get(i);
				Double price = order.getPrice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
			}

			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/front-end/shoppingcart/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

	private PRODUCT getproduct(HttpServletRequest req) {

		String quantity = req.getParameter("quantity");
		String name = req.getParameter("name");
		String price = req.getParameter("price");

		PRODUCT PRODUCT = new PRODUCT();

		PRODUCT.setName(name);
		PRODUCT.setPrice(new Double(price));
		PRODUCT.setQuantity((new Integer(quantity)).intValue());
		return PRODUCT;
	}
}