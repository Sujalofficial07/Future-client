package com.futureclient.modules.render;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import org.lwjgl.input.Keyboard;

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
        if (mc.gameSettings != null) {
            originalFov = mc.gameSettings.fovSetting;
            zooming = true;
        }
    }
    
    @Override
    public void onDisable() {
        zooming = false;
    }
    
    @Override
    public void onTick() {
        if (mc.gameSettings == null) return;
        
        if (zooming) {
            if (currentFov > targetFov) {
                currentFov -= (currentFov - targetFov) * 0.5f;
                if (Math.abs(currentFov - targetFov) < 0.5f) {
                    currentFov = targetFov;
                }
            }
        } else {
            if (currentFov < originalFov) {
                currentFov += (originalFov - currentFov) * 0.5f;
                if (Math.abs(currentFov - originalFov) < 0.5f) {
                    currentFov = originalFov;
                }
            }
        }
        
        mc.gameSettings.fovSetting = currentFov;
    }
}
