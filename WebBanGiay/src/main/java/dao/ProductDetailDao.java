package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProductDetailBean;

public class ProductDetailDao {
	public List<ProductDetailBean> GetProductDetail() throws SQLException {
		List<ProductDetailBean> ds = new ArrayList<>();
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		String sqlProduct = "SELECT  p.Product_id,p.ProductName,p.Price, p.Image,pd.ProductDetail_id,pd.Size,pd.Brand,pd.Stock_quantity,pd.Status,pd.Description FROM Product p join ProductDetail pd ON p.Product_id = pd.Product_id";
		PreparedStatement cmd = kn.cn.prepareStatement(sqlProduct);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			int Product_id = rs.getInt("Product_id");
			int ProductDetail_id=rs.getInt("Product_id");
			String ProductName = rs.getString("ProductName");
			double Price = rs.getDouble("Price");
			String Image = rs.getString("Image");
			float Size = rs.getFloat("Size");
			String Brand = rs.getString("Brand");
			int Stock_quantity = rs.getInt("Stock_quantity");
			String Status = rs.getString("Status");
			String Description=rs.getString("Description");
			ds.add(new ProductDetailBean(Product_id, ProductName, Price, Image,ProductDetail_id, Size, Brand, Stock_quantity, Status,Description));
		}
		return ds;
	}
}
