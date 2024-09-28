package me.modify.tetris.ui;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.ui.frames.MainFrame;
import me.modify.tetris.ui.panel.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MenuFacade {
    public static void openPanel(MenuType type) {
        EnhancedTetrisApp app = EnhancedTetrisApp.getInstance();
        MainFrame mainFrame = app.getMainFrame();

        JPanel allPanels = mainFrame.getCardPanel();
        CardLayout cl = (CardLayout) allPanels.getLayout();

        if (type != MenuType.GAME) {
            mainFrame.restoreSizeDefault();
        }

        switch (type) {
            case MAIN_MENU -> cl.show(allPanels, "Main_Menu_Panel");
            case GAME -> {

                app.getGameController().startGame();
                mainFrame.resizeFrame(GamePanel.getFrameWidth(),
                        GamePanel.getFrameHeight());

                if (app.getConfiguration().isMusic()) {
                    app.getMusicPlayer().start();
                }

                cl.show(allPanels, "Game_Panel");
            }
            case CONFIGURATION -> {
                cl.show(allPanels, "Configuration_Panel");
            }
            case HIGH_SCORES -> {
                mainFrame.getHighScoresPanel().updateHighScores();
                cl.show(allPanels, "High_Scores_Panel");
            }
        }
    }
}
