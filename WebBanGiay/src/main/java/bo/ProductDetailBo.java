package bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProductDetailBean;

import dao.ProductDetailDao;

public class ProductDetailBo {
	ProductDetailDao proDao = new ProductDetailDao();
	List<ProductDetailBean> ds = new ArrayList<>();

	public List<ProductDetailBean> getProDetail() throws SQLException {
		ds = proDao.GetProductDetail();
		return ds;
	}
}
