package me.modify.tetris.ui.frames;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.listeners.GameKeyInputListener;
import me.modify.tetris.ui.panel.ConfigurationPanel;
import me.modify.tetris.ui.panel.GamePanel;
import me.modify.tetris.ui.panel.HighScoresPanel;
import me.modify.tetris.ui.panel.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private JFrame frame;
    private GameKeyInputListener gameKeyInputListener;
    private JPanel allPanels;

    public MainFrame() {
        this.frame = new JFrame("Enhanced Tetris");
        this.allPanels = new JPanel(new CardLayout());
        this.gameKeyInputListener = new GameKeyInputListener(EnhancedTetrisApp.getInstance().getGameController());
    }

    public void init() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("resources/icon.png").getImage());
        frame.addKeyListener(gameKeyInputListener);

        allPanels.add(new MainMenuPanel(), "Main_Menu_Panel");
        allPanels.add(new ConfigurationPanel(), "Configuration_Panel");
        allPanels.add(new HighScoresPanel(), "High_Scores_Panel");
        allPanels.add(new GamePanel(), "Game_Panel");

        frame.add(allPanels);

        // Centers Main Frame to open in the center of the screen.
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel getAllPanels() {
        return this.allPanels;
    }

    public JFrame getJFrame() {
        return this.frame;
    }


    public GameKeyInputListener getMovementListener() {
        return gameKeyInputListener;
    }
}
