package me.modify.tetris.game.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigurationFile {

    private static boolean debug = false;
    private static final String path = System.getProperty("user.home") + "/Desktop/Tetris-Configuration.json";
    private static final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    public static GameConfiguration load()  {
        File file = new File(path);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    if (debug) {
                        System.out.println("[DEBUG] Created new game configuration file.");
                    }
                }
            } catch (IOException e) {
                if (debug) {
                    System.out.println("[DEBUG] Failed to create new game configuration file.");
                    e.printStackTrace();
                }
                return new GameConfiguration();
            }
        }

        try (FileReader reader = new FileReader(file)) {
            GameConfiguration deserialized = gson.fromJson(reader, GameConfiguration.class);

            if (deserialized == null) {
                if (debug) {
                    System.out.println("[DEBUG] Game Configuration deserialization was null, returning empty configuration.");
                }
                return new GameConfiguration();
            }

            return deserialized;
        } catch (IOException e) {
            if (debug) {
                System.out.println("[DEBUG] Failed to read game configuration file.");
                e.printStackTrace();
            }
            return new GameConfiguration();
        }
    }

    public static void save(GameConfiguration configuration)  {
        File file = new File(path);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    if (debug) {
                        System.out.println("[DEBUG] Created new game configuration file.");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        String serialized = gson.toJson(configuration);

        // Save the JSON string to a file on the desktop
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(serialized);

            if (debug) {
                System.out.println("[DEBUG] Saved game configuration data file.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
