package com.futureclient.modules.hud;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import com.futureclient.util.RenderUtil;
import net.minecraft.client.MinecraftClient;

/**
 * Displays current FPS on screen
 */
public class FPSModule extends Module {
    
    private int x = 5;
    private int y = 5;
    
    public FPSModule() {
        super("FPS", "Display frames per second", Category.HUD);
        setEnabled(true); // Enabled by default
    }
    
    @Override
    public void onRender(float partialTicks) {
        int fps = MinecraftClient.getInstance().getCurrentFps();
        String text = "FPS: " + fps;
        
        int color = getFPSColor(fps);
        RenderUtil.drawString(text, x, y, color);
    }
    
    /**
     * Get color based on FPS value
     */
    private int getFPSColor(int fps) {
        if (fps >= 60) {
            return 0xFF55FF55; // Green
        } else if (fps >= 30) {
            return 0xFFFFFF55; // Yellow
        } else {
            return 0xFFFF5555; // Red
        }
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}
