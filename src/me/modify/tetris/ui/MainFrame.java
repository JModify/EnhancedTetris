package me.modify.tetris.ui;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.ui.panel.ConfigurationPanel;
import me.modify.tetris.ui.panel.HighScoresPanel;
import me.modify.tetris.ui.panel.MainMenuPanel;

import javax.swing.*;

public class MainFrame {

    private EnhancedTetrisApp tetrisApp;

    private JFrame frame;
    private ConfigurationPanel configurationPanel;
    private HighScoresPanel highScoresPanel;
    private MainMenuPanel mainMenuPanel;

    public MainFrame(EnhancedTetrisApp tetrisApp) {
        this.frame = new JFrame("Enhanced Tetris");
        this.configurationPanel = new ConfigurationPanel(this);
        this.highScoresPanel = new HighScoresPanel(this);
        this.mainMenuPanel = new MainMenuPanel(this);
        this.tetrisApp = tetrisApp;
    }

    public void createUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("resources/icon.png").getImage());

        // Centers Main Frame to open in the center of the screen.
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public EnhancedTetrisApp getTetrisApp() {
        return this.tetrisApp;
    }

    public JFrame getJFrame() {
        return this.frame;
    }

    public void openMainMenu() {
        mainMenuPanel.paint();
    }

    public void openConfigurationMenu() {
        configurationPanel.paint();
    }

    public void openHighScoresMenu() {
        highScoresPanel.paint();
    }


}
