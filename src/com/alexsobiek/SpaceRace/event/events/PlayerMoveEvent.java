package com.alexsobiek.SpaceRace.event.events;

import com.alexsobiek.SpaceRace.entity.entities.Player;
import com.alexsobiek.SpaceRace.event.Cancellable;
import com.alexsobiek.SpaceRace.event.Event;

public class PlayerMoveEvent extends Event implements Cancellable {
    private boolean cancelled;
    private final Player player;

    public PlayerMoveEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void setCcancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public boolean isCcancelled() {
        return cancelled;
    }
}
