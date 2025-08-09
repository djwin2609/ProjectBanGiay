package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ClientDao {
	public static  List<String> getAddressListByUserId(int userId) throws Exception {
	    List<String> addressList = new ArrayList<>();
	    KetNoiCSDL kn = new KetNoiCSDL();
		kn.connectcsdl();
	    String sql = "SELECT Client FROM Client WHERE user_id = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setInt(1, userId);
	    ResultSet rs =  cmd.executeQuery();
	    while (rs.next()) {
	        addressList.add(rs.getString("Address"));
	    }
	    cmd.close();
	    
	    return addressList;
	}
}
