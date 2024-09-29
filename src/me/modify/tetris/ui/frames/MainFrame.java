package me.modify.tetris.ui.frames;

import me.modify.tetris.listeners.ExitApplicationListener;
import me.modify.tetris.listeners.GameKeyInputListener;
import me.modify.tetris.ui.panel.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    /** Card panel containing all main panels */
    private final JPanel cardPanel;

    /* References to all main panels */
    private final ConfigurationPanel configurationPanel;
    private final MainMenuPanel mainMenuPanel;
    private final HighScoresPanel highScoresPanel;
    private final GamePanel gamePanel;

    /** Game key input listener reference */
    private final GameKeyInputListener gameKeyInputListener;

    public MainFrame() {
        super("Enhanced Tetris");
        this.cardPanel = new JPanel(new CardLayout());

        this.mainMenuPanel = new MainMenuPanel();
        this.gamePanel = new GamePanel();
        this.configurationPanel = new ConfigurationPanel();
        this.highScoresPanel = new HighScoresPanel();

        this.gameKeyInputListener = new GameKeyInputListener();
    }

    /**
     * Initializes main frame and all panels. Also sets other important properties for the main frame.
     */
    public void init() {
        SwingUtilities.invokeLater(() -> {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            addWindowListener(new ExitApplicationListener());

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

    /**
     * Restores the main frame to it's default sizing.
     */
    public void restoreSizeDefault() {
        setSize(700, 500);
        revalidate();
        repaint();

        // Reposition frame to center
        setLocationRelativeTo(null);
    }

    /**
     * Resizes the main frame to the desired size.
     * Used for game panels of different sized game boards.
     * @param width width to set frame too.
     * @param height height to set frame too.
     */
    public void resizeFrame(int width, int height) {
        setSize(width, height);
        revalidate();
        repaint();

        // Reposition frame to center
        setLocationRelativeTo(null);
    }

    /**
     * Retrieves the main frame's card panel.
     * @return the card panel
     */
    public JPanel getCardPanel() {
        return this.cardPanel;
    }

    /**
     * Retrieves the key input listener for the frame.
     * @return the movement listener.
     */
    public GameKeyInputListener getGameInputListener() {
        return gameKeyInputListener;
    }

    /**
     * Retrieves the game's high score panel.
     * @return the high score panel.
     */
    public HighScoresPanel getHighScoresPanel() {
        return highScoresPanel;
    }

    /**
     * Retrieves the tetris game panel reference.
     * @return the game panel reference.
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
