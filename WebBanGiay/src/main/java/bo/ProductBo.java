package bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProductDetailBean;
import dao.ProductDao;

public class ProductBo {
	ProductDao proDao = new ProductDao();
	List<ProductDetailBean> ds=new ArrayList<>();
	public List<ProductDetailBean>getPro() throws SQLException{
		ds=proDao.GetProduct();
		return ds;
	}
}
