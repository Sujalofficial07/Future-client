package com.futureclient.modules.hud;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import com.futureclient.util.ColorUtil;
import com.futureclient.util.RenderUtil;
import org.lwjgl.input.Mouse;

public class KeystrokesModule extends Module {
    private int x = 100;
    private int y = 100;
    private final int keySize = 25;
    private final int padding = 2;
    
    private long[] lastClickTime = new long[2];
    private int[] clickCount = new int[2];
    private long lastResetTime = System.currentTimeMillis();
    
    public KeystrokesModule() {
        super("Keystrokes", "Display key presses", Category.HUD);
        setEnabled(true);
    }
    
    @Override
    public void onRender(float partialTicks) {
        updateCPS();
        
        // W key
        drawKey("W", x + keySize + padding, y, mc.gameSettings.keyBindForward.isKeyDown());
        
        // A key
        drawKey("A", x, y + keySize + padding, mc.gameSettings.keyBindLeft.isKeyDown());
        
        // S key
        drawKey("S", x + keySize + padding, y + keySize + padding, mc.gameSettings.keyBindBack.isKeyDown());
        
        // D key
        drawKey("D", x + (keySize + padding) * 2, y + keySize + padding, mc.gameSettings.keyBindRight.isKeyDown());
        
        // Mouse buttons
        boolean lmbPressed = Mouse.isButtonDown(0);
        if (lmbPressed && !wasPressed(0)) {
            registerClick(0);
        }
        drawMouseButton("LMB", x, y + (keySize + padding) * 2, lmbPressed, getCPS(0));
        
        boolean rmbPressed = Mouse.isButtonDown(1);
        if (rmbPressed && !wasPressed(1)) {
            registerClick(1);
        }
        drawMouseButton("RMB", x + keySize + padding + 5, y + (keySize + padding) * 2, rmbPressed, getCPS(1));
    }
    
    private void drawKey(String key, int x, int y, boolean pressed) {
        int bgColor = pressed ? ColorUtil.rgba(100, 100, 100, 200) : ColorUtil.rgba(0, 0, 0, 150);
        RenderUtil.drawRect(x, y, x + keySize, y + keySize, bgColor);
        
        int textColor = pressed ? 0xFFFFFFFF : 0xFFAAAAAA;
        int textX = x + (keySize - RenderUtil.getStringWidth(key)) / 2;
        int textY = y + (keySize - RenderUtil.getFontHeight()) / 2;
        RenderUtil.drawString(key, textX, textY, textColor);
    }
    
    private void drawMouseButton(String button, int x, int y, boolean pressed, int cps) {
        int width = 35;
        int bgColor = pressed ? ColorUtil.rgba(100, 100, 100, 200) : ColorUtil.rgba(0, 0, 0, 150);
        RenderUtil.drawRect(x, y, x + width, y + keySize, bgColor);
        
        int textColor = pressed ? 0xFFFFFFFF : 0xFFAAAAAA;
        String text = button + " " + cps;
        int textX = x + (width - RenderUtil.getStringWidth(text)) / 2;
        int textY = y + (keySize - RenderUtil.getFontHeight()) / 2;
        RenderUtil.drawString(text, textX, textY, textColor);
    }
    
    private void registerClick(int button) {
        if (button >= 0 && button < 2) {
            clickCount[button]++;
            lastClickTime[button] = System.currentTimeMillis();
        }
    }
    
    private boolean wasPressed(int button) {
        return lastClickTime[button] != 0 && (System.currentTimeMillis() - lastClickTime[button]) < 50;
    }
    
    private int getCPS(int button) {
        return (button >= 0 && button < 2) ? clickCount[button] : 0;
    }
    
    private void updateCPS() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastResetTime >= 1000) {
            clickCount[0] = 0;
            clickCount[1] = 0;
            lastResetTime = currentTime;
        }
    }
    
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
}
