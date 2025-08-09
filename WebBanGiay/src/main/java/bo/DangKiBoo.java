package bo;
import dao.DangKiDao;



public class DangKiBoo {
	DangKiDao dkdao=new DangKiDao();
	public void Add(String HoTen,String TenDangNhap,String MatKhau) throws Exception {

			dkdao.them(HoTen, TenDangNhap, MatKhau);
		}
}
