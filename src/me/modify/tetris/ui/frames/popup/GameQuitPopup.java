package me.modify.tetris.ui.frames.popup;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.GameController;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;
import me.modify.tetris.ui.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class GameQuitPopup extends PopupFrame {
    public GameQuitPopup() {
        super("Stop Game");
    }

    @Override
    public void paint() {
        JLabel text = new JLabel("Are you sure you want to stop the current game?");
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setBounds(0, 10, 300, 20);

        MainFrame mainFrame = EnhancedTetrisApp.getInstance().getMainFrame();

        frame.add(text);
        frame.add(getYesButton(e -> {
            MenuFacade.openPanel(MenuType.MAIN_MENU);
            frame.dispose();
        }));

        frame.add(getNoButton(e -> {
            GameController gameController = EnhancedTetrisApp.getInstance().getGameController();
            gameController.unpauseGame();

            frame.dispose();
            mainFrame.getJFrame().requestFocus();
        }));

        frame.setVisible(true);
    }
}
