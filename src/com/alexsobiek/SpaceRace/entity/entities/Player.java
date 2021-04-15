package com.alexsobiek.SpaceRace.entity.entities;

import com.alexsobiek.SpaceRace.SpaceRace;
import com.alexsobiek.SpaceRace.Window;
import com.alexsobiek.SpaceRace.entity.IEntity;
import com.alexsobiek.SpaceRace.event.events.PlayerMoveEvent;
import jdk.nashorn.internal.objects.annotations.Getter;

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

    /**
     * Constructor:
     * Creates a new player entity
     * @param x Starting X coordinate
     * @param y Starting Y coordinate
     * @param speed Speed (pixels per tick)
     */
    public Player(int x, int y, int speed) {
        this.speed = speed;
        originalX = x;
        originalY = y;

        id = UUID.randomUUID();
    }

    /**
     * Get the player's UUID
     * @return UUID
     */
    @Getter
    public UUID getId() {
        return id;
    }

    /**
     * Used for getting the MoveDirection of the player
     * @return MoveDirection
     */
    @Getter
    public MoveDirection getDirection() {
        return direction;
    }


    public int getY() {
        return y;
    }

    /**
     * Checks if player is out of the boundaries of the window
     * @return boolean
     */
    public boolean isOutOfBounds() {
        return (y > Window.winHeight || y <= 0);
    }

    /**
     * Returns player's score
     * @return int
     */
    @Getter
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
     * Resets a player's score and position
     */
    public void reset() {
        resetPos();
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
                y -= speed;
                break;
            case DOWN:
                y += speed;
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
