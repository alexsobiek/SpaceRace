package com.alexsobiek.SpaceRace.graphics;

import com.alexsobiek.SpaceRace.GameManager;
import com.alexsobiek.SpaceRace.SpaceRace;
import com.alexsobiek.SpaceRace.TickManager;
import com.alexsobiek.SpaceRace.event.EventHandler;
import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.event.events.GameTickEvent;
import com.alexsobiek.SpaceRace.graphics.Window;

import java.awt.*;

public class Timer implements Listener {

    private static final int timerStroke = 10; // Pixels to stroke the timer line
    private static double timerHeight;
    private static int maxTimerHeight;

    private static double pixelsPerTick;

    /**
     * Constructor:
     * Subscribes the Timer to the EventBus so we can listen for tick events
     */
    public Timer() {
        SpaceRace.EVENT_BUS.subscribe(this);
    }

    /**
     * Starts the game timer
     * @param time Amount of time the game should have
     */
    public static void start(int time) {
        int totalTicks = time * TickManager.TPS;
        timerHeight = 0;
        maxTimerHeight = Window.winHeight - (timerStroke * 4);
        pixelsPerTick = (double) maxTimerHeight / totalTicks;
    }

    @EventHandler
    public void onTick(GameTickEvent event) {
        timerHeight += pixelsPerTick;
        if (maxTimerHeight > 0 && timerHeight >= maxTimerHeight) GameManager.end();
    }

    /**
     * Draws the timer line on the screen
     * @param g2d Graphics2D object
     */
    public static void drawTimer(Graphics2D g2d) {
        g2d.setColor(Window.foregroundColor);
        g2d.setStroke(new BasicStroke(timerStroke));
        g2d.drawLine(Window.halfX, 0, Window.halfX, Window.winHeight);

        if (GameManager.isPaused()) g2d.setColor(Window.pauseColor);
        else g2d.setColor(Window.backgroundColor);
        g2d.setStroke(new BasicStroke(timerStroke + 1));
        g2d.drawLine(Window.halfX, 0, Window.halfX, (int) timerHeight);
        g2d.setColor(Window.foregroundColor); // Reset Color
        g2d.setStroke(new BasicStroke(1)); // Reset Stroke
    }
}
