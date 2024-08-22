package me.modify.tetris.game;

import javax.swing.*;
import java.awt.*;

public class Cell {

    private int x;
    private int y;
    private int data;
    private JPanel panel;

    public Cell(int x, int y, int data, JPanel panel) {
        this.x = x;
        this.y = y;
        this.data = data;
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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public Color getColor() {
        return panel.getBackground();
    }

    public void setColor(Color color) {
        if (data != 80) {
            panel.setBackground(color);
        }
    }

    public boolean isFixed() {
        return data <= 0;
    }

    public void setFixed() {
        if (data > 0) {
            setData(data * -1);
        }
    }
}
