<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Footer</title>
<style>
.site-footer {
  background-color: #993030; /* giống navbar */
  color: #ffffff;
  padding: 30px 20px;
  font-family: Arial, sans-serif;
}

.footer-container {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  max-width: 1200px;
  margin: 0 auto;
}

.footer-section {
  flex: 1;
  min-width: 250px;
  margin: 10px;
}

.footer-section h3 {
  margin-bottom: 10px;
  font-size: 18px;
}

.footer-section p, 
.footer-section li {
  font-size: 14px;
  line-height: 1.6;
}

.footer-section ul {
  list-style: none;
  padding: 0;
}

.footer-section a {
  color: #ffffff;
  text-decoration: none;
}

.footer-section a:hover {
  text-decoration: underline;
}

.footer-logo {
  width: 100px;
  margin-bottom: 10px;
}
</style>
</head>
<body>
 <footer class="site-footer">
  <div class="footer-container">
    <div class="footer-section">
      <img src="img/myshoes_logo.png" alt="Myshoes Logo" class="footer-logo">
      <p>Myshoes.vn được định hướng trở thành hệ thống thương mại điện tử bán giày chính hãng hàng đầu Việt Nam.</p>
      <p><strong>Showroom:</strong> Phương Ngạn, Ái Tử, Quảng Trị</p>
      <p><strong>Hotline:</strong> 0349996408</p>
    </div>

    <div class="footer-section">
      <h3>VỀ CHÚNG TÔI</h3>
      <ul>
        <li><a href="#">Giới thiệu</a></li>
        <li><a href="#">Điều khoản sử dụng</a></li>
        <li><a href="#">Chính sách bảo mật</a></li>
        <li><a href="#">Tin tức Myshoes</a></li>
        <li><a href="#">Cơ hội việc làm</a></li>
        <li><a href="#">Liên hệ</a></li>
      </ul>
    </div>

    <div class="footer-section">
      <h3>KHÁCH HÀNG</h3>
      <ul>
        <li><a href="#">Hướng dẫn mua hàng</a></li>
        <li><a href="#">Chính sách đổi trả</a></li>
        <li><a href="#">Chính sách bảo hành</a></li>
        <li><a href="#">Khách hàng thân thiết</a></li>
        <li><a href="#">Hướng dẫn chọn size</a></li>
        <li><a href="#">Chương trình khuyến mãi</a></li>
      </ul>
    </div>
  </div>
</footer>
</body>
</html>