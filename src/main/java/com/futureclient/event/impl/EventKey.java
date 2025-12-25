package com.futureclient.event.impl;

import com.futureclient.event.Event;

public class EventKey extends Event {
    private int key;
    public EventKey(int key) { this.key = key; }
    public int getKey() { return key; }
}
