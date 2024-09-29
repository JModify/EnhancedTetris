package me.modify.tetris.game.state;

public enum GameLevel {

    ONE(1,0, 1000),
    TWO(2, 10,900),
    THREE(3, 20,800),
    FOUR(4, 30,700),
    FIVE(5, 40,600),
    SIX(6, 50,500),
    SEVEN(7, 60,400),
    EIGHT(8, 70,300),
    NINE(9, 80,200),
    TEN(10, 90,100);

    private final int level;
    private final int fallSpeed;
    private final int threshold;

    GameLevel(int level, int threshold, int fallSpeed) {
        this.level = level;
        this.threshold = threshold;
        this.fallSpeed = fallSpeed;
    }

    public int getLevelNum() {
        return level;
    }

    public int getFallSpeed() {
        return fallSpeed;
    }

    public static GameLevel getByLevel(int level) {
        for (GameLevel gameLevel : values()) {
            if (gameLevel.getLevelNum() == level) {
                return gameLevel;
            }
        }
        return null;
    }

    public int getThreshold() {
        return threshold;
    }

    public static GameLevel nextLevel(GameLevel level) {
        for (GameLevel gameLevel : values()) {
            if ((level.getLevelNum() + 1) == gameLevel.getLevelNum()) {
                return gameLevel;
            }
        }

        // If at max return max.
        return GameLevel.TEN;
    }
}
