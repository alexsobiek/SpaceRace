package com.alexsobiek.spacerace;

import com.alexsobiek.spacerace.event.events.GameTickEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TickManager {

    private final SpaceRace sp;
    private final int ticksPerSecond;
    private ScheduledExecutorService service = null;

    TickManager(SpaceRace sp) {
        this.sp = sp;
        ticksPerSecond = Config.getInt(Config.TPS);

    }

    public int getTicksPerSecond() {
        return ticksPerSecond;
    }

    /**
     * Creates a Scheduled Thread Pool and begins ticking the game
     */
    public void startTicking() {
        service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(() -> sp.getEventBus().post(new GameTickEvent()), 0, (1000 / ticksPerSecond), TimeUnit.MILLISECONDS);
    }

    /**
     * Stops ticking the game
     */
    public void stopTicking() {
        if (service != null) {
            service.shutdown();
            service = null;
        }
    }
}
