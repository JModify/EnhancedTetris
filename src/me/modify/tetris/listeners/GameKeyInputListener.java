package me.modify.tetris.listeners;

import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.audio.effects.Effect;
import me.modify.tetris.audio.effects.SoundEffectFactory;
import me.modify.tetris.game.GameController;
import me.modify.tetris.game.config.GameConfiguration;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

    private final char TOGGLE_MUSIC = KeyEvent.VK_M;

    private final char TOGGLE_EFFECTS = KeyEvent.VK_S;

    /** Blocks the listener from listening */
    private boolean blockInput;


    /**
     * Creates a new GameKeyInputListener instance.
     */
    public GameKeyInputListener() {
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

        // If game movement is blocked due to pause, return and do nothing.
        if (blockInput && pressed != PAUSE_GAME) {
            return;
        }

        // Check if the pressed button is a movement key and play movement sound effect.
        if (isMovementKey(pressed)) {
            SoundEffectFactory.createSoundEffect(Effect.MOVE).play();
        }

        GameController gameController = EnhancedTetrisApp.getInstance().getGameController();

        switch(pressed) {
            case ROTATE_CLOCKWISE -> gameController.getGrid().rotateTetromino();

            case MOVE_LEFT -> gameController.getGrid().shiftLeft();

            case MOVE_RIGHT -> gameController.getGrid().shiftRight();

            case MOVE_DOWN -> gameController.getGrid().shiftDown(false);

            case TOGGLE_EFFECTS -> EnhancedTetrisApp.getInstance().getConfiguration().toggleSound();

            case TOGGLE_MUSIC -> {

                EnhancedTetrisApp app = EnhancedTetrisApp.getInstance();
                GameConfiguration configuration = app.getConfiguration();

                // Current music setting
                boolean isMusic = configuration.isMusic();

                // Update music setting and stop/start music player.
                if (isMusic) {
                    configuration.setMusic(false);
                    app.getMusicPlayer().stop();
                } else {
                    configuration.setMusic(true);
                    app.getMusicPlayer().start();
                }
            }

            case PAUSE_GAME -> {
                if (gameController.isPaused()) {
                    gameController.unpauseGame();
                    return;
                }

                gameController.pauseGame(false);
            }
        }

        // Update the game panel's information data to show change in music/sound effects settings.
        if (pressed == TOGGLE_EFFECTS || pressed == TOGGLE_MUSIC) {
            EnhancedTetrisApp.getInstance().getMainFrame().getGamePanel().updateInfoPanels();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setBlockInput(boolean blockInput) {
        this.blockInput = blockInput;
    }

    private boolean isMovementKey(int keyCode) {
        return keyCode == MOVE_LEFT || keyCode == MOVE_RIGHT || keyCode == ROTATE_CLOCKWISE;
    }
}
