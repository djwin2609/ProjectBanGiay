package bo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.OrderBean;
import bean.OrderDetailBean;
import dao.OrderDao;



public class OrderBo {
	OrderDao ordDao=new OrderDao();
	List<OrderDetailBean> ds=new ArrayList<>();
	
	public List<OrderDetailBean>getOrder(int User_id,int Order_id) throws SQLException{
		ds=ordDao.getOrder(User_id, Order_id);
		return ds;
	
	}
	public int getLastOrderId(int userId) throws Exception {
		return ordDao.getLastOrderId(userId);
	}
	
	List<OrderBean> list=new ArrayList<>();
	public List<OrderBean>getOrder1(int User_id) throws SQLException{
		list=ordDao.getOrderByUserID(User_id);
		return list;
	}
	List<OrderDetailBean> list1=new ArrayList<>();
	public List<OrderDetailBean>getOrderĐetails(int Order_id) throws SQLException{
		list1=ordDao.getOrderdetail(Order_id);
		return list1;
	}
	//them don hang vao db
	public boolean insertOrder(int userId, String name, String phone, Date orderDate, double totalAmount,
	        String paymentMethod, String shippingAddress, String status, List<OrderDetailBean> orderDetails)
	        throws SQLException {
		return ordDao.insertOrder(userId, name, phone, orderDate, totalAmount, paymentMethod, shippingAddress, status, orderDetails);
	}
	
}
