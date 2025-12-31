package com.futureclient.gui;

import net.minecraft.client.gui.DrawContext;

public class RenderUtils {
    // Lunar Colors
    public static final int BG_DARK = 0xFF181818;      // Main Background
    public static final int BG_SIDEBAR = 0xFF121212;   // Sidebar
    public static final int MOD_OFF = 0xFF2A2A2A;      // Module Off
    public static final int MOD_ON = 0xFF2A2A2A;       // Module On (Background same, accent changes)
    public static final int TEXT_WHITE = 0xFFFFFFFF;
    public static final int TEXT_GREY = 0xFFAAAAAA;
    public static final int ACCENT = 0xFF29B6F6;       // Lunar Blue

    public static void drawRect(DrawContext context, int x, int y, int width, int height, int color) {
        context.fill(x, y, x + width, y + height, color);
    }

    // Bordered Rect for Modules
    public static void drawRoundedRect(DrawContext context, int x, int y, int width, int height, int color, int radius) {
        // Simple approximation of rounded rect (Cross shape)
        context.fill(x + radius, y, x + width - radius, y + height, color); // Center horizontal
        context.fill(x, y + radius, x + width, y + height - radius, color); // Center vertical
        context.fill(x + radius, y + radius, x + width - radius, y + height - radius, color); // Fill center
    }

    // Toggle Switch (The pill shape)
    public static void drawToggle(DrawContext context, int x, int y, boolean enabled) {
        int width = 24;
        int height = 12;
        int color = enabled ? 0xFF29B6F6 : 0xFF444444; // Blue if ON, Grey if OFF
        
        // Draw Pill Body
        drawRect(context, x, y, width, height, color);
        
        // Draw Circle Indicator
        int circleX = enabled ? (x + width - 10) : (x + 2);
        drawRect(context, circleX, y + 2, 8, 8, 0xFFFFFFFF); // White circle
    }
}
