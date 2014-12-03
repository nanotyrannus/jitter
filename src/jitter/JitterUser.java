package jitter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;

public class JitterUser implements JitterComponent {
	
	private JitterUserFrame frame; //The frame connected to this user
	private Database database;
	private ArrayList<String> tweets;
	private ArrayList<String> newsfeed;
	private ArrayList<JitterUser> following; //Users that THIS user is following
	private ArrayList<JitterUser> followers; //Users that are following THIS user
	private SimpleDateFormat ft = new SimpleDateFormat("MMM dd hh:mm");
	private String joinDate, lastUpdateTime;
	private long lastUpdateMillis;
	
	public String userID;

	public JitterUser(String id, Database db) {
		userID = id;
		database = db;
		db.addUser(id, this);
		tweets = new ArrayList<String>();
		newsfeed = new ArrayList<String>();
		following = new ArrayList<JitterUser>();
		followers = new ArrayList<JitterUser>();
		joinDate = ft.format(new Date());
	}
	
	public void setFrame(JitterUserFrame theFrame){
		frame = theFrame;
	}
	
	public String getID(){
		return userID;
	}
	
	public String getJoinDate(){
		return joinDate;
	}
	
	public String getLastUpdateFormatted(){
		return lastUpdateTime;
	}
	
	public long getLastUpdateMillis(){
		return lastUpdateMillis;
	}

	public void tweet(String message) {
		String tweet = "[" + ft.format(new Date())+ "] " + userID + ": " + message;
		tweets.add(tweet);
		for(JitterUser u: followers){
			u.notifyTweet(tweet);
		}
		lastUpdateTime = ft.format(new Date());
		lastUpdateMillis = System.currentTimeMillis();
		frame.updateLastUpdate();
	}

	public ArrayList<String> getTweets() {
		return tweets;
	}
	
	public ArrayList<JitterUser> getFollowing(){
		return following;
	}

	public void subscribe(String user) {
		JitterUser toAdd = database.getUser(user);
		if(toAdd != null){
			toAdd.notifyFollowed(this);
			following.add(toAdd);
			frame.updateFollowing();
			lastUpdateTime = ft.format(new Date());
			frame.updateLastUpdate();
		}
		
	}

	public void notifyFollowed(JitterUser user){
		followers.add(user);
	}
	
	public void notifyTweet(String tweet){
		newsfeed.add(tweet);
		frame.updateFeed();
	}
	
	public Iterator<String> getNewsfeed(){
		return newsfeed.listIterator();
	}
	
	public String toString() {
		return ""+ userID +" Tweets: " + tweets.size() + ", Following "
				+ following.size() + ", users." + " Has " + followers.size() + " followers.";
	}
	
	public void addChild(JitterComponent child){
		System.out.println("Users are leaves and have no children!");
	}
}
