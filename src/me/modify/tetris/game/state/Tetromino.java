package me.modify.tetris.game.state;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public enum Tetromino {

    I(1, "I", Color.CYAN, new int[][]{
            {1, 1, 1, 1}
    }),

    J(2, "J", Color.BLUE, new int[][]{
            {2, 80, 80},
            {2, 2, 2}
    }),

    L(3, "L", Color.ORANGE, new int[][]{
            {80, 80, 3},
            {3, 3, 3}
    }),

    O(4, "O", Color.YELLOW, new int[][]{
            {4, 4},
            {4, 4}
    }),

    S(5, "S", Color.GREEN, new int[][]{
            {80, 5, 5},
            {5, 5, 80}
    }),

    T(6, "T", Color.MAGENTA, new int[][]{
            {80, 6, 80},
            {6, 6, 6}
    }),

    Z(7, "Z", Color.RED, new int[][]{
            {7, 7, 80},
            {80, 7, 7}
    });

    /** ID of the Tetromino */
    private final int id;

    /** Name of the tetromino */
    private final String name;

    /** 2D Shape of the tetromino */
    private final int[][] shape;

    /** Color of the Tetromino */
    private final Color color;

    /**
     * Constructor for a given Tetromino.
     * Initializes member variables using parsed information.
     * @param id id of tetromino
     * @param name name of tetromino
     * @param color color of tetromino
     * @param shape shape of tetromino
     */
    Tetromino(int id, String name, Color color, int[][] shape) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.shape = shape;
    }

    /**
     * Retrieves the ID of this Tetromino.
     * @return id of tetromino
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retrieves the name of this Tetromino.
     * @return name of tetromino
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the 2D shape of this Tetromino.
     * @return shape as a 2D array for tetromino
     */
    public int[][] getShape() {
        return this.shape;
    }

    /**
     * Retrieves the color of this Tetromino.
     * @return color of tetromino
     */
    public Color getColor() {
        return color;
    }

    /**
     * Selects a random Tetromino from all possible types.
     * @return a random tetromino
     */
    public static Tetromino randomTetromino() {
        Random random = new Random();
        int randId = random.nextInt(Tetromino.values().length) + 1;
        return Arrays.stream(Tetromino.values()).filter(
                t -> t.getId() == randId).findFirst().get();
    }

    /**
     * Retrieves a Tetromino using only its ID.
     * @param id id of desired Tetromino
     * @return the tetromino under the given id.
     */
    public static Tetromino getByID(int id) {
        return Arrays.stream(Tetromino.values()).filter(t -> t.getId() == id || t.getId() == -id).findFirst().get();
    }

    public static boolean doesRotate(Tetromino tetromino) {
        return !tetromino.getName().equalsIgnoreCase("O");
    }
}
