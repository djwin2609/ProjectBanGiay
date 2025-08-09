<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
 body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
 .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 108vh;
        }
        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        .form-container h2 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #666;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-group input[type="date"] {
            width: calc(100% / 3);
        }
        .btn-submit {
            background-color: #007bff;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            width: 100%;
            cursor: pointer;
        }
        .btn-submit:hover {
            background-color: #0056b3;
        }
</style>
</head>
<body>
	 <div class="container">
    <div class="form-container">
        <h2>Đăng ký</h2>
        <p>Tạo tài khoản</p>
        <form action="DangKiControoler" method="POST">
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
            <button type="submit" class="btn-submit">Đăng ký</button>
            <p>Nếu bạn đã có tài khoản, xin mời <a href="login.jsp">Đăng nhập</a></p>
         </form>
    </div>
</div>
</body>
</html>