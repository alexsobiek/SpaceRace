package com.alexsobiek.SpaceRace.graphics;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class PlayerModel {

    Polygon model;

    int[] xCoords = new int[22];
    int[] yCoords = new int[22];


    public PlayerModel(int xTop, int yTop) {

        int baseHeight = 2;
        int baseWidth = 4;

        int firstOffsetWidth = 10;
        int firstOffsetHeight = 8;

        int secondOffsetWidth = 10;
        int secondOffsetHeight = 12;

        int neckHeight = 10;

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


        int thrusterOffset = xTop - xCoords[7] + 3; // Calculate distance between thrusters
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

        System.out.println("X: " + Arrays.toString(xCoords));
        System.out.println("Y:" + Arrays.toString(yCoords));

        model = new Polygon(xCoords, yCoords, xCoords.length);

    }

    /**
     * Creates a thruster at the specified position
     * @param pos Starting position for thruster
     */
    private void makeThruster(int pos) {
        int thrusterHeight = 6;
        int thrusterWidth = 4;

        xCoords[pos+1] = xCoords[pos]; // First thruster
        yCoords[pos+1] = yCoords[pos] + thrusterHeight; // down

        xCoords[pos+2] = xCoords[pos+1] + thrusterWidth; // right
        yCoords[pos+2] = yCoords[pos+1];

        xCoords[pos+3] = xCoords[pos+2];
        yCoords[pos+3] = yCoords[pos+2] - thrusterHeight; // up
    }

    /*
    public PlayerModel(int xCenter, int yCenter, int speed) {

        // xCenter = 500
        // yCenter = 700

        this.speed = speed;

        int thrusterHeight = 10;
        int thrusterWidth = 5;

        // Draw half the player model

        // Left thruster
        xCoords[0] = xCenter;
        yCoords[0] = yCenter + 5;

        xCoords[1] = xCenter - 10;
        yCoords[1] = yCoords[0];

        xCoords[2] = xCoords[1];
        yCoords[2] = yCoords[1] + 10;

        xCoords[3] = xCoords[2] - 5;
        yCoords[3] = yCoords[2];

        xCoords[4] = xCoords[3];
        yCoords[4] = yCoords[3] - 10;

        xCoords[5] = xCoords[4] - 2;
        yCoords[5] = yCoords[4];

        xCoords[6] = xCenter - 10;
        yCoords[6] = yCoords[5] - 10;

        xCoords[7] = xCoords[6];
        yCoords[7] = yCoords[6] - 8;

        xCoords[8] = xCoords[7] - 5;
        yCoords[8] = yCoords[7];

        xCoords[9] = xCenter;
        yCoords[9] = yCoords[8] - 8;

        xCoords[10] = xCenter + 10;
        yCoords[10] = yCoords[8];

        xCoords[11] = xCoords[10] - 5;
        yCoords[11] = yCoords[10];

        xCoords[12] = xCoords[11];
        yCoords[12] = yCoords[11] + 8;

        model = new Polygon(xCoords, yCoords, xCoords.length);
    }

     */

    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolygon(model);
        g2d.setStroke(new BasicStroke(1));
    }

    public Polygon getPolygon() {
        return model;
    }

}
