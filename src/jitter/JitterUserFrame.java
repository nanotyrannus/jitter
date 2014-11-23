package jitter;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class JitterUserFrame extends JFrame{

	JTextArea nameArea, textInputArea;
	JButton subscribe, send;
	JList subscriptions, recieved;
	GridBagConstraints gbc = new GridBagConstraints();
	JScrollPane scroll;
	Dimension dim = new Dimension(20, 2);
	
	JitterUserFrame(){
		this.setSize(400, 200);
		setLayout(new GridBagLayout());
		nameArea = new JTextArea(1, 40);
		nameArea.setText("Sample Name");
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
		
		Object[] arr0 = {"User1","User2","User3"};
		subscriptions = new JList(arr0);
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
		
		
		Object[] arr1 = {"Message1","Message2","Message3"};
		recieved = new JList(arr1);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(recieved, gbc);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		this.setVisible(true);
	}
}
