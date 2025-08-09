<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*, bean.CartBean"%>
<%@page session="true"%>
<%@page import="dao.CartDao"%>
<%@page import="bean.UserBean"%>
<%@ page import="java.text.NumberFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VerifyOrder</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
}

.checkout-container {
	max-width: 900px;
	margin: 50px auto;
	background: #ffffff;
	padding: 40px;
	border-radius: 16px;
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1); h2 { text-align : center;
	color: #333;
	margin-bottom: 30px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 25px;
	background-color: #fafafa;
	border-radius: 8px;
	overflow: hidden;
}

th {
	background-color: #007bff;
	color: white;
	text-align: center;
	padding: 12px;
}

td {
	padding: 10px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

tr:last-child td {
	border-bottom: none;
	font-weight: bold;
	background-color: #f0f0f0;
}

.form-group {
	margin-bottom: 20px;
}

label {
	display: block;
	font-weight: 500;
	margin-bottom: 6px;
	color: #333;
}

input[type="text"], select {
	width: 100%;
	padding: 10px 14px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 15px;
	transition: border 0.3s, box-shadow 0.3s;
}

input[type="text"]:focus, select:focus {
	border-color: #007bff;
	box-shadow: 0 0 6px rgba(0, 123, 255, 0.3);
	outline: none;
}

.btn {
	display: block;
	width: 100%;
	padding: 12px;
	font-size: 16px;
	border: none;
	background-color: #007bff;
	color: white;
	border-radius: 6px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.btn:hover {
	background-color: #0056b3;
}

.popup {
	position: fixed;
	top: 80px;
	right: 20px;
	padding: 15px 25px;
	border-radius: 8px;
	color: #fff;
	font-weight: bold;
	z-index: 1000;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
	transition: opacity 0.5s ease;
}

.popup.success {
	background-color: #28a745;
}

.popup.error {
	background-color: #dc3545;
}

@media ( max-width : 600px) {
	.checkout-container {
		padding: 20px;
	}
	table, thead, tbody, th, td, tr {
		display: block;
	}
	th {
		text-align: left;
		background-color: #007bff;
	}
	td {
		text-align: left;
		padding-left: 50%;
		position: relative;
	}
	td::before {
		position: absolute;
		left: 10px;
		width: 45%;
		white-space: nowrap;
		font-weight: bold;
	}
	tr {
		margin-bottom: 15px;
	}
}
</style>
<script>
window.addEventListener("DOMContentLoaded", function () {
    const popup = document.querySelector(".popup");
    if (popup) {
        setTimeout(() => {
            popup.style.opacity = 0;
            setTimeout(() => popup.remove(), 500);
        }, 4000);
    }
});
</script>
</head>
<body>
	<%
	String OrderMsg = (String) request.getAttribute("success");
	if (OrderMsg != null && !OrderMsg.trim().isEmpty()) {
	%>
	<div class="popup success"><%=OrderMsg%></div>
	<%
	}
	%>

	<jsp:include page="Header.jsp"></jsp:include>

	<div class="checkout-container">
		<h2>Xác nhận đơn hàng</h2>

		<%
		if (session.getAttribute("Login") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		UserBean user = (UserBean) session.getAttribute("User");
		if (user == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		int User = user.getUser_id();
		CartDao cartDao = new CartDao();
		List<CartBean> cart = cartDao.getCartByUserId(User); // Lấy giỏ hàng từ DB

		if (cart == null || cart.size() == 0) {
		%>
		<p>Giỏ hàng của bạn đang trống.</p>
		<%
		} else {
		double totalAmount = 0;
		%>
		<table>
			<tr>
				<th>Sản phẩm</th>
				<th>Đơn giá</th>
				<th>Số lượng</th>
				<th>Thành tiền</th>
			</tr>
			<%
			java.text.DecimalFormat formatter = new java.text.DecimalFormat("#,###");
			%>
			<%
			for (CartBean item : cart) {
				double itemTotal = item.getPrice() * item.getQuantity_buy();
				totalAmount += itemTotal;
			%>

			<tr>

				<td><%=item.getProductName()%></td>
				<td><%=item.getPrice()%>₫</td>
				<td><%=item.getQuantity_buy()%></td>
				<td><%=formatter.format(itemTotal)%> ₫</td>
			</tr>
			<%
			}
			%>
			<tr>
				<th colspan="3">Tổng cộng:</th>
				<th><%=formatter.format(totalAmount)%>₫</th>
			</tr>
		</table>

		<form action="OrderController" method="post" accept-charset="UTF-8">

			<div class="form-group">
				<label for="fullName">Họ và tên:</label> <input type="text"
					id="fullName" name="fullName"
					placeholder="Nhập họ và tên người nhận">
			</div>
			<div class="form-group">
				<label for="phone">Số điện thoại:</label> <input type="text"
					id="phone" name="phone" placeholder="Nhập số điện thoại người nhận">
			</div>
			<div class="form-group">
				<label for="shippingAddress">Địa chỉ giao hàng:</label> <input
					type="text" id="address" name="address"
					placeholder="Số nhà, đường, phường, quận, TP...">
			</div>
			<div class="form-group">
				<label for="payment">Phương thức thanh toán:</label> <select
					id="payment" name="payment">
					<option value="COD">Thanh toán khi nhận hàng (COD)</option>
					<option value="Bank">Chuyển khoản ngân hàng</option>
					<option value="Momo">Ví Momo</option>
				</select>
			</div>
			<input type="hidden" name="totalAmount" value="<%=totalAmount%>">
			<button type="submit" class="btn">Xác nhận đơn hàng</button>
		</form>
		<%
		}
		%>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
