package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.event.EventHandler;
import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.event.events.GameTickEvent;

import java.awt.*;

public class Timer implements Listener {

    // Timer
    private static final int timerStroke = 10;
    private static double timerHeight;
    private static int maxTimerHeight;

    private static double pixelsPerTick;

    public Timer() {
        SpaceRace.EVENT_BUS.subscribe(this);
    }

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

    public static void drawTimer(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(timerStroke));
        g2d.drawLine(Window.halfX, 0, Window.halfX, Window.winHeight);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(timerStroke + 1));
        g2d.drawLine(Window.halfX, 0, Window.halfX, (int) timerHeight);
        g2d.setColor(Color.WHITE); // Reset Color
        g2d.setStroke(new BasicStroke(1)); // Reset Stroke
    }
}
