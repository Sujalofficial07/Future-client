package com.futureclient.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import java.awt.Color;

/**
 * Small helper utilities for GUI rendering. Keep operations minimal and cached where possible.
 */
public class GuiElements {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void drawStringScaled(String text, int x, int y, float scale, int color) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0);
        GL11.glScalef(scale, scale, 1f);
        mc.fontRendererObj.drawStringWithShadow(text, 0, 0, color);
        GL11.glPopMatrix();
    }

    public static int alpha(int color, int a) {
        Color c = new Color(color);
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), a).getRGB();
    }
}