package com.alexsobiek.SpaceRace.event.listener;

import com.alexsobiek.SpaceRace.SpaceRace;
import com.alexsobiek.SpaceRace.event.events.KeyInputEvent;

import java.awt.*;

public class KeyInput {
    public KeyInput() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(key -> {
            SpaceRace.EVENT_BUS.post(new KeyInputEvent(key.getKeyChar(), key.getKeyCode()));
            return true;
        });
    }
}
