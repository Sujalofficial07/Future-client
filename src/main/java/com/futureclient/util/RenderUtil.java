package com.futureclient.util;

import net.minecraft.client.gui.DrawableHelper;
import org.lwjgl.opengl.GL11;
import java.awt.Color;

public class RenderUtil {

    public static void drawRect(float x, float y, float w, float h, int color) {
        float alpha = (float) (color >> 24 & 255) / 255.0F;
        float red = (float) (color >> 16 & 255) / 255.0F;
        float green = (float) (color >> 8 & 255) / 255.0F;
        float blue = (float) (color & 255) / 255.0F;
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(red, green, blue, alpha);
        
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x, y + h);
        GL11.glVertex2f(x + w, y + h);
        GL11.glVertex2f(x + w, y);
        GL11.glVertex2f(x, y);
        GL11.glEnd();
        
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    // Simple rounded rect using GL_POINTS or GL_POLYGON would be complex, 
    // so we simulate it by drawing a center rect and 4 side rects + corners if needed.
    // For this implementation, we will stick to clean, modern flat rectangles 
    // which Lunar also uses for its "Mods" list.
    public static void drawBorderedRect(float x, float y, float w, float h, float thickness, int insideColor, int borderColor) {
        drawRect(x + thickness, y + thickness, w - thickness * 2, h - thickness * 2, insideColor);
        drawRect(x, y, w, thickness, borderColor); // Top
        drawRect(x, y + h - thickness, w, thickness, borderColor); // Bottom
        drawRect(x, y, thickness, h, borderColor); // Left
        drawRect(x + w - thickness, y, thickness, h, borderColor); // Right
    }
}
