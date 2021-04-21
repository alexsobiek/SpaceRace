package com.alexsobiek.SpaceRace.event;

public interface Cancellable {
    void setCancelled(boolean cancel);
    boolean isCancelled();
}
