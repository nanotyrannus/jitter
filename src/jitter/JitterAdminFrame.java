package jitter;

import javax.swing.*;
import java.awt.*;

public class JitterAdminFrame extends JFrame{

	JTree tree;
	JTextArea userField, groupField;
	JButton addUser, addGroup, userTotal, 
			groupTotal, messageTotal, positivePercent,
			viewUser;
	GridBagConstraints gbc = new GridBagConstraints();
	
	JitterAdminFrame(){
		setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		userField = new JTextArea(1, 10);
		userField.setText("Hello");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = gbc.HORIZONTAL;
		this.add(userField, gbc);
		
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
}
