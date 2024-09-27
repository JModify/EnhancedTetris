package me.modify.tetris.game.score;

import java.util.*;

public class HighScores {

    private Set<Score> scores;

    public HighScores(Set<Score> scores) {;
        this.scores = scores;
    }

    private List<Score> getSortedList() {
        List<Score> scoresList = new ArrayList<>(scores);
        scoresList.sort(Collections.reverseOrder());
        return scoresList;
    }

    public void addScore(Score score) {
        scores.add(score);
    }

    public List<Score> getTopScores() {
        return getSortedList().stream().limit(10).toList();
    }

    public Set<Score> getScores() {
        return scores;
    }

    public boolean isHighScore(int score) {
        if (scores.size() < 10) {
            return true;
        }

        return getTopScores().stream().anyMatch(s -> score > s.getScore());
    }

    public void clearHighScores() {
        scores.clear();
    }
}
