package me.modify.tetris.game.score;

import java.util.*;

/**
 * Acts as a cache for the high scores when the game is running.
 * Saved to a JSON file when it is not - to be loaded upon next launch.
 */
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
        HighScoresFile.save(scores);
    }

    public List<Score> getTopScores() {
        return getSortedList().stream().limit(10).toList();
    }

    public Set<Score> getScores() {
        return scores;
    }

    public boolean isHighScore(int score) {
        // Do not count 0 score attempts onto top score leaderboard
        if (score == 0) {
            return false;
        }

        // Score is greater than 0 and top scores is not full
        if (score > 0 && getTopScores().size() < 10) {
            return true;
        }

        return getTopScores().stream().anyMatch(s -> score > s.getScore());
    }

    public void clearHighScores() {
        scores.clear();
    }
}
