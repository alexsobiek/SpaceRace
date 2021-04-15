package com.alexsobiek.SpaceRace.entity;

import java.util.Random;

//Just to tag all entities
public interface IEntity {
    Random rand = new Random();
    void move(MoveDirection direction);
    void delete();

    enum MoveDirection {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
}
