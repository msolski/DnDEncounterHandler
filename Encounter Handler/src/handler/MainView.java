package handler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

//Main window, container of all panels
public class MainView {

	public static final short WIDTH = 650; //Width of window 
	public static final short HEIGHT = 150; //Height of each panel
	private static int amtChars = 0; //Number of characters
	
	JFrame frame;
	
	public static void main(String[] args) {
        MainView window = new MainView();
        window.frame.setSize(WIDTH, HEIGHT);
    }

	public MainView(){
		//Initialize main window frame and layout
		frame = new JFrame("Michael's too lazy to use a pencil and paper simulator v0.1");
		frame.setLayout(new BorderLayout());
		
		//Menu bar - TODO: Maybe make these not on a menu? Kinda finicky
		JMenuBar menubar = new JMenuBar();
		JMenu newPlayerMNU = new JMenu("New Player");
		
		//Adding a new player just creates a panel with the player's name
		//How about making a gridlayout with adding a new row with every thing?
		newPlayerMNU.addMenuListener(new MenuListener() {
		    @Override
		    public void menuSelected(MenuEvent e) {
		    	amtChars++;
		    	String playerName = JOptionPane.showInputDialog("Player name");
		    	frame.setSize(WIDTH, HEIGHT*amtChars);
				frame.add(new PlayerPanel(playerName));
				frame.revalidate();
				frame.validate();
				frame.setVisible(true);
				System.out.println(frame.getHeight());
		    }
		    //Unused
		    @Override
		    public void menuDeselected(MenuEvent e) {}
		    @Override
		    public void menuCanceled(MenuEvent e) {}
		});
		
		JMenu newNpcMNU = new JMenu("New NPC");
		
		menubar.add(newPlayerMNU);
		menubar.add(newNpcMNU);
		
		//Add everything to the frame
		frame.setJMenuBar(menubar);
		frame.setVisible(true);
	}
}
