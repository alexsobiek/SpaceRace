package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.listeners.KeyListener;
import com.alexsobiek.SpaceRace.listeners.PlayerMove;

public class GameManager {

    private static boolean running = false;
    private static boolean paused = false;

    public static void start() {
        running = true;
        subscribe(new KeyListener());
        subscribe(new PlayerMove());
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
        Window.player1.reset();
        Window.player2.reset();
        if (!running) {
            TickManager.startTicking();
            running = true;
        }
    }

    public static boolean isRunning() {
        return running;
    }

    private static void subscribe(Listener listener) {
        SpaceRace.EVENT_BUS.subscribe(listener);
    }
}
