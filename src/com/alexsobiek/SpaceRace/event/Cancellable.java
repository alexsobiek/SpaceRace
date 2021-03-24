package com.alexsobiek.SpaceRace.event;

public interface Cancellable {
    void setCcancelled(boolean cancel);

    boolean isCcancelled();
}
