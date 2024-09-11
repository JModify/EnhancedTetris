package me.modify.tetris.scores;

import java.io.Serializable;
import java.util.*;

public class HighScores {

    private Set<Score> scores;

    public HighScores(Set<Score> scores) {
        this.scores = scores;
    }

    private List<Score> getSortedList() {
        return scores.stream().sorted().toList();
    }

    public void addScore(Score score) {
        scores.add(score);
    }

    public List<Score> getTopScores() {
        return getSortedList().stream().limit(10).toList();
    }

    public boolean isHighScore(int score) {
        return getTopScores().stream().anyMatch(s -> score > s.getScore());
    }

//    public static Set<Score> loadScores() {
//
//    }


}
