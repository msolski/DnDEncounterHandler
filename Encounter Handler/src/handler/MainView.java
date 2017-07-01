package handler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
/* TODO:
 * Add more detail to NPC panel(?)
 * Add Re-ordering(?)
 */

//Main window, container of all panels
public class MainView {

	public static final short WIDTH = 600; //Width of window
	public static final short HEIGHT = 105; //Height of each panel
	public static final short BUTTONHEIGHT = 50; //Height of the button panel
	public static JPanel buttonPanel; //Panel with the buttons
	private static ArrayList<JPanel> chars; //List of characters
	private static JFrame frame; //The frame
	
	public static void main(String[] args) {
        MainView window = new MainView();
        window.frame.setSize(WIDTH, HEIGHT);
    }

	public MainView(){
		chars = new ArrayList<>();

		//Initialize main window frame and layout
		frame = new JFrame("Michael's too lazy to use a pencil and paper simulator v1.1");
		frame.setLayout(new GridLayout(1,1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Buttons
		buttonPanel = new JPanel();
		buttonPanel.setSize(WIDTH,BUTTONHEIGHT);
		buttonPanel.setLayout(new GridLayout(1,3));

		JButton newPlayerBTN = new JButton("New Player");
		JButton newNpcBTN = new JButton("New NPC");
		JButton rollInitBTN = new JButton("Roll Initiative");

		buttonPanel.add(newPlayerBTN);
		buttonPanel.add(newNpcBTN);
		buttonPanel.add(rollInitBTN);

		//Add a new player by replacing the girdLayout with a bigger one
		//There's definitely a much better way to do this but I'm lazy
		newPlayerBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				String playerName = JOptionPane.showInputDialog("Player name");

				if(playerName != null && !playerName.equals("")) {
					chars.add(new PlayerPanel(playerName));
					frame.setSize(WIDTH, HEIGHT * chars.size()+BUTTONHEIGHT);
					frame.setLayout(new GridLayout(chars.size()+1, 1));

					//Re-add all the panels
					for (int i = 0; i < chars.size(); i++)
						frame.add(chars.get(i));

					frame.revalidate();
					frame.validate();
					frame.setVisible(true);
				}
			}
		});

		newNpcBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				String npcName = JOptionPane.showInputDialog("NPC name");

				if(npcName != null && !npcName.equals("")) {
					chars.add(new EnemyPanel(npcName));
					frame.setSize(WIDTH, HEIGHT * chars.size()+BUTTONHEIGHT);
					frame.setLayout(new GridLayout(chars.size()+1, 1));

					//Re-add all the panels
					for (int i = 0; i < chars.size(); i++)
						frame.add(chars.get(i));

					frame.revalidate();
					frame.validate();
					frame.setVisible(true);
				}
			}
		});

		rollInitBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				rollInitiative();
			}
		});

		//Add everything to the frame
		frame.add(buttonPanel);
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
			frame.setSize(WIDTH, HEIGHT * chars.size()+BUTTONHEIGHT);

		frame.setLayout(new GridLayout(chars.size()+1, 1));

		//Re-add all the panels
		for (int j = 0; j < chars.size(); j++)
			frame.add(chars.get(j));

		frame.revalidate();
		frame.validate();
		frame.setVisible(true);
	}

	private void rollInitiative(){
		//Get the initiative rolls for each character
		//set/getDebugGraphicsOptions is setting/getting initiative values
		//It's like that because the arrayList is of JPanels and I'm too lazy to make it better
		for(int x=0;x<chars.size();x++){
			chars.get(x).setDebugGraphicsOptions(Integer.valueOf(JOptionPane.showInputDialog(chars.get(x).getName()+"'s roll")));
		}

		//Do a crude implementation of insertion sort
		JPanel temp;
		for(int i=1;i<chars.size();i++){
			for(int j=i;j>0;j--){
				if(chars.get(j).getDebugGraphicsOptions() > chars.get(j-1).getDebugGraphicsOptions()){
					temp = chars.get(j);
					chars.set(j,chars.get(j-1));
					chars.set(j-1,temp);
				}
			}
		}

		//Remove everything
		for(int y=0;y<chars.size();y++){
			frame.remove(chars.get(y));
		}

		//Now put them all back on the panel in the new order
		frame.setSize(WIDTH, HEIGHT * chars.size()+BUTTONHEIGHT);
		frame.setLayout(new GridLayout(chars.size()+1, 1));

		//Never forget the buttonPanel
		frame.add(buttonPanel);

		for (int z = 0; z < chars.size(); z++)
			frame.add(chars.get(z));

		frame.revalidate();
		frame.validate();
		frame.setVisible(true);

	}
}
