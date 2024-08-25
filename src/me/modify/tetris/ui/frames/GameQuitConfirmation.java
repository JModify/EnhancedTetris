package me.modify.tetris.ui.frames;

import me.modify.tetris.game.GameController;
import me.modify.tetris.ui.panel.MainMenuPanel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class GameQuitConfirmation extends PopupFrame {

    private MainFrame mainFrame;
    public GameQuitConfirmation(MainFrame mainFrame) {
        super("Stop Game");
        this.mainFrame = mainFrame;
    }

    @Override
    public void paint() {
        JLabel text = new JLabel("Are you sure you want to stop the current game?");
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setBounds(0, 10, 300, 20);

        JButton yesButton = new JButton("Yes");
        yesButton.setUI(new BasicButtonUI());
        yesButton.setBounds(50, 50, 75, 40);
        yesButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        yesButton.setBackground(Color.WHITE);
        yesButton.addActionListener(e -> {
            mainFrame.openMainMenu();
            frame.dispose();
        });

        JButton noButton = new JButton("No");
        noButton.setUI(new BasicButtonUI());
        noButton.setBounds(160, 50, 75, 40);
        noButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        noButton.setBackground(Color.WHITE);
        noButton.addActionListener(e -> {

            GameController gameController = mainFrame.getTetrisApp().getGameController();
            if (gameController.isTempPause()) {
                gameController.setTempPause(false);
                gameController.unpauseGame();
            }

            frame.dispose();
            mainFrame.getJFrame().requestFocus();
        });

        frame.add(text);
        frame.add(yesButton);
        frame.add(noButton);

        frame.setVisible(true);
    }
}
