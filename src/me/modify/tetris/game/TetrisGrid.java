package me.modify.tetris.game;

import java.awt.*;
import java.util.Arrays;

public class TetrisGrid {

    private final Cell[][] grid;

    // Number of columns
    private int width;

    // Number of rows
    private int height;

    public final int FIXED_PLACEHOLDER = -80;
    public final int PLACEHOLDER = 80;

    private GameController gameController;
    public TetrisGrid(GameController gameController, int width, int height) {
        grid = new Cell[height][width];
        this.height = height;
        this.width = width;
        this.gameController = gameController;
    }

    public void updateSize(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public void addCell(Cell cell) {
        grid[cell.getY()][cell.getX()] = cell;
    }

    public void printGrid() {
        int[][] tempGrid = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int data = grid[i][j].getData();
                tempGrid[i][j] = data;
            }
        }

        System.out.println(Arrays.deepToString(tempGrid).replace("], ", "]\n"));
    }

    public void insertTetromino(Tetromino tetromino) {
        int xCenter = (width - tetromino.getShape()[0].length) / 2;
        int yStart = 0;

        int[][] shape = tetromino.getShape();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    int x = xCenter + j;
                    int y = yStart + i;

                    if (x >= 0 && x < width && y >= 0 && y < height) {
                        grid[y][x].setData(shape[i][j]);
                        grid[y][x].setColor(tetromino.getColor());
                    }
                }
            }
        }
    }

    public void shiftRight() {
        // Loop through rows / columns from right to left
        for (int i = 0; i < height; i++) {
            for (int j = width - 2; j >= 0; j--) {

                Cell cell = grid[i][j];
                if (cell == null) {
                    continue;
                }

                // Skip current iteration if data of current cell is 0.
                // Does not need to be moved down.
                if (cell.getData() == 0) {
                    continue; // Skip if cell data is 0
                }

                if (cell.getData() < 0 && cell.getData() != PLACEHOLDER) {
                    continue;
                }

                // Handle case where next column is out of bounds.
                int nextColumn = j + 1;
                if (nextColumn >= width || grid[i][nextColumn] == null) {
                    continue;
                }

                // Move current cell data to next cell
                Cell nextCell = grid[i][nextColumn];
//                nextCell.setData(cell.getData());
//                nextCell.setColor(cell.getColor());
                if (nextCell.getData() == 0) {
                    nextCell.setData(cell.getData());
                    nextCell.setColor(cell.getColor());
                    cell.setData(0);
                    cell.setColor(Color.WHITE);
                }
            }
        }
    }

    public void shiftLeft() {
        // Loop through rows / columns from left to right
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Cell cell = grid[i][j];
                if (cell == null) {
                    continue;
                }

                // Skip current iteration if data of current cell is 0.
                // Does not need to be moved down.
                if (cell.getData() == 0) {
                    continue; // Skip if cell data is 0
                }

                if (cell.getData() < 0 && cell.getData() != PLACEHOLDER) {
                    continue;
                }

                // Handle case where next column is out of bounds.
                int nextColumn = j - 1;
                if (nextColumn < 0 || grid[i][nextColumn] == null) {
                    continue;
                }

                // Move current cell data to next cell
                Cell nextCell = grid[i][nextColumn];
//                nextCell.setData(cell.getData());
//                nextCell.setColor(cell.getColor());
                if (nextCell.getData() == 0) {
                    nextCell.setData(cell.getData());
                    nextCell.setColor(cell.getColor());
                    cell.setData(0);
                    cell.setColor(Color.WHITE);
                }
            }
        }
    }

    public void shiftDown() {
        boolean fixAll = false;

        // Loop through rows / columns from top to bottom.
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {

                Cell cell = grid[i][j];
                if (cell == null) {
                    continue;
                }

                // Skip current iteration if data of current cell is 0.
                // Does not need to be moved down.
                if (cell.getData() == 0) {
                    continue; // Skip if cell data is 0
                }

                if (cell.getData() < 0 && cell.getData() != PLACEHOLDER) {
                    continue;
                }

                // Handle case where next row is out of bounds.
                int nextRow = i + 1;
                if (nextRow >= height || grid[nextRow][j] == null) {
                    cell.setFixed();
                    continue;
                }

                // Move current cell data to next cell if it is empty
                Cell nextCell = grid[nextRow][j];
                if (nextCell.getData() == 0) {
                    nextCell.setData(cell.getData());
                    nextCell.setColor(cell.getColor());
                    cell.setData(0);
                    cell.setColor(Color.WHITE);
                } else {
                    gameController.blockMovementInput();
                    fixAll = true;
                    break;
                }
            }
        }

        if(fixAll) {
            setAllFixed();
        }
    }

    public boolean allCellsFixed() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = grid[i][j];
                if (!cell.isFixed()) {
                    System.out.println("False");
                    return false;

                }
            }
        }
        System.out.println("True");
        return true;
    }

    public void clearPlaceholders() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = grid[i][j];
                if (cell.getData() == FIXED_PLACEHOLDER) {
                    cell.setData(0);
                }
            }
        }
    }

    public void setAllFixed() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = grid[i][j];
                if (!cell.isFixed()) {
                    cell.setFixed();
                }
            }
        }
    }
}
