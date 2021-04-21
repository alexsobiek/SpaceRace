package com.alexsobiek.SpaceRace.event.events;

import com.alexsobiek.SpaceRace.entity.IEntity;
import com.alexsobiek.SpaceRace.entity.Location;
import com.alexsobiek.SpaceRace.event.Cancellable;
import com.alexsobiek.SpaceRace.event.Event;

public class EntityMoveEvent extends Event implements Cancellable {
    private final IEntity entity;
    private boolean cancelled = false;
    public EntityMoveEvent(IEntity entity) {
        this.entity = entity;
    }

    public IEntity getEntity() {
        return entity;
    }
    public Location getLocation() {
        return entity.getLocation();
    }
    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }
}
