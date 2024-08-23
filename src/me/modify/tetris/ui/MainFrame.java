package me.modify.tetris.ui;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.listeners.MovementListener;
import me.modify.tetris.ui.panel.ConfigurationPanel;
import me.modify.tetris.ui.panel.GamePanel;
import me.modify.tetris.ui.panel.HighScoresPanel;
import me.modify.tetris.ui.panel.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private EnhancedTetrisApp tetrisApp;

    private JFrame frame;

    private ConfigurationPanel configurationPanel;
    private HighScoresPanel highScoresPanel;
    private MainMenuPanel mainMenuPanel;
    private GamePanel gamePanel;

    private MovementListener movementListener;

    public MainFrame(EnhancedTetrisApp tetrisApp) {
        this.frame = new JFrame("Enhanced Tetris");
        this.configurationPanel = new ConfigurationPanel(this);
        this.highScoresPanel = new HighScoresPanel(this);
        this.mainMenuPanel = new MainMenuPanel(this);
        this.tetrisApp = tetrisApp;
        this.movementListener = new MovementListener(tetrisApp.getGameController());
        this.gamePanel = new GamePanel(this);
    }

    public void createUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("resources/icon.png").getImage());
        frame.addKeyListener(movementListener);
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
        tetrisApp.getGameController().endGame();
        mainMenuPanel.paint();
    }

    public void openConfigurationPanel() {
        configurationPanel.paint();
    }

    public void openHighScoresPanel() {
        highScoresPanel.paint();
    }

    public void openGamePanel() {
        gamePanel.paint();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public MovementListener getMovementListener() {
        return movementListener;
    }
}
