package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.listeners.KeyListener;
import com.alexsobiek.SpaceRace.listeners.EntityMove;

public class GameManager {

    private static boolean running = false;
    private static boolean paused = false;

    /**
     * Starts the game
     */
    public static void start() {
        running = true;
        subscribe(new KeyListener()); // Subscribes KeyListener to the EventBus
        subscribe(new EntityMove()); // Subscribes PlayerMove to the EventBus
        TickManager.startTicking();
    }

    private static void resetPlayers() {
        Window.player1.getLocation().reset();
        Window.player2.getLocation().reset();
        Window.player1.getModel().reset();
        Window.player2.getModel().reset();
    }

    /**
     * Ends the game
     */
    public static void end() { // Game over
        resetPlayers();
        TickManager.stopTicking();
        running = false;
    }

    /**
     * Restarts the game
     */
    public static void restart() {
        Window.reset();
        resetPlayers();
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

    public static boolean isPaused() { return paused; }

    /**
     * Utility method to subscribe listeners to the EventBus
     * @param listener Class to subscribe
     */
    private static void subscribe(Listener listener) {
        SpaceRace.EVENT_BUS.subscribe(listener);
    }
}
