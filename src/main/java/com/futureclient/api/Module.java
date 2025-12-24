package com.futureclient.api;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.input.Keyboard;

/**
 * Base class for all modules in FutureClient
 */
public abstract class Module {
    
    protected final MinecraftClient mc = MinecraftClient.getInstance();
    
    private final String name;
    private final String description;
    private final Category category;
    private boolean enabled;
    private int keyCode;
    
    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.keyCode = Keyboard.KEY_NONE;
    }
    
    /**
     * Called when the module is enabled
     */
    public void onEnable() {
        // Override in subclasses
    }
    
    /**
     * Called when the module is disabled
     */
    public void onDisable() {
        // Override in subclasses
    }
    
    /**
     * Called every client tick when enabled
     */
    public void onTick() {
        // Override in subclasses
    }
    
    /**
     * Called every render tick when enabled
     */
    public void onRender(float partialTicks) {
        // Override in subclasses
    }
    
    /**
     * Toggle the module on/off
     */
    public void toggle() {
        setEnabled(!enabled);
    }
    
    /**
     * Set the enabled state of the module
     */
    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;
        
        this.enabled = enabled;
        
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public int getKeyCode() {
        return keyCode;
    }
    
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }
}
