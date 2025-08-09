package bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.UserBean;
import dao.LoginDao;

public class LoginBo {
	LoginDao logDao=new LoginDao();
	List<UserBean>ds=new ArrayList<>();
	public List<UserBean>getLogin() throws SQLException{
		ds=logDao.getLogin();
		return ds;
	}
	//public boolean Chec
}
