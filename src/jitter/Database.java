package jitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Database {

	private HashMap<String, JitterUser> userList;
	private JitterGroup root;

	Database() {
		userList = new HashMap<String, JitterUser>();
		root = new JitterGroup("Root");
	}
	
	public JitterGroup getRoot(){
		return root;
	}

	public void addUser(String username, JitterUser user) {
		userList.put(username, user);
	}

	public Iterator<JitterUser> getList() {
		return userList.values().iterator();
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
	
	public ArrayList<JitterGroup> getGroups(JitterGroup parent){
		ArrayList<JitterGroup> groups = new ArrayList<JitterGroup>();
		for(JitterComponent jc : parent.getChildren()){
			if(jc instanceof JitterGroup){
				groups.addAll(getGroups((JitterGroup)jc));
				groups.add((JitterGroup) jc);
			}
		}
		return groups;
	}
}
