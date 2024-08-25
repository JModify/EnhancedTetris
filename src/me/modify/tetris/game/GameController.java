package me.modify.tetris.game;

import me.modify.tetris.EnhancedTetrisApp;

import javax.swing.*;

/**
 * GameController controls the flow of the game and contains the repeating Timer
 * which updates the game grid as the user plays.
 */
public class GameController {

    /** Configuration for the game */
    private final GameConfiguration configuration;

    /** Grid for the game */
    private final GameGrid grid;

    /** Reference to main class */
    private EnhancedTetrisApp main;

    /** Current state the game is in */
    private GameState gameState;

    /** Whether the game was paused due to clicking of the back button or not */
    private boolean tempPause;

    /** Timer executing the flow of the game */
    private Timer timer;

    /**
     * Creates a new game controller and initializes the grid.
     * Constructor also sets th game state to be IDLE and creates a timer instance.
     * @param main main class for the application
     */
    public GameController(EnhancedTetrisApp main) {
        this.main = main;
        this.configuration = new GameConfiguration();
        this.grid = new GameGrid(configuration.getFieldWidth(), configuration.getFieldHeight());
        this.gameState = GameState.IDLE;
        this.tempPause = false;

        timer = new Timer(1000, e -> updateGame());
    }

    /**
     * Updates the game every second.
     * Timer executes upon this method.
     */
    public void updateGame() {

        // Determine whether the current falling Tetromino has been placed.
        if (grid.allCellsFixed()) {

            // Clears Tetromino placeholders and any rows that are full.
            grid.clearPlaceholders();
            grid.clearRows();

            // Attempts to insert a new tetromino. If this fails, the game has been lost.
            Tetromino tetromino = Tetromino.randomTetromino();
            if (!grid.canInsertTetromino(tetromino)) {
                gameState = GameState.LOST;
                endGame();
                return;
            }
            grid.insertTetromino(Tetromino.randomTetromino());
            return;
        }

        // Shift falling Tetromino down if it is not yet placed.
        grid.shiftDown(false);

        // grid.printGrid();
    }

    /**
     * Ends the game by stopping the timer.
     */
    public void endGame() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    /**
     * Starts a new game and sets the game state to RUNNING.
     */
    public void startGame() {
        //TODO: Implement configuration to have affect on grid size later.
        //updateGridSize();

        grid.insertTetromino(Tetromino.randomTetromino());
        main.getMainFrame().getJFrame().requestFocus();
        timer.start();

        gameState = GameState.RUNNING;
    }

    /**
     * Determines whether the game is paused.
     * @return
     */
    public boolean isPaused() {
        return gameState == GameState.PAUSED;
    }

    /**
     * Pauses the game and displays pause text.
     */
    public void pauseGame() {
        gameState = GameState.PAUSED;
        if (timer.isRunning()) {
            timer.stop();
        }

        main.getMainFrame().getGamePanel().showPauseMessage();
    }

    /**
     * Unpauses the game and hides pause text.
     */
    public void unpauseGame() {
        gameState = GameState.RUNNING;
        if (!timer.isRunning()) {
            timer.start();
        }

        main.getMainFrame().getGamePanel().hidePauseMessage();
    }

    /**
     * Flags the pause as a temporary pause due to a click of the "Back" button.
     * @param tempPause
     */
    public void setTempPause(boolean tempPause) {
        this.tempPause = tempPause;
    }

    /**
     * Determines whether the game is paused temporarily.
     * @return
     */
    public boolean isTempPause() {
        return this.tempPause;
    }

    @Deprecated
    public void updateGridSize() {
        int fieldWidth = configuration.getFieldWidth();
        int fieldHeight = configuration.getFieldHeight();
        grid.updateSize(fieldWidth, fieldHeight);
    }

    /**
     * Retrieves the game configuration.
     * @return game configuration reference.
     */
    public GameConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Retrieves the game grid .
     * @return game grid reference.
     */
    public GameGrid getGrid() {
        return this.grid;
    }

    /**
     * Determines if the game is over (game state is set to LOST).
     * @return
     */
    public boolean isGameOver() {
        return gameState == GameState.LOST;
    }

    @Deprecated
    public void blockMovementInput() {
        main.getMainFrame().getMovementListener().setBlockInput(true);
    }

    @Deprecated
    public void resumeMovementInput() {
        main.getMainFrame().getMovementListener().setBlockInput(false);
    }
}
