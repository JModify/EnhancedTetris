package me.modify.tetris.game;

import javax.swing.*;

public class Cell {

    private int x;
    private int y;
    private JPanel panel;

    public Cell(int x, int y, JPanel panel) {
        this.x = x;
        this.y = y;
        this.panel = panel;
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

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
