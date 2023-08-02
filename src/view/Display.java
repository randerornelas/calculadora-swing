package view;

import model.Memory;
import model.MemoryObserver;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel implements MemoryObserver {
    private final JLabel label = new JLabel(Memory.getInstance().getCurrentText());

    public Display() {
        Memory.getInstance().registerObserver(this);

        label.setForeground(Color.WHITE);
        label.setFont(new Font("courier", Font.PLAIN, 36));

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 35));
        setBackground(new Color(26, 32, 49));
        setPreferredSize(new Dimension(290, 100));

        add(label);
    }

    @Override
    public void update(String newValue) {
        label.setText(newValue);
    }
}
