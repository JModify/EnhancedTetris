package me.modify.tetris.game.score;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Handles saving and loading to the Tetris high scores file.
 */
public class HighScoresFile {

    private static boolean debug = false;
    private  static final String path = System.getProperty("user.home") + "/Desktop/Tetris-HighScores.json";
    private static final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    public static Set<Score> load()  {
        File file = new File(path);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    if (debug) {
                        System.out.println("[DEBUG] Created new high scores file on READ.");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return new HashSet<>();
            }
        }

        try (FileReader reader = new FileReader(file)) {
            Score[] deserialized = gson.fromJson(reader, Score[].class);

            if (deserialized == null || deserialized.length == 0) {
                if (debug) {
                    System.out.println("[DEBUG] High scores deserialization was empty, returning empty high scores data.");
                }
                return new HashSet<>();
            }

            if (debug) {
                System.out.println("[DEBUG] High scores deserialization successful. Data has been read.");
            }
            return new HashSet<>(Arrays.asList(deserialized));
        } catch (IOException e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    public static void save(Set<Score> scores)  {
        File file = new File(path);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    if (debug) {
                        System.out.println("[DEBUG] Created new high scores file on SAVE.");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        String serialized = gson.toJson(scores);

        // Save the JSON string to a file on the desktop
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(serialized);

            if (debug) {
                System.out.println("[DEBUG] Saved high scores data file.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
