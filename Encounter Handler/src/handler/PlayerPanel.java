package handler;

import java.awt.Color;

import javax.swing.*;

/*
Player information panel, to be added onto the window.
Not a lot of info, because the players are supposed to keep track of that.
The DM needs some mystery too.*/

public class PlayerPanel extends JPanel {
	JPanel panel;
	
	public PlayerPanel(String name){
		super();
		this.setOpaque(true);
		this.setBackground(new Color(250,250,253));
		System.out.println(name);
	}
}
