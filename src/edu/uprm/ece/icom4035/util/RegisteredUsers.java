package edu.uprm.ece.icom4035.util;
import edu.uprm.ece.icom4035.list.*;

/**
 * 
 * @author Edwin O. Badillo
 * @version 1.0
 * 
 */
public class RegisteredUsers {

	private List<User> registeredUserList;

	/**
	 * Constructor for Registered User
	 */
	public RegisteredUsers() {
		this.registeredUserList = new LinkedList<User>();
	}

	/**
	 * adds a new registered user. Returns false if the user is already
	 * registered or true if added.
	 * 
	 * @param user
	 * @return
	 */
	public boolean addRegisteredUser(User user) {
		if(registeredUserList.contains(user)){
			return false;
		}
		else{
			registeredUserList.add(user);
			return true;
		}
		

	}

	/**
	 * returns true if the user is in the list of registered users, or false
	 * otherwise.
	 * 
	 * @param theUser
	 * @return
	 */
	public boolean isRegisteredUser(User theUser) {
		return registeredUserList.contains(theUser);

	}
}
