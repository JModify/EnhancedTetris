package me.modify.tetris.game;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.time.GameScheduler;

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

    /** Current state the game is in */
    private GameState gameState;

    /** Whether the game was paused due to clicking of the back button or not */
    private boolean tempPause;

    private int score;
    private int gameLevel;

    /**
     * Creates a new game controller and initializes the grid.
     * Constructor also sets th game state to be IDLE and creates a timer instance.
     */
    public GameController() {
        this.configuration = new GameConfiguration();
        this.grid = new GameGrid(configuration.getFieldWidth(), configuration.getFieldHeight());
        this.gameState = GameState.IDLE;
        this.tempPause = false;

        GameScheduler.getInstance().addTimer("Game_Update", new Timer(1000, t -> updateGame()));
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

            if (grid.clearRows() > 0) {
                //TODO: Score Logic
            }

            // Attempts to insert a new tetromino. If this fails, the game has been lost.
            Tetromino tetromino = Tetromino.randomTetromino();
            if (!grid.canInsertTetromino(tetromino)) {
                gameOver();
                return;
            }
            grid.insertTetromino(Tetromino.randomTetromino());
            return;
        }

        // Shift falling Tetromino down if it is not yet placed.
        grid.shiftDown(false);

        // grid.printGrid();
    }

    public void gameOver() {
        GameScheduler.getInstance().stopAll();
        gameState = GameState.LOST;
    }

    /**
     * Ends the game by stopping the timer.
     */
    public void endGame() {
        GameScheduler.getInstance().stopAll();
        grid.clearGrid();
    }

    /**
     * Starts a new game and sets the game state to RUNNING.
     */
    public void startGame() {
        grid.updateSize(configuration.getFieldWidth(), configuration.getFieldHeight());

        score = 0;
        gameLevel = getConfiguration().getGameLevel();

        grid.insertTetromino(Tetromino.randomTetromino());

        EnhancedTetrisApp.getInstance().getMainFrame().requestFocus();
        GameScheduler.getInstance().startAll();

        gameState = GameState.RUNNING;
        resumeMovementInput();
    }

    /**
     * Determines whether the game is paused.
     * @return true if paused otherwise false.
     */
    public boolean isPaused() {
        return gameState == GameState.PAUSED || gameState == GameState.TEMP_PAUSED;
    }

    /**
     * Pauses the game and displays pause text.
     */
    public void pauseGame(boolean tempPause) {
        gameState = tempPause ? GameState.TEMP_PAUSED : GameState.PAUSED;
        GameScheduler.getInstance().stopTimer("Game_Update");

        blockMovementInput();
        //EnhancedTetrisApp.getInstance().getGamePanel().showPauseMessage();
    }

    /**
     * Unpauses the game and hides pause text.
     */
    public void unpauseGame() {
        gameState = GameState.RUNNING;
        GameScheduler.getInstance().startTimer("Game_Update");

        resumeMovementInput();
        //MenuFacade.openPanel(MenuType.GAME);
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

    public void blockMovementInput() {
        EnhancedTetrisApp.getInstance().getMainFrame().getMovementListener().setBlockInput(true);
    }

    public void resumeMovementInput() {
        EnhancedTetrisApp.getInstance().getMainFrame().getMovementListener().setBlockInput(false);
    }
}
