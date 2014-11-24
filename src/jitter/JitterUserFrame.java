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
	JTextArea nameArea, textInputArea;
	JButton subscribe, send;
	JList subscriptions, recieved;
	GridBagConstraints gbc = new GridBagConstraints();
	JScrollPane scroll;
	Dimension dim = new Dimension(20, 2);
	
	JitterUserFrame(JitterUser user){
		this.user = user;
		this.setSize(400, 200);
		setLayout(new GridBagLayout());
		nameArea = new JTextArea(1, 40);
		nameArea.setText(user.getID());
		nameArea.setEditable(false);
		gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 3;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(nameArea, gbc);
		
		subscribe = new JButton("Follow");
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		
		this.add(subscribe, gbc);
		
		ArrayList<JitterUser> subs = new ArrayList<JitterUser>();
		subs = user.getFollowing();
		ArrayList<String> names = new ArrayList<String>();
		for(JitterUser u: subs){
			names.add(u.getID());
		}
		subscriptions = new JList(names.toArray());
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		this.add(subscriptions, gbc);
		
		textInputArea = new JTextArea(4, 20);
		textInputArea.setLineWrap(true);
		textInputArea.setWrapStyleWord(true);
		scroll = new JScrollPane(textInputArea);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textInputArea.setText("Test");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		this.add(scroll, gbc);
		
		send = new JButton("Send");
		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		this.add(send, gbc);
		
		ArrayList<String> feed = new ArrayList<String>();
		Iterator<String> it = user.getNewsfeed();
		while(it.hasNext()){
			feed.add(it.next());
		}
		
		recieved = new JList(feed.toArray());
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(recieved, gbc);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		this.setVisible(true);
	}
	
	private class ListenForButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
