package me.modify.tetris.listeners;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.GameController;
import me.modify.tetris.ui.frames.GameQuitConfirmation;
import me.modify.tetris.ui.frames.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitGameActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        GameController gameController = EnhancedTetrisApp.getInstance().getGameController();
        MainFrame mainFrame = EnhancedTetrisApp.getInstance().getMainFrame();
        if (gameController.isGameOver()) {
            mainFrame.openMainMenu();
            return;
        }

        if (!gameController.isPaused()) {
            gameController.setTempPause(true);
            gameController.pauseGame();
        }

        GameQuitConfirmation gameQuitConfirmation = new GameQuitConfirmation(mainFrame);
        gameQuitConfirmation.open();
    }
}
