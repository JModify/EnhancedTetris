package me.modify.tetris.game;

import java.awt.*;
import java.util.Arrays;

public class TetrisGrid {

    private final Cell[][] grid;

    // Number of columns
    private int width;

    // Number of rows
    private int height;

    public TetrisGrid(int width, int height) {
        grid = new Cell[height][width];
        this.height = height;
        this.width = width;
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

    public void turnCellFixed(Cell cell) {
        cell.setData(cell.getData() * -1);
    }

    public void shiftGridDown() {

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

                // Handle case where next row is out of bounds.
                int nextRow = i + 1;
                if (nextRow >= height || grid[nextRow][j] == null) {
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
                    // Handle cases where the next cell is not empty (e.g., if the grid is full)
                    // You may want to add additional logic here if needed
                }
            }
        }

//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                Cell cell = grid[i][j];
//
//                if (cell.getData() == 0) {
//                    continue;
//                }
//
//                int nextRow = i + 1;
//                if (grid[nextRow][j] == null || nextRow >= height) {
//                    continue;
//                }
//
//                Cell nextCell = grid[nextRow][j];
//                nextCell.setData(cell.getData());
//
//                int aboveRow = i - 1;
//                if (aboveRow < 0) {
//                    cell.setData(0);
//                    cell.setColor(Color.WHITE);
//                    continue;
//                }
//
//                Cell aboveCell = grid[aboveRow][j];
//                cell.setData(aboveCell.getData());
//                cell.setColor(aboveCell.getColor());
//            }
//        }
    }

    public void getCellAt(int x, int y) {

    }

    

}
