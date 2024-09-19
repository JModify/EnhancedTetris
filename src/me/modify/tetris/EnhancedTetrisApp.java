package me.modify.tetris;

import me.modify.tetris.game.GameController;
import me.modify.tetris.scores.HighScores;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;
import me.modify.tetris.ui.frames.MainFrame;
import me.modify.tetris.ui.panel.*;

import javax.swing.*;

/**
 * Main class for application.
 * Displays splash screen upon launch
 */
public class EnhancedTetrisApp {

    /** Instance for this main class */
    private static EnhancedTetrisApp instance;

    /** Game controller for the game itself */
    private final GameController gameController;

    //TODO: Implement loading this
    private HighScores highScores;

    /** Main frame of the application */
    private final MainFrame mainFrame;

    /**
     * Constructs a new EnhancedTetrisApp and initializes the game controller.
     */
    public EnhancedTetrisApp() {
        setInstance(this);
        this.gameController = new GameController();
        this.mainFrame = new MainFrame();
    }

    /**
     * Retrieves the game controller.
     * @return game controller
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Retrieves the main frame of the application
     * @return main frame
     */
    public MainFrame getMainFrame() {
        return this.mainFrame;
    }

    public void loadScores() {
    }

    public static void main(String[] args) {

        // Display splash screen for 3 seconds.
        TetrisSplashScreen splashScreen = new TetrisSplashScreen(3000);
        splashScreen.showSplash();

        // Instantiate main frame and open main menu panel.
        SwingUtilities.invokeLater(() -> {
            EnhancedTetrisApp main = new EnhancedTetrisApp();
            main.getMainFrame().init();

            MenuFacade.openPanel(MenuType.MAIN_MENU);
        });
    }

    /**
     * Retrieves the main class instance (this).
     * @return main class instance.
     */
    public static EnhancedTetrisApp getInstance() {
        return instance;
    }

    /**
     * Sets the main class instance.
     * @param instance instance to set too.
     */
    public void setInstance(EnhancedTetrisApp instance) {
        EnhancedTetrisApp.instance = instance;
    }
}
