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
    private int row;

    /** Column of the cell */
    private int column;

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
        this.row = row;
        this.column = column;
        this.data = data;
        this.panel = panel;
    }

    /**
     * Retrieves the row for this cell.
     * @return row of cell
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the row of the cell
     * @param row new row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Retrieves the column for this cell.
     * @return column of cell
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets the column for this cell.
     * @param column new column to set
     */
    public void setColumn(int column) {
        this.column = column;
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

    public void swap(Cell otherCell) {
        int rowCopy = row;
        int columnCopy = column;
        int dataCopy = data;
        Color colourCopy = panel.getBackground();

        setRow(otherCell.getRow());
        setColumn(otherCell.getColumn());
        setData(otherCell.getData());
        setColor(otherCell.getColor());

        otherCell.setRow(rowCopy);
        otherCell.setColumn(columnCopy);
        otherCell.setData(dataCopy);
        otherCell.setColor(colourCopy);
    }
}
