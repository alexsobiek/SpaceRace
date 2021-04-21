package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.graphics.Window;
import com.alexsobiek.SpaceRace.listeners.KeyListener;
import com.alexsobiek.SpaceRace.listeners.EntityMove;

public class GameManager {

    private static boolean running = false;
    private static boolean paused = false;

    public static void setRunning(boolean running) {
        GameManager.running = running;
    }

    /**
     * Starts the game
     */
    public static void start() {
        running = true;
        subscribe(new KeyListener()); // Subscribes KeyListener to the EventBus
        subscribe(new EntityMove()); // Subscribes PlayerMove to the EventBus
        TickManager.startTicking();
    }

    private static void resetPlayers(boolean resetScore) {
        Window.player1.reset(resetScore);
        Window.player2.reset(resetScore);
    }

    /**
     * Ends the game
     */
    public static void end() { // Game over
        resetPlayers(false);
        TickManager.stopTicking();
        running = false;
    }

    /**
     * Restarts the game
     */
    public static void restart() {
        Window.reset();
        resetPlayers(true);
        if (!running) {
            TickManager.startTicking();
            running = true;
        }
        System.out.println("Game Reset");
    }

    /**
     * Returns whether or not the game is active
     * @return boolean
     */
    public static boolean isRunning() {
        return running;
    }

    /**
     * Returns whether or not the game is paused
     * @return boolean
     */
    public static boolean isPaused() { return paused; }

    /**
     * Sets the game's pause state
     * @param paused Pause or unpause the game
     */
    public static void setPaused(boolean paused) {
        GameManager.paused = paused;
    }

    /**
     * Utility method to subscribe listeners to the EventBus
     * @param listener Class to subscribe
     */
    private static void subscribe(Listener listener) {
        SpaceRace.EVENT_BUS.subscribe(listener);
    }
}
