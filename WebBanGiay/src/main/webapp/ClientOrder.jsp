<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, bean.OrderBean,bean.OrderDetailBean"%>
<%@ page session="true"%>



<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Đơn mua của tôi</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f6f7;
	margin: 0;
	padding: 0;
}

.container {
	max-width: 1400px;
	margin: 40px auto;
	background: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
	margin-bottom: 20px;
	color: #333;
}

.order-table {
	width: 100%;
	border-collapse: collapse;
}

.order-table th, .order-table td {
	padding: 12px;
	border-bottom: 1px solid #ddd;
	text-align: center;
	white-space: nowrap; /* Ngăn xuống dòng */
}

.order-table th {
	background-color: #007bff;
	color: white;
}

.status {
	padding: 5px 10px;
	border-radius: 4px;
	color: white;
	font-weight: bold;
}

.status.pending {
	background-color: #f39c12; /* vàng cam */
}

.status.shipping {
	background-color: #3498db; /* xanh dương */
}

.status.cancel {
	background-color: #ff0000;
}

.status.done {
	background-color: #27ae60; /* xanh lá */
}

.btn-detail {
	background-color: #2980b9;
	color: white;
	padding: 6px 12px;
	border: none;
	border-radius: 4px;
	text-decoration: none;
}

.btn-detail:hover {
	background-color: #1f6391;
}
</style>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="container">
		<h2>Đơn mua của tôi</h2>
		<table class="order-table">
			<thead>
				<tr>
					<!--  <th>Mã đơn</th>-->
					<th>Tên khách hàng</th>
					<th>Số điện thoại</th>
					<th>Ngày đặt</th>
					<th>Phương thức thanh toán</th>
					<th>Địa chỉ giao hàng</th>
					<th>Tổng tiền</th>
					<th>Trạng thái</th>
					<th>Hành động</th>
				</tr>
			</thead>
			<%
			List<OrderBean> orders = (List<OrderBean>) request.getAttribute("dsOrderclient");
			if (orders != null && !orders.isEmpty()) {
				java.text.DecimalFormat formatter = new java.text.DecimalFormat("#,###");
				for (OrderBean o : orders) {
			%>
			<tr>
				<!--<td><%=o.getOrder_id()%></td>-->
				<td><%=o.getName()%></td>
				<td><%=o.getPhoneNumber()%></td>
				<td><%=o.getOrderDate()%></td>
				<td><%=o.getPaymentMethod()%></td>
				<td><%=o.getShippingAddress()%></td>
				<td><%=formatter.format(o.getTotalAmount())%> đ</td>
				<td><span
					class="status 
                    <%=o.getStatus().equals("Chờ xác nhận") ? "pending"
		: o.getStatus().equals("Đang giao") ? "shipping" : o.getStatus().equals("Đã hủy") ? "cancel " : "done"%>">
						<%=o.getStatus()%>
				</span></td>
				<td>
					<form action="ProductController" method="post"
						style="display: inline;">
						<input type="hidden" name="action" value="ViewOrderDetail" /> <input
							type="hidden" name="Order_id" value="<%=o.getOrder_id()%>" />
						<button type="submit" class="btn btn-detail">Xem chi tiết</button>
					</form>
				</td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="5">Bạn chưa có đơn hàng nào.</td>
			</tr>
			<%
			}
			%>


		</table>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
