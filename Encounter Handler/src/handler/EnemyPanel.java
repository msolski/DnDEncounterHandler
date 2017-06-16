package handler;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/*
NPC information panel, to be added onto the window.
Covers the name, HP, AC and other notes the DM wants to add like conditions or whatever.
*/
public class EnemyPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public EnemyPanel(String name){
        super();

        this.setSize(WIDTH, HEIGHT-20);
        this.setLayout(new GridLayout(1,3));
        this.setOpaque(true);
        this.setBackground(new Color(255, 150, 110));
        this.setVisible(true);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        JTextField nameField = new JTextField("\n"+name);
        nameField.setEditable(false);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(2,2));

        JTextField hpText = new JTextField("HP");
        hpText.setEditable(false);
        JTextField hpField = new JTextField();

        JTextField acText = new JTextField("AC");
        acText.setEditable(false);
        JTextField acField = new JTextField();

        statsPanel.add(hpText);
        statsPanel.add(acText);
        statsPanel.add(hpField);
        statsPanel.add(acField);

        JTextArea notesField = new JTextArea("Conditions/Resistances:");
        notesField.setLineWrap(true);

        this.add(nameField);
        this.add(statsPanel);
        this.add(notesField);
    }
}
