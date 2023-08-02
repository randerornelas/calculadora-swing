package view;

import model.Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Keyboard extends JPanel implements ActionListener {
    public Keyboard() {
        setBackground(new Color(26, 32, 49));

        final Color LIGHT_GRAY = new Color(55, 60, 77);
        final Color DARK_GRAY = new Color(46, 49, 66);
        final Color PURPLE = new Color(72, 64, 213);

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        c.gridwidth = 3;
        addButton("C", DARK_GRAY, c, 0, 0);

        c.gridwidth = 1;
        addButton("÷", DARK_GRAY, c, 3, 0);

        addButton("7", LIGHT_GRAY, c, 0, 1);
        addButton("8", LIGHT_GRAY, c, 1, 1);
        addButton("9", LIGHT_GRAY, c, 2, 1);
        addButton("x", DARK_GRAY, c, 3, 1);

        addButton("4", LIGHT_GRAY, c, 0, 2);
        addButton("5", LIGHT_GRAY, c, 1, 2);
        addButton("6", LIGHT_GRAY, c, 2, 2);
        addButton("-", DARK_GRAY, c, 3, 2);

        addButton("1", LIGHT_GRAY, c, 0, 3);
        addButton("2", LIGHT_GRAY, c, 1, 3);
        addButton("3", LIGHT_GRAY, c, 2, 3);
        addButton("+", DARK_GRAY, c, 3, 3);

        addButton("+/-", LIGHT_GRAY, c, 0, 4);
        addButton("0", LIGHT_GRAY, c, 1, 4);
        addButton(",", LIGHT_GRAY, c, 2, 4);
        addButton("=", PURPLE, c, 3, 4);
    }

    private void addButton(String text, Color color, GridBagConstraints c, int x, int y) {
        Button button = new Button(text, color);

        c.gridx = x;
        c.gridy = y;

        button.addActionListener(this);

        add(button, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        Memory.getInstance().notifyObservers(button.getText());
    }
}
