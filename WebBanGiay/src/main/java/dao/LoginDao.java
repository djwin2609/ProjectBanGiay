package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import bean.UserBean;



public class LoginDao {
	public List<UserBean>getLogin() throws SQLException{
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
	public List<UserBean>getUser() throws SQLException{
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
	
	
}
