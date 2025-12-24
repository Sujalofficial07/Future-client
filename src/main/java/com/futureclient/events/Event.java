package com.futureclient.events;

/**
 * Base event type (very small, synchronous).
 */
public class Event {
    private boolean cancelled = false;

    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
}