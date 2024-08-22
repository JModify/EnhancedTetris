package me.modify.tetris.game;

import javax.swing.*;

public class GameController {

    private final GameConfiguration configuration;
    private final TetrisGrid grid;

    private Timer timer;

    public GameController() {
        this.configuration = new GameConfiguration();
        this.grid = new TetrisGrid(configuration.getFieldWidth(), configuration.getFieldHeight());

        timer = new Timer(1000, e -> updateGame());
    }

    public void updateGame() {
        grid.shiftGridDown();
        grid.printGrid();
    }

    public void startGame() {
        updateGridSize();

        grid.insertTetromino(Tetromino.randomTetromino());
        grid.printGrid();

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
}
