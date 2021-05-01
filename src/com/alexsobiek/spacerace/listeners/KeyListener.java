package com.alexsobiek.spacerace.listeners;

import com.alexsobiek.spacerace.GameManager;
import com.alexsobiek.spacerace.TickManager;
import com.alexsobiek.spacerace.entity.IEntity.MoveDirection;
import com.alexsobiek.spacerace.entity.entities.Player;
import com.alexsobiek.spacerace.event.EventHandler;
import com.alexsobiek.spacerace.event.Listener;
import com.alexsobiek.spacerace.event.events.KeyInputEvent;
import com.alexsobiek.spacerace.graphics.Window;

public class KeyListener implements Listener {
    boolean paused = false;

    /**
     * Called when a key press event is invoked
     * @param event KeyInputEvent.Pressed event
     */
    @EventHandler
    public void keyReleased(KeyInputEvent.Pressed event) {
        int keyCode = event.getCode();
        switch (keyCode) {
            case 82: // Reset
                GameManager.restart();
                Window.startTimer();
                break;
            case 80: // Pause
                if (GameManager.isRunning()) {
                    if (GameManager.isPaused()) {
                        TickManager.startTicking();
                        Window.frame.setBackground(Window.backgroundColor);
                        Window.frame.repaint();
                        GameManager.setPaused(false);
                        System.out.println("Game Unpaused");
                    } else {
                        TickManager.stopTicking();
                        Window.frame.setBackground(Window.pauseColor);
                        Window.frame.repaint();
                        GameManager.setPaused(true);
                        System.out.println("Game Paused");
                    }
                }
                break;
            case 87: // Player 1 Up
                movePlayer(Window.player1, MoveDirection.UP);
                break;
            case 83: // Player 1 Down
                movePlayer(Window.player1, MoveDirection.DOWN);
                break;
            case 38: // Player 2 Up
                movePlayer(Window.player2, MoveDirection.UP);
                break;
            case 40: // Player 2 Down
                movePlayer(Window.player2, MoveDirection.DOWN);
                break;
        }
    }

    private void movePlayer(Player player, MoveDirection direction) {
        if (GameManager.isRunning() && !GameManager.isPaused()) player.move(direction);
    }

}
