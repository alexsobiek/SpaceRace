package com.alexsobiek.spacerace.entity;

public class Location {

    private int x;
    private int y;

    private final int xOrigin;
    private final int yOrigin;

    /**
     * Constructor:
     * Creates a new location
     *
     * @param x X coordinate for this location
     * @param y Y coordinate for this location
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        xOrigin = x;
        yOrigin = y;
    }

    /**
     * Updates this location's X coordinate
     *
     * @param x X coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Updates this location's Y coordinate
     *
     * @param y Y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Resets the location to its origin
     */
    public void reset() {
        x = xOrigin;
        y = yOrigin;
    }

    /**
     * Gets the X coordinate for this location
     *
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y coordinate for this location
     *
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the X origin coordinate for this location
     *
     * @return int
     */
    public int getXOrigin() {
        return xOrigin;
    }

    /**
     * Gets the Y origin coordinate for this location
     *
     * @return int
     */
    public int getYOrigin() {
        return yOrigin;
    }
}
