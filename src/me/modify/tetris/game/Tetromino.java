package me.modify.tetris.game;

import java.util.Arrays;
import java.util.Random;

public enum Tetromino {

    I(1, "I", new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    }),

    J(2, "J", new int[][]{
            {2, 0, 0, 0},
            {2, 2, 2, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    }),

    L(3, "L", new int[][]{
            {0, 0, 3, 0},
            {3, 3, 3, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    }),

    O(4, "O", new int[][]{
            {4, 4, 0, 0},
            {4, 4, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    }),

    S(5, "S", new int[][]{
            {0, 5, 5, 0},
            {5, 5, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    }),

    T(6, "T", new int[][]{
            {0, 6, 0, 0},
            {6, 6, 6, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    }),

    Z(7, "Z", new int[][]{
            {7, 7, 0, 0},
            {0, 7, 7, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    });

    private int id;
    private String name;
    private int[][] shape;

    Tetromino(int id, String name, int[][] shape) {
        this.id = id;
        this.name = name;
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

    public Tetromino randomTetromino() {
        Random random = new Random();
        int randId = random.nextInt(Tetromino.values().length);
        return Arrays.stream(Tetromino.values()).filter(
                t -> t.getId() == randId).findFirst().get();
    }

}
