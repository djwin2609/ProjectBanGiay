package bo;
import dao.KetNoiCSDL;
import dao.LoginDao;
import dao.RegisterDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.UserBean;
public class RegisterBo {
	RegisterDao regDao =new RegisterDao();
	LoginDao logDao=new LoginDao();
	List<UserBean>ds=new ArrayList<>();
	public List<UserBean> getds() throws SQLException{
		ds=logDao.getLogin();
		return ds;
	}
	public boolean Add(String UserName, String Password,String Name,String PhoneNumber,String Email, int Role) throws Exception {
//			if(checkusertontai(UserName)) {
//				 System.out.println("Ten dang nhap da ton tai");
//				 return false;
//			}
			regDao.them(UserName, Password, Name,  PhoneNumber, Email,Role );
			ds.add(new UserBean(0,UserName, Password, Name,  PhoneNumber, Email,Role));
			return true;
		//}
		
	}

//	public boolean checkusertontai(String UserName) throws SQLException {
//		KetNoiCSDL kn = new KetNoiCSDL();
//		kn.connectcsdl();
//		Connection conn = kn.cn;
//		String Sql = "select Count(*) from [User] WHERE UserName=?";
//		PreparedStatement cmd = conn.prepareStatement(Sql);
//		cmd.setString(1, UserName);
//		ResultSet rs = cmd.executeQuery();
//		boolean tontai = false;
//		if (rs.next()) {
//			tontai = rs.getInt(1) > 0;
//		}
//		rs.close();
//		cmd.close();
//		conn.close();
//		return tontai;
//	}
}
