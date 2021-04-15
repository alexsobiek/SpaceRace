package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.listeners.KeyListener;
import com.alexsobiek.SpaceRace.listeners.PlayerMove;

public class GameManager {

    private static boolean running = false;
    private static boolean paused = false;

    /**
     * Starts the game
     */
    public static void start() {
        running = true;
        subscribe(new KeyListener());
        subscribe(new PlayerMove());
        TickManager.startTicking();
    }

    /**
     * Ends the game
     */
    public static void end() { // Game over
        Window.player1.resetPos();
        Window.player2.resetPos();
        TickManager.stopTicking();
        running = false;
    }

    /**
     * Toggles pause/unpause
     */
    public static void togglePause() {
        if (!paused) {
            TickManager.stopTicking();
            paused = true;
        } else TickManager.startTicking();
    }

    /**
     * Restarts the game
     */
    public static void restart() {
        Window.reset();
        Window.player1.reset();
        Window.player2.reset();
        if (!running) {
            TickManager.startTicking();
            running = true;
        }
        System.out.println("Game Reset");
    }

    /**
     * Checks whether or not the game is active
     * @return boolean
     */
    public static boolean isRunning() {
        return running;
    }

    /**
     * Utility method to subscribe listeners to the EventBus
     * @param listener Class to subscribe
     */
    private static void subscribe(Listener listener) {
        SpaceRace.EVENT_BUS.subscribe(listener);
    }
}
