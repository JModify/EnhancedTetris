package me.modify.tetris.game.state;

import java.awt.*;

public class Cell {

    public static final Color EMPTY_CELL_COLOR = Color.WHITE;

    public static final int EMPTY_CELL = 0;
    public static final int PLACEHOLDER = 80;
    public static final int FIXED_PLACEHOLDER = -80;

    private int x, y, data;
    public Cell(int x, int y, int data) {
        this.x = x;
        this.y = y;
        this.data = data;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    /**
     * Determines if a cell is fixed or not (stuck in place on the grid).
     * @return true if fixed, else false.
     */
    public boolean isFixed() {
        return data <= 0;
    }

    /**
     * Sets the cell to be fixed / stuck in place on the grid.
     */
    public void setFixed() {
        if (data > 0) {
            setData(data * -1);
        }
    }
}
