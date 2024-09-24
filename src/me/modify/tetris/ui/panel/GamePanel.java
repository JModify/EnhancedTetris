package me.modify.tetris.ui.panel;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.GameConfiguration;
import me.modify.tetris.game.GameController;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.PopupType;
import me.modify.tetris.ui.UIHelper;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final int CELL_SIZE = 20;
    private final int EXTRA_SPACE = 50;

    private GameBoardPanel gameBoardPanel;

    public GamePanel() {
        gameBoardPanel = new GameBoardPanel();
        init();
    }

    public void init() {
        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(getFrameWidth(), getFrameHeight()));

            add(UIHelper.getEmptyPanel(new Dimension(getFrameWidth(), 50),
                    BorderFactory.createLineBorder(Color.RED)), BorderLayout.NORTH);

            add(UIHelper.getEmptyPanel(new Dimension(225, getFrameHeight() - 100),
                    BorderFactory.createLineBorder(Color.RED)), BorderLayout.EAST);

            add(new GameBoardPanel(), BorderLayout.CENTER);

            add(UIHelper.getEmptyPanel(new Dimension(225, getFrameHeight() - 100),
                    BorderFactory.createLineBorder(Color.RED)), BorderLayout.WEST);

            add(UIHelper.getBottomPanel(new Dimension(getFrameWidth(), 50), l -> {
                GameController gameController = EnhancedTetrisApp.getInstance().getGameController();
                if (!gameController.isPaused()) {
                    gameController.pauseGame(true);
                }

                MenuFacade.openPopup(PopupType.EXIT_GAME);
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
        System.out.println((configuration.getFieldWidth() * 20) + (225 * 2));
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
        System.out.println((configuration.getFieldHeight() * 20) + (50 * 2));
        return (configuration.getFieldHeight() * 20) + (50 * 2) + 38;
    }
}
