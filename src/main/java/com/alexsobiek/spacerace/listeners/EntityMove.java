package com.alexsobiek.spacerace.listeners;

import com.alexsobiek.spacerace.GameManager;
import com.alexsobiek.spacerace.SpaceRace;
import com.alexsobiek.spacerace.entity.IEntity;
import com.alexsobiek.spacerace.entity.Location;
import com.alexsobiek.spacerace.entity.entities.Player;
import com.alexsobiek.spacerace.entity.entities.Star;
import com.alexsobiek.spacerace.event.EventHandler;
import com.alexsobiek.spacerace.event.Listener;
import com.alexsobiek.spacerace.event.events.EntityMoveEvent;
import com.alexsobiek.spacerace.graphics.Window;

public class EntityMove implements Listener {

    private final SpaceRace sp;
    private final GameManager gm;
    private final Window window;

    public EntityMove(SpaceRace sp) {
        this.sp = sp;
        gm = sp.getGameManager();
        window = sp.getWindow();
    }

    /**
     * Called when an EntityMoveEvent is invoked
     *
     * @param event EntityMoveEvent
     */
    @EventHandler
    public void onEntityMove(EntityMoveEvent event) {
        if (gm.isRunning() || gm.isPaused()) {
            IEntity entity = event.getEntity();
            if (entity instanceof Star) {
                Star star = (Star) event.getEntity();
                Location loc = star.getLocation();

                // Check if star collides with a player
                if (window.player1 != null && window.player1.getModel().getPolygon().contains(loc.getX(), loc.getY()))
                    resetEntity(window.player1);
                else if (window.player1 != null && window.player2.getModel().getPolygon().contains(loc.getX(), loc.getY()))
                    resetEntity(window.player2);

            } else if (entity instanceof Player) {
                Player player = (Player) event.getEntity();
                Location loc = player.getLocation();
                if (player.isOutOfBounds()) {
                    if (loc.getY() <= 0) {
                        player.addScore();
                        sp.getLogger().info("Player with ID: " + player.getId() + " scored. They now have a score of: " + player.getScore());
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
     *
     * @param entity Entity to reset
     */
    private void resetEntity(IEntity entity) {
        entity.getLocation().reset();
        if (entity instanceof Player) ((Player) entity).getModel().reset();
    }
}
