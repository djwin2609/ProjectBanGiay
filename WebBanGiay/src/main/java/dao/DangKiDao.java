package dao;

import java.sql.PreparedStatement;

public class DangKiDao {
	 public int them(String HoTen,String TenDangNhap,String MatKhau) throws Exception {
	        // B1: Kết nối vào CSDL
	        KetNoiCSDL kn = new KetNoiCSDL();
	        kn.connectcsdl();

	        // B2: Chèn vào UserAccount 
	        String sqlUserAccount = "INSERT INTO UserAccount (HoTen,TenDangNhap,MatKhau) VALUES (?, ?, ?)";
	        PreparedStatement cmdUserAccount = kn.cn.prepareStatement(sqlUserAccount);
	        cmdUserAccount.setString(1, HoTen); 
	        cmdUserAccount.setString(2, TenDangNhap); 
	        cmdUserAccount.setString(3, MatKhau);
	        int kq=cmdUserAccount.executeUpdate();
	        if (kq > 0) {
	            System.out.println("Đăng ký thành công");
	        } else {
	            System.out.println("Không thêm được dữ liệu");
	        }

	        cmdUserAccount.close();
			kn.cn.close();
			return kq;
	 }

}
