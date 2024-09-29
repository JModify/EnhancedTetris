package me.modify.tetris.game.state;

import java.awt.*;

public class Cell {

    public static final Color EMPTY_CELL_COLOR = Color.WHITE;

    public static final int EMPTY_CELL = 0;
    public static final int PLACEHOLDER = 80;
    public static final int FIXED_PLACEHOLDER = -80;

    private int data;

    public Cell(int data) {
        this.data = data;
    }

    /**
     * Retrieves the data stored in this cell.
     * @return the data stored
     */
    public int getData() {
        return data;
    }

    /**
     * Sets the data for this cell.
     * @param data data to set too.
     */
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
