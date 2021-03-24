package com.alexsobiek.SpaceRace;
import com.alexsobiek.SpaceRace.event.events.GameTickEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TickManager {

    public static final int TPS = 200;
    private static ScheduledExecutorService service = null;

    public static void startTicking() {
        service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(() -> SpaceRace.EVENT_BUS.post(new GameTickEvent()), 0, (1000 / TPS), TimeUnit.MILLISECONDS);
    }

    public static void stopTicking() {
        if (service != null) {
            service.shutdown();
            service = null;
        }
    }

}
