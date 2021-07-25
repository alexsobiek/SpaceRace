package com.alexsobiek.spacerace.event.listener;

import com.alexsobiek.spacerace.SpaceRace;
import com.alexsobiek.spacerace.event.events.KeyInputEvent;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyInput {
    public KeyInput() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(event -> {
            switch (event.getID()) {
                case KeyEvent.KEY_PRESSED:
                    SpaceRace.eventBus.post(new KeyInputEvent.Pressed(event.getKeyChar(), event.getKeyCode()));
                    break;
                case KeyEvent.KEY_RELEASED:
                    SpaceRace.eventBus.post(new KeyInputEvent.Released(event.getKeyChar(), event.getKeyCode()));
                    break;
            }
            return true;
        });
    }
}
