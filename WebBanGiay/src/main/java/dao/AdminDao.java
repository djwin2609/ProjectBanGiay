package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bean.ProductDetailBean;
import bean.UserBean;

public class AdminDao {
	public List<UserBean>getUser1() throws SQLException{
		List<UserBean> ds	=new ArrayList<>();
		KetNoiCSDL kn=new KetNoiCSDL();
		kn.connectcsdl();
		String sql= "select* from [User]";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs= cmd.executeQuery();
		while(rs.next()) {
			int MaKhachHang=rs.getInt("User_id");
			String TenDangNhap=rs.getString("UserName");
			String MatKhau=rs.getString("Password");
			String HoTen=rs.getString("Name");
			String SoDienThoai=rs.getString("PhoneNumber");
			String Email=rs.getString("Email");		
			int Role=rs.getInt("Role");
			ds.add(new UserBean(MaKhachHang,TenDangNhap,MatKhau,HoTen,SoDienThoai,Email,Role));
		}
		rs.close();
		kn.cn.close();
		cmd.close();
		return ds;
	}
	public List<ProductDetailBean> GetProduct1() throws SQLException {
		List<ProductDetailBean> ds = new ArrayList<>();
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		String sqlProduct = "SELECT  p.Product_id,p.ProductName,p.Price, p.Image,pd.ProductDetail_id,pd.Size,pd.Brand,pd.Stock_quantity,pd.Status,pd.Description FROM Product p join ProductDetail pd ON p.Product_id = pd.Product_id";
		PreparedStatement cmd = kn.cn.prepareStatement(sqlProduct);
		ResultSet rs = cmd.executeQuery();
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
			String Description=rs.getString("Description");
			ds.add(new ProductDetailBean(Product_id, ProductName, Price, Image,ProductDetail_id, Size, Brand, Stock_quantity, Status,Description));
		}
		return ds;
	}
	public int AddUserAdmin(String UserName, String Password, String Name, String PhoneNumber, String Email, int Role)
			throws SQLException {
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		String sqlClient = "INSERT INTO [User] (UserName,Password,Name,PhoneNumber,Email,Role) VALUES (?,?,?,?,?,?)";
		PreparedStatement cmdClient = kn.cn.prepareStatement(sqlClient);
		cmdClient.setString(1, UserName);
		cmdClient.setString(2, Password);
		cmdClient.setString(3, Name);
		cmdClient.setString(4, PhoneNumber);
		cmdClient.setString(5, Email);
		cmdClient.setInt(6, Role);
		int resullt = cmdClient.executeUpdate();
		if (resullt > 0) {
			System.out.println("Thêm thành công");
		} else {
			System.out.println("Không thêm được dữ liệu");
		}
		cmdClient.close();
		kn.cn.close();

		return resullt;
	}
	public int AddProductAdmin(String ProductName, double Price, String Image, float Size, String Brand,
			int Stock_quantity, String Status, String Descriptions) throws SQLException {
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;

		int result = 0;
		int productId = 0;

		try {
			System.out.println(" Bắt đầu thêm Product");

			String sqlProduct = "INSERT INTO [Product] (ProductName, Price, Image) VALUES (?, ?, ?)";
			PreparedStatement psProduct = conn.prepareStatement(sqlProduct, Statement.RETURN_GENERATED_KEYS);
			psProduct.setString(1, ProductName);
			psProduct.setDouble(2, Price);
			psProduct.setString(3, Image);

			int rowsProduct = psProduct.executeUpdate();
			System.out.println("Số dòng thêm vào Product: " + rowsProduct);

			if (rowsProduct > 0) {
				ResultSet rs = psProduct.getGeneratedKeys();
				if (rs.next()) {
					productId = rs.getInt(1);
					System.out.println("Product ID được tạo: " + productId);
				}
				rs.close();
			}
			psProduct.close();

			if (productId > 0) {
				System.out.println(" Bắt đầu thêm ProductDetail");
				String sqlDetail = "INSERT INTO ProductDetail (Product_id, Size, Brand, Stock_quantity, Status, Description) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement psDetail = conn.prepareStatement(sqlDetail);
				psDetail.setInt(1, productId);
				psDetail.setFloat(2, Size);
				psDetail.setString(3, Brand);
				psDetail.setInt(4, Stock_quantity);
				psDetail.setString(5, Status);
				psDetail.setString(6, Descriptions);
				result = psDetail.executeUpdate();
				// test
				System.out.println("Số dòng thêm vào ProductDetail: " + result);
				psDetail.close();
			} else {
				System.out.println("❌ Không lấy được Product ID sau khi thêm");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.close();
		}

		return result;
	}
	public int UpdateUserAdmin(int User_id,String UserName, String Password, String Name, String PhoneNumber, String Email, int Role) throws SQLException {
		KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn = kn.cn;
		String sqlClient = "Update [User] SET UserName=?,Password=?,Name=?,PhoneNumber=?,Email=?,Role=? where User_id=?";
		PreparedStatement cmdClient = kn.cn.prepareStatement(sqlClient);
		
		cmdClient.setString(1, UserName);
		cmdClient.setString(2, Password);
		cmdClient.setString(3, Name);
		cmdClient.setString(4, PhoneNumber);
		cmdClient.setString(5, Email);
		cmdClient.setInt(6, Role);
		cmdClient.setInt(7, User_id);
		int resullt = cmdClient.executeUpdate();
		if (resullt > 0) {
			System.out.println("Cập nhật thành công");
		} else {
			System.out.println("Không cập nhật được dữ liệu");
		}
		cmdClient.close();
		kn.cn.close();
		return resullt;
	}
	public int UpdateProductAdmin(int Product_id,String ProductName,double Pirce,String Image,int ProductDetail_id, float Size,String Brand, int Stock_quantity,String Status, String Description) throws SQLException {
		KetNoiCSDL kn= new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn=kn.cn;
		int ResultProducts=0;
		int ResultProductDetail=0;
		//cap nhat cho bang product
		String SqlProduct="Update Product SET ProductName=?,Price=?,Image=? where Product_id=?";
		PreparedStatement cmdProduct= kn.cn.prepareStatement(SqlProduct);
		cmdProduct.setString(1, ProductName);
		cmdProduct.setDouble(2, Pirce);
		cmdProduct.setString(3, Image);
		cmdProduct.setInt(4, Product_id);
		ResultProducts+=cmdProduct.executeUpdate();
		//cap nhat cho bang productdetail
		String SqlProductDetail="Update ProductDetail SET Size = ?, Brand = ?, Stock_quantity = ?, Status = ?, Description = ? where ProductDetail_ID=?";
		PreparedStatement cmdProductDetail= kn.cn.prepareStatement(SqlProductDetail);
		cmdProductDetail.setFloat(1, Size);
		cmdProductDetail.setString(2, Brand);
		cmdProductDetail.setInt(3, Stock_quantity);
		cmdProductDetail.setString(4, Status);
		cmdProductDetail.setString(5, Description);
		cmdProductDetail.setInt(6, ProductDetail_id);
		ResultProductDetail+=cmdProductDetail.executeUpdate();
		cmdProduct.close();
		cmdProductDetail.close();
		conn.close();
		return ResultProducts+ResultProductDetail;
	}
	public int DeleteUserAdmin(int User_id) throws SQLException {
		KetNoiCSDL kn= new KetNoiCSDL();
		kn.connectcsdl();
		Connection conn=kn.cn;
		int result=0;
		String SqlUser="Delete [User] where User_id=? ";
		PreparedStatement cmdUser= kn.cn.prepareStatement(SqlUser);
		cmdUser.setInt(1, User_id);
		result=cmdUser.executeUpdate();
		cmdUser.close();
		conn.close();
		return result;
	}
	public int DeleteProductAdmin(int productDetailId) throws SQLException {
	    KetNoiCSDL kn = new KetNoiCSDL();
	    kn.connectcsdl();
	    Connection conn = kn.cn;
	    int deletedDetailCount = 0;
	    int deletedProductCount = 0;

	    try {
	        conn.setAutoCommit(false);

	        // 1. Lấy product_id từ ProductDetail
	        String getProductIdSQL = "SELECT Product_id FROM ProductDetail WHERE ProductDetail_id = ?";
	        PreparedStatement getProductIdStmt = conn.prepareStatement(getProductIdSQL);
	        getProductIdStmt.setInt(1, productDetailId);
	        ResultSet rs = getProductIdStmt.executeQuery();
	        
	        int productId = -1;
	        if (rs.next()) {
	            productId = rs.getInt("Product_id");
	        }
	        rs.close();
	        getProductIdStmt.close();

	        if (productId == -1) {
	            conn.rollback();
	            return 0; // Không tìm thấy sản phẩm
	        }

	        // 2. Xoá ProductDetail
	        String deleteDetailSQL = "DELETE FROM ProductDetail WHERE ProductDetail_id = ?";
	        PreparedStatement deleteDetailStmt = conn.prepareStatement(deleteDetailSQL);
	        deleteDetailStmt.setInt(1, productDetailId);
	        deletedDetailCount = deleteDetailStmt.executeUpdate();
	        deleteDetailStmt.close();

	        // 3. Kiểm tra còn size nào không
	        String checkRemainingSQL = "SELECT COUNT(*) AS count FROM ProductDetail WHERE Product_id = ?";
	        PreparedStatement checkStmt = conn.prepareStatement(checkRemainingSQL);
	        checkStmt.setInt(1, productId);
	        ResultSet countRs = checkStmt.executeQuery();
	        int count = 0;
	        if (countRs.next()) {
	            count = countRs.getInt("count");
	        }
	        countRs.close();
	        checkStmt.close();

	        // 4. Nếu không còn size nào → xoá luôn Product
	        if (count == 0) {
	            String deleteProductSQL = "DELETE FROM Product WHERE Product_id = ?";
	            PreparedStatement deleteProductStmt = conn.prepareStatement(deleteProductSQL);
	            deleteProductStmt.setInt(1, productId);
	            deletedProductCount += deleteProductStmt.executeUpdate(); // cộng dồn kết quả
	            deleteProductStmt.close();
	        }
	        conn.commit();
	    } catch (SQLException e) {
	        if (conn != null) conn.rollback();
	        throw e;
	    } finally {
	        if (conn != null) conn.close();
	    }
	    System.out.println("Số ProductDetail bị xóa: " + deletedDetailCount);
	    System.out.println("Số Product bị xóa (nếu không còn size): " + deletedProductCount);


	    return deletedDetailCount + deletedProductCount;
	}
	


}
