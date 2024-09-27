package me.modify.tetris.ui.frames;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.listeners.ExitApplicationListener;
import me.modify.tetris.listeners.GameKeyInputListener;
import me.modify.tetris.ui.panel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame{

    private JPanel cardPanel;

    private ConfigurationPanel configurationPanel;
    private MainMenuPanel mainMenuPanel;
    private HighScoresPanel highScoresPanel;
    private GamePanel gamePanel;

    private GameKeyInputListener gameKeyInputListener;

    public MainFrame() {
        super("Enhanced Tetris");
        this.cardPanel = new JPanel(new CardLayout());

        this.mainMenuPanel = new MainMenuPanel();
        this.gamePanel = new GamePanel();
        this.configurationPanel = new ConfigurationPanel();
        this.highScoresPanel = new HighScoresPanel();

        this.gameKeyInputListener = new GameKeyInputListener(EnhancedTetrisApp.getInstance().getGameController());
    }

    public void init() {
        SwingUtilities.invokeLater(() -> {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            setSize(700, 500);
            setResizable(false);
            setIconImage(new ImageIcon("resources/icon.png").getImage());
            addKeyListener(gameKeyInputListener);

            cardPanel.add(mainMenuPanel, "Main_Menu_Panel");
            cardPanel.add(configurationPanel, "Configuration_Panel");
            cardPanel.add(highScoresPanel, "High_Scores_Panel");
            cardPanel.add(gamePanel, "Game_Panel");

            add(cardPanel);

            // Centers Main Frame to open in the center of the screen.
            setLocationRelativeTo(null);
            setVisible(true);
        });
    }

    public void restoreSizeDefault() {
        setSize(700, 500);
        revalidate();
        repaint();

        // Reposition frame to center
        setLocationRelativeTo(null);
    }

    public void resizeFrame(int width, int height) {
        setSize(width, height);
        revalidate();
        repaint();

        // Reposition frame to center
        setLocationRelativeTo(null);
    }

    public JPanel getCardPanel() {
        return this.cardPanel;
    }

    public GameKeyInputListener getMovementListener() {
        return gameKeyInputListener;
    }

    public GameKeyInputListener getGameKeyInputListener() {
        return gameKeyInputListener;
    }

    public ConfigurationPanel getConfigurationPanel() {
        return configurationPanel;
    }

    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    public HighScoresPanel getHighScoresPanel() {
        return highScoresPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
