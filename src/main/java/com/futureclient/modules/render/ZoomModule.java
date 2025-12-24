package com.futureclient.modules.render;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import org.lwjgl.input.Keyboard;

/**
 * Smooth zoom functionality
 */
public class ZoomModule extends Module {
    
    private float originalFov = 70.0f;
    private float targetFov = 30.0f;
    private float currentFov = 70.0f;
    private boolean zooming = false;
    
    public ZoomModule() {
        super("Zoom", "Smooth zoom", Category.RENDER);
        setKeyCode(Keyboard.KEY_C);
    }
    
    @Override
    public void onEnable() {
        if (mc.options != null) {
            originalFov = mc.options.fov;
            zooming = true;
        }
    }
    
    @Override
    public void onDisable() {
        zooming = false;
    }
    
    @Override
    public void onTick() {
        if (mc.options == null) return;
        
        if (zooming) {
            // Smooth transition to zoom
            if (currentFov > targetFov) {
                currentFov -= (currentFov - targetFov) * 0.5f;
                if (Math.abs(currentFov - targetFov) < 0.5f) {
                    currentFov = targetFov;
                }
            }
        } else {
            // Smooth transition back to original FOV
            if (currentFov < originalFov) {
                currentFov += (originalFov - currentFov) * 0.5f;
                if (Math.abs(currentFov - originalFov) < 0.5f) {
                    currentFov = originalFov;
                }
            }
        }
        
        mc.options.fov = currentFov;
    }
}
