package me.modify.tetris.game.config;

import com.google.gson.annotations.Expose;

/**
 * GameConfiguration holds all data for the configuration of the game.
 * Saves across switching menus but currently has no effect on the game.
 */
public class GameConfiguration {

    @Expose
    private int gameLevel;

    @Expose
    private int fieldWidth;

    @Expose
    private int fieldHeight;

    @Expose
    private boolean music;

    @Expose
    private boolean soundEffects;

    @Expose
    private boolean aiPlay;

    @Expose
    private boolean extendMode;

    public final int FIELD_WIDTH_MIN = 5;
    public final int FIELD_WIDTH_MAX = 15;
    public final int FIELD_WIDTH_DEFAULT = 10;

    public final int FIELD_HEIGHT_MIN = 15;
    public final int FIELD_HEIGHT_MAX = 30;
    public final int FIELD_HEIGHT_DEFAULT = 20;

    public final int GAME_LEVEL_MIN = 1;
    public final int GAME_LEVEL_MAX = 10;
    private final int GAME_LEVEL_DEFAULT = 1;

    public final String CHECKBOX_OFF = "OFF";
    public final String CHECKBOX_ON = "ON";

    public GameConfiguration() {
        this.gameLevel = GAME_LEVEL_DEFAULT;
        this.fieldWidth = FIELD_WIDTH_DEFAULT;
        this.fieldHeight = FIELD_HEIGHT_DEFAULT;

        this.music = true;
        this.soundEffects = true;
        this.aiPlay = false;
        this.extendMode = false;
    }

    public GameConfiguration(int gameLevel, int fieldWidth, int fieldHeight,
                             boolean music, boolean soundEffects,
                             boolean aiPlay, boolean extendMode) {
        this.gameLevel = gameLevel;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.music = music;
        this.soundEffects = soundEffects;
        this.aiPlay = aiPlay;
        this.extendMode = extendMode;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public void setFieldWidth(int fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public void setFieldHeight(int fieldHeight) {
        this.fieldHeight = fieldHeight;
    }

    public boolean isMusic() {
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public boolean isSoundEffects() {
        return soundEffects;
    }

    public void setSoundEffects(boolean soundEffects) {
        this.soundEffects = soundEffects;
    }

    public boolean isAiPlay() {
        return aiPlay;
    }

    public void setAiPlay(boolean aiPlay) {
        this.aiPlay = aiPlay;
    }

    public boolean isExtendMode() {
        return extendMode;
    }

    public void setExtendMode(boolean extendMode) {
        this.extendMode = extendMode;
    }

    public void toggleSound() {
        this.soundEffects = !soundEffects;
    }
}
