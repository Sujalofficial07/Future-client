package com.futureclient.module;

import com.futureclient.event.Event;
import net.minecraft.client.MinecraftClient;

public class Module {
    protected MinecraftClient mc = MinecraftClient.getInstance();
    private String name;
    private String description;
    private int key;
    private Category category;
    private boolean enabled;

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.key = 0;
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (this.enabled) onEnable();
        else onDisable();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onEvent(Event event) {}

    // Getters and Setters
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }
    public boolean isEnabled() { return enabled; }
}
