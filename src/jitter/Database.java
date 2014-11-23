package jitter;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {

	private HashMap<String, JitterUser> userList;

	Database() {
		userList = new HashMap<String, JitterUser>();
	}

	public void addUser(String username, JitterUser user) {
		userList.put(username, user);
	}

	public HashMap<String, JitterUser> getList() {
		return userList;
	}

	public JitterUser getUser(String userID) {
		JitterUser user = userList.get(userID);
		if (user != null) {
			return user;
		} else {
			System.out.println("User not found: " + userID);
			return null;
		}
	}
}
