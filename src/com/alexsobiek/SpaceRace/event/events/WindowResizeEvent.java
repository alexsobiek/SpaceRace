package com.alexsobiek.SpaceRace.event.events;

import com.alexsobiek.SpaceRace.event.Event;

import java.awt.event.ComponentEvent;

public class WindowResizeEvent extends Event {
    private final ComponentEvent event;
    public WindowResizeEvent(ComponentEvent event) {
        this.event = event;
    }
    public ComponentEvent getComponentEvent() {
        return event;
    }
}
