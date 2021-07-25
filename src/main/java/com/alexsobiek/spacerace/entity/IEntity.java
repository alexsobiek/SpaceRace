package com.alexsobiek.spacerace.entity;

import com.alexsobiek.spacerace.SpaceRace;
import com.alexsobiek.spacerace.event.events.EntityMoveEvent;

import java.util.Random;

//Just to tag all entities
public interface IEntity {
    Random rand = new Random();

    void move(MoveDirection direction);

    void delete();

    Location getLocation();

    default boolean fireMoveEvent() {
        EntityMoveEvent moveEvent = new EntityMoveEvent(this);
        SpaceRace.eventBus.post(moveEvent);
        return !moveEvent.isCancelled();
    }

    enum MoveDirection {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
}
