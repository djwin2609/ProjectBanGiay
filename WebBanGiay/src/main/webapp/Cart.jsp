<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, bean.CartBean"%>
<%@page import="dao.CartDao"%>
<%@page import="bean.UserBean"%>


<%@ page session="true"%>
<%

UserBean user = (UserBean) session.getAttribute("User");
if (user == null) {
	response.sendRedirect("Login.jsp");
	return;
}
int User = user.getUser_id();

CartDao cartDao = new CartDao();
List<CartBean> cart = cartDao.getCartByUserId(User);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Giỏ hàng</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	
	<div class="container mt-4">
		<h2 class="mb-4">
			<i class="fa-solid fa-cart-shopping"></i> Giỏ hàng của bạn
		</h2>

		<%
		if (cart.isEmpty()) {
		%>
		<div class="alert alert-warning">Giỏ hàng đang trống.</div>
		<%
		} else {
		%>
		<form action="CartController" method="post">
			<table class="table table-bordered text-center align-middle">
				<thead class="table-dark">
					<tr>
						<th>Chọn</th>
						<!-- Cột checkbox -->
						<th>STT</th>
						<th>Hình ảnh</th>
						<th>Tên sản phẩm</th>
						<th>Đơn giá</th>
						<th>Số lượng</th>
						<th>Thành tiền</th>
						<th>Xóa</th>
					</tr>
				</thead>
				<tbody>
					<%
					int stt = 1;
					for (CartBean item : cart) {
						double subtotal = item.getPrice() * item.getQuantity_buy();
						
					%>
					<tr>
						<td>
						<input type="checkbox" name="selectedProducts"
							value="<%=item.getProductID()%>" class="product-checkbox" checked>
							
							</td>
						<td><%=stt++%></td>
						<td><img src="<%=item.getImage()%>" width="80" height="80"
							alt="Ảnh sản phẩm"></td>
						<td><%=item.getProductName()%></td>
						<td><%=String.format("%,.0f", item.getPrice())%> đ</td>
						<td><input type="number"
							name="quantity_<%=item.getProductID()%>"
							value="<%=item.getQuantity_buy()%>" min="1"
							class="form-control text-center"></td>
						<td class="subtotal" data-price="<%=item.getPrice()%>"><%=String.format("%,.0f", subtotal)%>
							đ</td>
							
						<td>
							<form action="CartController" method="post"style="display: inline;">
								<input type="hidden" name="action" value="remove" /> <input
									type="hidden" name="Product_id"
									value="<%=item.getProductID()%>" />
								<button type="submit" class="btn btn-danger btn-sm">X</button>
							</form>
						</td>

					</tr>
					<%
					}
					%>
					<tr>
						<td colspan="6" class="text-end"><strong>Tổng tiền
								sản phẩm:</strong></td>
						<td colspan="2"><strong id="totalSelected">0 đ</strong></td>
					</tr>
				</tbody>
			</table>

			<div class="d-flex justify-content-between">
				<a href="ProductController" class="btn btn-secondary"
					style="margin-bottom: 100px;">← Tiếp tục mua hàng</a>
				<div>
					<button type="submit" class="btn btn-primary"
						style="margin-bottom: 100px;">Cập nhật giỏ hàng</button>
					<a href="OrderController" class="btn btn-success"
						style="margin-bottom: 100px;">Đặt Hàng</a>
				</div>
			</div>
		</form>
		<%
		}
		%>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
<script>
function formatCurrency(num) {
    return num.toLocaleString('vi-VN') + " đ";
}
function updateTotal() {
    let total = 0;
    //lấy tất cả thẻ tr trong bảng giỏ hàng 
    const rows = document.querySelectorAll("tbody tr");
	//duyệt qua các hàng
    rows.forEach(row => {
        const checkbox = row.querySelector(".product-checkbox");
        const priceCell = row.querySelector(".subtotal");

        if (checkbox && checkbox.checked && priceCell) {
        	//lấy giá gốc của sản phẩm từ html
            const price = parseFloat(priceCell.dataset.price);
        	//lấy số lượng sản phẩm từ ô input.
            const quantityInput = row.querySelector("input[type='number']");
            const quantity = parseInt(quantityInput.value);
            //cộng dồn
            total += price * quantity;

            // Cập nhật lại thành tiền từng dòng
            const newSubtotal = price * quantity;
            priceCell.textContent = formatCurrency(newSubtotal);
        }
    });

    // Cập nhật tổng tiền
    document.getElementById("totalSelected").innerText = formatCurrency(total);
}

// Gọi lại khi checkbox hoặc số lượng thay đổi
document.querySelectorAll(".product-checkbox, input[type='number']").forEach(input => {
    input.addEventListener("change", updateTotal);
});

// Gọi khi trang tải xong
window.onload = updateTotal;

</script>

</html>
