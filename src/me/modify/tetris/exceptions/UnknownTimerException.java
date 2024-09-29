package me.modify.tetris.exceptions;

/**
 * Attempted to retrieve a timer which was unknown exception.
 */
public class UnknownTimerException extends Exception {

    public UnknownTimerException(String message) {
        super(message);
    }
}
