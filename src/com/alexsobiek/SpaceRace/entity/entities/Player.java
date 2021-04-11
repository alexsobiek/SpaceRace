package com.alexsobiek.SpaceRace.entity.entities;

import com.alexsobiek.SpaceRace.SpaceRace;
import com.alexsobiek.SpaceRace.Window;
import com.alexsobiek.SpaceRace.entity.IEntity;
import com.alexsobiek.SpaceRace.event.events.PlayerMoveEvent;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;
import java.util.UUID;

public class Player implements IEntity {
    private final int originalX;
    private final int originalY;
    private int x;
    private int y;
    private final int speed;
    private MoveDirection direction;
    private final UUID id;

    private int score;

    public Player(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        originalX = x;
        originalY = y;

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
        return (y > Window.winHeight || y <= 0);
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        score++;
    }

    public void resetPos() {
        x = originalX;
        y = originalY;
    }

    public void reset() {
        resetPos();
        score = 0;
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
