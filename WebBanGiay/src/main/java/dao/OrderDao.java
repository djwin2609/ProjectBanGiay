package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.OrderDetailBean;


public class OrderDao {
	public List<OrderDetailBean> getOrder(int Order_id, int User_id) throws SQLException {
		List<OrderDetailBean> orderdetails = new ArrayList<>();
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		String SqlOrder = "SELECT \r\n" + "    ord.Order_id,\r\n" + "   ord.User_id,\r\n"+"   ord.Name AS ReceiverName,\r\n"
				+ "    ord.PhoneNumber,\r\n" + "	 prod.ProductName,\r\n" + "    ord.OrderDate,\r\n"
				+ "    ord.totalAmount,\r\n" + "    ord.shippingAddress,\r\n" + "    ord.status,\r\n"
				+ "    ord.paymentMethod,\r\n" + "    ordetail.Product_id,\r\n" + "   \r\n"
				+ "    ordetail.Quantity\r\n" + " \r\n" + "FROM \r\n" + "    [Order] ord\r\n" + "JOIN \r\n"
				+ "    OrderDetail ordetail ON ord.Order_id = ordetail.Order_id\r\n" + "JOIN \r\n"
				+ "    Product prod ON ordetail.Product_id = prod.Product_id\r\n" + "WHERE \r\n"
				+ "    ord.Order_id = ? and User_id=?\r\n";
		PreparedStatement prcmd=conn.prepareStatement(SqlOrder);
		  prcmd.setInt(1, Order_id);
		  prcmd.setInt(2, User_id);
		ResultSet rs=prcmd.executeQuery();
		while(rs.next()) {
			int order_id=rs.getInt("Order_id");
			int user_id=rs.getInt("User_id");
			int product_id=rs.getInt("Product_id");
			int orderdetail_id=rs.getInt("OrderDetail_id");
			int quantity=rs.getInt("Quantity");
			String Name=rs.getString("Name");
			String Phone=rs.getString("PhoneNumber");
			String shippingAddress=rs.getString("shippingAddress");
			Date orderDate =rs.getDate("OrderDate");
			String paymentMethod=rs.getString("paymentMethod");
			String status =rs.getString("status");	
			String ProductName=rs.getString("ProductName");
			double Price=rs.getDouble("Price");
			double totalAmount=rs.getDouble("totalAmount");	
			orderdetails.add(new OrderDetailBean( order_id,user_id,product_id,orderdetail_id,quantity,Name,Phone,shippingAddress,orderDate,paymentMethod,status,ProductName,Price,totalAmount));
		}
		return orderdetails;
	}
	public int getLastOrderId(int userId) throws Exception {
	    
	    KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		int orderId = -1;
	    String sql = "SELECT MAX(order_id) FROM [Order] WHERE user_id = ?";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setInt(1, userId);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
	        orderId = rs.getInt(1);
	    }
	    rs.close();
	    ps.close();
	    conn.close();
	    return orderId;
	}

}
