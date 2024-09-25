package me.modify.tetris.game.time;

import javax.swing.*;
import java.util.UUID;

public class GameTimer {
    private String name;
    private Timer timer;

    public GameTimer(String name, Timer timer) {
        this.name = name;
        this.timer = timer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
