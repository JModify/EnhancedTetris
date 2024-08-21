package me.modify.tetris;

import me.modify.tetris.game.GameController;
import me.modify.tetris.ui.MainFrame;
import me.modify.tetris.ui.TetrisSplashScreen;

import javax.swing.*;

public class EnhancedTetrisApp {

    private GameController gameController;

    public EnhancedTetrisApp() {
        this.gameController = new GameController();
    }

    public GameController getGameController() {
        return gameController;
    }

    public static void main(String[] args) {

        TetrisSplashScreen splashScreen = new TetrisSplashScreen(3000);
        splashScreen.showSplash();

        SwingUtilities.invokeLater(() -> {
            EnhancedTetrisApp main = new EnhancedTetrisApp();
            MainFrame mainFrame = new MainFrame(main);
            mainFrame.createUI();
            mainFrame.openMainMenu();
        });
    }
}
