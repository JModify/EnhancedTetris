package me.modify.tetris.ui;

import javax.swing.*;
import java.awt.*;

public class TetrisSplashScreen extends JWindow {

    // Splash screen duration in milliseconds
    private int duration;

    public TetrisSplashScreen(int duration) {
        this.duration = duration;
    }

    public void showSplash() {
        JPanel content = (JPanel) getContentPane();

        content.setBackground(Color.white);
        int width = 510;
        int height = 510;

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        JLabel label = new JLabel(new ImageIcon("resources/splash.png"));
        content.add(label, BorderLayout.CENTER);

        Color borderColour = new Color(116, 70, 255, 255);
        content.setBorder(BorderFactory.createLineBorder(borderColour, 3));

        setVisible(true);

        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(false);
    }
}
