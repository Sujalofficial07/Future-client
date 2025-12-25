package com.futureclient.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

/**
 * Utility class for rendering operations
 */
public class RenderUtil {
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    /**
     * Draw a rectangle with the specified color
     */
    public static void drawRect(double left, double top, double right, double bottom, int color) {
        Gui.drawRect((int)left, (int)top, (int)right, (int)bottom, color);
    }
    
    /**
     * Draw a rounded rectangle (simplified for 1.8.9)
     */
    public static void drawRoundedRect(double x, double y, double width, double height, double radius, int color) {
        // Simplified: just draw a regular rect for now (can be enhanced)
        drawRect(x, y, x + width, y + height, color);
    }
    
    /**
     * Draw text with shadow
     */
    public static void drawString(String text, int x, int y, int color) {
        mc.fontRendererObj.drawStringWithShadow(text, x, y, color);
    }
    
    /**
     * Draw text without shadow
     */
    public static void drawStringWithoutShadow(String text, int x, int y, int color) {
        mc.fontRendererObj.drawString(text, x, y, color);
    }
    
    /**
     * Get the width of a string
     */
    public static int getStringWidth(String text) {
        return mc.fontRendererObj.getStringWidth(text);
    }
    
    /**
     * Get the height of the font
     */
    public static int getFontHeight() {
        return mc.fontRendererObj.FONT_HEIGHT;
    }
}
