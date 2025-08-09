package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.DangKiBoo;

/**
 * Servlet implementation class DangKiControoler
 */
@WebServlet("/DangKiControoler")
public class DangKiControoler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangKiControoler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			DangKiBoo dkbo=new DangKiBoo();
			
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String fullname=request.getParameter("fullname");
			try {
				if(!fullname.equals("")&& !username.equals("")&&!password.equals("")) {
					dkbo.Add(fullname, username, password);
					response.sendRedirect("Login.jsp");
					return;
				}else {
					 request.setAttribute("DangKiLoi", "Vui lòng nhập đầy đủ thông tin!");
				}
			}catch(Exception e) {
				request.setAttribute("kt", "Dang Ki Loi");
			}
			RequestDispatcher rd = request.getRequestDispatcher("dangki.jsp");
		    rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
