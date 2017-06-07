package handler;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

//Main window, container of all panels
public class MainView {

	JFrame frame;
	
	public static void main(String[] args) {
		MainView window = new MainView();
	}
	
	public MainView(){
		//Initialize main window frame and layout
		frame = new JFrame("Michael's too lazy to use a pencil and paper simulator v0.1");
		frame.setLayout(new BorderLayout());
		
		//Filler panel
		JPanel filler = new JPanel();
		filler.setOpaque(true);
		filler.setBackground(new Color(250,250,253));
		
		//Menu bar
		JMenuBar menubar = new JMenuBar();
		JMenu newPlayerMNU = new JMenu("New Player");
		
		//Adding a new player just creates a panel with the player's name
		newPlayerMNU.addMenuListener(new MenuListener() {
		    @Override
		    public void menuSelected(MenuEvent e) {
		    	String playerName = JOptionPane.showInputDialog("Player name");
				frame.add(new PlayerPanel(playerName));
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
		frame.add(filler);
		frame.setVisible(true);
	}
}
