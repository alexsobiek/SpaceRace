package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.event.events.GameTickEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TickManager {

    public static final int TPS = 200; // Define how many ticks per second the game should have
    private static ScheduledExecutorService service = null;


    /**
     * Creates a Scheduled Thread Pool and begins ticking the game
     */
    public static void startTicking() {
        service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(() -> SpaceRace.EVENT_BUS.post(new GameTickEvent()), 0, (1000 / TPS), TimeUnit.MILLISECONDS);
    }

    /**
     * Stops ticking the game
     */
    public static void stopTicking() {
        if (service != null) {
            service.shutdown();
            service = null;
        }
    }

}
