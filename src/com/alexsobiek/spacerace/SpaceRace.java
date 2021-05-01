package com.alexsobiek.spacerace;

import com.alexsobiek.spacerace.event.EventBus;
import com.alexsobiek.spacerace.event.listener.KeyInput;
import com.alexsobiek.spacerace.event.listener.WindowResize;
import com.alexsobiek.spacerace.graphics.Window;

/**
 * SpaceRace:
 * Written for COMP 170 Spring 2021 Loyola University Chicago
 *
 * @author Alexander Sobiek
 * @version 1.0
 */

public class SpaceRace {
    public static EventBus EVENT_BUS;

    /**
     * Main entry point for Space Race
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        System.out.println("Starting Space Race");
        EVENT_BUS = new EventBus();

        GameManager.start();
        new Window();
        new KeyInput();
        new WindowResize();
    }
}
