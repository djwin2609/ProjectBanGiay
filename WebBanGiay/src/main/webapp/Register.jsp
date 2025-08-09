<%@page import="bo.RegisterBo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="RegisterShoes.css">
	<style>
	
	.toast-error {
    position: fixed;
    top: 60px;
    right: 20px;
    background-color: #f44336; /* Màu đỏ */
    color: white;
    padding: 12px 20px;
    border-radius: 6px;
    box-shadow: 0 0 10px rgba(0,0,0,0.2);
    z-index: 9999;
    font-weight: bold;
    min-width: 250px;
    text-align: center;
    animation: slide-in 0.4s ease, fadeout 0.5s 4s ease forwards;
}

@keyframes slide-in {
    from {
        opacity: 0;
        transform: translateX(100%);
    }
    to {
        opacity: 1;
        transform: translateX(0);
    }
}

@keyframes fadeout {
    from {
        opacity: 1;
    }
    to {
        opacity: 0;
        transform: translateX(100%);
    }
	</style>
</head>
<body>
	<%
	String a = (String) request.getAttribute("kt");
	RegisterBo regBo = new RegisterBo();
	%>
 <div class="header">
    <div class="left">
        <div class="logo">MyShoes</div>
        <div class="register-label">Đăng ký</div>
    </div>
    <div class="help-text">Bạn cần giúp đỡ gì?</div>
</div>

   <div class="container">
    <div class="form-container">
        <h2>Đăng ký</h2>
        <p>Tạo tài khoản</p>
			<%
			String loi = (String) request.getAttribute("DangKiLoi");
			if (loi != null) {
			%>
			<div class="toast-error">
				<%=loi%>
			</div>
			<%
			}
			%>
			<form action="RegisterController" method="POST">
            <div class="form-group">
                <label for="username">Tên đăng nhập</label>
                <input type="text" id="username" name="username" placeholder="Tên đăng nhập" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" placeholder="Mật khẩu" required>
            </div>
				<div class="form-group">
                <label for="fullname">Họ tên</label>
                <input type="text" id="fullname" name="fullname" placeholder="Họ tên">
            </div>
            <div class="form-group">
                <label for="phone">Điện thoại</label>
                <input type="text" id="phone" name="phone" placeholder="Điện thoại">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Email">
            </div>
            <button type="submit" class="btn-submit">Đăng ký</button>
            <p>Nếu bạn đã có tài khoản, xin mời <a href="Login.jsp">Đăng nhập</a></p>
        </form>
    </div>
</div>
</body>
</html>