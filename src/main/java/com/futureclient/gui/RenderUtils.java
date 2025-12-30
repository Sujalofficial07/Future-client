package com.futureclient.gui;

import net.minecraft.client.gui.DrawContext;

public class RenderUtils {
    // Lunar Style Colors
    public static final int COLOR_BACKGROUND = 0xFF181818; // Dark Grey
    public static final int COLOR_SECONDARY = 0xFF242424; // Lighter Grey
    public static final int COLOR_ACCENT = 0xFF29B6F6;    // Lunar Blue
    public static final int COLOR_TEXT = 0xFFFFFFFF;      // White

    public static void drawRect(DrawContext context, int x, int y, int width, int height, int color) {
        context.fill(x, y, x + width, y + height, color);
    }

    // Bordered Box logic for Modules
    public static void drawBorderedRect(DrawContext context, int x, int y, int width, int height, int color, int borderColor) {
        drawRect(context, x, y, width, height, color);
        // Borders
        drawRect(context, x, y, width, 1, borderColor); // Top
        drawRect(context, x, y + height - 1, width, 1, borderColor); // Bottom
        drawRect(context, x, y, 1, height, borderColor); // Left
        drawRect(context, x + width - 1, y, 1, height, borderColor); // Right
    }
}
