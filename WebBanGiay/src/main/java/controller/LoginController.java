package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderBean;
import bean.ProductDetailBean;
import bean.UserBean;
import bo.AdminBo;
import bo.LoginBo;
import bo.ProductBo;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String MsgLogin=request.getParameter("success");
		if("true".equals(MsgLogin)) {
			request.setAttribute("success", "Đăng nhập thành công");
		}
		LoginBo LogBo = new LoginBo();
		AdminBo adbo = new AdminBo();
		ProductBo proBo = new ProductBo();
		String UserName = request.getParameter("txtun");
		String Password = request.getParameter("txtpass");
		System.out.println("User nhập: " + UserName + " / " + Password);
		
		
		try {
			List<UserBean>dsUser=adbo.getUser1();
			List<ProductDetailBean>dsProduct=adbo.getProduct1();
			List<OrderBean> Order = adbo.getOrderAdmin();
			if (UserName != null && Password != null && !UserName.trim().equals("") && !Password.trim().equals("")) {
				boolean found = false;
				// Duyệt danh sách và kiểm tra
				for (UserBean ub : LogBo.getLogin()) {
					if (UserName.equals(ub.getUserName()) && Password.equals(ub.getPassword())) {
						found = true;
						HttpSession session = request.getSession();
						session.setAttribute("Login", true);   //dùng để check login
						session.setAttribute("User", ub);     
						session.setAttribute("username",ub.getName() );//
						
						// Admin=0
						if (ub.getRole() == 0) {
							request.setAttribute("DsUser", dsUser);
							request.setAttribute("Dsproduct", dsProduct);
							
							request.setAttribute("dsOrder", Order);
							request.setAttribute("success", "Đăng nhập thành công");
							request.getRequestDispatcher("Admin.jsp").forward(request, response);
						} else {
							// client=1
							List<ProductDetailBean> productList = proBo.getPro();
							request.setAttribute("dssanpham", productList);
							
							request.setAttribute("success", "Đăng nhập thành công");
							request.getRequestDispatcher("HomePage.jsp").forward(request, response);
						}
						return;
					}
				}
				 if (!found) {
	                    request.setAttribute("ThongBao", "Sai tài khoản hoặc mật khẩu!");
	                }
				//request.setAttribute("kt", "dang nhap sai");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
		}
		RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
