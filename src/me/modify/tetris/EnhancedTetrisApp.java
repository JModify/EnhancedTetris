package me.modify.tetris;

import me.modify.tetris.audio.MusicPlayer;
import me.modify.tetris.audio.SoundEffectLoader;
import me.modify.tetris.game.GameController;
import me.modify.tetris.game.config.ConfigurationFile;
import me.modify.tetris.game.config.GameConfiguration;
import me.modify.tetris.game.score.HighScores;
import me.modify.tetris.game.score.HighScoresFile;
import me.modify.tetris.ui.MenuFacade;
import me.modify.tetris.ui.MenuType;
import me.modify.tetris.ui.frames.MainFrame;
import me.modify.tetris.ui.frames.TetrisSplashScreen;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class for application.
 * Displays splash screen upon launch
 */
public class EnhancedTetrisApp {

    /** Instance for this main class */
    private static EnhancedTetrisApp instance;

    /** Game controller for the game itself */
    private final GameController gameController;

    private final HighScores highScores;
    private final GameConfiguration configuration;

    /** Main frame of the application */
    private final MainFrame mainFrame;


    private final MusicPlayer musicPlayer;
    private final Map<String, Clip> soundEffects;
    /**
     * Constructs a new EnhancedTetrisApp and initializes the game controller.
     */
    public EnhancedTetrisApp() {
        setInstance(this);

        this.highScores = new HighScores(HighScoresFile.load());
        this.configuration = ConfigurationFile.load();

        this.gameController = new GameController();
        this.mainFrame = new MainFrame();
        this.musicPlayer = new MusicPlayer();
        this.soundEffects = new HashMap<>();
    }

    /**
     * Retrieves the game controller.
     * @return game controller
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Retrieves the main frame of the application
     * @return main frame
     */
    public MainFrame getMainFrame() {
        return this.mainFrame;
    }

    public HighScores getHighScores() {
        return highScores;
    }

    public GameConfiguration getConfiguration() {
        return configuration;
    }

    public static void main(String[] args) {
        EnhancedTetrisApp main = new EnhancedTetrisApp();

        // Load sound files
        SoundEffectLoader.loadSoundEffects(main.getSoundEffects());

        // Display splash screen for 3 seconds.
        TetrisSplashScreen splashScreen = new TetrisSplashScreen(3000);
        splashScreen.showSplash();


        // Instantiate main frame and open main menu panel.
        SwingUtilities.invokeLater(() -> {
            main.getMainFrame().init();

            MenuFacade.openPanel(MenuType.MAIN_MENU);
        });

        // Save data files in event of another graceful application exit.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            main.shutdown();
            System.out.println("[SHUTDOWN] All data files successfully saved.");
        }));

    }

    /**
     * Retrieves the main class instance (this).
     * @return main class instance.
     */
    public static EnhancedTetrisApp getInstance() {
        return instance;
    }

    /**
     * Sets the main class instance.
     * @param instance instance to set too.
     */
    public void setInstance(EnhancedTetrisApp instance) {
        EnhancedTetrisApp.instance = instance;
    }

    private void saveDataFiles() {
        HighScoresFile.save(highScores.getScores());
        ConfigurationFile.save(gameController.getConfiguration());
    }

    public synchronized MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public Map<String, Clip> getSoundEffects() {
        return soundEffects;
    }

    public void shutdown() {
        for (Clip clip : soundEffects.values()) {
            clip.close();
        }
        //musicPlayer.stop();
        saveDataFiles();
    }
}
