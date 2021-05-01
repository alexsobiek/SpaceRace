package com.alexsobiek.spacerace.listeners;

import com.alexsobiek.spacerace.GameManager;
import com.alexsobiek.spacerace.graphics.Window;
import com.alexsobiek.spacerace.entity.IEntity;
import com.alexsobiek.spacerace.entity.Location;
import com.alexsobiek.spacerace.entity.entities.Player;
import com.alexsobiek.spacerace.entity.entities.Star;
import com.alexsobiek.spacerace.event.EventHandler;
import com.alexsobiek.spacerace.event.Listener;
import com.alexsobiek.spacerace.event.events.EntityMoveEvent;

public class EntityMove implements Listener {

    /**
     * Called when an EntityMoveEvent is invoked
     * @param event EntityMoveEvent
     */
    @EventHandler
    public void onEntityMove(EntityMoveEvent event) {
        if (GameManager.isRunning() || GameManager.isPaused()) {
            IEntity entity = event.getEntity();
            if (entity instanceof Star) {
                Star star = (Star) event.getEntity();
                Location loc = star.getLocation();

                // Check if star collides with a player
                if (Window.player1 != null && Window.player1.getModel().getPolygon().contains(loc.getX(), loc.getY())) resetEntity(Window.player1);
                else if (Window.player1 != null && Window.player2.getModel().getPolygon().contains(loc.getX(), loc.getY())) resetEntity(Window.player2);

            } else if (entity instanceof Player) {
                Player player = (Player) event.getEntity();
                Location loc = player.getLocation();
                if (player.isOutOfBounds()) {
                    if (loc.getY() <= 0) {
                        player.addScore();
                        System.out.println("Player with ID: " + player.getId() + " scored. They now have a score of: " + player.getScore());
                    }
                    resetEntity(player);
                }
            }
        } else {
            event.setCancelled(true);
        }
    }

    /**
     * Resets the entity's location
     * @param entity Entity to reset
     */
    private void resetEntity(IEntity entity) {
        entity.getLocation().reset();
        if (entity instanceof Player) ((Player) entity).getModel().reset();
    }
}
