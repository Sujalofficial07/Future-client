package com.futureclient.settings;

/**
 * Marker base for settings. Name used as key for JSON.
 */
public abstract class Setting {
    private final String name;

    protected Setting(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}