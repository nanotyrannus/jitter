package jitter;

import java.util.ArrayList;

public class JitterUser {

	private Database database;
	private ArrayList<String> tweets;
	private ArrayList<JitterUser> following; //Users that THIS user is following
	private ArrayList<JitterUser> followers; //Users that are following THIS user

	public String userID;

	public JitterUser(String id, Database db) {
		userID = id;
		database = db;
		db.addUser(id, this);
		tweets = new ArrayList<String>();
		following = new ArrayList<JitterUser>();
	}

	public void tweet(String message) {
		tweets.add(message);
	}

	public ArrayList<String> getTweets() {
		return tweets;
	}

	public void subscribe(String user) {
		JitterUser toAdd = database.getUser(user);
		if(toAdd != null){
			toAdd.notifyFollowed(this);
			following.add(toAdd);
		}
	}

	public void notifyFollowed(JitterUser user){
		followers.add(user);
	}
	
	public String toString() {
		return "Tweets: " + tweets.size() + ", Following "
				+ following.size() + ", users" + "Has " + followers.size() + " followers.";
	}
}
