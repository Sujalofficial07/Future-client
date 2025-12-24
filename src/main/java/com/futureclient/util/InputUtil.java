package com.futureclient.util;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * Utility class for input handling
 */
public class InputUtil {
    
    private static long[] lastClickTimes = new long[2]; // Left and right mouse buttons
    private static int[] clickCounts = new int[2];
    private static long lastResetTime = System.currentTimeMillis();
    
    /**
     * Check if a key is currently pressed
     */
    public static boolean isKeyDown(int keyCode) {
        return Keyboard.isKeyDown(keyCode);
    }
    
    /**
     * Check if a mouse button is currently pressed
     */
    public static boolean isMouseButtonDown(int button) {
        return Mouse.isButtonDown(button);
    }
    
    /**
     * Get the key name from key code
     */
    public static String getKeyName(int keyCode) {
        return Keyboard.getKeyName(keyCode);
    }
    
    /**
     * Register a mouse click for CPS tracking
     */
    public static void registerClick(int button) {
        if (button >= 0 && button < 2) {
            clickCounts[button]++;
            lastClickTimes[button] = System.currentTimeMillis();
        }
    }
    
    /**
     * Get the current CPS for a mouse button
     */
    public static int getCPS(int button) {
        if (button < 0 || button >= 2) return 0;
        
        long currentTime = System.currentTimeMillis();
        
        // Reset every second
        if (currentTime - lastResetTime >= 1000) {
            clickCounts[0] = 0;
            clickCounts[1] = 0;
            lastResetTime = currentTime;
        }
        
        return clickCounts[button];
    }
    
    /**
     * Get combined CPS (left + right)
     */
    public static int getTotalCPS() {
        return getCPS(0) + getCPS(1);
    }
}
