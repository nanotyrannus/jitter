package jitter;

import java.awt.BorderLayout;
import java.awt.Dimension;


import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class JitterMainFrame extends JFrame{

	JPanel panel;
	JTextArea textArea;
	JTextField textField;
	JButton button;
	JScrollPane scroll;
	
	int timesClicked = 0;
	
	JitterMainFrame(){
		this.setSize(400, 200);
		setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		int x = (dim.width/2) - (this.getWidth()/2);
		int y = (dim.height/2) - (this.getHeight()/2);
		this.setLocation(x, y);
		
		
		panel = new JPanel();
		
		textArea = new JTextArea(2, 30);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText("ello");
		textArea.setEditable(true);
		
		scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		scroll.setPreferredSize(new Dimension(2, 30));
		
		button = new JButton("BUTTON");
		
		textField = new JTextField("None");
		textField.setEditable(false);
		
		//button listener
		
		ListenForButton lforbutton = new ListenForButton();
		button.addActionListener(lforbutton);
		
		ListenForKeys lforkey = new ListenForKeys();
		
		textArea.addKeyListener(lforkey);
		
		panel.add(textArea);
		panel.add(button);
		panel.add(textField);
		
		this.add(panel);
		this.setVisible(true);
	}
	
	private class ListenForButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == button){
				textField.setText("" + timesClicked);
				++timesClicked;
			}
		}
	}
	
	private class ListenForKeys implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			textField.setText(""+arg0.getKeyChar());
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
