package me.modify.tetris.audio.effects;

import me.modify.tetris.EnhancedTetrisApp;

import javax.sound.sampled.Clip;

/**
 * Represents a sound effect in the given file directory.
 */
public abstract class SoundEffect {

    public static final String FILE_DIRECTORY = "resources/audios";

    private final String fileName;

    public SoundEffect(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Plays the sound effect on a new thread.
     */
    public void play() {
        EnhancedTetrisApp app = EnhancedTetrisApp.getInstance();
        if (app.getConfiguration().isSoundEffects()) {
            Clip clip = app.getSoundEffects().get(fileName);
            if (clip != null) {
                new Thread(() -> {
                    clip.setFramePosition(0);
                    clip.start();
                }).start();
            } else {
                System.out.println("Failed to play sound: " + fileName);
            }
        }
    }
}
