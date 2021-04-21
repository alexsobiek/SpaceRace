package com.alexsobiek.SpaceRace.listeners;

import com.alexsobiek.SpaceRace.GameManager;
import com.alexsobiek.SpaceRace.entity.Location;
import com.alexsobiek.SpaceRace.entity.entities.Player;
import com.alexsobiek.SpaceRace.event.EventHandler;
import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.event.events.EntityMoveEvent;

public class PlayerMove implements Listener {

    /**
     * Called when a PlayerMoveEvent is invoked
     *
     * @param event PlayerMoveEvent
     */
    @EventHandler
    public void onPlayerMove(EntityMoveEvent event) {
        if (event.getEntity() instanceof Player) {
            if (GameManager.isRunning()) {
                Player player = (Player) event.getEntity();
                Location loc = event.getLocation();
                if (player.isOutOfBounds()) {
                    if (loc.getY() <= 0) {
                        player.addScore();
                        System.out.println("Player with ID: " + player.getId() + " scored. They now have a score of: " + player.getScore());
                    }
                    loc.reset();
                }
            } else {
                event.setCancelled(true);
            }
        }
    }
}
