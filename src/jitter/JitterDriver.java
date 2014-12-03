package jitter;

import java.util.ArrayList;
import java.util.Iterator;

public class JitterDriver {

	public static void main(String[] args) {
		//JitterMainFrame frame = new JitterMainFrame();
		Database users = new Database();
		
		JitterGroup[] groups = {new JitterGroup("Schoolmates"),
							    new JitterGroup("Family"),
							    new JitterGroup("Classmates")};
		
		users.getRoot().addChild(groups[0]);
		users.getRoot().getChildren().get(0).addChild(groups[2]);
		users.getRoot().addChild(groups[1]);
		
		JitterUser[] testUsers = {new JitterUser("Ryan", users),
								  new JitterUser("Lauren", users),
								  new JitterUser("Manuel", users),
								  new JitterUser("Mom", users),
								  new JitterUser("Dad", users)};
		
		

		users.getRoot().addChild(testUsers[0]);
		users.getRoot().addChild(testUsers[1]);
		users.getRoot().addChild(testUsers[2]);
		
		JitterUserFrame userframe = new JitterUserFrame(testUsers[0]);
		JitterUserFrame userframe1 = new JitterUserFrame(testUsers[1]);
		JitterAdminFrame adminframe = JitterAdminFrame.getInstance(users);
				testUsers[0].subscribe("Lauren");
		testUsers[0].subscribe("asdfg");
		testUsers[0].subscribe("Manuel");
		testUsers[1].subscribe("Ryan");
		testUsers[0].tweet("Hello");
		testUsers[1].tweet("Hello");
		//testUsers[2].tweet("Hello");
		
		Iterator<String> it = testUsers[0].getNewsfeed();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		ArrayList<String> alltweets = users.getRoot().getTweets();
		for(String s: alltweets){
			System.out.println(s);
		}
		
		
//		JitterUser ryan = users.getUser("Ryan");
//		JitterUser lauren = users.getUser("Lauren");
//		
//		ryan.tweet("Hello!");
//		ryan.tweet("Hello again!");
//		ryan.tweet("THIRD TWEET");
//		ryan.subscribe("Lauren");
//		ryan.subscribe("Lauren1");
		
//		Object[] list = users.getList().values().toArray();
//		lauren.tweet("GOD DAMN IT");
		
		
//		for(Object u : list){
//			System.out.println(u);
//		}
//		
//		Iterator<String> ryanfeed = ryan.getNewsfeed();
//		
//		while(ryanfeed.hasNext()){
//			System.out.println(ryanfeed.next());
//		}

	}

}
