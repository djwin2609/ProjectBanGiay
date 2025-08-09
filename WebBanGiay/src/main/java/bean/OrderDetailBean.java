package bean;



public class OrderDetailBean extends OrderBean {
	private int OrderDetail_id;
	private int Product_id;
	private String ProductName;
	private int Quantity;
	private double Price;


	public int getOrderDetail_id() {
		return OrderDetail_id;
	}
	public void setOrderDetail_id(int orderDetail_id) {
		OrderDetail_id = orderDetail_id;
	}
	public int getProduct_id() {
		return Product_id;
	}
	public void setProduct_id(int product_id) {
		Product_id = product_id;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}


	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public OrderDetailBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetailBean(int orderDetail_id, int product_id, int quantity,int order_id, int user_id, String name, String phoneNumber, String shippingAddress2, java.sql.Date orderDate,
			String paymentMethod, String shippingAddress,String productName,double price, double totalAmount) {
		super();
		this.OrderDetail_id = orderDetail_id;
		this.Product_id = product_id;
		this.ProductName=productName;
		this.Quantity = quantity;
		this.Price=price;
	}
	
	
	
}
