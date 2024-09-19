package me.modify.tetris.ui;

import me.modify.tetris.EnhancedTetrisApp;

import javax.swing.*;
import java.awt.*;

public class MenuFacade {
    public static void openPanel(MenuType type) {
        EnhancedTetrisApp app = EnhancedTetrisApp.getInstance();

        JPanel allPanels = app.getMainFrame().getAllPanels();
        CardLayout cl = (CardLayout) allPanels.getLayout();

        switch (type) {
            case MAIN_MENU -> {
                cl.show(allPanels, "Main_Menu_Panel");
                EnhancedTetrisApp.getInstance().getGameController().endGame();
            }
            case GAME -> {
                cl.show(allPanels, "Game_Panel");
                EnhancedTetrisApp.getInstance().getGameController().startGame();
            }
            case CONFIGURATION -> cl.show(allPanels, "Configuration_Panel");
            case HIGH_SCORES -> cl.show(allPanels, "High_Scores_Panel");
        }
    }
}
