package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.event.EventHandler;
import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.event.events.GameTickEvent;

import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class Timer implements Listener {

    public static int time;
    public static int tickTime;
    public static int timeLeft;

    private static int pixelUpdateTicks = -200;
    private static int timeUpdateTicks = 200;
    private static int timerHeight = Window.halfY;
    private static int ticksUntilPixelUpdate;

    public Timer() {
        SpaceRace.EVENT_BUS.subscribe(this);
    }

    public static void start(int time) {
        System.out.println("Starting timer with " + time + " seconds...");
        if ((time%2) == 0) time++; // Time must be odd for proper math to take place.
        tickTime = (time * TickManager.TPS); // 15 seconds in ticks
        timeLeft = time;
        ticksUntilPixelUpdate = TickManager.TPS / (Window.halfY / time);
    }

    public static int getTimeLeft() {
        return timeLeft;
    }

    @EventHandler
    public void onTick(GameTickEvent event) {
        if (pixelUpdateTicks == ticksUntilPixelUpdate) {
            timerHeight++;
            pixelUpdateTicks = 0;
        }
        if (timeUpdateTicks == TickManager.TPS) { // 1 second has passed
            timeLeft--;
            if (timeLeft == 0) GameManager.end();
            timeUpdateTicks = 0;
        }
        pixelUpdateTicks++;
        timeUpdateTicks++;
    }

    public static void drawTimer(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(Window.halfX, Window.winHeight, Window.halfX, timerHeight);
        g2d.drawString("Time Remaining: " + timeLeft, 10, Window.winHeight - 50);
    }
}
