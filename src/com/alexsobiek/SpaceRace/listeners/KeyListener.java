package com.alexsobiek.SpaceRace.listeners;

import com.alexsobiek.SpaceRace.GameManager;
import com.alexsobiek.SpaceRace.TickManager;
import com.alexsobiek.SpaceRace.Timer;
import com.alexsobiek.SpaceRace.Window;
import com.alexsobiek.SpaceRace.event.EventHandler;
import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.event.events.KeyInputEvent;

import java.awt.*;

public class KeyListener implements Listener {
    boolean paused = false;

    @EventHandler
    public void keyReleased(KeyInputEvent event) {
        char key = Character.toLowerCase(event.getKey());
        switch (key) {
            case 'r':
                GameManager.restart();
                Timer.start(15);
                break;
            case 'p':
                if (paused) {
                    TickManager.startTicking();
                    Window.frame.setBackground(Color.BLACK);
                    Window.frame.repaint();
                    paused = false;
                } else {
                    TickManager.stopTicking();
                    Window.frame.setBackground(Color.RED);
                    Window.frame.repaint();
                    paused = true;
                }
                break;
        }
    }
}
