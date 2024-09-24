package me.modify.tetris.scores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HighScoresFile {
    public static final String path = System.getProperty("user.home") + "/Desktop/Tetris-HighScores.json";
    private static final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    public static Set<Score> load()  {
        File file = new File(path);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Created new high scores file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return new HashSet<>();
            }
        }

        try (FileReader reader = new FileReader(file)) {
            Score[] deserialized = gson.fromJson(reader, Score[].class);

            if (deserialized == null || deserialized.length == 0) {
                return new HashSet<>();
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
                    System.out.println("Created new high scores file.");
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
            System.out.println("High score saved to TetrisHighScores.json on your desktop.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
