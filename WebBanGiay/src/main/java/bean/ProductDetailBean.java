package bean;

public class ProductDetailBean extends ProductBean {
	private int ProductDetail_id;
	private float size;
	private String brand;
	private int stock_quantity;
	private String status;
	private String Description;
	
	public int getProductDetail_id() {
		return ProductDetail_id;
	}

	public void setProductDetail_id(int productDetail_id) {
		ProductDetail_id = productDetail_id;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public ProductDetailBean(int productID, String productName, double price, String image, int productDetail_id,
			float size, String brand, int stock_quantity, String status, String description) {
		super(productID, productName, price, image);
		ProductDetail_id = productDetail_id;
		this.size = size;
		this.brand = brand;
		this.stock_quantity = stock_quantity;
		this.status = status;
		Description = description;
	}



	@Override
	public String toString() {
		return "ProductDetailBean {" + '\'' + ", size=" + size + ", brand='" + brand + '\'' + ", stock_quantity="
				+ stock_quantity + ", status='" + status + '\'' + ", description='" + Description + '\'' + '}';
	}


}
