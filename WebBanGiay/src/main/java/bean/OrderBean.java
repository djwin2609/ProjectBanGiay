package bean;

import java.util.Date;

public class OrderBean {
	private int Order_id;
	private int User_id;
	private String Name;
	private String PhoneNumber;
	private Date OrderDate;
	private double totalAmount;
	private String paymentMethod;
	private String ShippingAddress;
	private String Status;
	public int getOrder_id() {
		return Order_id;
	}
	public void setOrder_id(int order_id) {
		Order_id = order_id;
	}
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getShippingAddress() {
		return ShippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		ShippingAddress = shippingAddress;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public OrderBean() {
		
		
	}
	public OrderBean(int order_id, int user_id, String name, String phoneNumber, Date orderDate, double totalAmount,
			String paymentMethod, String shippingAddress, String status) {
		super();
		Order_id = order_id;
		User_id = user_id;
		Name = name;
		PhoneNumber = phoneNumber;
		OrderDate = orderDate;
		this.totalAmount = totalAmount;
		this.paymentMethod = paymentMethod;
		ShippingAddress = shippingAddress;
		Status = status;
	}


	
	
	
}
