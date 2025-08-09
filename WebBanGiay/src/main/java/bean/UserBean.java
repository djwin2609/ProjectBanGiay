package bean;

public class UserBean {
	private int User_id;
	private String UserName;
	private String Password;
	private String Name;
	private String PhoneNumber;
	private String Email;
	private int Role;
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getRole() {
		return Role;
	}
	public void setRole(int role) {
		Role = role;
	}
	public UserBean(int user_id, String userName, String password, String name, String phoneNumber, String email,
			int role) {
		super();
		User_id = user_id;
		UserName = userName;
		Password = password;
		Name = name;
		PhoneNumber = phoneNumber;
		Email = email;
		Role = role;
	}
	@Override
	public String toString() {
	    return "UserBean{" +
	            "User_id=" + User_id +
	            ", UserName='" + UserName + '\'' +
	            ", Password='" + Password + '\'' +
	            ", Name='" + Name + '\'' +
	            ", PhoneNumber='" + PhoneNumber + '\'' +
	            ", Email='" + Email + '\'' +
	            ", Role=" + Role +
	            '}';
	}

	
	
}
