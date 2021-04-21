package com.alexsobiek.SpaceRace.entity;

import com.alexsobiek.SpaceRace.SpaceRace;
import com.alexsobiek.SpaceRace.event.events.EntityMoveEvent;

import java.util.Random;

//Just to tag all entities
public interface IEntity {
    Random rand = new Random();

    void move(MoveDirection direction);

    void delete();

    Location getLocation();

    default boolean fireMoveEvent() {
        EntityMoveEvent moveEvent = new EntityMoveEvent(this);
        SpaceRace.EVENT_BUS.post(moveEvent);
        return !moveEvent.isCancelled();
    }

    enum MoveDirection {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
}
