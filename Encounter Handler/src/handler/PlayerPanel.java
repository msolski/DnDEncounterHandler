package handler;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/*
Player information panel, to be added onto the window.
Not a lot of info, because the players are supposed to keep track of that.
The DM needs some mystery too.*/

public class PlayerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public PlayerPanel(String name){
		super();
		this.setSize(WIDTH, HEIGHT-20);
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		this.setBackground(new Color(175, 175, 175));
		this.setVisible(true);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JTextArea nameField = new JTextArea(name);
		
		this.add(nameField, BorderLayout.WEST);
		
		System.out.println(name);
	}
}
