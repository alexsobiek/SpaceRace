package com.alexsobiek.SpaceRace.entity.entities;

import com.alexsobiek.SpaceRace.Window;
import com.alexsobiek.SpaceRace.entity.IEntity;

import java.util.Random;
import java.util.UUID;

public class Star implements IEntity {
    private int x;
    private int y;
    private int offset;
    private int speed;
    private MoveDirection direction;
    private static final Random rand = new Random();
    private final UUID id;

    public Star() {
        id = UUID.randomUUID();
        createStar();
    }


    private void createStar() {
        MoveDirection[] directions = {MoveDirection.LEFT, MoveDirection.RIGHT};
        direction = directions[rand.nextInt(directions.length)];
        offset = rand.nextInt(2000);
        speed = rand.nextInt(3);
        y = rand.nextInt((Window.winHeight / 4) * 3);
        reset();
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
        switch (direction) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
    }
    @Override
    public void delete() {

    }
}
