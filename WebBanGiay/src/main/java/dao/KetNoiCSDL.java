package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetNoiCSDL {
	public static Connection cn;
	public void connectcsdl() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("nap driver thanh cong");
			String url="jdbc:sqlserver://localhost:1433;databaseName=ShopBanGiay;encrypt=false;trustServerCertificate=true";
			String user="sa";
			String pass="sa";
			cn=DriverManager.getConnection(url,user,pass);
			System.out.println("ket noi csdl thanh cong");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
//	public static void main(String[] args) {
//		KetNoiCSDL K=new KetNoiCSDL();
//		K.connectcsdl();
//	}

}
