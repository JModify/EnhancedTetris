package me.modify.tetris.audio;

import me.modify.tetris.audio.effects.SoundEffect;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class used to load sound effect files into a cache in the main class.
 */
public class SoundEffectLoader {

    public static void loadSoundEffects(Map<String, Clip> soundEffects) {
        try {
            File directory = new File(SoundEffect.FILE_DIRECTORY);

            if (directory.exists() && directory.isDirectory()) {
                File[] wavFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".wav"));

                if (wavFiles == null) {
                    System.out.println("Error loading sound files. Directory " + SoundEffect.FILE_DIRECTORY + " is invalid.");
                    return;
                }

                for (File file : wavFiles) {
                    try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        soundEffects.put(file.getName(), clip);
                    } catch (LineUnavailableException | UnsupportedAudioFileException e) {
                        System.out.println("Error loading sound file: " + file.getName());
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading sound files.");
        }
    }
}
