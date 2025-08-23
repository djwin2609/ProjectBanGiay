<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, bean.OrderDetailBean"%>
<head>
<meta charset="UTF-8">
<title>OrderDetail</title>
<style>
body, h1, h2, button {
    margin: 0;
    padding: 0;
    font-family: 'Segoe UI', sans-serif;
}

body {
    background-color: #ecf0f1;
}


/* Topbar */
.topbar {
    background-color: #2c3e50;
    color: white;
    height: 60px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 20px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

.topbar .right {
    display: flex;
    align-items: center;
    gap: 15px;
}

.topbar .icon {
    font-size: 18px;
    cursor: pointer;
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
h2 {
	color: #2c3e50;
	margin-bottom: 15px;
}

p {
	margin: 6px 0;
	font-size: 15px;
}

p strong {
	width: 150px;
	display: inline-block;
	color: #2c3e50;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	background: white;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
	border-radius: 6px;
	overflow: hidden;
}

thead {
	background: #3498db;
	color: white;
}

th, td {
	padding: 12px 10px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

tbody tr:hover {
	background-color: #f1f7ff;
}

td:nth-child(1) {
	text-align: left;
}

.back-btn {
	display: inline-block;
	margin-top: 20px;
	padding: 8px 16px;
	background: #3498db;
	color: white;
	text-decoration: none;
	border-radius: 4px;
	transition: 0.3s;
}

.back-btn:hover {
	background: #2980b9;
}

.order-card {
	background: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	max-width: 800px;
	margin: auto;
	margin-top: 20px;
}
.total {
            text-align: right;
            font-size: 18px;
            font-weight: bold;
            margin-top: 25px;
            color: #e74c3c;
        }
</style>
</head>
<body>
	<!-- Thanh tiêu đề (Top Bar) -->
	<header class="topbar">
		<div class="left">
			<h1>Admin</h1>
		</div>
		<div class="right">
			<span class="username">Xin chào, Admin</span> <i
				class="fas fa-bell icon"></i> <i class="fas fa-cog icon"></i> <a
				href="LoginController?action=logout"
				class="logout-btn btn btn-danger">Đăng xuất</a>
		</div>
	</header>
	<%
	List<OrderDetailBean> details = (List<OrderDetailBean>) request.getAttribute("orderDetails");
	int orderId = (Integer) request.getAttribute("orderId");
	%>

	<div class="order-card">
		<h2>
			Chi tiết đơn hàng #<%=orderId%></h2>

		<%
		if (details != null && !details.isEmpty()) {
			OrderDetailBean first = details.get(0);
			java.text.DecimalFormat formatter = new java.text.DecimalFormat("#,###");
		%>
		<p>
			<strong>Tên KH:</strong>
			<%=first.getName()%></p>
		<p>
			<strong>SĐT:</strong>
			<%=first.getPhoneNumber()%></p>
		<p>
			<strong>Địa chỉ:</strong>
			<%=first.getShippingAddress()%></p>
		<p>
			<strong>Ngày đặt:</strong>
			<%=first.getOrderDate()%></p>
		<p>
			<strong>PT Thanh toán:</strong>
			<%=first.getPaymentMethod()%></p>
		<p>
			<strong>Trạng thái:</strong>
			<%=first.getStatus()%></p>
		<p>
			<strong>Tổng tiền:</strong>
			<%=formatter.format(first.getTotalAmount())%></p>
				

		<table>
			<thead>
				<tr>
					<th>Sản phẩm</th>
					<th>Số lượng</th>
					<th>Đơn giá</th>
					<th>Thành tiền</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (OrderDetailBean d : details) {
				%>
				<tr>
					<td><%=d.getProductName()%></td>
					<td><%=d.getQuantity()%></td>
					<td><%=formatter.format(d.getPrice()) %></td>
					<td><%=formatter.format(d.getQuantity() * d.getPrice()) %></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<div class="total">
            Tổng tiền: <%=formatter.format(first.getTotalAmount()) %> ₫
        </div>
		<%
		} else {
		%>
		<p>Không có chi tiết đơn hàng.</p>
		<%
		}
		%>

		<a href="AdminController?action=ViewOrderList" class="back-btn">←
			Quay lại danh sách đơn hàng</a>
	</div>
</body>

