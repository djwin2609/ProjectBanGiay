package bean;

public class CartBean extends ProductBean{
	private int Quantity_buy;
	private double Total_amount;
	public int getQuantity_buy() {
		return Quantity_buy;
	}
	public void setQuantity_buy(int quantity_buy) {
		Quantity_buy = quantity_buy;
	}
	public double getTotal_amount() {
		return Total_amount;
	}
	public void setTotal_amount(double total_amount) {
		Total_amount = total_amount;
	}
	public CartBean() {
	    super(); // Gọi constructor rỗng của lớp cha (ProductBean)
	}

	public CartBean(int productID, String productName, double price, String image, int quantity_buy,
			double total_amount) {
		super(productID, productName, price, image);
		Quantity_buy = quantity_buy;
		Total_amount = total_amount;
	}
	
}
