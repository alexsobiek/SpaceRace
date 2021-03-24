package com.alexsobiek.SpaceRace.event.events;

import com.alexsobiek.SpaceRace.event.Event;

public class KeyInputEvent extends Event {
    private final char key;

    public KeyInputEvent(char key) {
        this.key = key;
    }

    public char getKey() {
        return key;
    }
}
