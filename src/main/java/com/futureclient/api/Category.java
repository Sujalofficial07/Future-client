package com.futureclient.api;

/**
 * Categories for organizing modules
 */
public enum Category {
    HUD("HUD", "Display elements on screen"),
    MOVEMENT("Movement", "Movement enhancements"),
    RENDER("Render", "Visual modifications"),
    PERFORMANCE("Performance", "FPS and optimization");
    
    private final String name;
    private final String description;
    
    Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
}
