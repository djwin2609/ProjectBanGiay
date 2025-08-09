package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProductDetailBean;
import bo.ProductBo;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductBo proBo = new ProductBo();
		try {
			for (ProductDetailBean pd : proBo.getPro()) {
				System.out.println("ID: " + pd.getProductID());
				System.out.println("Tên sản phẩm: " + pd.getProductName());
				System.out.println("Giá: " + pd.getPrice());
				System.out.println("Hình ảnh: " + pd.getImage());
				System.out.println("Size: " + pd.getSize());
				System.out.println("Thương hiệu: " + pd.getBrand());
				System.out.println("Số lượng tồn: " + pd.getStock_quantity());
				System.out.println("Trạng thái: " + pd.getStatus());
				System.out.println("MoTa: "+pd.getDescription());
				System.out.println("------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			List<ProductDetailBean> ds = proBo.getPro();
			request.setAttribute("dssanpham", ds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("HomePage.jsp").forward(request, response);

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
