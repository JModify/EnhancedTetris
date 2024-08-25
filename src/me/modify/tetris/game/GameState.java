package me.modify.tetris.game;

public enum GameState {

    /** Game is not running, user is on another menu */
    IDLE,

    /** Game is in progress */
    RUNNING,

    /** Game has been paused */
    PAUSED,

    /** Game has been lost, user still on menu */
    LOST;

}
