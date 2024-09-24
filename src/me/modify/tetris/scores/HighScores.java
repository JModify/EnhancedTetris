package me.modify.tetris.scores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.*;
import java.util.*;

public class HighScores {

    private Set<Score> scores;

    public HighScores(Set<Score> scores) {;
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

    public Set<Score> getScores() {
        return scores;
    }

    public boolean isHighScore(int score) {
        if (scores.size() < 10) {
            return true;
        }

        return getTopScores().stream().anyMatch(s -> score > s.getScore());
    }
}
