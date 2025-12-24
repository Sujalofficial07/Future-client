package com.futureclient.core;

import com.futureclient.settings.Setting;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Base module class. Keep lightweight.
 */
public abstract class Module {
    private final String name;
    private final Category category;
    private final int defaultKeybind;
    private boolean enabled = false;
    private final List<Setting> settings = new ArrayList<>();

    public Module(String name, Category category, int defaultKeybind) {
        this.name = name;
        this.category = category;
        this.defaultKeybind = defaultKeybind;
    }

    public String getName() { return name; }
    public Category getCategory() { return category; }
    public int getDefaultKeybind() { return defaultKeybind; }

    public boolean isEnabled() { return enabled; }

    public void enable() {
        if (enabled) return;
        enabled = true;
        onEnable();
    }

    public void disable() {
        if (!enabled) return;
        enabled = false;
        onDisable();
    }

    public void toggle() {
        if (enabled) disable();
        else enable();
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onUpdate() {}
    public void onRender() {}

    public List<Setting> getSettings() { return settings; }
    public void addSetting(Setting s) { settings.add(s); }

    @Override
    public String toString() {
        return name + " [" + (enabled ? "ENABLED" : "DISABLED") + "]";
    }
}