package me.modify.tetris.ui.frames;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.listeners.GameKeyInputListener;
import me.modify.tetris.ui.panel.*;

import javax.swing.*;

public class MainFrame {

    private JFrame frame;

    private ConfigurationPanel configurationPanel;
    private HighScoresPanel highScoresPanel;
    private MainMenuPanel mainMenuPanel;
    private GamePanel gamePanel;

    private GameKeyInputListener gameKeyInputListener;


    public MainFrame() {
        this.frame = new JFrame("Enhanced Tetris");
        this.configurationPanel = new ConfigurationPanel();
        this.highScoresPanel = new HighScoresPanel();
        this.mainMenuPanel = new MainMenuPanel();
        this.gameKeyInputListener = new GameKeyInputListener(EnhancedTetrisApp.getInstance().getGameController());
        this.gamePanel = new GamePanel();
    }

    public void createUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("resources/icon.png").getImage());
        frame.addKeyListener(gameKeyInputListener);

        // Centers Main Frame to open in the center of the screen.
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JFrame getJFrame() {
        return this.frame;
    }

    public void openMainMenu() {
        EnhancedTetrisApp.getInstance().getGameController().endGame();
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

    public GameKeyInputListener getMovementListener() {
        return gameKeyInputListener;
    }
}
