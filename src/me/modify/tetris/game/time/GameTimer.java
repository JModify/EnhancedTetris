package me.modify.tetris.game.time;

import javax.swing.*;
import java.util.UUID;

public class GameTimer {
    private final String name;
    private final Timer timer;

    public GameTimer(String name, Timer timer) {
        this.name = name;
        this.timer = timer;
    }

    public String getName() {
        return name;
    }

    public Timer getTimer() {
        return timer;
    }
}
