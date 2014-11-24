package jitter;

import java.util.ArrayList;

public interface JitterComponent {
	
	public ArrayList<String> getTweets();
	public String getID();
	public void addChild(JitterComponent child);
}
