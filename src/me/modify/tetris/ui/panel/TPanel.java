package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public abstract class TPanel {

    private final MainFrame mainFrame;
    protected JPanel panel;

    public TPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.panel = new JPanel(null);
    }

    public GameConfiguration getConfiguration() {
        return mainFrame.getTetrisApp().getGameController().getConfiguration();
    }

    public EnhancedTetrisApp getApp() {
        return mainFrame.getTetrisApp();
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public abstract void paint();

    private void close() {
        getMainFrame().getJFrame().getContentPane().removeAll();
    }

    public void update(JPanel panel) {
        close();

        JFrame mainFrame = getMainFrame().getJFrame();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }




}
