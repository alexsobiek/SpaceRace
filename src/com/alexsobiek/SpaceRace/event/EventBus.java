package com.alexsobiek.SpaceRace.event;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class EventBus {
    ArrayList<Listener> listeners = new ArrayList<>();

    public void subscribe(Listener listener) {
        if (listeners.contains(listener)) return;
        listeners.add(listener);
    }

    public void unSubscribe(Listener listener) {
        if (!listeners.contains(listener)) return;
        listeners.remove(listener);
    }

    public void post(Event event) {
        for (Listener listener : listeners) {
            Method[] methods = listener.getClass().getMethods();
            if (methods.length > 0) {
                for (Method method : methods) {
                    if (method.isAnnotationPresent(EventHandler.class)) {
                        if (method.getParameterCount() > 0) {
                            Parameter param = method.getParameters()[0];
                            if (param.getType().getSuperclass().getSimpleName().equals("Event")) {
                                try {
                                    method.invoke(event);
                                } catch (Throwable t) {
                                    t.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}