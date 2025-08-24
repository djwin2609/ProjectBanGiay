package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.OrderBean;
import bean.OrderDetailBean;

public class OrderDao {
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
			psOrder.setString(3, phone);
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

	public List<OrderDetailBean> getOrder(int Order_id, int User_id) throws SQLException {
		List<OrderDetailBean> orderdetails = new ArrayList<>();
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		String SqlOrder = "SELECT \r\n" + "    ord.Order_id,\r\n" + "   ord.User_id,\r\n"
				+ "   ord.Name AS ReceiverName,\r\n" + "    ord.PhoneNumber,\r\n" + "	 prod.ProductName,\r\n"
				+ "    ord.OrderDate,\r\n" + "    ord.totalAmount,\r\n" + "    ord.shippingAddress,\r\n"
				+ "    ord.status,\r\n" + "    ord.paymentMethod,\r\n" + "    ordetail.Product_id,\r\n" + "   \r\n"
				+ "    ordetail.Quantity\r\n" + " \r\n" + "FROM \r\n" + "    [Order] ord\r\n" + "JOIN \r\n"
				+ "    OrderDetail ordetail ON ord.Order_id = ordetail.Order_id\r\n" + "JOIN \r\n"
				+ "    Product prod ON ordetail.Product_id = prod.Product_id\r\n" + "WHERE \r\n"
				+ "    ord.Order_id = ? and User_id=?\r\n";
		PreparedStatement prcmd = conn.prepareStatement(SqlOrder);
		prcmd.setInt(1, Order_id);
		prcmd.setInt(2, User_id);
		ResultSet rs = prcmd.executeQuery();
		while (rs.next()) {
			int order_id = rs.getInt("Order_id");
			int user_id = rs.getInt("User_id");
			String Name = rs.getString("Name");
			String Phone = rs.getString("PhoneNumber");
			Date orderDate = rs.getDate("OrderDate");
			double totalAmount = rs.getDouble("totalAmount");

			String paymentMethod = rs.getString("paymentMethod");
			String shippingAddress = rs.getString("shippingAddress");
			String status = rs.getString("status");
			int orderdetail_id = rs.getInt("OrderDetail_id");
			int product_id = rs.getInt("Product_id");
			String ProductName = rs.getString("ProductName");
			int quantity = rs.getInt("Quantity");
			double Price = rs.getDouble("Price");

			orderdetails.add(new OrderDetailBean(order_id, user_id, Name, Phone, orderDate, totalAmount, paymentMethod,
					shippingAddress, status, orderdetail_id, product_id, ProductName, quantity, Price));
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

	public List<OrderBean> getOrderByUserID(int User_id) throws SQLException {
		List<OrderBean> ds = new ArrayList<>();
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		String sql = """
				    SELECT
				        ord.Order_id,
				        ord.User_id,
				        ord.Name,
				        ord.PhoneNumber,
				        ord.OrderDate,
				        ord.totalAmount,
				        ord.paymentMethod,
				        ord.shippingAddress,
				        ord.status

				    FROM [Order] ord where User_id=?

				""";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, User_id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int order_id = rs.getInt("Order_id");
			String Name = rs.getString("Name");
			String Phone = rs.getString("PhoneNumber");
			Date orderDate = rs.getDate("OrderDate");
			double totalAmount = rs.getDouble("totalAmount");
			String paymentMethod = rs.getString("paymentMethod");
			String shippingAddress = rs.getString("shippingAddress");
			String status = rs.getString("status");

			ds.add(new OrderBean(order_id, User_id, Name, Phone, orderDate, totalAmount, paymentMethod, shippingAddress,
					status));
		}
		return ds;
	}

	public List<OrderDetailBean> getOrderdetail(int orderId) throws SQLException {
		List<OrderDetailBean> list = new ArrayList<>();
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		String sql = """
				    SELECT
				        ord.Order_id,
				        ord.User_id,
				        ord.Name,
				        ord.PhoneNumber,
				        ord.OrderDate,
				        ord.totalAmount,
				        ord.paymentMethod,
				        ord.shippingAddress,
				        ord.status,
				        ordetail.OrderDetail_id,
				        ordetail.Product_id,
				        ordetail.ProductName,
				        ordetail.Quantity,
				        ordetail.Price
				    FROM [Order] ord
				    JOIN OrderDetail  ordetail ON ord.Order_id = ordetail.Order_id where ord.Order_id=?

				""";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, orderId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int order_id = rs.getInt("Order_id");
			int User_id = rs.getInt("User_id");
			String Name = rs.getString("Name");
			String Phone = rs.getString("PhoneNumber");
			Date orderDate = rs.getDate("OrderDate");
			double totalAmount = rs.getDouble("totalAmount");
			String paymentMethod = rs.getString("paymentMethod");
			String shippingAddress = rs.getString("shippingAddress");
			String status = rs.getString("status");
			int orderdetail_id = rs.getInt("OrderDetail_id");
			int product_id = rs.getInt("Product_id");
			String ProductName = rs.getString("ProductName");
			int quantity = rs.getInt("Quantity");
			double Price = rs.getDouble("Price");

			list.add(new OrderDetailBean(order_id, User_id, Name, Phone, orderDate, totalAmount, paymentMethod,
					shippingAddress, status, orderdetail_id, product_id, ProductName, quantity, Price));
		}
		return list;
	}

	public int UpdateOrder(String status, int orderId) throws SQLException {
	    KetNoiCSDL kn = new KetNoiCSDL();
	    kn.connectcsdl();
	    Connection conn = kn.cn;

	    String sqlOrder = "UPDATE [Order] SET status=? WHERE Order_id=?";
	    try (PreparedStatement stmt = conn.prepareStatement(sqlOrder)) {
	        stmt.setString(1, status);
	        stmt.setInt(2, orderId);

	        return stmt.executeUpdate();
	    } finally {
	        if (conn != null) {
	            conn.close();
	        }
	    }
	}

}
