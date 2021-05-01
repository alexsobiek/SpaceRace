package com.alexsobiek.spacerace.graphics;

import com.alexsobiek.spacerace.GameManager;
import com.alexsobiek.spacerace.SpaceRace;
import com.alexsobiek.spacerace.TickManager;
import com.alexsobiek.spacerace.event.EventHandler;
import com.alexsobiek.spacerace.event.Listener;
import com.alexsobiek.spacerace.event.events.GameTickEvent;

import java.awt.*;

public class Timer implements Listener {

    private final int timerStroke = 10; // Pixels to stroke the timer line
    private double timerHeight;
    private int maxTimerHeight;

    private double pixelsPerTick;

    public long remainingTicks;
    public long totalTicks;

    /**
     * Constructor:
     * Creates a new timer instance
     * @param seconds Amount of time in seconds this timer should have
     */
    public Timer(int seconds) {
        SpaceRace.EVENT_BUS.subscribe(this);
        start(seconds);
    }

    /**
     * Constructor:
     * Creates a new timer instance
     * @param ticks Amount of time in ticks this timer should have
     */
    public Timer(long ticks) {
        SpaceRace.EVENT_BUS.subscribe(this);
        start(ticks);
    }

    /**
     * Starts the game timer
     * @param seconds Amount of time in seconds the game should have
     */
    private void start(int seconds) {
        long totalTicks = (long) seconds * TickManager.TPS;
        start(totalTicks);
    }

    /**
     * Starts the game timer
     * @param ticks Amount of time in ticks the game should have
     */
    private void start(long ticks) {
        timerHeight = 0;
        this.totalTicks = ticks;
        remainingTicks = ticks;
        maxTimerHeight = Window.winHeight - (timerStroke * 4);
        pixelsPerTick = (double) maxTimerHeight / ticks;
    }

    public long getRemainingTicks() {
        return remainingTicks;
    }

    @EventHandler
    public void onTick(GameTickEvent event) {
        remainingTicks--;
        timerHeight += pixelsPerTick;
        if (maxTimerHeight > 0 && timerHeight >= maxTimerHeight) GameManager.end();
    }

    /**
     * Draws the timer line on the screen
     * @param g2d Graphics2D object
     */
    public void drawTimer(Graphics2D g2d) {
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
