package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProductDetailBean;

public class TypeBrandDao {
	public List<ProductDetailBean> getProductByBrand(String brand) throws SQLException {
		List<ProductDetailBean> ds = new ArrayList<>();
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		String sqlBrand = "select* from Product p join ProductDetail pd on p.Product_id=pd.Product_id where Brand=?";
		PreparedStatement psBrand = conn.prepareStatement(sqlBrand);
		psBrand.setString(1, brand);
		ResultSet rs = psBrand.executeQuery();
		while (rs.next()) {
			int Product_id = rs.getInt("Product_id");
			int ProductDetail_id = rs.getInt("ProductDetail_id");
			String ProductName = rs.getString("ProductName");
			double Price = rs.getDouble("Price");
			String Image = rs.getString("Image");
			float Size = rs.getFloat("Size");
			String Brand = rs.getString("Brand");
			int Stock_quantity = rs.getInt("Stock_quantity");
			String Status = rs.getString("Status");
			String Description = rs.getString("Description");
			ds.add(new ProductDetailBean(Product_id, ProductName, Price, Image, ProductDetail_id, Size, Brand,
					Stock_quantity, Status, Description));
		}
		return ds;
	}
}
