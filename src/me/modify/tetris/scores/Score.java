package me.modify.tetris.scores;

import com.google.gson.annotations.Expose;

public class Score implements Comparable<Score> {

    @Expose
    private String name;

    @Expose
    private int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.score, o.getScore());
    }
}
