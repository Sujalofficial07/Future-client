package com.futureclient.modules.hud;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import com.futureclient.util.RenderUtil;
import org.lwjgl.input.Mouse;

/**
 * Displays clicks per second
 */
public class CPSModule extends Module {
    
    private int x = 5;
    private int y = 65;
    
    private long[] lastClickTime = new long[2];
    private int[] clickCount = new int[2];
    private long lastResetTime = System.currentTimeMillis();
    private boolean[] wasPressed = new boolean[2];
    
    public CPSModule() {
        super("CPS", "Display clicks per second", Category.HUD);
        setEnabled(true);
    }
    
    @Override
    public void onTick() {
        updateCPS();
        
        // Track left click
        boolean lmbPressed = Mouse.isButtonDown(0);
        if (lmbPressed && !wasPressed[0]) {
            registerClick(0);
        }
        wasPressed[0] = lmbPressed;
        
        // Track right click
        boolean rmbPressed = Mouse.isButtonDown(1);
        if (rmbPressed && !wasPressed[1]) {
            registerClick(1);
        }
        wasPressed[1] = rmbPressed;
    }
    
    @Override
    public void onRender(float partialTicks) {
        int totalCPS = clickCount[0] + clickCount[1];
        String text = "CPS: " + totalCPS + " [" + clickCount[0] + " | " + clickCount[1] + "]";
        RenderUtil.drawString(text, x, y, 0xFFFFFFFF);
    }
    
    private void registerClick(int button) {
        if (button >= 0 && button < 2) {
            clickCount[button]++;
            lastClickTime[button] = System.currentTimeMillis();
        }
    }
    
    private void updateCPS() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastResetTime >= 1000) {
            clickCount[0] = 0;
            clickCount[1] = 0;
            lastResetTime = currentTime;
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
