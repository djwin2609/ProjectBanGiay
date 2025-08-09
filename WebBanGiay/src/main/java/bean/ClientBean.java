package bean;

public class ClientBean {
	private int ClientID;
	private String Name;
	private String PhoneNumber;
	private String Email;
	private String UserName;
	private String Password;
	private int Role;
	public int getClientID() {
		return ClientID;
	}
	public void setClientID(int clientID) {
		ClientID = clientID;
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
	public ClientBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientBean(int clientID, String name, String phoneNumber, String email, String userName, String password,
			int role) {
		super();
		ClientID = clientID;
		Name = name;
		PhoneNumber = phoneNumber;
		Email = email;
		UserName = userName;
		Password = password;
		Role = role;
	}

	
	
}
