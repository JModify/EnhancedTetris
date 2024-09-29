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

    /** Numerical value of this game level */
    private final int level;

    /** Falling speed in MS at this game level */
    private final int fallSpeed;

    /** Threshold required to reach next level */
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

    public int getThreshold() {
        return threshold;
    }

    /**
     * Retrieves the game level by numerical value.
     * @param level the game level as an integer.
     * @return the respective GameLevel object.
     */
    public static GameLevel getByLevel(int level) {
        for (GameLevel gameLevel : values()) {
            if (gameLevel.getLevelNum() == level) {
                return gameLevel;
            }
        }
        return null;
    }

    /**
     * Determines the next game level by adding 1 to the numerical value then
     * finding the game level with this value.
     * @param level current game level
     * @return the next game level
     */
    public static GameLevel nextLevel(GameLevel level) {
        for (GameLevel gameLevel : values()) {
            if ((level.getLevelNum() + 1) == gameLevel.getLevelNum()) {
                return gameLevel;
            }
        }

        // If game level is already at it's maximum (10) return that.
        return GameLevel.TEN;
    }
}
