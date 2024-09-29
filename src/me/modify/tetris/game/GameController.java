package me.modify.tetris.game;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.audio.effects.Effect;
import me.modify.tetris.audio.effects.SoundEffectFactory;
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

        GameScheduler.getInstance().addTimer("Game_Update", new Timer(0, t -> updateGame()));
    }

    /**
     * Updates the game. Should be called at a constant rate to keep visuals relevant.
     */
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

                // Handle game level up if game requires it
                if (requiresLevelUp()) {
                    increaseLevel();

                    // Play level up sound if row clear results in level up
                    SoundEffectFactory.createSoundEffect(Effect.LEVEL_UP).play();
                } else {
                    // PLay row clear sound if the row clear(s) did not result in a level up
                    SoundEffectFactory.createSoundEffect(Effect.ROW_CLEAR).play();
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
            EnhancedTetrisApp.getInstance().getMainFrame().getGamePanel().updateInfoPanels();
        }
     }

    /**
     * Determines if the player has leveled up.
     * A player should level up every 10 rows they clear UNLESS they are already at the maximum level.
     * @return true if level up has occurred, false otherwise.
     */
     private boolean requiresLevelUp() {
        if (gameLevel.getLevelNum() == getConfiguration().GAME_LEVEL_MAX) {
            return false;
        }

        int nextThreshold = GameLevel.nextLevel(gameLevel).getThreshold();
        int startLevel = getConfiguration().getGameLevel();

        // Calculates if the player has reached the threshold for the next level based on initial level and the
         // number of rows they have erased in total.
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

    /**
     * Executes end of game logic. Sets the game state to lost and adds high score if necessary.
     */
    public void gameOver() {
        SoundEffectFactory.createSoundEffect(Effect.GAME_OVER).play();
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
        EnhancedTetrisApp.getInstance().getMainFrame().getGamePanel().updateInfoPanels();

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
    }

    /**
     * Unpauses the game and hides pause text.
     */
    public void unpauseGame() {
        gameState = GameState.RUNNING;
        GameScheduler.getInstance().startTimer("Game_Shift");

        resumeMovementInput();
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
     * @return true if game is over, else false.
     */
    public boolean isGameOver() {
        return gameState == GameState.LOST;
    }

    /**
     * Blocks user being able to move the current tetromino. Used in paused state.
     */
    public void blockMovementInput() {
        EnhancedTetrisApp.getInstance().getMainFrame().getGameInputListener().setBlockInput(true);
    }

    /**
     * Allows user to continue moving falling tetromino.
     */
    public void resumeMovementInput() {
        EnhancedTetrisApp.getInstance().getMainFrame().getGameInputListener().setBlockInput(false);
    }

    /** Adds a certain amount of score to this game */
    private void addScore(int amount) {
        score += amount;
    }

    /**
     * Retrieves the current score for this game.
     * @return current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Retrieves the current level for this game.
     * @return the game level.
     */
    public GameLevel getGameLevel() {
        if(gameLevel == null) {
            return GameLevel.getByLevel(getConfiguration().getGameLevel());
        }

        return gameLevel;
    }

    /**
     * Retrieves the total number of erased rows for this game.
     * @return number or rows erased.
     */
    public int getRowsErased() {
        return rowsErased;
    }

    /**
     * Retrieves the next Tetromino to be inserted into the grid.
     * @return the next tetromino.
     */
    public Tetromino getNextTetromino() {
        return nextTetromino;
    }
}
