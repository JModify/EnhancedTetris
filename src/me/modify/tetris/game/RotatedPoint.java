package me.modify.tetris.game;

import java.awt.*;

/**
 * Represents a rotated point to be inserted into the grid.
 * Object's purpose is to preserve placeholder data.
 */
public class RotatedPoint {

    /** Whether this point holds a placeholder or not */
    private boolean placeholder;

    /** Point of the rotated point */
    private Point point;

    /**
     * Creates a new rotated point using the parsed values.
     * @param point point of this rotated point.
     * @param placeholder whether it is a placeholder.
     */
    public RotatedPoint(Point point, boolean placeholder) {
        this.placeholder = placeholder;
        this.point = point;
    }

    /**
     * Determines whether this point is a placeholder or not.
     * @return true if it is, else false.
     */
    public boolean isPlaceholder() {
        return placeholder;
    }

    /**
     * Retrieves the Point reference for this RotatedPoint.
     * @return point reference.
     */
    public Point getPoint() {
        return point;
    }
}
