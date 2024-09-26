package me.modify.tetris.game;

public enum GameLevel {

    ONE(1, 1000),
    TWO(2, 900),
    THREE(3, 800),
    FOUR(4, 700),
    FIVE(5, 600),
    SIX(6, 500),
    SEVEN(7, 400),
    EIGHT(8, 300),
    NINE(9, 200),
    TEN(10, 100);

    private final int level;
    private final int fallSpeed;

    GameLevel(int level, int fallSpeed) {
        this.level = level;
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
