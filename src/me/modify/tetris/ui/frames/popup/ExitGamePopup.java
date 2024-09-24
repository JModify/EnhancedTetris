package me.modify.tetris.ui.frames.popup;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.GameController;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;
import me.modify.tetris.ui.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ExitGamePopup extends PopupFrame {
    public ExitGamePopup() {
        super("Stop Game");
    }

    @Override
    public void init() {
        SwingUtilities.invokeLater(() -> {

            MainFrame mainFrame = EnhancedTetrisApp.getInstance().getMainFrame();
            GameController gameController = EnhancedTetrisApp.getInstance().getGameController();

            JLabel text = new JLabel("Are you sure you want to stop the current game?");
            text.setAlignmentX(Component.CENTER_ALIGNMENT);
            text.setHorizontalAlignment(SwingConstants.CENTER);
            text.setBounds(0, 10, 300, 20);

            add(text);
            add(getYesButton(e -> {
                gameController.endGame();
                mainFrame.restoreSizeDefault();
                MenuFacade.openPanel(MenuType.MAIN_MENU);
                dispose();
            }));

            add(getNoButton(e -> {
                gameController.unpauseGame();

                dispose();
                mainFrame.requestFocus();
            }));

            setVisible(true);
        });
    }
}
