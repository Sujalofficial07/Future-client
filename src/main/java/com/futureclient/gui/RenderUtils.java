package com.futureclient.gui;

import net.minecraft.client.gui.DrawContext;

public class RenderUtils {
    // --- Modern Lunar Colors (New) ---
    public static final int BG_DARK = 0xFF181818;
    public static final int BG_SIDEBAR = 0xFF121212;
    public static final int MOD_OFF = 0xFF2A2A2A;
    public static final int MOD_ON = 0xFF2A2A2A;
    public static final int ACCENT = 0xFF29B6F6;       // Lunar Blue
    
    // --- Compatibility Colors (Old - Fixing your Errors) ---
    // Hum purane naamo ko naye colors se link kar rahe hain
    public static final int COLOR_BACKGROUND = BG_DARK; 
    public static final int COLOR_ACCENT = ACCENT;      
    public static final int COLOR_TEXT = 0xFFFFFFFF;

    // --- Basic Drawing ---
    public static void drawRect(DrawContext context, int x, int y, int width, int height, int color) {
        context.fill(x, y, x + width, y + height, color);
    }

    // --- Old Method (Fixing MainMenu Error) ---
    public static void drawBorderedRect(DrawContext context, int x, int y, int width, int height, int color, int borderColor) {
        drawRect(context, x, y, width, height, color);
        // Borders
        drawRect(context, x, y, width, 1, borderColor); // Top
        drawRect(context, x, y + height - 1, width, 1, borderColor); // Bottom
        drawRect(context, x, y, 1, height, borderColor); // Left
        drawRect(context, x + width - 1, y, 1, height, borderColor); // Right
    }

    // --- New Methods (For Mod Menu) ---
    public static void drawRoundedRect(DrawContext context, int x, int y, int width, int height, int color, int radius) {
        // Simple approximation of rounded rect
        context.fill(x + radius, y, x + width - radius, y + height, color); // Center horizontal
        context.fill(x, y + radius, x + width, y + height - radius, color); // Center vertical
        context.fill(x + radius, y + radius, x + width - radius, y + height - radius, color); // Fill center
    }

    public static void drawToggle(DrawContext context, int x, int y, boolean enabled) {
        int width = 24;
        int height = 12;
        int color = enabled ? ACCENT : 0xFF444444; // Blue if ON, Grey if OFF
        
        // Draw Pill Body
        drawRect(context, x, y, width, height, color);
        
        // Draw Circle Indicator
        int circleX = enabled ? (x + width - 10) : (x + 2);
        drawRect(context, circleX, y + 2, 8, 8, 0xFFFFFFFF); // White circle
    }
}
