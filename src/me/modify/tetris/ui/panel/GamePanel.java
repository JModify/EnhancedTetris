package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.config.GameConfiguration;
import me.modify.tetris.game.GameController;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;
import me.modify.tetris.ui.UIHelper;
import me.modify.tetris.ui.panel.sub.GameBoardPanel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final int CELL_SIZE = 20;
    private final int EXTRA_SPACE = 50;

    private GameBoardPanel gameBoardPanel;

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

            add(UIHelper.getEmptyPanel(new Dimension(225, getFrameHeight() - 100), null),
                    BorderLayout.EAST);

            gameBoardPanel = new GameBoardPanel();
            add(gameBoardPanel, BorderLayout.CENTER);

            add(UIHelper.getEmptyPanel(new Dimension(225, getFrameHeight() - 100), null),
                    BorderLayout.WEST);

            add(UIHelper.getBottomPanel(new Dimension(getFrameWidth(), 50), l -> {
                GameController gameController = EnhancedTetrisApp.getInstance().getGameController();
                if (gameController.isGameOver()) {
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
                    MenuFacade.openPanel(MenuType.MAIN_MENU);
                } else if (exitGamePopup == JOptionPane.NO_OPTION) {
                    gameController.unpauseGame();
                    EnhancedTetrisApp.getInstance().getMainFrame().requestFocus();
                }
            }), BorderLayout.SOUTH);
        });
    }

    public JPanel getInfoPanel() {
        return null;
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
}
