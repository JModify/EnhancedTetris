package me.modify.tetris;

import me.modify.tetris.game.GameController;
import me.modify.tetris.ui.frames.MainFrame;
import me.modify.tetris.ui.panel.TetrisSplashScreen;

import javax.swing.*;

/**
 * Main class for application.
 * Displays splash screen upon launch
 */
public class EnhancedTetrisApp {

    /** Game controller for the game itself */
    private GameController gameController;

    /** Main frame of the application */
    private MainFrame mainFrame;

    /**
     * Constructs a new EnhancedTetrisApp and initializes the game controller.
     */
    public EnhancedTetrisApp() {
        this.gameController = new GameController(this);
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

    public static void main(String[] args) {

        // Display splash screen for 3 seconds.
        TetrisSplashScreen splashScreen = new TetrisSplashScreen(3000);
        splashScreen.showSplash();

        // Instantiate main frame and open main menu panel.
        SwingUtilities.invokeLater(() -> {
            EnhancedTetrisApp main = new EnhancedTetrisApp();
            MainFrame mainFrame = new MainFrame(main);
            main.mainFrame = mainFrame;
            mainFrame.createUI();
            mainFrame.openMainMenu();
        });
    }


}
