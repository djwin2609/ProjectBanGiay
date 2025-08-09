package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bo.RegisterBo;
import dao.KetNoiCSDL;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
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
		RegisterBo regBo = new RegisterBo();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		try {
			if (!username.equals("") && !password.equals("") && !fullname.equals("") && !phone.equals("")
					&& !email.equals("")) {
				if (checkusertontai(username)) {

					request.setAttribute("DangKiLoi", "Tên đăng nhập đã tồn tại!");
				} else {
					boolean success = regBo.Add(username, password, fullname, phone, email, 1);
					if (success) {
						request.setAttribute("ThongBao", "Đăng ký thành công!");
						response.sendRedirect("Login.jsp");
						return;
					} else {
						request.setAttribute("DangKiLoi", "Đăng ký thất bại!");
					}
				}
			} else {
				request.setAttribute("DangKiLoi", "Vui lòng nhập đầy đủ thông tin!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("kt", "Đăng ký lỗi: " + e.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
		rd.forward(request, response);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
