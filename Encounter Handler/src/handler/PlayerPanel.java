package handler;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/*
Player information panel, to be added onto the window.
Not a lot of info, because the players are supposed to keep track of that.
The DM needs some mystery too.
*/

public class PlayerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String charName;

	public PlayerPanel(String name){
		super();

		this.setSize(WIDTH, HEIGHT-20);
		this.setLayout(new GridLayout(1,3));
		this.setOpaque(true);
		this.setBackground(new Color(200, 200, 250));
		this.setVisible(true);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.charName = name;
		JLabel nameField = new JLabel("\n"+name);

		JTextArea notesField1 = new JTextArea("Notes:");
		notesField1.setLineWrap(true);
		JTextArea notesField2 = new JTextArea();
		notesField2.setLineWrap(true);

		this.add(nameField);
		this.add(notesField1);
		this.add(notesField2);
	}

	@Override
	public String getName(){
		return charName;
	}
}
