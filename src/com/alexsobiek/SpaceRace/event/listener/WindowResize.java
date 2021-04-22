package com.alexsobiek.SpaceRace.event.listener;

import com.alexsobiek.SpaceRace.SpaceRace;
import com.alexsobiek.SpaceRace.event.events.WindowResizeEvent;
import com.alexsobiek.SpaceRace.graphics.Window;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class WindowResize {
    public WindowResize() {
        Window.frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent event) {
                SpaceRace.EVENT_BUS.post(new WindowResizeEvent(event));
            }
        });
    }
}
