package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.ui.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * TetrisPanel represents a menu's JPanel.
 */
public abstract class TetrisPanel extends JPanel {

    /**
     * Retrieves the game's configuration.
     * @return the game configuration
     */
    public GameConfiguration getConfiguration() {
        return EnhancedTetrisApp.getInstance().getGameController().getConfiguration();
    }

    public abstract void init();

    /**
     * Retrieves the main frame reference.
     * @return main frame reference.
     */
    public MainFrame getMainFrame() {
        return EnhancedTetrisApp.getInstance().getMainFrame();
    }

    /**
     * Method first clears the main frame by removing all elements in its content pane.
     * Following this, the method adds this panel to the main frame, revalidates the main frame and then reprints it.
     */
    public void updateFrame() {
        getMainFrame().getJFrame().getContentPane().removeAll();

        JFrame mainFrame = getMainFrame().getJFrame();
        mainFrame.add(this);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
