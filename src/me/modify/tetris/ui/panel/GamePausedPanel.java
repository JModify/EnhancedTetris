package me.modify.tetris.ui.panel;

import javax.swing.*;
import java.awt.*;

public class GamePausedPanel extends JPanel {

    public GamePausedPanel() {

    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        setOpaque(false);
        setBounds(0, 0, 250, 400);
        setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

        JLabel pauseLabel = new JLabel("PAUSED");
        pauseLabel.setFont(new Font("Arial", Font.BOLD, 20));
        pauseLabel.setForeground(Color.RED);
        pauseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pauseLabel.setBounds(70, 100, 100, 50);

        JLabel unpauseLabel = new JLabel("Press p to unpause");
        unpauseLabel.setFont(new Font("Arial", Font.BOLD, 12));
        unpauseLabel.setForeground(new Color(255, 101, 101, 255));
        unpauseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        unpauseLabel.setBounds(20, 120, 200, 50);

        add(pauseLabel);
        add(unpauseLabel);
    }
}
