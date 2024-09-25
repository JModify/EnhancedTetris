package me.modify.tetris.ui;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.ui.panel.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MenuFacade {
    public static void openPanel(MenuType type) {
        EnhancedTetrisApp app = EnhancedTetrisApp.getInstance();

        JPanel allPanels = app.getMainFrame().getCardPanel();
        CardLayout cl = (CardLayout) allPanels.getLayout();

        if (type != MenuType.GAME) {
            EnhancedTetrisApp.getInstance().getMainFrame().restoreSizeDefault();
        }

        switch (type) {
            case MAIN_MENU -> cl.show(allPanels, "Main_Menu_Panel");
            case GAME -> {
                EnhancedTetrisApp.getInstance().getGameController().startGame();
                EnhancedTetrisApp.getInstance().getMainFrame().resizeFrame(GamePanel.getFrameWidth(),
                        GamePanel.getFrameHeight());
                cl.show(allPanels, "Game_Panel");
            }
            case CONFIGURATION -> {
                cl.show(allPanels, "Configuration_Panel");
            }
            case HIGH_SCORES -> {
                EnhancedTetrisApp.getInstance().getMainFrame().getHighScoresPanel().updateContentPane();
                cl.show(allPanels, "High_Scores_Panel");
            }
        }
    }
}
