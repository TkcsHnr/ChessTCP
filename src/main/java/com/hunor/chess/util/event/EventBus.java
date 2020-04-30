package com.hunor.chess.util.event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventBus {

    private Map<Class, List<EventListener>> listeners = new HashMap<>();

    public <T extends Event> void listenFor(Class<T> eventClass, EventListener<T> listener) {
        if (!listeners.containsKey(eventClass)) {
            listeners.put(eventClass, new LinkedList<>());
        }
        listeners.get(eventClass).add(listener);
    }

    public void emit(Event event) {
        Class eventClass = event.getClass();
        if (listeners.get(eventClass) == null)
            return;

        for (EventListener eventListener : listeners.get(eventClass)) {
            eventListener.handle(event);
        }
    }

}
