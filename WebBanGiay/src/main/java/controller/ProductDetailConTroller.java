package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProductDetailBean;
import bo.ProductDetailBo;

/**
 * Servlet implementation class ProductDetailConTroller
 */
@WebServlet("/ProductDetailConTroller")
public class ProductDetailConTroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailConTroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDetailBo proDetail = new ProductDetailBo();
        String idParam = request.getParameter("id");
        try {
			for (ProductDetailBean pd : proDetail.getProDetail()) {
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
        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                System.out.println("ID nhận được: " + id);
                //lay tat ca chi tiet san pham
                List<ProductDetailBean> allDetails = proDetail.getProDetail();
                //luu ban ghi cua sản phẩm chính
                ProductDetailBean mainProduct = null;
                //luu tat cả san pham co id trung nhau
                List<ProductDetailBean> sizeOptions = new ArrayList<>();

                for (ProductDetailBean p : allDetails) {
                    if (p.getProductID() == id) {
                        if (mainProduct == null)
                        	mainProduct = p;
                        sizeOptions.add(p);
                    }
                }
                if (mainProduct == null) {
                    System.out.println("Không tìm thấy sản phẩm với ID: " + id);
                }
                request.setAttribute("productdetail", mainProduct);
                request.setAttribute("sizes", sizeOptions);
               
            } catch (NumberFormatException e) {
                System.out.println("Lỗi định dạng ID: " + idParam);
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Lỗi SQL khi lấy chi tiết sản phẩm");
                e.printStackTrace();
            }
        } else {
            System.out.println("Không có tham số 'id' được truyền vào");
        }

        request.getRequestDispatcher("ProductDetail.jsp").forward(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
