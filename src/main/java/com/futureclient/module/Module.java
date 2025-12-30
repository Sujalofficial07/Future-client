package com.futureclient.module;

import net.minecraft.client.MinecraftClient;

public class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();
    private String name;
    private String description;
    private Category category;
    private int key;
    private boolean enabled;

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.key = -1;
        this.enabled = false;
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (this.enabled) onEnable(); else onDisable();
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}

    // Getters/Setters
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean e) { this.enabled = e; }
    public int getKey() { return key; }
    public void setKey(int k) { this.key = k; }
}
