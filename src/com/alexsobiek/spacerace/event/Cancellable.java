package com.alexsobiek.spacerace.event;

public interface Cancellable {
    void setCancelled(boolean cancel);
    boolean isCancelled();
}
