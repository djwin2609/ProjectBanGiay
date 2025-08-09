	package bean;
	
	public class ProductBean {
		private int ProductID;
		private String ProductName;
		private double Price;
		private String image;
		
		public int getProductID() {
			return ProductID;
		}
	
		public void setProductID(int productID) {
			ProductID = productID;
		}
	
		public String getProductName() {
			return ProductName;
		}
	
		public void setProductName(String producName) {
			ProductName = producName;
		}
	
		public double getPrice() {
			return Price;
		}
	
		public void setPrice(double price) {
			Price = price;
		}
	
		public String getImage() {
			return image;
		}
	
		public void setImage(String image) {
			this.image = image;
		}
	
		public ProductBean(int productID, String productName, double price, String image) {
			super();
			ProductID = productID;
			ProductName = productName;
			Price = price;
			this.image = image;
		}
	
		public ProductBean() {
			// TODO Auto-generated constructor stub
		}
	
		@Override
		public String toString() {
		    return "ProductBean{" +
		            "productID='" + ProductID + '\'' +
		            ", productName='" + ProductName + '\'' + 
		            ", price=" + Price +
		            ", image='" + image + '\'' +         
		            '}';
		}
	
	}
