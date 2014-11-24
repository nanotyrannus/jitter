package jitter;

import java.util.ArrayList;
import java.util.Iterator;

public class JitterGroup implements JitterComponent{
	
	private ArrayList<JitterComponent> children;
	private String groupID;
	
	public JitterGroup(String name){
		groupID = name;
		children = new ArrayList<JitterComponent>();
	}
	
	public ArrayList<String> getTweets(){
		ArrayList<String> tweets = new ArrayList<String>();
		for(JitterComponent jc : children){
				tweets.addAll(jc.getTweets());
		}
		return tweets;
	}
	
	public String getID(){
		return groupID;
	}
	
	public void addChild(JitterComponent child){
		children.add(child);
	}
	
	public ArrayList<JitterComponent> getChildren(){
		return children;
	}

}
