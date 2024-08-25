package me.modify.tetris.game;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a single cell on the game board.
 */
public class Cell {

    /** Colour of the empty cell */
    public static final Color EMPTY_CELL = Color.WHITE;

    /** Row of the cell */
    private int x;

    /** Column of the cell */
    private int y;

    /** Data the cell holds */
    private int data;

    /** JPanel reference for the cell */
    private JPanel panel;

    /**
     * Creates a new cell and initializes related variables.
     * @param row row of cell
     * @param column column of cell
     * @param data data of cell
     * @param panel panel for cell
     */
    public Cell(int row, int column, int data, JPanel panel) {
        this.x = row;
        this.y = column;
        this.data = data;
        this.panel = panel;
    }

    /**
     * Retrieves the row for this cell.
     * @return row of cell
     */
    public int getRow() {
        return x;
    }

    /**
     * Sets the row of the cell
     * @param x new row to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retrieves the column for this cell.
     * @return column of cell
     */
    public int getColumn() {
        return y;
    }

    /**
     * Sets the column for this cell.
     * @param y new column to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retrieves the JPanel reference for this cell.
     * @return jpanel for this cell
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Retrieves the data this cell holds.
     * @return data of cell
     */
    public int getData() {
        return data;
    }

    /**
     * Sets the data this cell holds.
     * @param data data value to set too
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * Retrieves the colour of this cell.
     * @return
     */
    public Color getColor() {
        return panel.getBackground();
    }

    /**
     * Sets the colour for this cell.
     * @param color new colour of cell.
     */
    public void setColor(Color color) {
        if (data != 80) {
            panel.setBackground(color);
        }
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
