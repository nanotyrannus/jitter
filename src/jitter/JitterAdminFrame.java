package jitter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class JitterAdminFrame extends JFrame{
	
	private static JitterAdminFrame theFrame = null;

	Database db;
	JTree tree;
	JTextArea userField, groupField, lastUpdatedUser;
	JButton addUser, addGroup, userTotal, 
			groupTotal, messageTotal, positivePercent,
			viewUser, lastUpdate, validateNamespace;
	GridBagConstraints gbc = new GridBagConstraints();
	
	public static JitterAdminFrame getInstance(Database db){
		if(theFrame == null){
			theFrame = new JitterAdminFrame(db);
		}
		return theFrame;
	}
	
	private JitterAdminFrame(Database database){
		db = database;
		setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		userField = new JTextArea(1, 10);
		userField.setText("Add a user!");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = gbc.HORIZONTAL;
		this.add(userField, gbc);
		
		addUser = new JButton("ADD USER");
		gbc.gridx = 4;
		gbc.gridy = 0;
		this.add(addUser, gbc);
		
		groupField = new JTextArea(1, 10);
		groupField.setText("Add a group!");
		gbc.gridx = 2;
		gbc.gridy = 1;
		this.add(groupField, gbc);
		
		addGroup = new JButton("ADD GROUP");
		gbc.gridx = 4;
		gbc.gridy = 1;
		this.add(addGroup, gbc);
		
		viewUser = new JButton("VIEW HIGHLIGHTED USER");
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		this.add(viewUser, gbc);
		
		userTotal = new JButton("TOTAL USER COUNT: N/A");
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		this.add(userTotal, gbc);
		ListenForCount lfc = new ListenForCount();
		userTotal.addActionListener(lfc);
		
		groupTotal = new JButton("TOTAL GROUP COUNT");
		gbc.gridx = 4;
		this.add(groupTotal, gbc);
		groupTotal.addActionListener(lfc);
		
		messageTotal = new JButton("MESSAGE COUNT");
		gbc.gridy = 5;
		gbc.gridx = 2;
		this.add(messageTotal, gbc);
		messageTotal.addActionListener(lfc);
		
		positivePercent = new JButton("HAPPINESS FACTOR");
		gbc.gridx = 4;
		this.add(positivePercent, gbc);
		
		lastUpdatedUser = new JTextArea("Last update by: " + lastUpdatedUser().getID());
		gbc.gridx = 0;
		gbc.gridy = 6;
		this.add(lastUpdatedUser, gbc);
		ListenForButton lfb = new ListenForButton();

		
		lastUpdate = new JButton("Check last update");
		gbc.gridx = 2;
		this.add(lastUpdate, gbc);
		lastUpdate.addActionListener(lfb);
		
		validateNamespace = new JButton("Validate Namespace");
		gbc.gridx = 5;
		this.add(validateNamespace, gbc);
		
		tree = new JTree();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 6;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(tree, gbc);
		
		
		
		pack();
		this.setVisible(true);
	}
	
	public JitterUser lastUpdatedUser(){
		JitterUser lastUser, cursor;
		Iterator<JitterUser> it = db.getList();
		if(it.hasNext()) {
			lastUser = it.next();
		} else {
			return null;
		}
		while(it.hasNext()){
			cursor = it.next();
			if(lastUser.getLastUpdateMillis() < cursor.getLastUpdateMillis()){
				lastUser = cursor;
			}
		}
		return lastUser;
	}
	
	private class ListenForCount implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int count = 0;
			if(arg0.getSource() == userTotal){
				Iterator it = db.getList();
				while(it.hasNext()) {
					++count;
					it.next();
				}
				userTotal.setText("TOTAL USER COUNT: " + count);
			} else if(arg0.getSource() == groupTotal){
				count = db.getGroups(db.getRoot()).size();
				groupTotal.setText("TOTAL GROUP COUNT: " + count);
			} else if(arg0.getSource() == messageTotal){
				Iterator<JitterUser> it = db.getList();
				while(it.hasNext()){
					count += it.next().getTweets().size();
				}
				messageTotal.setText("MESSAGE TOTAL: " + count);
			}
		}
	}
	
	private class ListenForButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == lastUpdate){
				updateLastUpdate();
			}
		}
		
	}
	
	public void updateLastUpdate(){
		lastUpdatedUser.setText("Last update by: " + lastUpdatedUser().getID());
	}
	
}
