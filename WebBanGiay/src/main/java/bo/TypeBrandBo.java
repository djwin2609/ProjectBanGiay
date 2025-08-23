package bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProductDetailBean;
import dao.TypeBrandDao;

public class TypeBrandBo {
	List<ProductDetailBean> ds = new ArrayList<>();
	TypeBrandDao brDao=new TypeBrandDao();
	public List<ProductDetailBean>brand(String brand) throws SQLException{
		ds=brDao.getProductByBrand(brand);
		return ds;
	}
}
