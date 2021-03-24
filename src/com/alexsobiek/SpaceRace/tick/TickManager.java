package com.alexsobiek.SpaceRace.tick;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TickManager implements TickHandler {
    public static final List<TickHandler> listeners = new ArrayList<>();

    public static void subscribe(TickHandler listener) {
        listeners.add(listener);
    }

    public static void doTick() {
        listeners.forEach(listener -> {
            Method[] methods = listener.getClass().getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Tick.class)) {
                    try {
                        method.invoke(listener);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }



}
