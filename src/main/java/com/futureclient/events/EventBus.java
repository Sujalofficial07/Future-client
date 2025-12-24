package com.futureclient.events;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Tiny event bus. Modules can register listeners; events are dispatched synchronously.
 */
public class EventBus {
    private final List<Consumer<Event>> listeners = new ArrayList<>();

    public void register(Consumer<Event> listener) {
        listeners.add(listener);
    }

    public void unregister(Consumer<Event> listener) {
        listeners.remove(listener);
    }

    public void post(Event e) {
        for (Consumer<Event> l : listeners) {
            try {
                l.accept(e);
                if (e.isCancelled()) break;
            } catch (Throwable ignored) {}
        }
    }
}