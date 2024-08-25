package me.modify.tetris.game;

import me.modify.tetris.EnhancedTetrisApp;

import javax.swing.*;

public class GameController {

    private final GameConfiguration configuration;
    private final GameGrid grid;
    private EnhancedTetrisApp app;

    private GameState gameState;

    private boolean tempPause;

    private Timer timer;

    public GameController(EnhancedTetrisApp app) {
        this.app = app;
        this.configuration = new GameConfiguration();
        this.grid = new GameGrid(configuration.getFieldWidth(), configuration.getFieldHeight());
        this.gameState = GameState.IDLE;
        this.tempPause = false;

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
                return;
            }

            grid.insertTetromino(Tetromino.randomTetromino());
            return;
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

    public boolean isPaused() {
        return gameState == GameState.PAUSED;
    }

    public void pauseGame() {
        gameState = GameState.PAUSED;
        if (timer.isRunning()) {
            timer.stop();
        }

        app.getMainFrame().getGamePanel().showPauseMessage();
    }

    public void unpauseGame() {
        gameState = GameState.RUNNING;
        if (!timer.isRunning()) {
            timer.start();
        }

        app.getMainFrame().getGamePanel().hidePauseMessage();
    }

    public void setTempPause(boolean tempPause) {
        this.tempPause = tempPause;
    }

    public boolean isTempPause() {
        return this.tempPause;
    }

    @Deprecated
    public void updateGridSize() {
        int fieldWidth = configuration.getFieldWidth();
        int fieldHeight = configuration.getFieldHeight();
        grid.updateSize(fieldWidth, fieldHeight);
    }

    public GameConfiguration getConfiguration() {
        return configuration;
    }

    public GameGrid getGrid() {
        return this.grid;
    }

    public boolean isGameOver() {
        return gameState == GameState.LOST;
    }

    @Deprecated
    public void blockMovementInput() {
        app.getMainFrame().getMovementListener().setBlockInput(true);
    }

    @Deprecated
    public void resumeMovementInput() {
        app.getMainFrame().getMovementListener().setBlockInput(false);
    }
}
