package me.modify.tetris.audio;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import me.modify.tetris.EnhancedTetrisApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class MusicPlayer implements Runnable {

    private final String filePath = "resources/audios/background.mp3";
    private Thread musicThread;

    private Player player;
    private boolean isStopped;
    private boolean repeat;

    public MusicPlayer() {
        this.isStopped = false;
        this.repeat = true;
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
            if(!isStopped) {
                try {
                    initPlayer();
                    player.play();
                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        } while (repeat && !isStopped);
    }

    public synchronized void start() {
        isStopped = false;
        if (musicThread == null || !musicThread.isAlive()) {
            musicThread = new Thread(this);
            musicThread.start();
        }
    }

    public synchronized void stop() {
        isStopped = true;
        if (player != null) {
            player.close();
        }

        if (musicThread != null && musicThread.isAlive()) {
            musicThread.interrupt();
        }
    }
}