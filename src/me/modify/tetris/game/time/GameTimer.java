package me.modify.tetris.game.time;

import javax.swing.*;
import java.util.UUID;

public class GameTimer {
    private String name;
    private UUID id;
    private Timer timer;

    public GameTimer(String name, UUID id, Timer timer) {
        this.name = name;
        this.id = id;
        this.timer = timer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
