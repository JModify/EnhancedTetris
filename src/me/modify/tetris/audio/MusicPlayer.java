package me.modify.tetris.audio;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import me.modify.tetris.EnhancedTetrisApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class MusicPlayer implements Runnable {

    private final String filePath = "resources/audios/background.mp3";

    private Player player;
    private boolean isPaused;
    private boolean isStopped;
    private boolean repeat;

    public MusicPlayer() {
        this.isPaused = false;
        this.isStopped = false;
        this.repeat = false;
    }
    private synchronized void initPlayer(){
        try {
            FileInputStream fileInputStream = new
                    FileInputStream(filePath);
            player = new Player(fileInputStream);
        } catch (FileNotFoundException | JavaLayerException e) {
            System.out.println("Error: fail load mp3 file." + e.getMessage());
        }
    }
    @Override
    public void run() {
        do {
            if(!isPaused) {
                try {
                    initPlayer();
                    player.play();
                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(100); // Small delay to ensure the loop condition is checked properly
                } catch (InterruptedException e) {
                    System.out.println("Error during repeat: " + e.getMessage());
                }
            }
        } while (repeat && !isStopped);
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    // Method to stop the playback completely
    public synchronized void stop() {
        if (EnhancedTetrisApp.getInstance().getConfiguration().isMusic()) {
            isStopped = true;
            player.close();
        }
    }

    // Method to set repeat mode
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}