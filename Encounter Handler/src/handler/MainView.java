package handler;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
/* TODO:
 * Add delete button (only for NPCs?)
 * Add roll-initiative thing
 * Turn the menu into buttons
 * Add more detail to NPC panel(?)
 * Add Re-ordering(?)
 */

//Main window, container of all panels
public class MainView {

	public static final short WIDTH = 600; //Width of window
	public static final short HEIGHT = 105; //Height of each panel
	private static ArrayList<JPanel> chars; //List of characters
	private static JFrame frame; //The frame
	
	public static void main(String[] args) {
        MainView window = new MainView();
        window.frame.setSize(WIDTH, HEIGHT);
    }

	public MainView(){
		chars = new ArrayList<>();

		//Initialize main window frame and layout
		frame = new JFrame("Michael's too lazy to use a pencil and paper simulator v0.5");
		frame.setLayout(new GridLayout(1,1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		JMenu newPlayerMNU = new JMenu("New Player");
		JMenu newNpcMNU = new JMenu("New NPC");
		JMenu rollInitMNU = new JMenu("Roll Initiative");
		
		//Add a new player by replacing the girdLayout with a bigger one
		//There's definitely a much better way to do this but I'm lazy
		newPlayerMNU.addMenuListener(new MenuListener() {
		    @Override
		    public void menuSelected(MenuEvent e) {
		    	String playerName = JOptionPane.showInputDialog("Player name");

		    	if(playerName != null && !playerName.equals("")) {
					chars.add(new PlayerPanel(playerName));
					frame.setSize(WIDTH, HEIGHT * chars.size());
					frame.setLayout(new GridLayout(chars.size(), 1));

					//Re-add all the panels
					for (int i = 0; i < chars.size(); i++)
						frame.add(chars.get(i));

					frame.revalidate();
					frame.validate();
					frame.setVisible(true);
				}
		    }
		    //Unused
		    @Override
		    public void menuDeselected(MenuEvent e) {}
		    @Override
		    public void menuCanceled(MenuEvent e) {}
		});

		//Do the same thing with NPCs
		newNpcMNU.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				String npcName = JOptionPane.showInputDialog("NPC name");

				if(npcName != null && !npcName.equals("")) {
					chars.add(new EnemyPanel(npcName));
					frame.setSize(WIDTH, HEIGHT * chars.size());
					frame.setLayout(new GridLayout(chars.size(), 1));

					//Re-add all the panels
					for (int i = 0; i < chars.size(); i++)
						frame.add(chars.get(i));

					frame.revalidate();
					frame.validate();
					frame.setVisible(true);
				}
			}
			//Unused
			@Override
			public void menuDeselected(MenuEvent e) {}
			@Override
			public void menuCanceled(MenuEvent e) {}
		});

		rollInitMNU.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				rollInitiative();
			}
			//Unused
			@Override
			public void menuDeselected(MenuEvent e) {}
			@Override
			public void menuCanceled(MenuEvent e) {}
		});

		menuBar.add(newPlayerMNU);
		menuBar.add(newNpcMNU);
		menuBar.add(rollInitMNU);
		
		//Add everything to the frame
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}

	public static void removeChar(String name){
		for(int i=0;i<chars.size();i++) {
			if (chars.get(i).getName().equals(name)) {
				frame.remove(chars.get(i));
				chars.remove(i);
			}
		}

		if(chars.size() > 0)
			frame.setSize(WIDTH, HEIGHT * chars.size());

		frame.setLayout(new GridLayout(chars.size(), 1));

		//Re-add all the panels
		for (int j = 0; j < chars.size(); j++)
			frame.add(chars.get(j));

		frame.revalidate();
		frame.validate();
		frame.setVisible(true);
	}

	// TODO: FIX!!!!!!!!!!!
	private void rollInitiative(){
		int[] initArray = new int[chars.size()];

		//Get the initiative rolls for each character
		for(int i=0;i<chars.size();i++){
			initArray[i] = Integer.valueOf(JOptionPane.showInputDialog(chars.get(i).getName()+"'s roll"));
		}

		//Do a crude implementation of insertion(?) sort
		int tempInt;
		JPanel tempPanel;
		for(int i=1;i<chars.size();i++){
			for(int j=1;j>0;j--){

				if(initArray[j]<initArray[j-1]){
					tempPanel = chars.get(j);
					tempInt = initArray[j];

					chars.add(j, chars.get(j-1));
					initArray[j] = initArray[j-1];

					chars.add(j-1, tempPanel);
					initArray[j-1] = tempInt;
				}
			}
		}

		//Now put them all back on the panel in the new order
		frame.setSize(WIDTH, HEIGHT * chars.size());
		frame.setLayout(new GridLayout(chars.size(), 1));

		for (int i = 0; i < chars.size(); i++)
			frame.add(chars.get(i));

		frame.revalidate();
		frame.validate();
		frame.setVisible(true);
	}
}
