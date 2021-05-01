package com.alexsobiek.spacerace;

import com.alexsobiek.spacerace.event.EventBus;
import com.alexsobiek.spacerace.event.listener.KeyInput;
import com.alexsobiek.spacerace.event.listener.WindowResize;
import com.alexsobiek.spacerace.graphics.Window;

import java.util.logging.Logger;

/**
 * SpaceRace:
 * Written for COMP 170 Spring 2021 Loyola University Chicago
 *
 * @author Alexander Sobiek
 * @version 1.0
 */

public class SpaceRace {
    public static final EventBus EVENT_BUS = new EventBus();
    public static final Logger LOGGER = Logger.getLogger("SpaceRace");

    /**
     * Main entry point for Space Race
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        LOGGER.info("Starting Space Race");

        GameManager.start();
        new Window();
        new KeyInput();
        new WindowResize();
    }
}
