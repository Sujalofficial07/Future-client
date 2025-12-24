package com.futureclient.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.WorldRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Utility class for rendering operations
 */
public class RenderUtil {
    
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    
    /**
     * Draw a rectangle with the specified color
     */
    public static void drawRect(double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double temp = left;
            left = right;
            right = temp;
        }
        
        if (top < bottom) {
            double temp = top;
            top = bottom;
            bottom = temp;
        }
        
        float alpha = (float)(color >> 24 & 255) / 255.0F;
        float red = (float)(color >> 16 & 255) / 255.0F;
        float green = (float)(color >> 8 & 255) / 255.0F;
        float blue = (float)(color & 255) / 255.0F;
        
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer buffer = tessellator.getWorldRenderer();
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(red, green, blue, alpha);
        
        buffer.begin(7, net.minecraft.client.render.VertexFormats.POSITION);
        buffer.vertex(left, bottom, 0.0D).next();
        buffer.vertex(right, bottom, 0.0D).next();
        buffer.vertex(right, top, 0.0D).next();
        buffer.vertex(left, top, 0.0D).next();
        tessellator.draw();
        
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    /**
     * Draw a rounded rectangle
     */
    public static void drawRoundedRect(double x, double y, double width, double height, double radius, int color) {
        drawRect(x + radius, y, x + width - radius, y + height, color);
        drawRect(x, y + radius, x + width, y + height - radius, color);
        
        // Corners (simplified for performance)
        drawRect(x, y, x + radius, y + radius, color);
        drawRect(x + width - radius, y, x + width, y + radius, color);
        drawRect(x, y + height - radius, x + radius, y + height, color);
        drawRect(x + width - radius, y + height - radius, x + width, y + height, color);
    }
    
    /**
     * Draw text with shadow
     */
    public static void drawString(String text, int x, int y, int color) {
        mc.textRenderer.drawWithShadow(text, x, y, color);
    }
    
    /**
     * Draw text without shadow
     */
    public static void drawStringWithoutShadow(String text, int x, int y, int color) {
        mc.textRenderer.draw(text, x, y, color);
    }
    
    /**
     * Get the width of a string
     */
    public static int getStringWidth(String text) {
        return mc.textRenderer.getStringWidth(text);
    }
    
    /**
     * Get the height of the font
     */
    public static int getFontHeight() {
        return mc.textRenderer.fontHeight;
    }
}
