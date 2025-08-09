package bo;

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

    public double getTotalAmount(List<CartBean> cartList) {
        double total = 0;
        for (CartBean item : cartList) {
            total += item.getPrice() * item.getQuantity_buy();
        }
        return total;
    }
}
