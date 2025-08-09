package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class RegisterDao {
	    public int them(String UserName, String Password,String Name,String PhoneNumber,String Email, int Role) throws Exception {
	        // B1: Kết nối vào CSDL
	        KetNoiCSDL kn = new KetNoiCSDL();
	        kn.connectcsdl();
	        Connection conn = kn.cn;
	        // B3: Chèn vào Client (lưu thông tin khách hàng và liên kết với UserName)
	        String sqlClient = "INSERT INTO [User] (UserName,Password,Name,PhoneNumber,Email,Role) VALUES (?,?,?,?,?,?)";
	        PreparedStatement cmdClient = kn.cn.prepareStatement(sqlClient);
	        cmdClient.setString(1, UserName); // Liên kết với UserAccount
	        cmdClient.setString(2, Password); 
	        cmdClient.setString(3, Name);
	        cmdClient.setString(4, PhoneNumber);
	        cmdClient.setString(5, Email);
	        cmdClient.setInt(6, Role);
	        int resullt=cmdClient.executeUpdate();
	        if (resullt > 0) {
	            System.out.println("Đăng ký thành công");
	        } else {
	            System.out.println("Không thêm được dữ liệu");
	        }
	        cmdClient.close();
	        kn.cn.close();

	        return resullt;
	  }
}
