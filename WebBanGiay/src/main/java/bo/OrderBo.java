package bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.OrderDetailBean;
import dao.OrderDao;



public class OrderBo {
	OrderDao ordDao=new OrderDao();
	List<OrderDetailBean> ds=new ArrayList<>();
	
	public List<OrderDetailBean>getOrder(int User_id,int Order_id) throws SQLException{
		ds=ordDao.getOrder(User_id, Order_id);
		return ds;
	}
}
