package me.modify.tetris.game;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public enum Tetromino {

    I(1, new Point(0, 1), "I", Color.CYAN, new int[][]{
            {1, 1, 1, 1}
    }),

    J(2, new Point(1, 1), "J", Color.BLUE, new int[][]{
            {2, 80, 80},
            {2, 2, 2}
    }),

    L(3, new Point(1, 1), "L", Color.ORANGE, new int[][]{
            {80, 80, 3},
            {3, 3, 3}
    }),

    O(4, new Point(1, 1), "O", Color.YELLOW, new int[][]{
            {4, 4},
            {4, 4}
    }),

    S(5, new Point(1, 1), "S", Color.GREEN, new int[][]{
            {80, 5, 5},
            {5, 5, 80}
    }),

    T(6, new Point(1, 1), "T", Color.MAGENTA, new int[][]{
            {80, 6, 80},
            {6, 6, 6}
    }),

    Z(7, new Point(1, 1), "Z", Color.RED,  new int[][]{
            {7, 7, 80},
            {80, 7, 7}
    });

    private final int id;
    private final Point pivotPoint;

    private final String name;
    private final int[][] shape;
    private final Color color;

    Tetromino(int id, Point pivotPoint, String name, Color color, int[][] shape) {
        this.id = id;
        this.pivotPoint = pivotPoint;
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

    public Point getPivotPoint() {
        return pivotPoint;
    }

    public static Tetromino randomTetromino() {
        Random random = new Random();
        int randId = random.nextInt(Tetromino.values().length) + 1;
        return Arrays.stream(Tetromino.values()).filter(
                t -> t.getId() == randId).findFirst().get();
    }

    public static Tetromino getByID(int id) {
        return Arrays.stream(Tetromino.values()).filter(t -> t.getId() == id).findFirst().get();
    }
}
