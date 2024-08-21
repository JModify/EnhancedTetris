package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.ui.MainFrame;

import javax.swing.*;

public abstract class TPanel {

    private MainFrame mainFrame;

    public TPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public GameConfiguration getConfiguration() {
        return mainFrame.getTetrisApp().getGameController().getConfiguration();
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public EnhancedTetrisApp getApp() {
        return mainFrame.getTetrisApp();
    }

    public abstract void load();

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

}
