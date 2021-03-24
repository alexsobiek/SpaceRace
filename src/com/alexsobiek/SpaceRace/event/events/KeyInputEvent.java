package com.alexsobiek.SpaceRace.event.events;

import com.alexsobiek.SpaceRace.event.Event;

public class KeyInputEvent extends Event {
    private final char key;
    private final int code;

    public KeyInputEvent(char key, int code) {
        this.key = key;
        this.code = code;
    }

    public char getKey() {
        return key;
    }

    public int getCode() {
        return code;
    }
}
