package com.futureclient.modules;

import com.futureclient.core.Module;
import com.futureclient.core.Category;
import com.futureclient.gui.GuiElements;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

/**
 * Render player's coordinates compactly.
 */
public class CoordinatesModule extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public CoordinatesModule() {
        super("Coordinates", Category.HUD, Keyboard.KEY_NONE);
    }

    @Override
    public void onRender() {
        if (mc.thePlayer == null) return;
        int x = (int) mc.thePlayer.posX;
        int y = (int) mc.thePlayer.posY;
        int z = (int) mc.thePlayer.posZ;
        GuiElements.drawStringScaled("XYZ: " + x + " / " + y + " / " + z, mc.displayWidth / 4, 6, 1.0f, 0xFFFFFF);
    }
}