package me.modify.tetris.game.state;

public enum GameState {

    /** Game is not running, user is on another menu */
    IDLE,

    /** Game is in progress */
    RUNNING,

    /** Game has been paused */
    PAUSED,

    /** Game paused due to click of the back button */
    TEMP_PAUSED,

    /** Game has been lost, user still on menu */
    LOST;

}
