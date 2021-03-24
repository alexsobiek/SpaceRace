package com.alexsobiek.SpaceRace;
//Just to tag all entities
public interface IEntity {
    void move(MoveDirection direction);
    void delete();
    enum MoveDirection {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
}
