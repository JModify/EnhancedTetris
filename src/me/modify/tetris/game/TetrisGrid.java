package me.modify.tetris.game;

import java.util.List;

public class TetrisGrid {

    private int[][] grid;

    private int rows;
    private int columns;

    public TetrisGrid(int width, int height) {
        grid = new int[height][width];
        this.rows = height;
        this.columns = width;
        fillGrid();
    }

    public void updateSize(int width, int height) {
        this.rows = height;
        this.columns = width;
        fillGrid();
    }

    private void fillGrid() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; i < columns; i++) {
                grid[i][j] = 0;
            }
        }
    }

    public void insertAt(int row, int column, int value) {
        grid[row][column] = value;
    }

    public void removeAt(int row, int column) {
        grid[row][column] = 0;
    }

    public void clearRow(int row) {
        for (int i = 0; i < columns; i++) {
            grid[row][i] = 0;
        }
    }

    public void insertTetromino(Tetromino tetromino) {

    }

//    public void insertTetrominoAt(Tetromino tetromino, int x, int y) {
//        int[][] tetrominoShape = tetromino.getShape();
//        for (int i = 0; i < tetrominoShape.length; i++) {
//            for (int j = 0; j < tetrominoShape[i].length; j++) {
//                if (y + i < grid.length && x + j < grid[0].length) {
//                    grid[y + i][x + j] = tetrominoShape[i][j];
//                }
//            }
//        }
//    }

    

}
