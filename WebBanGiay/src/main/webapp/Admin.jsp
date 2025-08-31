<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="bean.UserBean"%>
<%@ page import="java.util.*"%>
<%@page import="bean.ProductDetailBean"%>
<%@page import="bean.OrderDetailBean"%>
<%@page import="bean.OrderBean"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
<link rel="stylesheet" href="admin.css">
<!-- Font Awesome cho biểu tượng -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<style>/* modal */
.modal {
	display: none;
	position: fixed;
	z-index: 1000;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
	background: #fff;
	margin: 10% auto;
	padding: 20px;
	width: 400px;
	border-radius: 8px;
}

.close {
	float: right;
	font-size: 22px;
	cursor: pointer;
}

.order-table {
	width: 100%;
	border-collapse: collapse;
	table-layout: auto;
}

.order-table th, .order-table td {
	white-space: nowrap;
}
</style>


<script>
    function showSection(id) {
        // Ẩn tất cả các section
        //tim nhung class có id la section va duyet tung phan tu s  va xoa class active section
        document.querySelectorAll('.section').forEach(s => s.classList.remove('active-section'));
        document.getElementById(id).classList.add('active-section');
    }
    // thêm người dùng
    function toggleAddUserForm() {
        const form = document.getElementById('add-user-form');
        if(form.style.display=="none"){
        	 form.style.display="block";
        }else{
        	form.style.display="none";
        }
    }
    //them sản phẩm
    function toggleAddProductForm() {
        const form = document.getElementById('add-product-form');
        form.style.display = form.style.display === 'none' ? 'block' : 'none';
    }
    //chỉnh sửa người dùng
   function toggleEditUserForm(id,username, name, password, phone, email, role) {
    const editForm = document.getElementById('edit-user-form');
    editForm.style.display = 'block';
    // Gán dữ liệu vào form
    document.getElementById("edit-User_id").value = id;
    document.getElementById("edit-UserName").value = username;
    document.getElementById("edit-Name").value = name;
    document.getElementById("edit-UserPassword").value = password; 
    document.getElementById("edit-PhoneNumber").value = phone;
    document.getElementById("edit-Email").value = email;

    const roleSelect = document.getElementById("edit-Role");
    for (let i = 0; i < roleSelect.options.length; i++) {
        roleSelect.options[i].selected = roleSelect.options[i].value === role;
    }
}
   function cancelEditUser() {
	    document.getElementById("edit-user-form").style.display = "none";
	}
	//chỉnh sửa sản phẩm
   function toggleEditProductForm(id,productdetail_id ,productName,price,image,size,brand,stock,status,description ) {
	    const editForm = document.getElementById('edit-product-form');
	    editForm.style.display = 'block';
	    // Gán dữ liệu vào form
	    document.getElementById("edit-Product_id").value = id;
	    document.getElementById("edit-ProductDetail_id").value = productdetail_id;
	    document.getElementById("edit-ProductName").value = productName;
	    document.getElementById("edit-Price").value = price;
	    document.getElementById("edit-Image").value = image; 
	    document.getElementById("edit-Size").value = size	    
        document.getElementById("edit-Brand").value = brand;
        document.getElementById("edit-Stock").value = stock;
	    document.getElementById("edit-Description").value = description;
	    const StatusSelect = document.getElementById("edit-Status");
	    for (let i = 0; i < StatusSelect.options.length; i++) {
	        StatusSelect.options[i].selected = StatusSelect.options[i].value === status;
	    }
	}
   function cancelEditProduct() {
	    document.getElementById("edit-product-form").style.display = "none";
	}
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
	<!-- Phần chính (Sidebar + Nội dung) -->
	<div class="container">
		<aside class="sidebar">
			<h2>Admin Page</h2>
			<button class="sidebar-btn" onclick="showSection('product-section')">
				<i class="fas fa-box"></i> Quản lý sản phẩm
			</button>
			<button class="sidebar-btn" onclick="showSection('user-section')">
				<i class="fas fa-user-cog"></i> Quản lý tài khoản
			</button>
			<button class="sidebar-btn" onclick="showSection('order-section')">
				<i class="fas fa-receipt"></i> Quản lý đơn hàng
			</button>

		</aside>
		<main class="main-content">
			<%
			String userMsg = (String) request.getAttribute("success");
			String productMsg = (String) request.getAttribute("productSuccess");
			String EditUsersuccessMsg = (String) request.getAttribute("EditUsersuccess");
			String EditProductsuccessMsg = (String) request.getAttribute("EditProductsuccess");
			String DeleteUsersuccessMsg = (String) request.getAttribute("DeleteUsersuccess");
			String DeleteProductsuccessMsg = (String) request.getAttribute("DeleteProductsuccess");
			String error = (String) request.getAttribute("Thong Bao");
			if (userMsg != null) {
			%>
			<div id="popup-message" class="popup success"><%=userMsg%></div>
			<%
			} else if (productMsg != null) {
			%>
			<div id="popup-message" class="popup success"><%=productMsg%></div>
			<%
			} else if (EditUsersuccessMsg != null) {
			%>
			<div id="popup-message" class="popup success"><%=EditUsersuccessMsg%></div>
			<%
			} else if (EditProductsuccessMsg != null) {
			%>
			<div id="popup-message" class="popup success"><%=EditProductsuccessMsg%></div>
			<%
			} else if (error != null) {
			%>
			<div id="popup-message" class="popup error"><%=error%></div>

			<%
			} else if (DeleteUsersuccessMsg != null) {
			%>
			<div id="popup-message" class="popup success"><%=DeleteUsersuccessMsg%></div>
			<%
			} else if (DeleteProductsuccessMsg != null) {
			%>
			<div id="popup-message" class="popup success"><%=DeleteProductsuccessMsg%></div>
			<%
			}
			%>



			<div id="welcome-section" class="section active-section">
				<h2>Chào mừng bạn đến với trang quản trị!</h2>
			</div>

			<div id="product-section" class="section">
				<h2>Quản lý sản phẩm</h2>
				<div style="margin-bottom: 20px;">
					<button class="btn btn-add" onclick="toggleAddProductForm()">
						<i class="fas fa-plus"></i> Thêm Sản phẩm
					</button>
				</div>
				<!-- FORM THÊM SẢN PHẨM (ẩn mặc định) -->
				<div id="add-product-form"
					style="display: none; margin-bottom: 20px;">
					<form action=AdminController method="post" class="add-form">
						<input type="hidden" name="action" value="addProduct" />
						<!-- THUỘC TÍNH BẢNG PRODUCT -->
						<input type="text" name="ProductName" placeholder="Tên sản phẩm"
							required> <input type="number" name="Price"
							placeholder="Giá sản phẩm" required min="0"> <input
							type="text" name="Image" placeholder="URL anh" required>

						<!-- THUỘC TÍNH BẢNG PRODUCTDETAIL -->
						<input type="text" name="Size" placeholder="Kích thước (Size)"
							required> <input type="text" name="Brand"
							placeholder="Thương hiệu" required> <input type="number"
							name="Stock_quantity" placeholder="Số lượng" required min="0">
						<select name="Status" required>
							<option value="Available">Còn hàng</option>
							<option value="Out of stock">Hết hàng</option>
						</select>

						<textarea name="Description" placeholder="Mô tả chi tiết sản phẩm"
							rows="3" style="resize: vertical;"></textarea>

						<div>
							<button type="submit" class="btn btn-save">Thêm sản phẩm</button>
						</div>
					</form>
				</div>
				<!--  FORM CHỈNH SỬA SẢN PHẨM (ẩn mặc định) -->
				<div id="edit-product-form"
					style="display: none; margin-bottom: 20px;">
					<form action="AdminController" method="post" class="add-form">
						<input type="hidden" name="action" value="EditProduct" /> <input
							type="hidden" name="Product_id" id="edit-Product_id" /> <input
							type="hidden" name="ProductDetail_id" id="edit-ProductDetail_id" />
						<input type="text" name="ProductName" id="edit-ProductName"
							placeholder="Tên sản phẩm" required /> <input type="text"
							name="Price" id="edit-Price" placeholder="Giá" required /> <input
							type="text" name="Image" id="edit-Image"
							placeholder="Ảnh sản phẩm" required /> <input type="text"
							name="Size" id="edit-Size" placeholder="Kích thước" required />
						<input type="text" name="Brand" id="edit-Brand"
							placeholder="Thương hiệu" required /> <input type="number"
							name="Stock" id="edit-Stock" placeholder="Số lượng trong kho"
							required /> <select name="Status" id="edit-Status" required>
							<option value="Available">Còn hàng</option>
							<option value="Out of stock">Hết hàng</option>
						</select>
						<textarea name="Description" id="edit-Description"
							placeholder="Mô tả sản phẩm" rows="3"></textarea>
						<button type="submit" class="btn btn-save">Lưu thay đổi</button>
						<button type="button" class="btn btn-cancel"
							onclick="cancelEditProduct()">Hủy</button>
					</form>
				</div>

				<!-- THÊM table -->
				<table class="Product_table">
					<thead>
						<tr>
							<th>Product ID</th>
							<th>ProductDetail_id</th>
							<th>ProductName</th>
							<th>Price</th>
							<th>Image</th>
							<th>Size</th>
							<th>Brand</th>
							<th>Stock</th>
							<th>Status</th>
							<th>Description</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<ProductDetailBean> listproduct = (List<ProductDetailBean>) request.getAttribute("Dsproduct");
						if (listproduct != null && !listproduct.isEmpty()) {
							for (ProductDetailBean product : listproduct) {
						%>
						<tr>
							<td><%=product.getProductID()%></td>
							<td><%=product.getProductDetail_id()%></td>
							<td><%=product.getProductName()%></td>
							<%
							//định dạng số thành chuỗi
							java.text.DecimalFormat formatter = new java.text.DecimalFormat("#,###");
							%>
							<td><%=formatter.format(product.getPrice())%> ₫</td>
							<td><img src="<%=product.getImage()%>" alt="Ảnh"
								style="height: 50px;"></td>
							<td><%=product.getSize()%></td>
							<td><%=product.getBrand()%></td>
							<td><%=product.getStock_quantity()%></td>
							<td><%=product.getStatus()%></td>
							<td><%=product.getDescription()%></td>
							<td><a href="#" class="btn btn-edit"
								onclick="toggleEditProductForm(
                                   '<%=product.getProductID()%>',
                                   '<%=product.getProductDetail_id()%>',
							       '<%=product.getProductName()%>',
							       '<%=product.getPrice()%>',
							       '<%=product.getImage()%>',
							       '<%=product.getSize()%>',
                                   '<%=product.getBrand()%>',
							       '<%=product.getStock_quantity()%>',
							       '<%=product.getStatus()%>',
							       '<%=product.getDescription()%>')">
									<i class="fas fa-edit"></i>
							</a>
								<form
									action="AdminController?action=DeleteProduct&id=<%=product.getProductDetail_id()%>"
									method="post"
									onsubmit="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');">
									<button type="submit" class="btn btn-delete">
										<i class="fas fa-trash-alt"></i>
									</button>
								</form></td>
						</tr>
						<%
						}
						} else {
						%>
						<tr>
							<td colspan="10">Không có dữ liệu sản phẩm.</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>

			<div id="user-section" class="section">
				<div id="inline-edit-form-container"></div>
				<h2>Quản lý tài khoản</h2>


				<div style="margin-bottom: 20px;">
					<button class="btn btn-add" onclick="toggleAddUserForm()">
						<i class="fas fa-plus"></i> Thêm tài khoản
					</button>
				</div>

				<!-- FORM THÊM NGƯỜI DÙNG (ẩn mặc định) -->
				<div id="add-user-form" style="display: none; margin-bottom: 20px;">
					<form action="AdminController" method="post" class="add-form">
						<input type="hidden" name="action" value="addUser" /> <input
							type="text" name="UserName" placeholder="Tên đăng nhập" required>
						<input type="password" name="Password" placeholder="Mật khẩu"
							required> <input type="text" name="Name"
							placeholder="Họ tên" required> <input type="text"
							name="PhoneNumber" placeholder="Số điện thoại" required>
						<input type="email" name="Email" placeholder="Email" required>
						<select name="Role">
							<option value="0">Admin</option>
							<option value="1">User</option>
						</select>
						<button type="submit" class="btn btn-save">Thêm người
							dùng</button>
					</form>
				</div>
				<!-- FORM CHỈNH SỬA NGƯỜI DÙNG (ẩn mặc định) -->
				<div id="edit-user-form" style="display: none; margin-bottom: 20px;">
					<form action="AdminController" method="post" class="add-form">
						<input type="hidden" name="action" value="EditUser" /> <input
							type="hidden" name="User_id" id="edit-User_id" /> <input
							type="text" name="UserName" id="edit-UserName"
							placeholder="Tên đăng nhập" required /> <input type="text"
							name="Password" id="edit-UserPassword"
							placeholder="(Để trống nếu không đổi)" /> <input type="text"
							name="Name" id="edit-Name" placeholder="Họ tên" required /> <input
							type="text" name="PhoneNumber" id="edit-PhoneNumber"
							placeholder="Số điện thoại" required /> <input type="email"
							name="Email" id="edit-Email" placeholder="Email" required /> <select
							name="Role" id="edit-Role" required>
							<option value="0">Admin</option>
							<option value="1">User</option>
						</select>
						<button type="submit" class="btn btn-save">Lưu thay đổi</button>
						<button type="button" class="btn btn-cancel"
							onclick="cancelEditUser()">Hủy</button>
					</form>
				</div>

				<table class="user-table">
					<thead>
						<tr>
							<th>User ID</th>
							<th>UserName</th>
							<th>Password</th>
							<th>Name</th>
							<th>PhoneNumber</th>
							<th>Email</th>
							<th>Role</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<UserBean> list = (List<UserBean>) request.getAttribute("DsUser");
						if (list != null && !list.isEmpty()) {
							for (UserBean user : list) {
						%>
						<tr>
							<td><%=user.getUser_id()%></td>
							<td><%=user.getUserName()%></td>
							<td><%=user.getPassword()%></td>
							<td><%=user.getName()%></td>
							<td><%=user.getPhoneNumber()%></td>
							<td><%=user.getEmail()%></td>
							<td><%=user.getRole() == 0 ? "Admin" : "User"%></td>
							<td><a href="#" class="btn btn-edit"
								onclick="toggleEditUserForm(
                                    '<%=user.getUser_id()%>',
							       '<%=user.getUserName()%>',
							       '<%=user.getName()%>',
                                   '<%=user.getPassword()%>',
							       '<%=user.getPhoneNumber()%>',
							       '<%=user.getEmail()%>',
							       '<%=user.getRole()%>')">
									<i class="fas fa-edit"></i>
							</a>
								<form
									action="AdminController?action=DeleteUser&id=<%=user.getUser_id()%>"
									method="post"
									onsubmit="return confirm('Bạn có chắc chắn muốn xóa tài khoản này?');">
									<button type="submit" class="btn btn-delete">
										<i class="fas fa-trash-alt"></i>
									</button>
								</form></td>
						</tr>
						<%
						}
						} else {
						%>
						<tr>
							<td colspan="7">Không có dữ liệu người dùng.</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>

			</div>

			<div id="order-section" class="section">
				<h2>Quản lý đơn hàng</h2>

				<table class="order-table">
					<thead>
						<tr>
							<th>Mã đơn</th>
							<th>Tên khách hàng</th>
							<th>Số điện thoại</th>
							<th>Ngày đặt</th>
							<th>PT thanh toán</th>
							<th>ĐC giao hàng</th>
							<th>Tổng tiền</th>
							<th>Trạng thái</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<OrderBean> orders = (List<OrderBean>) request.getAttribute("dsOrder");
						if (orders != null && !orders.isEmpty()) {
							java.text.DecimalFormat formatter = new java.text.DecimalFormat("#,###");
							for (OrderBean o : orders) {
						%>
						<tr>
							<td><%=o.getOrder_id()%></td>
							<td><%=o.getName()%></td>
							<td><%=o.getPhoneNumber()%></td>
							<td><%=o.getOrderDate()%></td>
							<td><%=o.getPaymentMethod()%></td>
							<td><%=o.getShippingAddress()%></td>
							<td><%=formatter.format(o.getTotalAmount())%> đ</td>
							<td>
								<!-- Form sửa trạng thái -->
								<form action="AdminController" method="post"
									style="display: inline;">

									<input type="hidden" name="action" value="EditOrderStatus">
									<input type="hidden" name="Order_id"
										value="<%=o.getOrder_id()%>"> <select name="Status">
										<option value="Chờ xác nhận"
											<%="Chờ xác nhận".equals(o.getStatus()) ? "selected" : ""%>>Chờ xác nhận</option>
										<option value="Đang giao"
											<%="Đang giao".equals(o.getStatus()) ? "selected" : ""%>>Đang
											giao</option>
										<option value="Hoàn thành"
											<%="Hoàn thành".equals(o.getStatus()) ? "selected" : ""%>>Hoàn
											thành</option>
										<option value="Đã hủy"
											<%="Đã hủy".equals(o.getStatus()) ? "selected" : ""%>>Đã
											hủy</option>
									</select>
									<button type="submit" class="btn btn-save">Cập nhật</button>
								</form> <!-- Xóa đơn --> 
								<!-- Form xem chi tiết -->
								<form action="AdminController" method="post"
									style="display: inline;">
									<input type="hidden" name="action" value="ViewOrderDetail" />
									<input type="hidden" name="Order_id"value="<%=o.getOrder_id()%>" />
									<button type="submit" class="btn btn-detail">Xem chi
										tiết</button>
								</form>
							</td>
						</tr>

						<%
						}
						} else {
						%>
						<tr>
							<td colspan="9">Không có dữ liệu đơn hàng.</td>
						</tr>
						<%
						}
						%>
					</tbody>

				</table>
			</div>

		</main>
	</div>
</body>
</html>
