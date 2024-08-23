package me.modify.tetris.game;

import me.modify.tetris.EnhancedTetrisApp;

import javax.swing.*;

public class GameController {

    private final GameConfiguration configuration;
    private final TetrisGrid grid;
    private EnhancedTetrisApp app;

    private GameState gameState;

    private Timer timer;
    public GameController(EnhancedTetrisApp app) {
        this.app = app;
        this.configuration = new GameConfiguration();
        this.grid = new TetrisGrid(configuration.getFieldWidth(), configuration.getFieldHeight());
        this.gameState = GameState.IDLE;

        timer = new Timer(1000, e -> updateGame());
    }

    public void updateGame() {
        if (grid.allCellsFixed()) {
            grid.clearPlaceholders();
            grid.clearRows();

            Tetromino tetromino = Tetromino.randomTetromino();
            if (!grid.canInsertTetromino(tetromino)) {
                gameState = GameState.LOST;
                endGame();
            }

            grid.insertTetromino(Tetromino.randomTetromino());
        }

        grid.shiftDown(false);
        //grid.printGrid();
    }

    public void endGame() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    public void startGame() {

        //TODO: Implement configuration to have affect on grid size later.
        //updateGridSize();

        grid.insertTetromino(Tetromino.randomTetromino());
        app.getMainFrame().getJFrame().requestFocus();
        timer.start();

        gameState = GameState.RUNNING;
    }

    public void pauseGame() {

    }

    public void unpauseGame() {

    }

    public void updateGridSize() {
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
