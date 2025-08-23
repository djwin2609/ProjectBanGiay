package bean;

import java.util.Date;

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

//	public OrderDetailBean(int order_id, int user_id, String name, String phoneNumber, Date orderDate,
//			double totalAmount, String paymentMethod, String shippingAddress, String status) {
//		super(order_id, user_id, name, phoneNumber, orderDate, totalAmount, paymentMethod, shippingAddress, status);
//		// TODO Auto-generated constructor stub
//	}

	public OrderDetailBean( int order_id, int user_id, String name,String phoneNumber,java.sql.Date orderDate, double totalAmount,  String paymentMethod,String shippingAddress,String status,
			 int orderDetail_id, int product_id,String productName, int quantity, double price) {
		super(order_id,user_id,name,phoneNumber,orderDate,totalAmount,paymentMethod,shippingAddress,status);
		this.OrderDetail_id = orderDetail_id;
		this.Product_id = product_id;
		this.ProductName = productName;
		this.Quantity = quantity;
		this.Price = price;
	}

	@Override
	public String toString() {
		return "OrderDetailBean{" + ", Order_id=" + getOrder_id() + ", User_id=" + getUser_id() + ", Name='" + getName()
				+ '\'' + ", PhoneNumber='" + getPhoneNumber() + '\'' + ", OrderDate=" + getOrderDate()
				+ ", TotalAmount=" + getTotalAmount() + ", PaymentMethod='" + getPaymentMethod() + '\''
				+ ", ShippingAddress='" + getShippingAddress() + '\'' + ", Status='" + getStatus() + '\''
				+ "OrderDetail_id=" + OrderDetail_id + ", Product_id=" + Product_id + ", ProductName='" + ProductName
				+ '\'' + ", Quantity=" + Quantity + ", Price=" + Price +

				'}';
	}

}
