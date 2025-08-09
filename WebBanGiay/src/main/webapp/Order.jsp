<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dao.OrderDao, bean.OrderBean, bean.OrderDetailBean, bean.UserBean,bean.ProductDetailBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OrderDetail</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
         .container {
            max-width: 900px;
            margin: 50px auto;
            background: #ffffff;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 25px;
        }
        .info {
            background-color: #f9f9f9;
            border-left: 5px solid #3498db;
            padding: 15px 25px;
            margin-bottom: 30px;
            border-radius: 8px;
        }
        .info p {
            margin: 8px 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            padding: 14px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }
        th {
            background-color: #3498db;
            color: #fff;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .total {
            text-align: right;
            font-size: 18px;
            font-weight: bold;
            margin-top: 25px;
            color: #e74c3c;
        }
        .back-button {
            display: inline-block;
            margin-top: 30px;
            padding: 12px 24px;
            background-color: #27ae60;
            color: white;
            text-decoration: none;
            font-weight: bold;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }
        .back-button:hover {
            background-color: #219150;
        }
        @media (max-width: 600px) {
            .container {
                padding: 20px;
            }
            th, td {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
	
    <div class="container">
        <%
            UserBean user = (UserBean) session.getAttribute("User");
            if (user == null) {
                response.sendRedirect("Login.jsp");
                return;
            }

            OrderBean order = (OrderBean) session.getAttribute("Order");
            if (order == null) {
                response.sendRedirect("Login.jsp");
                return;
            }

            int userId = user.getUser_id();
            int orderId = order.getOrder_id();

            OrderDao orderDao = new OrderDao();
         
            List<OrderDetailBean> list = (List<OrderDetailBean>)request.getAttribute("dsOrder");
            if (list == null || list.isEmpty()) {
                %>
                    <p>Chưa có đơn hàng nào.</p>
                <%
                    } else {
                %>


        <h2>Đơn hàng #<%= orderId %></h2>
        <div class="info">
            <p><strong>Họ tên người nhận:</strong> <%= order.getName() %></p>
            <p><strong>Số điện thoại:</strong> <%= order.getPhoneNumber() %></p>
            <p><strong>Địa chỉ giao hàng:</strong> <%= order.getShippingAddress() %></p>
            <p><strong>Ngày đặt hàng:</strong> <%= order.getOrderDate() %></p>
            <p><strong>Trạng thái:</strong> <%= order.getStatus() %></p>
        </div>

        <table>
            <tr>
                <th>Sản phẩm</th>
                <th>Số lượng</th>
                <th>Đơn giá</th>
                <th>Thành tiền</th>
            </tr>
            <%java.text.DecimalFormat formatter = new java.text.DecimalFormat("#,###"); %>
            <%
                for (OrderDetailBean item : list) {
            %>
            
            <tr>
                <td><%= item.getProductName() %></td>
                <td><%= item.getQuantity() %></td>
                <td><%= formatter.format(item.getPrice()) %> ₫</td>
                <td><%= formatter.format(item.getQuantity() * item.getPrice()) %> ₫</td>
            </tr>
            <%
                }
            %>
        </table>

        <div class="total">
            Tổng tiền: <%=formatter.format(order.getTotalAmount() )%> ₫
        </div>
	<%} %>
        <a href="ProductController" class="back-button">Quay lại trang chủ</a>
    </div>
    <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
