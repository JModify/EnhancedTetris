package me.modify.tetris.menu;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private final JFrame mainFrame;

    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;
    public MainMenu(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void load() {
        JPanel panel = new JPanel(null);

        JLabel mainMenuTitle = new JLabel("Main Menu");
        mainMenuTitle.setBounds(100, 50, 300, 50);
        mainMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainMenuTitle.setFont(new Font("Arial", Font.BOLD, 32));

        JButton playButton = new JButton("Play");
        playButton.setBounds(100, 120, 300, 50);

        JButton configButton = new JButton("Configuration");
        configButton.setBounds(100, 180, 300, 50);

        JButton scoresButton = new JButton("High Scores");
        scoresButton.setBounds(100, 240, 300, 50);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(100, 300, 300, 50);

        JLabel appAuthorTitle = new JLabel("Author: Joshua Lavagna-Slater");
        appAuthorTitle.setBounds(100, 360, 300, 50);
        appAuthorTitle.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(mainMenuTitle);
        panel.add(playButton);
        panel.add(configButton);
        panel.add(scoresButton);
        panel.add(exitButton);
        panel.add(appAuthorTitle);

        mainFrame.add(panel);
    }


}
