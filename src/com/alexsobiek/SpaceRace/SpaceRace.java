package com.alexsobiek.SpaceRace;

/*
Main entry point for SpaceRace
Written by Alexander Sobiek
for COMP 170 Loyola University Chicago
*/

import com.alexsobiek.SpaceRace.event.EventBus;

public class SpaceRace {
    public static EventBus EVENT_BUS;
    public static void main(String[] args) {
        EVENT_BUS = new EventBus();
        new Timer();
        System.out.println("Starting");
        GameManager.start();
        new Window();
    }
}
