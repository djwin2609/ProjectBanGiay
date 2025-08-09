package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.OrderDetailBean;
import bean.UserBean;
import dao.CartDao;
import dao.VerifyOrderDao;

/**
 * Servlet implementation class VerifyOrderController
 */
@WebServlet("/VerifyOrderController1")
public class VerifyOrderController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifyOrderController1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String successMsg = request.getParameter("success");
		if ("true".equals(successMsg)) {
			request.setAttribute("success", "Đặt hàng thành công!");
		}	
		HttpSession session = request.getSession(false); // false để không tạo session mới nếu chưa có

		// Kiểm tra người dùng đã đăng nhập chưa
		if (session == null || session.getAttribute("User") == null) {
			response.sendRedirect("Login.jsp"); // hoặc trang báo lỗi
			return;
		}
		UserBean user = (UserBean) session.getAttribute("User");
		int user_id = user.getUser_id();
		try {
			CartDao cartDao = new CartDao();
			List<CartBean> cart = cartDao.getCartByUserId(user_id);
			request.setAttribute("cart", cart);
			RequestDispatcher rd = request.getRequestDispatcher("VerifyOrder.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("User") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		UserBean user = (UserBean) session.getAttribute("User");
		int userId = user.getUser_id();
		System.out.println("id is call"+userId);
		System.out.println("VerifyOrderController is called");
		String fullName = request.getParameter("fullName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String payment = request.getParameter("payment");
		
		try {

			CartDao crDao = new CartDao();
			List<CartBean> cart = crDao.getCartByUserId(userId);
			List<OrderDetailBean> orderDetails = new ArrayList<>();
			double total = 0;
			for (CartBean item : cart) {
				total += item.getPrice() * item.getQuantity_buy();
				OrderDetailBean od = new OrderDetailBean();
				od.setProduct_id(item.getProductID());
				od.setQuantity(item.getQuantity_buy());
				od.setProductName(item.getProductName());
				od.setPrice(item.getPrice());
				orderDetails.add(od);
			}
			VerifyOrderDao orderDAO = new VerifyOrderDao();
			boolean success = orderDAO.insertOrder(userId, fullName, phone, new Date(System.currentTimeMillis()), total,
					payment, address, "Chờ xác nhận",orderDetails);
			if (success) {
				// Chuyển đến trang xác nhận thành công
				response.sendRedirect("VerifyOrderController?success=true");
				
			} else {
				request.setAttribute("error", "Đặt hàng thất bại!");
				request.getRequestDispatcher("HomePage.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
