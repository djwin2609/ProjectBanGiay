package bo;

import java.sql.SQLException;
import java.util.List;
import dao.CartDao;
import bean.CartBean;

public class CartBo {
    CartDao cartDao = new CartDao();

    public List<CartBean> getCartByUserId(int user_id) throws Exception {
        return cartDao.getCartByUserId(user_id);
    }

    public void addToCart(int userId, int productId, int quantity) throws Exception {
        cartDao.AddtoCart(userId, productId, quantity);
    }
    public void RemoveFromCart(int User_id, int Product_id) throws SQLException {
    	cartDao.RemoveFromCart(User_id, Product_id);
    }
    public double getProductPrice(int productId) throws SQLException {
    	
    	return cartDao.getProductPrice(productId);
    	
    }
    public double getTotalAmount(List<CartBean> cartList) {
        double total = 0;
        for (CartBean item : cartList) {
            total += item.getPrice() * item.getQuantity_buy();
        }
        return total;
    }
}
