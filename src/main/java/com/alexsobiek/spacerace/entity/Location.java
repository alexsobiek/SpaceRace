package com.alexsobiek.spacerace.entity;

public class Location {

    private final int xOrigin;
    private final int yOrigin;
    private int x;
    private int y;

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
     * Updates this location's X coordinate
     *
     * @param x X coordinate
     */
    public void setX(int x) {
        this.x = x;
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
     * Updates this location's Y coordinate
     *
     * @param y Y coordinate
     */
    public void setY(int y) {
        this.y = y;
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
