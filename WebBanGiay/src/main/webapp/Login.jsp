<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="Login.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script>
setTimeout(() => {
    const popup = document.getElementById("popup-message");
    if (popup) {
        popup.remove();
    }
}, 5000);
window.addEventListener('DOMContentLoaded', () => {
    const popup = document.getElementById("popup-message");
    if (popup) {
        popup.style.display = "block";
        setTimeout(() => {
            popup.style.opacity = "0";
            setTimeout(() => popup.remove(), 500); 
        }, 3000);
    }
});
</script>
</head>
<body>

	<%
	String LoginMsg = (String) request.getAttribute("success");
	String error = (String) request.getAttribute("ThongBao");
	if (LoginMsg != null) {
	%>
	<div id="popup-message" class="popup success"><%=LoginMsg%></div>
	<%
	} else if(error!=null){
	%>
	<div id="popup-message" class="popup error"><%=error%></div>
	<%
	}
	%>

	<div class="header">
		<div class="header-content">
			<h1>MyShoes.</h1>
		</div>
	</div>
	<div class="loginbox">
		<h1>Đăng nhập</h1>
		<form action="LoginController" method="post">
			<p>Tên đăng nhập</p>
			<input type="text" name="txtun"
				placeholder="Email hoặc số điện thoại">
			<p>Mật khẩu</p>
			<input type="password" name="txtpass" placeholder="Mật khẩu">
			<input type="submit" name="th" value="Login"> <a href="#">Quên
				mật khẩu</a><br> <a href="RegisterController">Bạn chưa đăng kí
				tài khoản?</a>
		</form>
	</div>
</body>
</html>