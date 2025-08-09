package bo;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProductDetailBean;
import bean.UserBean;
import dao.AdminDao;
import dao.KetNoiCSDL;

public class AdminBo {
	AdminDao adDao = new AdminDao();
	List<UserBean> ds = new ArrayList<>();

	public List<UserBean> getUser1() throws SQLException {
		ds = adDao.getUser1();
		return ds;
	}

	AdminDao AdDao = new AdminDao();
	List<ProductDetailBean> dsProduct = new ArrayList<>();

	public List<ProductDetailBean> getProduct1() throws SQLException {
		dsProduct = AdDao.GetProduct1();
		return dsProduct;
	}

	

	
		
		
}
