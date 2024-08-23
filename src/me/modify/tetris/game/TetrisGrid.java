package me.modify.tetris.game;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.BinaryOperator;

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

    /**
     * Insert a tetromino into the grid at the top (centered).
     * @param tetromino tetromino to insert
     */
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

    public List<Point> getCurrentTetromino() {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = grid[i][j];

                if (cell.getData() == 0) {
                    continue;
                }

                if (cell.getData() < 0) {
                    continue;
                }

                points.add(new Point(i, j));
            }
        }
        return points;
    }

    /**
     * Checks movement in a specific direction defined by provided offset values.
     * Possible Values:
     * RIGHT: 0, 1
     * LEFT: 0, -1
     * DOWN: 1, 0
     *
     * @param rowOffset - row offset value
     * @param columnOffset - column offset
     * @return true if tetromino can move in that direction otherwise false.
     */
    public boolean canMoveDirection(int rowOffset, int columnOffset) {
        for (Point point : getCurrentTetromino()) {
            int row = (int) point.getX();
            int column = (int) point.getY();

            int nextRow = row + rowOffset;
            int nextColumn = column + columnOffset;

            if (nextRow >= height || nextColumn >= width ||
                    nextColumn < 0 || grid[nextRow][nextColumn] == null) {
                return false;
            }

            // If cell is a placeholder and the direction is going down. Disregard check.
            Cell cell = grid[row][column];
            if (cell.getData() == PLACEHOLDER && rowOffset == 1) {
                continue;
            }

            if (grid[nextRow][nextColumn].getData() < 0) {
                return false;
            }
        }
        return true;
    }

    public void shiftDown() {
        if (!canMoveDirection(1, 0)) {
            setAllFixed();
        }

        // Loop through rows / columns from top to bottom.
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {

                Cell cell = grid[i][j];
                if (!isCellDynamic(cell)) {
                    continue;
                }

                // Handle case where next row is out of bounds.
                int nextRow = i + 1;
                if (nextRow >= height || grid[nextRow][j] == null) {
                    //cell.setFixed();
                    continue;
                }

                // Move current cell data to next cell if it is empty
                Cell nextCell = grid[nextRow][j];
                swapCells(cell, nextCell);
            }
        }
    }

    public void shiftLeft() {
        if (!canMoveDirection(0, -1)) {
            return;
        }

        // Loop through rows / columns from left to right
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Cell cell = grid[i][j];
                if (!isCellDynamic(cell)) {
                    continue;
                }

                // Handle case where next column is out of bounds.
                int nextColumn = j - 1;
                if (nextColumn < 0 || grid[i][nextColumn] == null) {
                    continue;
                }

                // Move current cell data to next cell
                Cell nextCell = grid[i][nextColumn];
                swapCells(cell, nextCell);
            }
        }
    }

    public void shiftRight() {
        if (!canMoveDirection(0, 1)) {
            return;
        }

        // Loop through rows / columns from right to left
        for (int i = 0; i < height; i++) {
            for (int j = width - 2; j >= 0; j--) {

                Cell cell = grid[i][j];
                if (!isCellDynamic(cell)) {
                    continue;
                }

                // Handle case where next column is out of bounds.
                int nextColumn = j + 1;
                if (nextColumn >= width || grid[i][nextColumn] == null) {
                    continue;
                }

                // Move current cell data to next cell
                Cell nextCell = grid[i][nextColumn];
                swapCells(cell, nextCell);
            }
        }
    }

    /**
     * Helper method used to swap the properties of two given cells.
     * @param cell - cell swapping from.
     * @param otherCell - cell swapping too.
     */
    private void swapCells(Cell cell, Cell otherCell) {
        if (otherCell.getData() == 0 || otherCell.getData() == PLACEHOLDER) {
            otherCell.setData(cell.getData());
            otherCell.setColor(cell.getColor());
            cell.setData(0);
            cell.setColor(Color.WHITE);
        }
    }

    /**
     * Helper method used to check if a cell is dynamic and requires shifting.
     * @param cell - cell to check
     */
    private boolean isCellDynamic(Cell cell) {
        // Handle case where cell does not exist.
        if (cell == null) {
            return false;
        }

        // Handle case where cell is empty.
        if (cell.getData() == 0) {
            return false;
        }

        // Handle case where cell is a fixed piece or placeholder.
        if (cell.getData() < 0 /*&& cell.getData() != PLACEHOLDER*/) {
            return false;
        }

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

    public boolean allCellsFixed() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = grid[i][j];
                if (!cell.isFixed()) {
                    return false;

                }
            }
        }
        return true;
    }
}
