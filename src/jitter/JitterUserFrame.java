package jitter;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class JitterUserFrame extends JFrame{

	
	JitterUser user;
	JTextArea nameArea, textInputArea, joinDate, lastUpdate;
	JButton subscribe, send;
	JList subscriptions, recieved;
	GridBagConstraints gbc = new GridBagConstraints();
	JScrollPane scroll, newsScroll, subScroll;
	Dimension dim = new Dimension(20, 2);
	
	JitterUserFrame(JitterUser newUser){
		ListenForButton lfb = new ListenForButton();
		
		user = newUser;
		user.setFrame(this);
		this.setSize(600, 500);
		setLayout(new GridBagLayout());
		
		this.setTitle(user.getID());
		
		nameArea = new JTextArea(1, 40);
		nameArea.setText("Follow a user...");
		nameArea.setEditable(true);
		gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 3;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(nameArea, gbc);
		
		subscribe = new JButton("Follow");
		subscribe.addActionListener(lfb);
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		
		this.add(subscribe, gbc);
		
//		ArrayList<JitterUser> subs = new ArrayList<JitterUser>();
//		subs = user.getFollowing();
//		
//		ArrayList<String> names = new ArrayList<String>();
//		for(JitterUser u: subs){
//			names.add(u.getID());
//		}
		subscriptions = new JList();
		subScroll = new JScrollPane(subscriptions);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		this.add(subScroll, gbc);
		
		textInputArea = new JTextArea(4, 20);
		textInputArea.setLineWrap(true);
		textInputArea.setWrapStyleWord(true);
		scroll = new JScrollPane(textInputArea);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textInputArea.setText("Send a tweet...");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		this.add(scroll, gbc);
		
		send = new JButton("Send");
		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		this.add(send, gbc);
		
		send.addActionListener(lfb);
		
		ArrayList<String> feed = new ArrayList<String>();
		Iterator<String> it = user.getNewsfeed();
		while(it.hasNext()){
			feed.add(it.next());
		}
		
		recieved = new JList(feed.toArray());
		newsScroll = new JScrollPane(recieved);
		newsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(newsScroll, gbc);
		
		joinDate = new JTextArea("Join date: " + user.getJoinDate());
		joinDate.setEditable(false);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		this.add(joinDate, gbc);
		
		lastUpdate = new JTextArea("Updated: " + user.getLastUpdateFormatted());
		lastUpdate.setEditable(false);
		gbc.gridx = 4;
		this.add(lastUpdate, gbc);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	private class ListenForButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == send){
				user.tweet(textInputArea.getText());
				updateFeed();
			}  if (arg0.getSource() == subscribe){
				user.subscribe(nameArea.getText());
				updateFollowing();
			}
			
		}
		
	}
	
	public void updateFeed(){
		DefaultListModel<String> model = new DefaultListModel<String>();
		Iterator<String> it = user.getNewsfeed();
		while(it.hasNext()){
			model.addElement(it.next());
		}
		recieved.setModel(model);
	}
	
	public void updateFollowing(){
		DefaultListModel<String> model = new DefaultListModel<String>();
		ArrayList<JitterUser> fol = user.getFollowing();
		for(JitterUser u : fol) {
			model.addElement(u.getID());
		}
		subscriptions.setModel(model);
	}
	
	public void updateLastUpdate(){
		lastUpdate.setText("Updated: " + user.getLastUpdateFormatted());
	}
}
