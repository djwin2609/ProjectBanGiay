package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import bean.OrderDetailBean;

public class VerifyOrderDao {
	public boolean insertOrder(int userId, String name, String phone, Date orderDate, double totalAmount,
	        String paymentMethod, String shippingAddress, String status, List<OrderDetailBean> orderDetails)
	        throws SQLException {

	    KetNoiCSDL kn = new KetNoiCSDL();
	    kn.connectcsdl();
	    Connection conn = kn.cn;

	    try {
	        conn.setAutoCommit(false);

	        // 1. Thêm đơn hàng vào bảng Order
	        String sqlOrder = "INSERT INTO [Order] (User_id,Name,PhoneNumber, OrderDate, totalAmount, paymentMethod, shippingAddress, status) VALUES (?, ?, ?, ?, ?, ?,?,?)";
	        PreparedStatement psOrder = conn.prepareStatement(sqlOrder, PreparedStatement.RETURN_GENERATED_KEYS);
	        psOrder.setInt(1, userId);
	        psOrder.setString(2, name);
	        psOrder.setString(3,phone);
	        psOrder.setDate(4, orderDate);
	        psOrder.setDouble(5, totalAmount);
	        psOrder.setString(6, paymentMethod);
	        psOrder.setString(7, shippingAddress);
	        psOrder.setString(8, status);

	        int affectedRows = psOrder.executeUpdate();
	        if (affectedRows == 0) {
	            conn.rollback();
	            return false;
	        }

	        // 2. Lấy Order_id vừa tạo
	        int orderId = 0;
	        ResultSet rs = psOrder.getGeneratedKeys();
	        if (rs.next()) {
	            orderId = rs.getInt(1);
	        }
	        rs.close();
	        psOrder.close();

	        // 3. Thêm các sản phẩm vào OrderDetail
	        String sqlDetail = "INSERT INTO OrderDetail (Order_id, Product_id,ProductName, Quantity,Price) VALUES (?, ?, ?,?,?)";
	        PreparedStatement psDetail = conn.prepareStatement(sqlDetail);
	        for (OrderDetailBean od : orderDetails) {
	            psDetail.setInt(1, orderId);
	            psDetail.setInt(2, od.getProduct_id());
	            psDetail.setString(3, od.getProductName());
	            psDetail.setInt(4, od.getQuantity());
	            psDetail.setDouble(5, od.getPrice());
	            psDetail.addBatch();
	        }
	        psDetail.executeBatch();
	        psDetail.close();

	        conn.commit();
	        conn.close();
	        return true;

	    } catch (Exception e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    }
	}


}
