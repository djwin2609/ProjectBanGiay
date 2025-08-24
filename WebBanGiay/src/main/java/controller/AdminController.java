package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.OrderBean;
import bean.OrderDetailBean;
import bean.ProductDetailBean;
import bean.UserBean;
import bo.AdminBo;
import dao.KetNoiCSDL;


/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String successMsg = request.getParameter("success");
		if ("true".equals(successMsg)) {
			request.setAttribute("success", "Thêm người dùng thành công!");
		}
		String productSuccessMsg = request.getParameter("productSuccess");
		if ("true".equals(productSuccessMsg)) {
			request.setAttribute("productSuccess", "Thêm sản phẩm thành công!");
		}
		String EditUsersuccessMsg = request.getParameter("EditUsersuccess");
		if ("true".equals(EditUsersuccessMsg)) {
			request.setAttribute("EditUsersuccess", "Cập nhật người dùng thành công!");
		}
		String EditProductsuccessMsg = request.getParameter("EditProductsuccess");
		if ("true".equals(EditProductsuccessMsg)) {
			request.setAttribute("EditProductsuccessMsg", "Cập nhật sản phẩm thành công!");
		}
		String DeleteUsersuccessMsg = request.getParameter("DeleteUsersuccess");
		if ("true".equals(DeleteUsersuccessMsg)) {
			request.setAttribute("DeleteUsersuccessMsg", "Xóa người dùng thành công");
		}
		String DeleteProcuctsuccessMsg = request.getParameter("DeleteProductsuccess");
		if ("true".equals(DeleteProcuctsuccessMsg)) {
			request.setAttribute("DeleteProductsuccess", "Xóa sản phẩm thành công");
		}
		AdminBo adbo = new AdminBo();
		try {
			for (ProductDetailBean pd : adbo.getProduct1()) {
				System.out.println("ID: " + pd.getProductID());
				System.out.println("Tên sản phẩm: " + pd.getProductName());
				System.out.println("Giá: " + pd.getPrice());
				System.out.println("Hình ảnh: " + pd.getImage());
				System.out.println("Size: " + pd.getSize());
				System.out.println("Thương hiệu: " + pd.getBrand());
				System.out.println("Số lượng tồn: " + pd.getStock_quantity());
				System.out.println("Trạng thái: " + pd.getStatus());
				System.out.println("MoTa: " + pd.getDescription());
				System.out.println("------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			List<UserBean> dsUser = adbo.getUser1();
			request.setAttribute("DsUser", dsUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			List<ProductDetailBean> dsProduct = adbo.getProduct1();
			request.setAttribute("Dsproduct", dsProduct);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			List<OrderBean> Order = adbo.getOrderAdmin();
			request.setAttribute("dsOrder", Order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("Admin.jsp").forward(request, response);
	}

	public boolean checkusertontai(String UserName) throws SQLException {
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		String Sql = "select Count(*) from [User] WHERE UserName=?";
		PreparedStatement cmd = conn.prepareStatement(Sql);
		cmd.setString(1, UserName);
		ResultSet rs = cmd.executeQuery();
		boolean tontai = false;
		if (rs.next()) {
			tontai = rs.getInt(1) > 0;
		}
		rs.close();
		cmd.close();
		conn.close();
		return tontai;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// TODO Auto-generated method stub
		// User
		String UserId = request.getParameter("User_id");
		String UserName = request.getParameter("UserName");
		String Password = request.getParameter("Password");
		String Name = request.getParameter("Name");
		String PhoneNumber = request.getParameter("PhoneNumber");
		String Email = request.getParameter("Email");
		String Role = request.getParameter("Role");
		// Products

		String Product_id = request.getParameter("Product_id");
		String ProductDetail_id = request.getParameter("ProductDetail_id");
		String ProductName = request.getParameter("ProductName");
		String Price = request.getParameter("Price");
		String Image = request.getParameter("Image");
		String Size = request.getParameter("Size");
		String Brand = request.getParameter("Brand");
		String Stock_quantity = request.getParameter("Stock_quantity");
		String Status = request.getParameter("Status");
		String Description = request.getParameter("Description");
		int role = 0;
		double price = 0.0;
		float size = 0;
		int stock_quantity = 0;
		int userId = 0;
		int product_id = 0;
		int productDetail_id = 0;
		try {
			if (Role != null && !Role.isEmpty())
				role = Integer.parseInt(Role);
			if (Price != null && !Price.isEmpty())
				price = Double.parseDouble(Price);
			if (Size != null && !Size.isEmpty())
				size = Float.parseFloat(Size);
			if (Stock_quantity != null && !Stock_quantity.isEmpty())
				stock_quantity = Integer.parseInt(Stock_quantity);
			if (ProductDetail_id != null && !ProductDetail_id.isEmpty())
				productDetail_id = Integer.parseInt(ProductDetail_id);
		} catch (NumberFormatException e) {
			stock_quantity = 0;
		}
		//AdminDao adDao = new AdminDao();
		AdminBo adbo = new AdminBo();
		// Thêm người dùng admin
		String action = request.getParameter("action");
		if (action.equals("addUser")) {

			try {
				if (!UserName.equals("") && !Password.equals("") && !Name.equals("") && !PhoneNumber.equals("")
						&& !Email.equals("")) {

					if (checkusertontai(UserName)) {
						request.setAttribute("Thong Bao", "Tên đăng nhập đã tồn tại!");
						List<UserBean> Listuser = adbo.getUser1();
						request.setAttribute("DsUser", Listuser);
						request.getRequestDispatcher("Admin.jsp").forward(request, response);
						return;
					} else {
						int result = adbo.AddUserAdmin(UserName, Password, Name, PhoneNumber, Email, role);
						if (result > 0) {
							List<UserBean> Listuser = adbo.getUser1();
							request.setAttribute("DsUser", Listuser);
							response.sendRedirect("AdminController?success=true");
							return;
						} else {
							request.setAttribute("Thong Bao", "Them nguoi dung that bai!");
							request.getRequestDispatcher("Admin.jsp").forward(request, response);
							return;
						}
					}

				} else {
					request.setAttribute("Thong Bao", "Vui lòng nhập đầy đủ thông tin!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("Thong Bao", "Lỗi hệ thống!");
			}
		} else if (action.equals("EditUser")) {
			// Chỉnh sửa người dùng
			try {
				if (UserId != null && !UserId.trim().isEmpty()) {
					userId = Integer.parseInt(UserId);
					int resultEditUser = adbo.UpdateUserAdmin(userId, UserName, Password, ProductName, PhoneNumber, Email, role);
					if (resultEditUser > 0) {
						List<UserBean> Listuser = adbo.getUser1();
						request.setAttribute("DsUser", Listuser);
						List<ProductDetailBean> listProduct = adbo.getProduct1();
						request.setAttribute("Dsproduct", listProduct);
						response.sendRedirect("AdminController?EditUsersuccess=true");
						return;
					} else {
						request.setAttribute("Thong Bao", "Them nguoi dung that bai!");
						request.getRequestDispatcher("Admin.jsp").forward(request, response);
						return;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("Thong Bao", "Lỗi hệ thống chinh sửa!");
			}
		} else if (action.equals("addProduct")) {
			// Thêm sản phẩm Admin
			try {
				if (!ProductName.equals("") && !Price.equals("") && !Image.equals("") && !Size.equals("")
						&& !Brand.equals("") && !Stock_quantity.equals("") && !Status.equals("")
						&& !Description.equals("")) {
					int resultProduct = adbo.AddProductAdmin(ProductName, price, Image, size, Brand, stock_quantity,
							Status, Description);
					if (resultProduct > 0) {
						List<ProductDetailBean> listProduct = adbo.getProduct1();
						request.setAttribute("Dsproduct", listProduct);
						response.sendRedirect("AdminController?productSuccess=true");
						return;
					} else {
						request.setAttribute("Thong Bao san pham", "Them san pham that bai!");
						request.getRequestDispatcher("Admin.jsp").forward(request, response);
						return;
					}
				} else {
					System.out.println("khong co san pham");
					request.getRequestDispatcher("Admin.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("Thong Bao", "Lỗi hệ thống them san pham!");
			}
		} else if (action.equals("EditProduct")) {
			try {
				if (Product_id != null && !Product_id.trim().isEmpty()) {
					product_id = Integer.parseInt(Product_id);
					int resulteditproduct = adbo.UpdateProductAdmin(product_id, ProductName, price, Image,
							productDetail_id, size, Brand, stock_quantity, Status, Description);
					if (resulteditproduct > 0) {
						List<UserBean> Listuser = adbo.getUser1();
						request.setAttribute("DsUser", Listuser);
						List<ProductDetailBean> listProduct = adbo.getProduct1();
						request.setAttribute("Dsproduct", listProduct);
						response.sendRedirect("AdminController?EditProductsuccess=true");
					} else {
						request.setAttribute("Thong Bao", "Chinh sửa sản phẩm thất bại!");
						request.getRequestDispatcher("Admin.jsp").forward(request, response);
						return;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("Thong Bao", "Lỗi hệ thống cập nhật san pham!");
			}
		} else if (action.equals("DeleteUser")) {
			try {
				int userid = Integer.parseInt(request.getParameter("id"));
				int resultUserDelete = adbo.DeleteUserAdmin(userid);
				if (resultUserDelete > 0) {
					List<UserBean> Listuser = adbo.getUser1();
					request.setAttribute("DsUser", Listuser);
					List<ProductDetailBean> listProduct = adbo.getProduct1();
					request.setAttribute("Dsproduct", listProduct);
					response.sendRedirect("AdminController?DeleteUsersuccess=true");
				} else {
					request.setAttribute("Thong Bao", "Xóa người dùng thất bại!");
					request.getRequestDispatcher("Admin.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("Thong Bao", "Lỗi hệ thống xóa người dùng!");
			}
		} else if (action.equals("DeleteProduct")) {
			try {
				int productDetail_ID = Integer.parseInt(request.getParameter("id"));
				System.out.println("ID sản phẩm cần xóa: " + request.getParameter("id"));
				System.out.println("ID cần xóa: " + productDetail_ID);

				int resultProductDelete = adbo.DeleteProductAdmin(productDetail_ID);
				if (resultProductDelete > 0) {
					List<UserBean> Listuser = adbo.getUser1();
					request.setAttribute("DsUser", Listuser);
					List<ProductDetailBean> listProduct = adbo.getProduct1();
					request.setAttribute("Dsproduct", listProduct);
					response.sendRedirect("AdminController?DeleteProductsuccess=true");
				} else {
					request.setAttribute("Thong Bao", "Xóa sản phẩm thất bại!");
					request.getRequestDispatcher("Admin.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("Thong Bao", "Lỗi hệ thống xóa sản phẩm!");
			}
		} else if (action.equals("EditOrderStatus")) {

			int Order_id = Integer.parseInt(request.getParameter("Order_id"));
			String status = request.getParameter("Status");
			try {

				adbo.UpdateOrderAdmin(status, Order_id);
				List<OrderBean> Order = adbo.getOrderAdmin();
				request.setAttribute("dsOrder", Order);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
			rd.forward(request, response);
		} else if (action.equals("ViewOrderDetail")) {

			int Order_id = Integer.parseInt(request.getParameter("Order_id"));

			try {
				
				List<OrderDetailBean> orderDetails = adbo.dsproDetail(Order_id);
				request.setAttribute("orderDetails", orderDetails);
				request.setAttribute("orderId", Order_id);
				RequestDispatcher rd = request.getRequestDispatcher("AdminOrderDetail.jsp");
				rd.forward(request, response);
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
