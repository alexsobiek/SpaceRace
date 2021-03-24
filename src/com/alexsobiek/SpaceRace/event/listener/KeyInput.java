package com.alexsobiek.SpaceRace.event.listener;

import com.alexsobiek.SpaceRace.SpaceRace;
import com.alexsobiek.SpaceRace.event.events.KeyInputEvent;

import java.awt.*;

public class KeyInput {
    public KeyInput() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(ke -> {
            SpaceRace.EVENT_BUS.post(new KeyInputEvent(ke.getKeyChar()));
            return true;
        });
    }
}
