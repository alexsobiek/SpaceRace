package com.alexsobiek.spacerace.entity.entities;

import com.alexsobiek.spacerace.entity.IEntity;
import com.alexsobiek.spacerace.entity.Location;
import com.alexsobiek.spacerace.graphics.PlayerModel;
import com.alexsobiek.spacerace.graphics.Window;

import java.util.UUID;

public class Player implements IEntity {
    private final PlayerModel playerModel;
    private final int speed;
    private final UUID id;
    private Location location;
    private MoveDirection direction;
    private int score;

    /**
     * Constructor:
     * Creates a new player entity
     *
     * @param x     Starting X coordinate
     * @param y     Starting Y coordinate
     * @param speed Speed (pixels per tick)
     */
    public Player(int x, int y, int speed) {
        this.speed = speed;
        location = new Location(x, y);
        id = UUID.randomUUID();
        playerModel = new PlayerModel(location);
    }

    /**
     * Get the player's UUID
     *
     * @return UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Used for getting the MoveDirection of the player
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
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        int deltaX = location.getX() - this.location.getX();
        int deltaY = location.getY() - this.location.getY();
        this.location = location;
        playerModel.getPolygon().translate(deltaX, deltaY);
        playerModel.setSpawn(location);
    }

    /**
     * Checks if player is out of the boundaries of the window
     *
     * @return boolean
     */
    public boolean isOutOfBounds() {
        return (location.getY() > Window.winHeight || location.getY() <= 0);
    }

    /**
     * Returns player's score
     *
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

    public PlayerModel getModel() {
        return playerModel;
    }

    /**
     * Resets a player's score and position
     */
    public void reset(boolean resetScore) {
        location.reset();
        playerModel.reset();
        if (resetScore) score = 0;
    }

    /**
     * Moves the player relative to their speed in the provided direction
     *
     * @param direction MoveDirection we should move the player
     */
    @Override
    public void move(MoveDirection direction) {
        if (fireMoveEvent()) {
            int y;
            switch (direction) {
                case UP -> {
                    y = location.getY() - speed;
                    location.setY(y);
                    playerModel.getPolygon().translate(0, -speed);
                }
                case DOWN -> {
                    y = location.getY() + speed;
                    location.setY(y);
                    playerModel.getPolygon().translate(0, speed);
                }
            }
        }
    }

    /**
     * Deletes the player
     */
    @Override
    public void delete() {
        throw new UnsupportedOperationException();
    }
}
