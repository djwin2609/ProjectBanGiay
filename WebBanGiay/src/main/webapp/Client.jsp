<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.UserBean"%>
<%@ page import="dao.LoginDao"%>
<%@ page import="dao.ClientDao"%>
<%@ page import="java.util.*"%>
<%
UserBean user = (UserBean) session.getAttribute("User");
if (user == null) {
	response.sendRedirect("Login.jsp");
	return;
}

// Lấy địa chỉ từ database nếu đã có
String address = "";
try {
	//address = ClientDao.getAddressByUserId(user.getUserId());
} catch (Exception e) {
	address = "";
}
%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Hồ Sơ Của Tôi</title>
<style>
body {
	margin: 0;
	font-family: Arial, sans-serif;
	background: #f5f5f5;
}

.container {
	display: flex;
	max-width: 1000px;
	margin: 30px auto;
	background: white;
	border-radius: 10px;
	overflow: hidden;
}

.sidebar {
	width: 250px;
	background: #fff;
	border-right: 1px solid #ddd;
	padding: 20px;
}

.sidebar h3 {
	margin-bottom: 10px;
}

.sidebar ul {
	list-style: none;
	padding: 0;
}

.sidebar ul li {
	padding: 10px 0;
	color: #333;
	cursor: pointer;
}

.sidebar ul li:hover, .sidebar ul li.active {
	color: #ee4d2d;
	font-weight: bold;
}

.profile-content {
	flex: 1;
	padding: 30px;
}

.profile-content h2 {
	margin-top: 0;
	font-size: 22px;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: inline-block;
	width: 120px;
	font-weight: bold;
}

.form-group input {
	padding: 8px;
	width: 250px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.form-group .change-link {
	margin-left: 10px;
	color: #0055cc;
	text-decoration: underline;
	cursor: pointer;
}

.gender-options input {
	margin-left: 15px;
	margin-right: 5px;
}

.btn-save {
	margin-top: 20px;
	padding: 10px 25px;
	background: #ee4d2d;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}
</style>


</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="container">
		<div class="sidebar">
			<h3><%=user.getUserName()%></h3>
			<p>
				<a href="#">✎ Sửa Hồ Sơ</a>
			</p>
			<ul>
				<li class="active">Hồ Sơ</li>
				<li>Ngân Hàng</li>
				<li id="toggle-address-btn" style="cursor: pointer;">Địa chỉ</li>
				<li>Đổi Mật Khẩu</li>
				<li>Cài Đặt Thông Báo</li>
				<li>Thông Tin Cá Nhân</li>
				<li><a href="VerifyOrderController1">Đơn mua</a></li>
				<li>Kho Voucher</li>
			</ul>
		</div>
		<div class="profile-content">
			<h2>Hồ Sơ Của Tôi</h2>
			<p>Quản lý thông tin hồ sơ để bảo mật tài khoản</p>

			<form action="UpdateProfileController" method="post">
				<div class="form-group">
					<label>Tên đăng nhập:</label> <span><%=user.getUserName()%></span>
				</div>
				<div class="form-group">
					<label>Tên:</label> <input type="text" name="Name"
						value="<%=user.getName()%>">
				</div>
				<div class="form-group">
					<label>Email:</label> <span><%=user.getEmail().replaceAll("(?<=.{2}).(?=.*@)", "*")%></span>
					<span class="change-link">Thay Đổi</span>
				</div>
				<div class="form-group">
					<label>Số điện thoại:</label> <span><%=user.getPhoneNumber().replaceAll(".(?=.{2})", "*")%></span>
					<span class="change-link">Thay Đổi</span>
				</div>
				<button class="btn-save" type="submit">Lưu</button>
			</form>
		</div>
	</div>
	<script>
		document
				.getElementById('toggle-address-btn')
				.addEventListener(
						'click',
						function() {
							var section = document
									.getElementById('address-section');
							section.style.display = (section.style.display === 'none' || section.style.display === '') ? 'block'
									: 'none';
						});
	</script>
	
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
