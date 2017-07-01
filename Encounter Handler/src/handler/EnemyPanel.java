package handler;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
NPC information panel, to be added onto the window.
Covers the name, HP, AC and other notes the DM wants to add like conditions or whatever.
*/
public class EnemyPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private String charName;
    private int initiative;

    public EnemyPanel(String name){
        super();

        this.setSize(WIDTH, HEIGHT-20);
        this.setLayout(new GridLayout(1,3));
        this.setOpaque(true);
        this.setBackground(new Color(250, 150, 120));
        this.setVisible(true);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.initiative = 0;

        //Name and delete button
        this.charName = name;

        JButton deleteButton = new JButton("X");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainView.removeChar(charName);
            }
        });

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(3,1));

        JLabel nameField = new JLabel(name);

        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(new GridLayout(1,3));
        deletePanel.add(new JPanel());
        deletePanel.add(new JPanel());
        deletePanel.add(deleteButton);

        namePanel.add(new JPanel());
        namePanel.add(nameField);
        namePanel.add(deletePanel);

        //Stats and notes
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(2,2));

        JLabel hpText = new JLabel("HP");
        JTextField hpField = new JTextField();

        JLabel acText = new JLabel("AC");
        JTextField acField = new JTextField();

        statsPanel.add(hpText);
        statsPanel.add(acText);
        statsPanel.add(hpField);
        statsPanel.add(acField);

        JTextArea notesField = new JTextArea("Conditions/Resistances:");
        notesField.setLineWrap(true);

        this.add(namePanel);
        this.add(statsPanel);
        this.add(notesField);
    }

    //Overriding shit because the arraylist is with an ancestor class
    @Override
    public String getName(){
        return charName;
    }

    //I really shouldn't be doing this
    @Override
    public int getDebugGraphicsOptions() {return initiative;}
    @Override
    public void setDebugGraphicsOptions(int i) {initiative = i;}
}
