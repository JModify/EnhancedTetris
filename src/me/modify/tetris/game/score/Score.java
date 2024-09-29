package me.modify.tetris.game.score;

import com.google.gson.annotations.Expose;
import me.modify.tetris.EnhancedTetrisApp;
import me.modify.tetris.game.config.GameConfiguration;

/**
 * Represents a game score with associated data at the time the score was achieved.
 */
public class Score implements Comparable<Score> {

    @Expose
    private String name;

    @Expose
    private int score;

    @Expose
    private String config;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
        this.config = getConfigString();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getConfig() {
        return config;
    }

    private String getConfigString() {
        GameConfiguration configuration = EnhancedTetrisApp.getInstance().getConfiguration();
        return configuration.getFieldWidth() +
                "x" +
                configuration.getFieldHeight() +
                "(" + configuration.getGameLevel() + ")" +
                " Human" +
                " Single";
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.score, o.getScore());
    }
}
