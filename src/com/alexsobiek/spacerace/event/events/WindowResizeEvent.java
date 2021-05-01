package com.alexsobiek.spacerace.event.events;

import com.alexsobiek.spacerace.event.Event;

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
