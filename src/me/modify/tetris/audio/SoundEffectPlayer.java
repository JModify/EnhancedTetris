package me.modify.tetris.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundEffectPlayer {

    private Map<String, Clip> soundEffects;

    public SoundEffectPlayer() {
        soundEffects = new HashMap<>();
    }

    public void loadSoundEffects(String[] soundFiles) {
        for (String fileName : soundFiles) {
            try {
                File soundFile = new File("resources/audios/" + fileName + ".wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                soundEffects.put(fileName, clip);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                System.out.println("Error loading sound file: " + fileName);
                e.printStackTrace();
            }
        }
    }

    public void playSound(final String name) {
        Clip clip = soundEffects.get(name);
        if (clip != null) {
            new Thread (() -> {
                clip.setFramePosition(0);
                clip.start();
            }).start();
        } else {
            System.out.println("Failed to play sound: " + name);
        }
    }

    public void close() {
        for (Clip clip : soundEffects.values()) {
            clip.close();
        }
    }
}
