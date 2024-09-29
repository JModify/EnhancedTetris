package me.modify.tetris.game.time;

import me.modify.tetris.exceptions.UnknownTimerException;

import javax.swing.Timer;
import java.util.*;

/**
 * Schedules all tasks / timers for the application and stores them to be stopped/started later
 */
public class GameScheduler {

    private static GameScheduler instance;
    private final List<GameTimer> timers;

    private GameScheduler() {
        timers = new ArrayList<>();
    }

    public static GameScheduler getInstance() {
        if (instance == null) {
            instance = new GameScheduler();
            return instance;
        }
        return instance;
    }

    /**
     * Adds a game timer with the specified name.
     * @param name name of timer
     * @param timer timer object
     * @return the game timer which was created added.
     */
    public GameTimer addTimer(String name, Timer timer) {
        GameTimer gameTimer = new GameTimer(name, timer);
        timers.add(gameTimer);
        return gameTimer;
    }

    /**
     * Retrieves the game timer under the specified name.
     * @param name name of timer
     * @return GameTimer object under this name
     * @throws UnknownTimerException if the game timer with the specified name is invalid.
     */
    private GameTimer getTimer(String name) throws UnknownTimerException {
        return timers.stream()
                .filter(t -> t.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new UnknownTimerException("Timer with name '" + name + "' not found!"));
    }

    /**
     * Starts a timer using it's name.
     * @param name name of timer to start
     */
    public void startTimer(String name) {
        try {
            GameTimer timer = getTimer(name);
            timer.getTimer().start();
        } catch (UnknownTimerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts a game timer using it's direct GameTimer reference.
     * @param gameTimer game timer to start
     */
    public void startTimer(GameTimer gameTimer) {
        gameTimer.getTimer().start();
    }

    /**
     * Stops a timer using it's name.
     * @param name name of timer to stop.
     */
    public void stopTimer(String name) {
        try {
            GameTimer timer = getTimer(name);
            timer.getTimer().stop();
        } catch (UnknownTimerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts all timers stored in the scheduler.
     */
    public void startAll() {
        timers.forEach(t -> t.getTimer().start());
    }

    /**
     * Stops ALL timers stored in the scheduler.
     */
    public void stopAll() {
        timers.forEach(t -> t.getTimer().stop());
    }

    /**
     * Deletes a timer from the scheduler.
     * @param name name of timer to delete.
     */
    public void deleteTimer(String name) {
        try {
            GameTimer gameTimer = getTimer(name);
            gameTimer.getTimer().stop();

            timers.remove(gameTimer);
        } catch (UnknownTimerException ignored) {
            // Ignore since deleting a timer which does not exist should not be an issue.
        }
    }

    /**
     * Initiates shutdown for the scheduler.
     * Shutdown stops all timers and clears the scheduler.
     */
    public void shutdown() {
        stopAll();
        timers.clear();
    }
}
