package com.alexsobiek.spacerace.graphics;

import com.alexsobiek.spacerace.GameManager;
import com.alexsobiek.spacerace.entity.Location;

import java.awt.*;

public class PlayerModel {
    Polygon model;

    int[] xCoords = new int[22];
    int[] yCoords = new int[22];

    int xTop;
    int yTop;

    /**
     * Constructor:
     * Creates a new player model at the starting location
     *
     * @param location location of player model
     */
    public PlayerModel(Location location) {
        xTop = location.getX();
        yTop = location.getY();
        calculatePos();
    }

    /**
     * Calculates the models polygon
     */
    private void calculatePos() {
        var baseHeight = 2;
        var baseWidth = 4;

        var firstOffsetWidth = 10;
        var firstOffsetHeight = 10;

        var secondOffsetWidth = 10;
        var secondOffsetHeight = 12;

        var neckHeight = 12;

        xCoords[0] = xTop;
        yCoords[0] = yTop;

        xCoords[1] = xCoords[0] - firstOffsetWidth;
        yCoords[1] = yCoords[0] + firstOffsetHeight;

        xCoords[2] = xCoords[1];
        yCoords[2] = yCoords[1] + baseHeight;

        xCoords[3] = xCoords[2] + baseWidth;
        yCoords[3] = yCoords[2];

        xCoords[4] = xCoords[3];
        yCoords[4] = yCoords[3] + neckHeight;

        xCoords[5] = xCoords[4] - secondOffsetWidth;
        yCoords[5] = yCoords[4] + secondOffsetHeight;

        xCoords[6] = xCoords[5];
        yCoords[6] = yCoords[5] + baseHeight;

        xCoords[7] = xCoords[6] + baseWidth;
        yCoords[7] = yCoords[6];

        makeThruster(7); // pos 8 - 10

        int thrusterOffset = xTop - xCoords[7]; // Calculate distance between thrusters
        xCoords[11] = xCoords[10] + thrusterOffset;
        yCoords[11] = yCoords[10];

        makeThruster(11); // pos 12 - 14

        xCoords[15] = xCoords[14] + baseWidth;
        yCoords[15] = yCoords[14];

        xCoords[16] = xCoords[15];
        yCoords[16] = yCoords[15] - baseHeight;

        xCoords[17] = xCoords[16] - secondOffsetWidth;
        yCoords[17] = yCoords[16] - secondOffsetHeight;

        xCoords[18] = xCoords[17];
        yCoords[18] = yCoords[17] - neckHeight;

        xCoords[19] = xCoords[18] + baseWidth;
        yCoords[19] = yCoords[18];

        xCoords[20] = xCoords[19];
        yCoords[20] = yCoords[19] - baseHeight;

        xCoords[21] = xTop;
        yCoords[21] = yTop;

        model = new Polygon(xCoords, yCoords, xCoords.length);
    }


    /**
     * Creates a thruster at the specified position
     *
     * @param pos Starting position for thruster
     */
    private void makeThruster(int pos) {
        var thrusterHeight = 8;
        var thrusterWidth = 6;

        xCoords[pos + 1] = xCoords[pos]; // First thruster
        yCoords[pos + 1] = yCoords[pos] + thrusterHeight; // down

        xCoords[pos + 2] = xCoords[pos + 1] + thrusterWidth; // right
        yCoords[pos + 2] = yCoords[pos + 1];

        xCoords[pos + 3] = xCoords[pos + 2];
        yCoords[pos + 3] = yCoords[pos + 2] - thrusterHeight; // up
    }

    /**
     * Draws this model using Graphics2D
     *
     * @param g2d Graphics2D object
     */
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(3));
        if (GameManager.isPaused()) g2d.setColor(Window.pauseColor);
        else g2d.setColor(Window.backgroundColor);

        g2d.fillPolygon(model);
        g2d.setColor(Window.foregroundColor);
        g2d.drawPolygon(model);
        g2d.setStroke(new BasicStroke(1));
    }

    /**
     * Returns this model's polygon
     *
     * @return Polygon
     */
    public Polygon getPolygon() {
        return model;
    }

    /**
     * Sets this models spawn point and recalculates its position
     *
     * @param location location to set spawn at
     */
    public void setSpawn(Location location) {
        xTop = location.getX();
        yTop = location.getY();
        calculatePos();
    }

    /**
     * Resets this models position
     */
    public void reset() {
        model = new Polygon(xCoords, yCoords, xCoords.length);
    }

}
