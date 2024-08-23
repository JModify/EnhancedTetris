package me.modify.tetris.game;

import java.awt.*;

public class RotatedPoint {

    private boolean placeholder;
    private Point point;

    public RotatedPoint(Point point, boolean placeholder) {
        this.placeholder = placeholder;
        this.point = point;
    }

    public boolean isPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(boolean placeholder) {
        this.placeholder = placeholder;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
