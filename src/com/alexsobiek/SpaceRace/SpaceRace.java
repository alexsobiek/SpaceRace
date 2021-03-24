package com.alexsobiek.SpaceRace;

/*
Main entry point for SpaceRace
Written by Alexander Sobiek
for COMP 170 Loyola University Chicago
*/

import com.alexsobiek.SpaceRace.event.EventBus;
import com.alexsobiek.SpaceRace.event.events.GameTickEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SpaceRace {
    private static ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
    public static final int TPS = 200;
    public static EventBus EVENT_BUS;
    public static void main(String[] args) {
        System.out.println("Starting");
        EVENT_BUS = new EventBus();
        service.scheduleAtFixedRate(() -> EVENT_BUS.post(new GameTickEvent()), 0, (1000 / TPS), TimeUnit.MILLISECONDS);
        new Window();
    }
}
