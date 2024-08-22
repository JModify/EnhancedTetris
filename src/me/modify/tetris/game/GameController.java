package me.modify.tetris.game;

import me.modify.tetris.EnhancedTetrisApp;

import javax.swing.*;

public class GameController {

    private final GameConfiguration configuration;
    private final TetrisGrid grid;
    private EnhancedTetrisApp app;

    private Timer timer;

    public GameController(EnhancedTetrisApp app) {
        this.app = app;
        this.configuration = new GameConfiguration();
        this.grid = new TetrisGrid(this, configuration.getFieldWidth(), configuration.getFieldHeight());

        timer = new Timer(1000, e -> updateGame());
    }

    public void updateGame() {
        if (grid.allCellsFixed()) {
            grid.clearPlaceholders();
            grid.insertTetromino(Tetromino.randomTetromino());
            resumeMovementInput();
        }

        grid.shiftDown();

        grid.printGrid();
    }

    public void endGame() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    public void startGame() {
        updateGridSize();

        grid.insertTetromino(Tetromino.randomTetromino());
        grid.printGrid();
        app.getMainFrame().getJFrame().requestFocus();
        timer.start();
    }

    public void pauseGame() {

    }

    public void unpauseGame() {

    }

    private void updateGridSize() {
        int fieldWidth = configuration.getFieldWidth();
        int fieldHeight = configuration.getFieldHeight();
        grid.updateSize(fieldWidth, fieldHeight);
    }

    public GameConfiguration getConfiguration() {
        return configuration;
    }

    public TetrisGrid getGrid() {
        return this.grid;
    }

    public void blockMovementInput() {
        app.getMainFrame().getMovementListener().setBlockInput(true);
    }

    public void resumeMovementInput() {
        app.getMainFrame().getMovementListener().setBlockInput(false);
    }
}
