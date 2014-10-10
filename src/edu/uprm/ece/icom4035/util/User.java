package edu.uprm.ece.icom4035.util;

public class User  {
	private String userName;
	private String password;
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public User() {
		super();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument cannot be null");
		}
		if (obj instanceof User){
			User temp = (User) obj;
			return this.userName.equals(temp.userName) && this.password.equals(temp.password);
		}
		else {
			return false;
		}
	}
	
	
	

}
