package com.futureclient.events;

/**
 * Event fired during rendering
 */
public class RenderEvent {
    
    private final float partialTicks;
    
    public RenderEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
    
    public float getPartialTicks() {
        return partialTicks;
    }
}
