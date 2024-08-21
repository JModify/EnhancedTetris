package me.modify.tetris.ui.panel;

import me.modify.tetris.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends TPanel {

    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;
    private final int BUTTON_X_OFFSET = 200;

    public MainMenuPanel(MainFrame mainFrame) {
        super(mainFrame);
    }

    @Override
    public void paint() {
        JLabel mainMenuTitle = new JLabel("Main Menu");
        mainMenuTitle.setBounds(200, 50, 300, 100);
        mainMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainMenuTitle.setFont(new Font("Arial", Font.BOLD, 32));

        JButton playButton = new JButton("Play");
        formatMenuButton(120, playButton);
        playButton.addActionListener(e -> {
            GamePanel gamePanel = new GamePanel(getMainFrame());
            gamePanel.paint();
        });

        JButton configButton = new JButton("Configuration");
        formatMenuButton(180, configButton);
        configButton.addActionListener(e -> {
            getMainFrame().openConfigurationMenu();
        });

        JButton scoresButton = new JButton("High Scores");
        formatMenuButton(240, scoresButton);
        scoresButton.addActionListener(e -> getMainFrame().openHighScoresMenu());

        JButton exitButton = new JButton("Exit");
        formatMenuButton(300, exitButton);
        exitButton.addActionListener(e -> System.exit(0));

        JLabel appAuthorTitle = new JLabel("Author: Joshua Lavagna-Slater");
        appAuthorTitle.setBounds(200, 360, 300, 50);
        appAuthorTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainMenuTitle.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(mainMenuTitle);
        panel.add(playButton);
        panel.add(configButton);
        panel.add(scoresButton);
        panel.add(exitButton);
        panel.add(appAuthorTitle);

        update(panel);
    }

    private void formatMenuButton(int offsetY, JButton button) {
        button.setFocusable(false);
        button.setBounds(BUTTON_X_OFFSET, offsetY, BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setBackground(new Color(139, 207, 253, 255));
        button.setFont(new Font("Arial", Font.PLAIN, 24));
    }


}
