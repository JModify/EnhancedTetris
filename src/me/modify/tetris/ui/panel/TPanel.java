package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.ui.frames.MainFrame;

import javax.swing.*;

/**
 * TPanel (TetrisPanel) represents a menu's JPanel.
 */
public abstract class TPanel {

    /** Reference to the main frame for the game */
    private final MainFrame mainFrame;

    /** Panel for this TetrisPanel */
    protected JPanel panel;

    /**
     * Constructs a new TetrisPanel
     * @param mainFrame reference to main frame.
     */
    public TPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
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
        return mainFrame;
    }

    /**
     * Paints this TetrisPanel using Swing API.
     */
    public abstract void paint();

    /**
     * Method first clears the main frame by removing all elements in it's content pane.
     * Following this, the method adds this panel to the main frame, revalidates the main frame and then reprints it.
     * @param panel panel of which to update
     */
    public void update(JPanel panel) {
        getMainFrame().getJFrame().getContentPane().removeAll();

        JFrame mainFrame = getMainFrame().getJFrame();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    /**
     * Sets the JPanel of this TetrisPanel to another one.
     * @param panel other JPanel to set too
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }




}
