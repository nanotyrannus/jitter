package jitter;

import java.util.ArrayList;
import java.util.Iterator;

public class JitterDriver {

	public static void main(String[] args) {
		//JitterMainFrame frame = new JitterMainFrame();
		Database users = new Database();
		
		JitterUser[] testUsers = {new JitterUser("Ryan", users),
								  new JitterUser("Lauren", users),
								  new JitterUser("Manuel", users)};
		
		JitterUser ryan = users.getUser("Ryan");
		ryan.tweet("Hello!");
		ryan.tweet("Hello again!");
		ryan.tweet("THIRD TWEET");
		ryan.subscribe("Lauren");
		ryan.subscribe("Lauren1");
		
		Object[] list = users.getList().values().toArray();
		
		for(Object u : list){
			System.out.println(u);
		}
		
		ArrayList<String> tweets = users.getUser("Ryan").getTweets();
		
		Iterator<String> it = tweets.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		JitterUserFrame userframe = new JitterUserFrame();
		JitterAdminFrame adminframe = new JitterAdminFrame();
	}

}
