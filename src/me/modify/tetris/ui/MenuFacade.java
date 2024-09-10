package me.modify.tetris.ui;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.ui.frames.MainFrame;

public class MenuFacade {
    public static void openPanel(MenuType type) {
        EnhancedTetrisApp app = EnhancedTetrisApp.getInstance();

        switch (type) {
            case MAIN_MENU -> app.getMainMenuPanel().paint();
            case GAME -> app.getGamePanel().paint();
            case CONFIGURATION -> app.getConfigurationPanel().paint();
            case HIGH_SCORES -> app.getHighScoresPanel().paint();
        }
    }
}
