package me.modify.tetris.game.time;

import me.modify.tetris.exceptions.UnknownTimerException;

import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

public class GameScheduler {

    public static GameScheduler instance;
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

    public UUID addTimer(String name, Timer timer) {
        UUID id = UUID.randomUUID();
        GameTimer gameTimer = new GameTimer(name, timer);
        timers.add(gameTimer);
        return id;
    }

    private GameTimer getTimer(String name) throws UnknownTimerException {
        return timers.stream()
                .filter(t -> t.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new UnknownTimerException("Timer with name '" + name + "' not found!"));
    }

    public void startTimer(String name) {
        try {
            GameTimer timer = getTimer(name);
            timer.getTimer().start();
        } catch (UnknownTimerException e) {
            e.printStackTrace();
        }
    }

    public void stopTimer(String name) {
        try {
            GameTimer timer = getTimer(name);
            timer.getTimer().stop();
        } catch (UnknownTimerException e) {
            e.printStackTrace();
        }
    }

    public void stopAll() {
        timers.forEach(t -> t.getTimer().stop());
    }

    public void shutdown() {
        stopAll();
        timers.clear();
    }

    public void startAll() {
        timers.forEach(t -> t.getTimer().start());
    }
}
