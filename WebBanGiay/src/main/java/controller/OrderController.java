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
import bean.OrderBean;
import bean.OrderDetailBean;
import bean.UserBean;
import bo.CartBo;
import bo.OrderBo;


/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
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
		CartBo cartbo = new CartBo();
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

			List<CartBean> cart = cartbo.getCartByUserId(user_id);
			request.setAttribute("cart", cart);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}

		RequestDispatcher rd = request.getRequestDispatcher("VerifyOrder.jsp");
		rd.forward(request, response);
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
		System.out.println("id is call" + userId);
		System.out.println("VerifyOrderController is called");
		String fullName = request.getParameter("fullName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String payment = request.getParameter("payment");
		CartBo cartbo = new CartBo();
		OrderBo ordBo = new OrderBo();

		try {

			List<CartBean> cart = cartbo.getCartByUserId(userId);
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
			boolean success = ordBo.insertOrder(userId, fullName, phone, new Date(System.currentTimeMillis()), total,
					payment, address, "Chờ xác nhận", orderDetails);
			if (success) {

				// response.sendRedirect("VerifyOrderController?success=true");
				OrderBean order = new OrderBean();
				
				order.setOrder_id(ordBo.getLastOrderId(userId));
				order.setName(fullName);
				order.setPhoneNumber(phone);
				order.setShippingAddress(address);
				order.setOrderDate(new Date(System.currentTimeMillis()));
				order.setStatus("Chờ xác nhận");
				order.setTotalAmount(total);

				session.setAttribute("Order", order);
				request.setAttribute("dsOrder", orderDetails);

				// Chuyển đến trang đơn hàng
				request.getRequestDispatcher("Order.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "Đặt hàng thất bại!");
				request.getRequestDispatcher("HomePage.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
