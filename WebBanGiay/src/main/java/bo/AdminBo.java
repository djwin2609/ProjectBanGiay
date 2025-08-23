package bo;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.OrderBean;
import bean.OrderDetailBean;
import bean.ProductDetailBean;
import bean.UserBean;
import dao.AdminDao;


public class AdminBo {
	AdminDao adDao = new AdminDao();
	List<UserBean> ds = new ArrayList<>();

	public List<UserBean> getUser1() throws SQLException {
		ds = adDao.getUser1();
		return ds;
	}


	List<ProductDetailBean> dsProduct = new ArrayList<>();

	public List<ProductDetailBean> getProduct1() throws SQLException {
		dsProduct = adDao.GetProduct1();
		return dsProduct;
	}
	
	List<OrderBean>dsOrder= new ArrayList<>();
	public List<OrderBean> getOrderAdmin() throws SQLException {
		dsOrder = adDao.getOrderAdmin();
		return dsOrder;
	}
	
	List<OrderDetailBean> list = new ArrayList<>();
	public List<OrderDetailBean>dsproDetail(int orderId) throws SQLException{
		list=adDao.getOrderDetails(orderId);
		return list;
	}
	//them nguoi dung
	
	public int AddUserAdmin(String UserName, String Password, String Name, String PhoneNumber, String Email, int Role)
			throws SQLException {
		
		return adDao.AddUserAdmin(UserName, Password, Name, PhoneNumber, Email, Role);
		
	}
	//chinh sua tai khoan nguoi dung
	public int UpdateUserAdmin(int User_id, String UserName, String Password, String Name, String PhoneNumber,
			String Email, int Role) throws SQLException {
		return adDao.UpdateUserAdmin(User_id, UserName, Password, Name, PhoneNumber, Email, Role);
	}
	//xoa tai khoan nguoi dung
	public int DeleteUserAdmin(int User_id) throws SQLException {
		return adDao.DeleteUserAdmin(User_id);
	}
	//them san pham 
	public int AddProductAdmin(String ProductName, double Price, String Image, float Size, String Brand,
	        int Stock_quantity, String Status, String Descriptions) throws SQLException {
		return adDao.AddProductAdmin(ProductName, Price, Image, Size, Brand, Stock_quantity, Status, Descriptions);
	}
	//chinh sua san pham
	public int UpdateProductAdmin(int Product_id, String ProductName, double Pirce, String Image, int ProductDetail_id,
			float Size, String Brand, int Stock_quantity, String Status, String Description) throws SQLException {
		return adDao.UpdateProductAdmin(Product_id, ProductName, Pirce, Image, ProductDetail_id, Size, Brand, Stock_quantity, Status, Description);
	}
	//xoa sản phẩm
	public int DeleteProductAdmin(int productDetailId) throws SQLException {
		return adDao.DeleteProductAdmin(productDetailId);
	}
	//Cap nhat don hang
	public int UpdateOrderAdmin(String status, int orderId) throws SQLException {
		return adDao.UpdateOrderAdmin(status, orderId);
	}
	
	
		
		
}
