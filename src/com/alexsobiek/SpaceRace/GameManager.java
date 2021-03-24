package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.listeners.KeyListener;

public class GameManager {

    private static boolean running = false;
    private static boolean paused = false;

    public static void start() {
        running = true;
        subscribe(new KeyListener());
        TickManager.startTicking();
    }

    public static void end() { // Game over
        TickManager.stopTicking();
        running = false;
    }

    public static void togglePause() {
        if (!paused) {
            TickManager.stopTicking();
            paused = true;
        } else TickManager.startTicking();
    }

    public static void restart() {
        Window.reset();
        if (!running) {
            TickManager.startTicking();
            running = true;
        }
    }

    public static boolean isRunning() {
        return running;
    }
    private static void subscribe(com.alexsobiek.SpaceRace.event.Listener listener) {
        SpaceRace.EVENT_BUS.subscribe(listener);
    }
}
