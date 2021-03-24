package com.alexsobiek.SpaceRace;

import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class Timer {
    public static int time = 10; // 10 seconds
    public static int tickTime = (time * SpaceRace.TPS); // 10 seconds in ticks
    private static int timerHeight = Window.halfY;

    public static void handleTick() {
        tickTime--;
        timerHeight -= Window.halfX / tickTime;
        //System.out.println(timerHeight);
    }

    public static void drawTimer(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(Window.halfX, Window.winHeight, Window.halfX, timerHeight);
    }
}
