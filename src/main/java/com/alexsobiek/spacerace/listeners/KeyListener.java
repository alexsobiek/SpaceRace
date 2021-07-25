package com.alexsobiek.spacerace.listeners;

import com.alexsobiek.spacerace.GameManager;
import com.alexsobiek.spacerace.SpaceRace;
import com.alexsobiek.spacerace.TickManager;
import com.alexsobiek.spacerace.entity.IEntity.MoveDirection;
import com.alexsobiek.spacerace.entity.entities.Player;
import com.alexsobiek.spacerace.event.EventHandler;
import com.alexsobiek.spacerace.event.Listener;
import com.alexsobiek.spacerace.event.events.KeyInputEvent;
import com.alexsobiek.spacerace.graphics.Window;

public class KeyListener implements Listener {

    private final SpaceRace sp;
    private final GameManager gm;
    private final Window window;
    private final TickManager tm;

    public KeyListener(SpaceRace sp) {
        this.sp = sp;
        gm = sp.getGameManager();
        window = sp.getWindow();
        tm = sp.getTickManager();
    }

    /**
     * Called when a key press event is invoked
     *
     * @param event KeyInputEvent.Pressed event
     */
    @EventHandler
    public void keyReleased(KeyInputEvent.Pressed event) {
        int keyCode = event.getCode();
        switch (keyCode) {
            case 82: // Reset
                gm.restart();
                window.startTimer();
                break;
            case 80: // Pause
                if (gm.isRunning()) {
                    if (gm.isPaused()) {
                        tm.startTicking();
                        window.frame.setBackground(window.backgroundColor);
                        window.frame.repaint();
                        gm.setPaused(false);
                        SpaceRace.Logger.info("Game Unpaused");
                    } else {
                        tm.stopTicking();
                        window.frame.setBackground(window.pauseColor);
                        window.frame.repaint();
                        gm.setPaused(true);
                        SpaceRace.Logger.info("Game Paused");
                    }
                }
                break;
            case 87: // Player 1 Up
                movePlayer(window.player1, MoveDirection.UP);
                break;
            case 83: // Player 1 Down
                movePlayer(window.player1, MoveDirection.DOWN);
                break;
            case 38: // Player 2 Up
                movePlayer(window.player2, MoveDirection.UP);
                break;
            case 40: // Player 2 Down
                movePlayer(window.player2, MoveDirection.DOWN);
                break;
        }
    }

    private void movePlayer(Player player, MoveDirection direction) {
        if (gm.isRunning() && !gm.isPaused()) player.move(direction);
    }

}
