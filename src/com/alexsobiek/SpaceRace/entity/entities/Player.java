package com.alexsobiek.SpaceRace.entity.entities;

import com.alexsobiek.SpaceRace.SpaceRace;
import com.alexsobiek.SpaceRace.Window;
import com.alexsobiek.SpaceRace.entity.IEntity;
import com.alexsobiek.SpaceRace.event.events.PlayerMoveEvent;

import java.awt.event.InputEvent;
import java.util.Random;
import java.util.UUID;

public class Player implements IEntity {
    private int x;
    private int y;
    private int offset;
    private int speed;
    private MoveDirection direction;
    private final UUID id;

    public Player() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public MoveDirection getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOutOfBounds() {
        return (x > Window.winWidth && direction == IEntity.MoveDirection.RIGHT || x < 0 && direction == IEntity.MoveDirection.LEFT);
    }

    public void reset() {
        if (direction == MoveDirection.LEFT) x = Window.winWidth + offset;
        else x = -offset;
    }

    @Override
    public void move(MoveDirection direction) {
        PlayerMoveEvent moveEvent = new PlayerMoveEvent(this);
        SpaceRace.EVENT_BUS.post(moveEvent);
        if (moveEvent.isCcancelled()) return;
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
        }

    }

    @Override
    public void delete() {

    }
}
