package com.alexsobiek.SpaceRace.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class EventBus {
    private final ConcurrentHashMap<Listener, List<ListenerInfo>> listeners = new ConcurrentHashMap<>();

    public void subscribe(Listener listener) {
        for (Method method : listener.getClass().getMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                if (method.getParameterCount() == 1) {
                    Class<?> param = method.getParameters()[0].getType();
                    if (param.getSuperclass().equals(Event.class) || param.getSuperclass().getSuperclass().equals(Event.class)) {
                        List<ListenerInfo> infos = new ArrayList<>();
                        infos.add(new ListenerInfo(listener, method));
                        listeners.put(listener, infos);
                    }
                }
            }
        }
    }

    public void unSubscribe(Listener listener) {
        listeners.remove(listener);
    }

    public synchronized void post(Event event) {
        listeners.forEach((listener, listenerInfos) -> listenerInfos.forEach(listenerInfo -> {
            try {
                Method method = listenerInfo.method;
                Class<?> param = method.getParameters()[0].getType();
                if (param.equals(event.getClass())) {
                    listenerInfo.method.invoke(listenerInfo.target, event);
                }
            } catch (Throwable t) {
            }
        }));
    }

    private static final class ListenerInfo {
        public final Listener target;
        public final Method method;

        public ListenerInfo(Listener target, Method method) {
            this.target = target;
            this.method = method;
        }
    }
}
