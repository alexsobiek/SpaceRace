package com.alexsobiek.spacerace.event.events;

import com.alexsobiek.spacerace.entity.IEntity;
import com.alexsobiek.spacerace.event.Cancellable;
import com.alexsobiek.spacerace.event.Event;

public class EntityMoveEvent extends Event implements Cancellable {
    private final IEntity entity;
    private boolean cancelled = false;

    public EntityMoveEvent(IEntity entity) {
        this.entity = entity;
    }

    public IEntity getEntity() {
        return entity;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
