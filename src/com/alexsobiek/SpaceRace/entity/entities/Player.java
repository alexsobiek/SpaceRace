package com.alexsobiek.SpaceRace.entity.entities;

import com.alexsobiek.SpaceRace.Matrix;
import com.alexsobiek.SpaceRace.SpaceRace;
import com.alexsobiek.SpaceRace.Window;
import com.alexsobiek.SpaceRace.entity.IEntity;
import com.alexsobiek.SpaceRace.entity.Location;
import com.alexsobiek.SpaceRace.event.events.PlayerMoveEvent;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.UUID;

public class Player implements IEntity {
    private final Matrix matrix;
    private final Location location;
    private final int speed;
    private MoveDirection direction;
    private final UUID id;

    private int score;

    /**
     * Constructor:
     * Creates a new player entity
     * @param x Starting X coordinate
     * @param y Starting Y coordinate
     * @param speed Speed (pixels per tick)
     */
    public Player(int x, int y, int speed) {
        this.speed = speed;
        location = new Location(x, y);
        id = UUID.randomUUID();
        try {
            matrix = new Matrix("models/Player.mtx");
        } catch(FileNotFoundException e) {
            throw new Error(e);
        }
    }

    /**
     * Get the player's UUID
     * @return UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Used for getting the MoveDirection of the player
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
     * Checks if player is out of the boundaries of the window
     * @return boolean
     */
    public boolean isOutOfBounds() {
        return (location.getY() > Window.winHeight || location.getY() <= 0);
    }

    /**
     * Returns player's score
     * @return int
     */
    public int getScore() {
        return score;
    }

    /**
     * Increments player's score by 1
     */
    public void addScore() {
        score++;
    }

    /**
     * Gets the player model matrix
     * @return Matrix
     */
    public Matrix getMatrix() {
        return matrix;
    }

    /**
     * Resets a player's score and position
     */
    public void reset() {
        location.reset();
        score = 0;
    }

    /**
     * Moves the player relative to their speed in the provided direction
     * @param direction MoveDirection we should move the player
     */
    @Override
    public void move(MoveDirection direction) {
        PlayerMoveEvent moveEvent = new PlayerMoveEvent(this);
        SpaceRace.EVENT_BUS.post(moveEvent);
        if (moveEvent.isCancelled()) return;
        switch (direction) {
            case UP:
                location.setY(location.getY() - speed);
                break;
            case DOWN:
                location.setY(location.getY() + speed);
                break;
        }
    }

    /**
     * Deletes the player
     */
    @Override
    public void delete() {

    }
}
