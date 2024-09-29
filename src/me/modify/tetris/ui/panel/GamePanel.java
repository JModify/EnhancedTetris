package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.config.GameConfiguration;
import me.modify.tetris.game.GameController;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;
import me.modify.tetris.ui.UIHelper;
import me.modify.tetris.ui.panel.sub.GameBoardPanel;
import me.modify.tetris.ui.panel.sub.NextTetrominoPanel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final int CELL_SIZE = 20;
    private final int EXTRA_SPACE = 50;

    private GameBoardPanel gameBoardPanel;

    private JPanel infoPanel;
    private JPanel rightPanel;

    public GamePanel() {
        gameBoardPanel = new GameBoardPanel();
        initContentPane();
    }

    public void initContentPane() {
        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(getFrameWidth(), getFrameHeight()));

            add(UIHelper.getEmptyPanel(new Dimension(getFrameWidth(), 50), null),
                    BorderLayout.NORTH);

//            add(UIHelper.getEmptyPanel(new Dimension(225, getFrameHeight() - 100), null),
//                    BorderLayout.EAST);

            gameBoardPanel = new GameBoardPanel();
            infoPanel = getInfoPanel();
            rightPanel = getRightPanel();

            add(infoPanel, BorderLayout.WEST);
            add(rightPanel, BorderLayout.EAST);

            add(gameBoardPanel, BorderLayout.CENTER);

//            add(UIHelper.getEmptyPanel(new Dimension(225, getFrameHeight() - 100), null),
//                    BorderLayout.WEST);

            add(UIHelper.getBottomPanel(new Dimension(getFrameWidth(), 50), l -> {
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
            }), BorderLayout.SOUTH);
        });
    }

    public JPanel getInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        infoPanel.setPreferredSize(new Dimension(225, getFrameHeight() - 100));

        GameConfiguration configuration = EnhancedTetrisApp.getInstance().getConfiguration();
        GameController gameController = EnhancedTetrisApp.getInstance().getGameController();

        // Game Info Title
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        infoPanel.add(UIHelper.getLabel("Game Info", titleFont, CENTER_ALIGNMENT));

        // Game Info Fields
        Font fieldFont = new Font("Arial", Font.PLAIN, 24);
        infoPanel.add((UIHelper.getLabel("Initial Level: " + configuration.getGameLevel(),
                fieldFont, CENTER_ALIGNMENT)));
        infoPanel.add(Box.createVerticalStrut(4));
        infoPanel.add(UIHelper.getLabel("Current Level: " + gameController.getGameLevel().getLevelNum(),
                fieldFont, CENTER_ALIGNMENT));
        infoPanel.add(Box.createVerticalStrut(4));
        infoPanel.add(UIHelper.getLabel("Score: " + gameController.getScore(),
                fieldFont, CENTER_ALIGNMENT));
        infoPanel.add(Box.createVerticalStrut(4));
        infoPanel.add(UIHelper.getLabel("Lines Erased: " + gameController.getRowsErased(),
                fieldFont, CENTER_ALIGNMENT));

        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(UIHelper.getLabel("Next Tetromino", titleFont, CENTER_ALIGNMENT));
        NextTetrominoPanel nextTetrominoPanel = new NextTetrominoPanel(gameController.getNextTetromino());
        nextTetrominoPanel.setAlignmentX(CENTER_ALIGNMENT);
        infoPanel.add(nextTetrominoPanel);

        //add(infoPanel, BorderLayout.WEST);
        return infoPanel;
    }

    public JPanel getRightPanel() {
        JPanel rightpanel = new JPanel();
        rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.PAGE_AXIS));
        rightpanel.setPreferredSize(new Dimension(225, getFrameHeight() - 100));

        GameConfiguration configuration = EnhancedTetrisApp.getInstance().getConfiguration();

        Font titleFont = new Font("Arial", Font.BOLD, 24);
        rightpanel.add(UIHelper.getLabel("Sound", titleFont, CENTER_ALIGNMENT));

        Font fieldFont = new Font("Arial", Font.PLAIN, 24);
        rightpanel.add((UIHelper.getLabel("Music: " + (configuration.isMusic() ? "ON" : "OFF"),
                fieldFont, CENTER_ALIGNMENT)));
        rightpanel.add(Box.createVerticalStrut(4));
        rightpanel.add((UIHelper.getLabel("Effects: " + (configuration.isSoundEffects() ? "ON" : "OFF"),
                fieldFont, CENTER_ALIGNMENT)));
        //add(rightpanel, BorderLayout.EAST);
        return rightpanel;
    }

    /**
     * Calculates the required frame width in pixels using the following algorithm:
     * (Configured Field Width * Cell Size) + (Left and Right Panel Width) + Extra Space + random offset?
     *
     * @return required frame width for the game.
     */
    public static int getFrameWidth() {
        GameConfiguration configuration = EnhancedTetrisApp.getInstance().getGameController().getConfiguration();
        return (configuration.getFieldWidth() * 20) + (225 * 2) + 15;
    }

    /**
     * Calculates the required frame height in pixels using the following algorithm:
     * (Configured Field Height * Cell Size) + (Top and Bottom Panel Height) + Extra Space + random offset?
     *
     * @return required frame height for the game.
     */
    public static int getFrameHeight() {
        GameConfiguration configuration = EnhancedTetrisApp.getInstance().getGameController().getConfiguration();
        return (configuration.getFieldHeight() * 20) + (50 * 2) + 38;
    }

    public GameBoardPanel getGameBoardPanel() {
        return gameBoardPanel;
    }

    public void updateInfoPanels() {
        SwingUtilities.invokeLater(() -> {
            remove(infoPanel);
            remove(rightPanel);

            infoPanel = getInfoPanel();
            rightPanel = getRightPanel();

            add(infoPanel, BorderLayout.WEST);
            add(rightPanel, BorderLayout.EAST);

            revalidate();
            repaint();
        });
    }
}
