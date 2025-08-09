<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="bean.ProductDetailBean"%>
<%@ page import="java.util.List"%>
<%
ProductDetailBean productdetail = (ProductDetailBean) request.getAttribute("productdetail");
List<ProductDetailBean> sizes = (List<ProductDetailBean>) request.getAttribute("sizes");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>ProductDetail</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<style>
body {
	margin: 0;
	font-family: 'Segoe UI', sans-serif;
	background-color: #f5f5f5;
	color: #333;
}

.container {
	max-width: 1100px;
	margin: 0 auto;
	background-color: #fff;
	border-radius: 12px;
	display: flex;
	flex-wrap: nowrap; /* Không cho xuống dòng */
	gap: 40px;
	padding: 30px;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.08);
	align-items: flex-start;
}

.product-img {
	flex: 1;
	min-width: 300px;
	max-width: 45%;
	border-radius: 20px;
}

.product-img img {
	width: 100%;
	max-height: 400px;
	border-radius: 20px;
	object-fit: contain;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.product-info {
	flex: 1.2;
	min-width: 300px;
	max-width: 55%;
}

.product-info h1 {
	font-size: 28px;
	font-weight: bold;
	color: #b30000;
	margin-bottom: 15px;
}

.options {
	margin-bottom: 25px;
	padding-top: 10px;
	border-top: 1px solid #eee;
}

.options label {
	font-weight: bold;
	display: block;
	margin-bottom: 8px;
}

.options select {
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 6px;
	width: 200px;
	margin-bottom: 20px;
}

.options p {
	margin: 5px 0;
	font-size: 15px;
}

.options p strong {
	color: #111;
}

.status {
	font-weight: bold;
}

.status.available {
	color: green;
}

.status.out {
	color: red;
}

.btn-group {
	display: flex;
	gap: 15px;
	margin-top: 20px;
	flex-wrap: wrap;
}

.btn-group button {
	padding: 12px 20px;
	font-size: 16px;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	display: flex;
	align-items: center;
	gap: 8px;
	transition: all 0.3s ease;
}

.btn-buy {
	background-color: #28a745;
	color: white;
}

.btn-buy:hover {
	background-color: #218838;
}

.btn-order {
	background-color: #007bff;
	color: white;
}

.btn-order:hover {
	background-color: #0056b3;
}

@media screen and (max-width: 992px) {
	.container {
		flex-direction: column;
		padding: 20px;
	}
	.product-img, .product-info {
		max-width: 100%;
	}
	.btn-group {
		flex-direction: column;
	}
	.btn-group button {
		width: 100%;
	}
}
</style>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>

	<%
	if (productdetail != null) {
	%>
	<div class="container">
		<div class="product-img">
			<img src="<%=productdetail.getImage()%>"
				alt="<%=productdetail.getProductName()%>">
		</div>

		<div class="product-info">
			<h1><%=productdetail.getProductName()%></h1>

			<div class="options">
				<label for="size-select">Chọn size:</label> <select id="size-select"
					onchange="updateDetail()">
					<%
					if (sizes != null && !sizes.isEmpty()) {
						for (ProductDetailBean s : sizes) {
					%>
					<option value="<%=s.getSize()%>"
						data-stock="<%=s.getStock_quantity()%>"
						data-status="<%=s.getStatus()%>"
						data-desc="<%=s.getDescription()%>">Size
						<%=s.getSize()%>
					</option>
					<%
					}
					} else {
					%>
					<option disabled>Không có size khả dụng</option>
					<%
					}
					%>
				</select>

				<p>
					<strong>Tình trạng:</strong> <span id="status"
						class="status available"></span>
				</p>
				<!--  <p><strong>Số lượng còn lại:</strong> <span id="stock"style="display: none;"></span> đôi</p>-->
				<p>
					<strong>Mô tả :</strong> <span id="desc"></span>
				</p>
			</div>

			<form action="CartController" method="post">
				<input type="hidden" name="action" value="add"> <input
					type="hidden" name="Product_id"
					value="<%=productdetail.getProductID()%>"> <input
					type="hidden" name="Quantity" value="1"> <input
					type="hidden" name="Size" id="selectedSize">
				<button type="submit" class="btn-buy">
					<i class="fa-solid fa-cart-plus"></i> Thêm vào giỏ hàng
				</button>
			</form>

			<button type="button" class="btn-order"
				onclick="location.href='Order.jsp'">
				<i class="fa-solid fa-bag-shopping"></i> Đặt hàng
			</button>

		</div>
	</div>
	<%
	} else {
	%>
	<p style="color: red;">Sản phẩm không tồn tại hoặc chưa được chọn!</p>
	<%
	}
	%>

	<script>
		function updateDetail() {
			const select = document.getElementById('size-select');
			const option = select.options[select.selectedIndex];

			const stock = option.dataset.stock;
			const status = option.dataset.status;
			const desc = option.dataset.desc;

			// Gán mô tả
			const descEl = document.getElementById('desc');
			if (descEl)
				descEl.innerText = desc;

			// Gán tình trạng
			const statusEl = document.getElementById('status');
			if (statusEl) {
				statusEl.innerText = status;
				statusEl.className = "status "
						+ (status === "Available" ? "available" : "out");
			}

			// Nút mua và đặt hàng
			const buyBtn = document.querySelector('.btn-buy');
			const orderBtn = document.querySelector('.btn-order');

			if (stock === "0") {
				buyBtn.disabled = true;
				buyBtn.style.opacity = 0.5;
				orderBtn.disabled = true;
				orderBtn.style.opacity = 0.5;
			} else {
				buyBtn.disabled = false;
				buyBtn.style.opacity = 1;
				orderBtn.disabled = false;
				orderBtn.style.opacity = 1;
			}
		}
		document.querySelector('.btn-order').addEventListener('click',
				function() {
					window.location.href = 'OrderController?action=newOrder';
				});

		updateDetail();
	</script>

	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
