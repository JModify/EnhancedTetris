package me.modify.tetris.listeners;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.GameController;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitGameListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GameController gameController = EnhancedTetrisApp.getInstance().getGameController();
        if (gameController.isGameOver()) {
            EnhancedTetrisApp.getInstance().getMusicPlayer().stop();
            MenuFacade.openPanel(MenuType.MAIN_MENU);
            return;
        }

        if (!gameController.isPaused()) {
            gameController.pauseGame(true);
        }

        int exitGamePopup = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to stop the current game?",
                "Quit Game",
                JOptionPane.YES_NO_OPTION);

        if (exitGamePopup == JOptionPane.YES_OPTION) {
            gameController.endGame();
            EnhancedTetrisApp.getInstance().getMainFrame().restoreSizeDefault();
            EnhancedTetrisApp.getInstance().getMusicPlayer().stop();
            MenuFacade.openPanel(MenuType.MAIN_MENU);
        } else if (exitGamePopup == JOptionPane.NO_OPTION) {
            gameController.unpauseGame();
            EnhancedTetrisApp.getInstance().getMainFrame().requestFocus();
        }
    }
}
