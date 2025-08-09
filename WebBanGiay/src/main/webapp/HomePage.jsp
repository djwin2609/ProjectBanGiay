<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@page import="bean.ProductDetailBean"%>
<%@ page isErrorPage="true"%>
<%@ page errorPage=""%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>HomePage</title>

<link rel="stylesheet"
	href="https://fontawesome.com/icons/truck?f=classic&s=solid">
<style>
body {
	margin: 0;
	font-family: 'Segoe UI', sans-serif;
	background-color: #f5f5f5;
	color: #333;
}

/* === Banner === */
.banner {
	width: 100%;
	height: 380px;
	overflow: hidden;
	background-color: #ddd;
	display: flex;
    margin-top:40px;
	align-items: center;
	justify-content: center;
}

.banner img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

/* === Info Boxes === */
.info-row {
	display: flex;
	justify-content: center;
	text-align: center;
	flex-wrap: wrap;
	margin-top: 20px;
}

.info-box {
	flex: 1;
	min-width: 250px;
	padding: 16px;
	border: 1px solid #ccc;
	background-color: #fff;
	font-weight: 500;
}

.product-list {
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	gap: 24px;
	padding: 30px;
}

.product {
	background: #f9f9f9;
	border-radius: 10px;
	padding: 16px;
	text-align: center;
	transition: 0.2s ease;
	border: 1px solid #ddd;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.product:hover {
	transform: translateY(-5px);
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.product img {
	width: 100%;
	max-height: 220px; /* Giới hạn chiều cao */
	aspect-ratio: 1/1; /* Giữ ảnh vuông */
	object-fit: cover; /* Cắt ảnh cho đầy khung vuông */
	border-radius: 8px;
	margin-bottom: 12px;
}

.product h3 a {
	color: #333;
	text-decoration: none;
	transition: 0.2s;
}

.product h3 a:hover {
	color: #007bff;
	text-decoration: underline;
}
.product-link {
	text-decoration: none;
	color: inherit;
	display: block;
}

.product-link .btn-buy {
	pointer-events: none; /* để không bị chặn khi click toàn khối */
}

.btn-buy {
	background-color: #28a745;
	color: white;
	padding: 12px 20px;
	font-size: 16px;
	border: none;
	border-radius: 6px;
	text-decoration: none; /* để bỏ gạch chân */
	display: inline-flex;
	justify-content: center;
	align-items: center;
	gap: 8px;
	transition: all 0.3s ease;
}

.btn-buy:hover {
	background-color: #218838;
}

.slideshow-container {
    width: calc(100vw - 90px); /* tràn gần hết nhưng chừa 20px mỗi bên */
    max-width: 1200px;         /* tùy ý - giúp không quá to trên màn hình lớn */
    height: 500px;
    margin: 30px auto 0 auto; /* 👈 Thêm margin-top 30px */
    margin: 0 auto;
    position: relative;
    overflow: hidden;
    border-radius: 20px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}


.slide {
	display: none;
	position: absolute;
	width: 100%;
	height: 100%;
}

.slide img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.fade {
	animation: fadeEffect 1.5s ease-in-out;
}

@
keyframes fadeEffect {
	from {opacity: 0.4;
}

to {
	opacity: 1;
}
}
</style>

</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>

	<!-- Slideshow -->
	<div class="slideshow-container">
		<div class="slide fade">
			<img
				src="https://tse3.mm.bing.net/th/id/OIP.U16aXcZQPX_6pskOg5a33AHaEo?r=0&rs=1&pid=ImgDetMain&o=7&rm=3"
				alt="Slide 1">
		</div>
		<div class="slide fade">
			<img
				src="https://upcontent.vn/wp-content/uploads/2024/07/banner-giay.jpg"
				alt="Slide 2">
		</div>
		<div class="slide fade">
			<img
				src="https://m.360buyimg.com/mobilecms/s750x750_jfs/t1/130237/18/33239/85428/643623b3Fb0d3d413/66eba78844802307.jpg!q80.dpg"
				alt="Slide 3">
		</div>
	</div>
	<%
	String message = (String) request.getAttribute("message");
	%>
	<%
	if (message != null) {
	%>
	<div style="color: green;"><%=message%></div>
	<%
	}
	%>
	<!-- Info bar -->
	<div class="info-row">
		<div class="info-box">
			<i class="fas fa-certificate"></i> Hàng chính hãng 100%
		</div>
		<div class="info-box">
			<i class="fas fa-truck"></i> Miễn phí giao hàng với đơn >500k
		</div>
		<div class="info-box">
			<i class="fas fa-rotate"></i> Đổi hàng 30 ngày, bảo hành 12 tháng
		</div>
	</div>

	<!-- Product list -->

	<div class="product-list">
	<%
	List<ProductDetailBean> list = (List<ProductDetailBean>) request.getAttribute("dssanpham");
	Set<Integer> shownIds = new HashSet<>();

	if (list != null && !list.isEmpty()) {
		for (ProductDetailBean p : list) {
			if (!shownIds.contains(p.getProductID())) {
		shownIds.add(p.getProductID());
	%>
	<a href="ProductDetailConTroller?id=<%=p.getProductID()%>" class="product-link">
		<div class="product">
			<img src="<%=(p.getImage() != null) ? p.getImage() : "images/default.png"%>" 
			     alt="<%=p.getProductName()%>">
			
			<h3><%=p.getProductName()%></h3>
			
			<p><%=String.format("%,.0f", p.getPrice())%>₫</p>
			
			<div class="btn-buy">
				<i class="fa-solid fa-cart-plus"></i> Mua ngay
			</div>
		</div>
	</a>
	<%
			}
		}
	} else {
	%>
	<p>Không có sản phẩm nào để hiển thị.</p>
	<%
	}
	%>
</div>

	<jsp:include page="Footer.jsp"></jsp:include>
</body>
<script>
	let slideIndex = 0;
	showSlides();
	function showSlides() {
		const slides = document.getElementsByClassName("slide");
		for (let i = 0; i < slides.length; i++) {
			slides[i].style.display = "none";
		}
		slideIndex++;
		if (slideIndex > slides.length) {
			slideIndex = 1
		}
		slides[slideIndex - 1].style.display = "block";
		setTimeout(showSlides, 3000); // Chuyển slide mỗi 3 giây
	}
</script>
</html>
