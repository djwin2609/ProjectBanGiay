package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CartBean;

public class CartDao {
	public List<CartBean> getCartByUserId(int user_id) throws Exception {
		List<CartBean> cart = new ArrayList<>();
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		String sql = " Select cb.Product_id,p.ProductName,p.Price,p.Image ,cb.Quantity\r\n"
				+ " from Cart cb join Product p \r\n" + " on cb.Product_id=p.Product_id where User_id=?";
		PreparedStatement cmdCart = conn.prepareStatement(sql);
		cmdCart.setInt(1, user_id);
		ResultSet rs = cmdCart.executeQuery();
		while (rs.next()) {
			CartBean cb = new CartBean();
			cb.setProductID((rs.getInt("Product_id")));
			cb.setProductName(rs.getString("ProductName"));
			cb.setPrice((rs.getDouble("Price")));
			cb.setQuantity_buy((rs.getInt("Quantity")));
			cb.setImage((rs.getString("Image")));
			cart.add(cb);
		}
		rs.close();
		cmdCart.close();
		conn.close();
		return cart;
	}

	public void AddtoCart(int User_id, int Product_id, int quantity) throws SQLException {
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		String priceSql = "SELECT Price FROM Product WHERE Product_id = ?";
		PreparedStatement priceStmt = conn.prepareStatement(priceSql);
		priceStmt.setInt(1, Product_id);
		ResultSet rsPrice = priceStmt.executeQuery();

		double price = 0;
		if (rsPrice.next()) {
			price = rsPrice.getDouble("Price");
		} else {
			throw new SQLException("Không tìm thấy sản phẩm với ID: " + Product_id);
		}
		rsPrice.close();
		priceStmt.close();

		double totalAmount = price * quantity;
		String checksql = "select Quantity from Cart where User_id =? and Product_id=?";
		PreparedStatement checkStmt = conn.prepareStatement(checksql);
		checkStmt.setInt(1, User_id);
		checkStmt.setInt(2, Product_id);
		ResultSet rs = checkStmt.executeQuery();
		if (rs.next()) {
			  int oldQuantity = rs.getInt("Quantity");
		        int newQuantity = oldQuantity + quantity;
		        double TotalAmount = newQuantity * price;
		        String sqlUdate = "UPDATE Cart  Set Quantity=?, Total_Amount=? where User_id=? and Product_id=?";
		        PreparedStatement updateStmt = conn.prepareStatement(sqlUdate);
		        updateStmt.setInt(1, newQuantity);           // Quantity cập nhật đúng
		        updateStmt.setDouble(2, TotalAmount);        // Total_Amount cập nhật đúng
		        updateStmt.setInt(3, User_id);
		        updateStmt.setInt(4, Product_id);
		        updateStmt.executeUpdate();
		        updateStmt.close();
		} else {
			String sqlInsert = "INSERT INTO Cart(User_id, Product_id, Quantity, Price, Total_Amount) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
	        insertStmt.setInt(1, User_id);
	        insertStmt.setInt(2, Product_id);
	        insertStmt.setInt(3, quantity);
	        insertStmt.setDouble(4, price);
	        insertStmt.setDouble(5, totalAmount);
	        insertStmt.executeUpdate();
	        insertStmt.close();

		}
		rs.close();
		checkStmt.close();
		conn.close();
	}

	public void RemoveFromCart(int User_id, int Product_id) throws SQLException {
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		String sqlDelete = "Delete from Cart where User_id=? and Product_id =?";
		PreparedStatement cmd = conn.prepareStatement(sqlDelete);
		cmd.setInt(1, User_id);
		cmd.setInt(2, Product_id);
		cmd.executeUpdate();
		cmd.close();
		conn.close();
	}

	public double getProductPrice(int productId) throws SQLException {

		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		double price = 0;
		String sql = "SELECT Price FROM Product WHERE Product_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, productId);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			price = rs.getDouble("Price");
		}
		rs.close();
		stmt.close();
		conn.close();
		return price;
	}

}
