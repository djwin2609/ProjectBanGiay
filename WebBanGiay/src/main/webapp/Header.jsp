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

.menu li:hover {
	color: #ffd54f;
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
  box-shadow: 0px 8px 16px rgba(0,0,0,0.2);
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
        <li>Nike</li>
        <li>Adidas</li>
        <li>Lacoste</li>
        <li>Puma</li>
    </ul>

    <div class="search">
        <input type="text" placeholder="Tìm kiếm">
        <button>Tìm kiếm</button>
    </div>

    <div class="links">
        <a href="#">Liên Hệ</a>
        <a href="Cart.jsp" class="cart-link">
            <span class="cart">
                <i class="fa-solid fa-cart-shopping"></i>
            </span>
        </a>

        <%-- Avatar + Dropdown --%>
        <%
            String username = (String) session.getAttribute("username");
            String firstChar = username != null && !username.isEmpty() ? username.substring(0, 1).toUpperCase() : "?";
        %>

        <div class="profile-dropdown">
            <button class="profile-btn">
                <div class="profile-icon"><%= firstChar %></div>
                <%= username %>
            </button>
            <div class="dropdown-content">
                <a href="Client.jsp">Hồ sơ</a>
                <a href="ChangePassword.jsp">Đổi mật khẩu</a>
                <a href="LoginController?action=logout">Đăng xuất</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>