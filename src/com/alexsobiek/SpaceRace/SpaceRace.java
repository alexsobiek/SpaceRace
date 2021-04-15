package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.event.EventBus;
import com.alexsobiek.SpaceRace.event.listener.KeyInput;

/**
 *
 *  SpaceRace:
 *  Written for COMP 170 Loyola University Chicago
 *
 *  @author  Alexander Sobiek
 *  @version 1.0
 *
 */

public class SpaceRace {
    public static EventBus EVENT_BUS;

    /**
     * Main entry point for Space Race
     * @param args Unused
     */
    public static void main(String[] args) {
        EVENT_BUS = new EventBus();
        new KeyInput();
        new Timer();
        System.out.println("Starting Space Race");
        GameManager.start();
        new Window();
    }
}
