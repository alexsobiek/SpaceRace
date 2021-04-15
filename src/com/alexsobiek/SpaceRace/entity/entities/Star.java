package com.alexsobiek.SpaceRace.entity.entities;

import com.alexsobiek.SpaceRace.Matrix;
import com.alexsobiek.SpaceRace.Window;
import com.alexsobiek.SpaceRace.entity.IEntity;
import com.alexsobiek.SpaceRace.entity.Location;

import java.io.FileNotFoundException;
import java.nio.file.Path;
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
        try {
            matrix = new Matrix("Player.mtx");
        } catch(FileNotFoundException e) {
            throw new Error(e);
        }
        MoveDirection[] directions = {MoveDirection.LEFT, MoveDirection.RIGHT};
        direction = directions[rand.nextInt(directions.length)];
        offset = rand.nextInt(2000);
        speed = rand.nextInt(3);
        reset();
    }

    /**
     * Gets the star's UUID
     * @return UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the star's MoveDirection Enum
     * @return MoveDirection
     */
    public MoveDirection getDirection() {
        return direction;
    }

    /**
     * Gets the star's location
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Checks whether or not the star is out of the window boundaries
     * @return boolean
     */
    public boolean isOutOfBounds() {
        return (location.getX() > Window.winWidth && direction == IEntity.MoveDirection.RIGHT || location.getY() < 0 && direction == IEntity.MoveDirection.LEFT);
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
                location.setX(location.getX() - speed);
                break;
            case RIGHT:
                location.setX(location.getX() + speed);
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
