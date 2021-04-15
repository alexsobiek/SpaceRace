package com.alexsobiek.SpaceRace.entity.entities;

import com.alexsobiek.SpaceRace.Matrix;
import com.alexsobiek.SpaceRace.Window;
import com.alexsobiek.SpaceRace.entity.IEntity;
import com.alexsobiek.SpaceRace.entity.Location;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.io.FileNotFoundException;
import java.util.UUID;

public class Star implements IEntity {
    private Location location;
    private Matrix matrix;
    private final int offset;
    private final int speed;
    private final MoveDirection direction;
    private final UUID id;

    /**
     * Constructor:
     * Creates a new star entity
     */
    public Star() {
        id = UUID.randomUUID();
        createStar();
    }


    private void createStar() {
        direction = directions[rand.nextInt(directions.length)];
        offset = rand.nextInt(2000);
        speed = rand.nextInt(3);
        reset();
    }

    /**
     * Gets the star's UUID
     * @return UUID
     */
    @Getter
    public UUID getId() {
        return id;
    }

    /**
     * Gets the star's MoveDirection Enum
     * @return MoveDirection
     */
    @Getter
    public MoveDirection getDirection() {
        return direction;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    /**
     * Checks whether or not the star is out of the window boundaries
     * @return boolean
     */
    public boolean isOutOfBounds() {
        return (x > Window.winWidth && direction == IEntity.MoveDirection.RIGHT || x < 0 && direction == IEntity.MoveDirection.LEFT);
    }

    /**
     * Resets the stars position
     */
    public void reset() {
        int y = rand.nextInt((Window.winHeight / 4) * 3);
        int x;
        if (direction == MoveDirection.LEFT) x = Window.winWidth + offset;
        else x = -offset;
        location = null;
        location = new Location(x, y);
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

    /**
     * Deletes the star
     */
    @Override
    public void delete() {

    }
}
