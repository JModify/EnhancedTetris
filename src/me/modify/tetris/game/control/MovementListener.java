package me.modify.tetris.game.control;

import me.modify.tetris.game.GameController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementListener implements KeyListener {

    private final char ROTATE_CLOCKWISE = 'w';
    private final char MOVE_LEFT = 'a';
    private final char MOVE_RIGHT = 'd';
    private final char MOVE_DOWN = 's';

    private boolean blockInput;

    private GameController gameController;
    public MovementListener(GameController gameController) {
        this.gameController = gameController;
        this.blockInput = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (blockInput) {
            return;
        }

        char pressed = e.getKeyChar();
        switch(pressed) {
            case ROTATE_CLOCKWISE -> {
                gameController.getGrid().rotateTetromino();
            }
            case MOVE_LEFT -> {
                gameController.getGrid().shiftLeft();
            }
            case MOVE_RIGHT -> {
                gameController.getGrid().shiftRight();
            }
            case MOVE_DOWN -> {
                gameController.getGrid().shiftDown();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setBlockInput(boolean blockInput) {
        this.blockInput = blockInput;
    }
}
