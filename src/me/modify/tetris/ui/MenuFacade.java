package me.modify.tetris.ui;

import me.modify.tetris.EnhancedTetrisApp;

public class MenuFacade {
    public static void openPanel(MenuType type) {
        EnhancedTetrisApp app = EnhancedTetrisApp.getInstance();

        switch (type) {
            case MAIN_MENU -> app.getMainMenuPanel().init();
            case GAME -> app.getGamePanel().init();
            case CONFIGURATION -> app.getConfigurationPanel().init();
            case HIGH_SCORES -> app.getHighScoresPanel().init();
        }
    }
}
