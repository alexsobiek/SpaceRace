package com.alexsobiek.SpaceRace.event.events;

import com.alexsobiek.SpaceRace.event.Event;

//For testing the efficiency of the event system
public class TestEvent extends Event {
    private final long createTime;

    public TestEvent(long createTime) {
        this.createTime = createTime;
    }

    public long getCreateTime() {
        return createTime;
    }
}
