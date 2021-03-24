package com.alexsobiek.SpaceRace.listeners;

import com.alexsobiek.SpaceRace.GameManager;
import com.alexsobiek.SpaceRace.TickManager;
import com.alexsobiek.SpaceRace.Timer;
import com.alexsobiek.SpaceRace.Window;
import com.alexsobiek.SpaceRace.entity.IEntity;
import com.alexsobiek.SpaceRace.event.EventHandler;
import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.event.events.KeyInputEvent;

import java.awt.*;

public class KeyListener implements Listener {
    boolean paused = false;

    @EventHandler
    public void keyReleased(KeyInputEvent event) {
        int keyCode = event.getCode();
        System.out.println(keyCode);
        switch (keyCode) {
            case 82:
                GameManager.restart();
                Timer.start(15);
                break;
            case 80:
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
            case 87: // Player 1 Up
                Window.player1.move(IEntity.MoveDirection.UP);
                break;
            case 83: // Player 1 Down
                Window.player1.move(IEntity.MoveDirection.DOWN);
                break;
            case 38: // Player 2 Up
                Window.player2.move(IEntity.MoveDirection.UP);
                break;
            case 40: // Player 2 Down
                Window.player2.move(IEntity.MoveDirection.DOWN);
                break;
        }
    }
}
