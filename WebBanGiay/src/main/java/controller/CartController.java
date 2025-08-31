package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import bo.CartBo;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("Cart.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CartBo cartBo=new CartBo();
		String action = request.getParameter("action");
		if (action.equals("add")) {
			try {
				// Lấy session hiện tại
				HttpSession session = request.getSession(false); // false để không tạo session mới nếu chưa có

				// Kiểm tra người dùng đã đăng nhập chưa
				if (session == null || session.getAttribute("User") == null) {
					response.sendRedirect("Login.jsp"); 
					return;
				}
				UserBean user = (UserBean) session.getAttribute("User");
				int user_id = user.getUser_id();
				// Lấy thông tin sản phẩm từ request
				int Product_id = Integer.parseInt(request.getParameter("Product_id"));
				int quantity = Integer.parseInt(request.getParameter("Quantity"));

				// Gọi DAO để thêm vào giỏ
				
				System.out.println("User_id: " + user_id);
				System.out.println("Product_id: " + Product_id);
				System.out.println("Quantity: " + quantity);

				cartBo.addToCart(user_id, Product_id, quantity);
				
				response.sendRedirect("ClientPagesController");

			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi thêm sản phẩm vào giỏ hàng");
			}
		} else if (action.equals("remove")) {
			HttpSession session = request.getSession(false);
			UserBean user = (UserBean) session.getAttribute("User");
			int User_id = user.getUser_id();
			int Product_id = Integer.parseInt(request.getParameter("Product_id"));
			
			try {
				cartBo.RemoveFromCart(User_id, Product_id);
				response.sendRedirect("Cart.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			request.setAttribute("Thong Bao", "Không xác định được hành động!");
			request.getRequestDispatcher("Admin.jsp").forward(request, response);
		}

	}

}
