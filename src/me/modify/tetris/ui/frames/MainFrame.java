package me.modify.tetris.ui.frames;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.listeners.GameKeyInputListener;
import me.modify.tetris.ui.panel.*;

import javax.swing.*;

public class MainFrame {

    private JFrame frame;

    private GameKeyInputListener gameKeyInputListener;

    public MainFrame() {
        this.frame = new JFrame("Enhanced Tetris");
        this.gameKeyInputListener = new GameKeyInputListener(EnhancedTetrisApp.getInstance().getGameController());
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


    public GameKeyInputListener getMovementListener() {
        return gameKeyInputListener;
    }
}
