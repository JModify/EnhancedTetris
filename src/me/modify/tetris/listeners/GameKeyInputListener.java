package me.modify.tetris.listeners;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.GameController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Listens for all game related keyboard inputs.
 * Executes action upon pressing them respectively.
 */
public class GameKeyInputListener implements KeyListener {

    /** Rotate Tetromino clockwise key */
    private final char ROTATE_CLOCKWISE = KeyEvent.VK_UP;

    /** Move Tetromino to the left key */
    private final char MOVE_LEFT = KeyEvent.VK_LEFT;

    /** Move Tetromino to the right key */
    private final char MOVE_RIGHT = KeyEvent.VK_RIGHT;

    /** Move Tetromino downwards key */
    private final char MOVE_DOWN = KeyEvent.VK_DOWN;

    /** Pause the game key */
    private final char PAUSE_GAME = KeyEvent.VK_P;

    /** Blocks the listener from listening */
    private boolean blockInput;

    /** Game controller for the current game */
    private final GameController gameController;

    /**
     * Creates a new GameKeyInputListener instance.
     * @param gameController game controller for current game.
     */
    public GameKeyInputListener(GameController gameController) {
        this.gameController = gameController;
        this.blockInput = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Event where a key is pressed down.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int pressed = e.getKeyCode();

        if (blockInput && pressed != PAUSE_GAME) {
            return;
        }

        if (isMovementKey(pressed)) {
            EnhancedTetrisApp.getInstance().getSoundEffectPlayer().playSound("move-turn");
        }

        switch(pressed) {
            case ROTATE_CLOCKWISE -> gameController.getGrid().rotateTetromino();

            case MOVE_LEFT -> gameController.getGrid().shiftLeft();

            case MOVE_RIGHT -> gameController.getGrid().shiftRight();

            case MOVE_DOWN -> gameController.getGrid().shiftDown(false);

            case PAUSE_GAME -> {
                if (gameController.isPaused()) {
                    gameController.unpauseGame();
                    return;
                }

                gameController.pauseGame(false);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setBlockInput(boolean blockInput) {
        this.blockInput = blockInput;
    }

    private boolean isMovementKey(int keyCode) {
        return keyCode == MOVE_DOWN || keyCode == MOVE_LEFT || keyCode == MOVE_RIGHT || keyCode == ROTATE_CLOCKWISE;
    }
}
