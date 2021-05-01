package com.alexsobiek.spacerace.event;

public interface Cancellable {
    boolean isCancelled();

    void setCancelled(boolean cancel);
}
