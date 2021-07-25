package com.alexsobiek.spacerace;

import com.alexsobiek.spacerace.event.EventBus;
import com.alexsobiek.spacerace.event.listener.KeyInput;
import com.alexsobiek.spacerace.event.listener.WindowResize;
import com.alexsobiek.spacerace.graphics.Window;
import com.alexsobiek.spacerace.util.Logger;


/**
 * SpaceRace:
 * Written for COMP 170 Spring 2021 Loyola University Chicago
 *
 * @author Alexander Sobiek
 * @version 1.0
 */

public class SpaceRace {
    public static final EventBus eventBus = new EventBus(); // TODO: Move away from static
    public static final Logger Logger = new Logger("SpaceRace"); // TODO: Move away from static

    /**
     * Main entry point for Space Race
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        new SpaceRace().run();
    }

    /**
     * Returns the Logger
     * @return Logger
     */
    public Logger getLogger() {
        return Logger;
    }

    /**
     * Returns the Event Bus
     * @return EventBus
     */
    public EventBus getEventBus() {
        return eventBus;
    }

    /**
     * Main method to run Space Race
     */
    public void run() {
        Logger.info("Starting Space Race");

        GameManager.start();
        new Window(this);
        new KeyInput();
        new WindowResize();
    }

}
