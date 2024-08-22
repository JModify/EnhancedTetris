package me.modify.tetris.game;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public enum Tetromino {

    I(1, -1, "I", Color.CYAN, new int[][]{
            {80, 80, 80, 80},
            {1, 1, 1, 1}
    }),

    J(2, -2, "J", Color.BLUE, new int[][]{
            {2, 80, 80, 80},
            {2, 2, 2, 80}
    }),

    L(3, -3, "L", Color.ORANGE, new int[][]{
            {80, 80, 3, 80},
            {3, 3, 3, 80}
    }),

    O(4, -4, "O", Color.YELLOW, new int[][]{
            {4, 4, 80, 80},
            {4, 4, 80, 80}
    }),

    S(5, -5, "S", Color.GREEN, new int[][]{
            {80, 5, 5, 80},
            {5, 5, 80, 80}
    }),

    T(6, -6, "T", Color.MAGENTA, new int[][]{
            {80, 6, 80, 80},
            {6, 6, 6, 80}
    }),

    Z(7, -7, "Z", Color.RED,  new int[][]{
            {7, 7, 80, 80},
            {80, 7, 7, 80}
    });

    private final int id;
    private final int fixedId;

    private final String name;
    private final int[][] shape;
    private final Color color;

    Tetromino(int id, int fixedId, String name, Color color, int[][] shape) {
        this.id = id;
        this.fixedId = fixedId;
        this.name = name;
        this.color = color;
        this.shape = shape;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int[][] getShape() {
        return this.shape;
    }

    public Color getColor() {
        return color;
    }

    public int getStationaryId() {
        return fixedId;
    }

    public static Tetromino randomTetromino() {
        Random random = new Random();
        int randId = random.nextInt(Tetromino.values().length) + 1;
        System.out.println(randId);

        return Arrays.stream(Tetromino.values()).filter(
                t -> t.getId() == randId).findFirst().get();
    }

}
