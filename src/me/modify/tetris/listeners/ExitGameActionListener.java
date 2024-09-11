package me.modify.tetris.listeners;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.GameController;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;
import me.modify.tetris.ui.frames.popup.PopupFrameFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitGameActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        GameController gameController = EnhancedTetrisApp.getInstance().getGameController();
        if (gameController.isGameOver()) {
            MenuFacade.openPanel(MenuType.MAIN_MENU);
            return;
        }

        if (!gameController.isPaused()) {
            gameController.pauseGame(true);
        }

        PopupFrameFactory.getPopupFrame("Stop Game").open();

    }
}
