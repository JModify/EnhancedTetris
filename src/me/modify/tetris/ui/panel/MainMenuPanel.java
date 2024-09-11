package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;
import me.modify.tetris.ui.frames.popup.PopupFrameFactory;
import me.modify.tetris.ui.UIHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuPanel extends TetrisPanel {

    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;
    private final int BUTTON_SPACING = 25;
    private final int BUTTON_TEXT_SIZE = 20;

    @Override
    public void paint() {
        SwingUtilities.invokeLater(() ->{
            setLayout(new BorderLayout());
            panel.add(getTopPanel(), BorderLayout.NORTH);
            panel.add(getCenterPanel(), BorderLayout.CENTER);
            panel.add(getBottomPanel(), BorderLayout.SOUTH);
            updateFrame();

            EnhancedTetrisApp.getInstance().getGameController().endGame();
        });

    }

    private JPanel getTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(700, 100));
        topPanel.add(UIHelper.getLabel("Main Menu", new Font("Arial", Font.BOLD, 50)));
        return topPanel;
    }

    private JPanel getCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setPreferredSize(new Dimension(700, 300));

        centerPanel.add(getMainMenuButton("Play", e -> MenuFacade.openPanel(MenuType.GAME)));
        centerPanel.add(Box.createVerticalStrut(BUTTON_SPACING));
        centerPanel.add(getMainMenuButton("Configuration", e -> MenuFacade.openPanel(MenuType.CONFIGURATION)));
        centerPanel.add(Box.createVerticalStrut(BUTTON_SPACING));
        centerPanel.add(getMainMenuButton("High Scores", e -> MenuFacade.openPanel(MenuType.HIGH_SCORES)));
        centerPanel.add(Box.createVerticalStrut(BUTTON_SPACING));

        centerPanel.add(getMainMenuButton("Exit", e ->
                PopupFrameFactory.getPopupFrame("Exit Confirmation").open()));

        return centerPanel;
    }

    private JPanel getBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(UIHelper.getLabel("Author: Joshua Lavagna-Slater",
                new Font("Arial", Font.BOLD, 16)));
        return bottomPanel;
    }

    private JButton getMainMenuButton(String text, ActionListener actionListener) {
        return UIHelper.getButton(text, BUTTON_TEXT_SIZE,
                new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT), actionListener, 0.5F);
    }

}
