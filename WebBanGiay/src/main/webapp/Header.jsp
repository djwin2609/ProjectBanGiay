<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<style>
/* === Navbar === */
.navbar {
	background-color: #b71c1c; /* đỏ đô tươi */
	color: white;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 12px 24px;
	flex-wrap: wrap;
	margin-bottom: 20px;
}

.logo {
	font-size: 28px;
	font-weight: bold;
	color: #ffffff; /* màu trắng */
	text-decoration: none; /* bỏ gạch chân */
	font-family: 'Poppins', sans-serif;
	transition: color 0.3s ease;
}

.logo:hover {
	color: #FF7043; /* cam khi hover */
}

.menu {
	list-style: none;
	display: flex;
	gap: 24px;
	padding: 0;
	margin: 0;
}

.menu li {
	font-size: 16px;
	cursor: pointer;
	transition: color 0.3s;
}

.menu a {
	display: block;
	padding: 8px 6px;
	color: #fff;
	text-decoration: none;
	font-weight: 600;
	letter-spacing: .2px;
	border-radius: 8px;
	transition: background .2s ease, color .2s ease, transform .08s ease;
}

.menu a:hover {
	background: rgba(255, 255, 255, .12);
}

.menu a:active {
	transform: translateY(1px);
}

.menu a.active {
	background: #ffffff;
	color: #b71c1c;
}
/* Responsive nhỏ gọn trên mobile */
@media ( max-width : 600px) {
	.menu {
		gap: 12px;
		padding: 10px 14px;
		overflow-x: auto; /* trượt ngang nếu tràn */
		-webkit-overflow-scrolling: touch;
	}
	.menu a {
		white-space: nowrap;
	}
}

.search input {
	padding: 6px 10px;
	border-radius: 4px;
	border: none;
}

.search button {
	padding: 6px 12px;
	margin-left: 6px;
	background-color: #fff;
	color: #b71c1c;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-weight: bold;
}

.search button:hover {
	background-color: #fce4ec;
}

.links a {
	color: white;
	text-decoration: none;
	font-weight: bold;
}

.links a:hover {
	text-decoration: underline;
}

.profile-dropdown {
	position: relative;
	display: inline-block;
	margin-left: 16px;
}

.profile-btn {
	display: flex;
	align-items: center;
	cursor: pointer;
	background-color: transparent;
	border: none;
	color: white;
	font-weight: bold;
	font-size: 16px;
}

.profile-icon {
	background-color: #004d40;
	border-radius: 50%;
	color: white;
	width: 32px;
	height: 32px;
	display: flex;
	justify-content: center;
	align-items: center;
	margin-right: 8px;
	font-size: 14px;
}

.dropdown-content {
	display: none;
	position: absolute;
	right: 0;
	background-color: white;
	color: black;
	min-width: 160px;
	box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
	border-radius: 8px;
	z-index: 1;
}

.dropdown-content a {
	padding: 10px 16px;
	display: block;
	text-decoration: none;
	color: black;
}

.dropdown-content a:hover {
	background-color: #f1f1f1;
}

.profile-dropdown:hover .dropdown-content {
	display: block;
}

.logout-btn {
	background-color: #e74c3c;
	color: white;
	border: none;
	padding: 6px 12px;
	border-radius: 4px;
	cursor: pointer;
}

.logout-btn:hover {
	background-color: #c0392b;
}
</style>
</head>
<body>
	<!-- Navbar -->
	<div class="navbar">
		<a href="ProductController" class="logo">MyShoes</a>

		<ul class="menu">
			<li><a
				href="${pageContext.request.contextPath}/ProductController?Brand=Nike">Nike</a></li>
			<li><a
				href="${pageContext.request.contextPath}/ProductController?Brand=Adidas">Adidas</a></li>
			<li><a
				href="${pageContext.request.contextPath}/ProductController?Brand=Lacoste">Lacoste</a></li>
			<li><a
				href="${pageContext.request.contextPath}/ProductController?Brand=Puma">Puma</a></li>
		</ul>

		<form class="search" action="SearchController" method="get">
			<input type="text" name="keyword" placeholder="Tìm kiếm sản phẩm..."
				required>
			<button type="submit">Tìm kiếm</button>
		</form>


		<div class="links">
			<a href="#">Liên Hệ</a> <a
				href="${pageContext.request.contextPath}/Cart.jsp" class="cart-link">
				<span class="cart"> <i class="fa-solid fa-cart-shopping"></i>
			</span>
			</a>

			<%
			String username = (String) session.getAttribute("username");
			if (username == null) {
			%>
			<!-- Nếu chưa đăng nhập -->
			<a href="Login.jsp" style="margin-left: 10px;">Đăng nhập</a> <a
				href="Register.jsp" style="margin-left: 10px;">Đăng ký</a>
			<%
			} else {
			String firstChar = !username.isEmpty() ? username.substring(0, 1).toUpperCase() : "?";
			%>
			<!-- Nếu đã đăng nhập -->
			<div class="profile-dropdown">
				<button class="profile-btn">
					<div class="profile-icon"><%=firstChar%></div>
					<%=username%>
				</button>
				<div class="dropdown-content">
					<a href="Client.jsp">Hồ sơ</a> <a href="ChangePassword.jsp">Đổi
						mật khẩu</a> <a href="Logout">Đăng xuất</a>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>