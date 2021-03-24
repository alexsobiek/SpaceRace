package com.alexsobiek.SpaceRace.entity.entities;

import com.alexsobiek.SpaceRace.SpaceRace;
import com.alexsobiek.SpaceRace.Window;
import com.alexsobiek.SpaceRace.entity.IEntity;
import com.alexsobiek.SpaceRace.event.events.PlayerMoveEvent;

import java.awt.event.InputEvent;
import java.util.Random;
import java.util.UUID;

public class Player implements IEntity {
    private int[] originalPos = new int[2];
    private int x;
    private int y;
    private int speed;
    private MoveDirection direction;
    private final UUID id;

    public Player(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        originalPos[0] = x;
        originalPos[1] = y;

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
        return (y > Window.winHeight && direction == IEntity.MoveDirection.DOWN || y < 0 && direction == IEntity.MoveDirection.UP);
    }

    public void reset() {
        x = originalPos[0];
        y = originalPos[1];
    }

    @Override
    public void move(MoveDirection direction) {
        PlayerMoveEvent moveEvent = new PlayerMoveEvent(this);
        SpaceRace.EVENT_BUS.post(moveEvent);
        if (moveEvent.isCancelled()) return;
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
