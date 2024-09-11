package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.ui.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * TetrisPanel represents a menu's JPanel.
 */
public abstract class TetrisPanel {

    /** Panel for this TetrisPanel */
    protected JPanel panel;

    /**
     * Constructs a new TetrisPanel
     */
    public TetrisPanel() {
        this.panel = new JPanel(null);
    }

    /**
     * Retrieves the game's configuration.
     * @return the game configuration
     */
    public GameConfiguration getConfiguration() {
        return EnhancedTetrisApp.getInstance().getGameController().getConfiguration();
    }

    /**
     * Retrieves the main frame reference.
     * @return main frame reference.
     */
    public MainFrame getMainFrame() {
        return EnhancedTetrisApp.getInstance().getMainFrame();
    }

    /**
     * Paints this TetrisPanel using Swing API.
     */
    public abstract void paint();

    /**
     * Method first clears the main frame by removing all elements in its content pane.
     * Following this, the method adds this panel to the main frame, revalidates the main frame and then reprints it.
     */
    public void updateFrame() {
        getMainFrame().getJFrame().getContentPane().removeAll();

        JFrame mainFrame = getMainFrame().getJFrame();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    /**
     * Sets the layout for this JPanel to something else.
     * @param layout layout to set too
     */
    public void setLayout(LayoutManager layout) {
        this.panel.setLayout(layout);
    }
}
