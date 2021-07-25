package com.alexsobiek.spacerace.graphics;

import com.alexsobiek.spacerace.GameManager;
import com.alexsobiek.spacerace.SpaceRace;
import com.alexsobiek.spacerace.TickManager;
import com.alexsobiek.spacerace.event.EventHandler;
import com.alexsobiek.spacerace.event.Listener;
import com.alexsobiek.spacerace.event.events.GameTickEvent;

import java.awt.*;

public class Timer implements Listener {

    private final SpaceRace sp;
    private final Window window;

    private final int timerStroke = 10; // Pixels to stroke the timer line
    public long remainingTicks;
    public long totalTicks;
    private double timerHeight;
    private int maxTimerHeight;
    private double pixelsPerTick;

    /**
     * Constructor:
     * Creates a new timer instance
     *
     * @param seconds Amount of time in seconds this timer should have
     */
    public Timer(SpaceRace sp, Window window,  int seconds) {
        this.sp = sp;
        this.window = window;
        sp.getEventBus().subscribe(this);
        start(seconds);
    }

    /**
     * Constructor:
     * Creates a new timer instance
     *
     * @param ticks Amount of time in ticks this timer should have
     */
    public Timer(SpaceRace sp, Window window, long ticks) {
        this.sp = sp;
        this.window = window;
        sp.getEventBus().subscribe(this);
        start(ticks);
    }

    /**
     * Starts the game timer
     *
     * @param seconds Amount of time in seconds the game should have
     */
    private void start(int seconds) {
        long totalTicks = (long) seconds * sp.getTickManager().getTicksPerSecond();
        start(totalTicks);
    }

    /**
     * Starts the game timer
     *
     * @param ticks Amount of time in ticks the game should have
     */
    private void start(long ticks) {
        timerHeight = 0;
        this.totalTicks = ticks;
        remainingTicks = ticks;
        maxTimerHeight = window.getWinHeight() - (timerStroke * 4);
        pixelsPerTick = (double) maxTimerHeight / ticks;
    }

    public long getRemainingTicks() {
        return remainingTicks;
    }

    @EventHandler
    public void onTick(GameTickEvent event) {
        remainingTicks--;
        timerHeight += pixelsPerTick;
        if (maxTimerHeight > 0 && timerHeight >= maxTimerHeight) sp.getGameManager().end();
    }

    /**
     * Draws the timer line on the screen
     *
     * @param g2d Graphics2D object
     */
    public void drawTimer(Graphics2D g2d) {
        g2d.setColor(window.getBackground());
        g2d.setStroke(new BasicStroke(timerStroke));
        g2d.drawLine(window.getHalfX(), 0, window.getHalfX(), window.getWinHeight());

        if (sp.getGameManager().isPaused()) g2d.setColor(window.getPauseColor());
        else g2d.setColor(window.getForeground());
        g2d.setStroke(new BasicStroke(timerStroke + 1));
        g2d.drawLine(window.getHalfX(), 0, window.getHalfX(), (int) timerHeight);
        g2d.setColor(window.getBackground()); // Reset Color
        g2d.setStroke(new BasicStroke(1)); // Reset Stroke
    }
}
