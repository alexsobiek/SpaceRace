package com.alexsobiek.spacerace;

import com.alexsobiek.spacerace.event.Listener;
import com.alexsobiek.spacerace.graphics.Window;
import com.alexsobiek.spacerace.listeners.EntityMove;
import com.alexsobiek.spacerace.listeners.KeyListener;

public class GameManager {

    private final SpaceRace sp;
    private final TickManager tm;
    private final Window window;
    private boolean running = false;
    private boolean paused = false;

    GameManager(SpaceRace sp) {
        this.sp = sp;
        this.tm = sp.getTickManager();
        this.window = sp.getWindow();
    }

    /**
     * Starts the game
     */
    public void start() {
        running = true;
        subscribe(new KeyListener(sp)); // Subscribes KeyListener to the EventBus
        subscribe(new EntityMove(sp)); // Subscribes PlayerMove to the EventBus
        sp.getTickManager().startTicking();
    }

    private void resetPlayers(boolean resetScore) {
        window.reset();
        window.reset();
    }

    /**
     * Ends the game
     */
    public void end() { // Game over
        resetPlayers(false);
        tm.stopTicking();
        running = false;
    }

    /**
     * Restarts the game
     */
    public void restart() {
        sp.getWindow().reset();
        resetPlayers(true);
        if (!running) {
            tm.startTicking();
            running = true;
        }
        sp.getLogger().info("Game Reset");
    }

    /**
     * Returns whether or not the game is active
     *
     * @return boolean
     */
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Returns whether or not the game is paused
     *
     * @return boolean
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Sets the game's pause state
     *
     * @param paused Pause or unpause the game
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * Utility method to subscribe listeners to the EventBus
     *
     * @param listener Class to subscribe
     */
    private void subscribe(Listener listener) {
        sp.getEventBus().subscribe(listener);
    }
}
