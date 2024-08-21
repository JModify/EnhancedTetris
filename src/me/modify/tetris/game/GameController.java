package me.modify.tetris.game;

import me.modify.tetris.ui.panel.GamePanel;

public class GameController {

    private GameConfiguration configuration;
    private TetrisGrid grid;
    private GamePanel gamePanel;

    public GameController() {
        this.configuration = new GameConfiguration();
        this.grid = new TetrisGrid(configuration.getFieldWidth(), configuration.getFieldHeight());
    }

    public void startGame() {
        updateGridSize();

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
}
