package com.alexsobiek.spacerace.entity.entities;

import com.alexsobiek.spacerace.entity.IEntity;
import com.alexsobiek.spacerace.entity.Location;
import com.alexsobiek.spacerace.graphics.Window;

import java.util.Random;
import java.util.UUID;

public class Star implements IEntity {
    private final Window window;
    private final int offset;
    private final int speed;
    private final MoveDirection direction;
    private final UUID id;
    private final Random rand = new Random();
    private Location location;

    /**
     * Constructor:
     * Creates a new star entity
     */
    public Star(Window window) {
        this.window = window;
        id = UUID.randomUUID();
        MoveDirection[] directions = {MoveDirection.LEFT, MoveDirection.RIGHT};
        direction = directions[rand.nextInt(directions.length)];
        offset = rand.nextInt(2000);
        speed = rand.nextInt(3);
        reset();
    }

    /**
     * Gets the star's UUID
     *
     * @return UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the star's MoveDirection Enum
     *
     * @return MoveDirection
     */
    public MoveDirection getDirection() {
        return direction;
    }

    /**
     * Gets the star's location
     *
     * @return Location
     */
    @Override
    public Location getLocation() {
        return location;
    }

    /**
     * Checks whether or not the star is out of the window boundaries
     *
     * @return boolean
     */
    public boolean isOutOfBounds() {
        return (location.getX() > window.getWinWidth() && direction == IEntity.MoveDirection.RIGHT ||
                location.getX() < 0 && direction == IEntity.MoveDirection.LEFT);
    }

    /**
     * Resets the stars position
     */
    public void reset() {
        int y = rand.nextInt((window.getWinHeight() / 4) * 3);
        int x;
        if (direction == MoveDirection.LEFT) x = window.getWinWidth() + offset;
        else x = -offset;
        location = null;
        location = new Location(x, y);
    }

    @Override
    public void move(MoveDirection direction) {
        if (fireMoveEvent()) {
            switch (direction) {
                case LEFT:
                    location.setX(location.getX() - speed);
                    break;
                case RIGHT:
                    location.setX(location.getX() + speed);
                    break;
            }
        }
    }

    /**
     * Deletes the star
     */
    @Override
    public void delete() {
        throw new UnsupportedOperationException();
    }
}
