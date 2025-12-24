package com.futureclient.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Simple event bus for handling events in FutureClient
 */
public class EventBus {
    
    private final Map<Class<?>, List<Consumer<?>>> listeners = new HashMap<>();
    
    /**
     * Register a listener for an event type
     */
    public <T> void subscribe(Class<T> eventClass, Consumer<T> listener) {
        listeners.computeIfAbsent(eventClass, k -> new ArrayList<>()).add(listener);
    }
    
    /**
     * Post an event to all registered listeners
     */
    @SuppressWarnings("unchecked")
    public <T> void post(T event) {
        List<Consumer<?>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (Consumer<?> listener : eventListeners) {
                ((Consumer<T>) listener).accept(event);
            }
        }
    }
}
