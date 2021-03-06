package com.alexsobiek.spacerace.event.listener;

import com.alexsobiek.spacerace.SpaceRace;
import com.alexsobiek.spacerace.event.events.WindowResizeEvent;
import com.alexsobiek.spacerace.graphics.Window;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class WindowResize {
    public WindowResize(Window window) {
        window.getFrame().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                SpaceRace.EVENT_BUS.post(new WindowResizeEvent(event));
            }
        });
    }
}
