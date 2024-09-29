package me.modify.tetris.game;

import me.modify.tetris.game.state.Cell;
import me.modify.tetris.game.state.RotatedPoint;
import me.modify.tetris.game.state.Tetromino;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GameGrid {

    private Cell[][] grid;

    private int width;
    private int height;

    private Tetromino currentTetromino;

    private int x;
    private int y;

    public GameGrid(int width, int height) {
        grid = new Cell[height][width];
        this.width = width;
        this.height = height;
    }

    public void fill() {
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                Cell cell = new Cell(r, c, Cell.EMPTY_CELL);
                grid[r][c] = cell;
            }
        }
    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
    }

    public void paint(Graphics g) {
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                int CELL_SIZE = 20;
                int x = c * CELL_SIZE;
                int y = r * CELL_SIZE;

                Cell cell = grid[r][c];
                if (cell.getData() == currentTetromino.getId()) {
                    g.setColor(currentTetromino.getColor());
                    g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    public void updateSize(int width, int height) {
        this.grid = new Cell[height][width];
        this.width = width;
        this.height = height;
        fill();
    }

    /**
     * Determines if there is space for the given tetromino to be inserted into the grid.
     * @param tetromino tetromino to check
     * @return true if it can be inserted, else false.
     */
    public boolean canInsertTetromino(Tetromino tetromino) {
        int xCenter = (width - tetromino.getShape()[0].length) / 2;
        int yStart = 0;

        int[][] shape = tetromino.getShape();

        boolean doesFit = true;

        // Uses same logic here as insertTetromino() but does not actually insert.
        // Just checks if the cell it is trying to insert at is EMPTY. If all
        // cells it wants to insert into are empty, then the tetromino fits.
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    int x = xCenter + j;
                    int y = yStart + i;

                    if (x >= 0 && x < width && y < height) {
                        Cell cell = grid[y][x];
                        if (cell.getData() != 0) {
                            doesFit = false;
                            break;
                        }
                    }
                }
            }
        }

        return doesFit;
    }

    /**
     * Insert a tetromino into the grid at the top (centered).
     * @param tetromino tetromino to insert
     */
    public void insertTetromino(Tetromino tetromino) {
        // Determines the approximate center of the shape for this tetromino.
        int xCenter = (width - tetromino.getShape()[0].length) / 2;

        // Row for which this tetromino should be spawned in at.
        int yStart = 0;

        int[][] shape = tetromino.getShape();

        // Loop shape's rows.
        for (int i = 0; i < shape.length; i++) {

            // Loop through shape's columns
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    int x = xCenter + j;
                    int y = yStart + i;

                    // Set the data values of the cells where the shape is
                    // being spawned to the ID of the shape. Also changes color
                    // of cell panel to match shape's color.
                    if (x >= 0 && x < width && y < height) {
                        grid[y][x].setData(shape[i][j]);
                    }
                }
            }
        }

        this.x = width / 2;
        this.y = 0;
        this.currentTetromino = tetromino;
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
    private boolean canMoveDirection(int rowOffset, int columnOffset) {
        for (Point point : getTetrominoAsPoints()) {
            int row = (int) point.getX();
            int column = (int) point.getY();

            int nextRow = row + rowOffset;
            int nextColumn = column + columnOffset;

            // Check if next row and column is not outside the game board.
            if (nextRow >= height || nextColumn >= width ||
                    nextColumn < 0 || grid[nextRow][nextColumn] == null) {
                return false;
            }

            // If cell is a placeholder and the direction is going down. Disregard check.
            Cell cell = grid[row][column];
            if (cell.getData() == Cell.PLACEHOLDER && rowOffset == 1) {
                continue;
            }

            if (grid[nextRow][nextColumn].getData() < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Shifts the current falling tetromino downwards.
     * Game automatically does this every second but player can also use it to make it fall faster.
     * @param shiftFixedCells whether fixed cells should ALSO be shifted down.
     */
    public void shiftDown(boolean shiftFixedCells) {
        if (!canMoveDirection(1, 0) && !shiftFixedCells) {
            setAllFixed();
        }

        // Loop through rows / columns from top to bottom.
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {

                Cell cell = grid[i][j];
                if (!isCellDynamic(cell) && !shiftFixedCells) {
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
                if(nextCell.getData() == 0 || nextCell.getData() == Cell.PLACEHOLDER) {
                    swapProperties(cell, nextCell);
                }
            }
        }

        this.y++;
    }

    /**
     * Shifts the current falling tetromino one cell to the left
     * Checks if it can move in that direction before hand using canMoveDirection().
     */
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
                swapProperties(cell, nextCell);
            }
        }

        this.x--;
    }

    /**
     * Shifts the current falling tetromino one cell to the right.
     * Checks if it can move in that direction before hand using canMoveDirection().
     */
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
                swapProperties(cell, nextCell);
            }
        }

        this.x++;
    }

    /**
     * Helper method used to swap the properties of two given cells.
     * Only does so under the condition that the other cell is empty.
     * @param cell - cell swapping from.
     * @param otherCell - cell swapping too.
     */
    private void swapProperties(Cell cell, Cell otherCell) {
        if (otherCell.getData() == 0 || otherCell.getData() == Cell.PLACEHOLDER) {
            int otherCellDataCopy = otherCell.getData();
            otherCell.setData(cell.getData());
            cell.setData(otherCellDataCopy);
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
        if (cell.getData() == Cell.EMPTY_CELL) {
            return false;
        }

        // Handle case where cell is a fixed piece or placeholder.
        if (cell.getData() < 0) {
            return false;
        }

        return true;
    }

    /**
     * Clears any placeholders currently on the grid (sets them to be empty).
     */
    public void clearPlaceholders() {
        forEachCell(cell -> {
            if (cell.getData() == Cell.FIXED_PLACEHOLDER) {
                cell.setData(Cell.EMPTY_CELL);
            }
        });
    }

    /**
     * Sets all cells in the grid to be fixed.
     */
    public void setAllFixed() {
        forEachCell(cell -> {
            if (!cell.isFixed()) cell.setFixed();
        });
    }

    /**
     * Determines if all cells in the grid are fixed.
     * @return true if all are fixed, else false.
     */
    public boolean allCellsFixed() {

        //return isGridInCondition(cell -> !cell.isFixed());

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = grid[i][j];

                // If a cell is not fixed, then the assumption
                // that ALL cells are fixed cannot be true.
                if (!cell.isFixed()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Loops through entire grid and clears any rows which are full.
     * @return number of rows cleared
     */
    public int clearRows() {
        int rowsCleared = 0;

        // Loop through every row
        for (int i = 0; i < height; i++) {

            boolean full = true;

            // Loop through each column
            for (int j = 0; j < width; j++) {
                Cell cell = grid[i][j];

                // If a single cell is empty for this row, the row is NOT full
                // and should not be cleared.
                if (cell.getData() == 0) {
                    full = false;
                    break;
                }
            }

            // If the row is full, clear it and shift entire grid down.
            if (full) {
                clearRow(i);
                shiftDown(true);
                rowsCleared++;
            }
        }

        return rowsCleared;
    }

    /**
     * Helper method which clears a row from the grid at the specified index.
     * @param rowIndex index or row to clear
     */
    private void clearRow(int rowIndex) {
        for (int j = 0; j < width; j++) {
            grid[rowIndex][j].setData(Cell.EMPTY_CELL);
            //grid[rowIndex][j].setColor(Cell.EMPTY_CELL);
        }
    }

    public void forEachCell(Consumer<Cell> action) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = grid[i][j];
                action.accept(cell);
            }
        }
    }

    public void clearGrid(){
        forEachCell(cell -> {
            cell.setData(Cell.EMPTY_CELL);
        });
    }

    /**
     * Rotates the current falling tetromino by 90 degrees (clockwise).
     * Does nothing if the rotation is not valid (outside game board or collides with another fixed cell).
     */
    public void rotateTetromino(){
        if (!Tetromino.doesRotate(currentTetromino)) {
            return;
        }

        List<Point> points = getTetrominoAsPoints();
        Point pivot = new Point(y, x);

        List<RotatedPoint> rotatedPoints = points.stream().map(p -> rotatePoint(p, pivot)).toList();

        // If the rotation is not valid, return the method and do nothing.
        if(!isRotationValid(rotatedPoints)) {
            return;
        }
        replaceTetromino(points, rotatedPoints);
    }

    /**
     * Helper method which determines if the rotation of a Tetromino is valid. A rotation is valid if and only if:
     * 1. No rotated points are outside the range of the grid.
     * 2. No rotated points collide with fixed cells in the grid
     * @param rotatedPoints list of rotated points
     * @return true if rotation is valid, else false.
     */
    private boolean isRotationValid(List<RotatedPoint> rotatedPoints) {
        for(RotatedPoint rotatedPoint : rotatedPoints) {
            int row = (int) rotatedPoint.getPoint().getX();
            int column = (int) rotatedPoint.getPoint().getY();

            // Check row is inside bounds of game board.
            if (row >= height || row < 0) {
                return false;
            }

            // Check column is inside bounds of game board.
            if (column >= width || column < 0) {
                return false;
            }

            // Check cell in question is not somehow null.
            Cell cell = grid[row][column];
            if (cell == null) {
                return false;
            }

            // Check Cell in question is not fixed (stuck in place at the bottom).
            if (cell.getData() < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Helper method which rotates a given point using a pivot.
     * @param point point to rotate
     * @param pivot pivot point
     * @return the rotated point
     */
    private RotatedPoint rotatePoint(Point point, Point pivot) {
        int x = point.x - pivot.x;
        int y = point.y - pivot.y;

        boolean placeholder = grid[(int) point.getX()][(int) point.getY()].getData() == Cell.PLACEHOLDER;

        int rotatedX = pivot.x + y;
        int rotatedY = pivot.y - x;

        return new RotatedPoint(new Point(rotatedX, rotatedY), placeholder);
    }

    /**
     * Replaces the falling tetromino on the grid with the rotated version of it.
     * @param tetrominoPoints original tetromino as points
     * @param replacementPoints rotated tetromino as points
     */
    private void replaceTetromino(List<Point> tetrominoPoints, List<RotatedPoint> replacementPoints) {

        // Clear tetromino at current points, but preserve placeholders.
        for (Point point : tetrominoPoints) {
            int row = (int) point.getX();
            int column = (int) point.getY();

            Cell cell = grid[row][column];
            cell.setData(Cell.EMPTY_CELL);
            //cell.setColor(Cell.EMPTY_CELL);
        }

        // Place the new rotated tetromino, but skip placeholder cells in the new positions.
        for (RotatedPoint rotatedPoint : replacementPoints) {
            int row = (int) rotatedPoint.getPoint().getX();
            int column = (int) rotatedPoint.getPoint().getY();

            Cell cell = grid[row][column];

            // Only update cells that are empty or placeholders (not fixed cells)
            if (rotatedPoint.isPlaceholder()) {
                cell.setData(Cell.PLACEHOLDER);
                //cell.setColor(Cell.EMPTY_CELL);
                continue;
            }

            cell.setData(currentTetromino.getId());
            //cell.setColor(tetromino.getColor());
        }
    }

    /**
     * Retrieves the falling tetromino as a list of points.
     * @return the falling tetromino as a list of points.
     */
    private List<Point> getTetrominoAsPoints() {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = grid[i][j];

                if (cell.getData() == Cell.EMPTY_CELL) {
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
}
