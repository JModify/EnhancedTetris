package me.modify.tetris.game;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.config.GameConfiguration;
import me.modify.tetris.game.state.GameLevel;
import me.modify.tetris.game.state.GameState;
import me.modify.tetris.game.state.Tetromino;
import me.modify.tetris.game.time.GameScheduler;
import me.modify.tetris.game.time.GameTimer;
import me.modify.tetris.game.score.HighScores;
import me.modify.tetris.game.score.Score;

import javax.swing.*;

/**
 * GameController controls the flow of the game and contains the repeating Timer
 * which updates the game grid as the user plays.
 */
public class GameController {

    /** Grid for the game */
    private final GameGrid grid;

    /** Current state the game is in */
    private GameState gameState;

    /** Whether the game was paused due to clicking of the back button or not */
    private boolean tempPause;

    private int score;
    private int rowsErased;
    private GameLevel gameLevel;

    private Tetromino nextTetromino;

    /**
     * Creates a new game controller and initializes the grid.
     * Constructor also sets th game state to be IDLE and creates a timer instance.
     */
    public GameController() {
        this.grid = new GameGrid(getConfiguration().getFieldWidth(), getConfiguration().getFieldHeight());
        this.gameState = GameState.IDLE;
        this.tempPause = false;

        GameScheduler.getInstance().addTimer("Game_Update", new Timer(0, t -> updateGame()));
    }

    private void updateGame() {

        // Determine whether the current falling Tetromino has been placed.
        if (grid.allCellsFixed()) {

            // Clears Tetromino placeholders and any rows that are full.
            grid.clearPlaceholders();

            int rowsCleared = grid.clearRows();
            if (rowsCleared == 1) {
                addScore(100);
            } else if (rowsCleared == 2) {
                addScore(300);
            } else if (rowsCleared == 3) {
                addScore(600);
            } else if(rowsCleared >= 4) {
                addScore(1000);
            }

            if (rowsCleared > 0) {
                rowsErased += rowsCleared;

                // Handle game level up.
                // Rows erased is not equal to 0 and player has erased 10 more rows
                if (requiresLevelUp()) {
                    System.out.println("Rows erased is a multiple of 10");
                    if (gameLevel.getLevelNum() != getConfiguration().GAME_LEVEL_MAX) {

                        System.out.println("Game level is not maxed, attempting level up.");
                        increaseLevel();

                        // Play level up sound if row clear results in level up
                        EnhancedTetrisApp.getInstance().getSoundEffectPlayer().playSound("level-up");
                    }
                } else {
                    System.out.println("Cleared row");
                    // PLay row clear sound if the clear did not result in a level up
                    EnhancedTetrisApp.getInstance().getSoundEffectPlayer().playSound("erase-line");
                }
            }

            // Attempts to insert a new tetromino. If this fails, the game has been lost.
            Tetromino tetromino = Tetromino.randomTetromino();
            if (!grid.canInsertTetromino(tetromino)) {
                gameOver();
                return;
            }

            grid.insertTetromino(nextTetromino);
            nextTetromino = Tetromino.randomTetromino();

            // Update info panel at the end of each block falling.
            EnhancedTetrisApp.getInstance().getMainFrame().getGamePanel().updateInfoPanel();
        }
     }

     private boolean requiresLevelUp() {
        int nextThreshold = GameLevel.nextLevel(gameLevel).getThreshold();
        int startLevel = getConfiguration().getGameLevel();

         return (rowsErased + ((startLevel - 1) * 10)) >= nextThreshold;
     }


     private void increaseLevel() {
        gameLevel = GameLevel.nextLevel(gameLevel);

        GameScheduler.getInstance().deleteTimer("Game_Shift");
        GameTimer gameTimer = GameScheduler.getInstance().addTimer("Game_Shift",
                new Timer(gameLevel.getFallSpeed(), t -> shiftGrid()));
        GameScheduler.getInstance().startTimer(gameTimer);
        System.out.println("Level up!");
     }

    /**
     * Updates the game every second.
     * Timer executes upon this method.
     */
    private void shiftGrid() {
        grid.shiftDown(false);
    }

    public void gameOver() {
        EnhancedTetrisApp.getInstance().getSoundEffectPlayer().playSound("game-finish");
        GameScheduler.getInstance().stopAll();
        gameState = GameState.LOST;

        HighScores highScores = EnhancedTetrisApp.getInstance().getHighScores();

        if (highScores.isHighScore(score)) {
            String scoreNameEntry = JOptionPane.showInputDialog(null, "Enter your name: ", "Player Name", JOptionPane.QUESTION_MESSAGE);
            if (scoreNameEntry != null && !scoreNameEntry.trim().isEmpty()) {
                Score scoreEntry = new Score(scoreNameEntry, score);
                highScores.addScore(scoreEntry);
            }
        }
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
        grid.updateSize(getConfiguration().getFieldWidth(), getConfiguration().getFieldHeight());

        score = 0;
        rowsErased = 0;
        gameLevel = GameLevel.getByLevel(getConfiguration().getGameLevel());

        grid.insertTetromino(Tetromino.randomTetromino());
        nextTetromino = Tetromino.randomTetromino();

        GameScheduler.getInstance().deleteTimer("Game_Shift");
        GameScheduler.getInstance().addTimer("Game_Shift", new Timer(gameLevel.getFallSpeed(),
                t -> shiftGrid()));

        // Update info panel to show next tetromino upon start up.
        EnhancedTetrisApp.getInstance().getMainFrame().getGamePanel().updateInfoPanel();

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
        GameScheduler.getInstance().stopTimer("Game_Shift");

        blockMovementInput();
        //EnhancedTetrisApp.getInstance().getGamePanel().showPauseMessage();
    }

    /**
     * Unpauses the game and hides pause text.
     */
    public void unpauseGame() {
        gameState = GameState.RUNNING;
        GameScheduler.getInstance().startTimer("Game_Shift");

        resumeMovementInput();
        //MenuFacade.openPanel(MenuType.GAME);
    }

    /**
     * Retrieves the game configuration.
     * @return game configuration reference.
     */
    public GameConfiguration getConfiguration() {
        return EnhancedTetrisApp.getInstance().getConfiguration();
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

    public void addScore(int amount) {
        score += amount;
    }

    public int getScore() {
        return score;
    }

    public GameLevel getGameLevel() {
        if(gameLevel == null) {
            return GameLevel.getByLevel(getConfiguration().getGameLevel());
        }

        return gameLevel;
    }

    public int getRowsErased() {
        return rowsErased;
    }

    public Tetromino getNextTetromino() {
        return nextTetromino;
    }
}
